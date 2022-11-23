package com.newfastwa.msgemojitype.gbfo.activity.Captions;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.utils.Header;

import static com.newfastwa.msgemojitype.gbfo.activity.Captions.Activity_Caption_Main.positionSelected;
import static com.newfastwa.msgemojitype.gbfo.activity.Captions.Activity_Caption_Main.selectedArray;


public class CategoryCaptionsActivity extends BaseActivity implements Header.onback{

    RecyclerView rv_caption_static;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_category_captions);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);


        setTitle(Categories.categoriesArray[positionSelected]);
        //   CaptionsListAdapter captionsListAdapter2 = new CaptionsListAdapter(this, selectedArray);
        //   this.captionsListAdapter = captionsListAdapter2;
        //   this.listViewFacts.setAdapter(captionsListAdapter2);

        rv_caption_static = findViewById(R.id.rv_caption_static);

        Adapter_Caption_Static adapter_caption_static = new Adapter_Caption_Static(CategoryCaptionsActivity.this, selectedArray);
        rv_caption_static.setAdapter(adapter_caption_static);
    }

    @Override
    public void onbacks(Boolean i) {
        onBackPressed();
    }

    @Override
    protected void onResume() {
        Header header = findViewById(R.id.header);
        Header.onback onBackPressed = (Header.onback) this;
        header.init(onBackPressed);
        super.onResume();
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

class Adapter_Caption_Static extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context ctx;
    public String[] items;

    public Adapter_Caption_Static(Context context, String[] strings) {
        this.items = strings;
        this.ctx = context;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OriginalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itm_caption_fact, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof OriginalViewHolder) {
            ((OriginalViewHolder) viewHolder).textViewEachFact.setText(items[i]);

            ((OriginalViewHolder) viewHolder).imageViewShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.SEND");
                    intent.putExtra("android.intent.extra.TEXT", ((OriginalViewHolder) viewHolder).textViewEachFact.getText());
                    intent.setType("text/plain");
                    ctx.startActivity(Intent.createChooser(intent, "Share this"));
                }
            });
            ((OriginalViewHolder) viewHolder).imageViewCopy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((ClipboardManager) ctx.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Message", ((OriginalViewHolder) viewHolder).textViewEachFact.getText()));
                    Toast.makeText(ctx, "Text Copied!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public int getItemCount() {
        return this.items.length;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        TextView textViewEachFact;
        ImageView imageViewShare;
        ImageView imageViewCopy;

        public OriginalViewHolder(View view) {
            super(view);

            imageViewCopy = view.findViewById(R.id.imageViewCopy);
            imageViewShare = view.findViewById(R.id.imageViewShare);
            textViewEachFact = view.findViewById(R.id.textViewEachFact);
        }
    }
}

