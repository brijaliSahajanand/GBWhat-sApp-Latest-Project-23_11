package com.newfastwa.msgemojitype.gbfo.VPN;

import android.content.Intent;
import android.content.res.Resources;
import android.net.VpnService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ads.adsdemosp.AdsClass.Ads_Adapter_List;
import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.ads.adsdemosp.AdsClass.Ads_VN_Screen;
import com.bumptech.glide.Glide;
import com.newfastwa.msgemojitype.gbfo.Appcontroller;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.retrofit.APIClient;
import com.newfastwa.msgemojitype.gbfo.retrofit.APIInterface;
import com.newfastwa.msgemojitype.gbfo.retrofit.TraficLimitResponse;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;
import com.newfastwa.msgemojitype.gbfo.utils.Utils;
import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import unified.vpn.sdk.Callback;
import unified.vpn.sdk.CompletableCallback;
import unified.vpn.sdk.HydraTransport;
import unified.vpn.sdk.RemainingTraffic;
import unified.vpn.sdk.SessionConfig;
import unified.vpn.sdk.TrackingConstants;
import unified.vpn.sdk.TrafficRule;
import unified.vpn.sdk.UnifiedSdk;
import unified.vpn.sdk.VpnException;

public class Connect_Network_Screen extends com.newfastwa.msgemojitype.gbfo.BaseActivity {

