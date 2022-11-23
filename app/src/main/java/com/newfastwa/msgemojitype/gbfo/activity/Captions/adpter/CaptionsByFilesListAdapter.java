package com.newfastwa.msgemojitype.gbfo.activity.Captions.adpter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.newfastwa.msgemojitype.gbfo.R;

import java.util.ArrayList;

public class CaptionsByFilesListAdapter extends BaseAdapter {
    ArrayList<String> arraylistGoodNightMessage;
    SharedPreferences.Editor localEditor;
    SharedPreferences localSharedPreferences;
    /* access modifiers changed from: private */
    public Context mContext;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public CaptionsByFilesListAdapter(Context context, ArrayList<String> arrayList) {
        this.mContext = context;
        this.arraylistGoodNightMessage = arrayList;
    }

    public int getCount() {
        return this.arraylistGoodNightMessage.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.itm_caption_fact, viewGroup, false);
        final String str = this.arraylistGoodNightMessage.get(i);
        ((ImageView) inflate.findViewById(R.id.imageViewCopy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ClipboardManager) CaptionsByFilesListAdapter.this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Message", str));
                Toast.makeText(CaptionsByFilesListAdapter.this.mContext, "Copied", Toast.LENGTH_SHORT).show();
            }
        });
        ((ImageView) inflate.findViewById(R.id.imageViewShare)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", str);
                intent.setType("text/plain");
                CaptionsByFilesListAdapter.this.mContext.startActivity(Intent.createChooser(intent, "Share this"));
            }
        });
        ((TextView) inflate.findViewById(R.id.textViewEachFact)).setText(str);
        return inflate;
    }
}
