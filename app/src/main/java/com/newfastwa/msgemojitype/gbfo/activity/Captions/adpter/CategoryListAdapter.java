package com.newfastwa.msgemojitype.gbfo.activity.Captions.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.newfastwa.msgemojitype.gbfo.R;

public class CategoryListAdapter extends BaseAdapter {
    String[] categories = {"Girls", "Love & Emotion", "Friends", "Hillarious", "For Insta", "Motivational", "Birthday", "Cool", "Fitness", "Flirty", "Food", "Friendship", "Funny", "Inspiring", "Life", "Love", "Party", "Sarcastic", "Savage", "Selfie", "Selflove", "Smile", "Success", "Sweet", "Travel", "Happiness"};
    private Context mContext;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public CategoryListAdapter(Context context, String[] strArr) {
        this.mContext = context;
    }

    public int getCount() {
        String[] strArr = this.categories;
        if (strArr != null) {
            return strArr.length;
        }
        return 28;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).
                inflate(R.layout.itm_caption_cagtegory, viewGroup, false);
        ((TextView) inflate.findViewById(R.id.textViewEachCategory)).setText(this.categories[i]);
        return inflate;
    }
}
