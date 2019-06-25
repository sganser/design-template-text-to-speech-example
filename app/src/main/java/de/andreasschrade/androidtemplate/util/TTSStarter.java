package de.andreasschrade.androidtemplate.util;

import android.app.Activity;
import android.content.Intent;

public class TTSStarter {

    public static void startTTS(Activity activity, String text) {
        Intent tts = new Intent(activity, TTSService.class);
        tts.putExtra(TTSService.TEXT, text);
        activity.startService(tts);
    }
}
