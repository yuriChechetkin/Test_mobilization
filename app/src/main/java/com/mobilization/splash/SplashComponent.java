package com.mobilization.splash;
import dagger.Subcomponent;

/**
 * Created by mac on 11.04.17.
 */

@SplashScope
@Subcomponent(modules = {SplashModule.class})
public interface SplashComponent {
    SplashActivity inject(SplashActivity activity);
}