    LinearLayout lnr_connecter, ll_status;
    TextView txt_status, tv_country, tv_country_2;
    MKLoader mkloaderReg;
    ImageView img_off_on, iv_flag;
    RelativeLayout rl_header;
    ScrollView rlt_disconnect_layout;
    RelativeLayout rlt_connect_layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_connect__v_p_n__screen );

        if (Preference.getRendomserver()) {
            if (Utils.country_List != null && Utils.country_List.size() != 0) {
                int pos = new Random().nextInt( Utils.country_List.size() );
                String selectedCountryCode = Utils.country_List.get( pos ).code;
                String selectedCountryName = Utils.country_List.get( pos ).name;
                String selectedCountryImage = Utils.country_List.get( pos ).cuntryimages;
                Log.d( "selectedCountry", "pos = " + pos );
                Log.d( "selectedCountry", "selectedCountry = " + selectedCountryCode );
                Log.d( "selectedCountry", "selectedCountryName = " + selectedCountryName );
                Preference.set_server_short( selectedCountryCode );
                Preference.setserver_name( selectedCountryName );
                Preference.setServer_image( selectedCountryImage );
            } else {
                startActivity( new Intent( Connect_Network_Screen.this, Privacy_Policy_Screen.class ) );
                finish();
            }
        }

        rlt_disconnect_layout = findViewById( R.id.rlt_disconnect_layout );
        rlt_connect_layout = findViewById( R.id.rlt_connect_layout );

        lnr_connecter = findViewById( R.id.lnr_connecter );
        txt_status = findViewById( R.id.txt_status );
        mkloaderReg = findViewById( R.id.mkloaderReg );
        img_off_on = findViewById( R.id.img_off_on );
        iv_flag = findViewById( R.id.iv_flag );
        tv_country = findViewById( R.id.tv_country );
        tv_country_2 = findViewById( R.id.tv_country_2 );
        ll_status = findViewById( R.id.ll_status );
        rl_header = findViewById( R.id.rl_header );


        LinearLayout llnative = findViewById( R.id.llnative );
        LinearLayout lnr_ads_show = findViewById( R.id.llline );

        if (Ads_Adapter_List.admob_nativehashmap != null) {
            Ads_Adapter_List.admob_nativehashmap.clear();
        }

        String type_connection = getIntent().getStringExtra( "type_connection" );
        if (Preference.getVn_direct_connect()) {

            if (type_connection.equals( "connection" )) {

                rlt_connect_layout.setVisibility( View.VISIBLE );
                rlt_disconnect_layout.setVisibility( View.GONE );

                if (isConnecting()) {
                    return;
                }
                if (txt_status.getText().toString().equals( getResources().getString( R.string.switch_off ) )) {
                    disconnectFromVnp( false );
                } else if (txt_status.getText().toString().equals( getResources().getString( R.string.switch_on ) )) {
                    prepareVpn();
                }

            } else {

                Ads_Adapter_List.NativeFull_Show( this, llnative, lnr_ads_show, 0 );

                rlt_connect_layout.setVisibility( View.GONE );
                rlt_disconnect_layout.setVisibility( View.VISIBLE );
            }
        } else {
            rlt_connect_layout.setVisibility( View.GONE );
            rlt_disconnect_layout.setVisibility( View.VISIBLE );

            Ads_Adapter_List.NativeFull_Show( this, llnative, lnr_ads_show, 0 );
        }


        Glide.with( Connect_Network_Screen.this ).load( APIClient.img_url + Preference.getServer_image() ).error( R.drawable.vpn_place ).placeholder( R.drawable.vpn_place ).into( iv_flag );
        tv_country.setText( Preference.getserver_name() );
        tv_country_2.setText( Preference.getserver_name() );

        lnr_connecter.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnecting()) {
                    return;
                }
                if (txt_status.getText().toString().equals( getResources().getString( R.string.switch_off ) )) {
                    disconnectFromVnp( false );
                } else if (txt_status.getText().toString().equals( getResources().getString( R.string.switch_on ) )) {
                    prepareVpn();

                }

            }
        } );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (resultCode == RESULT_OK) {
            Utils.isConnectingToInternet( Connect_Network_Screen.this, new Utils.OnCheckNet() {
                @Override
                public void OnCheckNet(boolean b) {
                    if (b) {
                        startVpn();
                    } else {
                        finishAffinity();
                    }
                }
            } );


        } else {
            Toast.makeText( Connect_Network_Screen.this, "Permission Deny !! ", Toast.LENGTH_SHORT ).show();
        }
    }

    private boolean isConnecting() {
        boolean isConnecting = false;

        if (txt_status.getText().toString().equals( Connect_Network_Screen.this.getResources().getString( R.string.switch_off ) ) || txt_status.getText().toString().equals( Connect_Network_Screen.this.getResources().getString( R.string.switch_on ) )) {
            isConnecting = false;
        } else {
            isConnecting = true;
            Toast.makeText( this, "Server connecting...", Toast.LENGTH_SHORT ).show();
        }


        return isConnecting;
    }

    private void prepareVpn() {
        if (!Utils.vpnStart) {
            Utils.isConnectingToInternet( Connect_Network_Screen.this, new Utils.OnCheckNet() {
                @Override
                public void OnCheckNet(boolean b) {
                    if (b) {

                        Intent intent = VpnService.prepare( Connect_Network_Screen.this );

                        if (intent != null) {
                            startActivityForResult( intent, 1 );
                        } else {
                            startVpn();

                        }

                    } else {
                        finishAffinity();
                    }
                }
            } );

        }
    }

    private void startVpn() {
        status( "connecting" );
        connectToVpn();
    }

    public void status(String status) {
        Log.d( "MainActivity12", "status = " + status );
        if (status.equals( "connect" )) {
            Utils.vpnStart = false;
            Preference.setisVpnConnect( false );
            txt_status.setText( getResources().getString( R.string.switch_on ) );
            mkloaderReg.setVisibility( View.GONE );

            img_off_on.setImageResource( R.drawable.ic_disconnect );
//            ll_status.setBackgroundResource(R.drawable.bg_button_round);
//            rl_header.setBackgroundResource(R.drawable.bg_upper_vn_off);
            tv_country_2.setVisibility( View.GONE );

        } else if (status.equals( "connecting" )) {

            Preference.setisVpnConnect( false );
            txt_status.setText( "Connecting...\nPlease Wait!" );
            mkloaderReg.setVisibility( View.VISIBLE );
            tv_country_2.setVisibility( View.GONE );
//            ll_status.setBackgroundResource(R.drawable.bg_button_round);
//            rl_header.setBackgroundResource(R.drawable.bg_upper_vn_off);

        } else if (status.equals( "connected" )) {

            Preference.setisVpnConnect( true );
            Ads_VN_Screen.VN_showAds( Connect_Network_Screen.this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    startActivity( new Intent( Connect_Network_Screen.this, Privacy_Policy_Screen.class ) );
                    finish();
                }
            } );

            txt_status.setText( getResources().getString( R.string.switch_off ) );
            mkloaderReg.setVisibility( View.GONE );
            tv_country_2.setVisibility( View.GONE );
            img_off_on.setImageResource( R.drawable.ic_connect );
