package com.junkturkey.train;

import com.junkturkey.domino.Domino;

public class MexicanTrain implements Train {
    private static MexicanTrain ourInstance = new MexicanTrain();

    public static MexicanTrain getInstance() {
        return ourInstance;
    }

    private MexicanTrain() {
    }



    private boolean isOpenFlag = false;
    private Domino lastDomino;

    public void setEngine(Domino engine) { this.lastDomino = engine; }

    public void addDomino(Domino domino){
        if ((domino.firstside()==lastDomino.firstside() &&  domino.viableSide()==1)){
            this.lastDomino=domino;
            this.lastDomino.setViable(1,false);
            System.out.print("attached with side 1, side 1 locked, unlocked side = " + lastDomino.secondside());
        }
        if (domino.secondside()==lastDomino.secondside() &&  domino.viableSide()==2){
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

    public Domino getLast(){ return lastDomino; }
}
