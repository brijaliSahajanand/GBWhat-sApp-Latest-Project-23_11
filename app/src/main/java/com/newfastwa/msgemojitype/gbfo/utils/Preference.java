package com.newfastwa.msgemojitype.gbfo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.newfastwa.msgemojitype.gbfo.Appcontroller;
import com.newfastwa.msgemojitype.gbfo.retrofit.Example;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class Preference {

    private static final String ads_click = "ads_click";
    private static final String back_click = "back_click";

    private static final String ads_type = "ads_type";
    private static final String link = "link";
    private static final String privacy_policy = "privacy_policy";
    private static final String videocall_show = "videocall_show";
    private static final String howtoshow = "howtoshow";
    private static final String percentage = "percentage";
    private static final String aura_user_id = "aura_user_id";
    private static final String accessToken = "accessToken";
    private static final String server_short = "server_short";
    private static final String server_name = "server_name";
    private static final String server_image = "server_image";
    private static final String insta_login = "insta_login";
    private static final String rendomserver = "rendomserver";
    private static final String privacy_policy_html = "privacy_policy_html";
    private static final String Whatsapp_URI = "Whatsapp_URI";
    private static final String StateNameVPN = "StateNameVPN";
    private static final String CountryNmeVPN = "CountryNmeVPN";
    private static final String CityNmeVPN = "CityNmeVPN";
    private static final String VPN_Show = "VPN_Show";
    private static final String appOpen_click = "appOpen_click";
    private static final String appOpen_inter_show = "appOpen_inter_show";
    private static final String isVpnConnect = "isVpnConnect";
    private static final String isLive = "isLive";
    private static final String native_button_color = "native_button_color";
    private static final String native_btn_text_color = "native_btn_text_color";
    private static final String vnid = "vnid";
    private static final String vnpassword = "vnpassword";
    private static final String native_by_page = "native_by_page";
    private static final String admob_page = "admob_page";
    private static final String native_admob = "native_admob";
    private static final String banner_admob = "banner_qureka";
    private static final String ads_open_admob = "ads_open_admob";
    private static final String inter_back_admob = "inter_back_admob";
    private static final String inter_admob = "inter_admob";
    private static final String buttonanimate = "buttonanimate";
    private static final String animationtype = "animationtype";
    private static final String is_big_native_qureka = "is_big_native_qureka";
    private static final String fb_native_id = "fb_native_id";
    private static final String social_downloader = "social_downloader";
    private static final String video_player = "video_player";
    private static final String fb_native_banner_id = "fb_native_banner_id";
    private static final String fb_interstitial_id = "fb_interstitial_id";
    private static final String is_quiz = "is_quiz";
    private static final String quiz_header_show = "quiz_header_show";
    private static final String is_small_native_qureka = "is_small_native_qureka";
    private static final String bottom_ads_type = "bottom_ads_type";
    private static final String vn_direct_connect = "vn_direct_connect";
    private static final String vn_header_show = "vn_header_show";
    private static final String app_open_back_interstitial_click = "app_open_back_interstitial_click";
    private static final String qureka_link = "qureka_link";
    private static final String fb_native_btn_color = "fb_native_btn_color";
    private static final String fb_native_btn_text_color = "fb_native_btn_text_color";
    private static final String fb_native_content_text_color = "fb_native_content_text_color";
    private static final String fb_native_bg_color = "fb_native_bg_color";
    private static final String admob_native_content_text_color = "admob_native_content_text_color";
    private static final String admob_native_bg_color = "admob_native_bg_color";
    private static final String coming_soon = "coming_soon";
    private static final String screen_show = "screen_show";
    private static final String ad_one_by_one_ids = "ad_one_by_one_ids";
    private static final String cookie = "cookie";
    private static final String gender_color = "gender_color";
    private static final String url_type = "url_type";
    private static final String url_default = "url_default";
    private static final String button_animation_native = "button_animation_native";
    private static final String native_btn_type = "native_btn_type";

    public static String getNative_btn_type() {
        return get().getString(native_btn_type, "small");
    }

    public static void setNative_btn_type(String value) {
        get().edit().putString(native_btn_type, value).apply();
    }


    public static Boolean getButton_animation_native() {
        return get().getBoolean(button_animation_native, true);
    }

    public static void setButton_animation_native(Boolean value) {
        get().edit().putBoolean(button_animation_native, value).apply();
    }


    public static void setUrl_type(boolean value) {
        get().edit().putBoolean(url_type, value).apply();
    }

    public static boolean getUrl_type() {
        return get().getBoolean(url_type, false);
    }


    public static void setUrl_default(String value) {
        get().edit().putString(url_default, value).apply();
    }

    public static String getUrl_default() {
        return get().getString(url_default, "");
    }



    public static void setgender_color(String value) {
        get().edit().putString(gender_color, value).apply();
    }

    public static String getgender_color() {
        return get().getString(gender_color, "#000000");
    }

    public static void setcookie(String value) {
        get().edit().putString(cookie, value).apply();
    }

    public static String getcookie() {
        return get().getString(cookie, "");
    }


    public static void setScreen_show(Integer value) {
        get().edit().putInt(screen_show, value).apply();
    }

    public static Integer getScreen_show() {
        return get().getInt(screen_show, 2);
    }

    public static void setAd_one_by_one_ids(boolean value) {
        get().edit().putBoolean(ad_one_by_one_ids, value).apply();
    }

    public static boolean getAd_one_by_one_ids() {
        return get().getBoolean(ad_one_by_one_ids, false);
    }



    public static void setComing_soon(boolean value) {
        get().edit().putBoolean(coming_soon, value).apply();
    }

    public static boolean getComing_soon() {
        return get().getBoolean(coming_soon, false);
    }

    public static String getPrivacy_policy() {
        return get().getString(privacy_policy, "");
    }

    public static void setPrivacy_policy(String value) {
        get().edit().putString(privacy_policy, value).apply();
    }


    public static void setadmob_native_content_text_color(String value) {
        get().edit().putString(admob_native_content_text_color, value).apply();
    }

    public static String getadmob_native_content_text_color() {
        return get().getString(admob_native_content_text_color, "");
    }

    public static void setadmob_native_bg_color(String value) {
        get().edit().putString(admob_native_bg_color, value).apply();
    }

    public static String getadmob_native_bg_color() {
        return get().getString(admob_native_bg_color, "");
    }

    public static void setfb_native_btn_color(String value) {
        get().edit().putString(fb_native_btn_color, value).apply();
    }

    public static String getfb_native_btn_color() {
        return get().getString(fb_native_btn_color, "");
    }

    public static void setfb_native_btn_text_color(String value) {
        get().edit().putString(fb_native_btn_text_color, value).apply();
    }

    public static String getfb_native_btn_text_color() {
        return get().getString(fb_native_btn_text_color, "");
    }

    public static void setfb_native_content_text_color(String value) {
        get().edit().putString(fb_native_content_text_color, value).apply();
    }

    public static String getfb_native_content_text_color() {
        return get().getString(fb_native_content_text_color, "");
    }

    public static void setfb_native_bg_color(String value) {
        get().edit().putString(fb_native_bg_color, value).apply();
    }

    public static String getfb_native_bg_color() {
        return get().getString(fb_native_bg_color, "");
    }

    public static String getQureka_link() {
        return get().getString(qureka_link, "");
    }

    public static void setQureka_link(String value) {
        get().edit().putString(qureka_link, value).apply();
    }

    public static Integer getApp_open_back_interstitial_click() {
        return get().getInt(app_open_back_interstitial_click, 2);
    }

    public static void setApp_open_back_interstitial_click(Integer value) {
        get().edit().putInt(app_open_back_interstitial_click, value).apply();
    }

    public static void setVn_header_show(boolean value) {
        get().edit().putBoolean(vn_header_show, value).apply();
    }

    public static boolean getVn_header_show() {
        return get().getBoolean(vn_header_show, false);
    }


    public static void setVn_direct_connect(boolean value) {
        get().edit().putBoolean(vn_direct_connect, value).apply();
    }

    public static boolean getVn_direct_connect() {
        return get().getBoolean(vn_direct_connect, false);
    }

    public static String getVnid() {
        return get().getString(vnid, "59161_myvpn");
    }

    public static void setVnid(String value) {
        get().edit().putString(vnid, value).apply();
    }

    public static String getVnpassword() {
        return get().getString(vnpassword, "ZEziW6v0O3");
    }

    public static void setVnpassword(String value) {
        get().edit().putString(vnpassword, value).apply();
    }

    public static Integer getNative_by_page() {
        return get().getInt(native_by_page, 3);
    }

    public static void setNative_by_page(Integer value) {
        get().edit().putInt(native_by_page, value).apply();
    }

    public static int get_AdsClick() {
        return get().getInt(ads_click, 0);
    }

    public static void set_AdsClick(int value) {
        get().edit().putInt(ads_click, value).apply();
    }

    public static int getBack_click() {
        return get().getInt(back_click, 0);
    }

    public static void setBack_click(int value) {
        get().edit().putInt(back_click, value).apply();
    }

    public static void setads_type(String value) {
        get().edit().putString(ads_type, value).apply();
    }

    public static String getads_type() {
        return get().getString(ads_type, "");
    }


    public static void setBottom_ads_type(String value) {
        get().edit().putString(bottom_ads_type, value).apply();
    }

    public static String getBottom_ads_type() {
        return get().getString(bottom_ads_type, "");
    }


    public static void setIs_big_native_qureka(boolean value) {
        get().edit().putBoolean(is_big_native_qureka, value).apply();
    }

    public static boolean getIs_big_native_qureka() {
        return get().getBoolean(is_big_native_qureka, false);
    }


    public static void setIs_small_native_qureka(boolean value) {
        get().edit().putBoolean(is_small_native_qureka, value).apply();
    }

    public static boolean getIs_small_native_qureka() {
        return get().getBoolean(is_small_native_qureka, false);
    }

    public static void setQuiz_header_show(boolean value) {
        get().edit().putBoolean(quiz_header_show, value).apply();
    }

    public static boolean getQuiz_header_show() {
        return get().getBoolean(quiz_header_show, false);
    }

    public static void setIs_quiz(boolean value) {
        get().edit().putBoolean(is_quiz, value).apply();
    }

    public static boolean getIs_quiz() {
        return get().getBoolean(is_quiz, false);
    }

    public static void setFb_interstitial_id(String value) {
        get().edit().putString(fb_interstitial_id, value).apply();
    }

    public static String getFb_interstitial_id() {
        return get().getString(fb_interstitial_id, "");
    }


    public static void setFb_native_banner_id(String value) {
        get().edit().putString(fb_native_banner_id, value).apply();
    }

    public static String getFb_native_banner_id() {
        return get().getString(fb_native_banner_id, "");
    }


    public static void setbuttonanimate(boolean value) {
        get().edit().putBoolean(buttonanimate, value).apply();
    }

    public static boolean getbuttonanimate() {
        return get().getBoolean(buttonanimate, false);
    }

    public static void setanimationtype(String value) {
        get().edit().putString(animationtype, value).apply();
    }

    public static String getanimationtype() {
        return get().getString(animationtype, "alpha");
    }


    private static SharedPreferences get() {
        return Appcontroller.getApp().getSharedPreferences(
                "GBwhatsapp", Context.MODE_PRIVATE);
    }

    public static void setAds_open_admob(Integer value) {
        get().edit().putInt(ads_open_admob, value).apply();
    }

    public static Integer getAds_open_admob() {
        return get().getInt(ads_open_admob, 3);
    }


    public static void setInter_back_admob(Integer value) {
        get().edit().putInt(inter_back_admob, value).apply();
    }

    public static Integer getInter_back_admob() {
        return get().getInt(inter_back_admob, 3);
    }

    public static void setinter_admob(Integer value) {
        get().edit().putInt(inter_admob, value).apply();
    }

    public static Integer getinter_admob() {
        return get().getInt(inter_admob, 3);
    }


    public static void setbanner_admob(Integer value) {
        get().edit().putInt(banner_admob, value).apply();
    }

    public static Integer getbanner_admob() {
        return get().getInt(banner_admob, 3);
    }

    public static void setnative_admob(Integer value) {
        get().edit().putInt(native_admob, value).apply();
    }

    public static Integer getnative_admob() {
        return get().getInt(native_admob, 3);
    }


    public static void setAdmob_page(boolean value) {
        get().edit().putBoolean(admob_page, value).apply();
    }

    public static boolean getAdmob_page() {
        return get().getBoolean(admob_page, false);
    }

    public static void setAppOpen_click(int value) {
        get().edit().putInt(appOpen_click, value).apply();
    }

    public static int getAppOpen_click() {
        return get().getInt(appOpen_click, 0);
    }


    public static void setAppOpen_inter_show(boolean value) {
        get().edit().putBoolean(appOpen_inter_show, value).apply();
    }

    public static boolean getAppOpen_inter_show() {
        return get().getBoolean(appOpen_inter_show, false);
    }

    public static int getpercentage() {
        return get().getInt(percentage, 0);
    }

    public static void setpercentage(int token) {
        get().edit().putInt(percentage, token).apply();
    }

    public static boolean gethowtoshow() {
        return get().getBoolean(howtoshow, true);
    }

    public static void sethowtoshow(boolean token) {
        get().edit().putBoolean(howtoshow, token).apply();
    }


    public static boolean getvideocall_show() {
        return get().getBoolean(videocall_show, false);
    }

    public static void setvideocall_show(boolean token) {
        get().edit().putBoolean(videocall_show, token).apply();
    }


    public static String getprivacy_policy() {
        return get().getString(privacy_policy, "");
    }

    public static void setlink(String value) {
        get().edit().putString(link, value).apply();
    }

    public static String getlink() {
        return get().getString(link, "");
    }


    public static void setAura_user_id(long value) {
        get().edit().putLong(aura_user_id, value).apply();
    }

    public static long getAura_user_id() {
        return get().getLong(aura_user_id, 0);
    }

    public static void setAccessToken(String value) {
        get().edit().putString(accessToken, value).apply();
    }

    public static String getAccessToken() {
        return get().getString(accessToken, "");
    }


    public static void set_server_short(String value) {
        get().edit().putString(server_short, value).apply();
    }

    public static String getServer_short() {
        return get().getString(server_short, "US");
    }


    public static void setserver_name(String value) {
        get().edit().putString(server_name, value).apply();
    }

    public static String getserver_name() {
        return get().getString(server_name, "");
    }

    public static void setServer_image(String value) {
        get().edit().putString(server_image, value).apply();
    }

    public static String getServer_image() {
        return get().getString(server_image, "");
    }


    public static boolean getInsta_login() {
        return get().getBoolean(insta_login, false);
    }

    public static void setInsta_login(boolean token) {
        get().edit().putBoolean(insta_login, token).apply();
    }


    public static void setRendomserver(boolean value) {
        get().edit().putBoolean(rendomserver, value).apply();
    }

    public static boolean getRendomserver() {
        return get().getBoolean(rendomserver, false);
    }


    public static void setPrivacy_policy_html(String value) {
        get().edit().putString(privacy_policy_html, value).apply();
    }

    public static String getPrivacy_policy_html() {
        return get().getString(privacy_policy_html, "");
    }


    public static void setWhatsapp_URI(String value) {
        get().edit().putString(Whatsapp_URI, value).apply();
    }

    public static String getWhatsapp_URI() {
        return get().getString(Whatsapp_URI, "");
    }

    public static void setStateNameVPN(String value) {
        get().edit().putString(StateNameVPN, value).apply();
    }

    public static String getStateNameVPN() {
        return get().getString(StateNameVPN, "");
    }

    public static void setCountryNmeVPN(String value) {
        get().edit().putString(CountryNmeVPN, value).apply();
    }

    public static String getCountryNmeVPN() {
        return get().getString(CountryNmeVPN, "");
    }

    public static void setCityNmeVPN(String value) {
        get().edit().putString(CityNmeVPN, value).apply();
    }

    public static String getCityNmeVPN() {
        return get().getString(CityNmeVPN, "");
    }

    public static void setnative_button_color(String value) {
        get().edit().putString(native_button_color, value).apply();
    }

    public static String getnative_button_color() {
        return get().getString(native_button_color, "#b600de");
    }

    public static void setnative_btn_text_color(String value) {
        get().edit().putString(native_btn_text_color, value).apply();
    }

    public static String getnative_btn_text_color() {
        return get().getString(native_btn_text_color, "#ffffff");
    }

    public static void setVPN_Show(boolean value) {
        get().edit().putBoolean(VPN_Show, value).apply();
    }

    public static boolean getVPN_Show() {
        return get().getBoolean(VPN_Show, true);
    }

    public static void setisVpnConnect(boolean value) {
        get().edit().putBoolean(isVpnConnect, value).apply();
    }

    public static boolean getisVpnConnect() {
        return get().getBoolean(isVpnConnect, false);
    }

    public static void setisLive(boolean value) {
        get().edit().putBoolean(isLive, value).apply();
    }

    public static boolean getisLive() {
        return get().getBoolean(isLive, false);
    }


    public static void setFb_native_id(String value) {
        get().edit().putString(fb_native_id, value).apply();
    }

    public static String getFb_native_id() {
        return get().getString(fb_native_id, "");
    }


    public static void setSocial_downloader(boolean value) {
        get().edit().putBoolean(social_downloader, value).apply();
    }

    public static boolean getSocial_downloader() {
        return get().getBoolean(social_downloader, false);
    }


    public static void setVideo_player(boolean value) {
        get().edit().putBoolean(video_player, value).apply();
    }

    public static boolean getVideo_player() {
        return get().getBoolean(video_player, false);
    }

    private static final String user_img_uri = "user_img_uri";
    private static final String user_name = "user_name";


    private static final String country_list = "country_list";

    public static List<Example.CountryList> getCountry_list() {
        Gson gson = new Gson();
        String json = get().getString(country_list, null);
        Type type = new TypeToken<ArrayList<Example.CountryList>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void setCountry_list(List<Example.CountryList> playlistArray) {
        Gson gson = new Gson();
        String hashMapString = gson.toJson(playlistArray);
        get().edit().putString(country_list, hashMapString).apply();

    }

    public static void setuser_img_uri(String value) {
        get().edit().putString(user_img_uri, value).apply();
    }

    public static String getuser_img_uri() {
        return get().getString(user_img_uri, "");
    }

    public static void setuser_name(String value) {
        get().edit().putString(user_name, value).apply();
    }

    public static String getuser_name() {
        return get().getString(user_name, "");
    }


}
