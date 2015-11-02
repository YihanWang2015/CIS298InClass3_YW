package edu.kvcc.cis298.inclass3.inclass3;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by ywang4241 on 11/2/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {

    //This method is declared as abstract so that every activity
    //that we create must implement this method.
    //Instead of having every activity that we create inherit from FragmentActivity,
    //it will inherit from THIS class, which now provides some base functionality for us.
    //We will no longer need to duplicate this code in any child activity that we create.
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //P142 Getting the FragmentManager
        //Get a variable that represents the support version
        //of the fragment manager. If the Android OS version
        //of Fragment manager is imported, this statement will fail,
        //Must have support version imported.
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

        //P143 Adding a CrimeFragment
        //Use the fragment manager to get the fragment that is currently
        //in the frame that we created in the crimeFragment.xml file.
        //On the start of the app, this will be null since there
        //won't be anything there until we add it. Once something is
        //added, this method will return whatever is in it.
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //Check to see if the fragment is null. On starting the app,
        //it will be. But once we add a fragment to the frame, it will not be.
        if (fragment == null) {
            //Since it is empty, let's create a new stance of the CrimeFragment.
            fragment =  createFragment();
            //Now go through the work of adding it to the frame.
            //This is done by starting a transaction, then adding the
            //fragment we would like to attach, and then committing the changes.
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
