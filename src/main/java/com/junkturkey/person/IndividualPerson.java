package com.junkturkey.person;

import com.junkturkey.domino.Domino;
import com.junkturkey.train.MexicanTrain;
import com.junkturkey.train.OwnTrain;
import com.junkturkey.train.Train;

import java.util.ArrayList;

public class IndividualPerson implements Person{

    private OwnTrain myTrain;



    private ArrayList<Domino> myHand;
    private int points=0;

    public IndividualPerson() {
        myHand = new ArrayList<Domino>();
    }
    public void setTrain(int id){
        this.myTrain=new OwnTrain(id);
    }

    public void addToHand(Domino domino){
        myHand.add(domino);
    }

    public void addPoints(int points){
        this.points+=points;
    }

    public int getPoints() {
        return points;
    }

    public void createMexicanTrain(Domino domino){
        MexicanTrain.getInstance().addDomino(domino);
    }

    public void putDomino(Domino domino, Train train){
        train.addDomino(domino);
        myHand.remove(domino);
    }

    public boolean search4Domino(Domino domino){
        if (myHand.contains(domino)) return true;
        else return false;
    }

    public boolean isEmptyHand(){
        if(myHand.isEmpty()) return true;
        else return false;
    }

    public Train getTrain(){
        return myTrain;
    }


    public ArrayList<Domino> returnHand(){ return myHand; }
}
