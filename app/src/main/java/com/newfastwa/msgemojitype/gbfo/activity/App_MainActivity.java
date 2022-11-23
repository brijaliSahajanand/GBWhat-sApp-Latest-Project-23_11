package com.newfastwa.msgemojitype.gbfo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.VPN.Connect_Network_Screen;
import com.newfastwa.msgemojitype.gbfo.activity.Captions.Activity_Caption_Main;
import com.newfastwa.msgemojitype.gbfo.activity.Emojis.Activity_Text_TO_Emoji;
import com.newfastwa.msgemojitype.gbfo.activity.Font_style.Font_Style_Activity;
import com.newfastwa.msgemojitype.gbfo.activity.Info.Activity_Info;
import com.newfastwa.msgemojitype.gbfo.activity.Profile.Profile_Activity;
import com.newfastwa.msgemojitype.gbfo.activity.Quick_REply.Activity_Quick_Reply;
import com.newfastwa.msgemojitype.gbfo.activity.Repeater.Activity_Text_Repeater;
import com.newfastwa.msgemojitype.gbfo.activity.Status.WAstatusActivity;
import com.newfastwa.msgemojitype.gbfo.floating.CircleMenuView;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;

public class App_MainActivity extends BaseActivity implements View.OnClickListener {

    ImageView iv_emoji, iv_fontstyle, iv_profile, iv_status, iv_reply, iv_repeater, iv_caption, iv_wa_info;
    ImageView iv_vpn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_main);

        iv_vpn = findViewById(R.id.iv_vpn);


        if (Preference.getVPN_Show()) {
            iv_vpn.setVisibility(View.VISIBLE);
        } else {
            iv_vpn.setVisibility(View.GONE);

        }

        iv_vpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Ads_Interstitial.showAds_full(App_MainActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Intent intent = new Intent(App_MainActivity.this, Connect_Network_Screen.class);
                        intent.putExtra("type_connection", "disconnect");
                        startActivity(intent);
                    }
                });



            }
        });

        iv_status = findViewById(R.id.iv_status);
        iv_profile = findViewById(R.id.iv_profile);
        iv_fontstyle = findViewById(R.id.iv_fontstyle);
        iv_emoji = findViewById(R.id.iv_emoji);
        iv_reply = findViewById(R.id.iv_reply);
        iv_repeater = findViewById(R.id.iv_repeater);
        iv_caption = findViewById(R.id.iv_caption);
        iv_wa_info = findViewById(R.id.iv_wa_info);

        iv_status.setOnClickListener(this);
        iv_profile.setOnClickListener(this);
        iv_fontstyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("22/11 - ", "Calling");
                Ads_Interstitial.showAds_full(App_MainActivity.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Intent intent = new Intent(App_MainActivity.this, Font_Style_Activity.class);
                        startActivity(intent);
                    }
                });


            }
        });
        iv_emoji.setOnClickListener(this);
        iv_reply.setOnClickListener(this);
        iv_repeater.setOnClickListener(this);
        iv_caption.setOnClickListener(this);
        iv_wa_info.setOnClickListener(this);

        final CircleMenuView menu = findViewById(R.id.circle_menu);
        menu.setEventListener(new CircleMenuView.EventListener() {
            @Override
            public void onMenuOpenAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationStart");
            }

            @Override
            public void onMenuOpenAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuOpenAnimationEnd");
            }

            @Override
            public void onMenuCloseAnimationStart(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationStart");
            }

            @Override
            public void onMenuCloseAnimationEnd(@NonNull CircleMenuView view) {
                Log.d("D", "onMenuCloseAnimationEnd");
            }

            @Override
            public void onButtonClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonClickAnimationEnd| index: " + index);

                if (index == 0) {
                    Intent intent = new Intent(App_MainActivity.this, WAstatusActivity.class);
                    startActivity(intent);
                } else if (index == 1) {
                    Intent intent = new Intent(App_MainActivity.this, Profile_Activity.class);
                    startActivity(intent);
                } else if (index == 2) {
                    Intent intent = new Intent(App_MainActivity.this, Font_Style_Activity.class);
                    startActivity(intent);
                } else if (index == 3) {
                    Intent intent = new Intent(App_MainActivity.this, Activity_Text_TO_Emoji.class);
                    startActivity(intent);
                } else if (index == 4) {
                    Intent intent = new Intent(App_MainActivity.this, Activity_Text_Repeater.class);
                    startActivity(intent);
                } else if (index == 5) {
                    Intent intent = new Intent(App_MainActivity.this, Activity_Quick_Reply.class);
                    startActivity(intent);
                } else if (index == 6) {
                    Intent intent = new Intent(App_MainActivity.this, Activity_Caption_Main.class);
                    startActivity(intent);
                } else if (index == 7) {
                    Intent intent = new Intent(App_MainActivity.this, Activity_Info.class);
                    startActivity(intent);
                }
            }

            @Override
            public boolean onButtonLongClick(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClick| index: " + index);
                return true;
            }

            @Override
            public void onButtonLongClickAnimationStart(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationStart| index: " + index);
            }

            @Override
            public void onButtonLongClickAnimationEnd(@NonNull CircleMenuView view, int index) {
                Log.d("D", "onButtonLongClickAnimationEnd| index: " + index);
            }
        });
    }

    @Override
    public void onClick(View index) {
        if (index == iv_status) {
            if (Preference.getWhatsapp_URI().equals("")) {
                Intent intent = new Intent(App_MainActivity.this, WAstatusActivity.class);
                startActivity(intent);
            } else {
                Ads_Interstitial.showAds_full(this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Intent intent = new Intent(App_MainActivity.this, WAstatusActivity.class);
                        startActivity(intent);
                    }
                });
            }
        } else
            Ads_Interstitial.showAds_full(this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    Log.d("22/11 - ", "Calling");
                    if (index == iv_profile) {
                        Intent intent = new Intent(App_MainActivity.this, Profile_Activity.class);
                        startActivity(intent);
                    } else if (index == iv_fontstyle) {
                        Intent intent = new Intent(App_MainActivity.this, Font_Style_Activity.class);
                        startActivity(intent);
                    } else if (index == iv_emoji) {
                        Intent intent = new Intent(App_MainActivity.this, Activity_Text_TO_Emoji.class);
                        startActivity(intent);
                    } else if (index == iv_repeater) {
                        Intent intent = new Intent(App_MainActivity.this, Activity_Text_Repeater.class);
                        startActivity(intent);
                    } else if (index == iv_reply) {
                        Intent intent = new Intent(App_MainActivity.this, Activity_Quick_Reply.class);
                        startActivity(intent);
                    } else if (index == iv_caption) {
                        Intent intent = new Intent(App_MainActivity.this, Activity_Caption_Main.class);
                        startActivity(intent);
                    } else if (index == iv_wa_info) {
                        Intent intent = new Intent(App_MainActivity.this, Activity_Info.class);
                        startActivity(intent);
                    }


//                    passactivity(view);
                }
            });
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

    public void passactivity(View index) {
        if (index == iv_profile) {
            Intent intent = new Intent(App_MainActivity.this, Profile_Activity.class);
            startActivity(intent);
        } else if (index == iv_fontstyle) {
            Intent intent = new Intent(App_MainActivity.this, Font_Style_Activity.class);
            startActivity(intent);
        } else if (index == iv_emoji) {
            Intent intent = new Intent(App_MainActivity.this, Activity_Text_TO_Emoji.class);
            startActivity(intent);
        } else if (index == iv_repeater) {
            Intent intent = new Intent(App_MainActivity.this, Activity_Text_Repeater.class);
            startActivity(intent);
        } else if (index == iv_reply) {
            Intent intent = new Intent(App_MainActivity.this, Activity_Quick_Reply.class);
            startActivity(intent);
        } else if (index == iv_caption) {
            Intent intent = new Intent(App_MainActivity.this, Activity_Caption_Main.class);
            startActivity(intent);
        } else if (index == iv_wa_info) {
            Intent intent = new Intent(App_MainActivity.this, Activity_Info.class);
            startActivity(intent);
        }
    }
}
