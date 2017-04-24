package com.mobilization.splash;

/**
 * Created by mac on 24.04.17.
 */

import com.mobilization.BaseApplication;
import com.mobilization.BuildConfig;
import com.mobilization.models.LanguagesResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21, application = BaseApplication.class)
public class SplashPresenterImplTest {

    @Mock
    private SplashInteractor interactor;
    @Mock
    private SplashView view;
    @Mock
    Throwable throwable;
    @Mock
    private LanguagesResponse languagesResponse;

    private SplashPresenterImpl presenter;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        presenter = new SplashPresenterImpl(interactor);
    }

    @After
    public void teardown()
    {
        presenter.destroy();
    }

    @Test
    public void shouldBeAbleToDisplayLangs()
    {
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<LanguagesResponse> testSubscriber = new TestSubscriber<>();
        Observable<LanguagesResponse> responseObservable = Observable.just(languagesResponse).subscribeOn(testScheduler);
        responseObservable.subscribe(testSubscriber);
        when(interactor.getLangs()).thenReturn(responseObservable);

        presenter.setView(view);
        testScheduler.triggerActions();

        testSubscriber.assertNoErrors();
        testSubscriber.onCompleted();
    }

}