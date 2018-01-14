package com.wix.traitsoft.tpo_mnnit;

/**
 * Created by saksham_ on 12-Oct-17.
 */

public class Graph_Draw {

    private int cse;
    private int ece;
    private int mech;
    private int civil;
    private int ee;
    private int bio;
    private int it;
    private int chem;

    Graph_Draw(int cse,int ece,int mech,int civil,int ee,int bio,int it,int chem)
    {

        this.cse = cse;
        this.ece = ece;
        this.mech = mech;
        this.civil = civil;
        this.ee = ee;
        this.bio = bio;
        this.it = it;
        this.chem = chem;
    }


    public int getEe() {
        return ee;
    }


    public int getCse() {
        return cse;
    }



    public int getEce() {
        return ece;
    }


    public int getChem() {
        return chem;
    }


    public int getIt() {
        return it;
    }


    public int getBio() {
        return bio;
    }


    public int getCivil() {
        return civil;
    }


    public int getMech() {
        return mech;
    }


}
