package com.mobilization.languages;

import com.mobilization.models.Language;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 23.04.17.
 */

public interface SelectLanguageInteractor {

    void setOriginalLang(Language l);

    Language getOriginalLang();

    void setTranslateLang(Language l);

    Language getTranslateLang();

    ArrayList<Language> getDirsByUi(String fromUi);

    ArrayList<Language> getLangs();

}
