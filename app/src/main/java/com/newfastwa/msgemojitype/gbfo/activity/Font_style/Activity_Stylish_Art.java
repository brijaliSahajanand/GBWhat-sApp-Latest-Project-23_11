package com.newfastwa.msgemojitype.gbfo.activity.Font_style;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.utils.Header;

public class Activity_Stylish_Art extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback {

    RecyclerView rv_Stylish_art;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylish_art);

        rv_Stylish_art = findViewById(R.id.rv_Stylish_art);


        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

        Adapter_Auto_generate adapter_auto_generate = new Adapter_Auto_generate(this, new String[]{".•♥•.¸¸.•♥•" + "•♥•.¸¸.•♥•.",
                "❋. _ " + "_ .❋",
                "♥•*" + "*•♥",
                "*..." + "...*",
                "L●L",
                "✪☻" + "✪☻",
                "✿¨¯`✿",
                "»♥",
                "░" + "░",
                "▒░░" + "░▒▓",
                "*´♥`*",
                "●́‿●",
                "♬♪♫",
                "◦'⌣'◦",
                "ξ◕◡◕ξ",
                "❆❆",
                "╠♥╣",
                "︻┳═一",
                "꧁༒☬" + "☬༒꧂",
                "꧁༺" + "༻꧂",
                "꧁༒♛" + "♛༒꧂",
                "◥꧁ད" + "ཌ꧂◤",
                "亗『" + "』亗",
                "༒☬〖ℳℜ〗"

        }, "");
        rv_Stylish_art.setAdapter(adapter_auto_generate);

    }

    class Adapter_Auto_generate extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public Context ctx;
        public String tab_string;
        public String[] items;

        public Adapter_Auto_generate(Context context, String[] strings, String tab_string) {
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

                        Ads_Interstitial.showAds_full(Activity_Stylish_Art.this, new Ads_Interstitial.OnFinishAds() {
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
