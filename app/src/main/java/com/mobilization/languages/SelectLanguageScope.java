package com.mobilization.languages;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by mac on 23.04.17.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SelectLanguageScope
{
}