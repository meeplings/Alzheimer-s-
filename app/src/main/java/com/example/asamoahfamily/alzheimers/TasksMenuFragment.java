package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TasksMenuFragment extends Fragment implements GlobalVariables{

    private int idCounter;
    private OnFragmentInteractionListener mFragListener;
    private int buffer;
    private int fragIDCounter;
    private TableRow.LayoutParams rowLP;
    private TableLayout.LayoutParams tableLP;
    private static final String TAG = "asamoahDebug";
    private TableLayout menuTable;  private TableLayout fragTable;
    float screenScale;


    public TasksMenuFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragListener = (OnFragmentInteractionListener) getContext();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState!=null){
            //TODO: Figure out how to store stuffs
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //TODO: Figure out how to store stuffs

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenScale = dm.density;
        buffer =  Math.round(6*screenScale);

        rowLP = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        tableLP = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT
        );

        rowLP.setMargins(buffer, buffer, buffer, buffer);

        return inflater.inflate(R.layout.fragment_tasks_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        idCounter = 0;
        fragIDCounter = 100;

        menuTable = (TableLayout) getActivity().findViewById(R.id.menuTable);
        fragTable = (TableLayout) getActivity().findViewById(R.id.fragTable);
        newMenuRow(null);
        newFragRow(null);
        newFragBut("FOOD");
//        addToFragment("PHYSICAL ACTIVITY");
//
//        addToFragment("MEDICINE");
//        addToFragment("MEMORY ACTIVITY ");
//        addToFragment("OTHER");
//        addToMenu("BREAKFAST");
//        addToMenu("LUNCH");
//        addToMenu("DINNER");
        newMenuBut("VITAMINS");
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private TableRow newMenuRow(Button b) {

        TableRow nr;

        if (menuTable.getChildCount() == 0)
             nr = new TableRow(getContext());
        else {
            nr = (TableRow) menuTable.getChildAt(menuTable.getChildCount() - 1);
            if (nr.getChildCount() == 3)
                 nr = new TableRow(getContext());
            else{
                nr.addView(b);
                return nr;
            }
        }
        nr.setLayoutParams(rowLP);
        if(b!= null) nr.addView(b);
        if (menuTable.getChildCount() % 2 == 0)
            nr.setBackgroundColor(getResources().getColor(R.color.Blue16));
        if (menuTable.getChildCount() % 2 == 1)
            nr.setBackgroundColor(getResources().getColor(R.color.Blue14));
        nr.setBackgroundColor(getResources().getColor(R.color.Blue16));
        return nr;
    }
    private TableRow newFragRow(Button b){

        TableRow nr;

        if (fragTable.getChildCount() == 0)
            nr = new TableRow(getContext());
        else {
            nr = (TableRow) fragTable.getChildAt(fragTable.getChildCount() - 1);
            if (nr.getChildCount() == 3) {
                nr = new TableRow(getContext());
            }
            else{
                nr.addView(b);
                return nr;
            }
        }
        nr.setLayoutParams(rowLP);
        if(b!= null)  nr.addView(b);
        if (fragTable.getChildCount() % 2 == 0)
            nr.setBackgroundColor(getResources().getColor(R.color.Blue6));
        if (fragTable.getChildCount() % 2 == 1)
            nr.setBackgroundColor(getResources().getColor(R.color.Blue4));
        return nr;
    }

    public interface OnFragmentInteractionListener{
          void OnFragmentInteraction(View v);
    }

    private void newFragBut(final String t){
        final Button fragBut = new Button(getContext());
        fragBut.setText(t);
        getActivity().getWindowManager().getDefaultDisplay().getSize(p);
        fragBut.setMaxWidth(p.x/3);
        fragBut.setGravity(View.FOCUS_LEFT);
        fragBut.setLayoutParams(rowLP);
        fragBut.setPadding(buffer, buffer, buffer, buffer);
        fragBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        final int currentFragID = fragIDCounter;    fragIDCounter++;
        fragBut.setId(currentFragID);
        if(currentFragID%2 == 0) {
            fragBut.setBackgroundResource(R.color.Blue4);
            fragBut.setTextColor(getResources().getColor(R.color.Blue12));
        }
        if(currentFragID%2 == 1) {
            fragBut.setBackgroundResource(R.color.Blue4);
            fragBut.setTextColor(getResources().getColor(R.color.Blue12));
        }
        fragTable.addView(newFragRow(fragBut));
    }

    private void newMenuBut(String t){
        final Button menuBut = new Button(getContext());
        menuBut.setText(t);
        menuBut.setTag(t);
        menuBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),TaskDescriptionFragment.class);
                i.putExtra("Button Task",menuBut.getTag().toString());
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(TasksMenuFragment.this);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        menuBut.setGravity(View.FOCUS_LEFT);
        menuBut.setMaxWidth(p.x/3);
        menuBut.setLayoutParams(rowLP);
        menuBut.setPadding(buffer, buffer, buffer, buffer);
        final int currentID = idCounter;
        idCounter++;
        if((currentID)%2 == 0) {
            menuBut.setBackgroundResource(R.color.Blue12);
            menuBut.setTextColor(getResources().getColor(R.color.Blue4));
        }
        if((currentID)%2 == 1) {
            menuBut.setBackgroundResource(R.color.Blue12);
            menuBut.setTextColor(getResources().getColor(R.color.Blue4));
        }
        menuBut.setId(currentID);
        menuTable.addView(newMenuRow(menuBut));
    }

}
