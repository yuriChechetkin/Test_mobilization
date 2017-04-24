package com.mobilization.main;

import com.mobilization.models.Language;
import com.mobilization.models.Translate;
import com.mobilization.models.TranslateResponse;

import java.util.List;

import rx.Observable;

/**
 * Created by mac on 02.04.17.
 */

public interface MainInteractor {
    Observable<TranslateResponse> getTranslation(String... str);

    void addHistory(Translate t);

    void setFavorite(Translate t);

    void unFavorite(Translate t);

    boolean isFavorite(Translate t);

    String getHistory();

    Language getCurrentOriginalLang();

    Language getCurrentTranslateLang();

    void setOriginalLang(Language l);

    void setTranslateLang(Language l);

    void swapLang();

    Translate getLastTranslate();

    void setLastTranslate(Translate t);

    Language getLangByUi(String str);
}
