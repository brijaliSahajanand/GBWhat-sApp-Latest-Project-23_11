package com.newfastwa.msgemojitype.gbfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;

public class Second_Activity extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements View.OnClickListener {

    Button iv_get_started;
    LinearLayout  ln_share_app;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        iv_get_started = findViewById(R.id.iv_get_started);
        ln_share_app = findViewById(R.id.ln_share_app);


        iv_get_started.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ads_Interstitial.showAds_full(Second_Activity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        if (Preference.getScreen_show() != 2) {
                            Intent intent = new Intent(Second_Activity.this, ThiredActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(Second_Activity.this, App_MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });


            }
        });

        ln_share_app.setOnClickListener(this);

    }

    boolean exit_flag = false;

    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                Intent intent = new Intent(Second_Activity.this, FirstActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    @Override
    public void onClick(View view) {
         if (view.getId() == R.id.ln_share_app) {
           /* try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.app_name));
                String shareMessage = "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch (Exception e) {
                //e.toString();
            }*/
        }
    }
}
