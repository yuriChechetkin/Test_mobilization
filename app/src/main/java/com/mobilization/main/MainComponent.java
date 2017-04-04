package com.mobilization.main;

import dagger.Subcomponent;

/**
 * Created by mac on 02.04.17.
 */

@MainScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    MainFragment inject(MainFragment fragment);

}