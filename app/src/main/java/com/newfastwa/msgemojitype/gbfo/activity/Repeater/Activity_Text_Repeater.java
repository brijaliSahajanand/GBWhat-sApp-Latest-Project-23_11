package com.newfastwa.msgemojitype.gbfo.activity.Repeater;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.BaseActivity;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.utils.Header;
import com.google.android.gms.ads.RequestConfiguration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class
Activity_Text_Repeater extends BaseActivity implements Header.onback{


    public SharedPreferences data;
    public boolean first_repeat = false;
    public EditText how_many_input;
    public Intent intent = new Intent();
    public CheckBox line_checkbox;
    public double line_number = 0.0d;
    public CheckBox line_number_checkbox;
    public double repeat = 0.0d;
    public EditText repeat_input;
    public TextView repeated_output;
    public CheckBox space_checkbox;
    public String text_repeat = RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED;
    public String text_to_repeat = RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED;
    private Calendar calendar = Calendar.getInstance();
    private double history_index = 0.0d;
    private LinearLayout ln_repeat;
    private Vibrator vibrator;
    ImageView iv_delete_text,iv_copy_text,ic_whats_share;
    
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_text_repeater);
        initialize(bundle);
        initializeLogic();
    }

    private void initialize(Bundle bundle) {

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

        this.ln_repeat = findViewById(R.id.ln_repeat);
        this.repeat_input = (EditText) findViewById(R.id.repeat_input);
        this.how_many_input = (EditText) findViewById(R.id.how_many_input);
        this.space_checkbox = (CheckBox) findViewById(R.id.space_checkbox);
        this.line_checkbox = (CheckBox) findViewById(R.id.line_checkbox);
        this.line_number_checkbox = (CheckBox) findViewById(R.id.line_number_checkbox);
        this.repeated_output = (TextView) findViewById(R.id.repeated_output);
        ic_whats_share = findViewById(R.id.ic_whats_share);


        iv_copy_text = findViewById(R.id.iv_copy_text);
        iv_delete_text = findViewById(R.id.iv_delete_text);

        this.vibrator = (Vibrator) getSystemService("vibrator");
        this.data = getSharedPreferences("data", 0);
        this.ln_repeat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                _vibrate();
                if (repeat_input.getText().toString().equals(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED) || how_many_input.getText().toString().equals(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED)) {
                    showMessage("Enter your text and how many repetitions");
                    return;
                }
                text_to_repeat = RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED;
                text_repeat = RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED;
                line_number = 1.0d;
                first_repeat = true;
                repeat = Double.parseDouble(how_many_input.getText().toString());
                text_to_repeat = repeat_input.getText().toString();
                if (((double) text_to_repeat.length()) * repeat > 100000.0d) {
                    showMessage("Character limit will be exceeded, try fewer repetitions");
                    return;
                }
                if (space_checkbox.isChecked()) {
                    for (int i = 0; i < ((int) repeat); i++) {
                        if (first_repeat) {
                            text_repeat = text_to_repeat;
                            first_repeat = false;
                        } else {
                            text_repeat = text_repeat.concat(" ".concat(text_to_repeat));
                        }
                    }
                } else if (!line_checkbox.isChecked()) {
                    for (int i2 = 0; i2 < ((int) repeat); i2++) {
                        if (first_repeat) {
                            text_repeat = text_to_repeat;
                            first_repeat = false;
                        } else {
                            text_repeat = text_repeat.concat(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED.concat(text_to_repeat));
                        }
                    }
                } else if (line_number_checkbox.isChecked()) {
                    for (int i3 = 0; i3 < ((int) repeat); i3++) {
                        if (first_repeat) {
                            text_repeat = "1. ".concat(text_to_repeat);
                            first_repeat = false;

                            line_number = line_number + 1.0d;
                        } else {
                            text_repeat = text_repeat.concat("\n".concat(String.valueOf((long) line_number).concat(".".concat(" ".concat(text_to_repeat)))));

                            line_number = line_number + 1.0d;
                        }
                    }
                } else {
                    for (int i4 = 0; i4 < ((int) repeat); i4++) {
                        if (first_repeat) {
                            text_repeat = text_to_repeat;
                            first_repeat = false;
                        } else {
                            text_repeat = text_repeat.concat("\n".concat(text_to_repeat));
                        }
                    }
                }
                repeated_output.setText(text_repeat);
                _save_history();
            }
        });

        this.how_many_input.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.toString().contains("-")) {
                    how_many_input.setText(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED);
                }
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.space_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    line_number_checkbox.setVisibility(View.INVISIBLE);
                    line_checkbox.setChecked(false);
                    line_number_checkbox.setChecked(false);
                    data.edit().putString("space", "on").commit();
                    return;
                }
                data.edit().putString("space", "off").commit();
            }
        });
        this.line_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    space_checkbox.setChecked(false);
                    line_number_checkbox.setVisibility(View.VISIBLE);
                    data.edit().putString("newline", "on").commit();
                    return;
                }
                line_number_checkbox.setChecked(false);
                line_number_checkbox.setVisibility(View.INVISIBLE);
                data.edit().putString("newline", "off").commit();
            }
        });
        this.line_number_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    data.edit().putString("numbers", "on").commit();
                } else {
                    data.edit().putString("numbers", "off").commit();
                }
            }
        });

        iv_delete_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeat_input.setText(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED);
                how_many_input.setText(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED);
                repeated_output.setText(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED);
                repeat = 0.0d;
                text_to_repeat = RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED;
                text_repeat = RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED;
                line_number = 1.0d;
                first_repeat = true;
            }
        });


        iv_copy_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (repeated_output.getText().toString().length() > 0) {

                    getApplicationContext();
                    ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("clipboard", repeated_output.getText().toString()));
                    Toast.makeText(Activity_Text_Repeater.this, "Text Copied!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ic_whats_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, repeated_output.getText().toString());

                if (whatsappIntent.resolveActivity(getPackageManager()) == null) {
                    Toast.makeText(Activity_Text_Repeater.this, "Whatsapp not installed.", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(whatsappIntent);
            }
        });

    }

    private void initializeLogic() {
        this.line_number_checkbox.setVisibility(View.INVISIBLE);
        this.line_number = 1.0d;
    }

    
    public void onActivityResult(int i, int i2, Intent intent2) {
        super.onActivityResult(i, i2, intent2);
    }

    public void onStart() {
        super.onStart();
        if (this.data.getString("historyindex", RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED).equals(RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED)) {
            this.history_index = 0.0d;
        } else {
            this.history_index = Double.parseDouble(this.data.getString("historyindex", RequestConfiguration.MAX_AD_CONTENT_RATING_UNSPECIFIED));
        }
    }

    public void _vibrate() {
        this.vibrator.vibrate(30);
    }

    public void _save_history() {
        this.calendar = Calendar.getInstance();
        this.data.edit().putString(String.valueOf((long) this.history_index).concat("text"), this.repeat_input.getText().toString()).commit();
        this.data.edit().putString(String.valueOf((long) this.history_index).concat("reps"), this.how_many_input.getText().toString()).commit();
        this.data.edit().putString(String.valueOf((long) this.history_index).concat("date"), new SimpleDateFormat("dd MMM yyyy").format(this.calendar.getTime()).concat(" ".concat(new SimpleDateFormat("HH:mm").format(this.calendar.getTime())))).commit();
        this.history_index += 1.0d;
        this.data.edit().putString("historyindex", String.valueOf((long) this.history_index)).commit();
    }

    @Deprecated
    public void showMessage(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationX(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[0];
    }

    @Deprecated
    public int getLocationY(View view) {
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        return iArr[1];
    }

    @Deprecated
    public int getRandom(int i, int i2) {
        return new Random().nextInt((i2 - i) + 1) + i;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView listView) {
        ArrayList<Double> arrayList = new ArrayList<>();
        SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
        for (int i = 0; i < checkedItemPositions.size(); i++) {
            if (checkedItemPositions.valueAt(i)) {
                arrayList.add(Double.valueOf((double) checkedItemPositions.keyAt(i)));
            }
        }
        return arrayList;
    }

    @Deprecated
    public float getDip(int i) {
        return TypedValue.applyDimension(1, (float) i, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
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
