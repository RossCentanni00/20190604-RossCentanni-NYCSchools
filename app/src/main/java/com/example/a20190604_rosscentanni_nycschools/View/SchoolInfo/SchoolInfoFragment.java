package com.example.a20190604_rosscentanni_nycschools.View.SchoolInfo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a20190604_rosscentanni_nycschools.Model.ScoresPOJO;
import com.example.a20190604_rosscentanni_nycschools.Presenter.SchoolInfoPresenter;
import com.example.a20190604_rosscentanni_nycschools.R;

/**
 * Name: SchoolInfoFragment
 * Purpose: Displays a given school's name and SAT scores
 */
//TODO: Setup proper back handling for this screen.
public class SchoolInfoFragment extends Fragment implements SchoolInfoView {

    private TextView mSchoolNameTV;
    private TextView mSATMathTV;
    private TextView mSATReadingTV;
    private TextView mSATWritingTV;

    public SchoolInfoFragment() {
        // Required empty public constructor
    }

    /*
        LIFECYCLE METHODS
     */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_info, container, false);

        bindUI(view);

        SchoolInfoPresenter presenter = new SchoolInfoPresenter(this);


        //Query backend for school's SAT scores using dbKey in args.
        Bundle args = getArguments();
        if (args != null) {
            mSchoolNameTV.setText(args.getString("schoolName"));
            //TODO Set up a progress bar for this service call.
            presenter.getSATScores(args.getString("dbKey"));
        }

        return view;
    }

    /*
        PUBLIC METHODS
     */

    /**
     * If the service request fails, this method displays an error toast.
     */
    @Override
    public void displayErrorToast() {
        Toast.makeText(getContext(), R.string.service_request_failed, Toast.LENGTH_LONG).show();
    }

    /**
     * Populates the fragment's TextViews with the SAT score info received from the presenter
     *
     * @param scores a ScoresPOJO object, which will always be populated
     */
    @Override
    public void populateSATScores(ScoresPOJO scores) {
        mSATMathTV.setText(scores.getMathSATScore());
        mSATWritingTV.setText(scores.getWritingSATScore());
        mSATReadingTV.setText(scores.getReadingSATScore());
    }

    /*
        PRIVATE METHODS
     */

    /**
     * Binds this fragment's TextViews.
     *
     * @param view the fragment
     */
    private void bindUI(View view) {
        mSchoolNameTV = view.findViewById(R.id.tv_info_school_name);
        mSATMathTV = view.findViewById(R.id.tv_avg_sat_math);
        mSATReadingTV = view.findViewById(R.id.tv_avg_sat_reading);
        mSATWritingTV = view.findViewById(R.id.tv_avg_sat_writing);
    }
}
