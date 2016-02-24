package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TasksMenu extends Fragment {



    private int idCounter;
    private static final int BUTTON_LENGTH = 250;
    private static final int BUTTON_HEIGHT = 150;
    private int fragIDCounter;
    private List<Button> taskList;
    private RelativeLayout fl;
    private RelativeLayout rl;
    private RelativeLayout.LayoutParams fragLP;
    private float xOffset;
    private float yOffset;
    public OpenActivity o;


    //Screen Density -- getWindowManager().getDefaultDisplay().getMetrics(dm);

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
    View v = inflater.inflate(R.layout.tasks_menu, container, false);
        idCounter = 0;
        fragIDCounter = 100;
        taskList = new ArrayList<>();
        resetOffset();
        fl = (RelativeLayout) v.findViewById(R.id.tasksMenu);
        fragLP = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        rl = (RelativeLayout) getActivity().findViewById(R.id.taskLayout);
        taskList = defaultButtonOptions();
        for(int i = 0; i < taskList.size(); i++){
            taskList.get(i).setX(xOffset);
            taskList.get(i).setY(yOffset);
            buttonOffset();
            fl.addView(taskList.get(i), fragLP);
        }
        resetOffset();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private Button addButtons(String t){

        Button fb = new Button(this.getContext());
        final int currentFragID = fragIDCounter; fragIDCounter++;
        fb.setText(t);
        fb.setId(currentFragID);
        fb.setOnClickListener(
                new Button.OnClickListener() {
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                            RelativeLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT
                    );

                    public void onClick(View v) {
                        Button pb = new Button(getContext());
                        pb.setText(((Button) v).getText());
                        pb.setWidth(100);
                        pb.setHeight(100);
                        final int currentId = idCounter;
                        pb.setId(currentId);
                        idCounter++;
                        pb.setBackgroundColor(0x548314);
                        pb.setLayoutParams(lp);
                        rl.addView(pb);
                        buttonOffset();
                        Log.d(TAG, Integer.toString(pb.getId()));
                    }
                }

        );
        resetOffset();
        return fb;

    }
    private List<Button> defaultButtonOptions(){
        taskList.add(addButtons("Breakfast"));
        taskList.add(addButtons("Lunch"));
        taskList.add(addButtons("Dinner"));
        taskList.add(addButtons("Medicine"));
        taskList.add(addButtons("PhysicalActivity"));
        return sortButtons();
    }

    public List<Button> sortButtons(){
        String[] sortCharacters = new String[taskList.size()];
        List<Button> sortedButtons = new ArrayList<>();
        for(int i = 0; i < taskList.size(); i++){
            sortCharacters[i] = taskList.get(i).toString();
        }
        Arrays.sort(sortCharacters);
        for(int i = 0; i < taskList.size(); i++){
            Button b = new Button(getContext());
            for(int j = 0; j < taskList.size(); j++){
                if(sortCharacters[j].equals(taskList.get(i).getText().toString()))
                    b = taskList.get(i);
            }
            sortedButtons.add(b);
        }
        return sortedButtons;
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
         public void OnFragmentInteraction(Uri uri);
    }

}
