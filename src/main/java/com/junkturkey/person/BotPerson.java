package com.junkturkey.person;

import com.junkturkey.domino.Domino;
import com.junkturkey.train.MexicanTrain;
import com.junkturkey.train.OwnTrain;
import com.junkturkey.train.Train;

import java.util.ArrayList;
import java.util.List;

public class BotPerson implements Person{

    private OwnTrain myTrain;
    private List<Domino> myHand;
    private int points=0;

    public BotPerson() {
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

    public Train getTrain() {
        return myTrain;
    }

    public void createMexicanTrain(Domino domino){
        MexicanTrain.getInstance().addDomino(domino);
    }

    public boolean search4Domino(Domino domino) {
        if (myHand.contains(domino)) return true;
        else return false;
    }

    public boolean isEmptyHand() {
        if(myHand.isEmpty()) return true;
        else return false;
    }

    public void printHand() {
        for (int i=0; i<myHand.size(); i++)
            System.out.print(myHand.get(i).firstside() + "|" + myHand.get(i).secondside()+ "    ");
    }

    public void putDomino(Domino domino, Train train){
        train.addDomino(domino);
        myHand.remove(domino);
    }
}