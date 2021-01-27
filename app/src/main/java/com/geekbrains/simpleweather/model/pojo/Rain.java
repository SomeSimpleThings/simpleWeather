
package com.geekbrains.simpleweather.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rain implements Serializable
{

    @SerializedName("3h")
    @Expose
    private double volume;
    private final static long serialVersionUID = -1187878773553840702L;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

}
