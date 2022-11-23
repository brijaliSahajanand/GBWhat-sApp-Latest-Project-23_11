package com.newfastwa.msgemojitype.gbfo.activity.Font_style;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.utils.Header;

public class Activity_Stylish_Text extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback{

    RecyclerView rv_stylish_name;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylish_text);

        rv_stylish_name = findViewById(R.id.rv_stylish_name);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("text_details");


        Adapter_Auto_generate adapter_auto_generate = new Adapter_Auto_generate(Activity_Stylish_Text.this, new String[]{".•♥•.¸¸.•♥•" + stringExtra + "•♥•.¸¸.•♥•.",
                "♥♥" + stringExtra + "♥♥",
                "★彡" + stringExtra + "彡☆",
                "❋. _ " + stringExtra + "_ .❋",
                "ღ。" + stringExtra + "★ღ",
                "♥•*" + stringExtra + "*•♥",
                "*..." + stringExtra + "...*",
                "♥" + stringExtra + "♥",
                "L●L" + stringExtra + "L●L",
                "●♥" + stringExtra + "♥●",
                "_ •" + stringExtra + "• _",
                "✪☻" + stringExtra + "✪☻",
                "✿¨¯`✿" + stringExtra + "✿¨¯`✿",
                "»♥" + stringExtra + "»♥",
                "░" + stringExtra + "░",
                "▒░░" + stringExtra + "░▒▓",
                "*´♥`*" + stringExtra + "*´♥`*",
                "●́‿●" + stringExtra + "●́‿●",
                "♬♪♫" + stringExtra + "♬♪♫",
                "◦'⌣'◦" + stringExtra + "◦'⌣'◦",
                "ξ◕◡◕ξ" + stringExtra + "ξ◕◡◕ξ",
                "✿" + stringExtra + "✿",
                "❆❆" + stringExtra + "❆❆",
                "╠♥╣" + stringExtra + "╠♥╣",
                "►" + stringExtra + "◄",
                ":̲̅:" + stringExtra + ":̲̅:",
                "︻┳═一" + stringExtra,
                "꧁༒☬" + stringExtra + "☬༒꧂",
                "꧁༺" + stringExtra + "༻꧂",
                "꧁༒♛" + stringExtra + "♛༒꧂",
                "◥꧁ད" + stringExtra + "ཌ꧂◤",
                "亗『" + stringExtra + "』亗",
                "༒☬〖ℳℜ〗" + stringExtra + "73☬༒"

        }, "");
        rv_stylish_name.setAdapter(adapter_auto_generate);

        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.item_anim);
        rv_stylish_name.setLayoutAnimation(controller);
        adapter_auto_generate.notifyDataSetChanged();
        rv_stylish_name.scheduleLayoutAnimation();

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


class Adapter_Auto_generate extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Activity ctx;
    public String tab_string;
    public String[] items;

    public Adapter_Auto_generate(Activity context, String[] strings, String tab_string) {
        this.items = strings;
        this.ctx = context;
        this.tab_string = tab_string;
    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OriginalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof OriginalViewHolder) {
            ((OriginalViewHolder) viewHolder).text.setText(items[i]);

            ((OriginalViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Ads_Interstitial.showAds_full(ctx, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            Intent intent = new Intent(ctx, Activity_Stylish_share.class);
                            intent.putExtra("selected_text", items[i]);
                            ctx.startActivity(intent);
                        }
                    });



                }
            });
        }
    }

    public int getItemCount() {
        return this.items.length;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public OriginalViewHolder(View view) {
            super(view);

            text = view.findViewById(R.id.text);
        }
    }
}
