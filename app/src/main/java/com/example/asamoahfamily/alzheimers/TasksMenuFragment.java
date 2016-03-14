package com.example.asamoahfamily.alzheimers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class TasksMenuFragment extends Fragment implements GlobalVariables{

    private static final String MENU = "TaskManager";
    private static final String FRAG = "FragmentManager";
    private static final int MED_TAG = 1;
    private static final int FOOD_TAG = 2;
    private static final int PHYS_TAG = 3;
    private static final int MENT_TAG = 4;
    private static final int OTHER = 5;

    private int menuIDCounter,fragIDCounter;
    private OnFragmentInteractionListener mFragListener;

    ThemeHandler tHandler;
    /*
    Order:
        Primary
        Dark
        Light
        Text Dark
        Text Light
        Secondary Text
     */
    private int buffer;
    private int menuButCounter,fragButCounter;
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

        int mLength = menuTable.getChildCount();
        int fLength = menuTable.getChildCount();

        for(int i = 1; i <=mLength; i++)
            for (int j = 1; j <= ((TableRow) menuTable.getChildAt(j)).getChildCount(); j++)
                outState.putString( "MENU_BUT " + i + " TEXT",
                        ((Button) ((TableLayout) menuTable.getChildAt(i)).getChildAt(j)).getText().toString()
                );

        for(int i = 1; i <= fLength; i++)
            for (int j = 1; j <= ((TableRow) fragTable.getChildAt(j)).getChildCount(); j++)
                outState.putString( "FRAG_BUT " + i + " TEXT",
                        ((Button) ((TableLayout) fragTable.getChildAt(i)).getChildAt(j)).getText().toString()
                );
        outState.putInt("MENU CHILD COUNT", mLength);
        outState.putInt("FRAG CHILD COUNT", fLength);
        mFragListener.SaveToActivity(outState);
        //getActivity().onSaveInstanceState(outState,);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenScale = dm.density;
        buffer =  Math.round(6*screenScale);

        rowLP = new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        tableLP = new TableLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT
        );
        rowLP.gravity = Gravity.CENTER;
        tableLP.setMargins(buffer/3,buffer/3,buffer/3,buffer/3);
        tableLP.gravity = Gravity.CENTER;

        rowLP.setMargins(buffer, buffer, buffer, buffer);
        tableLP.setMargins(buffer / 2, buffer / 2, buffer / 2, buffer / 2);
        fragButCounter = 0;
        menuButCounter = 0;

        return inflater.inflate(R.layout.fragment_tasks_menu, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        menuIDCounter = 0;
        fragIDCounter = 100;
        tHandler = new ThemeHandler();
        tHandler.setThemeCols(getContext());


        menuTable = (TableLayout) getActivity().findViewById(R.id.menuTable);
        fragTable = (TableLayout) getActivity().findViewById(R.id.fragTable);

        newBut(MEDICINE, FRAG);
        newBut(FOOD, FRAG);
        newBut(PHYSICAL_ACTIVITY, FRAG);
        newBut(MENTAL_ACTIVITY, FRAG);
        newBut("BREAKFAST", MENU);
        newBut("LUNCH", MENU);
        newBut("DINNER", MENU);
        newBut("VITAMINS", MENU);

    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void newRow(Button b, String type) {

        int i;
        TableRow nr;
        TableLayout table;
        switch (type){
            case FRAG:
                table = fragTable;
                i = fragButCounter;
                fragButCounter++;
                break;
            case MENU:
                table = menuTable;
                i = menuButCounter;
                menuButCounter++;
                break;
            default:
                i = 0;
                table = null;
        }

        if (table.getChildCount() == 0)
             nr = new TableRow(getContext());
        else {
            if (i%3==0) {
                nr = new TableRow(getContext());
            }
            else {
                nr = (TableRow) table.getChildAt(table.getChildCount() - 1);
                nr.addView(b);
                return;
            }
        }
        nr.setLayoutParams(rowLP);
        nr.addView(b);
        if (i%2== 0)
            nr.setBackgroundColor(tHandler.getmLight());
        if (i%2== 1)
            nr.setBackgroundColor(tHandler.getmDark());
        table.addView(nr);
    }

    public interface OnFragmentInteractionListener{
        void TaskMenuInteraction(Button b);
        Bundle SaveToActivity(Bundle b);

    }

    private void newBut(final String t, final String type) {

        int id;
        final Button newBut = new Button(getContext());
        newBut.setText(t);
        int key;

        switch (type) {
            case FRAG:
                id = fragIDCounter;
                fragIDCounter++;
                newBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        newBut(t, MENU);
                    }
                });
                break;
            case MENU:
                switch (t) {
                    case MEDICINE:
                        key = MED_TAG;
                        break;
                    case FOOD:
                        key = FOOD_TAG;
                        break;
                    case PHYSICAL_ACTIVITY:
                        key = PHYS_TAG;
                        break;
                    case MENTAL_ACTIVITY:
                        key = MENT_TAG;
                        break;
                    default:
                        key = OTHER;
                        break;
                }
                newBut.setTag(type + "_" + t + "_" + Integer.toString(key));
                id = menuIDCounter;
                menuIDCounter++;
                newBut.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragListener.TaskMenuInteraction((Button) v);
                    }
                });
                break;
            default:
                id = 0;
        }
        getActivity().getWindowManager().getDefaultDisplay().getSize(p);
        newBut.setMaxWidth(p.x / 4);
        newBut.setGravity(Gravity.CENTER);
        newBut.setLayoutParams(rowLP);
        newBut.setPadding(buffer / 4, buffer / 4, buffer / 4, buffer / 4);
        final int currentID = id;
        newBut.setId(currentID);
        newBut.setBackgroundColor(tHandler.getmPrime());
        newBut.setTextColor(tHandler.getmTextPrime());
        newRow(newBut, type);
    }
}
