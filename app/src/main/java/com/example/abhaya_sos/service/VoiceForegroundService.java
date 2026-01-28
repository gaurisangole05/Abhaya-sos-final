package com.abhaya.sos.service;

import android.app.*;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.*;
import com.abhaya.sos.sos.SOSManager;
import java.util.ArrayList;

public class VoiceForegroundService extends Service {

    SpeechRecognizer recognizer;
    int count = 0;
    String KEYWORD = "help abhaya";

    @Override
    public void onCreate() {
        startForeground(1, createNotification());
        startListening();
    }

    private void startListening() {

        recognizer = SpeechRecognizer.createSpeechRecognizer(this);
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        recognizer.setRecognitionListener(new RecognitionListener() {

            public void onResults(Bundle results) {
                ArrayList<String> list =
                        results.getStringArrayList(
                                SpeechRecognizer.RESULTS_RECOGNITION);

                if (list != null) {
                    for (String s : list) {
                        if (s.toLowerCase().contains(KEYWORD)) {
                            count++;
                            if (count == 2) {
                                SOSManager.activate(
                                        VoiceForegroundService.this);
                                count = 0;
                            }
                        }
                    }
                }
                startListening();
            }

            public void onError(int error) { startListening(); }
            public void onReadyForSpeech(Bundle params) {}
            public void onBeginningOfSpeech() {}
            public void onRmsChanged(float rmsdB) {}
            public void onBufferReceived(byte[] buffer) {}
            public void onEndOfSpeech() {}
            public void onPartialResults(Bundle partialResults) {}
            public void onEvent(int eventType, Bundle params) {}
        });

        recognizer.startListening(i);
    }

    private Notification createNotification() {
        NotificationChannel ch = new NotificationChannel(
                "voice", "Voice Service",
                NotificationManager.IMPORTANCE_LOW);

        getSystemService(NotificationManager.class)
                .createNotificationChannel(ch);

        return new Notification.Builder(this, "voice")
                .setContentTitle("ABHAYA SOS Active")
                .setContentText("Listening for emergency keyword")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .build();
    }

    public IBinder onBind(Intent intent) { return null; }
}
