package com.newfastwa.msgemojitype.gbfo.activity.Captions;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.utils.Header;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.newfastwa.msgemojitype.gbfo.activity.Captions.Activity_Caption_Main.fileToOpen;
import static com.newfastwa.msgemojitype.gbfo.activity.Captions.Activity_Caption_Main.positionSelected;

public class CaptionsByFilesActivity extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback{
    ArrayList<String> arraylistGoodCaptions;
    RecyclerView rc_caption_by_files;
    BufferedReader reader;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_captions_by_files);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);


        rc_caption_by_files = findViewById(R.id.rc_caption_by_files);

        // this.listVewCaptions = (ListView) findViewById(R.id.listViewFacts);
        setTitle(Categories.categoriesArray[positionSelected]);
        this.arraylistGoodCaptions = new ArrayList<>();
        new AsyncTaskExample().execute("");
    }

    Dialog pd_ad_loading;

    private class AsyncTaskExample extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd_ad_loading = new Dialog(CaptionsByFilesActivity.this);
            pd_ad_loading.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            pd_ad_loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            pd_ad_loading.requestWindowFeature(Window.FEATURE_NO_TITLE);
            pd_ad_loading.setCancelable(false);
            pd_ad_loading.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
            pd_ad_loading.setContentView(R.layout.ads_loading_dialog);
            pd_ad_loading.show();

        }
        @Override
        protected String doInBackground(String... strings) {
            try {
                reader = new BufferedReader(new InputStreamReader(getAssets().open(fileToOpen)));
                while (true) {
                    String readLine = reader.readLine();
                  //  if (readLine != null) {
                        if (!readLine.equals("")) {
                            arraylistGoodCaptions.add(readLine);
                        }
                  /*  } else {
                        return;
                    }*/
                }
            } catch (Exception e) {
                Log.i("RAJ", "Exception : " + e.getMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Adapter_Caption_Files adapter_caption_files = new Adapter_Caption_Files(CaptionsByFilesActivity.this, arraylistGoodCaptions);
            rc_caption_by_files.setAdapter(adapter_caption_files);
            pd_ad_loading.dismiss();
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

class Adapter_Caption_Files extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context ctx;

    public ArrayList<String> items;

    public Adapter_Caption_Files(Context context, ArrayList<String> strings) {
        this.items = strings;
        this.ctx = context;

    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OriginalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itm_caption_fact, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof OriginalViewHolder) {
            ((OriginalViewHolder) viewHolder).textViewEachFact.setText(items.get(i));

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
        return this.items.size();
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

