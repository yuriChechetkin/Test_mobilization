package com.mobilization.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
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
import com.mobilization.DetailTextActivity;
import com.mobilization.R;
import com.mobilization.languages.SelectLanguageActivity;
import com.mobilization.models.Language;
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
    private final static int ORIGINAL_LANG_REQUEST_CODE = 100;
    private final static int TRANSLATE_LANG_REQUEST_CODE = 101;

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
        tvOriginalLang.setOnClickListener(this);
        tvTranslateLang.setOnClickListener(this);
        btnSwapLang.setOnClickListener(this);

        mainPresenter.setView(this);

        RxTextView.textChanges(etText)
                .skip(1)
                //.filter(charSequence -> charSequence.length()>0)
                //.filter(charSequence -> !charSequence.toString().trim().equals(currentTranslate.getOriginalText().trim()))
                .debounce(750, TimeUnit.MILLISECONDS)
                .subscribe(this::onCompleteEnter);

        RxTextView.textChanges(tvRes)
                //.skip(1)
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
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnFavorite:
                mainPresenter.addFavorite(currentTranslate);
                break;
            case R.id.btnClear:
                etText.setText("");
                break;
            case R.id.tvOriginalLang:
                mainPresenter.selectOriginalLang();
                break;
            case R.id.tvTranslateLang:
                mainPresenter.selectTranslateLang();
                break;
            case R.id.btnSwapLang:
                mainPresenter.swapLang();
                break;
            case R.id.btnShare:
                share();
                break;
            case R.id.btnFullScreen:
                fullScreen();
            default:
                break;
        }
    }

    @Override
    public void setLastTranslate(Translate t) {
        currentTranslate = t;
        tvOriginalLang.setText(t.getOriginalLang().getName());
        tvTranslateLang.setText(t.getTranlsateLang().getName());
        etText.setText(t.getOriginalText());
        tvRes.setText(t.getTranslatedText());
        if(t.isFavorite())
            showFavorite();
    }

    @Override
    public void selectOriginalLang() {
        startActivityForResult(new Intent(getActivity(), SelectLanguageActivity.class), ORIGINAL_LANG_REQUEST_CODE);
    }

    @Override
    public void selectTranslateLang(String langUi) {
        startActivityForResult(new Intent(getActivity(), SelectLanguageActivity.class).putExtra("lang", langUi), TRANSLATE_LANG_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK) {
            if (requestCode == ORIGINAL_LANG_REQUEST_CODE) {
                Language newLang = (Language) data.getSerializableExtra("newLang");
                mainPresenter.setOriginalLang(newLang);
            } else if (requestCode == TRANSLATE_LANG_REQUEST_CODE) {
                Language newLang = (Language) data.getSerializableExtra("newLang");
                mainPresenter.setTranslateLang(newLang);
            }
        }
    }

    @Override
    public void setOriginalLang(String langName) {
        tvOriginalLang.setText(langName);
        mainPresenter.displayTranslation(etText.getText().toString());
    }

    @Override
    public void setTranslateLang(String langName) {
        tvTranslateLang.setText(langName);
        mainPresenter.displayTranslation(etText.getText().toString());
    }

    @Override
    public void setSwappedLangs(String originalLangName, String translateLangName) {
        tvOriginalLang.setText(originalLangName);
        tvTranslateLang.setText(translateLangName);
        etText.setText(tvRes.getText().toString());
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

    public void share(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND).setType("text/plain");
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "\n\n");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, tvRes.getText().toString());
        c.startActivity(Intent.createChooser(sharingIntent,  "Отправить перевод с помощью"));
    }

    public void fullScreen(){
        Intent intent = new Intent(getActivity(), DetailTextActivity.class).putExtra("text", tvRes.getText().toString());
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), (View)tvRes, "profile");
        startActivity(intent, options.toBundle());
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
