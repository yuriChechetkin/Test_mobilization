package com.mobilization.languages;


import com.mobilization.models.Language;
import com.mobilization.models.Translate;

import java.util.ArrayList;

/**
 * Created by mac on 23.04.17.
 */

public interface SelectLanguageView {

    void showOriginalLangs(ArrayList<Language> languages, Language currentOriginalLang);

    void showTranslateLangs(ArrayList<Language> languages, Language currentTranslateLang);

    void onLangClicked(Language language);
}
