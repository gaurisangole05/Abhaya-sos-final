package com.abhaya.sos.utils;

import android.Manifest;
import android.app.Activity;
import androidx.core.app.ActivityCompat;

public class PermissionUtil {
    public static void requestAll(Activity a) {
        ActivityCompat.requestPermissions(a,
                new String[]{
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.SEND_SMS,
                        Manifest.permission.ACCESS_FINE_LOCATION
                }, 101);
    }
}
