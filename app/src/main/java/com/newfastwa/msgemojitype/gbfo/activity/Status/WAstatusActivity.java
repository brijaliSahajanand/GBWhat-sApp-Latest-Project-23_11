package com.newfastwa.msgemojitype.gbfo.activity.Status;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.activity.Status.Fragment.WhatsappImgFragment;
import com.newfastwa.msgemojitype.gbfo.activity.Status.Fragment.WhatsappVidFragment;

import com.newfastwa.msgemojitype.gbfo.utils.Header;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class WAstatusActivity extends BaseActivity implements Header.onback{

    //View
    TabLayout tabs;
    ViewPager viewpager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_astatus);

        Declaration();
        Intialization();
    }

    private void Declaration() {
        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

    }

    private void Intialization() {


        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), 1);
        viewPagerAdapter.addFragment(new WhatsappImgFragment(), "Images");
        viewPagerAdapter.addFragment(new WhatsappVidFragment(), "Videos");
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(1);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        ViewPagerAdapter(FragmentManager fragmentManager, int i) {
            super(fragmentManager, i);
        }

        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        /* access modifiers changed from: package-private */
        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
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
}