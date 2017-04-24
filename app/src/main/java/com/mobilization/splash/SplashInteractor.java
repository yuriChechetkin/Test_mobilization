package com.mobilization.splash;

import com.mobilization.models.Language;
import com.mobilization.models.LanguagesResponse;
import com.mobilization.models.TranslateResponse;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by mac on 11.04.17.
 */

public interface SplashInteractor {

    Observable<LanguagesResponse> getLangs();

    void saveLangs(LanguagesResponse lr);

    ArrayList<Language> getDirsByUi(String fromUi);

}
