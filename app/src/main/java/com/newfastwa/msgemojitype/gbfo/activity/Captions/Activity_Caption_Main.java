package com.newfastwa.msgemojitype.gbfo.activity.Captions;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;
import com.newfastwa.msgemojitype.gbfo.activity.Captions.adpter.CategoryListAdapter;
import com.newfastwa.msgemojitype.gbfo.utils.Header;
import com.newfastwa.msgemojitype.gbfo.utils.RecyclerTouchListener;

public class Activity_Caption_Main extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback {

    static String fileToOpen;
    static int positionSelected;
    static String[] selectedArray;
    String[][] captionsAtrrays;
    CategoryListAdapter categoryListAdapter;
    //   ListView listViewCategory;
    SharedPreferences.Editor localEditor;
    SharedPreferences localSharedPreferences;
    RecyclerView rv_caption_list;

    @Override
    protected void onResume() {
        Header header = findViewById(R.id.header);
        Header.onback onBackPressed = (Header.onback) this;
        header.init(onBackPressed);
        super.onResume();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caption_main);

        String[][] strArr = {new String[]{"sckjdch", "dcdscdsd"}, getResources().getStringArray(R.array.Birthday_Captions), getResources().getStringArray(R.array.Cool_Captions), getResources().getStringArray(R.array.Fitness_Captions), getResources().getStringArray(R.array.Flirty), getResources().getStringArray(R.array.Food_Captions), getResources().getStringArray(R.array.Friendship), getResources().getStringArray(R.array.Funny_Captions), getResources().getStringArray(R.array.Inspiring_Captions), getResources().getStringArray(R.array.Life), getResources().getStringArray(R.array.Love), getResources().getStringArray(R.array.Party_Captions), getResources().getStringArray(R.array.Sarcastic_Captions), getResources().getStringArray(R.array.Savage_Captions), getResources().getStringArray(R.array.Selfie_Captions), getResources().getStringArray(R.array.Selflove_Captions), getResources().getStringArray(R.array.Smile_Captions), getResources().getStringArray(R.array.Sucess_Captions), getResources().getStringArray(R.array.Sweet_Captions), getResources().getStringArray(R.array.Travel_Captions), getResources().getStringArray(R.array.happiness)};
        //this.listViewCategory = (ListView) findViewById(R.id.listViewCategories);


    /*    Header header = findViewById(R.id.header);
        Header.onback onBackPressed = (Header.onback) this;
        header.init(onBackPressed);*/


        rv_caption_list = findViewById(R.id.rv_caption_list);

        Adapter_Caption_List adapter_caption_list = new Adapter_Caption_List(Activity_Caption_Main.this, Categories.categoriesArray);
        rv_caption_list.setAdapter(adapter_caption_list);

        SharedPreferences sharedPreferences = getSharedPreferences("FAVORITEMESSAGES", 0);
        this.localSharedPreferences = sharedPreferences;
        this.localEditor = sharedPreferences.edit();
        this.captionsAtrrays = strArr;
        rv_caption_list.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), rv_caption_list, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("positionSelected - ", positionSelected + " - ");
                positionSelected = position;
                if (positionSelected < 0 || positionSelected > 5) {
                    selectedArray = captionsAtrrays[positionSelected - 5];

                    Ads_Interstitial.showAds_full(Activity_Caption_Main.this, new Ads_Interstitial.OnFinishAds() {
                        @Override
                        public void onFinishAds(boolean b) {
                            startActivity(new Intent(Activity_Caption_Main.this, CategoryCaptionsActivity.class));
                        }
                    });
                    return;
                }
                if (positionSelected == 0) {
                    fileToOpen = "girls.txt";
                } else if (positionSelected == 1) {
                    fileToOpen = "emotion.txt";
                } else if (positionSelected == 2) {
                    fileToOpen = "friends.txt";
                } else if (positionSelected == 3) {
                    fileToOpen = "hilarious.txt";
                } else if (positionSelected == 4) {
                    fileToOpen = "insta.txt";
                } else if (positionSelected == 5) {
                    fileToOpen = "motivational.txt";
                }
                Ads_Interstitial.showAds_full(Activity_Caption_Main.this, new Ads_Interstitial.OnFinishAds() {
                    @Override
                    public void onFinishAds(boolean b) {
                        startActivity(new Intent(Activity_Caption_Main.this, CaptionsByFilesActivity.class));

                    }
                });

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        /*this.listViewCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
                positionSelected = i;
                if (positionSelected < 0 || positionSelected > 5) {
                    selectedArray = captionsAtrrays[positionSelected - 5];
                    startActivity(new Intent(Activity_Caption_Main.this, CategoryCaptionsActivity.class));
                    return;
                }
                if (positionSelected == 0) {
                    fileToOpen = "girls.txt";
                } else if (positionSelected == 1) {
                    fileToOpen = "emotion.txt";
                } else if (positionSelected == 2) {
                    fileToOpen = "friends.txt";
                } else if (positionSelected == 3) {
                    fileToOpen = "hilarious.txt";
                } else if (positionSelected == 4) {
                    fileToOpen = "insta.txt";
                } else if (positionSelected == 5) {
                    fileToOpen = "motivational.txt";
                }
                startActivity(new Intent(Activity_Caption_Main.this, CaptionsByFilesActivity.class));
            }
        });*/
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

class Adapter_Caption_List extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context ctx;

    public String[] items;

    public Adapter_Caption_List(Context context, String[] strings) {
        this.items = strings;
        this.ctx = context;

    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OriginalViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text_caption, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof OriginalViewHolder) {
            ((OriginalViewHolder) viewHolder).text.setText(items[i]);

        }
    }

    public int getItemCount() {
        return this.items.length;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {

        TextView text;

        public OriginalViewHolder(View view) {
            super(view);

            text = view.findViewById(R.id.text);
        }
    }


}

