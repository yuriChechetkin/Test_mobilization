package com.mobilization.languages;

import com.mobilization.models.Language;
import com.mobilization.storeModule.StoreInteractor;

import java.util.ArrayList;

/**
 * Created by mac on 23.04.17.
 */

public class SelectLanguageInteractorImpl implements SelectLanguageInteractor {

    private StoreInteractor storeInteractor;


    SelectLanguageInteractorImpl(StoreInteractor storeInteractor) {
        this.storeInteractor = storeInteractor;
    }


    @Override
    public void setOriginalLang(Language l) {
        storeInteractor.setCurrentOriginalLang(l);
    }

    @Override
    public Language getOriginalLang() {
        return storeInteractor.getCurrentOriginalLang();
    }

    @Override
    public void setTranslateLang(Language l) {
        storeInteractor.setCurrentTranslateLang(l);
    }

    @Override
    public Language getTranslateLang() {
        return storeInteractor.getCurrentTranslateLang();
    }

    @Override
    public ArrayList<Language> getDirsByUi(String fromUi) {
        ArrayList<Language> dirs = new ArrayList<>();
        dirs = storeInteractor.getDirsByUi(fromUi);
        return dirs;
    }

    @Override
    public ArrayList<Language> getLangs() {
        return storeInteractor.getLangs();
    }
}
