package com.newfastwa.msgemojitype.gbfo.activity.Font_style;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.utils.Header;

public class Activity_Stylish_share extends BaseActivity implements Header.onback{

    LinearLayout ln_copy, ln_whatsapp, ln_more_share;
    TextView tv_selected_txt;
    String selected_text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylish_share);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);


        ln_copy = findViewById(R.id.ln_copy);
        ln_more_share = findViewById(R.id.ln_more_share);
        ln_whatsapp = findViewById(R.id.ln_whatsapp);
        tv_selected_txt = findViewById(R.id.tv_selected_txt);

        Intent intent = getIntent();
        selected_text = intent.getStringExtra("selected_text");
        tv_selected_txt.setText(selected_text);



        ln_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Text", tv_selected_txt.getText().toString()));
                Toast.makeText(Activity_Stylish_share.this, "Text Copied!", Toast.LENGTH_SHORT).show();
            }
        });

        ln_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean installed = isAppInstalled("com.whatsapp");
               /* if(installed) {

                } else {
                    Toast.makeText(Activity_Stylish_share.this, "App Not installed", Toast.LENGTH_SHORT).show();

                }*/

                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, selected_text);

                if (whatsappIntent.resolveActivity(getPackageManager()) == null) {
                    Toast.makeText(Activity_Stylish_share.this, "Whatsapp not installed.", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(whatsappIntent);
            }
        });

        ln_more_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("text/plain");
                intent.putExtra("android.intent.extra.SUBJECT", getResources().getString(R.string.app_name));
                intent.putExtra("android.intent.extra.TEXT", tv_selected_txt.getText().toString());
                startActivity(Intent.createChooser(intent, "choose one"));
            }
        });
    }

    private boolean isAppInstalled(String packageName) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
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
