package com.mobilization.splash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mobilization.BaseApplication;
import com.mobilization.MainActivity;
import com.mobilization.R;
import com.mobilization.models.Language;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by mac on 11.04.17.
 */

public class SplashActivity extends AppCompatActivity implements SplashView {


    @Inject
    SplashPresenter splashPresenter;

    @Inject
    Context c;

    @Inject
    Resources resources;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ((BaseApplication)getApplication()).createSplashComponent().inject(this);
        splashPresenter.setView(this);
        splashPresenter.displayLangs();
    }

    @Override
    public void launchMainScreen() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(c, error, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        splashPresenter.destroy();
        ((BaseApplication) getApplication()).releaseSplashComponent();
    }
}
