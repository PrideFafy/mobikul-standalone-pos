package com.webkul.mobikul.mobikulstandalonepos.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.telpo.tps550.api.TelpoException;
import com.telpo.tps550.api.magnetic.MagneticCard;

public class MagneticCardUtil {
    static Thread readThread;


    //initialize MagneticCardDevice
    public static void init(final Context context){
        try {
            MagneticCard.open(context);
        } catch (Exception e) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Could not open magnetic card device");
            alertDialog.show();
        }
    }

    //Start swipe read thread
    public static void swipe(){
        readThread = new ReadThread();
        readThread.start();
    }

    //cancel read thread
    public static void cancel(){
        readThread.interrupt();
        readThread = null;
    }

    //Message handler
    public static Handler getHandler(){
        return new Handler()
        {

            @Override
            public void handleMessage(Message msg)
            {
                //TracData is data returned after Swiping
                String[] TracData = (String[])msg.obj;
                for(int i=0; i<3; i++){
                    if(TracData[i] != null){
                        switch (i)
                        {
                            case 0:
                                System.out.println(TracData[i]);
                                break;
                            case 1:
                                System.out.println(TracData[i]);
                                break;
                            case 2:
                                System.out.println(TracData[i]);
                                break;
                        }

                    }
                }
            }

        };
    }

    //Close Magntic Card Device.. Even after you cancel th read thread you have to close device
    public static void close(){
        if (readThread != null)
        {
            readThread.interrupt();
        }
        MagneticCard.close();
    }

    //Read Thread fo listening to Mgnetic Card Device and return data
    private static class ReadThread extends Thread
    {
        String[] TracData = null;

        Handler handler = MagneticCardUtil.getHandler();

        @Override
        public void run()
        {
            MagneticCard.startReading();
            while (!Thread.interrupted()){
                try{
                    TracData = MagneticCard.check(1000);
                    handler.sendMessage(handler.obtainMessage(1, TracData));
                    MagneticCard.startReading();
                }catch (TelpoException e){
                }
            }
        }

    }
}
