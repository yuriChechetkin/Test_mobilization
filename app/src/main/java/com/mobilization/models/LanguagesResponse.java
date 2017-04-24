package com.mobilization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mac on 11.04.17.
 */

public class LanguagesResponse implements Serializable {

    @SerializedName("dirs")
    @Expose
    private ArrayList<String> dirs;

    @SerializedName("langs")
    @Expose
    private HashMap<String, String> langs;

    public ArrayList<String> getDirs() {
        return dirs;
    }

    public void setDirs(ArrayList<String> dirs) {
        this.dirs = dirs;
    }

    public Map<String, String> getLangs() {
        return langs;
    }

    public void setLangs(HashMap<String, String> langs) {
        this.langs = langs;
    }
}
