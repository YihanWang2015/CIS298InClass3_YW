package edu.kvcc.cis298.inclass3.inclass3;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ywang4241 on 10/19/2015.
 */
public class Crime {

    //Private Variables for our models
    private UUID mId;
    private String mTitle;

    private Date mDate;
    private boolean mSolved;



    //Default constructor
    public Crime(){
        //Make a new unique id for this particular crime
        mId = UUID.randomUUID();

        mDate = new Date();
    }


    //CSV -> **************************************
    //4 parameter constructor to make a new Crime
    public Crime(UUID uuid, String title, Date date, boolean isSolved){
        mId= uuid;
        mTitle = title;
        mDate = date;
        mSolved = isSolved;
    }

    //CSV <- **************************************


    //Getters and Setters

    //Only need to get the UUID, no need to set it, so no setter here
    public UUID getId() {
        return mId;
    }

/*    public void setId(UUID id) {
        mId = id;
    }*/

    //Getter and Setter for the title
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }







    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }





}
