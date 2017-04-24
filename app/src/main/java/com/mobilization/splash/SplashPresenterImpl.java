package com.mobilization.splash;

import com.mobilization.RxUtils;
import com.mobilization.main.MainInteractor;
import com.mobilization.main.MainView;
import com.mobilization.models.Language;
import com.mobilization.models.LanguagesResponse;
import com.mobilization.models.Translate;
import com.mobilization.models.TranslateResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by mac on 11.04.17.
 */

public class SplashPresenterImpl implements SplashPresenter {

    private SplashView view;
    private SplashInteractor splashInteractor;
    private Subscription subscription;


    SplashPresenterImpl(SplashInteractor interactor) {
        splashInteractor = interactor;
    }

    @Override
    public void setView(SplashView view) {
        this.view = view;
    }

    @Override
    public void destroy() {
        view = null;
        RxUtils.unsubscribe(subscription);
    }

    @Override
    public void displayLangs() {
        subscription = splashInteractor.getLangs()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onGetLangsSuccess, this::onGetLangsFailed);
    }

    private void onGetLangsSuccess(LanguagesResponse lr) {
        splashInteractor.saveLangs(lr);
        if (isViewAttached()) {
            view.launchMainScreen();
        }
    }


    private void onGetLangsFailed(Throwable e) {
        view.showError(e.getMessage());
    }

    private boolean isViewAttached() {
        return view != null;
    }


}
