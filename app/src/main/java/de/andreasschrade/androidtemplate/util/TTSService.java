package de.andreasschrade.androidtemplate.util;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class TTSService extends Service implements TextToSpeech.OnInitListener {

    private TextToSpeech textToSpeech;
    private boolean isInit;
    public static final String TEXT = null;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        final String text = "<speak>"+intent.getStringExtra(TEXT)+"</speak>";

        if(isInit) {
            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            final Handler handler = new Handler();
            final Runnable r = new Runnable() {
                @Override
                public void run() {
                    if (isInit) {
                        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
                    } else {
                        handler.postDelayed(this, 50);
                    }
                }
            };
            handler.postDelayed(r, 10);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            isInit = true;

            int ttsLang = textToSpeech.setLanguage(Locale.US);
            textToSpeech.setSpeechRate((float)0.95);

            if (ttsLang == TextToSpeech.LANG_MISSING_DATA || ttsLang == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTSService", "The Language is not supported!");
            } else {
                Log.i("TTSService", "Language supported.");
            }
            Log.i("TTSService", "Initialization success.");
        } else {
            Log.e("TTSService", "Could not initialize TextToSpeech.");
        }
    }
}