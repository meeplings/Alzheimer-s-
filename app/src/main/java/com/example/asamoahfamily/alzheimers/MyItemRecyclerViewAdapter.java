package com.example.asamoahfamily.alzheimers;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asamoahfamily.alzheimers.ItemFragment.OnListFragmentInteractionListener;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<LinearLayout> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<LinearLayout> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final EditText mNameContent, mDoneView,mPrioView;
        public final TextView mNameView, mDoneContent, mPrioContent;
        public LinearLayout mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNameView = (TextView) view.findViewById(R.id.nameTask);
            mNameContent = (EditText) view.findViewById(R.id.contentTask);

            mDoneView = (EditText) view.findViewById(R.id.nameDone);
            mDoneContent = (TextView) view.findViewById(R.id.contentDone);

            mPrioView = (EditText) view.findViewById(R.id.namePrio);
            mPrioContent = (TextView) view.findViewById(R.id.contentPrio);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameContent.getText() + "'";
        }
    }
}
