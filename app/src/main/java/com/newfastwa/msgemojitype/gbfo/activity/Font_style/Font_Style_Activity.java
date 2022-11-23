package com.newfastwa.msgemojitype.gbfo.activity.Font_style;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.utils.Header;

public class Font_Style_Activity extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements View.OnClickListener, Header.onback{


    LinearLayout ln_stylish_number, ln_stylish_name, ln_stylish_text, ln_Stylish_emojis, ln_stylish_Art;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_style);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

        ln_stylish_Art = findViewById(R.id.ln_stylish_Art);
        ln_Stylish_emojis = findViewById(R.id.ln_Stylish_emojis);
        ln_stylish_text = findViewById(R.id.ln_stylish_text);
        ln_stylish_name = findViewById(R.id.ln_stylish_name);
        ln_stylish_number = findViewById(R.id.ln_stylish_number);

        ln_stylish_number.setOnClickListener(this);
        ln_stylish_name.setOnClickListener(this);
        ln_stylish_text.setOnClickListener(this);
        ln_Stylish_emojis.setOnClickListener(this);
        ln_stylish_Art.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Ads_Interstitial.showAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                passactivity(view);
            }
        });

    }

    public void passactivity(View view) {
        if (view == ln_stylish_number) {
            Intent intent = new Intent(Font_Style_Activity.this, Activity_Entered_Text.class);
            intent.putExtra("stylish_style", "number");
            startActivity(intent);
        } else if (view == ln_stylish_name) {
            Intent intent = new Intent(Font_Style_Activity.this, Activity_Entered_Text.class);
            intent.putExtra("stylish_style", "name");
            startActivity(intent);
        } else if (view == ln_stylish_text) {
            Intent intent = new Intent(Font_Style_Activity.this, Activity_Entered_Text.class);
            intent.putExtra("stylish_style", "text");
            startActivity(intent);
        } else if (view == ln_Stylish_emojis) {

            Intent intent = new Intent(Font_Style_Activity.this, Activity_Stylish_Emojis_List.class);
            startActivity(intent);
        } else if (view == ln_stylish_Art) {
            Intent intent = new Intent(Font_Style_Activity.this, Activity_Stylish_Art.class);
            startActivity(intent);
        }
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
