package com.newfastwa.msgemojitype.gbfo.activity;

import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.ads.adsdemosp.AdsClass.Ads_SplashAppOpen;
import com.ads.adsdemosp.Appcontroller;
import com.ads.adsdemosp.Ids_Class;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.BuildConfig;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.VPN.Connect_Network_Screen;
import com.newfastwa.msgemojitype.gbfo.retrofit.APIClient;
import com.newfastwa.msgemojitype.gbfo.retrofit.APIInterface;
import com.newfastwa.msgemojitype.gbfo.retrofit.Example;
import com.newfastwa.msgemojitype.gbfo.retrofit.Pro_IPModel;
import com.newfastwa.msgemojitype.gbfo.retrofit.TraficLimitResponse;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;
import com.newfastwa.msgemojitype.gbfo.utils.Utils;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import unified.vpn.sdk.AuthMethod;
import unified.vpn.sdk.ClientInfo;
import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.HydraTransport;
import unified.vpn.sdk.HydraTransportConfig;
import unified.vpn.sdk.OpenVpnTransport;
import unified.vpn.sdk.OpenVpnTransportConfig;
import unified.vpn.sdk.RemainingTraffic;
import unified.vpn.sdk.SdkNotificationConfig;
import unified.vpn.sdk.SessionConfig;
import unified.vpn.sdk.TrackingConstants;
import unified.vpn.sdk.TrafficRule;
import unified.vpn.sdk.TransportConfig;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.User;
import unified.vpn.sdk.VpnException;
import unified.vpn.sdk.VpnState;

public class Splash_Activity extends BaseActivity {

