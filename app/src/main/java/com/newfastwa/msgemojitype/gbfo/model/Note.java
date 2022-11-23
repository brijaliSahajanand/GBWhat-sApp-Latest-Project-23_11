package com.newfastwa.msgemojitype.gbfo.model;

public class Note {
    private String content;
    private int id = -1;
    private String thickness;
    private String title;

    public Note(String str, String str2, String str3) {
        this.title = str;
        this.content = str2;
        this.thickness = str3;
    }

    public Note(int i, String str, String str2, String str3) {
        this.id = i;
        this.title = str;
        this.content = str2;
        this.thickness = str3;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getThickness() {
        return this.thickness;
    }

    public void setThickness(String str) {
        this.thickness = str;
    }
}