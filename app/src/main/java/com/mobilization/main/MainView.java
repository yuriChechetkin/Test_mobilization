package com.mobilization.main;

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
}
