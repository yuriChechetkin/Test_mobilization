package com.mobilization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 03.04.17.
 */

public class TranslateResponse implements Serializable {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("lang")
    @Expose
    private String lang;

    @SerializedName("text")
    @Expose
    private List<String> text = new ArrayList<>();

    private List<String> originalText = new ArrayList<>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getText() {
        return text;
    }

    public void setText(List<String> text) {
        this.text = text;
    }

    public List<String> getOriginalText() {
        return originalText;
    }

    public void setOriginalText(List<String> originalText) {
        this.originalText = originalText;
    }
}
