package com.mobilization.languages;

/**
 * Created by mac on 23.04.17.
 */

public class SelectLanguagePresenterImpl implements SelectLanguagePresenter {

    private SelectLanguageView view;
    private SelectLanguageInteractor slInteractor;


    SelectLanguagePresenterImpl(SelectLanguageInteractor interactor) {
        slInteractor = interactor;
    }


    @Override
    public void displayLangsByUi(String ui) {
        view.showTranslateLangs(slInteractor.getDirsByUi(ui), slInteractor.getTranslateLang());
    }

    @Override
    public void displayAllLangs() {
        view.showOriginalLangs(slInteractor.getLangs(), slInteractor.getOriginalLang());
    }

    @Override
    public void setView(SelectLanguageView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
    }
}
