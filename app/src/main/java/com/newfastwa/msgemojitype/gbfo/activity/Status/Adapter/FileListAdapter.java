package com.newfastwa.msgemojitype.gbfo.activity.Status.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.activity.Status.ImageAndVideoListingActivity;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.util.ArrayList;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder> {
    private ImageAndVideoListingActivity imageAndVideoListingActivity;
    private ArrayList<File> fileArrayList;

    ItemClickInterface itemClickInterface;
    private LayoutInflater layoutInflater;

    public FileListAdapter(ImageAndVideoListingActivity context2, ArrayList<File> arrayList) {
        this.imageAndVideoListingActivity = context2;
        this.fileArrayList = arrayList;
        itemClickInterface = (ItemClickInterface) imageAndVideoListingActivity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder((LayoutInflater.from(imageAndVideoListingActivity).inflate(R.layout.items_file_view, viewGroup, false)));
    }

    @SuppressLint("WrongConstant")
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) imageAndVideoListingActivity).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        double width = displayMetrics.widthPixels / 2.7;
        double height = displayMetrics.widthPixels / 1.4;

        viewHolder.iv_image.getLayoutParams().height = (int) height;
        viewHolder.ll_downloading.getLayoutParams().height = (int) height;


        final File file = fileArrayList.get(i);

        if (file != null) {

            viewHolder.rl_view.setVisibility(View.VISIBLE);
            viewHolder.ll_downloading.setVisibility(View.GONE);

            try {
                if (file.getName().substring(file.getName().lastIndexOf(".")).equals(".mp4")) {
                    viewHolder.ivCameraIcon.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.ivCameraIcon.setVisibility(View.GONE);
                }

                Glide.with(imageAndVideoListingActivity).load(file.getPath()).into(viewHolder.iv_image);


            } catch (Exception unused) {
            }
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    itemClickInterface.getItempositon(i);

                }
            });
        } else {

            viewHolder.rl_view.setVisibility(View.GONE);
            viewHolder.ll_downloading.setVisibility(View.VISIBLE);
//            Glide.with(context).load(UtilisExtra.getURLForResource(R.color.white2)).into(viewHolder.pc);
            viewHolder.rl_view.setVisibility(View.GONE);
            viewHolder.ivCameraIcon.setVisibility(View.GONE);
            viewHolder.ll_downloading.setVisibility(View.VISIBLE);
            viewHolder.tv_update.setText(Preference.getpercentage() + "%");
        }

    }

    public int getItemCount() {
        ArrayList<File> arrayList = fileArrayList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView iv_image;
        ImageView ivCameraIcon;
        RelativeLayout rl_view;
        LinearLayout ll_downloading;
        TextView tv_update;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCameraIcon = itemView.findViewById(R.id.ivCameraIcon);
            iv_image = itemView.findViewById(R.id.iv_image);
            rl_view = itemView.findViewById(R.id.rl_view);
            ll_downloading = itemView.findViewById(R.id.ll_downloading);
            tv_update = itemView.findViewById(R.id.tv_update);
        }
    }
}
