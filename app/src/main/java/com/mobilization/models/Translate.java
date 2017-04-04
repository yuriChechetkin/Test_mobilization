package com.mobilization.models;

import java.io.Serializable;

/**
 * Created by mac on 02.04.17.
 */

public class Translate implements Serializable {

    private String originalText;
    private String translatedText;
    private Language originalLang;
    private Language tranlsateLang;
    private boolean isFavorite = false;
    private boolean isHistory = false; /*??????*/

    public Translate() {

    }

    public Translate(TranslateResponse tr) {
        Language l1 = new Language(tr.getLang(), tr.getLang().split("-")[0]);
        Language l2 = new Language(tr.getLang(), tr.getLang().split("-")[1]);
        this.setOriginalLang(l1);
        this.setTranlsateLang(l2);
        this.setTranslatedText(tr.getText().get(0));
    }

    public Translate(String originalText, String translatedText, Language originalLang, Language tranlsateLang, boolean isFavorite, boolean isHistory) {
        this.originalText = originalText;
        this.translatedText = translatedText;
        this.originalLang = originalLang;
        this.tranlsateLang = tranlsateLang;
        this.isFavorite = isFavorite;
        this.isHistory = isHistory;
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

    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Translate translate = (Translate) o;

        if (originalText.equals(translate.originalText) && translatedText.equals(translate.translatedText))
            return true;
        return false;
    }
}
