package com.newfastwa.msgemojitype.gbfo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.newfastwa.msgemojitype.gbfo.R;

public class ComingsoonActivity extends com.newfastwa.msgemojitype.gbfo.BaseActivity {
    ImageView imgclose;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comingsoon);

        imgclose = findViewById(R.id.imgclose);
        imgclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                finishAffinity();
            }
        });

    }
}