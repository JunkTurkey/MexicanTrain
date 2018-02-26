package com.junkturkey.stage;

import com.junkturkey.MainGUI;
import com.junkturkey.Run;
import com.junkturkey.domino.Domino;
import com.junkturkey.person.IndividualPerson;
import com.junkturkey.person.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Round implements Runnable{

    private ArrayList<Domino> roundDominos;
    private Domino engine;
    private MainGUI form = Run.getForm();

    public ArrayList<Domino> getRoundDominos() { return roundDominos; }

    @Override
    public void run() {

        final Random random = new Random();

        roundDominos = Run.getDominos();
        engine = Run.getEngine();
        roundDominos.remove(engine);

        //Gaining dominos to the start hand
        for (int i=0; i<Run.personMap.size(); i++){
            Person individ = Run.personMap.get(i);
            for (int j = 0; j < 12; j++) {        //TO CHANGE: Change (12) as start dominos amount
                int temp = random.nextInt(roundDominos.size());
                individ.addToHand(roundDominos.get(temp));
                roundDominos.remove(temp);
                individ.getTrain().setEngine(engine);
            }
            if (individ.getClass()==IndividualPerson.class){        //TO CHANGE: Simple mode choosing method
                form.setSoloGame(individ);
                form.setVisible(true);
            }
        }
        Run.setRoundDominos(roundDominos);
    }
}
