package com.geekbrains.simpleweather.model;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.geekbrains.simpleweather.R;

import java.util.ArrayList;
import java.util.List;

public class DaysViewModel extends AndroidViewModel {

    List<String> days = new ArrayList<>();
    private MutableLiveData<List<String>> daysLiveData;

    public DaysViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<String>> getDays() {
        if (daysLiveData == null) {
            daysLiveData = new MutableLiveData<>();
            loadDays();
        }
        return daysLiveData;
    }

    private void loadDays() {
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {
            populate();
            daysLiveData.setValue(days);
        }, 100);
    }

    private void populate() {
        Context context = getApplication().getApplicationContext();
        days.add(context.getString(R.string.monday));
        days.add(context.getString(R.string.tuesday));
        days.add(context.getString(R.string.wednesday));
        days.add(context.getString(R.string.thursday));
        days.add(context.getString(R.string.friday));
        days.add(context.getString(R.string.saturday));
        days.add(context.getString(R.string.sunday));
    }
}
