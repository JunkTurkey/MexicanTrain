package com.junkturkey.train;

import com.junkturkey.Run;
import com.junkturkey.domino.Domino;

public class OwnTrain implements Train {

    public int id;
    private boolean isOpenFlag = false;
    private Domino lastDomino;

    public void setEngine(Domino engine) { this.lastDomino = engine; }

    public OwnTrain(int id) {
        this.id = id;
    }

    public void addDomino(Domino domino){
        if ((domino.firstside()==lastDomino.firstside() && lastDomino.viableSide()==1)){
            this.lastDomino=domino;
            this.lastDomino.setViable(1,false);
            System.out.println("attached with side 1, side 1 locked, unlocked side = " + lastDomino.secondside());
        }
        else if (domino.secondside()==lastDomino.secondside() && lastDomino.viableSide()==2){
            this.lastDomino=domino;
            this.lastDomino.setViable(2,false);
            System.out.println("attached with side 2, side 2 locked, unlocked side = " + lastDomino.firstside());
        }
        else throw new IllegalArgumentException();//System.out.println("can not attach your domino");

    }

    public boolean isOpen(){
        if (isOpenFlag) return true;
        else return false;
    }

    public Domino getLast(){ return lastDomino; }
}
