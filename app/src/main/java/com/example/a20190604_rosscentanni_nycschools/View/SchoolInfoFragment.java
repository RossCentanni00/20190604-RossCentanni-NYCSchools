package com.example.a20190604_rosscentanni_nycschools.View;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a20190604_rosscentanni_nycschools.R;

public class SchoolInfoFragment extends Fragment {

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

        return view;
    }
}
