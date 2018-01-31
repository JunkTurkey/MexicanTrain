package com.junkturkey.train;

import com.junkturkey.Run;
import com.junkturkey.domino.Domino;

public class OwnTrain implements Train {

    public int id;
    private boolean isOpenFlag = false;
    private Domino lastDomino;
    //private Domino engine;

    public OwnTrain(int id) {
        this.id = id;
    }

    public void addDomino(Domino domino){
        if ((domino.firstside()==lastDomino.firstside() && domino.viableSide()==1)){
            this.lastDomino=domino;
            this.lastDomino.setViable(1,false);
            System.out.print("attached with side 1, side 1 locked, unlocked side = " + lastDomino.secondside());
        }
        if (domino.secondside()==lastDomino.secondside() && domino.viableSide()==2){
            this.lastDomino=domino;
            this.lastDomino.setViable(2,false);
            System.out.print("attached with side 2, side 2 locked, unlocked side = " + lastDomino.firstside());
        }
        else System.out.print("can not attach your domino");
    }

    public boolean isOpen(){
        if (isOpenFlag) return true;
        else return false;
    }

    public Domino getLast(){
        return lastDomino;
    }
}
