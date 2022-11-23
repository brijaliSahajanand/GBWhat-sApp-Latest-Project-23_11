package com.newfastwa.msgemojitype.gbfo.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.ads.adsdemosp.AdsClass.Ads_ExitNativeFull;
import com.newfastwa.msgemojitype.gbfo.BuildConfig;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.activity.Status.WhatsappStatusModel;
import com.newfastwa.msgemojitype.gbfo.retrofit.Example;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Utils {

    private void changeurl() {
        String url = "https://instagram.fjga1-1.fna.fbcdn.net/v/t51.2885-15/286369125_5774173735945015_1289185936929805006_n.jpg?stp=dst-jpg_e15_fr_s1080x1080&_nc_ht=instagram.fjga1-1.fna.fbcdn.net&_nc_cat=107&_nc_ohc=f7-Jib43jZoAX8efZeu&edm=AABBvjUBAAAA&ccb=7-5&oh=00_AT_VMkcYHDV1NFVK3smrzky3j4F0YdOa456gLz-XruIh2w&oe=62A52D8D&_nc_sid=83d603";
    }

    public static boolean Check_WhatsApp_path = true;
    public static boolean ad_sample = false;

    public static int select_image_pos;
    public static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0";

    public static List<Example.CountryList> country_List = null;
    public static List<String> Native_image = new ArrayList<>();
    public static List<String> mutli_image = new ArrayList<>();

    public static boolean vpnStart = false;
    public static boolean isVpnConnect = false;
    public static ArrayList<WhatsappStatusModel> whatsappStatusModelArrayList;
    //    public static WhatsappStatusModel whatsappStatusModel ;
    public static int position = 0;
    public static boolean isVideo = false;


    public static String TikTokUrl = "";
    public static String FilePath = "";
    public static String PrivacyPolicyUrl = "";
//    public static String RootDirectoryInsta = "/InstaPro/";
//    public static File RootDirectoryInstaShow = new File(Environment.getExternalStorageDirectory() + "/Download/InstaPro");

    public static String RootDirectoryFacebook = "/VideoPlayerse/Facebook/";
    public static File RootDirectoryFacebookShow = new File(Environment.getExternalStorageDirectory() + "/Download/VideoPlayerse/Facebook");
    public static String RootDirectoryInsta = "/VideoPlayerse/Insta/";
    public static String RootTwitter = "/VideoPlayerse/Twitter/";
    public static String RootTikTOk = "/VideoPlayerse/TikTok/";
    public static String RootSharechat = "/VideoPlayerse/ShareChat/";


    public static File RootDirectoryInstaShow = new File(Environment.getExternalStorageDirectory() + "/Download/VideoPlayerse/Insta");
    public static File RootDirectoryWhatsappShow = new File(Environment.getExternalStorageDirectory() + "/Download/VideoPlayerse/Whatsapp");
    public static File RootDirectoryTwitter = new File(Environment.getExternalStorageDirectory() + "/Download/VideoPlayerse/Twitter");
    public static File RootDirectoryTikTok = new File(Environment.getExternalStorageDirectory() + "/Download/VideoPlayerse/TikTok");
    public static File RootDirectoryShareChat = new File(Environment.getExternalStorageDirectory() + "/Download/VideoPlayerse/ShareChat");


    public static String StaticShareDownloadRepost = "";
    private static Context context;
    public static Dialog customDialog;
    public static BroadcastReceiver onComplete = new BroadcastReceiver() {
        /* class com.insavedownloader.easyinsavedownloader.util.Utils.AnonymousClass2 */

        public void onReceive(Context context, Intent intent) {
            if (Utils.StaticShareDownloadRepost.equals("Share")) {
                if (Utils.FilePath.contains(".mp4")) {
                    Utils.shareVideo(Utils.context, Utils.FilePath);
                } else {
                    Utils.shareImage(Utils.context, Utils.FilePath);
                }
            } else if (!Utils.StaticShareDownloadRepost.equals("Repost")) {
            } else {
                if (Utils.FilePath.contains(".mp4")) {
                    Utils.shareImageVideoOnInstagram((Activity) Utils.context, Utils.FilePath, true);
                } else {
                    Utils.shareImageVideoOnInstagram((Activity) Utils.context, Utils.FilePath, false);
                }
            }
        }
    };
    public static BroadcastReceiver onComplete_download = new BroadcastReceiver() {


        public void onReceive(Context context, Intent intent) {
            if (downloadDoneinterface != null) {
                downloadDoneinterface.refreshPage();
            }
        }
    };
    public static ProgressDialog progress = null;

    public Utils(Context context2) {
        context = context2;
    }

    public static void setToast(Activity activity, String str) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, "" + str, Toast.LENGTH_SHORT).show();
            }
        });



        /*try {
            Snackbar snack = Snackbar.make(activity.findViewById(android.R.id.content), "" + str, Snackbar.LENGTH_LONG);
            View view = snack.getView();
            TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
            tv.setTextColor(activity.getResources().getColor(R.color.white));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            } else {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            snack.show();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
//        Toast makeText = Toast.makeText(context2, str, Toast.LENGTH_SHORT);
//        makeText.setGravity(17, 0, 0);
//        makeText.show();
    }
    public static HashMap<Integer, View> nativehashmap_list = new HashMap<>();


    public static void createFileFolder() {
        if (!RootDirectoryFacebookShow.exists()) {
            RootDirectoryFacebookShow.mkdirs();
        }
        if (!RootDirectoryInstaShow.exists()) {
            RootDirectoryInstaShow.mkdirs();
        }

        if (!RootDirectoryWhatsappShow.exists()) {
            RootDirectoryWhatsappShow.mkdirs();
        }
        if (!RootDirectoryTwitter.exists()) {
            RootDirectoryTwitter.mkdirs();
        }
        if (!RootDirectoryTikTok.exists()) {
            RootDirectoryTikTok.mkdirs();
        }
        if (!RootDirectoryShareChat.exists()) {
            RootDirectoryShareChat.mkdirs();
        }

    }

  /*  public static void showProgressDialog(Activity activity) {
        System.out.println("Show");
        Dialog dialog = customDialog;
        if (dialog != null) {
            dialog.dismiss();
            customDialog = null;
        }
        customDialog = new Dialog(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.progress_dialog, (ViewGroup) null);
        customDialog.setCancelable(false);
        customDialog.setContentView(inflate);
        if (!customDialog.isShowing() && !activity.isFinishing()) {
            customDialog.show();
        }
    }*/

    public static void hideProgressDialog(Activity activity) {
        Dialog dialog = customDialog;
        if (dialog != null && dialog.isShowing()) {
            customDialog.dismiss();
        }
    }

    public boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    public static String lattestfile_path = "";

    public static void startDownload(String str, String str2, Activity activity, String str3, String str4) {
        StaticShareDownloadRepost = str4;
        FilePath = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3).getAbsolutePath();
        if (str4.equals("Share")) {
            if (!new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3).exists()) {
//                setToast(activity, activity.getResources().getString(R.string.download_started));
                DownloadFile(str, str2, activity, str3);
                activity.registerReceiver(onComplete, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            } else if (str3.contains(".mp4")) {
                shareVideo(activity, FilePath);
            } else {
                shareImage(activity, FilePath);
            }
        } else if (str4.equals("Repost")) {
            if (!new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3).exists()) {
//                setToast(activity, activity.getResources().getString(R.string.download_started));

                DownloadFile(str, str2, activity, str3);
                activity.registerReceiver(onComplete, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
            } else if (str3.contains(".mp4")) {
                shareImageVideoOnInstagram(activity, FilePath, true);
            } else {
                shareImageVideoOnInstagram(activity, FilePath, false);
            }
        } else {
            if (!new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3).exists()) {
//                setToast(activity, activity.getResources().getString(R.string.download_started));
                lattestfile_path = Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3;
                DownloadFile(str, str2, activity, str3);
                activity.registerReceiver(onComplete_download, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
                return;
            }
            setToast(activity, activity.getResources().getString(R.string.already_downloader));
        }
    }


   /* public static boolean isDownloading;
    public static int fromintent;
    public static DownloadDoneinterface downloadDoneinterface;
    public static UpdateDownPer updateDownPer;


    public static void DownloadFile(String str, String str2, Context context2, String str3) {

        isDownloading = true;

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setAllowedNetworkTypes(3);
        request.setNotificationVisibility(1);
        request.setTitle(str3 + "");
        request.setVisibleInDownloadsUi(true);
        String str4 = Environment.DIRECTORY_DOWNLOADS;
        request.setDestinationInExternalPublicDir(str4, str2 + str3);
        ((DownloadManager) context2.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);


        DownloadManager downloadManager = ((DownloadManager) context2.getSystemService(Context.DOWNLOAD_SERVICE));
        long idDownLoad = downloadManager.enqueue(request);
//        ((DownloadManager) context2.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);

        context2.registerReceiver(attachmentDownloadCompleteReceive, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        new Thread(new Runnable() {

            @Override
            public void run() {

                boolean downloading = true;

                while (downloading) {

                    DownloadManager.Query q = new DownloadManager.Query();
                    q.setFilterById(idDownLoad);

                    Cursor cursor = downloadManager.query(q);
                    cursor.moveToFirst();
                    int bytes_downloaded = cursor.getInt(cursor
                            .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false;
                    }

                    final int dl_progress = (int) ((bytes_downloaded * 100l) / bytes_total);
                    Log.d("percentagedown", "run: " + dl_progress);


                    if (updateDownPer != null) {
                        updateDownPer.percentage(dl_progress);
                    }


                    cursor.close();
                }

            }
        }).start();

        if (str2.equals(RootDirectoryInsta)) {
            fromintent = 1;
        } else if (str2.equals(RootDirectoryFacebook)) {
            fromintent = 2;
        }


        try {
            if (Build.VERSION.SDK_INT >= 19) {
                MediaScannerConnection.scanFile(context2, new String[]{new File(Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3).getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    *//* class com.insavedownloader.easyinsavedownloader.util.Utils.AnonymousClass1 *//*

                    public void onScanCompleted(String str, Uri uri) {
                    }
                });
                return;
            }
            context2.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.fromFile(new File(Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static BroadcastReceiver attachmentDownloadCompleteReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(@NonNull Context context, @NonNull Intent intent) {
            String action = intent.getAction();

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                long downloadId = intent.getLongExtra(
                        DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                Log.d("Downloadcon", "onReceive: successfully complete2");
                isDownloading = false;
                fromintent = 0;
                if (downloadDoneinterface != null) {
                    downloadDoneinterface.refreshPage();
                }
            } else {
                // Loader Dismissed and Displayed appropriate Message to user.
            }
        }
    };*/

    //    public static DownloadDoneinterface downloadDoneinterface;
    public static boolean isDownloading;
    public static int fromintent;
    public static DownloadDoneinterface downloadDoneinterface;
    public static UpdateDownPer updateDownPer;

    public static void DownloadFile(String str, String str2, Context context2, String str3) {

        isDownloading = true;

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
        request.setAllowedNetworkTypes(3);
        request.setNotificationVisibility(1);
        request.setTitle(str3 + "");
        request.setVisibleInDownloadsUi(true);
        String str4 = Environment.DIRECTORY_DOWNLOADS;
        request.setDestinationInExternalPublicDir(str4, str2 + str3);
//        ((DownloadManager) context2.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);

        DownloadManager downloadManager = ((DownloadManager) context2.getSystemService(Context.DOWNLOAD_SERVICE));
        long idDownLoad = downloadManager.enqueue(request);
//        ((DownloadManager) context2.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);

        context2.registerReceiver(attachmentDownloadCompleteReceive, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

        new Thread(new Runnable() {

            @Override
            public void run() {

                boolean downloading = true;

                while (downloading) {

                    DownloadManager.Query q = new DownloadManager.Query();
                    q.setFilterById(idDownLoad);

                    Cursor cursor = downloadManager.query(q);
                    cursor.moveToFirst();
                    int bytes_downloaded = cursor.getInt(cursor
                            .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));

                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        downloading = false;
                    }
                    int dl_progress;
                    if (bytes_total != 0) {
                        dl_progress = (int) ((bytes_downloaded * 100l) / bytes_total);
                    } else {
                        dl_progress = 0;
                    }
                    Log.d("percentagedown", "run: " + dl_progress);


                    if (updateDownPer != null) {
                        updateDownPer.percentage(dl_progress);
                    }


                    cursor.close();
                }

            }
        }).start();

        if (str2.equals(RootDirectoryInsta)) {
            fromintent = 1;
        } else if (str2.equals(RootDirectoryFacebook)) {
            fromintent = 2;
        } else if (str2.equals(RootTwitter)) {
            fromintent = 3;
        } else if (str2.equals(RootTikTOk)) {
            fromintent = 4;
        } else if (str2.equals(RootSharechat)) {
            fromintent = 5;
        }


        try {
            if (Build.VERSION.SDK_INT >= 19) {
                MediaScannerConnection.scanFile(context2, new String[]{new File(Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3).getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {

                    public void onScanCompleted(String str, Uri uri) {
                    }
                });
                return;
            }
            context2.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.fromFile(new File(Environment.DIRECTORY_DOWNLOADS + "/" + str2 + str3))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static BroadcastReceiver attachmentDownloadCompleteReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(@NonNull Context context, @NonNull Intent intent) {
            String action = intent.getAction();

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                long downloadId = intent.getLongExtra(
                        DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                Log.d("Downloadcon", "onReceive: successfully complete2");
                isDownloading = false;
                lattestfile_path = "";
                fromintent = 0;
                if (downloadDoneinterface != null) {
                    downloadDoneinterface.refreshPage();
                }
            } else {
                // Loader Dismissed and Displayed appropriate Message to user.
            }
        }
    };

    public static void shareImage(Context context2, String str) {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", context2.getResources().getString(R.string.share_txt));
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(context2.getContentResolver(), str, "", (String) null)));
            intent.setType("image/*");
            context2.startActivity(Intent.createChooser(intent, context2.getResources().getString(R.string.share_image_via)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shareImageVideoOnInstagram(Context context2, String str, boolean z) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.setPackage("com.instagram.android");
            intent.putExtra("android.intent.extra.TEXT", "");
            if (z) {
                intent.putExtra("android.intent.extra.STREAM", Uri.parse(str));
                intent.setType("simplevideoshow/*");
            } else {
                intent.putExtra("android.intent.extra.STREAM", Uri.parse(MediaStore.Images.Media.insertImage(context2.getContentResolver(), str, "", (String) null)));
                intent.setType("image/*");
            }
            intent.addFlags(1);
            try {
                context2.startActivity(intent);
            } catch (Exception unused) {
                setToast((Activity) context2, context2.getResources().getString(R.string.instagram_not_installed));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shareVideo(Context context2, String str) {
        Uri parse = Uri.parse(str);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("simplevideoshow/mp4");
        intent.putExtra("android.intent.extra.STREAM", parse);
        intent.addFlags(1);
        try {
            context2.startActivity(Intent.createChooser(intent, "Share Video using"));
        } catch (ActivityNotFoundException unused) {
            setToast((Activity) context2, context2.getResources().getString(R.string.no_app_found));
        }
    }

    public static void RateApp(Context context2) {
        String packageName = context2.getPackageName();
        try {
            context2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
        } catch (ActivityNotFoundException unused) {
            context2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    public static void MoreApp(Context context2) {
        String packageName = context2.getPackageName();
        try {
            context2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + packageName)));
        } catch (ActivityNotFoundException unused) {
            context2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    public static void ShareApp(Context context2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.SUBJECT", context2.getString(R.string.app_name));
        intent.putExtra("android.intent.extra.TEXT", context2.getString(R.string.share_app_message) + ("\nhttps://play.google.com/store/apps/details?id=" + context2.getPackageName()));
        intent.setType("text/plain");
        context2.startActivity(Intent.createChooser(intent, "Share"));
    }

    public static void OpenApp(Context context2, String str) {
        Intent launchIntentForPackage = context2.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            context2.startActivity(launchIntentForPackage);
        } else {
            setToast((Activity) context2, "App Not Available.");
        }
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.equalsIgnoreCase("0");
    }

    public static String getDate(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return simpleDateFormat.format(instance.getTime());
    }

    public static String getTimeAgoString(long j, Context context2) {
        try {
            Date parse = new SimpleDateFormat("dd/MM/yyyy").parse(getDate(j));
            Date date = new Date();
            long seconds = TimeUnit.MILLISECONDS.toSeconds(date.getTime() - parse.getTime());
            long minutes = TimeUnit.MILLISECONDS.toMinutes(date.getTime() - parse.getTime());
            long hours = TimeUnit.MILLISECONDS.toHours(date.getTime() - parse.getTime());
            long days = TimeUnit.MILLISECONDS.toDays(date.getTime() - parse.getTime());
            if (seconds < 60) {
                return seconds + context2.getResources().getString(R.string.second_ago);
            } else if (minutes < 60) {
                return minutes + context2.getResources().getString(R.string.minutes_ago);
            } else if (hours < 24) {
                return hours + context2.getResources().getString(R.string.hours_ago);
            } else {
                return days + context2.getResources().getString(R.string.day_ago);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void Logout(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton(activity.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialogInterface, int i) {
//                SharePrefs.getInstance(activity).putBoolean(SharePrefs.ISINSTALOGIN, false);
//                SharePrefs.getInstance(activity).putString(SharePrefs.COOKIES, "");
//                SharePrefs.getInstance(activity).putString(SharePrefs.CSRF, "");
//                SharePrefs.getInstance(activity).putString(SharePrefs.SESSIONID, "");
//                SharePrefs.getInstance(activity).putString(SharePrefs.USERID, "");
                dialogInterface.cancel();
//                activity.startActivity(new Intent(activity, LoginActivity.class));
            }
        });
        builder.setNegativeButton(activity.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog create = builder.create();
        create.setMessage(activity.getResources().getString(R.string.logout_message));
        create.show();
    }

/*
    public static void showProgress(Context context2) {
        if (context2 != null) {
            try {
                if (progress == null || !progress.isShowing()) {
                    progress = ProgressDialog.show(context2, null, null);
                    progress.getWindow().setBackgroundDrawableResource(17170445);
                    progress.setContentView(R.layout.progress_loading);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
*/

    public static void hideProgress() {
        ProgressDialog progressDialog = progress;
        if (progressDialog != null && progressDialog.isShowing()) {
            progress.dismiss();
        }
    }


    static Dialog dialog_simple;

    public static void showProgressDialog(Activity context) {
        try {
            try {
                if (dialog_simple != null && dialog_simple.isShowing()) {
                    dialog_simple.dismiss();
                }
            } catch (NullPointerException n) {
                n.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            dialog_simple = new Dialog(context);
            dialog_simple.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            LayoutInflater inflater = (LayoutInflater) dialog_simple.getLayoutInflater();
            View customView = inflater.inflate(R.layout.custom_progressbar, null);
            dialog_simple.setContentView(customView);
            dialog_simple.setCancelable(false);
            dialog_simple.setCanceledOnTouchOutside(false);
            dialog_simple.show();
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void hideProgressDialog() {
        try {
            if (dialog_simple != null && dialog_simple.isShowing()) {
                dialog_simple.dismiss();
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static Dialog dialog_simple2;

    public static void Progress_show(Activity context) {
        try {
            try {
                if (dialog_simple2 != null && dialog_simple2.isShowing()) {
                    dialog_simple2.dismiss();
                }
            } catch (NullPointerException n) {
                n.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            dialog_simple2 = new Dialog(context);
            dialog_simple2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            LayoutInflater inflater = (LayoutInflater) dialog_simple2.getLayoutInflater();
            View customView = inflater.inflate(R.layout.custom_progressbar, null);
            dialog_simple2.setContentView(customView);
            dialog_simple2.setCancelable(false);
            dialog_simple2.setCanceledOnTouchOutside(false);
            dialog_simple2.show();
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void Progress_hide() {
        try {
            if (dialog_simple2 != null && dialog_simple2.isShowing()) {
                dialog_simple2.dismiss();
            }
        } catch (NullPointerException n) {
            n.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface DownloadDoneinterface {
        void refreshPage();
    }

    public interface UpdateDownPer {
        void percentage(int percentage);
    }


    public static Integer Old_size = 0;
    public static int age_selected_pos = 0;


    public static int CheckInstallTask(Context context) {
        int install_app = 0;
        List<ApplicationInfo> apps = context.getPackageManager().getInstalledApplications(0);
        if (apps != null && apps.size() != 0) {
            Log.d("CheckInstallTask", "pkgAppsList = " + apps.size());
            install_app = apps.size();

            return install_app;
        }
        Log.d("CheckInstallTask", "pkgAppsList null");
        return 0;
    }

    public static ProgressDialog pd_ad_loading;

    public static void ads_loading_dialog(Context context, boolean dialog_check) {

        if (dialog_check) {
            pd_ad_loading = new ProgressDialog(context);
            try {
                pd_ad_loading.show();
            } catch (WindowManager.BadTokenException e) {

            }
            pd_ad_loading.setCancelable(false);
            pd_ad_loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            pd_ad_loading.setContentView(R.layout.ads_loading_dialog);

        } else {
            if (pd_ad_loading != null) {
                pd_ad_loading.dismiss();
            }
        }
    }


    private static OnCheckNet onChecknet;

    public interface OnCheckNet {
        void OnCheckNet(boolean b);
    }


    public static void isConnectingToInternet(Context context, OnCheckNet onChecknets, boolean... booleans) {
        onChecknet = onChecknets;
        CheckInternetData(context);

        // return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static void CheckInternetData(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            onChecknet.OnCheckNet(true);
        } else {
            try {
                showAlertDialog(context, context.getString(R.string.app_name),
                        context.getString(R.string.disconnected),
                        "Retry");
            } catch (NumberFormatException ex) { // handle your exception
            }
        }
    }

    public static void showAlertDialog(Context context, String title, String msg, String positiveText) {
        androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton(positiveText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CheckInternetData(context);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onChecknet.OnCheckNet(false);
                    }
                })
                .show();
    }

    public static boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


    @SuppressLint("NewApi")
    public static void url_passing(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            Bundle bundle = new Bundle();
            bundle.putBinder("android.support.customtabs.extra.SESSION", (IBinder) null);
            intent.putExtras(bundle);
            //intent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", getResources().getString(First_Activity.this, R.color.colorPrimary));
            intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
            intent.setPackage("com.android.chrome");
            intent.setData(Uri.parse(Preference.getlink()));
            context.startActivity(intent, (Bundle) null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isNetworkConnected(Activity context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }



    public static void setUpCountry() {
        Utils.country_List = Preference.getCountry_list();
        int pos = new Random().nextInt(Utils.country_List.size());
        if(Utils.country_List.size() > 2 && Utils.country_List.get(pos).name.equals(Preference.getserver_name())){
            setUpCountry();
            return;
        }

        String selectedCountryCode = Utils.country_List.get(pos).code;
        String selectedCountryName = Utils.country_List.get(pos).name;
        String selectedCountryImage = Utils.country_List.get(pos).cuntryimages;
     //  Log.d("selectedCountry", "pos = " + pos);
        //Log.d("selectedCountry", "selectedCountry = " + selectedCountryCode);
      //  Log.d("selectedCountry", "selectedCountryName = " + selectedCountryName);
        Preference.set_server_short(selectedCountryCode);
        Preference.setserver_name(selectedCountryName);
        Preference.setServer_image(selectedCountryImage);
    }
    public static Boolean  isdebuggle(Context context) {
        if (BuildConfig.DEBUG) {
            return false;
        }
        if (Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 0) == 1) {
            // debugging enabled
            Log.d("oncheck_debug", "isdebuggle  true ");
            return true;
        } else {
            Log.d("oncheck_debug", "isdebuggle  false ");
            return false;
            // debugging does not enabled
        }
    }


    public static void Get_Developer_Dialog(Context context) {

        warningdialog = new Dialog(context);
        warningdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        warningdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        warningdialog.setContentView(R.layout.developer_dialog);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        warningdialog.getWindow().setLayout(width, height);
        warningdialog.setCancelable(false);
        warningdialog.show();

        Button btn_recheck = warningdialog.findViewById(R.id.btn_recheck);
        Button btn_turnoff = warningdialog.findViewById(R.id.btn_turnoff);

        btn_recheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warningdialog.dismiss();
                CheckDebugData(context);
            }
        });

        btn_turnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                developer_intent(context);
                warningdialog.dismiss();
            }
        });

    }

    private static OnCheckDebug onCheckDebug1;

    public interface OnCheckDebug {
        void OnCheckdebug(boolean b);
    }


    public static void developer_intent(Context context){
        context.startActivity(new Intent("android.settings.APPLICATION_DEVELOPMENT_SETTINGS"));
    }

    public static no_debug no_debug;
    public interface no_debug {
        void no_debug();
    }


    public static void debug_check(no_debug no_debug) {
        Utils.no_debug = no_debug;
    }

    private static void CheckDebugData(Context context) {
        if (isdebuggle(context)) {
            Get_Developer_Dialog(context);
        } else {
            onCheckDebug1.OnCheckdebug(true);
        }
    }

    public static Dialog warningdialog;
    public static Dialog exit_dialog;


    public static void Exit_Dialog(Activity activity) {
        exit_dialog = new Dialog(activity);
        exit_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Window window = exit_dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        window.getDecorView().setSystemUiVisibility(uiOptions);

        LayoutInflater inflater = (LayoutInflater) exit_dialog.getLayoutInflater();
        View customView = inflater.inflate(R.layout.custom_exit_dialog, null);
        exit_dialog.setContentView(customView);
        exit_dialog.getWindow().setGravity(Gravity.BOTTOM);
        exit_dialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        exit_dialog.getWindow().getAttributes().windowAnimations = R.style.ExitDialogAnimation;
        exit_dialog.setCancelable(true);
        exit_dialog.setCanceledOnTouchOutside(true);
        TextView txt_done = (TextView) exit_dialog.findViewById(R.id.txt_done);

        LinearLayout llline = (LinearLayout) exit_dialog.findViewById(R.id.llline);
        LinearLayout llnative = (LinearLayout) exit_dialog.findViewById(R.id.llnative);
        TextView ad_call_to_action = (TextView) exit_dialog.findViewById(R.id.ad_call_to_action);

        Ads_ExitNativeFull.Exit_NativeFull_Show(activity, llnative, llline, ad_call_to_action);

        txt_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finishAffinity();
            }
        });

        exit_dialog.show();
    }

}
