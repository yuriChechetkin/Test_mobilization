package com.mobilization.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mac on 02.04.17.
 */

public class Translate implements Serializable {

    private String originalText;
    private String translatedText;
    private Language originalLang;
    private Language tranlsateLang;
    private String dirs;
    private boolean isFavorite = false;
    private long unixTime=0; //для идентификации;

    public Translate() {
        this.unixTime = new Date().getTime();
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }

    public String getTranslatedText() {
        return translatedText;
    }

    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    public Language getTranlsateLang() {
        return tranlsateLang;
    }

    public void setTranlsateLang(Language tranlsateLang) {
        this.tranlsateLang = tranlsateLang;
    }

    public Language getOriginalLang() {
        return originalLang;
    }

    public void setOriginalLang(Language originalLang) {
        this.originalLang = originalLang;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getDirs() {
        return dirs;
    }

    public void setDirs(String dirs) {
        this.dirs = dirs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Translate translate = (Translate) o;

        if (unixTime==translate.unixTime)
            return true;
        return false;
    }
}
