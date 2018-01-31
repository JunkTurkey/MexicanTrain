package com.junkturkey.train;

import com.junkturkey.domino.Domino;

public interface Train {


    public void addDomino(Domino domino);

    public boolean isOpen();

    public Domino getLast();


}
