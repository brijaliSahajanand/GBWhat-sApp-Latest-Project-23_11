package com.newfastwa.msgemojitype.gbfo.activity.Emojis;

import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ads.adsdemosp.AdsClass.Ads_Interstitial;
import com.newfastwa.msgemojitype.gbfo.R;

import com.newfastwa.msgemojitype.gbfo.model.DatabaseHelper;
import com.newfastwa.msgemojitype.gbfo.model.Note;
import com.newfastwa.msgemojitype.gbfo.utils.Header;
import com.newfastwa.msgemojitype.gbfo.utils.MyEmojiEditText;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Activity_Text_TO_Emoji extends com.newfastwa.msgemojitype.gbfo.BaseActivity implements Header.onback {


    public static final String NORMAL_THICKNESS = "NORMAL";
    public static final String THICK_THICKNESS = "THICK";
    public static Integer flag = 0;
    private static Note mNote = null;
    private final java.util.List<String> emojisList = new java.util.ArrayList();
    EditText eTResultEmoji;
    private DatabaseHelper db;
    private MyEmojiEditText eTEmoji;
    private TextInputEditText eTWord;
    private TextInputLayout inputEmoji;
    private TextInputLayout inputWord;
    private String mThickness = NORMAL_THICKNESS;
    private android.widget.TextView normalThickness;
    private android.widget.TextView tVGenerate;
    private android.widget.TextView thickThickness;
    private int lastEmojiPos = 0;
    LinearLayout ln_emojis_list;
    ImageView iv_delete_emojis, iv_copy_emojis, ic_whats_share;

    @Override
    public void onCreate(android.os.Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_text_to_emojis);
        initID();
        setUP();
        this.db = new DatabaseHelper(getApplicationContext());

        if (mNote != null) {
            addDataInFields();
        }
    }

    private void initID() {

        Header header = findViewById(R.id.header);
        Header.onback  onBackPressed = (Header.onback) this;
        header.init(onBackPressed);

        this.eTWord = findViewById(R.id.eTWord);
        ln_emojis_list = findViewById(R.id.ln_emojis_list);
        eTEmoji = findViewById(R.id.eTEmoji);
        normalThickness = findViewById(R.id.tvThicknessNormal);
        thickThickness = findViewById(R.id.tvThicknessThin);
        inputWord = findViewById(R.id.inputWord);
        inputEmoji = findViewById(R.id.inputEmoji);
        tVGenerate = findViewById(R.id.tVGenerate);
        eTResultEmoji = findViewById(R.id.eTResultEmoji);
        iv_copy_emojis = findViewById(R.id.iv_copy_emojis);
        iv_delete_emojis = findViewById(R.id.iv_delete_emojis);
        ic_whats_share = findViewById(R.id.ic_whats_share);


        iv_copy_emojis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(android.content.ClipData.newPlainText(eTWord.getText().toString(), eTResultEmoji.getText().toString()));
                Toast.makeText(Activity_Text_TO_Emoji.this, "Text Copied!", Toast.LENGTH_SHORT).show();
            }
        });

        iv_delete_emojis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eTEmoji.setText("");
                ln_emojis_list.setVisibility(View.GONE);
            }
        });

        ic_whats_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, eTResultEmoji.getText().toString());

                if (!isAppInstalled("com.whatsapp")) {
                    Toast.makeText(Activity_Text_TO_Emoji.this, "Whatsapp not installed.", Toast.LENGTH_SHORT).show();
                    return;
                }

                startActivity(whatsappIntent);
            }
        });

    }

    private boolean isAppInstalled(String packageName) {
        try {
            getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException ignored) {
            return false;
        }
    }

    private void setUP() {

        setUpTextWatcher();
        setUpThicknessViews();
        this.normalThickness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThickness = NORMAL_THICKNESS;
                setUpThicknessViews();
            }
        });
        this.thickThickness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThickness = THICK_THICKNESS;
                setUpThicknessViews();
            }
        });
        this.tVGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmptyField(eTWord)) {
                    inputWord.setError("Required Field");
                } else if (eTEmoji.getText() == null || !android.text.TextUtils.isEmpty(eTEmoji.getText().toString())) {
                    Toast.makeText(Activity_Text_TO_Emoji.this, "succesffully", Toast.LENGTH_SHORT).show();

                    emojisList.addAll(com.vdurmont.emoji.EmojiParser.extractEmojis(eTEmoji.getText().toString()));
                    eTResultEmoji.setText(".\n");
                    ln_emojis_list.setVisibility(View.VISIBLE);
                    String assetsFolder = getAssetsFolder(mThickness);

                    char[] charArray = eTWord.getText().toString().toCharArray();
                    for (char c : charArray) {
                        if (c == '?') {
                            try {
                                java.io.InputStream open = getBaseContext().getAssets().open(assetsFolder + "ques.txt");
                                byte[] bArr = new byte[open.available()];
                                open.read(bArr);
                                open.close();
                                eTResultEmoji.append(getTextInEmojis(bArr) + "\n\n");
                            } catch (java.io.IOException e) {
                                e.printStackTrace();
                            }
                        } else if (c == ((char) (c & '_')) || Character.isDigit(c)) {
                            try {
                                java.io.InputStream open2 = getBaseContext().getAssets().open(assetsFolder + c + ".txt");
                                byte[] bArr2 = new byte[open2.available()];
                                open2.read(bArr2);
                                open2.close();
                                eTResultEmoji.append(getTextInEmojis(bArr2) + "\n\n");
                            } catch (java.io.IOException e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            try {
                                java.io.InputStream open3 = getBaseContext().getAssets().open(assetsFolder + "low" + c + ".txt");
                                byte[] bArr3 = new byte[open3.available()];
                                open3.read(bArr3);
                                open3.close();
                                eTResultEmoji.append(getTextInEmojis(bArr3) + "\n\n");
                            } catch (java.io.IOException e3) {
                                e3.printStackTrace();
                            }
                        }

                    }
                    //  adsConfig.showInterstitialAd(this, new emoji.letter.app.Activity.$$Lambda$Activity_Text_TO_Emoji$yNGihtic_GzoUbn07zgaJr3pay4(this));
                } else {
                    inputEmoji.setError("Required Field");
                }

            }
        });
    }

    private String getAssetsFolder(String str) {
        String str2 = "single";
        if (str != null && str.equalsIgnoreCase(Activity_Text_TO_Emoji.THICK_THICKNESS)) {
            str2 = "double";
        }
        return str2 + "/";
    }


    private String getTextInEmojis(byte[] bArr) {
        String str = new String(bArr);
        do {
            str = str.replaceFirst("[*]", getReplacmentEmoji());
        } while (str.contains("*"));
        return str;
    }

    private String getReplacmentEmoji() {
        if (this.emojisList.size() == 0) {
            return "";
        }
        if (this.emojisList.size() == 1) {
            return this.emojisList.get(0);
        }
        int i = lastEmojiPos + 1;
        lastEmojiPos = i;
        if (i > this.emojisList.size() - 1) {
            lastEmojiPos = 0;
        }
        return this.emojisList.get(lastEmojiPos);
    }


    private void setUpTextWatcher() {
        this.eTWord.addTextChangedListener(new AnonymousClass1());
        this.eTEmoji.addTextChangedListener(new AnonymousClass2());
    }

    private void setUpThicknessViews() {
        String str = this.mThickness;

        if (str.equals(NORMAL_THICKNESS)) {
            this.normalThickness.setBackground(androidx.core.content.res.ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select, null));
            this.thickThickness.setBackground(androidx.core.content.res.ResourcesCompat.getDrawable(getResources(), R.drawable.bg_unselect, null));
            this.thickThickness.setTextColor(androidx.core.content.ContextCompat.getColor(this, R.color.black));
            this.normalThickness.setTextColor(androidx.core.content.ContextCompat.getColor(this, R.color.white));


        } else if (str.equals(THICK_THICKNESS)) {
            this.thickThickness.setBackground(androidx.core.content.res.ResourcesCompat.getDrawable(getResources(), R.drawable.bg_select, null));
            this.normalThickness.setBackground(androidx.core.content.res.ResourcesCompat.getDrawable(getResources(), R.drawable.bg_unselect, null));
            this.thickThickness.setTextColor(androidx.core.content.ContextCompat.getColor(this, R.color.white));
            this.normalThickness.setTextColor(androidx.core.content.ContextCompat.getColor(this, R.color.black));
        }
       /* if (str.equals(NORMAL_THICKNESS)) {
            this.normalThickness.setBackground(androidx.core.content.res.ResourcesCompat.getDrawable(getResources(), R.drawable.selected_thickness, null));
            this.normalThickness.setTextColor(androidx.core.content.ContextCompat.getColor(this, R.color.white));
            this.thickThickness.setBackground(null);
            this.thickThickness.setTextColor(androidx.core.content.ContextCompat.getColor(this, R.color.black));
        } else if (str.equals(THICK_THICKNESS)) {
            this.thickThickness.setBackground(androidx.core.content.res.ResourcesCompat.getDrawable(getResources(), R.drawable.selected_thickness, null));
            this.thickThickness.setTextColor(androidx.core.content.ContextCompat.getColor(this, R.color.white));
            this.normalThickness.setBackground(null);
            this.normalThickness.setTextColor(androidx.core.content.ContextCompat.getColor(this, R.color.black));
        }*/

    }


    private void addDataInFields() {
        this.eTWord.setText(mNote.getTitle());
        this.eTEmoji.setText(mNote.getContent());
        this.mThickness = mNote.getThickness();
        setUpThicknessViews();
    }

    private boolean isEmptyField(TextInputEditText textInputEditText) {
        return textInputEditText.getText() != null && android.text.TextUtils.isEmpty(textInputEditText.getText().toString());
    }

    @Override
    public void onStop() {
        mNote = null;
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (flag.intValue() == 1) {
            this.eTWord.setText("");
            this.eTEmoji.setText("");
            flag = 0;
            mNote = null;
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

    public class AnonymousClass1 implements android.text.TextWatcher {
        AnonymousClass1() {
        }

        public void afterTextChanged(android.text.Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (Activity_Text_TO_Emoji.this.inputWord.isErrorEnabled()) {
                Activity_Text_TO_Emoji.this.inputWord.setErrorEnabled(false);
            }
        }
    }

    public class AnonymousClass2 implements android.text.TextWatcher {
        AnonymousClass2() {
        }

        public void afterTextChanged(android.text.Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (Activity_Text_TO_Emoji.this.inputEmoji.isErrorEnabled()) {
                Activity_Text_TO_Emoji.this.inputEmoji.setErrorEnabled(false);
            }
        }
    }

}
