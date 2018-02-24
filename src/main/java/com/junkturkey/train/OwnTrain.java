package com.junkturkey.train;

import com.junkturkey.MainGUI;
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
        MainGUI form = Run.getForm();
        switch (lastDomino.viableSide()){
            case 1:
                if (lastDomino.getViableSide()==domino.firstside()){
                    this.lastDomino=domino;
                    this.lastDomino.setViable(1,false);
                    form.urtrain.setText(new StringBuilder().append(lastDomino.firstside()).append("|").append(lastDomino.secondside()).toString());
                }
                else if (lastDomino.getViableSide()==domino.secondside()){
                    this.lastDomino=domino;
                    this.lastDomino.setViable(2,false);
                    form.urtrain.setText(new StringBuilder().append(lastDomino.secondside()).append("|").append(lastDomino.firstside()).toString());
                }
                else throw new IllegalArgumentException();
                break;
            case 2:
                if (lastDomino.getViableSide()==domino.firstside()){
                    this.lastDomino=domino;
                    this.lastDomino.setViable(1,false);
                    form.urtrain.setText(new StringBuilder().append(lastDomino.firstside()).append("|").append(lastDomino.secondside()).toString());
                }
                else if (lastDomino.getViableSide()==domino.secondside()){
                    this.lastDomino=domino;
                    this.lastDomino.setViable(2,false);
                    form.urtrain.setText(new StringBuilder().append(lastDomino.secondside()).append("|").append(lastDomino.firstside()).toString());
                }
                else throw new IllegalArgumentException();
                break;

                default: throw new IllegalArgumentException();
        }
    }

    public boolean isOpen(){
        if (isOpenFlag) return true;
        else return false;
    }

    public Domino getLast(){ return lastDomino; }
}
