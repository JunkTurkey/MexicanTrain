package com.junkturkey.domino;

public class Domino {

//    private final int side1 = 1;
//    private final int side2 = 2;
    private int value1;
    private int value2;
    private boolean[] valueViability = {true, true};


    public Domino(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public int firstside(){
        return value1;
    }
    public int secondside(){
        return value2;
    }

    public int getSide(int side){
        if (side==1) return value1;
        else return value2;
    }
    public void setViable(int side, boolean value){
        this.valueViability[side-1]=value;
    }

    public int viableSide(){
        if (valueViability[0]) return 1;
        else return 2;
    }

    public int getViableSide(){
        if (valueViability[0]) return value1;
        else if (valueViability[1]) return value2;
        else return 0;
    }
    public void setfirstside(int value1) {
        this.value1 = value1;
    }

    public void setsecondside(int value2) {
        this.value2 = value2;
    }
}
