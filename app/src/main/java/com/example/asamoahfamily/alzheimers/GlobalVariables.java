package com.example.asamoahfamily.alzheimers;

import android.graphics.Point;
import android.util.DisplayMetrics;

/**
 * Created by Asamoah Family on 2/26/2016.
 */
public interface GlobalVariables {
    //TODO: should probably get rid of this for something else...

    DisplayMetrics dm = new DisplayMetrics();
    Point p = new Point();

    String MENU_TAG = "MENU_FRAG";
    String DIALOG_TAG = "DIALOG_TAG";
    String BUTTON_DATA = "BUTTON_DATA";

    String T_NAME = "THEME_NAME";

    String MEDICINE = "MEDICINE";
    String FOOD = "FOOD";
    String PHYSICAL_ACTIVITY = "PHYSICAL ACTIVITY";
    String MENTAL_ACTIVITY = "MENTAL ACTIVITY";
    String OTHER = "OTHER";
}
