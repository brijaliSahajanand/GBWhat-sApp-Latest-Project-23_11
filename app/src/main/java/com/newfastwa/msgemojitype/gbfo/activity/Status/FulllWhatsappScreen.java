package com.newfastwa.msgemojitype.gbfo.activity.Status;

import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.activity.Status.Adapter.FullImageAdapter;
import com.newfastwa.msgemojitype.gbfo.utils.AppConstants;
import com.newfastwa.msgemojitype.gbfo.utils.Utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

public class FulllWhatsappScreen extends com.newfastwa.msgemojitype.gbfo.BaseActivity {

    //View
    ImageView iv_back ,iv_download;
    Button btn_download;
    ViewPager viewpager_imgvid;

    private ArrayList<WhatsappStatusModel> fileArrayList;
    private int Position = 0;
    FullImageAdapter fullImageAdapter;


    public String SaveFilePath = (Utils.RootDirectoryWhatsappShow + "/");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fulll_whatsapp_screen);

        Declaration();
        Initiazlization();
    }

    private void Declaration() {

        btn_download = findViewById(R.id.btn_download);
        iv_download = findViewById(R.id.iv_download);
        iv_back = findViewById(R.id.iv_back);
        viewpager_imgvid = findViewById(R.id.viewpager_imgvid);

        fileArrayList = Utils.whatsappStatusModelArrayList;
        Position = Utils.position;


    }

    private void Initiazlization() {


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fullImageAdapter = new FullImageAdapter(this, fileArrayList);
        viewpager_imgvid.setAdapter(fullImageAdapter);
        viewpager_imgvid.setCurrentItem(Position);

        viewpager_imgvid.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int pos) {
                Position = pos;
                System.out.println("Current position==" + Position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int num) {
            }
        });


        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Ads_Interstitial.showAds_full(FulllWhatsappScreen.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        Utils.createFileFolder();
                        if (Build.VERSION.SDK_INT >= 29) {
                            if(Utils.Check_WhatsApp_path) {
                                Downloadnew(fileArrayList.get(Position));
                            }else{
                                Download(fileArrayList.get(Position));
                            }
                        } else {
                            Download(fileArrayList.get(Position));
                        }
                        startActivity(new Intent(FulllWhatsappScreen.this, ImageAndVideoListingActivity.class).putExtra("Name", AppConstants.WHATSAPP));

                    }
                });



            }
        });


    }


    private void Downloadnew(WhatsappStatusModel whatsappStatusModel) {
        byte[] videoBytes;
        if (whatsappStatusModel.getUri().getScheme().equals("content")) {
            //videoBytes = getBytes(iStream);
            try {
                InputStream iStream = FulllWhatsappScreen.this.getContentResolver().openInputStream(whatsappStatusModel.getUri());
                videoBytes = IOUtils.toByteArray(iStream);
                Log.d("FullWhatsApp12", "videoBytes = " + videoBytes);
                String extension = whatsappStatusModel.getPath().substring(whatsappStatusModel.getPath().lastIndexOf("."));
                SavePhotoTask(videoBytes, extension);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            File file = new File(whatsappStatusModel.getUri().getPath());
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                videoBytes = IOUtils.toByteArray(fileInputStream);
                Log.d("FullWhatsApp12", "videoBytes 1 = " + videoBytes);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void SavePhotoTask(byte[] jpeg, String extension) {
        Log.d("FullWhatsApp12", "extension = " + extension);
        UUID uuid = UUID.randomUUID();
        String fileName = uuid + extension;

        Log.d("FullWhatsApp12", "SavePhotoTask = " + fileName);
        File directory = new File(SaveFilePath);
        if (!directory.exists()) {
            Log.d("VideoDownload_Screen12", "makeing = ");
            directory.mkdir();
        }
        Log.d("FullWhatsApp12", "SavePhotoTask = ");
        final File photo = new File(directory, fileName);
        Log.d("FullWhatsApp12", "photo = " + photo.getPath());
        try {
            Log.d("FullWhatsApp12", "FileOutputStream = ");
            FileOutputStream fos = new FileOutputStream(photo.getPath());
            fos.write(jpeg);
            fos.close();
        } catch (Exception e) {
            Log.d("FullWhatsApp12", "Exception = " + e.getMessage());
        }
    }

    private void Download(WhatsappStatusModel whatsappStatusModel) {
        Utils.createFileFolder();
        String path = whatsappStatusModel.getPath();
        String substring = path.substring(path.lastIndexOf("/") + 1);
        try {
            FileUtils.copyFileToDirectory(new File(path), new File(SaveFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String substring2 = substring.substring(12);
        MediaScannerConnection.scanFile(FulllWhatsappScreen.this, new String[]{new File(SaveFilePath + substring2).getAbsolutePath()}, new String[]{whatsappStatusModel.getUri().toString().endsWith(".mp4") ? "video/*" : "image/*"}, new MediaScannerConnection.MediaScannerConnectionClient() {
            public void onMediaScannerConnected() {
            }

            public void onScanCompleted(String str, Uri uri) {
            }
        });
        new File(SaveFilePath, substring).renameTo(new File(SaveFilePath, substring2));
        Toast.makeText(FulllWhatsappScreen.this, "Saved to:" + SaveFilePath + substring2, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {

        AppConstants.BackFromFullView = true;
        super.onBackPressed();

    }

}