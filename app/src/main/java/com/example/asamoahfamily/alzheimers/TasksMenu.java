package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class TasksMenu extends Fragment {



    private int idCounter;
    private static final int BUTTON_LENGTH = 250;
    private static final int BUTTON_HEIGHT = 150;
    private int fragIDCounter;
    private RelativeLayout fl;
    private RelativeLayout rl;
    private float xOffset;
    private float yOffset;

    private static final String TAG = "asamoahDebug";




    public TasksMenu() {
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        idCounter = 0;
        fragIDCounter = 100;
        resetOffset();
        fl = (RelativeLayout) container;
        return inflater.inflate(R.layout.fragment_tasks_menu, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        rl = (RelativeLayout) getActivity().findViewById(R.id.taskLayout);
        fl.addView(addButtons("BREAKFAST"));
        buttonOffset();
        fl.addView(addButtons("LUNCH"));
        buttonOffset();
        fl.addView(addButtons("DINNER"));
        buttonOffset();
        resetOffset();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private Button addButtons(String t){
        Button fragBut = new Button(getContext());
        fragBut.setX(xOffset);
        fragBut.setY(yOffset);
        fragBut.setText(t);
        final int currentFragID = fragIDCounter;
        fragIDCounter++;
        fragBut.setId(currentFragID);
        fragBut.setWidth(BUTTON_LENGTH);
        fragBut.setHeight(BUTTON_HEIGHT);
        fragBut.setBackgroundColor(Color.BLUE);
        return fragBut;
    }

    private void buttonOffset(){
        xOffset +=BUTTON_LENGTH;
        if(xOffset%3 == 0 && xOffset != 0){
            yOffset+=BUTTON_HEIGHT;
            xOffset = 0;
        }
    }
    private void resetOffset(){
        xOffset = 0;
        yOffset = 0;
    }

    public interface OnFragmentInteractionListener{
          void OnFragmentInteraction(Uri uri);
    }

}
