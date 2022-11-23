package com.newfastwa.msgemojitype.gbfo.model;

import java.util.ArrayList;

public class TextPojo {
   public ArrayList<String> pojo;
   public String text;

    public TextPojo(ArrayList<String> arrayList, String str) {
        this.pojo = arrayList;
        this.text = str;
    }

    public ArrayList<String> getPojo() {
        return this.pojo;
    }

    public void setPojo(ArrayList<String> arrayList) {
        this.pojo = arrayList;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
