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
import android.widget.EditText;


public class CrimeFragment extends Fragment {

    //Declare a class leve variable for a crime.
    private Crime mCrime;


    private EditText mTitleField;

    //This method does not do the inflating of the view
    //like the onCreate for an activity does.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Create a new instance of a Crime class
        mCrime = new Crime();


    }




    //This method IS responsible for inflating the view
    //and getting the content on the screen.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //false  pass the "root"

        //make the layout that can be displayed
        View v = inflater.inflate(R.layout.fragment_crime, container, false);


        mTitleField = (EditText)v.findViewById(R.id.crime_title);
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


        return v;




    }
}
