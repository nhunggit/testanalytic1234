package com.example.testanalytic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.TelephonyManager;
import android.view.View;

import com.google.android.gms.measurement.module.Analytics;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
FirebaseAnalytics firebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAnalytics=FirebaseAnalytics.getInstance(this);
    }
    public void setData(){
        firebaseAnalytics.setUserId(getImei(this));
        Bundle bundle = new Bundle();
        bundle.putInt("count", 10);
        firebaseAnalytics.logEvent("home", bundle);
    }

    public void onClick(View view) {
        setData();
    }
    public static String getImei(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (null == telephonyManager) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return telephonyManager.getImei();
        }else
            return telephonyManager.getDeviceId();
    }
}
