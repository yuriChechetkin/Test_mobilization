package com.mobilization.main;

import com.mobilization.BaseApplication;
import com.mobilization.BuildConfig;
import com.mobilization.models.Translate;
import com.mobilization.models.TranslateResponse;

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

/**
 * Created by mac on 24.04.17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk=21, application = BaseApplication.class)
public class MainPresenterImplTest {

    @Mock
    private MainInteractor interactor;
    @Mock
    private MainView view;
    @Mock
    Throwable throwable;
    @Mock
    private TranslateResponse translateResponse;
    private String str = "hello";
    private MainPresenterImpl presenter;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenterImpl(interactor);
    }

    @After
    public void teardown()
    {
        presenter.destroy();
    }

    @Test
    public void shouldBeAbleToDisplayTranslation()
    {
        TestScheduler testScheduler = new TestScheduler();
        TestSubscriber<TranslateResponse> testSubscriber = new TestSubscriber<>();
        Observable<TranslateResponse> responseObservable = Observable.just(translateResponse).subscribeOn(testScheduler);
        responseObservable.subscribe(testSubscriber);
        when(interactor.getTranslation(str)).thenReturn(responseObservable);

        presenter.setView(view);
        testScheduler.triggerActions();

        testSubscriber.assertNoErrors();
        testSubscriber.onCompleted();
    }
}
