package com.newfastwa.msgemojitype.gbfo.utils;

public class MyEmojiEditText extends androidx.appcompat.widget.AppCompatEditText {
    public MyEmojiEditText(android.content.Context context) {
        super(context);
        init();
    }

    public MyEmojiEditText(android.content.Context context, android.util.AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MyEmojiEditText(android.content.Context context, android.util.AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setFilters(new android.text.InputFilter[]{new EmojiIncludeFilter(), new android.text.InputFilter.LengthFilter(10)});
    }

    /* access modifiers changed from: private */
    public static class EmojiIncludeFilter implements android.text.InputFilter {
        private EmojiIncludeFilter() {
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, android.text.Spanned spanned, int i3, int i4) {
            while (i < i2) {
                int type = Character.getType(charSequence.charAt(i));
                if (type != 19 && type != 28) {
                    return "";
                }
                i++;
            }
            return null;
        }
    }
}