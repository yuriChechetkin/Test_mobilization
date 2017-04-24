package com.mobilization.languages;
import dagger.Subcomponent;

/**
 * Created by mac on 23.04.17.
 */

@SelectLanguageScope
@Subcomponent(modules = {SelectLanguageModule.class})
public interface SelectLanguageComponent {
    SelectLanguageActivity inject(SelectLanguageActivity activity);
}