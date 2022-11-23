package com.newfastwa.msgemojitype.gbfo.activity.Font_style;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.model.TextPojo;
import com.newfastwa.msgemojitype.gbfo.utils.DatabaseHelper_Font;
import com.newfastwa.msgemojitype.gbfo.utils.Header;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class Activity_Stylish_name extends BaseActivity implements Header.onback{

    public static ArrayList<TextPojo> textPojos = new ArrayList<>();
    public static ArrayList<ArrayList<String>> textStyle = new ArrayList<>();
    RecyclerView rv_stylish_name;
    private DatabaseHelper_Font mDBHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stylish_name);

        rv_stylish_name = findViewById(R.id.rv_stylish_name);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

        this.mDBHelper = new DatabaseHelper_Font(this);

        if (!getDatabasePath(DatabaseHelper_Font.DBNAME).exists()) {
            this.mDBHelper.getReadableDatabase();
            if (!copyDatabase(this)) {
                return;
            }
        }

        textStyle = this.mDBHelper.getTextStyles();
        Intent intent = getIntent();
        String text_details = intent.getStringExtra("text_details");
        loadTextData_font(text_details, this);
    }

    public void loadTextData_font(String str, Activity context) {
        textPojos.clear();

        for (int i = 0; i < textStyle.size(); i++) {
            textPojos.add(new TextPojo(textStyle.get(i), str));
        }

        Adapter_Nmae_Text adapterText = new Adapter_Nmae_Text(context, textPojos);
        rv_stylish_name.setAdapter(adapterText);

        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.item_anim);
        rv_stylish_name.setLayoutAnimation(controller);
        adapterText.notifyDataSetChanged();
        rv_stylish_name.scheduleLayoutAnimation();
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream open = context.getAssets().open(DatabaseHelper_Font.DBNAME);
            FileOutputStream fileOutputStream = new FileOutputStream(("/data/data/" + getPackageName() + "/databases/") + DatabaseHelper_Font.DBNAME);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Log.e("MainActivity", "DB copied");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

class Adapter_Nmae_Text extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Activity ctx;

    private ArrayList<TextPojo> items = new ArrayList<>();

    public Adapter_Nmae_Text(Activity context, ArrayList<TextPojo> arrayList) {
        this.items = arrayList;
        this.ctx = context;

    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OriginalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {

        if (viewHolder instanceof OriginalViewHolder) {
            final OriginalViewHolder originalViewHolder = (OriginalViewHolder) viewHolder;
            Boolean.valueOf(false);
            TextPojo textPojo = this.items.get(i);
            String str = textPojo.text;
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < str.length(); i2++) {
                if (str.charAt(i2) >= 'A' && str.charAt(i2) <= 'Z') {
                    sb.append(textPojo.pojo.get(str.charAt(i2) - 'A'));
                } else if (str.charAt(i2) >= 'a' && str.charAt(i2) <= 'z') {
                    sb.append(textPojo.pojo.get((str.charAt(i2) - 'a') + 26));
                } else if (str.charAt(i2) < '0' || str.charAt(i2) > '9') {
                    sb.append(str.charAt(i2));
                } else {
                    sb.append(textPojo.pojo.get((str.charAt(i2) - '0') + 52));
                }
            }


            originalViewHolder.text.setText(sb.toString());

            originalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Ads_Interstitial.showAds_full(ctx, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            Intent intent = new Intent(ctx, Activity_Stylish_share.class);
                            intent.putExtra("selected_text", originalViewHolder.text.getText().toString());
                            ctx.startActivity(intent);
                        }
                    });

                }
            });

        }
    }

    public int getItemCount() {
        return this.items.size();
    }

    public interface Font_Text_click {
        void Click_Font_text(String s);
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        public TextView text;

        public OriginalViewHolder(View view) {
            super(view);

            this.text = view.findViewById(R.id.text);

        }
    }
}

