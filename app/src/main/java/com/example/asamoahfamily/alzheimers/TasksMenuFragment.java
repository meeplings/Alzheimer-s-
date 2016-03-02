package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.List;

public class TasksMenuFragment extends Fragment implements GlobalVariables{

    private int idCounter;
    private OnFragmentInteractionListener mFragListener;
    private int buffer;
    private ScrollView scroller;
    private ImageButton upBut,downBut;
    private int fragIDCounter;
    private List<TableRow> menuRows;
    private TableRow menuR1;
    private List<TableRow> fragRows;
    private TableRow fragR1;
    private TableRow fragR2;
    private TableRow.LayoutParams lp;
    private int xMenuCo;    private int yMenuCo;
    private int xFragCo = 0;    private int yFragCo = 0;
    private static final String TAG = "asamoahDebug";
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

        menuRows = new ArrayList<>();
        fragRows = new ArrayList<>();

        lp = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        lp.setMargins(buffer, buffer, buffer, buffer);

        return inflater.inflate(R.layout.fragment_tasks_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        xMenuCo = 0;    yMenuCo = 0;
        idCounter = 0;
        fragIDCounter = 100;

        menuR1 = (TableRow) getActivity().findViewById(R.id.menuRow1);
        menuRows.add(menuR1);
        fragR1 = (TableRow) getActivity().findViewById(R.id.fragRow1);
        fragR2 = (TableRow)getActivity().findViewById(R.id.fragRow2);
        fragRows.add(fragR1);
        fragRows.add(fragR2);

        scroller = (ScrollView) getActivity().findViewById(R.id.menuScroller);
        upBut = (ImageButton) getActivity().findViewById(R.id.upButton);
        upBut.setOnClickListener(
                new ImageButton.OnClickListener() {
                    public void onClick(View v) {
                        scroll(upBut);
                    }
                }
        );
        downBut = (ImageButton) getActivity().findViewById(R.id.downButton);
        downBut.setOnClickListener(
                new ImageButton.OnClickListener(){
                    public void onClick(View v){
                        scroll(downBut);
                    }
                }
        );

        Log.d(TAG, Integer.toString(xMenuCo));
        addToFragment("FOOD");
        addToFragment("PHYSICAL ACTIVITY");

        addToFragment("MEDICINE");
        addToFragment("MEMORY ACTIVITY ");
        addToFragment("OTHER");
        addToMenu("BREAKFAST");
        addToMenu("LUNCH");
        Log.d(TAG, Integer.toString(xMenuCo));
        addToMenu("DINNER");
        addToMenu("VITAMINS");
        Log.d(TAG, Integer.toString(xMenuCo));
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void incrementMenuGrid(){
        xMenuCo++;
        Log.d(TAG,"MENU X HAS GONE UP");
        if(xMenuCo >= 2){
            yMenuCo++;
            xMenuCo = 0;
            TableRow i = new TableRow(getContext());
            i.setLayoutParams(menuR1.getLayoutParams());
            menuRows.add(i);
        }
    }
    private void incrementFragGrid(){
        xFragCo++;
        Log.d(TAG,"FRAG X HAS GONE UP");
        if(xFragCo >= 2){
            yFragCo++;
            xFragCo = 0;
            TableRow j = new TableRow(getContext());
            j.setLayoutParams(fragR1.getLayoutParams());
            fragRows.add(j);
        }
    }

    public interface OnFragmentInteractionListener{
          void OnFragmentInteraction(View v);
    }

    private void addToFragment(final String t){
        final Button fragBut = new Button(getContext());
        fragBut.setText(t);
        getActivity().getWindowManager().getDefaultDisplay().getSize(p);
        fragBut.setMaxWidth(p.x/3);
        fragBut.setGravity(View.FOCUS_LEFT);
        fragBut.setLayoutParams(lp);
        fragBut.setPadding(buffer, buffer, buffer, buffer);
        fragBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToMenu(t);
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
        fragRows.get(yFragCo).addView(fragBut,xFragCo);
        incrementFragGrid();
    }

    private void addToMenu(String t){
        Button menuBut = new Button(getContext());
        menuBut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getActivity().openOptionsMenu();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(TasksMenuFragment.this);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        menuBut.setGravity(View.FOCUS_LEFT);
        menuBut.setMaxWidth(p.x/3);
        menuBut.setLayoutParams(lp);
        menuBut.setPadding(buffer, buffer, buffer, buffer);
        final int currentID = idCounter;
        idCounter++;
        if((xMenuCo+yMenuCo)%2 == 0) {
            menuBut.setBackgroundResource(R.color.Blue12);
            menuBut.setTextColor(getResources().getColor(R.color.Blue4));
        }
        if((xMenuCo+yMenuCo)%2 == 1) {
            menuBut.setBackgroundResource(R.color.Blue12);
            menuBut.setTextColor(getResources().getColor(R.color.Blue4));
        }
        menuBut.setId(currentID);
        menuBut.setText(t);
        menuRows.get(yMenuCo).addView(menuBut,xMenuCo);
        incrementMenuGrid();
    }

    public void scroll(View v){
        int s = p.y/4;

        int id = v.getId();
        if(id == upBut.getId())
            scroller.arrowScroll(s);
        else if(id == downBut.getId())
            scroller.arrowScroll(-s);
    }
}
