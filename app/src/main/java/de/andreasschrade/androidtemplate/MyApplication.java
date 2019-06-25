package de.andreasschrade.androidtemplate;

import android.app.Application;
import android.content.Intent;

import de.andreasschrade.androidtemplate.util.TTSService;
import de.andreasschrade.androidtemplate.util.TTSStarter;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Intent serviceIntent = new Intent(getApplicationContext(), TTSService.class);
        String text = "Welcome to the text to speech example app. To use the speech output, just perform a long click on any written text!";
        serviceIntent.putExtra(TTSService.TEXT, text);
        startService(serviceIntent);
    }
}
