package edu.kvcc.cis298.inclass3.inclass3;

import android.content.Context;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by ywang4241 on 10/28/2015.
 */
public class CrimeLab {

    //Static variable to hold the instance of CrimeLab.
    //Rather than returning a new instance of CrimeLab,
    //We will return this variable that holds our instances.
    private static CrimeLab sCrimeLab;

    //Generic type: Crime  -> a list of crimes
    //A variable to TYPE List, which is an interface, to hold a list of TYPE Crime.
    private List<Crime> mCrimes;

//CSV -> *********************
    //Make a class level variable to hold the context that is passed into the constructor.
    //We will need access to the context in order to read in the data file.
    private Context mContext;

//CSV <- *********************

    //This is the method that will be used to get an instance of CrimeLab.
    //It will check to see if the current instance in the variable is null,
    //and if it is, it will create a new one using the private constructor.
    //If it is NOT null, it will just return the instance that exists.
    public static CrimeLab get(Context context){
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    //This is the constructor. It is PRIVATE rather than public.
    //It is private because we don't want people to be able to
    //create a new instance from outside classes. If they want
    //to make an instance, we want them to use the get method
    //declared right above here.
    private CrimeLab(Context context){

        //Instantiate a new ArrayList, which is a child class that
        //implements the Interface List. Because ArrayList is a child
        //of List, we can store it in the mCrimes variable which is of
        // type List, and not ArrayList. (Polymorphism)

        mCrimes = new ArrayList<>();


        //CSV -> *********************
            //Assign the passed in context to the class level one.
        mContext = context;

        //CSV <- *********************


        //CSV -> **************************************
        //For loop to populate our ArrayList with some dummy data.
 /*       for (int i = 0; i<100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }*/


        this.loadCrimeList();
        //CSV <- **************************************
    }

    //Getter to get the crimes
    public List<Crime> getCrimes(){
        return mCrimes;
    }

    //Method to get a specific crime based on the UUID that is passed in.
    public Crime getCrime(UUID id){

        //This is a foreach loop to go through all of the crimes
        //at each iteration the current crime will be called 'crime'.
        for (Crime crime: mCrimes){

            //If we find a match, return it.
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        //No match, return null.
        return null;
    }



//CSV -> ***************************************
    private void loadCrimeList(){

        //Create a new scanner which will be used to get the data from the file
        Scanner scanner = null ;

        //Try to do the work of parsing it
        try{
            //Create a new scanner that opens up the file in the raw directory called crimes.
            scanner = new Scanner(mContext.getResources().openRawResource(R.raw.crimes));

            //While the scanner recognizes that there is a new line in the file
            while(scanner.hasNextLine()) {

                //Get the next line and assign it to a string
                String line = scanner.nextLine();

                //Split the line apart by the comma into an array
                String parts[] = line.split(",");

                //Assign each part to a variable that is more meaningful.
                //This step can be skipped if you want to use the parts directly
                String id = parts[0];
                String title = parts[1];
                String dateString = parts[2];
                String solved = parts[3];

                //Parse the UUID String into an actual UUID
                UUID uuid = UUID.fromString(id);

                //Create a date formatter to use in formatting the date from a string
                //into a Date object.
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date = df.parse(dateString);


                //Parse the solved string to see if isSolved should be true or false
                boolean isSolved;

                if (solved.equals("T")) {
                    isSolved = true;
                } else {
                    isSolved = false;
                }

                //Add a new crime to the mCrimes list using a 4 parameter constructor
                //that we added to the Crime class.
                mCrimes.add(new Crime(uuid, title, date, isSolved));
            }
        }
        catch (Exception e)
        {
            //Log the error to the Logcat
            Log.e("Read CSV", e.toString());
        } finally{
            //Finally, regardless of whether or not the reading was successful.
            //Close the scanner.
            scanner.close();
        }

    }

//CSV <- **************************************


}
