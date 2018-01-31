package com.junkturkey.person;

import com.junkturkey.domino.Domino;
import com.junkturkey.train.Train;

import java.util.List;

public interface Person {

    public void setTrain(int id);

    public void addToHand(Domino domino);

    public void putDomino(Domino domino, Train train);

    public void addPoints(int points);

    public int getPoints();

    public Train getTrain();

    public void createMexicanTrain(Domino domino);

    public boolean search4Domino(Domino domino);

    public boolean isEmptyHand();

    public void printHand();

}
