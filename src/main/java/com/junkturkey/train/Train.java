package com.junkturkey.train;

import com.junkturkey.domino.Domino;

public interface Train {


    void addDomino(Domino domino);

    boolean isOpen();

    Domino getLast();


}
