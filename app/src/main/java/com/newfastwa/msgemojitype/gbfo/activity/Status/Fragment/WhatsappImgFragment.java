package com.newfastwa.msgemojitype.gbfo.activity.Status.Fragment;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static android.app.Activity.RESULT_OK;

public class WhatsappImgFragment extends Fragment {
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
        Initialization();

    }

    private void Declaration(View view) {
        tv_nodata = view.findViewById(R.id.tv_nodata);
        swiperefresh = view.findViewById(R.id.swiperefresh);
        rv_fileList = view.findViewById(R.id.rv_fileList);

    }

    private void Initialization() {

        statusModelArrayList = new ArrayList<>();
//        LoadImagedata loadImagedata = new LoadImagedata();
//        loadImagedata.execute();


        if (Build.VERSION.SDK_INT >= 29) {
            Utils.Check_WhatsApp_path = true;
            if (Preference.getWhatsapp_URI().equals("")) {

                Uri Paths = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/media/com.whatsapp" + "/WhatsApp/Media/.Statuses");
                File file = new File(Paths.getPath());
                if (file.exists()){
                    Utils.Check_WhatsApp_path = true;
                    Log.d("Whatsappimg12","file exist");
                    openDirectory(Paths);
                }else{
                    Utils.Check_WhatsApp_path = false;
                    Log.d("Whatsappimg12","not file exist");
                    getData();
                }
            } else {
                SetImageArray(Uri.parse(Preference.getWhatsapp_URI()));
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
                    if (Preference.getWhatsapp_URI().equals("")) {

                        Uri Paths = Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/media/com.whatsapp" + "/WhatsApp/Media/.Statuses");
                        File file = new File(Paths.getPath());
                        if (file.exists()){
                            Utils.Check_WhatsApp_path = true;
                            Log.d("Whatsappimg12","file exist");
                            openDirectory(Paths);
                        }else{
                            Utils.Check_WhatsApp_path = false;
                            Log.d("Whatsappimg12","not file exist");
                            getData();
                        }
                    } else {
                        SetImageArray(Uri.parse(Preference.getWhatsapp_URI()));
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
        //String targetPath = "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses";
//        String str3 = Environment.getExternalStorageDirectory().getAbsolutePath();
//        String str2 = "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses/";
//        String targetPath = str3 + str2;

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
                    if (Uri.fromFile(file).toString().endsWith(".png") || Uri.fromFile(file).toString().endsWith(".jpg")) {
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
                    if (Uri.fromFile(file).toString().endsWith(".png") || Uri.fromFile(file).toString().endsWith(".jpg")) {
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

    private class LoadImagedata extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            WhatsappStatusModel whatsappStatusModel;
            String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/media/com.whatsapp" + "/WhatsApp/Media/.Statuses";
            //String targetPath = "content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses";
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
                    if (Uri.fromFile(file).toString().endsWith(".png") || Uri.fromFile(file).toString().endsWith(".jpg")) {
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
                    if (Uri.fromFile(file).toString().endsWith(".png") || Uri.fromFile(file).toString().endsWith(".jpg")) {
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


    public void openDirectory(Uri uriToLoad) {
        Toast.makeText(getContext(), "Please allow permission to download status!", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//        intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriToLoad);
//        startActivityForResult(intent, 42);

        StorageManager sm = (StorageManager) getActivity().getSystemService(Context.STORAGE_SERVICE);

        Intent intent = sm.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
        //String startDir = "Android";
        //String startDir = "Download"; // Not choosable on an Android 11 device
        //String startDir = "DCIM";
        //String startDir = "DCIM/Camera";  // replace "/", "%2F"
        //String startDir = "DCIM%2FCamera";
        String startDir = "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses";
        // String startDir = "WhatsApp%2FMedia%2F.Statuses";


        Uri uri = intent.getParcelableExtra("android.provider.extra.INITIAL_URI");

        String scheme = uri.toString();

        Log.d("TAG", "INITIAL_URI scheme: " + scheme);

        scheme = scheme.replace("/root/", "/document/");

        scheme += "%3A" + startDir;

        uri = Uri.parse(scheme);

        intent.putExtra("android.provider.extra.INITIAL_URI", uri);

        Log.d("TAG", "uri: " + uri.toString());
//   intent.putExtra("android.content.extra.SHOW_ADVANCED", true);

        startActivityForResult(intent, 42);

    }

  /*  public void openDirectory(Uri uriToLoad) {
        Toast.makeText(getContext(), "Please allow permission to download status!", Toast.LENGTH_SHORT).show();
        StorageManager sm = (StorageManager) getActivity().getSystemService(Context.STORAGE_SERVICE);

        Intent intent = sm.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
        String startDir = "Android%2Fmedia%2Fcom.whatsapp%2FWhatsApp%2FMedia%2F.Statuses";
        Uri uri = intent.getParcelableExtra("android.provider.extra.INITIAL_URI");
        String scheme = uri.toString();
        Log.d("TAG", "INITIAL_URI scheme: " + scheme);
        scheme = scheme.replace("/root/", "/document/");
        scheme += "%3A" + startDir;
        uri = Uri.parse(scheme);
        intent.putExtra("android.provider.extra.INITIAL_URI", uri);
        Log.d("TAG", "uri: " + uri.toString());
        startActivityForResult(intent, 42);


    }*/

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("NextActivity12", "onActivityResult");
        if (resultCode == RESULT_OK) {
            Log.d("NextActivity12", "RESULT_OK");
            if (requestCode == 42) {
                Log.d("NextActivity12", "42");
                if (data != null) {
                    Log.d("NextActivity12", "data");
                    Uri uri = data.getData();
                    Log.d("NextActivity12", "uri = " + uri);
                    if (uri.getPath().endsWith(".Statuses")) {
                        Log.d("TAG", "onActivityResult: " + uri.getPath());
                      /*  final int takeFlags = data.getFlags()
                                & (Intent.FLAG_GRANT_READ_URI_PERMISSION);*/
                        final int takeFlags = data.getFlags()
                                & (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            Log.d("NextActivity12", "KITKAT = ");
                            getActivity().getContentResolver().takePersistableUriPermission(uri, takeFlags);
                        }

                        if (Build.VERSION.SDK_INT >= 29) {
                            /*DocumentFile fromTreeUri = DocumentFile.fromTreeUri(getContext(), uri);
                            DocumentFile[] documentFiles = fromTreeUri.listFiles();
                            Log.d("NextActivity12", "allow " + documentFiles.length);*/
                            Preference.setWhatsapp_URI(String.valueOf(uri));
                            SetImageArray(Uri.parse(Preference.getWhatsapp_URI()));
                        }
                        Toast.makeText(getContext(), "Permission allow", Toast.LENGTH_SHORT).show();

                    } else {
                        // dialog when user gave wrong path
                        Toast.makeText(getContext(), "Permission not allow", Toast.LENGTH_SHORT).show();
                        ((Activity) getContext()).finish();
                        //showWrongPathDialog();
                    }

                }
            }
        } else {
            Toast.makeText(getContext(), "Permission not allow", Toast.LENGTH_SHORT).show();
            ((Activity) getContext()).finish();
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
                    // String filePath=getPath(getContext(),documentFiles[i].getUri());
                    Log.d("NextActiv12", "path = " + path);
                    if (path.endsWith(".png") || path.endsWith(".jpg")) {
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


    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        final boolean needToCheckUri = Build.VERSION.SDK_INT >= 19;
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        // deal with different Uris.
        if (needToCheckUri && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{split[1]};
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
