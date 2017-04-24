package com.mobilization.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by mac on 02.04.17.
 */

public class Language implements Serializable {

    private String ui;
    private String name;
    private ArrayList<String> dirs = new ArrayList<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Language language = (Language) o;

        if(language.getName().equals(this.getName()) && language.getUi().equals(this.getUi()))
            return true;
        else
            return false;

    }
}
