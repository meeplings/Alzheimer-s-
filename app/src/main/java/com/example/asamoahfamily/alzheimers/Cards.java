package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

public class Cards  extends Button{

    private boolean visible;
    private boolean solved;
    private Drawable mImage;

    public Cards(Context context,Drawable mImage) {
        super(context);
        this.solved = false;
        this.visible = false;
        this.mImage = mImage;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isSolved() {
        return solved;
    }

    public Drawable getmImage() {
        return mImage;
    }

    public void swapVisible(){
        visible = !visible;
    }

    public void setSolved(boolean solved){
        this.solved = solved;
    }

    public void setmImage(int id){
        mImage = ContextCompat.getDrawable(super.getContext(),id);
    }
}
