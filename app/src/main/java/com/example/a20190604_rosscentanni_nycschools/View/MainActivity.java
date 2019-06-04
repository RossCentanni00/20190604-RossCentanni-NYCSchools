package com.example.a20190604_rosscentanni_nycschools.View;

import android.Manifest;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.a20190604_rosscentanni_nycschools.R;

import static android.content.pm.PackageManager.*;

/**
 * Name: MainActivity
 * Purpose: Contains the School List and Info fragments, checks for necessary permissions.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() + "_TAG";
    private static final int REQUEST_INTERNET_PERMISSION = 333;
    /*
        LIFECYCLE METHODS
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Start by checking for permissions.
        if(appHasPermissions()){
            //If we have permission to use Internet, go ahead and load the school list fragment.
            openSchoolList();
        } else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    REQUEST_INTERNET_PERMISSION);
        }
    }

    /**
     * Callback for permission request
     * @param requestCode the request code used to make the request
     * @param permissions the permissions requested
     * @param grantResults the results of each request
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(requestCode == REQUEST_INTERNET_PERMISSION){
            //Since we only have one permission to worry about, we can hardcode indices for now
            if(grantResults.length > 0 && grantResults[0] == PERMISSION_GRANTED){
                //We can open school list fragment now.
                openSchoolList();
            } else {
                Log.d(TAG, "User denied permissions!");
                Toast.makeText(this, R.string.permission_denied_toast, Toast.LENGTH_LONG)
                        .show();
            }
        }
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
     * Loads the school info fragment.
     */
    /*
        private void openSchoolInfoFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SchoolInfoFragment())
                .commit();
    }
     */


    /**
     * Checks to see if application has permission to use Internet
     * @return true if it does, false if not
     */
    private boolean appHasPermissions() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) ==
                PERMISSION_GRANTED;
    }
}
