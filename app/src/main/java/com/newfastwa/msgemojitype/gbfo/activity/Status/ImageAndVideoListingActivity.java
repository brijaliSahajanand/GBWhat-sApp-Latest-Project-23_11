package com.newfastwa.msgemojitype.gbfo.activity.Status;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.activity.Status.Adapter.FileListAdapter;
import com.newfastwa.msgemojitype.gbfo.activity.Status.Adapter.ItemClickInterface;

import com.newfastwa.msgemojitype.gbfo.utils.AppConstants;
import com.newfastwa.msgemojitype.gbfo.utils.Header;
import com.newfastwa.msgemojitype.gbfo.utils.ItemOffsetDecoration;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;
import com.newfastwa.msgemojitype.gbfo.utils.Utils;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static com.newfastwa.msgemojitype.gbfo.utils.Utils.RootDirectoryWhatsappShow;
import static com.newfastwa.msgemojitype.gbfo.utils.Utils.lattestfile_path;

public class ImageAndVideoListingActivity extends BaseActivity implements ItemClickInterface , Header.onback {



    TextView /*tv_name,*/ tv_nodata;
    SwipeRefreshLayout swiperefresh;
    RecyclerView rv_fileList;


    //variabler
    private FileListAdapter fileListAdapter;
    private ArrayList<File> fileArrayList;
    String header;
    int fromintent;


    GridLayoutManager gridLayoutManager;
    ItemClickInterface itemClickInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_and_video_listing);
        itemClickInterface = (ItemClickInterface) this;

        Declaration();
        Initialization();

    }

    private void Declaration() {
        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);
       // tv_name = findViewById(R.id.tv_name);
        tv_nodata = findViewById(R.id.tv_nodata);
        swiperefresh = findViewById(R.id.swiperefresh);
        rv_fileList = findViewById(R.id.rv_fileList);
        gridLayoutManager = new GridLayoutManager(ImageAndVideoListingActivity.this, 2);
        rv_fileList.setLayoutManager(gridLayoutManager);

        rv_fileList.addItemDecoration(new ItemOffsetDecoration(5));


    }

    private void Initialization() {


        if (getIntent() != null) {


            Utils.downloadDoneinterface = new Utils.DownloadDoneinterface() {
                @Override
                public void refreshPage() {
                    if (fileListAdapter != null) {
//                            fileListAdapter.notifyItemChanged(0);
//                        SessionManagement.setIntValue(ImageAndVideoListingActivity.this, AppConstant.PERCENTAGE, 0);
                        Preference.setpercentage(0);
                        getAllFiles();
                    }

                }
            };

            Utils.updateDownPer = new Utils.UpdateDownPer() {
                @Override
                public void percentage(int percentage) {
                    if (fileListAdapter != null) {
                        Preference.setpercentage(percentage);
//                        SessionManagement.setIntValue(ImageAndVideoListingActivity.this, AppConstant.PERCENTAGE, percentage);
                        rv_fileList.post(new Runnable() {
                            @Override
                            public void run() {
                                fileListAdapter.notifyItemChanged(0);
                            }
                        });
                    }
                }
            };

            header = getIntent().getStringExtra("Name");

          //  tv_name.setText(header);

            getAllFiles();

            swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    getAllFiles();
                    swiperefresh.setRefreshing(false);
                }
            });

        }

    }


    private void getAllFiles() {
        fileArrayList = new ArrayList<>();
        File[] files = new File[0];
       if (header.equals(AppConstants.WHATSAPP)) {
            files = RootDirectoryWhatsappShow.listFiles();
        }


        if (files != null) {

            Arrays.sort(files, new Comparator<File>() {
                public int compare(File f1, File f2) {
                    return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
                }
            });


            if (files.length > 0 /*|| Utils.isDownloading*/) {

                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }

                for (File file : files) {
                    fileArrayList.add(file);
                }

                Collections.reverse(fileArrayList);

                if (Utils.isDownloading) {

                    if (isFileDownload()) {

                        if(lattestfile_path !=null) {
                            if (!lattestfile_path.isEmpty()) {

                                if (new File(lattestfile_path).exists()) {
                                    int pos = fileArrayList.indexOf(new File(lattestfile_path));
//                                    Toast.makeText(this, "pos:" + pos, Toast.LENGTH_SHORT).show();
                                    fileArrayList.set(pos, null);
                                } else {
                                    fileArrayList.add(0, null);
                                }
                            } else {
                                fileArrayList.add(0, null);
                            }
                        }
                    }
                }

                if (fileArrayList.size() > 0) {
                    Log.d("listofdile", new Gson().toJson(fileArrayList));


                    fileListAdapter = new FileListAdapter(ImageAndVideoListingActivity.this, fileArrayList);
                    rv_fileList.setAdapter(fileListAdapter);


                    rv_fileList.setVisibility(View.VISIBLE);
                    tv_nodata.setVisibility(View.GONE);
                } else {

                    rv_fileList.setVisibility(View.GONE);
                    tv_nodata.setVisibility(View.VISIBLE);
                }
            } else {

                rv_fileList.setVisibility(View.GONE);
                tv_nodata.setVisibility(View.VISIBLE);
            }
        } else {
            rv_fileList.setVisibility(View.GONE);
            tv_nodata.setVisibility(View.VISIBLE);
        }
    }

    private boolean isFileDownload() {
        boolean isdownload = false;
       /* if (Utils.fromintent == 1 && tv_name.getText().toString().equals(AppConstants.INSTAGRAM)) {
            isdownload = true;
        } else if (Utils.fromintent == 2 && tv_name.getText().toString().equals(AppConstants.FACEBOOK)) {
            isdownload = true;
        } else if (Utils.fromintent == 3 && tv_name.getText().toString().equals(AppConstants.TWITTER)) {
            isdownload = true;
        } else if (Utils.fromintent == 4 && tv_name.getText().toString().equals(AppConstants.TIKTOK)) {
            isdownload = true;
        } else if (Utils.fromintent == 5 && tv_name.getText().toString().equals(AppConstants.SHARECHAT)) {
            isdownload = true;
        }*/
        return isdownload;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (AppConstants.BackFromFullView) {
            AppConstants.BackFromFullView = false;
            if (fileListAdapter != null) {
                getAllFiles();
            }
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

    @Override
    public void getItempositon(int i) {
        /*if (Ads.adsclick == Preference.getads_click()) {
            Ads.adsclick = 0;
            Ads.showAds(ImageAndVideoListingActivity.this, new Ads.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    Intent inNext = new Intent(ImageAndVideoListingActivity.this, FullViewActivity.class);
                    inNext.putExtra("ImageDataFile", fileArrayList);
                    inNext.putExtra("Position", i);
                    startActivity(inNext);
                }
            });
        } else {
            Ads.adsclick++;
            Intent inNext = new Intent(ImageAndVideoListingActivity.this, FullViewActivity.class);
            inNext.putExtra("ImageDataFile", fileArrayList);
            inNext.putExtra("Position", i);
            startActivity(inNext);
        }*/
    }

}