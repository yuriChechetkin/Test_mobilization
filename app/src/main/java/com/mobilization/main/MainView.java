package com.mobilization.main;

import com.mobilization.models.Language;
import com.mobilization.models.Translate;

import java.util.List;

/**
 * Created by mac on 02.04.17.
 */

public interface MainView {

    void showTranslation(Translate translate);

    void showFavorite();

    void showUnFavorite();

    void loadingStarted();

    void loadingFailed(String errorMessage);

    void selectOriginalLang();

    void selectTranslateLang(String currentLangUi);

    void setOriginalLang(String langName);

    void setTranslateLang(String langName);

    void setSwappedLangs(String originalLangName, String translateLangName);

    void setLastTranslate(Translate t);
}
