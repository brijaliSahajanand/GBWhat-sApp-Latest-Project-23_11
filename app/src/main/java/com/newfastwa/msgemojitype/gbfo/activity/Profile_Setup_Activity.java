package com.newfastwa.msgemojitype.gbfo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.bumptech.glide.Glide;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.utils.Preference;


public class Profile_Setup_Activity extends com.newfastwa.msgemojitype.gbfo.BaseActivity {

    EditText edt_name;
    Button btn_next;
    
    private Uri selectedImageUri;
    ImageView iv_profile;
    private static final int SELECT_PICTURE = 100;
    FrameLayout fm_profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        edt_name = findViewById(R.id.edt_name);
        btn_next = findViewById(R.id.btn_next);
        iv_profile = findViewById(R.id.iv_profile);
        fm_profile = findViewById(R.id.fm_profile);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_name.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_Setup_Activity.this, "Enter Your Name", Toast.LENGTH_SHORT).show();
                } else {

                    if (selectedImageUri != null) {

                        Preference.setuser_img_uri(selectedImageUri.toString());
                    }
                    Preference.setuser_name(edt_name.getText().toString());

                    Ads_Interstitial.showAds_full(Profile_Setup_Activity.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            startActivity(new Intent(Profile_Setup_Activity.this, FirstActivity.class));
                            finish();
                        }
                    });


                }
            }
        });

        fm_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                //  intent.addCategory(Intent.CATEGORY_OPENABLE);
                //  intent.setType("image/*");
                //  startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, SELECT_PICTURE);
            }
        });

    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {

                    Log.d("uriiiii", "new   " + selectedImageUri);
                    //  iv_profile.setImageURI(selectedImageUri);
//                    new LoadImageDataTask(selectedImageUri).execute();

                    Glide.with(Profile_Setup_Activity.this).load(selectedImageUri).placeholder(R.drawable.ic_profile_place_holder).error(R.drawable.ic_profile_place_holder).into(iv_profile);

                }
            }
        }
    }


    boolean exit_flag = false;

    @Override
    public void onBackPressed() {
        Ads_Interstitial.BackshowAds_full(this, new Ads_Interstitial.OnFinishAds() {
            @Override
            public void onFinishAds(boolean b) {
                finish();
            }
        });
    }




}