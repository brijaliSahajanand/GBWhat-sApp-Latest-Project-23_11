package com.newfastwa.msgemojitype.gbfo.VPN;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.Appcontroller;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.activity.Profile_Setup_Activity;
import com.newfastwa.msgemojitype.gbfo.activity.FirstActivity;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;
import com.newfastwa.msgemojitype.gbfo.utils.Utils;


public class Privacy_Policy_Screen extends BaseActivity {

    //    WebView webView;
    TextView textView;
    boolean exit_flag = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy__policy__screen);


        textView = (TextView) findViewById(R.id.txt_privacy);
        TextView next = (TextView) findViewById(R.id.next);
        //UserDataGet();
        Utils.isConnectingToInternet(Privacy_Policy_Screen.this, new Utils.OnCheckNet() {
            @Override
            public void OnCheckNet(boolean b) {
                if (b) {
                  /*webView.setWebViewClient(new MyWebViewClient());
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.getSettings().setDisplayZoomControls(true);*/
                    try {
                        if (Preference.getPrivacy_policy_html() != null) {
                            textView.setMovementMethod(LinkMovementMethod.getInstance());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                textView.setText(Html.fromHtml(Preference.getPrivacy_policy_html(), Html.FROM_HTML_MODE_COMPACT));
                            } else {
                                textView.setText(Html.fromHtml(Preference.getPrivacy_policy_html()));
                            }
                        } else {
                            Toast.makeText(Privacy_Policy_Screen.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //webView.loadUrl("file:///android_asset/pp.html");

                } else {
                    finishAffinity();
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ads_Interstitial.showAds_full(Privacy_Policy_Screen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        if (Preference.getuser_name().isEmpty()) {
                            startActivity(new Intent(Privacy_Policy_Screen.this, Profile_Setup_Activity.class));
                            finish();
                        } else {
                            startActivity(new Intent(Privacy_Policy_Screen.this, FirstActivity.class));
                            finish();
                        }
                    }
                });


            }
        });
    }

    @Override
    public void onBackPressed() {
        if (exit_flag) {
            Appcontroller.fast_start = true;
            finishAffinity();
        } else {
            exit_flag = true;
            Toast.makeText(this, "Please tap again to exit!", Toast.LENGTH_SHORT).show();
        }
    }

    public class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }
}