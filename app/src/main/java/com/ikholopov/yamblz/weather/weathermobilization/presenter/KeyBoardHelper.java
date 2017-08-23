package com.ikholopov.yamblz.weather.weathermobilization.presenter;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.ikholopov.yamblz.weather.weathermobilization.ui.MainActivity;

import javax.inject.Inject;

/**
 * Created by turist on 23.08.2017.
 */

public class KeyBoardHelper {

    private final MainActivity activity;

    @Inject
    public KeyBoardHelper(MainActivity activity) {
        this.activity = activity;
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }

        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}