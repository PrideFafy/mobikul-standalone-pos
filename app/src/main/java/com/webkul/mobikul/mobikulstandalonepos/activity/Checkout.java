package com.webkul.mobikul.mobikulstandalonepos.activity;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.printer.UsbThermalPrinter;
import com.webkul.mobikul.mobikulstandalonepos.R;
import com.webkul.mobikul.mobikulstandalonepos.databinding.ActivityCheckoutBinding;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Product;
import com.webkul.mobikul.mobikulstandalonepos.handlers.CheckoutHandler;
import com.webkul.mobikul.mobikulstandalonepos.helper.AppSharedPref;
import com.webkul.mobikul.mobikulstandalonepos.model.CartModel;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Checkout extends BaseActivity {
    public ActivityCheckoutBinding checkoutBinding;
    CartModel cartData;
    UsbThermalPrinter usbThermalPrinter = new UsbThermalPrinter(this);

    private Button checkout;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkoutBinding = DataBindingUtil.setContentView(this, R.layout.activity_checkout);
        setSupportActionBar(checkoutBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (getIntent().getExtras().containsKey("cartData")) {
            cartData = (CartModel) getIntent().getExtras().getSerializable("cartData");
            checkoutBinding.setData(cartData);
        }
//        checkout=findViewById(R.id.cc_button);
//        checkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            usbThermalPrinter.start(1);
//                            printExample(cartData);
//                        } catch (TelpoException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();
//            }
//        });
        checkoutBinding.setVisibility(AppSharedPref.isCashEnabled(this, true));
        checkoutBinding.setHandler(new CheckoutHandler(this));
        checkoutBinding.setHasReturn(AppSharedPref.isReturnCart(this));
    }


    private void printExample(final CartModel cartData){
        new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    try {
                        usbThermalPrinter.reset();
                        usbThermalPrinter.setMonoSpace(true);
                        usbThermalPrinter.setAlgin(UsbThermalPrinter.ALGIN_MIDDLE);
                        usbThermalPrinter.setTextSize(30);
                        usbThermalPrinter.setGray(2);
                         usbThermalPrinter.addString("POS SALES SLIP\n");
                        usbThermalPrinter.setAlgin(UsbThermalPrinter.ALGIN_LEFT);
                        usbThermalPrinter.setTextSize(24);
                        usbThermalPrinter.addString("MERCHANT NAME:             " + cartData.getCustomer().getLastName());
                        usbThermalPrinter.addString("MERCHANT SURNAME:                  " + cartData.getCustomer().getLastName());
                        usbThermalPrinter.addString("TERMINAL NO:                  02");

                        for(Product p : cartData.getProducts()){
                            usbThermalPrinter.addString(p.getProductName() + "       x"+p.getCartQty()+"     " + p.getPrice());
                        }
                        int i = usbThermalPrinter.measureText("CARD NO:" + "653256689565545");
                        int i1 = usbThermalPrinter.measureText(" ");
                        int SpaceNumber=(384-i)/i1;
                        String spaceString = "";
                        for (int j=0;j<SpaceNumber;j++){
                            spaceString+=" ";
                        }

                        usbThermalPrinter.addString("CARD NO:"+spaceString+"653256689565545");
                        usbThermalPrinter.addString("TRANS TYPE:                GOODS");
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
                        String str = formatter.format(curDate);
                        usbThermalPrinter.addString("DATE/TIME:   "+str);
                        usbThermalPrinter.addString("EXP DATE:             2019-12-30" );
                        usbThermalPrinter.addString("BATCH NO:             2019000168");
                        usbThermalPrinter.addString("REFER NO:             2019001232");
                        i = usbThermalPrinter.measureText("AMOUNT:" + "$"+ cartData.getTotals().getFormatedGrandTotal());
                        i1 = usbThermalPrinter.measureText(" ");
                        SpaceNumber=(384-i)/i1;
                        spaceString = "";
                        for (int j=0;j<SpaceNumber;j++){
                            spaceString+=" ";
                        }
                        usbThermalPrinter.addString("AMOUNT:" + spaceString +"$"+ cartData.getTotals().getSubTotal());
                        usbThermalPrinter.printString();
                        usbThermalPrinter.walkPaper(10);

                    } catch (TelpoException e) {
                        e.printStackTrace();
//                        Result = e.toString();
//                        if (Result.equals("com.telpo.tps550.api.printer.NoPaperException")) {
//                            handler.sendMessage(handler.obtainMessage(NOPAPER, 1, 0, null));
//                        } else if (Result.equals("com.telpo.tps550.api.printer.OverHeatException")) {
//                            handler.sendMessage(handler.obtainMessage(OVERHEAT, 1, 0, null));
//                        }
                    }
                }
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.findItem(R.id.menu_item_search).setVisible(false);
        menu.findItem(R.id.menu_item_scan_barcode).setVisible(false);
        return true;
    }

}
