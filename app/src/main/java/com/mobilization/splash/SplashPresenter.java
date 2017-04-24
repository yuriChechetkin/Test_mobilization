package com.mobilization.splash;

import com.mobilization.main.MainView;
import com.mobilization.models.Language;

import java.util.ArrayList;

/**
 * Created by mac on 11.04.17.
 */

public interface SplashPresenter {

    void displayLangs();

    void setView(SplashView view);

    void destroy();

}
