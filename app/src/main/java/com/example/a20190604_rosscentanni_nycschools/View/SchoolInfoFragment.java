package com.example.a20190604_rosscentanni_nycschools.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * Purpose: Display a given school's SAT scores
 */
//TODO: Setup proper back handling for this screen.
public class SchoolInfoFragment extends Fragment implements SchoolInfoView{

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_info, container, false);

        bindUI(view);

        SchoolInfoPresenter presenter = new SchoolInfoPresenter(this);


        //Query backend for school's SAT scores using dbKey in args.
        Bundle args = getArguments();
        if(args != null){
            presenter.getSATScores(args.getString("dbKey"));
        }

        return view;
    }

    /**
     * Binds this screen's textviews.
     * @param view the view
     */
    private void bindUI(View view) {
        mSchoolNameTV = view.findViewById(R.id.tv_info_school_name);
        mSATMathTV = view.findViewById(R.id.tv_avg_sat_math);
        mSATReadingTV = view.findViewById(R.id.tv_avg_sat_reading);
        mSATWritingTV = view.findViewById(R.id.tv_avg_sat_writing);
    }

    @Override
    public void displayErrorToast() {
        Toast.makeText(getContext(), R.string.service_request_failed, Toast.LENGTH_LONG).show();
    }

    @Override
    public void populateSATScores(ScoresPOJO scores) {
        mSchoolNameTV.setText(scores.getSchoolName());
        mSATMathTV.setText(scores.getMathSATScore());
        mSATWritingTV.setText(scores.getWritingSATScore());
        mSATReadingTV.setText(scores.getReadingSATScore());
    }
}
