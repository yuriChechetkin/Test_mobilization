package com.mobilization.models;

import java.io.Serializable;

/**
 * Created by mac on 02.04.17.
 */

public class Language implements Serializable {

    private String ui;
    private String name;

    public Language() {

    }

    public Language(String name, String ui) {
        this.name = name;
        this.ui = ui;
    }

    public String getUi() {
        return ui;
    }

    public void setUi(String ui) {
        this.ui = ui;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
