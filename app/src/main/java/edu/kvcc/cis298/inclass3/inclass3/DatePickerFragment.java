package edu.kvcc.cis298.inclass3.inclass3;



import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
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
import java.util.GregorianCalendar;

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

                //.setPositiveButton(android.R.string.ok, null)
                .setPositiveButton(android.R.string.ok,

                        //Make a new onClickListener for the positive button
                        new DialogInterface.OnClickListener() {

                            //Setup the onClick method
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //Get each part of the date out of the date picker
                                int year = mDatePicker.getYear();
                                int month = mDatePicker.getMonth();
                                int day = mDatePicker.getDayOfMonth();


                                //Create a new date to send to the sendResult method.
                                //In order to do this, we need to use the GregorianCalendar class
                                //to create a GregoricanCalendar. With the calendar created, we
                                //can call the getTime method on it so that a timestamp is returned.
                                //The date object is expecting a timestamp, not a year, month, date or
                                //Gregorican Calendar object.
                                Date date = new GregorianCalendar(year, month, day).getTime();

                                //Date the date we just made and send it along with a Result OK
                                //to the sendResult method created at the bottom of this file.
                                //SendResult will explicitly call onActivityResult with this information
                                //for the activity that is the target of this dialog.
                                sendResult(Activity.RESULT_OK, date);

                            }
                        })

                .create();
    }


    private void sendResult(int resultCode, Date date){

        //The target fragment was set over in the CrimeFragment class when we wired up the date button to
        //start this dialog fragment. Because it was set over there before starting this fragment,
        //this decision below should always fail. But just in case, we are putting in a check.
        if(getTargetFragment() == null ){
            return;
        }

        //Since we now know that there IS in fact a target fragment,
        //we can use that fragment to do work.

        //start by making an intent to hold the data that we want to return
        Intent intent = new Intent();
        //Add the date we want to send back as an extra with the putExtra on the intent object.
        intent.putExtra(EXTRA_DATE, date);

        //Use the getTargetFragment to get the destination of the return from this fragment,
        //and explicitly call onActivityResult method.
        //Pass over the target request code that was send when the target fragment was set,
        //the result code  that got passed into this function and the intent that has the data we just put together.
        //
        //Remember that when an activity returns on its own, onActivityResult is called implicitly by
        //the android OS. Since we are not returning from an activity we have to do the work of calling that method explicitly.
        getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
    }





}
