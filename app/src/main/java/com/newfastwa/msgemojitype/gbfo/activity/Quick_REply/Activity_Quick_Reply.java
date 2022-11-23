package com.newfastwa.msgemojitype.gbfo.activity.Quick_REply;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.utils.Header;

import java.util.ArrayList;
import java.util.List;

public class Activity_Quick_Reply extends BaseActivity implements Header.onback {

    RecyclerView rv_reply_messages;
    LinearLayout ln_add_messages;
    List<Note> arrayList;
    TextView ivaddnew;
    LinearLayout ln_act_copy_quickreply, ln_act_edit_quickreply;
    TextView tv_message;
    private DatabaseHelper_Quick mDBHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_reply);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);



        mDBHelper = new DatabaseHelper_Quick(this);
        ln_add_messages = findViewById(R.id.ln_add_messages);
        rv_reply_messages = findViewById(R.id.rv_reply_messages);
        ln_act_edit_quickreply = findViewById(R.id.ln_act_edit_quickreply);
        ln_act_copy_quickreply = findViewById(R.id.ln_act_copy_quickreply);
        tv_message = findViewById(R.id.tv_message);
        this.ivaddnew = findViewById(R.id.ivaddnew);

        ln_act_copy_quickreply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", tv_message.getText().toString()));
                Toast.makeText(Activity_Quick_Reply.this, "Text Copied!", Toast.LENGTH_SHORT).show();
            }
        });


        this.arrayList = new ArrayList();
        arrayList =  mDBHelper.getAllNotes();

        start();
        this.ivaddnew.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Edit_dialog_quick(Activity_Quick_Reply.this);
            }
        });

    }

    public void Edit_dialog_quick(Context context) {
        Dialog edit_dialog = new Dialog(context);

        edit_dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        edit_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        edit_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        edit_dialog.setCancelable(false);
        edit_dialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
        edit_dialog.setContentView(R.layout.edit_quick_reply);
        edit_dialog.show();


        LinearLayout ln_save = edit_dialog.findViewById(R.id.ln_save);
        LinearLayout ln_cancel = edit_dialog.findViewById(R.id.ln_cancel);
        EditText etmessageedit = edit_dialog.findViewById(R.id.etmessageedit);


        ln_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_dialog.dismiss();
            }
        });


        ln_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDBHelper.insertNote( etmessageedit.getText().toString());
                arrayList.clear();
                arrayList =  mDBHelper.getAllNotes();
                rv_reply_messages.setAdapter(new MyAdapterDb(Activity_Quick_Reply.this, arrayList));
                edit_dialog.dismiss();
            }
        });
    }

    public void start() {
        rv_reply_messages.setLayoutManager(new LinearLayoutManager(this));
        rv_reply_messages.addItemDecoration(new DividerItemDecoration(rv_reply_messages.getContext(), 1));
        rv_reply_messages.setAdapter(new MyAdapterDb(this, this.arrayList));
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
