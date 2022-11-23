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

public class Activity_Stylish_Emojis_List extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback{

    String[] name = {"✢", "꧁", "✡︎", "✪", "✣", "✤", "✥", "✦", "✧", "★", "☆", "✯", "✩", "✫", "✬", "✭", "✮", "✶", "✷", "✵", "✸", "✹", "✺", "❊", "✻", "✽", "✼", "❉", "✱", "✲", "✾", "❃", "❋", "✳︎", "✴︎", "❇︎", "❈", "※", "❅", "❆", "❄︎", "⚙︎", "✿", "❀", "❁", "❂", "●", "○", "◎", "◉", "⦿", "⁌", "⁍", "☀", "☼", "☽", "☾", "☁", "☂", "☔", "☃", "☇", "☈", "☻", "☹", "☺", "☕", "✌", "✍︎", "✎", "✏︎", "✐", "✑", "✒︎", "✁", "✂︎", "✃", "✄", "⚾︎", "✇", "✈︎", "⚓︎", "♨︎", "♈︎", "♉︎", "♊︎", "♋︎", "♌︎", "♍︎", "♎︎", "♏︎", "♐︎", "♑︎", "♒︎", "♓︎", "☉", "☿", "♀︎", "♁", "♂︎", "♃", "♄", "♅", "⛢", "♆", "♇", "☄︎", "⚲", "⚢", "⚣", "⚤", "⚦", "⚧", "⚨", "⚩", "⚬", "⚭", "⚮", "⚯", "⚰︎", "⚱︎", "☊", "☋", "☌", "☍", "✦", "✧", "✙", "✚", "✛", "✜", "✝︎", "✞", "✟", "✠", "☦︎", "☨", "☩", "☥", "♰", "♱", "☓", "⚜︎", "☤", "⚚", "⚕︎", "⚖︎", "⚗︎", "⚙︎", "⚘", "☘︎", "⚛︎", "☧", "⚒︎", "☭", "☪︎", "☫", "☬", "⚑", "⚐", "☮︎", "☯︎", "☸︎", "⚔︎", "☗", "☖", "■", "□", "☐", "☑︎", "☒", "▪︎", "▫︎", "◻︎", "◼︎", "◘", "◆", "◇", "❖", "✓", "✔︎", "✕", "✖︎", "✗", "✘", "﹅", "﹆", "❍", "❏", "❐", "❑", "❒", "✰", "❤︎", "❥", "☙", "❧", "❦", "❡", "🞡", "🞢", "🞣", "🞤", "🞥", "🞦", "🞧", "🞨", "🞩", "🞪", "🞫", "🞬", "🞭", "🞮", "©", "®", "™", "℠", "℡", "℗", "‱", "№", "℀", "℁", "℅", "℆", "⅍", "☊", "☎", "☏", "⌨", "✁", "✂", "✃", "✄", "✆", "✇", "✈", "✉", "✎", "✏", "✐", "✑", "✒", "‰", "§", "¶", "✌", "☝", "☞", "☛", "☟", "☜", "☚", "✍", "$", "€", "¥", "¢", "£", "₽", "₨", "₩", "฿", "₺", "₮", "₱", "₭", "₴", "₦", "৲", "৳", "૱", "௹", "﷼", "₹", "₲", "₪", "₡", "₫", "៛", "₵", "₢", "₸", "₤", "₳", "₥", "₠", "₣", "₰", "₧", "₯", "₶", "₷", "$", "€", "¥", "¢", "£", "₽", "₨", "₩", "฿", "₺", "₮", "₱", "₭", "₴", "₦", "৲", "৳", "૱", "௹", "﷼", "₹", "₲", "₪", "₡", "₫", "៛", "₵", "₢", "₸", "₤", "₳", "₥", "₠", "₣", "₰", "₧", "₯", "₶", "₷", "♚", "♛", "♜", "♝", "♞", "♟", "♔", "♕", "♖", "♗", "♘", "♙", "⚀", "⚁", "⚂", "⚃", "⚄", "⚅", "🂠", "⚈", "⚉", "⚆", "⚇", "🀰", "🀀", "🀁", "🀂", "🀃", "🀅", "🀆", "🀇", "🀈", "🀉", "🀊", "🀋", "🀌", "🀍", "🀎", "🀏", "🀐", "🀑", "🀒", "🀓", "🀔", "🀕", "🀖", "🀗", "🀘", "🀙", "🀚", "🀛", "🀜", "🀝", "🀞", "🀟", "🀠", "🀡", "🀢", "🀣", "🀤", "🀥", "🀦", "🀧", "🀨", "🀩", "🀪", "🀫", "♠︎", "♣︎", "♥︎", "♦︎", "♤", "♧", "♡", "♢", "🂡", "🂢", "🂣", "🂤", "🂥", "🂦", "🂧", "🂨", "🂩", "🂪", "🂫", "🂬", "🂭", "🂮", "🂱", "🂲", "🂳", "🂴", "🂵", "🂶", "🂷", "🂸", "🂹", "🂺", "🂻", "🂼", "🂽", "🂾", "🃁", "🃂", "🃃", "🃄", "🃅", "🃆", "🃇", "🃈", "🃉", "🃊", "🃋", "🃌", "🃍", "🃎", "🃑", "🃒", "🃓", "🃔", "🃕", "🃖", "🃗", "🃘", "🃙", "🃚", "🃛", "🃜", "🃝", "🃞", "🃟"};
    RecyclerView rv_stylish_emojisss;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylish_emojis_list);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);


        rv_stylish_emojisss = findViewById(R.id.rv_stylish_emojisss);
        Adapter_Stylish_Emojis adapter_stylish_emojis = new Adapter_Stylish_Emojis(Activity_Stylish_Emojis_List.this, name, "");
        rv_stylish_emojisss.setAdapter(adapter_stylish_emojis);

        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.item_anim);
        rv_stylish_emojisss.setLayoutAnimation(controller);
        adapter_stylish_emojis.notifyDataSetChanged();
        rv_stylish_emojisss.scheduleLayoutAnimation();
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

class Adapter_Stylish_Emojis extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Activity ctx;
    public String tab_string;
    public String[] items;

    public Adapter_Stylish_Emojis(Activity context, String[] strings, String tab_string) {
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
