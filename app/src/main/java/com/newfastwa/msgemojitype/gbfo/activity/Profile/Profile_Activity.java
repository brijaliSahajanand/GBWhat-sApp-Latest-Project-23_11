package com.newfastwa.msgemojitype.gbfo.activity.Profile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.utils.Header;

public class Profile_Activity extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback {

    EditText wNum;
    LinearLayout ln_search_profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);



        ln_search_profile = findViewById(R.id.ln_search_profile);
        wNum = findViewById(R.id.wNum);

        ln_search_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!wNum.getText().toString().isEmpty()) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s", new Object[]{wNum.getText().toString()/*, this.wMsg.getText().toString()*/}))));
                   /* if (wMsg.getText().toString().isEmpty()) {
                        str = "No message found.";
                    } else {
                        str = wMsg.getText().toString();
                    }
                    AddData(this.wNum.getText().toString(), getDateTime(), str);*/
                }
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
