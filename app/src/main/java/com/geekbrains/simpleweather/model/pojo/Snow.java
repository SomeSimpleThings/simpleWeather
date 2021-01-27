
package com.geekbrains.simpleweather.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Snow implements Serializable {

    @SerializedName("3h")
    @Expose
    private double volume;
    private final static long serialVersionUID = 4579047848046178059L;

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

}
