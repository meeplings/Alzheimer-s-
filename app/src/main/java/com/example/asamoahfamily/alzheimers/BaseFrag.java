package com.example.asamoahfamily.alzheimers;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaseFrag extends android.support.v4.app.Fragment {


    protected DisplayMetrics dm;
    protected Point p;
    protected float screenScale;

    protected static final String TAG = "asamoahDebug";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
