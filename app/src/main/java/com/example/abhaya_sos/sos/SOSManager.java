package com.abhaya.sos.sos;

import android.app.*;
import android.content.Context;
import android.media.MediaPlayer;
import android.telephony.SmsManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;
import com.abhaya.sos.utils.LocationUtil;

public class SOSManager {

    public static void activate(Context context) {

        MediaPlayer mp = MediaPlayer.create(
                context,
                android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI);
        mp.start();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getUid();

        LocationUtil.getLocation(context, location -> {

            String msg = "🚨 SOS ALERT 🚨\nLocation:\n" +
                    "https://maps.google.com/?q=" +
                    location.getLatitude() + "," +
                    location.getLongitude();

            db.collection("users")
                    .document(uid)
                    .collection("contacts")
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        for (DocumentSnapshot d : snapshot) {
                            String phone = d.getString("phone");
                            SmsManager.getDefault()
                                    .sendTextMessage(phone, null,
                                            msg, null, null);
                        }
                    });
        });

        NotificationManager nm =
                (NotificationManager)
                        context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationChannel ch = new NotificationChannel(
                "sos", "SOS Alerts",
                NotificationManager.IMPORTANCE_HIGH);

        nm.createNotificationChannel(ch);

        Notification n = new Notification.Builder(context, "sos")
                .setContentTitle("ABHAYA SOS")
                .setContentText("Emergency Triggered")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .build();

        nm.notify(1, n);
    }
}

