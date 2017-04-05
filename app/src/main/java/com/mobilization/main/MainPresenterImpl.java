package com.mobilization.main;

import com.mobilization.RxUtils;
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
        //displayTranslation();
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
                //.observeOn(Schedulers.computation())
                .flatMap(new Func1<TranslateResponse, Observable<Translate>>() {
                    @Override
                    public Observable<Translate> call(TranslateResponse tr) {
                        tr.setCode(str[0]);
                        Translate t = new Translate(tr);
                        t.setOriginalText(str[0]);
                        return Observable.just(t);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTranslationFetchSuccess, this::onTranslationFetchFailed);
    }

    private void onTranslationFetchSuccess(Translate tr) {
        if (isViewAttached()) {
            view.showTranslation(tr);
            if(!tr.getTranslatedText().isEmpty())
                translationInteractor.addHistory(tr);
        }
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

    private void onTranslationFetchFailed(Throwable e) {
        view.loadingFailed(e.getMessage());
    }

    private boolean isViewAttached() {
        return view != null;
    }

}