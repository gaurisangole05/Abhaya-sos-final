package com.abhaya.sos.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.abhaya.sos.service.VoiceForegroundService;
import com.abhaya.sos.utils.PermissionUtil;

public class DashboardActivity extends AppCompatActivity {

    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_dashboard);

        PermissionUtil.requestAll(this);
        startService(new Intent(this, VoiceForegroundService.class));
    }
}

