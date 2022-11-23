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

public class CaptionsListAdapter extends BaseAdapter {
    String[] captionsArray;
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

    public CaptionsListAdapter(Context context, String[] strArr) {
        this.mContext = context;
        this.captionsArray = strArr;
        SharedPreferences sharedPreferences = context.getSharedPreferences("FAVORITEMESSAGES", 0);
        this.localSharedPreferences = sharedPreferences;
        this.localEditor = sharedPreferences.edit();
    }

    public int getCount() {
        String[] strArr = this.captionsArray;
        if (strArr != null) {
            return strArr.length;
        }
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.itm_caption_fact, viewGroup, false);
        final String str = this.captionsArray[i];
        ((ImageView) inflate.findViewById(R.id.imageViewCopy)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ClipboardManager) CaptionsListAdapter.this.mContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Message", str));
                Toast.makeText(CaptionsListAdapter.this.mContext, "Copied", Toast.LENGTH_SHORT).show();
            }
        });
        ((ImageView) inflate.findViewById(R.id.imageViewShare)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", str);
                intent.setType("text/plain");
                CaptionsListAdapter.this.mContext.startActivity(Intent.createChooser(intent, "Share this"));
            }
        });
        ((TextView) inflate.findViewById(R.id.textViewEachFact)).setText(str);
        return inflate;
    }
}
