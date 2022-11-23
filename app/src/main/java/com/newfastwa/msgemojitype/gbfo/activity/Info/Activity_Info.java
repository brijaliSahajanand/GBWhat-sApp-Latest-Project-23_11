package com.newfastwa.msgemojitype.gbfo.activity.Info;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BuildConfig;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.utils.Header;

public class Activity_Info extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback {

    LinearLayout ln_link;
    TextView tv_linked;
    TextView tv_installed_version;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

        ln_link = findViewById(R.id.ln_link);
        tv_linked = findViewById(R.id.tv_linked);
        tv_installed_version = findViewById(R.id.tv_installed_version);


        tv_installed_version.setText(BuildConfig.VERSION_NAME);

        ln_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", tv_linked.getText().toString()));
                Toast.makeText(Activity_Info.this, "Text Copied!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onbacks(Boolean i) {
        onBackPressed();
    }


    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });

    }
}
