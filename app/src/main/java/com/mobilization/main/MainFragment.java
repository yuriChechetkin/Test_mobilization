package com.mobilization.main;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.mobilization.BaseApplication;
import com.mobilization.R;
import com.mobilization.models.Translate;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by mac on 02.04.17.
 */

public class MainFragment extends Fragment implements MainView, View.OnClickListener {

    @Inject
    MainPresenter mainPresenter;

    @Inject
    Context c;

    @Inject
    Resources resources;

    @BindView(R.id.etText)
    EditText etText;
    @BindView(R.id.tvRes)
    TextView tvRes;
    @BindView(R.id.tvTranslateLang)
    TextView tvTranslateLang;
    @BindView(R.id.tvOriginalLang)
    TextView tvOriginalLang;
    @BindView(R.id.btnSwapLang)
    ImageButton btnSwapLang;
    @BindView(R.id.btnClear)
    ImageButton btnClear;
    @BindView(R.id.btnMic)
    ImageButton btnMic;
    @BindView(R.id.btnSound)
    ImageButton btnSound;
    @BindView(R.id.btnSoundTranslate)
    ImageButton btnSoundTranslate;
    @BindView(R.id.btnFavorite)
    ImageButton btnFavorite;
    @BindView(R.id.btnShare)
    ImageButton btnShare;
    @BindView(R.id.btnFullScreen)
    ImageButton btnFullscreen;


    private Translate currentTranslate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
        ((BaseApplication) getActivity().getApplication()).createMainComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, rootView);
        btnClear.setOnClickListener(this);
        btnSound.setOnClickListener(this);
        btnMic.setOnClickListener(this);
        btnSoundTranslate.setOnClickListener(this);
        btnFavorite.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnFullscreen.setOnClickListener(this);

        RxTextView.textChanges(etText)
                //.filter(charSequence -> charSequence.length()>3)
                .debounce(750, TimeUnit.MILLISECONDS)
                .subscribe(this::onCompleteEnter);

        RxTextView.textChanges(tvRes)
                .subscribe(this::onCompleteTranslate);

        return rootView;
    }


    private void onCompleteTranslate(CharSequence charSequence) {
        if (charSequence.length() > 0)
            showControls();
        else
            hideControls();
    }

    public void onCompleteEnter(CharSequence charSequence) {
        onDetectEnter(charSequence);
        mainPresenter.displayTranslation(charSequence.toString());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainPresenter.setView(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnFavorite) {
            mainPresenter.addFavorite(currentTranslate);
        } else if (view.getId() == R.id.btnClear) {
            etText.setText("");
        }
    }

    @Override
    public void showFavorite() {
        btnFavorite.setColorFilter(resources.getColor(R.color.colorPrimary));
    }

    @Override
    public void showUnFavorite() {
        btnFavorite.setColorFilter(resources.getColor(R.color.colorDarkGray));
    }

    @Override
    public void showTranslation(Translate translate) {
        tvRes.setText(translate.getTranslatedText());
        currentTranslate = translate;
    }

    @Override
    public void loadingStarted() {

    }

    @Override
    public void loadingFailed(String errorMessage) {
        tvRes.setText("");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mainPresenter.destroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((BaseApplication) getActivity().getApplication()).releaseMainComponent();
    }

    private void hideControls() {
        btnSoundTranslate.animate().translationX(btnSoundTranslate.getWidth()).alpha(0.0f).setDuration(300);
        btnFavorite.animate().translationX(btnFavorite.getWidth()).alpha(0.0f).setDuration(300);
        btnShare.animate().translationX(btnShare.getWidth()).alpha(0.0f).setDuration(300);
        btnFullscreen.animate().translationX(btnFullscreen.getWidth()).alpha(0.0f).setDuration(300);
    }

    private void showControls() {
        btnSoundTranslate.animate().translationX(0).alpha(1.0f).setDuration(300);
        btnFavorite.animate().translationX(0).alpha(1.0f).setDuration(300);
        btnShare.animate().translationX(0).alpha(1.0f).setDuration(300);
        btnFullscreen.animate().translationX(0).alpha(1.0f).setDuration(300);
    }

    private void onDetectEnter(CharSequence charSequence) {
        if (charSequence.length() > 0) {
            btnClear.animate().translationX(0).alpha(1.0f).setDuration(300);
            btnSound.animate().translationX(0).alpha(1.0f).setDuration(300);
        } else {
            btnClear.animate().translationX(-btnClear.getWidth()).alpha(0.0f).setDuration(300);
            btnSound.animate().translationX(btnClear.getWidth()).alpha(0.0f).setDuration(300);
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showUnFavorite();
            }
        });
    }

}
