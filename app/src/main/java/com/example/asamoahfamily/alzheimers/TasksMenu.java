package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

public class TasksMenu extends Fragment implements GlobalVariables{

    private int idCounter;
    int buffer;private int fragIDCounter;
    private TableRow[] menuRows;
    private TableRow[] fragRows;
    private TableRow.LayoutParams lp;
    private int xMenuCo;    private int yMenuCo;
    private int xFragCo = 0;    private int yFragCo = 0;
    private static final String TAG = "asamoahDebug";
    float screenScale;


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

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenScale = dm.density;
        buffer =  Math.round(6*screenScale);

        lp = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lp.setMargins(buffer,buffer,buffer,buffer);
        menuRows = new TableRow[4];
        fragRows = new TableRow[4];

        return inflater.inflate(R.layout.fragment_tasks_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        xMenuCo = 0;    yMenuCo = 0;
        idCounter = 0;
        fragIDCounter = 100;

        for(int i = 0; i < menuRows.length; i++ ){
            menuRows[i] = (TableRow) getActivity().findViewById(R.id.menuRow1+i);
            menuRows[i].setLayoutParams(lp);
            menuRows[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
            menuRows[i].setVerticalGravity(Gravity.CENTER_VERTICAL);
            fragRows[i] = (TableRow) getActivity().findViewById(R.id.fragRow1+i);
            fragRows[i].setLayoutParams(lp);
            fragRows[i].setHorizontalGravity(Gravity.CENTER_HORIZONTAL);
            fragRows[i].setVerticalGravity(Gravity.CENTER_VERTICAL);
        }

        addToFragment("FOOD");
        addToFragment("MEDICINE");
        addToFragment("Memmory Game");
        addToFragment("Physical Activity");
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private Button newButton(final String t){
        final Button fragBut = new Button(getContext());
        fragBut.setText(t);
        fragBut.setMaxWidth((int) (50*screenScale));
        fragBut.setGravity(View.FOCUS_LEFT);
        fragBut.setLayoutParams(lp);
        fragBut.setPadding(buffer, buffer, buffer, buffer);
        fragBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button menuBut = new Button(getContext());
                menuBut.setGravity(View.FOCUS_LEFT);
                menuBut.setMaxWidth((int) (50*screenScale));
                menuBut.setLayoutParams(lp);
                menuBut.setPadding(buffer,buffer,buffer,buffer);
                final int currentID = idCounter;
                idCounter++;
                if((xMenuCo+yMenuCo)%2 == 0) {
                    menuBut.setBackgroundResource(R.color.purpleDark);
                    menuBut.setTextColor(getResources().getColor(R.color.greenDark));
                }
                if((xMenuCo+yMenuCo)%2 == 1) {
                    menuBut.setBackgroundResource(R.color.purpleLight);
                    menuBut.setTextColor(getResources().getColor(R.color.greenMid));
                }
                menuBut.setId(currentID);
                menuBut.setText(t);
                menuRows[yMenuCo].addView(menuBut, xMenuCo);
                incrementMenuGrid();
            }
        });
        final int currentFragID = fragIDCounter;    fragIDCounter++;
        fragBut.setId(currentFragID);
        if(currentFragID%2 == 0) {
            fragBut.setBackgroundResource(R.color.yellowDark);
            fragBut.setTextColor(getResources().getColor(R.color.purpleLight));
        }
        if(currentFragID%2 == 1) {
            fragBut.setBackgroundResource(R.color.yellowLight);
            fragBut.setTextColor(getResources().getColor(R.color.purpleDark));

        }
        return fragBut;
    }
    private void incrementMenuGrid(){
        xMenuCo++;
        if(xMenuCo >= 3){
            yMenuCo++;
            xMenuCo = 0;
        }
    }
    private void incrementFragGrid(){
        xFragCo++;
        if(xFragCo >= 3){
            yFragCo++;
            xFragCo = 0;
        }
    }

    public interface OnFragmentInteractionListener{
          void OnFragmentInteraction(Uri uri);
    }
    private void addToFragment(String s){
        fragRows[yFragCo].addView(newButton(s),xFragCo);
        incrementFragGrid();
    }

}
