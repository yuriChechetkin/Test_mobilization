package com.mobilization.main;

import com.mobilization.RxUtils;
import com.mobilization.models.Language;
import com.mobilization.models.Translate;
import com.mobilization.models.TranslateResponse;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 02.04.17.
 */

class MainPresenterImpl implements MainPresenter {
    private MainView view;
    private MainInteractor translationInteractor;
    private Subscription fetchSubscription;

    private static Subscriber subscriber;
    private static Subscription subscription;

    MainPresenterImpl(MainInteractor interactor) {
        translationInteractor = interactor;
    }

    @Override
    public void setView(MainView view) {
        this.view = view;
        try {
            Translate translate = translationInteractor.getLastTranslate();
            if (translationInteractor.isFavorite(translate))
                translate.setFavorite(true);
            if (translate == null) {
                view.setOriginalLang(translationInteractor.getCurrentOriginalLang().getName());
                view.setTranslateLang(translationInteractor.getCurrentTranslateLang().getName());
            } else {
                view.setLastTranslate(translate);
            }
        }catch (Exception e){}

    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(fetchSubscription);
    }

    @Override
    public void displayTranslation(String... str) {
        fetchSubscription = translationInteractor.getTranslation(str)
                .subscribeOn(Schedulers.io())
                .flatMap(translateResponse -> transform(translateResponse, str[0]))
                /*.flatMap(new Func1<TranslateResponse, Observable<Translate>>() {
                    @Override
                    public Observable<Translate> call(TranslateResponse tr) {
                        Translate t = new Translate();
                        t.setOriginalText(str[0]);
                        t.setTranslatedText(tr.getText().get(0));
                        t.setDirs(tr.getLang());
                        t.setOriginalLang(translationInteractor.getLangByUi(t.getDirs().split("-")[0]));
                        t.setTranlsateLang(translationInteractor.getLangByUi(t.getDirs().split("-")[1]));
                        translationInteractor.setLastTranslate(t);
                        return Observable.just(t);
                    }
                })*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTranslationFetchSuccess, this::onTranslationFetchFailed);
    }

    Observable<Translate> transform(TranslateResponse tr, String str){
        Translate t = new Translate();
        t.setOriginalText(str);
        t.setTranslatedText(tr.getText().get(0));
        t.setDirs(tr.getLang());
        t.setOriginalLang(translationInteractor.getLangByUi(t.getDirs().split("-")[0]));
        t.setTranlsateLang(translationInteractor.getLangByUi(t.getDirs().split("-")[1]));
        translationInteractor.setLastTranslate(t);
        return Observable.just(t);
    }

    private void onTranslationFetchSuccess(Translate tr) {
        if (isViewAttached()) {
            view.showTranslation(tr);
            if(!tr.getTranslatedText().isEmpty())
                translationInteractor.addHistory(tr);
        }
    }

    private void onTranslationFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
        translationInteractor.setLastTranslate(null);
    }

    @Override
    public void selectOriginalLang() {
        view.selectOriginalLang();
    }

    @Override
    public void selectTranslateLang() {
        view.selectTranslateLang(translationInteractor.getCurrentOriginalLang().getUi());
    }

    @Override
    public void swapLang() {
        translationInteractor.swapLang();
        view.setSwappedLangs(translationInteractor.getCurrentOriginalLang().getName(),
                translationInteractor.getCurrentTranslateLang().getName());
    }

    @Override
    public void setOriginalLang(Language lang) {
        translationInteractor.setOriginalLang(lang);
        view.setOriginalLang(lang.getName());
    }

    @Override
    public void setTranslateLang(Language lang) {
        translationInteractor.setTranslateLang(lang);
        view.setTranslateLang(lang.getName());
    }

    @Override
    public void addFavorite(Translate translate) {
        if (translationInteractor.isFavorite(translate)) {
            translationInteractor.unFavorite(translate);
            view.showUnFavorite();
        } else {
            translationInteractor.setFavorite(translate);
            view.showFavorite();
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

}