package com.newfastwa.msgemojitype.gbfo.model;

import java.util.ArrayList;

public class Pojo {
    public ArrayList<String> Currency;
    public ArrayList<String> Punctuation;
    public ArrayList<String> Quotation;
    public ArrayList<String> Roman;
    public ArrayList<String> arrows;
    public ArrayList<String> chess;
    public ArrayList<String> emoji;
    public ArrayList<String> flower;
    public ArrayList<String> heart;
    public ArrayList<String> special;
    public ArrayList<String> stars;
    public ArrayList<String> zodiac;

    public Pojo(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3, ArrayList<String> arrayList4, ArrayList<String> arrayList5, ArrayList<String> arrayList6, ArrayList<String> arrayList7, ArrayList<String> arrayList8, ArrayList<String> arrayList9, ArrayList<String> arrayList10, ArrayList<String> arrayList11, ArrayList<String> arrayList12) {
        this.arrows = arrayList;
        this.heart = arrayList2;
        this.special = arrayList3;
        this.flower = arrayList4;
        this.Quotation = arrayList5;
        this.stars = arrayList6;
        this.emoji = arrayList7;
        this.chess = arrayList8;
        this.zodiac = arrayList9;
        this.Currency = arrayList10;
        this.Roman = arrayList11;
        this.Punctuation = arrayList12;
    }

    public ArrayList<String> getArrows() {
        return this.arrows;
    }

    public void setArrows(ArrayList<String> arrayList) {
        this.arrows = arrayList;
    }

    public ArrayList<String> getHeart() {
        return this.heart;
    }

    public void setHeart(ArrayList<String> arrayList) {
        this.heart = arrayList;
    }

    public ArrayList<String> getSpecial() {
        return this.special;
    }

    public void setSpecial(ArrayList<String> arrayList) {
        this.special = arrayList;
    }

    public ArrayList<String> getFlower() {
        return this.flower;
    }

    public void setFlower(ArrayList<String> arrayList) {
        this.flower = arrayList;
    }

    public ArrayList<String> getQuotation() {
        return this.Quotation;
    }

    public void setQuotation(ArrayList<String> arrayList) {
        this.Quotation = arrayList;
    }

    public ArrayList<String> getStars() {
        return this.stars;
    }

    public void setStars(ArrayList<String> arrayList) {
        this.stars = arrayList;
    }

    public ArrayList<String> getEmoji() {
        return this.emoji;
    }

    public void setEmoji(ArrayList<String> arrayList) {
        this.emoji = arrayList;
    }

    public ArrayList<String> getChess() {
        return this.chess;
    }

    public void setChess(ArrayList<String> arrayList) {
        this.chess = arrayList;
    }

    public ArrayList<String> getZodiac() {
        return this.zodiac;
    }

    public void setZodiac(ArrayList<String> arrayList) {
        this.zodiac = arrayList;
    }

    public ArrayList<String> getCurrency() {
        return this.Currency;
    }

    public void setCurrency(ArrayList<String> arrayList) {
        this.Currency = arrayList;
    }

    public ArrayList<String> getRoman() {
        return this.Roman;
    }

    public void setRoman(ArrayList<String> arrayList) {
        this.Roman = arrayList;
    }

    public ArrayList<String> getPunctuation() {
        return this.Punctuation;
    }

    public void setPunctuation(ArrayList<String> arrayList) {
        this.Punctuation = arrayList;
    }
}
