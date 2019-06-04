package com.example.a20190604_rosscentanni_nycschools.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;
import com.example.a20190604_rosscentanni_nycschools.R;

import org.w3c.dom.Text;

import java.util.List;

class SchoolListAdapter extends RecyclerView.Adapter<SchoolListAdapter.MyViewHolder> {

    private List<SchoolPOJO> mDataset;

    /**
     * Public constructor
     * @param schoolList a list of SchoolPOJO objects
     */
    SchoolListAdapter(List<SchoolPOJO> schoolList) {
        mDataset = schoolList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView schoolName;
        TextView schoolBorough;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.tv_school_name);
            schoolBorough = itemView.findViewById(R.id.tv_school_borough);

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

    /**
     * Gets the SchoolPOJO object associated with a position in the recyclerview
     * @param position an int position
     * @return a single SchoolPOJO object
     */
    public SchoolPOJO getItem(int position){
        return mDataset.get(position);
    }
}
