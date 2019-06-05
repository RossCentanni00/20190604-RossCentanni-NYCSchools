package com.example.a20190604_rosscentanni_nycschools.View.SchoolList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;
import com.example.a20190604_rosscentanni_nycschools.R;


import java.util.List;

/**
 * Name: SchoolListAdapter
 * Purpose: Adapter for the SchoolListFragment RecyclerView
 */
class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.MyViewHolder> {

    private List<SchoolPOJO> mDataset;
    private MyRecyclerViewClickListener mListener;

    /**
     * Public constructor
     *
     * @param schoolList a list of SchoolPOJO objects
     */
    SchoolListAdapter(MyRecyclerViewClickListener listener, List<SchoolPOJO> schoolList) {
        mListener = listener;
        mDataset = schoolList;
    }

    /*
        Viewholder, includes an onClick to pass data along when entry is clicked.
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView schoolName;
        TextView schoolBorough;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.tv_list_school_name);
            schoolBorough = itemView.findViewById(R.id.tv_list_school_borough);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            mListener.onItemSelected(mDataset.get(getAdapterPosition()).getId(),
                    mDataset.get(getAdapterPosition()).getName());
        }
    }

    /*
        Boilerplate adapter code
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v = inflater.inflate(R.layout.item_school_list, viewGroup, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.schoolName.setText(mDataset.get(i).getName());
        myViewHolder.schoolBorough.setText(mDataset.get(i).getBorough());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
