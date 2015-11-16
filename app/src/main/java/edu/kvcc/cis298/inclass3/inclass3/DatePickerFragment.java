package edu.kvcc.cis298.inclass3.inclass3;



import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ywang4241 on 11/16/2015.
 */
public class DatePickerFragment extends DialogFragment {

    //This is the key that is used to pass the date to the date
    //picker in a bundle object.
    private static final String ARG_DATE = "date";
    public static final String EXTRA_DATE = "edu.kvcc.cis298.inclass3.inclass3.date";

    //class level variable for the datePicker.
    private DatePicker mDatePicker;

    //Public static method that will return a properly formatted fragment that can be displayed.
    public static DatePickerFragment newInstance(Date date){
        //create a bundle that can be attached as data to the fragment
        Bundle argus = new Bundle();
        //add the date as a serializable to the bundle object
        argus.putSerializable(ARG_DATE, date);

        //create a new datePickerFragment object
        DatePickerFragment fragment = new DatePickerFragment();
        //attach the bundle that contains the data to the object
        fragment.setArguments(argus);
        //return the completed fragment
        return fragment;
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //Use the arguments to get the serialized date.
        //This date will come out of the arguments as a serializable interface type
        //and will therefore need to be cast to a Date object.
        Date date = (Date)getArguments().getSerializable(ARG_DATE);

        //Make a calendar object that can do the work of translating a date(Which is a timestamp)
        //into a format that is readable by humans(Year, month, day)
        Calendar calendar = Calendar.getInstance();
        //Set the time for calendar to be the date that was passed in
        calendar.setTime(date);

        //Pull out the parts we need from the calendar object
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);





        //Use the LayoutInflater to inflate the date dialog
        //layout that we would like to use inside of our dialog.
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);


        //get a reference to the date picker layout
        mDatePicker = (DatePicker)v.findViewById(R.id.dialog_date_date_picker);

        //Assign the calendar variables that we pulled out to the datePicker so that it has a default date.
        mDatePicker.init(year, month, day, null);

        //Return a new Dialog that is built from calling the  builder method,
        //and then subsequent chained methods.
        //Each chained method adds more information about the dialog to it.
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }


    private void sendResult(int resultCode, Date date){
        if(getTargetFragment() == null ){
            return;
        }

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }





}
