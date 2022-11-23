package com.newfastwa.msgemojitype.gbfo.activity.Quick_REply;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.newfastwa.msgemojitype.gbfo.R;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import java.util.List;

public class MyAdapterDb extends RecyclerView.Adapter<MyAdapterDb.MyViewHolder> {

    CountryCodePicker ccp;
    Activity cntx;
    List<Note> list;

    public MyAdapterDb(Activity activity, List<Note> list2) {
        this.cntx = activity;
        this.list = list2;


    }

    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_quick_reply, viewGroup, false));
    }

    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_message.setText(this.list.get(i).getNote());

        myViewHolder.ln_edit_quickreply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //  MyAdapterDb.this.editView(i);
                Edit_dialog_quick(cntx, i);
            }
        });
        myViewHolder.ln_copy_quickreply.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((ClipboardManager) MyAdapterDb.this.cntx.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", myViewHolder.tv_message.getText().toString()));
                Toast.makeText(MyAdapterDb.this.cntx, "Text Copied!", Toast.LENGTH_SHORT).show();
            }
        });
      /*  myViewHolder.btnsend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("https://api.whatsapp.com/send?text=" + myViewHolder.tv_message.getText().toString()));
                MyAdapterDb.this.cntx.startActivity(intent);
            }
        });*/
        /*myViewHolder.txtdel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MyAdapterDb.this.cntx);
                dialog.requestWindowFeature(1);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.confirm_dialog_delete);
                ((MaterialButton) dialog.findViewById(R.id.btnnodetails1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                ((MaterialButton) dialog.findViewById(R.id.btnyesdetails1)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        dao.deleteById(MyAdapterDb.this.list.get(i).id + "");
                        MyAdapterDb.this.list.remove(i);
                        MyAdapterDb.this.notifyItemRemoved(i);
                        MyAdapterDb.this.notifyItemRangeChanged(i, MyAdapterDb.this.list.size());
                        MyAdapterDb.this.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });*/
    }


    public void Edit_dialog_quick(Context context, Integer integer) {
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

        etmessageedit.setText(list.get(integer).getNote());

        ln_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_dialog.dismiss();
            }
        });
        DatabaseHelper_Quick mDBHelper = new DatabaseHelper_Quick(context);

        ln_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etmessageedit.getText().toString().isEmpty()) {
                    Toast.makeText(context, "Please Type Text!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Note n = list.get(integer);
                // updating note text
                n.setNote(etmessageedit.getText().toString());

                // updating note in db
                mDBHelper.updateNote(n);

                // refreshing the list
                list.set(integer, n);
                //   mAdapter.notifyItemChanged(position);
                notifyDataSetChanged();
                edit_dialog.dismiss();
               /* users.Ctycode = ccp.getSelectedCountryCode();
                if (this.ccp.getSelectedCountryCodeWithPlus().isEmpty()) {
                    Toast.makeText(this, "Please Select Your Country!", 0).show();
                } else {

                }*/
            }
        });

    }


    public int getItemCount() {
        return this.list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ln_copy_quickreply;
        LinearLayout ln_edit_quickreply;
        TextView tv_message;

        public MyViewHolder(View view) {
            super(view);

            ln_edit_quickreply = view.findViewById(R.id.ln_edit_quickreply);
            ln_copy_quickreply = view.findViewById(R.id.ln_copy_quickreply);
            tv_message = view.findViewById(R.id.tv_message);

        }
    }
}