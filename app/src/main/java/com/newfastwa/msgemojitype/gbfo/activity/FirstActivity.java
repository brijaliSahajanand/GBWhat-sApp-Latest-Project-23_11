package com.newfastwa.msgemojitype.gbfo.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ads.adsdemosp.AdsClass.Ads_ExitNativeFull;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BuildConfig;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;

import java.util.ArrayList;
import java.util.List;

import static com.newfastwa.msgemojitype.gbfo.utils.Utils.Exit_Dialog;

public class FirstActivity extends com.newfastwa.msgemojitype.gbfo.BaseActivity {


    LinearLayout ln_privacy_app, ln_share_app, ln_rate_us;
    Button iv_get_started;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        ln_privacy_app = findViewById(R.id.ln_privacy_app);
        iv_get_started = findViewById(R.id.iv_get_started);
        ln_share_app = findViewById(R.id.ln_share_app);
        ln_rate_us = findViewById(R.id.ln_rate_us);


        ln_privacy_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Preference.getPrivacy_policy().isEmpty()) {

                    String urlString = Preference.getPrivacy_policy();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.android.chrome");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null);
                        startActivity(intent);
                    }
                }
            }
        });

        ln_share_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

        ln_rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID));
                startActivity(intent);
            }
        });

        iv_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (askPermissions(true)) {
                    Ads_Interstitial.showAds_full(FirstActivity.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            if (Preference.getScreen_show() != 1) {
                                Intent intent = new Intent(FirstActivity.this, Second_Activity.class);
                                startActivity(intent);
                            } else {
                                Intent intent = new Intent(FirstActivity.this, App_MainActivity.class);
                                startActivity(intent);
                            }

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


    boolean exit_flag = false;

    @Override
    public void onBackPressed() {
        if (Ads_ExitNativeFull.checkExitAdsLoaded()) {
            Exit_Dialog(FirstActivity.this);
        } else {
            if (exit_flag) {
                finishAffinity();
            } else {
                exit_flag = true;
                Toast.makeText(this, "Please tap again!", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit_flag = false;
                    }
                }, 3000);
            }
        }


    }
}
