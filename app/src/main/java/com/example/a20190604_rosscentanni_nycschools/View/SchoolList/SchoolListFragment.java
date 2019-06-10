package com.example.a20190604_rosscentanni_nycschools.View.SchoolList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.a20190604_rosscentanni_nycschools.Model.SchoolPOJO;
import com.example.a20190604_rosscentanni_nycschools.Presenter.SchoolListPresenter;
import com.example.a20190604_rosscentanni_nycschools.R;

import java.util.List;

import static android.view.View.*;

/**
 * Name: SchoolListFragment
 * Purpose: Displays a list of NYC schools in a RecyclerView.
 */
public class SchoolListFragment extends Fragment implements SchoolListView, MyRecyclerViewClickListener {

    private RecyclerView mSchoolList;
    private ProgressBar mProgressBar;
    private OnSchoolClickedListener mCallback;

    private List<SchoolPOJO> mSchoolData;

    public SchoolListFragment() {
        // Required empty public constructor
    }

    /**
     * Interface for letting MainActivity know it's time to change fragments
     */
    public interface OnSchoolClickedListener {
        void onSchoolSelected(String dbKey, String schoolName);
    }

    /*
        LIFECYCLE METHODS
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_list, container, false);

        //Create the presenter
        SchoolListPresenter mSchoolListPresenter = new SchoolListPresenter(this);

        //Bind the RecyclerView
        mSchoolList = view.findViewById(R.id.recycler_school_list);
        mProgressBar = view.findViewById(R.id.pb_school_list_loading);

        //Start downloading list of schools. If already downloaded, just populate RecyclerView.
        if(mSchoolData == null){
            mSchoolListPresenter.getListOfSchools();
        } else{
            populateListOfSchools(mSchoolData);
        }


        return view;
    }

    /*
        PUBLIC METHODS
     */

    /**
     * Populates the RecyclerView with a list of NYC schools
     *
     * @param schoolList a list of SchoolPOJO objects
     */
    @Override
    public void populateListOfSchools(List<SchoolPOJO> schoolList) {
        //Retain the data for later, since it's not likely to change much.
        mSchoolData = schoolList;
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mSchoolList.setLayoutManager(layoutManager);
        mSchoolList.setAdapter(new SchoolListAdapter(this, schoolList));

        //Hide loading bar and show RecyclerView if not already visible
        mProgressBar.setVisibility(GONE);
        mSchoolList.setVisibility(VISIBLE);
    }

    /**
     * Displays an error message if service call fails.
     */
    @Override
    public void displayErrorToast() {
        Toast.makeText(getContext(), R.string.service_request_failed, Toast.LENGTH_LONG).show();
    }


    /**
     * Sets up school clicked listener for this fragment
     *
     * @param listener An instance of MainActivity
     */
    public void setOnSchoolClickedListener(OnSchoolClickedListener listener) {
        mCallback = listener;
    }

    /**
     * Gets the dbKey and name of a clicked school and passes it to MainActivity.
     *
     * @param dbKey the clicked school's internal id string
     * @param schoolName the clicked school's name
     */
    @Override
    public void onItemSelected(String dbKey, String schoolName) {
        mCallback.onSchoolSelected(dbKey, schoolName);
    }
}
