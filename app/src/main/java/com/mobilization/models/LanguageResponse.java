package com.mobilization.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mac on 11.04.17.
 */

public class LanguageResponse implements Serializable {

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("lang")
    @Expose
    private String lang;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
