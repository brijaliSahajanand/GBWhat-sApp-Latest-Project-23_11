package com.newfastwa.msgemojitype.gbfo.activity.Status.Fragment;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.activity.Status.Adapter.WhatsappStatusAdapter;
import com.newfastwa.msgemojitype.gbfo.activity.Status.WAstatusActivity;
import com.newfastwa.msgemojitype.gbfo.activity.Status.WhatsappStatusModel;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;
import com.newfastwa.msgemojitype.gbfo.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class WhatsappVidFragment extends Fragment {

    //View
    TextView tv_nodata;
    SwipeRefreshLayout swiperefresh;
    RecyclerView rv_fileList;

    //variable
    public File[] allfiles;
    public File[] allfilesBusiness;
    public ArrayList<WhatsappStatusModel> statusModelArrayList;
    public WhatsappStatusAdapter whatsappStatusAdapter;

    WAstatusActivity wAstatusActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wAstatusActivity = (WAstatusActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_whatsappimagefragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Declaration(view);



    }

    private void Declaration(View view) {
        tv_nodata = view.findViewById(R.id.tv_nodata);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        rv_fileList = view.findViewById(R.id.rv_fileList);

    }

    private void Initialization() {

        statusModelArrayList = new ArrayList<>();
        //getData();
        if (Build.VERSION.SDK_INT >= 29) {
            Utils.Check_WhatsApp_path = true;
            if (!Preference.getWhatsapp_URI().equals("")) {
                Utils.Check_WhatsApp_path = true;
                SetImageArray(Uri.parse(Preference.getWhatsapp_URI()));
            }else{
                Utils.Check_WhatsApp_path = false;
                getData();
            }
        } else {
            Utils.Check_WhatsApp_path = false;
            getData();
        }
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                statusModelArrayList = new ArrayList<>();
                if (Build.VERSION.SDK_INT >= 29) {
                    Utils.Check_WhatsApp_path = true;
                    if (!Preference.getWhatsapp_URI().equals("")) {
                        Utils.Check_WhatsApp_path = true;
                        SetImageArray(Uri.parse(Preference.getWhatsapp_URI()));
                    }else{
                        Utils.Check_WhatsApp_path = false;
                        getData();
                    }
                } else {
                    Utils.Check_WhatsApp_path = false;
                    getData();
                }
                swiperefresh.setRefreshing(false);
            }
        });
    }

    private void getData() {
        WhatsappStatusModel whatsappStatusModel;

        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/media/com.whatsapp" + "/WhatsApp/Media/.Statuses";
        File targetDirector = new File(targetPath);
        allfiles = targetDirector.listFiles();

        if (targetDirector.listFiles() == null) {
            String targetPath1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WhatsApp/Media/.Statuses";
            File targetDirector1 = new File(targetPath1);
            allfiles = targetDirector1.listFiles();
        }

        String targetPathBusiness = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/media/com.whatsapp" + "/WhatsApp Business/Media/.Statuses";
        File targetDirectorBusiness = new File(targetPathBusiness);
        allfilesBusiness = targetDirectorBusiness.listFiles();

        if (targetDirectorBusiness.listFiles() == null) {
            String targetPathBusiness1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WhatsApp Business/Media/.Statuses";
            File targetDirectorBusiness1 = new File(targetPathBusiness1);
            allfilesBusiness = targetDirectorBusiness1.listFiles();
        }


        if (allfiles != null) {
            try {
                Arrays.sort(allfiles, (Comparator) (o1, o2) -> {
                    if (((File) o1).lastModified() > ((File) o2).lastModified()) {
                        return -1;
                    } else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
                        return +1;
                    } else {
                        return 0;
                    }
                });

                for (int i = 0; i < allfiles.length; i++) {
                    File file = allfiles[i];
                    if (Uri.fromFile(file).toString().endsWith(".mp4")) {
                        whatsappStatusModel = new WhatsappStatusModel("WhatsStatus: " + (i + 1),
                                Uri.fromFile(file),
                                allfiles[i].getAbsolutePath(),
                                file.getName());
                        statusModelArrayList.add(whatsappStatusModel);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (allfilesBusiness != null) {
            try {
                Arrays.sort(allfilesBusiness, (Comparator) (o1, o2) -> {
                    if (((File) o1).lastModified() > ((File) o2).lastModified()) {
                        return -1;
                    } else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
                        return +1;
                    } else {
                        return 0;
                    }
                });

                for (int i = 0; i < allfilesBusiness.length; i++) {
                    File file = allfilesBusiness[i];
                    if (Uri.fromFile(file).toString().endsWith(".mp4")) {
                        whatsappStatusModel = new WhatsappStatusModel("WhatsStatusB: " + (i + 1),
                                Uri.fromFile(file),
                                allfilesBusiness[i].getAbsolutePath(),
                                file.getName());
                        statusModelArrayList.add(whatsappStatusModel);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (statusModelArrayList.size() != 0) {
            tv_nodata.setVisibility(View.GONE);
        } else {
            tv_nodata.setVisibility(View.VISIBLE);
        }
        whatsappStatusAdapter = new WhatsappStatusAdapter(getActivity(), statusModelArrayList);
        rv_fileList.setAdapter(whatsappStatusAdapter);

    }

    private class LoadVideodata extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            WhatsappStatusModel whatsappStatusModel;

            String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/media/com.whatsapp" + "/WhatsApp/Media/.Statuses";
            File targetDirector = new File(targetPath);
            allfiles = targetDirector.listFiles();

            if (targetDirector.listFiles() == null) {
                String targetPath1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WhatsApp/Media/.Statuses";
                File targetDirector1 = new File(targetPath1);
                allfiles = targetDirector1.listFiles();
            }

            String targetPathBusiness = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/media/com.whatsapp" + "/WhatsApp Business/Media/.Statuses";
            File targetDirectorBusiness = new File(targetPathBusiness);
            allfilesBusiness = targetDirectorBusiness.listFiles();

            if (targetDirectorBusiness.listFiles() == null) {
                String targetPathBusiness1 = Environment.getExternalStorageDirectory().getAbsolutePath() + "/WhatsApp Business/Media/.Statuses";
                File targetDirectorBusiness1 = new File(targetPathBusiness1);
                allfilesBusiness = targetDirectorBusiness1.listFiles();
            }


            try {
                Arrays.sort(allfiles, (Comparator) (o1, o2) -> {
                    if (((File) o1).lastModified() > ((File) o2).lastModified()) {
                        return -1;
                    } else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
                        return +1;
                    } else {
                        return 0;
                    }
                });

                for (int i = 0; i < allfiles.length; i++) {
                    File file = allfiles[i];
                    if (Uri.fromFile(file).toString().endsWith(".mp4")) {
                        whatsappStatusModel = new WhatsappStatusModel("WhatsStatus: " + (i + 1),
                                Uri.fromFile(file),
                                allfiles[i].getAbsolutePath(),
                                file.getName());
                        statusModelArrayList.add(whatsappStatusModel);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Arrays.sort(allfilesBusiness, (Comparator) (o1, o2) -> {
                    if (((File) o1).lastModified() > ((File) o2).lastModified()) {
                        return -1;
                    } else if (((File) o1).lastModified() < ((File) o2).lastModified()) {
                        return +1;
                    } else {
                        return 0;
                    }
                });

                for (int i = 0; i < allfilesBusiness.length; i++) {
                    File file = allfilesBusiness[i];
                    if (Uri.fromFile(file).toString().endsWith(".mp4")) {
                        whatsappStatusModel = new WhatsappStatusModel("WhatsStatusB: " + (i + 1),
                                Uri.fromFile(file),
                                allfilesBusiness[i].getAbsolutePath(),
                                file.getName());
                        statusModelArrayList.add(whatsappStatusModel);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (statusModelArrayList.size() != 0) {
                tv_nodata.setVisibility(View.GONE);
            } else {
                tv_nodata.setVisibility(View.VISIBLE);
            }
            whatsappStatusAdapter = new WhatsappStatusAdapter(getActivity(), statusModelArrayList);
            rv_fileList.setAdapter(whatsappStatusAdapter);
        }
    }

    private void SetImageArray(Uri uri) {
        DocumentFile fromTreeUri = DocumentFile.fromTreeUri(getContext(), uri);
        DocumentFile[] documentFiles = fromTreeUri.listFiles();
        Log.d("NextActivity12", "allow " + documentFiles.length);
        WhatsappStatusModel whatsappStatusModel;
        if (documentFiles != null) {
            try {

                for (int i = 0; i < documentFiles.length; i++) {
                    // File file = allfiles[i];
                    String path = documentFiles[i].getUri().toString();
                    Log.d("NextActiv12", "Path = " + path);
                    if (path.endsWith(".mp4")) {
                        whatsappStatusModel = new WhatsappStatusModel("WhatsStatus: " + (i + 1),
                                documentFiles[i].getUri(),
                                path,
                                documentFiles[i].getName());
                        statusModelArrayList.add(whatsappStatusModel);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (statusModelArrayList.size() != 0) {
                tv_nodata.setVisibility(View.GONE);
            } else {
                tv_nodata.setVisibility(View.VISIBLE);
            }
            whatsappStatusAdapter = new WhatsappStatusAdapter(getActivity(), statusModelArrayList);
            rv_fileList.setAdapter(whatsappStatusAdapter);

        }
    }

    @Override
    public void onResume() {
        Log.d("NextActivity12","onResume vide");
        Initialization();
        super.onResume();
    }

}
