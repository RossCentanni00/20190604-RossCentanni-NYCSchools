package com.example.a20190604_rosscentanni_nycschools.View;

import android.Manifest;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.a20190604_rosscentanni_nycschools.R;
import com.example.a20190604_rosscentanni_nycschools.View.SchoolInfo.SchoolInfoFragment;
import com.example.a20190604_rosscentanni_nycschools.View.SchoolList.SchoolListFragment;

import static android.content.pm.PackageManager.*;

/**
 * Name: MainActivity
 * Purpose: Contains the School List and Info fragments, checks for necessary permissions.
 */
public class MainActivity extends AppCompatActivity implements SchoolListFragment.OnSchoolClickedListener {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final int REQUEST_INTERNET_PERMISSION = 333;
    private static final String WAS_CONFIGURATION_CHANGE = "config_change";

    /*
        LIFECYCLE/SYSTEM METHODS
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean shouldSkipOnCreate = false;
        if(savedInstanceState != null){
            shouldSkipOnCreate = savedInstanceState.getBoolean(WAS_CONFIGURATION_CHANGE);
        }

        if(!shouldSkipOnCreate){
            //Start by checking for permissions.
            if (appHasPermissions()) {
                //If we have permission to use Internet, go ahead and load the school list fragment.
                openSchoolList();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        REQUEST_INTERNET_PERMISSION);
            }
        }

    }

    /**
     * Callback for permission request
     *
     * @param requestCode  the request code used to make the request
     * @param permissions  the permissions requested
     * @param grantResults the results of each request
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_INTERNET_PERMISSION) {
            //Since we only have one permission to worry about, we can hardcode indices for now
            if (grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED) {
                //We can open school list fragment now.
                openSchoolList();
            } else {
                Log.d(TAG, "User denied permissions!");
                Toast.makeText(this, R.string.permission_denied_toast, Toast.LENGTH_LONG)
                        .show();
            }
        }
    }

    /**
     * Sets the callback for the SchoolListFragment
     *
     * @param fragment a fragment
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof SchoolListFragment) {
            SchoolListFragment schoolListFragment = (SchoolListFragment) fragment;
            schoolListFragment.setOnSchoolClickedListener(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(WAS_CONFIGURATION_CHANGE, true);
        super.onSaveInstanceState(outState);
    }

    /*
        PUBLIC METHODS
     */

    /**
     * After a school on the list is clicked, open its info screen
     *
     * @param dbKey the database key used to query SAT scores
     */
    @Override
    public void onSchoolSelected(String dbKey, String schoolName) {
        SchoolInfoFragment fragment = new SchoolInfoFragment();
        Bundle args = new Bundle();
        args.putString("dbKey", dbKey);
        args.putString("schoolName", schoolName);

        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    /*
        PRIVATE METHODS
     */

    /**
     * Loads the school list fragment.
     */
    private void openSchoolList() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new SchoolListFragment())
                .commit();
    }

    /**
     * Checks to see if application has permission to use Internet
     *
     * @return true if it does, false if not
     */
    private boolean appHasPermissions() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) ==
                PERMISSION_GRANTED;
    }
}
