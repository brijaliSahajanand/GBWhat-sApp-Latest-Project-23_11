package com.newfastwa.msgemojitype.gbfo.retrofit;


import com.newfastwa.msgemojitype.gbfo.utils.Preference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

//import okhttp3.OkHttpClient;
//import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public  class APIClient {

    private static Retrofit retrofit = null;
    public static String img_url = "";

   public static Retrofit getClient() {
       img_url = stringFromJNI();
/*
       OkHttpClient client = new OkHttpClient.Builder()
               .readTimeout(7000, TimeUnit.SECONDS)
               .connectTimeout(7000, TimeUnit.SECONDS).build();

*/

       Gson gson = new GsonBuilder()
               .setLenient()
               .create();
       retrofit = new Retrofit.Builder()
               .baseUrl(img_url)
               .addConverterFactory(GsonConverterFactory.create(gson))
               //.client(client)
               .build();
       return retrofit;

    }

    static {
        System.loadLibrary("native-lib");
    }


    public static native String stringFromJNI();
}