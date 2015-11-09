package edu.kvcc.cis298.inclass3.inclass3;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;


public class CrimeFragment extends Fragment {

    //static string to be used as the key for parameters we set and retrieve from the bundle
    private static final String ARG_CRIME_ID ="crime_id";


    //Declare a class level variable for a crime.
    private Crime mCrime;

    private EditText mTitleField;

    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    //This is a static method that is used to create a new instance of a CrimeFragment
    // with the correction information of a Crime based on a UUID that is passed in.
    //Any Activity including the one we are using (Crime Activity) can call this method
    //and get a properly created CrimeFragment. The method takes the UUID that is passed in,
    //and then sets it in an argument bundle that can be passed along with the Fragment.
    //Once the fragment is started, the data in the bundle can be retrieved and used.
    public static CrimeFragment newInstance(UUID crimeId){
        //Create a new arguments bundle
        Bundle args = new Bundle();
        //Put the UUID in as a value to the bundle.
        args.putSerializable(ARG_CRIME_ID, crimeId);

        //Create a new instance of this fragment
        CrimeFragment fragment = new CrimeFragment();
        //Set the arguments on the fragment with the bundle
        fragment.setArguments(args);
        //finally return the fragment that was created.
        return fragment;
    }


    //This method does not do the inflating of the view
    //like the onCreate for an activity does.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//**********************************************************************************
        //Create a new instance of a Crime class
        //  mCrime = new Crime();
//**********************************************************************************
        //Chap10

        //Get the UUID from the Extras for the activity.
        //Since UUID is not a simple type such as int, or double
        //It must be of a type that implements the serializable interface.
        //UUID does implement serializable, so it can be sent through as an Extra using serializable type
        //We didn't have to do anything with serializable. We just needed to know that UUID is serializable,
        //and so that is the method we used to store and retrieve it.
        //Since it arrives here as a serializable, it must be casted to a UUID.
        //That  is   the (UUID) part.
      //  UUID crimeId = (UUID) getActivity().getIntent().getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID);



        //Now that the Fragment is being started with any UUID passed
        //by the Bundle called savedInstanceState, we need to fetch
        //out the CrimeId from the bundle.
        //Get the UUID as a serializable, and then cast it to type UUID.
        UUID crimeId=(UUID)getArguments().getSerializable(ARG_CRIME_ID);


        //**********************************************************************************
        //Chap10

        //We want to get a specific crime from out Crime collection based on the UUID that we have.
        //In order to do that, we get the (singleton)instance of our crime lab by calling the static
        //method 'get' on CrimeLab.

        //CrimeLab lab = CrimeLab.get(getActivity());

        //Now that we have the crime lab, we can call the getCrime method on it passing in the UUID
        //to get back a single crime.

        //mCrime = lab.getCrime(crimeId);

        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
        //**********************************************************************************




    }




    //This method IS responsible for inflating the view
    //and getting the content on the screen.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //false  pass the "root"

        //make the layout that can be displayed
        View v = inflater.inflate(R.layout.fragment_crime, container, false);


        mTitleField = (EditText)v.findViewById(R.id.crime_title);

        //**********************************************************************************
        //Chap10
        mTitleField.setText(mCrime.getTitle());
        //**********************************************************************************

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                //This space intentionally left blank
            }
        });


        //Find the date button.
        mDateButton = (Button)v.findViewById(R.id.crime_date);
        //Set the text on the date button to the date from the crime model converted to a string.
        mDateButton.setText(mCrime.getDate().toString());
        //Disable the button so it doesn't do anything until we wire it up to do something.
        mDateButton.setEnabled(false);

        //Get a handle to the CheckBox
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        //Set the on Check Changed Listener. CheckBox is a subclass of the CompoundButton Class.
        //That is why we use that class to setup the new listener.

        //**********************************************************************************
        //Chap10
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        //**********************************************************************************

        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //Set the solved bool on the model to the result of the check changed event.
                //Set the crime's solved property
                mCrime.setSolved(isChecked);
            }
        });



        return v;


    }
}
