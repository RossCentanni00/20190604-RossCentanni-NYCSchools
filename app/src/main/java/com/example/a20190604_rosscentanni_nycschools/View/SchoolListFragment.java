package com.example.a20190604_rosscentanni_nycschools.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;
import com.example.a20190604_rosscentanni_nycschools.Presenter.SchoolListPresenter;
import com.example.a20190604_rosscentanni_nycschools.R;

import java.util.List;

/**
 * Name: SchoolListFragment
 * Purpose: Displays a list of NYC schools in a recyclerview.
 */
public class SchoolListFragment extends Fragment implements SchoolListView {

    private SchoolListPresenter mSchoolListPresenter;
    private RecyclerView mSchoolList;

    public SchoolListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_list, container, false);

        //Create the presenter
        mSchoolListPresenter = new SchoolListPresenter(this);

        //Bind the RecyclerView
        mSchoolList = view.findViewById(R.id.recycler_school_list);

        //Start downloading list of schools
        //TODO: Add progress bar for this.
        mSchoolListPresenter.getListOfSchools();

        return view;
    }

    /**
     * Populates the recyclerview with a list of NYC schools
     * @param schoolList a list of SchoolPOJO objects
     */
    @Override
    public void populateListOfSchools(List<SchoolPOJO> schoolList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mSchoolList.setLayoutManager(layoutManager);
        mSchoolList.setAdapter(new SchoolListAdapter(schoolList));
    }

    /**
     * Displays an error message if service call fails.
     */
    @Override
    public void displayErrorToast() {
        Toast.makeText(getContext(), R.string.service_request_failed, Toast.LENGTH_LONG).show();
    }
}
