package com.newfastwa.msgemojitype.gbfo.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;

import java.util.ArrayList;
import java.util.List;


public class ThiredActivity extends com.newfastwa.msgemojitype.gbfo.BaseActivity {

    Button iv_nxt_button;
    LinearLayout ln_rate_us;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        iv_nxt_button = findViewById(R.id.iv_nxt_button);
        ln_rate_us = findViewById(R.id.ln_rate_us);
        ln_rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID));
                startActivity(intent);*/
            }
        });


        iv_nxt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (askPermissions(true)) {

                    Ads_Interstitial.showAds_full(ThiredActivity.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            Intent intent = new Intent(ThiredActivity.this, App_MainActivity.class);
                            startActivity(intent);
                        }
                    });


                }
            }
        });
    }


    Boolean isRationale;

    private Boolean askPermissions(boolean isForOpen) {
        isRationale = false;
        List permissionsRequired = new ArrayList();

        final List<String> permissionsList = new ArrayList<String>();

        if (!checkPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE))
            permissionsRequired.add("Write External Storage");
        if (!checkPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsRequired.add("Read External Storage");

        if (permissionsList.size() > 0 && !isRationale) {
            if (permissionsRequired.size() > 0) {

            }
            if (isForOpen) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ActivityCompat.requestPermissions(this, permissionsList.toArray(new String[permissionsList.size()]),
                            11);
                }
            }

        } else if (isRationale) {
            if (isForOpen) {

                new AlertDialog.Builder(this, R.style.MyAlertDialogStyle)
                        .setTitle("Permission Alert")
                        .setMessage("You need to grant permissions manually. Go to permission and grant all permissions.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                Uri uri = Uri.fromParts("package", getPackageName(), null);
                                intent.setData(uri);
                                startActivityForResult(intent, 123);
                            }
                        })
                        .show();
            }
        } else {
            return true;
        }
        return false;
    }

    Boolean isFirst = true;

    private boolean checkPermission(List permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!isFirst) {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    isRationale = true;
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressLint("NewApi")
    public static void url_passing(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Bundle bundle = new Bundle();
            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
            intent.putExtras(bundle);
            //intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", C3962e.m14208a(this, R.color.colorPrimary));
            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
            intent.setPackage("com.android.chrome");
            intent.setData(Uri.parse(Preference.getQureka_link()));
            context.startActivity(intent, (Bundle) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean exit_flag = false;

    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                Intent intent = new Intent(ThiredActivity.this, Second_Activity.class);
                startActivity(intent);
                finish();

            }
        });

    }


}