    private static final String CHANNEL_ID = "VPNMaster";
    public static List<String> admob_native_list = new ArrayList<>();
    public static List<String> admob_native_banner_list = new ArrayList<>();
    public static List<String> admob_interstitial_list = new ArrayList<>();
    public static List<String> admob_app_open_list = new ArrayList<>();
    public static List<String> admob_adaptive_banner_list = new ArrayList<>();
    public static List<String> unknown_url_list = new ArrayList<>();
    public static APIInterface apiInterface;
    public static int fromstart = 0;
    boolean today_date = true;
    boolean vpn_show = true;
    String todaydate;
    String Region, Country, city;
    UnifiedSdk unifiedSDK;
    String publicVpnKey = "";
    String PasswordVpn = "";
    Dialog Waringdialog;
    private Handler mUIHandler = new Handler(Looper.getMainLooper());
    final Runnable mUIUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            checkRemainingTraffic();
            mUIHandler.postDelayed(mUIUpdateRunnable, 10000);
        }
    };

    public static String megabyteCount(long bytes) {
        return String.format(Locale.getDefault(), "%.0f", (double) bytes / 1024 / 1024);
    }

    @Override
    protected void onResume() {
        Appcontroller.fast_start = true;
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Utils.isdebuggle(Splash_Activity.this)) {
            Utils.Get_Developer_Dialog(Splash_Activity.this);
            Utils.debug_check(new Utils.no_debug() {
                @Override
                public void no_debug() {
                    Log.d("oncheck_debug", "splash ");
                    Utils.warningdialog.dismiss();
                    if (Utils.isNetworkConnected(Splash_Activity.this)) {
                        subScribePushChannel();
                    } else {
                        getNodataAlert();
                    }

                }
            });
        } else {
            if (Utils.isNetworkConnected(Splash_Activity.this)) {
                subScribePushChannel();
            } else {
                getNodataAlert();
            }
        }

    }




    private void subScribePushChannel() {
        CheckUpSetting();
        try {
            FirebaseMessaging.getInstance().subscribeToTopic("GBWhatsApp_BZ5")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void CallIpApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ip-api.com/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(APIInterface.class);
        Call<Pro_IPModel> call = apiInterface.getipdata();
        call.enqueue(new Callback<Pro_IPModel>() {
            @Override
            public void onResponse(Call<Pro_IPModel> call, Response<Pro_IPModel> response) {
                if (response.isSuccessful()) {
                    Region = response.body().getRegionName();
                    Country = response.body().getCountry();
                    city = response.body().getCity();
                    Preference.setStateNameVPN(Region);
                    Preference.setCountryNmeVPN(Country);
                    Preference.setCityNmeVPN(city);

                    Log.d("publicip", "Region = " + Region);
                    Log.d("publicip", "Country = " + Country);
                    Log.d("publicip", "Country = " + city);
                } else {
                    Log.d("publicip", "onResponse: failed");
                }

                CallApiAds();
            }

            @Override
            public void onFailure(Call<Pro_IPModel> call, Throwable t) {
                Log.d("publicip", "onfailure: failed" +t.getLocalizedMessage());
                CallApiAds();
            }
        });
    }

    private AlertDialog.Builder getNodataAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Splash_Activity.this);
        builder.setTitle("Internet is required to use this app");

        builder.setPositiveButton("ok", (dialog, which) -> {
            finish();

        });


        builder.setNegativeButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Utils.isNetworkConnected(Splash_Activity.this)) {

                    CheckUpSetting();
                    dialog.cancel();

                } else {

                    dialog.cancel();
                    getNodataAlert();
                }

            }
        });

        builder.setCancelable(false);
        try {
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder;
    }

    ;

    private void CallApiAds() {
        HashMap<String, String> SendData = new HashMap<>();
        SendData.put("package", this.getPackageName());
        SendData.put("country", Preference.getCountryNmeVPN());
        SendData.put("state", Preference.getStateNameVPN());
        SendData.put("city", Preference.getCityNmeVPN());

        APIInterface apiInterface_local = APIClient.getClient().create(APIInterface.class);
        Call<Example> call = apiInterface_local.setting_api(SendData);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                if (response.isSuccessful()) {
                    Example.Package aPackage = response.body().getPackage();

                    admob_adaptive_banner_list = response.body().getPackage().getAdmob_banner_list();
                    admob_native_list = response.body().getPackage().getAdmob_native_list();
                    admob_native_banner_list = response.body().getPackage().getAdmob_native_banner_list();
                    admob_interstitial_list = response.body().getPackage().getAdmob_interstitial_list();
                    admob_app_open_list = response.body().getPackage().getAdmob_app_open_list();

                    Preference.setFb_interstitial_id(aPackage.fb_interstitial_id);
                    Preference.setFb_native_banner_id(aPackage.fb_native_banner_id);
                    Preference.setFb_native_id(aPackage.fb_native_id);

                    Preference.setVn_direct_connect(aPackage.vn_direct_connect);

                    vpn_show = response.body().vn_show;
                    Preference.setIs_quiz(aPackage.is_quiz);
                    Preference.setQuiz_header_show(aPackage.quiz_header_show);
                    Preference.setIs_small_native_qureka(aPackage.is_small_native_quiz);
                    Preference.setIs_big_native_qureka(aPackage.is_big_native_quiz);
                    Preference.setAd_one_by_one_ids(aPackage.ad_one_by_one_ids);


                    Preference.setBottom_ads_type(aPackage.bottom_ads_type);
                    Preference.setads_type(aPackage.ads_type);
                    Preference.set_AdsClick(aPackage.adsClick);
                    Preference.setBack_click(aPackage.backClick);
                    Preference.setNative_by_page(aPackage.native_by_page);


                    Preference.setAdmob_page(aPackage.admob_page);
                    Preference.setinter_admob(aPackage.inter_admob_click);
                    Preference.setnative_admob(aPackage.native_admob_click);
                    Preference.setbanner_admob(aPackage.banner_admob_click);
                    Preference.setAds_open_admob(aPackage.ads_open_admob_click);
                    Preference.setInter_back_admob(aPackage.inter_back_admob_click);

                    Preference.setVPN_Show(vpn_show);

                    Utils.country_List = response.body().countryList;
                    Preference.setCountry_list(Utils.country_List);

                    try {
                        if (!response.body().getPackage().vn_id.isEmpty() && !response.body().getPackage().vn_pass.isEmpty()) {
                            Preference.setVnid(response.body().getPackage().vn_id);
                            Preference.setVnpassword(response.body().getPackage().vn_pass);
                        }
                    } catch (NullPointerException n) {
                        n.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Preference.setUrl_type(aPackage.url_type);
                    Preference.setUrl_default(aPackage.url_default);
                    unknown_url_list = response.body().getPackage().getUrlArrays();


                    Preference.setVn_header_show(aPackage.vn_header_show);
                    Preference.setRendomserver(response.body().getPackage().random_server);
                    Preference.set_server_short(response.body().getPackage().country_code);
                    Preference.setserver_name(response.body().getPackage().country_name);
                    Preference.setServer_image(response.body().getPackage().images_url);

                    Preference.setAppOpen_click(aPackage.app_open_interstitial_click);
                    Preference.setAppOpen_inter_show(aPackage.app_open_interstitial_show);
                    Preference.setApp_open_back_interstitial_click(aPackage.app_open_back_interstitial_click);

                    Preference.setanimationtype(aPackage.ads_button_animation_type);
                    Preference.setbuttonanimate(aPackage.ads_buttom_animation);

                    try {
                        Preference.setNative_btn_type(aPackage.native_btn_type);
                        Preference.setButton_animation_native(aPackage.button_animation_native);
                    } catch (Exception e) {

                    }


                    Preference.setQureka_link(aPackage.link);

                    Preference.setnative_button_color(aPackage.admob_native_btn_color);
                    Preference.setnative_btn_text_color(aPackage.admob_native_btn_text_color);
                    Preference.setadmob_native_content_text_color(aPackage.admob_native_content_text_color);
                    Preference.setadmob_native_bg_color(aPackage.admob_native_bg_color);

                    Preference.setfb_native_btn_color(aPackage.fb_native_btn_color);
                    Preference.setfb_native_btn_text_color(aPackage.fb_native_btn_text_color);
                    Preference.setfb_native_content_text_color(aPackage.fb_native_content_text_color);
                    Preference.setfb_native_bg_color(aPackage.fb_native_bg_color);
                    Preference.setPrivacy_policy(aPackage.privacy_policy);
                    Preference.setComing_soon(aPackage.coming_soon);
                    Preference.setScreen_show(aPackage.screen_show);



                    Preference.setPrivacy_policy_html(response.body().privacypolicy);

                    FirebaseAnalytics.getInstance(Splash_Activity.this).setAnalyticsCollectionEnabled(aPackage.firebase_analytics);


                    Release_AdsSetUP();

                    int VERSION_CODE = BuildConfig.VERSION_CODE;
                    int Api_VERSION_CODE = Integer.parseInt(response.body().getPackage().getVersionName());

                    if (response.body().getPackage().getMaintenance()) {
                        finish_dialogue(response.body().getPackage().getAppMsg(), response.body().getPackage().getUpdateUrl(), "alert_msg");
                    } else if (Api_VERSION_CODE > VERSION_CODE) {
                        finish_dialogue(response.body().getPackage().getAppMsg(), response.body().getPackage().getUpdateUrl(), "update");
                    } else {

                        int version1 = Integer.parseInt(response.body().getPackage().versionCode);
                        int real_version1 = BuildConfig.VERSION_CODE;
                        Log.d("Splash123", "version = " + version1);

                        Log.d("Splash123", "real_version = " + real_version1);
                        if (version1 == real_version1) {
                            Log.d("Splash123", "true");
                            if (response.body().getPackage().update_coming_soon) {
                                Preference. setComing_soon(true);
                                startActivity(new Intent(Splash_Activity.this, ComingsoonActivity.class));
                                finish();
                                return;
                            }
                        }

                        if (Preference.getVPN_Show()) {
                            initHydraSdk();
                        } else {
                            PassActivity();
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("prefrences", "onFailure: " + t.toString());

            }
        });

    }

    public void finish_dialogue(String message, final String link, String type) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.version_check_dialogue);
        dialog.show();
        TextView txt_download = dialog.findViewById(R.id.txt_download);
        TextView txt_message = dialog.findViewById(R.id.txt_message);
        txt_message.setText(message);

        if (type.equals("alert_msg")) {
            txt_download.setText("Download");
        } else {
            txt_download.setText("Update");
        }

        txt_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri uri = Uri.parse(link);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.android.vending");
                    startActivity(intent);

                } catch (Exception e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(link));
                    startActivity(intent);
                }
            }
        });
    }

    private void Release_AdsSetUP() {

        Ids_Class.admobInterstitialIds_list = admob_interstitial_list;

        Ids_Class.admobNativeFullIds_list = admob_native_list;

        Ids_Class.admobNativeCustomIds_list = admob_native_banner_list;

        Ids_Class.admobBannerIds_list = admob_adaptive_banner_list;

        Ids_Class.admobAppopenIds_list = admob_app_open_list;


        Ids_Class.fb_Interstitial_ids = Preference.getFb_interstitial_id();
        Ids_Class.fb_NativeFull_ids = Preference.getFb_native_id();
        Ids_Class.fb_NativeBanner_ids = Preference.getFb_native_banner_id();

        Ids_Class.ads_type = Preference.getads_type();
        Ids_Class.ads_native_type = Preference.getBottom_ads_type();

        Ids_Class.ads_native_btn_color = Preference.getnative_button_color();
        Ids_Class.ads_native_text_color = Preference.getnative_btn_text_color();
        Ids_Class.ads_native_bg_color = Preference.getadmob_native_bg_color();
        Ids_Class.ads_native_in_text_color = Preference.getadmob_native_content_text_color();

        Ids_Class.buttonanimate = Preference.getbuttonanimate();
        Ids_Class.animation_type = Preference.getanimationtype();
        Ids_Class.button_animation_native = Preference.getButton_animation_native();
        Ids_Class.native_btn_type = Preference.getNative_btn_type();

        Ids_Class.Interstitial_adsclick = Preference.get_AdsClick();
        Ids_Class.Interstitial_Backadsclick = Preference.getBack_click();
        Ids_Class.Native_adsscreen = Preference.getNative_by_page();
        Ids_Class.AppOpen_adsclick = Preference.getAppOpen_click();

        Ids_Class.ads_App_open_InterstitialAd = Preference.getAppOpen_inter_show();
        Ids_Class.AppOpen_Backadsclick = Preference.getApp_open_back_interstitial_click();


        Ids_Class.ads_quiz_show = Preference.getIs_quiz();
        Ids_Class.ads_quiz_by_page_show = Preference.getAdmob_page();
        Ids_Class.quiz_Interstitial_adsclick = Preference.getinter_admob();
        Ids_Class.quiz_Native_adsclick = Preference.getnative_admob();
        Ids_Class.quiz_Banner_adsclick = Preference.getbanner_admob();
        Ids_Class.quiz_AppOpen_adsclick = Preference.getAds_open_admob();
        Ids_Class.quiz_Interstitial_backadsclick = Preference.getInter_back_admob();

        Ids_Class.is_big_native_quiz = Preference.getIs_big_native_qureka();
        Ids_Class.is_small_native_quiz = Preference.getIs_small_native_qureka();
        Ids_Class.quiz_header_show = Preference.getQuiz_header_show();
        Ids_Class.ad_one_by_one_ids = Preference.getAd_one_by_one_ids();


        Ids_Class.fb_ads_native_btn_color = Preference.getfb_native_btn_color();
        Ids_Class.fb_ads_native_text_color = Preference.getfb_native_btn_text_color();
        Ids_Class.fb_ads_native_bg_color = Preference.getfb_native_bg_color();
        Ids_Class.fb_ads_native_in_txt_color = Preference.getfb_native_content_text_color();


        Ids_Class.quizLink = Preference.getQureka_link();


    }

    private void LoadNativeAds() {
        Ids_Class.Laod_NativeAds(Splash_Activity.this);
    }

    private void CheckUpSetting() {
        UnifiedSdk.getVpnState(new unified.vpn.sdk.Callback<VpnState>() {
            @Override
            public void success(@NonNull VpnState vpnState) {

                switch (vpnState) {
                    case IDLE: {
                        Log.d("Splash_Activity12", "IDLE = " + vpnState);
                        Preference.setisVpnConnect(false);
                        Utils.vpnStart = false;
                        if (!vpn()) {
                            Log.d("Splash_Activity12", "vpn disconnect");
                            if (Preference.getCountryNmeVPN().isEmpty() || Preference.getStateNameVPN().isEmpty() || Preference.getCityNmeVPN().isEmpty()) {
                                Log.d("Splash_Activity12", "check ip");
                                CallIpApi();
                            } else {
                                Log.d("Splash_Activity12", "not check ip");
                                CallApiAds();
                            }
                        } else {
                            //  Toast.makeText(this, "Please disconnect VPN after use our APP!", Toast.LENGTH_SHORT).show();
                            Log.d("Splash_Activity12", "other vpn connected");
                            GetWaringDialog();

                        }
                        break;
                    }
                    case CONNECTED: {
                        Log.d("Splash_Activity12", "CONNECTED = " + vpnState);
                        Preference.setisVpnConnect(true);
                        Utils.vpnStart = true;
                        CallApiAds();
                        break;
                    }
                    case CONNECTING_VPN: {
                        Log.d("Splash_Activity12", "CONNECTING_VPN = " + vpnState);

                        break;
                    }

                    case CONNECTING_CREDENTIALS:
                    case CONNECTING_PERMISSIONS:
                    case PAUSED: {
                        Log.d("Splash_Activity12", "PAUSED  = " + vpnState);
                        break;
                    }
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
                Log.d("Splash_Activity12", "getVpnState  failure = " + e.getMessage());
                Preference.setisVpnConnect(false);
                if (!vpn()) {
                    Log.d("Splash_Activity12", "vpn disconnect");
                    if (Preference.getCountryNmeVPN().isEmpty() || Preference.getStateNameVPN().isEmpty() || Preference.getCityNmeVPN().isEmpty()) {
                        Log.d("Splash_Activity12", "check ip");
                        CallIpApi();
                    } else {
                        Log.d("Splash_Activity12", "not check ip");
                        CallApiAds();
                    }
                } else {
                    //  Toast.makeText(this, "Please disconnect VPN after use our APP!", Toast.LENGTH_SHORT).show();
                    Log.d("Splash_Activity12", "other vpn connected");
                    GetWaringDialog();

                }
            }
        });


    }

    public boolean vpn() {
        String iface = "";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iface = networkInterface.getName();
                Log.d("DEBUG", "IFACE NAME: " + iface);
                if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true;
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }

        return false;
    }

    public void initHydraSdk() {
        publicVpnKey = Preference.getVnid();
        PasswordVpn = Preference.getVnpassword();

        createNotificationChannel();
        ClientInfo clientInfo;
        if (Preference.getUrl_type()) {
            clientInfo = ClientInfo.newBuilder()
                    .addUrls(unknown_url_list)
                    .carrierId(publicVpnKey)
                    .build();
        } else {
            clientInfo = ClientInfo.newBuilder()
                    .addUrl(Preference.getUrl_default())
                    .carrierId(publicVpnKey)
                    .build();
        }
        List<TransportConfig> transportConfigList = new ArrayList<>();
        transportConfigList.add(HydraTransportConfig.create());
        transportConfigList.add(OpenVpnTransportConfig.tcp());
        transportConfigList.add(OpenVpnTransportConfig.udp());
        UnifiedSdk.update(transportConfigList, CompletableCallback.EMPTY);

        unifiedSDK = UnifiedSdk.getInstance(clientInfo);
        SdkNotificationConfig notificationConfig = SdkNotificationConfig.newBuilder()
                .title(getResources().getString(R.string.app_name))
                .channelId(CHANNEL_ID)
                .build();
        UnifiedSdk.update(notificationConfig);
        loginToVpn();

    }

    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "VPNMaster";
            String description = "VPN notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public void loginToVpn() {
        Log.e("MainActivity12", "loginToVpn");
        AuthMethod authMethod = AuthMethod.anonymous();
        UnifiedSdk.getInstance().getBackend().login(authMethod, new unified.vpn.sdk.Callback<User>() {
            @Override
            public void success(@NonNull User user) {
                Preference.setAura_user_id(user.getSubscriber().getId());
                // Log.e("MainActivity12", "success" + user);
                LoginAPi_Token();
            }

            @Override
            public void failure(@NonNull VpnException e) {
                Log.e("MainActivity12 e - ", e.getMessage());

                Preference.setVPN_Show(false);
                PassActivity();
                //Log.e("MainActivity12", "success = " + e);
            }
        });
    }

    private void LoginAPi_Token() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-prod.northghost.com/partner/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        APIInterface apiInterface_local = retrofit.create(APIInterface.class);
        Call<TraficLimitResponse> call = apiInterface_local.Call_Add_Trafic("login?login=" + publicVpnKey + "&password=" + PasswordVpn);
        call.enqueue(new Callback<TraficLimitResponse>() {
            @Override
            public void onResponse(Call<TraficLimitResponse> call, Response<TraficLimitResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("ScratchActivity1", "LoginAPi_Token  =  response succ = " + response.body().result);
                    if (response.body().result.equals("OK")) {
                        Preference.setAccessToken(response.body().access_token);
                        PassActivity();
                    } else {
                        PassActivity();
                    }

                } else {
                    //  Log.d("ScratchActivity1", "LoginAPi_Token = " + response.message());
                    PassActivity();
                }


            }

            @Override
            public void onFailure(Call<TraficLimitResponse> call, Throwable t) {
                PassActivity();
                Log.d("ScratchActivity1", "LoginAPi_Token onFailure= " + t.getMessage());
            }
        });
    }

    private void Set_Limit_size() {
        int New_limit_traffic = 1000;
        long total_bytes = New_limit_traffic * 1048576;
        Delete_ApiCall(total_bytes);
    }

    private void Delete_ApiCall(long total_bytes) {
        Utils.showProgressDialog(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-prod.northghost.com/partner/subscribers/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        APIInterface mApiInterface = retrofit.create(APIInterface.class);
        Call<TraficLimitResponse> call = mApiInterface.Call_Delete_Trafic(String.valueOf(Preference.getAura_user_id()) + "/traffic?access_token=" + Preference.getAccessToken());
        call.enqueue(new Callback<TraficLimitResponse>() {
            @Override
            public void onResponse(Call<TraficLimitResponse> call, Response<TraficLimitResponse> response) {
                Utils.hideProgressDialog();
                if (response.isSuccessful()) {
                    Log.d("ScratchActivity1", "Delete_ApiCall  =  OK");
                    Add_Trafic_size(total_bytes);
                } else {
                    Vn_PassActivity();
                    Log.d("ScratchActivity1", "Delete_ApiCall = " + response.message());
                }

            }

            @Override
            public void onFailure(Call<TraficLimitResponse> call, Throwable t) {
                Utils.hideProgressDialog();
                Vn_PassActivity();
            }
        });
    }

    private void Add_Trafic_size(long total_bytes) {
        Utils.showProgressDialog(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api-prod.northghost.com/partner/subscribers/")
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        APIInterface mApiInterface = retrofit.create(APIInterface.class);
        Call<TraficLimitResponse> call = mApiInterface.Call_Add_Trafic(String.valueOf(Preference.getAura_user_id()) + "/traffic?access_token=" + Preference.getAccessToken() + "&traffic_limit=" + String.valueOf(total_bytes));
        call.enqueue(new Callback<TraficLimitResponse>() {
            @Override
            public void onResponse(Call<TraficLimitResponse> call, Response<TraficLimitResponse> response) {
                Utils.hideProgressDialog();
                if (response.isSuccessful()) {
                    Log.d("ScratchActivity1", "Add_Trafic_size  =  OK");
                    Vn_PassActivity();
                } else {
                    Log.d("ScratchActivity1", "Add_Trafic_size = " + response.message());
                    //  Toast.makeText(Pro_Splash_Activity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                    Vn_PassActivity();
                }

            }

            @Override
            public void onFailure(Call<TraficLimitResponse> call, Throwable t) {
                Utils.hideProgressDialog();
                Vn_PassActivity();
                // Toast.makeText(Pro_Splash_Activity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void PassActivity() {
        if (!Preference.getComing_soon()) {
            if (Preference.getVPN_Show()) {
                if (Preference.getVn_direct_connect()) {
                    AutoVNStart();
                    return;
                }
            }
        }


        Vn_PassActivity();
    }

    private void AutoVNStart() {
        if (Preference.getRendomserver()) {
            Utils.setUpCountry();
        }

        ConnectVN();

    }

    private void ConnectVN() {

        if (Preference.getisVpnConnect()) {
            Utils.vpnStart = true;// it will use after restart this activity
            status("connected");
//            startUIUpdateTask();
        } else {
            prepareVpn();
        }
    }

    private void prepareVpn() {
        if (!Utils.vpnStart) {
            Utils.isConnectingToInternet(Splash_Activity.this, new Utils.OnCheckNet() {
                @Override
                public void OnCheckNet(boolean b) {
                    if (b) {

                        Intent intent = VpnService.prepare(Splash_Activity.this);
                        if (intent != null) {
                            startActivityForResult(intent, 1);
                        } else {
                            startVpn();
                        }
                    } else {
                        finishAffinity();
                    }
                }
            });

        }
    }

    private void startVpn() {
        status("connecting");
        connectToVpn();
    }

    public void isLoggedIn(unified.vpn.sdk.Callback<Boolean> callback) {
        UnifiedSdk.getInstance().getBackend().isLoggedIn(callback);

    }

    public void connectToVpn() {
        isLoggedIn(new unified.vpn.sdk.Callback<Boolean>() {
            @Override
            public void success(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    List<String> fallbackOrder = new ArrayList<>();
                    fallbackOrder.add(HydraTransport.TRANSPORT_ID);
                    fallbackOrder.add(OpenVpnTransport.TRANSPORT_ID_TCP);
                    fallbackOrder.add(OpenVpnTransport.TRANSPORT_ID_UDP);
                    List<String> bypassDomains = new LinkedList<>();
                    bypassDomains.add("*facebook.com");
                    bypassDomains.add("*wtfismyip.com");
                    UnifiedSdk.getInstance().getVpn().start(new SessionConfig.Builder()
                            .withReason(TrackingConstants.GprReasons.M_UI)
                            .withTransportFallback(fallbackOrder)
                            .withVirtualLocation(Preference.getServer_short().toLowerCase())
                            .withTransport(HydraTransport.TRANSPORT_ID)
                            .addDnsRule(TrafficRule.Builder.bypass().fromDomains(bypassDomains))
                            .build(), new CompletableCallback() {
                        @Override
                        public void complete() {
                            //  Log.d("MainActivity12", "complete");
                            Utils.vpnStart = true;// it will use after restart this activity
                            status("connected");
//                            startUIUpdateTask();
                        }

                        @Override
                        public void error(@NonNull VpnException e) {
                            //  Log.d("MainActivity12", "error = " + e.getMessage());
                            status("connect");
                            Utils.vpnStart = false;


                            if (e.getMessage().contains("TRAFFIC_EXCEED")) {
                                Set_Limit_size();
                            } else {
                                Vn_PassActivity();


                            }
                        }
                    });
                }
            }

            @Override
            public void failure(@NonNull VpnException e) {
                Vn_PassActivity();
            }
        });
    }

    private void Vn_PassActivity() {

        if (!Preference.getComing_soon()) {
            LoadNativeAds();
        }


        Ads_SplashAppOpen.Splash_OpenAppAds_Load(Splash_Activity.this, new Ads_SplashAppOpen.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                if (Preference.getVPN_Show()) {
                    UnifiedSdk.getVpnState(new unified.vpn.sdk.Callback<VpnState>() {
                        @Override
                        public void success(@NonNull VpnState vpnState) {
                            if (vpnState == VpnState.IDLE) {
                                Utils.vpnStart = false;
                                Preference.setisVpnConnect(false);
                            }
                            IntentActivy();
                            Log.e("MainActivity12", "vpnState = " + vpnState);
                        }

                        @Override
                        public void failure(@NonNull VpnException e) {
                            Utils.vpnStart = false;
                            Preference.setisVpnConnect(false);
                            Log.e("MainActivity12", "failure");
                            IntentActivy();
                        }
                    });
                } else {
                    IntentActivy();
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Utils.isConnectingToInternet(Splash_Activity.this, new Utils.OnCheckNet() {
                @Override
                public void OnCheckNet(boolean b) {
                    if (b) {
                        startVpn();
                    } else {
                        finishAffinity();
                    }
                }
            });

        } else {
            if (!Preference.getComing_soon()) {
                LoadNativeAds();
            }
            Ads_SplashAppOpen.Splash_OpenAppAds_Load(Splash_Activity.this, new Ads_SplashAppOpen.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    Main_Intent_Pass();
                }
            });
//            Toast.makeText(Splash_Screen.this, "Permission Deny !! ", Toast.LENGTH_SHORT).show();
        }
    }

    protected void startUIUpdateTask() {
        mUIHandler.removeCallbacks(mUIUpdateRunnable);
        mUIHandler.post(mUIUpdateRunnable);
    }

    private void checkRemainingTraffic() {
        UnifiedSdk.getInstance().getBackend().remainingTraffic(new unified.vpn.sdk.Callback<RemainingTraffic>() {
            @Override
            public void success(RemainingTraffic remainingTraffic) {
                updateRemainingTraffic(remainingTraffic);
            }

            @Override
            public void failure(VpnException e) {
            }
        });
    }

    protected void updateRemainingTraffic(RemainingTraffic remainingTrafficResponse) {
        Log.d("MainActivity12", "updateRemainingTraffic = " + remainingTrafficResponse);
        Log.d("MainActivity12", "updateRemainingTraffic one = " + remainingTrafficResponse.isUnlimited());

        String trafficUsed = megabyteCount(remainingTrafficResponse.getTrafficUsed());
        String trafficLimit = megabyteCount(remainingTrafficResponse.getTrafficLimit()) + "Mb";
        Log.d("MainActivity12", "updateRemainingTraffic trafficUsed = " + trafficUsed);
        Log.d("MainActivity12", "updateRemainingTraffic trafficLimit = " + trafficLimit);
        if (remainingTrafficResponse.getTrafficLimit() <= remainingTrafficResponse.getTrafficUsed()) {
            mUIHandler.removeCallbacks(mUIUpdateRunnable);
            Set_Limit_size();
        }
    }

    public void status(String status) {
        Log.d("MainActivity12", "status = " + status);
        if (status.equals("connect")) {
            Utils.vpnStart = false;
            Preference.setisVpnConnect(false);
        } else if (status.equals("connecting")) {
            Preference.setisVpnConnect(false);
        } else if (status.equals("connected")) {

            Preference.setisVpnConnect(true);
            Vn_PassActivity();
        }
    }
    /*private void IntentActivy() {

        if (!Preference.getcomingsoon()) {
            Ads.OpenAppAds(Splash_Activity.this);
        }

        if (Preference.getcomingsoon()) {

            if (Preference.getisVpnConnect()) {
                disconnectFromVnp();
            } else {
                startActivity(new Intent(Splash_Activity.this, ComingsoonActivity.class));
                finish();
            }

//            startActivity(new Intent(Splash_Activity.this, ComingsoonActivity.class));
//            finish();
        } else {
            if (Preference.getVPN_Show()) {



               *//* Intent intent = new Intent(Splash_Activity.this, Connect_VPN_Screen.class);
                startActivity(intent);
                finish();*//*
            } else {
                if (Preference.getisVpnConnect()) {
                    disconnectFromVnp();
                } else {
                    if(Preference.getscreenshow() > 4){

                        startActivity(new Intent(Splash_Activity.this, Screen_1.class));
                        finish();
                    }else if(Preference.getscreenshow() == 4){

                        startActivity(new Intent(Splash_Activity.this, Screen_2.class));
                        finish();
                    }else if(Preference.getscreenshow() == 3){

                        startActivity(new Intent(Splash_Activity.this, Screen_3.class));
                        finish();
                    }else if(Preference.getscreenshow() == 2){

                        startActivity(new Intent(Splash_Activity.this, Screen_4.class));
                        finish();
                    }else if(Preference.getscreenshow() == 1){

                        startActivity(new Intent(Splash_Activity.this, Screen_5.class));
                        finish();
                    }else {
                        startActivity(new Intent(Splash_Activity.this, Screen_5.class));
                        finish();
                    }
                }

            }
        }
    }*/

    private void IntentActivy() {

        if (Preference.getComing_soon()) {
            if (Preference.getisVpnConnect()) {
                disconnectFromVnp();
            } else {
                startActivity(new Intent(Splash_Activity.this, ComingsoonActivity.class));
                finish();
            }
        } else {

            if (Preference.getisVpnConnect()) {
                if (!Preference.getVPN_Show()) {
                    disconnectFromVnp();
                } else {
                    Main_Intent_Pass();
                }
            } else {
                if (Preference.getVPN_Show()) {
                    Intent intent = new Intent(Splash_Activity.this, Connect_Network_Screen.class);
                    intent.putExtra("type_connection", "connection");
                    startActivity(intent);
                    finish();
                } else {

                    if (Preference.getisVpnConnect()) {
                        disconnectFromVnp();
                    } else {
                        Main_Intent_Pass();
                    }
                }
            }
        }

    }

    public void disconnectFromVnp() {
        UnifiedSdk.getInstance().getVpn().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
            @Override
            public void complete() {
                Utils.vpnStart = false;
                Preference.setisVpnConnect(false);

                Main_Intent_Pass();

            }

            @Override
            public void error(@NonNull VpnException e) {
                Utils.vpnStart = false;
                Preference.setisVpnConnect(false);
                Main_Intent_Pass();
            }
        });
//        UnifiedSDK.getInstance().getVPN().stop(TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
//            @Override
//            public void complete() {
//                Utils.vpnStart = false;
//                Preference.setisVpnConnect(false);
//
//
//                if (Preference.getComing_soon()) {
//                    startActivity(new Intent(Splash_Activity.this, ComingsoonActivity.class));
//                    finish();
//                } else {
//                    if (Preference.getScreen_show() > 3) {
//                        fromstart = 3;
//                        startActivity(new Intent(Splash_Activity.this, LetsgoActivity.class));
//                        finish();
//                    } else if (Preference.getScreen_show() == 3) {
//                        fromstart = 3;
//                        startActivity(new Intent(Splash_Activity.this, LetsgoActivity.class));
//                        finish();
//                    } else if (Preference.getScreen_show() == 2) {
//                        fromstart = 3;
//                        startActivity(new Intent(Splash_Activity.this, NextActivity.class));
//                        finish();
//                    } else if (Preference.getScreen_show() == 1) {
//                        fromstart = 4;
//                        startActivity(new Intent(Splash_Activity.this, StartActivity.class));
//                        finish();
//                    } else {
//                        fromstart = 5;
//                        startActivity(new Intent(Splash_Activity.this, StartActivity.class));
//                        finish();
//                    }
//
//                }
//
//
//            }
//
//            @Override
//            public void error(@NonNull VpnException e) {
//                //Toast.makeText(Pro_MainActivity.this, "Disconnect error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    private void Main_Intent_Pass() {
        fromstart = 1;
        startActivity(new Intent(Splash_Activity.this, FirstActivity.class));
        finish();
    }

    private void GetWaringDialog() {

        Waringdialog = new Dialog(Splash_Activity.this);
        Waringdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Waringdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Waringdialog.setContentView(R.layout.dialog_undermaintenanace);
        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        final int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        Waringdialog.getWindow().setLayout(width, height);
        Waringdialog.setCancelable(false);
        Waringdialog.show();

        TextView tv_maintenance_msg = Waringdialog.findViewById(R.id.tv_maintenance_msg);
        Button btn_ok = Waringdialog.findViewById(R.id.btn_ok);
        Button btn_cancel = Waringdialog.findViewById(R.id.btn_cancel);


        tv_maintenance_msg.setText("Please disconnect other VPN, after use our APP!");
        btn_ok.setText("Setting");
        btn_cancel.setVisibility(View.VISIBLE);
        btn_cancel.setText("Ok");
        btn_ok.setVisibility(View.GONE);


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Waringdialog.dismiss();
                Intent intent = new Intent("android.net.vpn.SETTINGS");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 10);

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Waringdialog.dismiss();
                finishAffinity();
            }
        });

    }


}