package com.webkul.mobikul.mobikulstandalonepos.activity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.j256.ormlite.support.ConnectionSource;
import com.journeyapps.barcodescanner.ViewfinderView;
import com.webkul.mobikul.mobikulstandalonepos.BuildConfig;
import com.webkul.mobikul.mobikulstandalonepos.R;
import com.webkul.mobikul.mobikulstandalonepos.connections.VersionChecker;
import com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants;
import com.webkul.mobikul.mobikulstandalonepos.db.ORMDataSource;
import com.webkul.mobikul.mobikulstandalonepos.helper.AppSharedPref;
import com.webkul.mobikul.mobikulstandalonepos.helper.Helper;
import com.webkul.mobikul.mobikulstandalonepos.helper.SweetAlertBox;
import com.webkul.mobikul.mobikulstandalonepos.helper.ToastHelper;
import com.webkul.mobikul.mobikulstandalonepos.helper.ZipManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.concurrent.ExecutionException;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.webkul.mobikul.mobikulstandalonepos.helper.Helper.DB_NAME;
import static com.webkul.mobikul.mobikulstandalonepos.helper.Helper.DB_PATH;
import static com.webkul.mobikul.mobikulstandalonepos.helper.ZipManager.unzip;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String TAG = "SplashScreenActivity";
    private String latestVersion = "1.0";
    private String currentVersion = "1.0";
    private boolean isInternetAvailable;
    private SweetAlertDialog sweetAlert;
    private TextView versionText;
    private boolean isFetched = false;
    //The Android's default system path of your application database.


    void fetch() {
        long cacheExpiration = 3600; // 1 hour in seconds.
        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.


    }

    private void displayWelcomeMessage() {

        Log.d(TAG, "onCreate: " + latestVersion);
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            currentVersion = pInfo.versionName;
            versionText.setText(currentVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        flow();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
versionText = findViewById(R.id.versionNumber);
        checkDataBase();
        displayWelcomeMessage();
        checkPostgres();
        //            latestVersion = new VersionChecker().execute().get();
    }

    private boolean checkDataBase() {
        Log.d(TAG, "checkDataBase: Enter");
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
                    SQLiteDatabase.OPEN_READWRITE);
            checkDB.close();
            Log.d(TAG, "checkDataBase: loaded");
        } catch (SQLiteException e) {
            Log.d(TAG, "checkDataBase: SQLiteException---" + e);
            e.printStackTrace();
            Helper.setDefaultDataBase(this);
            AppSharedPref.setSignedUp(this, true);
        } catch (Exception e) {
            Log.d(TAG, "checkDataBase: Exception " + e);
            e.printStackTrace();
            Helper.setDefaultDataBase(this);
            AppSharedPref.setSignedUp(this, true);
        }
        return checkDB != null;
    }

    private boolean checkPostgres(){
        ConnectionSource connectionSource = null;
        try{
            connectionSource = ORMDataSource.createDataSource();
            Helper.setDefaultConnection(connectionSource);
        }catch (Exception e){
            Log.d(TAG, "check Postgres Connection: Exception " + e);
        }
        return  connectionSource !=null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetch();
    }

    void flow() {
        isOnline();
        if (isInternetAvailable) {
            if (Float.parseFloat(latestVersion) > Float.parseFloat(currentVersion)) {
                if (sweetAlert != null) {
                    sweetAlert.dismissWithAnimation();
                }
                sweetAlert = new SweetAlertDialog(SplashScreenActivity.this, SweetAlertDialog.WARNING_TYPE);
                sweetAlert.setTitleText(getString(R.string.warning))
                        .setContentText(getResources().getString(R.string.new_version_available))
                        .setConfirmText(getResources().getString(R.string.update))
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "&hl=en"));
                                startActivity(i);
                                AppSharedPref.deleteSignUpdata(SplashScreenActivity.this);
                                isFetched = false;
                            }
                        })
                        .setCancelText(getResources().getString(R.string.later))
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                connection(500);
                            }
                        })
                        .show();
                sweetAlert.setCancelable(false);
            } else
                connection(2000);
        } else {
            connection(2000);
        }
    }

    void connection(int time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (AppSharedPref.isShowWalkThrough(SplashScreenActivity.this, false)) {
//                    Intent i = new Intent(SplashScreenActivity.this, WalkthroughActivity.class);
//                    startActivity(i);
//                    SplashScreenActivity.this.finish();
//                } else
                Log.d(TAG, "run: " + AppSharedPref.isLoggedIn(SplashScreenActivity.this, false));
                if (AppSharedPref.isLoggedIn(SplashScreenActivity.this, false)) {
                    Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(i);
                    SplashScreenActivity.this.finish();
                } else {
                    Intent i = new Intent(SplashScreenActivity.this, SignUpSignInActivity.class);
                    startActivity(i);
                    SplashScreenActivity.this.finish();
                }
            }
        }, time);
    }

    public static int versionCompare(String str1, String str2) {
        String[] vals1 = str1.split("\\.");
        String[] vals2 = str2.split("\\.");
        int i = 0;
        while (i < vals1.length && i < vals2.length && vals1[i].equals(vals2[i])) {
            i++;
        }
        if (i < vals1.length && i < vals2.length) {
            int diff = Integer.valueOf(vals1[i]).compareTo(Integer.valueOf(vals2[i]));
            return Integer.signum(diff);
        }
        return Integer.signum(vals1.length - vals2.length);
    }

    public void isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        isInternetAvailable = !(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable());
    }
}