//            ll_status.setBackgroundResource(R.drawable.bg_button_round_plan);
//            rl_header.setBackgroundResource(R.drawable.bg_upper_vn_on);

        } else if (status.equals( "tryDifferentServer" )) {
            txt_status.setText( "Try Different\nServer" );
        } else if (status.equals( "loading" )) {
            txt_status.setText( "Loading Server.." );
        } else if (status.equals( "invalidDevice" )) {
            txt_status.setText( "Invalid Device" );
        } else if (status.equals( "authenticationCheck" )) {
            txt_status.setText( "Authentication \n Checking..." );
        }
    }

    public void isLoggedIn(Callback<Boolean> callback) {
        UnifiedSdk.getInstance().getBackend().isLoggedIn( callback );
    }

    public void connectToVpn() {
        isLoggedIn( new Callback<Boolean>() {
            @Override
            public void success(@NonNull Boolean aBoolean) {
                if (aBoolean) {
                    List<String> fallbackOrder = new ArrayList<>();
                    fallbackOrder.add( HydraTransport.TRANSPORT_ID );
                    //fallbackOrder.add(CaketubeTransport.TRANSPORT_ID_TCP);
                    // fallbackOrder.add(CaketubeTransport.TRANSPORT_ID_UDP);
                    //showConnectProgress();
                    List<String> bypassDomains = new LinkedList<>();
                    bypassDomains.add( "*facebook.com" );
                    bypassDomains.add( "*wtfismyip.com" );
                    UnifiedSdk.getInstance().getVpn().start( new SessionConfig.Builder()
                            .withReason( TrackingConstants.GprReasons.M_UI )
                            .withTransportFallback( fallbackOrder )
                            .withVirtualLocation( Preference.getServer_short().toLowerCase() )
                            .withTransport( HydraTransport.TRANSPORT_ID )
                            .addDnsRule( TrafficRule.Builder.bypass().fromDomains( bypassDomains ) )
                            .build(), new CompletableCallback() {
                        @Override
                        public void complete() {
                            Log.d( " ", "complete" );
                            setStatus( "CONNECTED" );
                            startUIUpdateTask();
                        }

                        @Override
                        public void error(@NonNull VpnException e) {
                            Log.d( "MainActivity12", "error = " + e.getMessage() );
                            setStatus( "DISCONNECTED" );
                            if (e.getMessage().contains( "TRAFFIC_EXCEED" )) {
                                Ads_VN_Screen.VN_showAds( Connect_Network_Screen.this, new Ads_Interstitial.OnFinishAds() {
                                    @Override
                                    public void onFinishAds(boolean b) {
                                        startActivity( new Intent( Connect_Network_Screen.this, Privacy_Policy_Screen.class ) );
                                        finish();
                                    }
                                } );
                            } else if (e.getMessage().contains( "Wrong state to call start" )) {
                                Toast.makeText( Connect_Network_Screen.this, "try again!", Toast.LENGTH_SHORT ).show();
                                disconnectFromVnp( true );
                            }
                        }
                    } );
                }
            }
            @Override
            public void failure(@NonNull VpnException e) {
                Log.d( "MainActivity12", "Vp  = " + e.getMessage() );
            }
        } );
    }

    public void disconnectFromVnp(boolean isfromConnnecting) {
        UnifiedSdk.getInstance().getVpn().stop( TrackingConstants.GprReasons.M_UI, new CompletableCallback() {
            @Override
            public void complete() {
                status( "connect" );
                Utils.vpnStart = false;
//                Utils.isVpnConnect = false;
                Preference.setisVpnConnect( false );
                /*if (!isfromConnnecting) {
                    Ads.showAds(Connect_VPN_Screen.this, new Ads.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            mUIHandler.removeCallbacks(mUIUpdateRunnable);
                             startActivity(new Intent(Connect_VPN_Screen.this, Privacy_Policy_Screen.class));
                        }
                    });
                }*/
            }

            @Override
            public void error(@NonNull VpnException e) {
                //Toast.makeText(Pro_MainActivity.this, "Disconnect error", Toast.LENGTH_SHORT).show();
            }
        } );
    }


    public void setStatus(String connectionState) {
        if (connectionState != null)
            switch (connectionState) {
                case "DISCONNECTED":
                    status( "connect" );
                    Utils.vpnStart = false;
                    break;
                case "CONNECTED":
                    Utils.vpnStart = true;// it will use after restart this activity
                    status( "connected" );
                    //CallIpApi();
                    break;
                case "WAIT":

                    break;
                case "AUTH":
                    break;
                case "RECONNECTING":
                    status( "connecting" );
                    break;
                case "NONETWORK":
                    break;
            }

    }


    protected void startUIUpdateTask() {
        mUIHandler.removeCallbacks( mUIUpdateRunnable );
        mUIHandler.post( mUIUpdateRunnable );
    }

    private Handler mUIHandler = new Handler( Looper.getMainLooper() );
    final Runnable mUIUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            checkRemainingTraffic();
            mUIHandler.postDelayed( mUIUpdateRunnable, 10000 );
        }
    };

    private void checkRemainingTraffic() {
        UnifiedSdk.getInstance().getBackend().remainingTraffic( new Callback<RemainingTraffic>() {
            @Override
            public void success(RemainingTraffic remainingTraffic) {
                updateRemainingTraffic( remainingTraffic );
            }

            @Override
            public void failure(VpnException e) {
            }
        } );
    }

    protected void updateRemainingTraffic(RemainingTraffic remainingTrafficResponse) {
        Log.d( "MainActivity12", "updateRemainingTraffic = " + remainingTrafficResponse );
        Log.d( "MainActivity12", "updateRemainingTraffic one = " + remainingTrafficResponse.isUnlimited() );

        String trafficUsed = megabyteCount( remainingTrafficResponse.getTrafficUsed() );
        String trafficLimit = megabyteCount( remainingTrafficResponse.getTrafficLimit() ) + "Mb";
        Log.d( "MainActivity12", "updateRemainingTraffic trafficUsed = " + trafficUsed );
        Log.d( "MainActivity12", "updateRemainingTraffic trafficLimit = " + trafficLimit );
        if (remainingTrafficResponse.getTrafficLimit() <= remainingTrafficResponse.getTrafficUsed()) {
            mUIHandler.removeCallbacks( mUIUpdateRunnable );
            Set_Limit_size();
        }
    }

    public static String megabyteCount(long bytes) {
        return String.format( Locale.getDefault(), "%.0f", (double) bytes / 1024 / 1024 );
    }

    private void Set_Limit_size() {
        int New_limit_traffic = 1000;
        long total_bytes = New_limit_traffic * 1048576;
        Delete_ApiCall( total_bytes );
    }

    APIInterface mApiInterface;

    private void Delete_ApiCall(long total_bytes) {
        Utils.showProgressDialog( this );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://developer.anchorfree.com/api/prod/partner/subscribers/" )
                .addConverterFactory( GsonConverterFactory.create() ) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        mApiInterface = retrofit.create( APIInterface.class );
        Call<TraficLimitResponse> call = mApiInterface.Call_Delete_Trafic( String.valueOf( Preference.getAura_user_id() ) + "/traffic?access_token=" + Preference.getAccessToken() );
        call.enqueue( new retrofit2.Callback<TraficLimitResponse>() {
            @Override
            public void onResponse(Call<TraficLimitResponse> call, Response<TraficLimitResponse> response) {
                Utils.hideProgressDialog();
                if (response.isSuccessful()) {
                    Log.d( "ScratchActivity1", "Delete_ApiCall  =  OK" );
                    Add_Trafic_size( total_bytes );
                } else {

                    Log.d( "ScratchActivity1", "Delete_ApiCall = " + response.message() );
                    Toast.makeText( Connect_Network_Screen.this, "Something went wrong!", Toast.LENGTH_SHORT ).show();
                }

            }

            @Override
            public void onFailure(Call<TraficLimitResponse> call, Throwable t) {
                Utils.hideProgressDialog();
                Log.d( "ScratchActivity1 on filer ", t.getLocalizedMessage()  );
                Toast.makeText( Connect_Network_Screen.this, "Something went wrong!", Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void Add_Trafic_size(long total_bytes) {
        Utils.showProgressDialog( this );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( "https://developer.anchorfree.com/api/prod/partner/subscribers/" )
                .addConverterFactory( GsonConverterFactory.create() ) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        mApiInterface = retrofit.create( APIInterface.class );
        Call<TraficLimitResponse> call = mApiInterface.Call_Add_Trafic( String.valueOf( Preference.getAura_user_id() ) + "/traffic?access_token=" + Preference.getAccessToken() + "&traffic_limit=" + String.valueOf( total_bytes ) );
        call.enqueue( new retrofit2.Callback<TraficLimitResponse>() {
            @Override
            public void onResponse(Call<TraficLimitResponse> call, Response<TraficLimitResponse> response) {
                Utils.hideProgressDialog();
                if (response.isSuccessful()) {
                    Log.d( "ScratchActivity1", "Add_Trafic_size  =  OK" );
                    Toast.makeText( Connect_Network_Screen.this, "Please connect again vpn!", Toast.LENGTH_SHORT ).show();
                } else {
                    Log.d( "ScratchActivity1", "Add_Trafic_size = " + response.message() );
                    Toast.makeText( Connect_Network_Screen.this, "Something went wrong!", Toast.LENGTH_SHORT ).show();

                }

            }

            @Override
            public void onFailure(Call<TraficLimitResponse> call, Throwable t) {
                Utils.hideProgressDialog();
                Log.d( "ScratchActivity1 Add_Trafic_size ", t.getLocalizedMessage()  );

                Toast.makeText( Connect_Network_Screen.this, "Something went wrong!", Toast.LENGTH_SHORT ).show();
                // Toast.makeText(Pro_SplashActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        } );
    }


    @Override
    public void onResume() {
        Log.d( "MainActivity12", "onResume" );
        Log.d( "MainActivity12", "onResume = " + Utils.vpnStart );
        Log.d( "MainActivity12", "onResume = " + Preference.getisVpnConnect() );

        Resources resources = Connect_Network_Screen.this.getResources();
        String sb = "drawable/" + Preference.getServer_short().toLowerCase();
        Glide.with( Connect_Network_Screen.this ).load( APIClient.img_url + Preference.getServer_image() ).error( R.drawable.vpn_place ).placeholder( R.drawable.vpn_place).into( iv_flag );
        tv_country.setText( Preference.getserver_name() );
        tv_country_2.setText( Preference.getserver_name() );
        checkRemainingTraffic();
        if (Preference.getisVpnConnect()) {
            startUIUpdateTask();
            Preference.setisVpnConnect( true );
            txt_status.setText( getResources().getString( R.string.switch_off ) );
            mkloaderReg.setVisibility( View.GONE );
            img_off_on.setImageResource( R.drawable.ic_connect );
        }

        super.onResume();
    }
    boolean exit_flag = false;
    @Override
    public void onBackPressed() {

        if (Preference.getisVpnConnect()) {
            Ads_Interstitial.BackshowAds_full( this, new Ads_Interstitial.OnFinishAds() {
                @Override
                public void onFinishAds(boolean b) {
                    finish();

                }
            } );

        } else {
            if (exit_flag) {
                Appcontroller.fast_start = true;
                finishAffinity();
            } else {
                exit_flag = true;
                Toast.makeText( this, "Please tap again to exit!", Toast.LENGTH_SHORT ).show();
            }
        }
    }
}