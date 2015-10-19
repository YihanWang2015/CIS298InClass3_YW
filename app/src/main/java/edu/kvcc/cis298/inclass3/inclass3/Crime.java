package edu.kvcc.cis298.inclass3.inclass3;

import java.util.UUID;

/**
 * Created by ywang4241 on 10/19/2015.
 */
public class Crime {

    //Private Variables for our models
    private UUID mId;
    private String mTitle;

    //Default constructor
    public Crime(){
        //Make a new unique id for this particular crime
        mId = UUID.randomUUID();
    }

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
}
