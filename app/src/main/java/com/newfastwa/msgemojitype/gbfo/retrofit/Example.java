package com.newfastwa.msgemojitype.gbfo.retrofit;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {

    @SerializedName("package")
    @Expose
    private Package _package;

    @SerializedName("countrylist")
    @Expose
    public List<CountryList> countryList = null;

    @SerializedName("privacypolicy")
    @Expose
    public String privacypolicy;
    @SerializedName("privacypolicytwo")
    @Expose
    public String privacypolicytwo;


    @SerializedName("vn_show")
    public boolean vn_show;

    public Package getPackage() {
        return _package;
    }

    public void setPackage(Package _package) {
        this._package = _package;
    }

    public class Package {
        @SerializedName("ads_click")
        @Expose
        public Integer adsClick;
        @SerializedName("maintenance")
        @Expose
        public boolean maintenance;
        @SerializedName("back_click")
        @Expose
        public Integer backClick;

        @SerializedName("version_name")
        @Expose
        public String versionName;

        @SerializedName("version_code")
        @Expose
        public String versionCode;
        @SerializedName("app_msg")
        @Expose
        public String appMsg;
        @SerializedName("update_url")
        @Expose
        public String updateUrl;
        @SerializedName("is_update")
        @Expose
        public String isUpdate;
        @SerializedName("ads_type")
        @Expose
        public String ads_type;
        @SerializedName("link")
        @Expose
        public String link;
        @SerializedName("privacy_policy")
        @Expose
        public String privacy_policy;
        @SerializedName("admob_native_id_list")
        @Expose
        public List<String> admob_native_list = null;
        @SerializedName("admob_bottom_native_id_list")
        @Expose
        public List<String> admob_native_banner_list = null;
        @SerializedName("admob_interstitial_id_list")
        @Expose
        public List<String> admob_interstitial_list = null;
        @SerializedName("app_open_id_list")
        @Expose
        public List<String> admob_app_open_list = null;
        @SerializedName("admob_banner_id_list")
        @Expose
        public List<String> admob_banner_list = null;

        @SerializedName("random_server")
        @Expose
        public boolean random_server;

        @SerializedName("country_name")
        @Expose
        public String country_name;

        @SerializedName("country_code")
        @Expose
        public String country_code;

        @SerializedName("images_url")
        @Expose
        public String images_url;

        @SerializedName("app_open_interstitial_click")
        @Expose
        public Integer app_open_interstitial_click;

        @SerializedName("app_open_interstitial_show")
        @Expose
        public boolean app_open_interstitial_show;
        @SerializedName("vn_id")
        @Expose
        public String vn_id;
        @SerializedName("vn_pass")
        @Expose
        public String vn_pass;

        @SerializedName("fb_native_btn_color")
        @Expose
        public String fb_native_btn_color;
        @SerializedName("fb_native_btn_text_color")
        @Expose
        public String fb_native_btn_text_color;
        @SerializedName("fb_native_content_text_color")
        @Expose
        public String fb_native_content_text_color;
        @SerializedName("fb_native_bg_color")
        @Expose
        public String fb_native_bg_color;

        @SerializedName("fb_native_banner_id")
        @Expose
        public String fb_native_banner_id;

        @SerializedName("fb_native_id")
        @Expose
        public String fb_native_id;

        @SerializedName("fb_interstitial_id")
        @Expose
        public String fb_interstitial_id;
        @SerializedName("bottom_ads_type")
        @Expose
        public String bottom_ads_type;

        @SerializedName("is_quiz")
        @Expose
        public Boolean is_quiz;
        @SerializedName("quiz_header_show")
        @Expose
        public Boolean quiz_header_show;

        @SerializedName("admob_native_btn_color")
        @Expose
        public String admob_native_btn_color;
        @SerializedName("admob_native_btn_text_color")
        @Expose
        public String admob_native_btn_text_color;
        @SerializedName("admob_native_content_text_color")
        @Expose
        public String admob_native_content_text_color;

        @SerializedName("admob_native_bg_color")
        @Expose
        public String admob_native_bg_color;
        @SerializedName("ads_button_animation_type")
        @Expose
        public String ads_button_animation_type;

        @SerializedName("ads_buttom_animation")
        @Expose
        public Boolean ads_buttom_animation;
        @SerializedName("is_big_native_quiz")
        @Expose
        public Boolean is_big_native_quiz;

        @SerializedName("is_small_native_quiz")
        @Expose
        public Boolean is_small_native_quiz;
        @SerializedName("coming_soon")
        @Expose
        public Boolean coming_soon;
        @SerializedName("vn_direct_connect")
        @Expose
        public Boolean vn_direct_connect;

        @SerializedName("vn_header_show")
        @Expose
        public Boolean vn_header_show;
        @SerializedName("admob_page")
        @Expose
        public Boolean admob_page;
        @SerializedName("ads_open_admob_click")
        @Expose
        public Integer ads_open_admob_click;
        @SerializedName("inter_back_admob_click")
        @Expose
        public Integer inter_back_admob_click;
        @SerializedName("inter_admob_click")
        @Expose
        public Integer inter_admob_click;
        @SerializedName("native_admob_click")
        @Expose
        public Integer native_admob_click;
        @SerializedName("banner_admob_click")
        @Expose
        public Integer banner_admob_click;
        @SerializedName("native_by_page")
        @Expose
        public Integer native_by_page;
        @SerializedName("screen_show")
        @Expose
        public Integer screen_show;
        @SerializedName("app_open_back_interstitial_click")
        @Expose
        public Integer app_open_back_interstitial_click;

        @SerializedName("firebaseanalytics")
        @Expose
        public Boolean firebase_analytics;
        @SerializedName("social_downloader")
        @Expose
        public Boolean social_downloader;

        @SerializedName("video_player")
        @Expose
        public Boolean video_player;
        @SerializedName("is_live")
        @Expose
        public Boolean is_live;

        @SerializedName("videocall_show")
        @Expose
        public Boolean videocall_show;

        @SerializedName("update_coming_soon")
        @Expose
        public boolean update_coming_soon;

        @SerializedName("ad_one_by_one_ids")
        @Expose
        public Boolean ad_one_by_one_ids;

        @SerializedName("url_type")
        @Expose
        public Boolean url_type;
        @SerializedName("url_default")
        @Expose
        public String url_default;
        @SerializedName("url_arrays")
        @Expose
        private List<String> urlArrays = null;
        @SerializedName("button_animation_native")
        @Expose
        public Boolean button_animation_native;

        @SerializedName("native_btn_type")
        @Expose
        public String native_btn_type;




        public List<String> getUrlArrays() {
            return urlArrays;
        }

        public void setUrlArrays(List<String> urlArrays) {
            this.urlArrays = urlArrays;
        }




        public Boolean getAd_one_by_one_ids() {
            return ad_one_by_one_ids;
        }

        public void setAd_one_by_one_ids(Boolean ad_one_by_one_ids) {
            this.ad_one_by_one_ids = ad_one_by_one_ids;
        }


        public List<String> getAdmob_banner_list() {
            return admob_banner_list;
        }

        public void setAdmob_banner_list(List<String> admob_banner_list) {
            this.admob_banner_list = admob_banner_list;
        }


        public List<String> getAdmob_app_open_list() {
            return admob_app_open_list;
        }

        public void setAdmob_app_open_list(List<String> admob_app_open_list) {
            this.admob_app_open_list = admob_app_open_list;
        }

        public List<String> getAdmob_interstitial_list() {
            return admob_interstitial_list;
        }

        public void setAdmob_interstitial_list(List<String> admobInterstitialList) {
            this.admob_interstitial_list = admobInterstitialList;
        }

        public List<String> getAdmob_native_list() {
            return admob_native_list;
        }

        public void setAdmob_native_list(List<String> admobnativeID) {
            this.admob_native_list = admobnativeID;
        }

        public List<String> getAdmob_native_banner_list() {
            return admob_native_banner_list;
        }

        public void setAdmob_native_banner_list(List<String> admobnativeID) {
            this.admob_native_banner_list = admobnativeID;
        }




        public Integer getAdsClick() {
            return adsClick;
        }

        public void setAdsClick(Integer adsClick) {
            this.adsClick = adsClick;
        }

        public Boolean getMaintenance() {
            return maintenance;
        }

        public void setMaintenance(Boolean maintenance) {
            this.maintenance = maintenance;
        }

        public Integer getBackClick() {
            return backClick;
        }

        public void setBackClick(Integer backClick) {
            this.backClick = backClick;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getAppMsg() {
            return appMsg;
        }

        public void setAppMsg(String appMsg) {
            this.appMsg = appMsg;
        }

        public String getUpdateUrl() {
            return updateUrl;
        }

        public void setUpdateUrl(String updateUrl) {
            this.updateUrl = updateUrl;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }

    public class CountryList {
        @SerializedName("name")
        public String name;

        @SerializedName("countrycode")
        public String code;

        @SerializedName("image")
        public String cuntryimages;
    }
}

