package com.newfastwa.msgemojitype.gbfo.activity.Font_style;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.utils.Header;

public class Activity_Entered_Text extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback {

    LinearLayout ln_generate_style;
    LinearLayout ln_stylish_number;
    EditText wNum;
    String stylish_style;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entered_text);

        Intent intent = getIntent();
        stylish_style = intent.getStringExtra("stylish_style");

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

        wNum = findViewById(R.id.wNum);
        ln_stylish_number = findViewById(R.id.ln_stylish_number);
        ln_generate_style = findViewById(R.id.ln_generate_style);

        if (stylish_style.equals("number")) {
            wNum.setInputType(InputType.TYPE_CLASS_PHONE);
        } else if (stylish_style.equals("name")) {
            wNum.setInputType(InputType.TYPE_CLASS_TEXT);
        } else if (stylish_style.equals("text")) {
            wNum.setInputType(InputType.TYPE_CLASS_TEXT);
        }


        ln_generate_style.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (wNum.getText().toString().equals("")) {
                    Toast.makeText(Activity_Entered_Text.this, "Enter Text First", Toast.LENGTH_SHORT).show();
                } else {
                    Ads_Interstitial.showAds_full(Activity_Entered_Text.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            passactivity(view);
                        }
                    });


                }
            }
        });
    }

    public void passactivity(View view) {
        if (stylish_style.equals("number")) {
            Intent intent1 = new Intent(Activity_Entered_Text.this, Activity_Stylish_Number.class);
            intent1.putExtra("text_details", wNum.getText().toString());
            startActivity(intent1);
        } else if (stylish_style.equals("name")) {
            Intent intent1 = new Intent(Activity_Entered_Text.this, Activity_Stylish_name.class);
            intent1.putExtra("text_details", wNum.getText().toString());
            startActivity(intent1);
        } else if (stylish_style.equals("text")) {
            Intent intent1 = new Intent(Activity_Entered_Text.this, Activity_Stylish_Text.class);
            intent1.putExtra("text_details", wNum.getText().toString());
            startActivity(intent1);
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
