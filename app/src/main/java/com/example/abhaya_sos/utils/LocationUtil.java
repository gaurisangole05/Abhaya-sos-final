package com.abhaya.sos.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import com.google.android.gms.location.*;

public class LocationUtil {

    public interface Callback {
        void onLocation(Location location);
    }

    @SuppressLint("MissingPermission")
    public static void getLocation(Context c, Callback cb) {
        FusedLocationProviderClient client =
                LocationServices.getFusedLocationProviderClient(c);
        client.getLastLocation().addOnSuccessListener(cb::onLocation);
    }
}

