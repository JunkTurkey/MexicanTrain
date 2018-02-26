package com.junkturkey;

import com.junkturkey.domino.Domino;
import com.junkturkey.person.BotPerson;
import com.junkturkey.person.IndividualPerson;
import com.junkturkey.person.Person;
import com.junkturkey.stage.Round;

import java.util.*;

public class Run {

    private static ArrayList<Domino> dominos;
    public static ArrayList<Domino> roundDominos;
    private static Domino engine;
    private static MainGUI form;
    public static HashMap<Integer, Person> personMap;
    private static int dominoLevel;
    private static int playersAmount;
    private static int individualPlayersAmount;

    public static void setRoundDominos(ArrayList<Domino> roundDominos) { Run.roundDominos = roundDominos; }

    public static ArrayList<Domino> getDominos() { return dominos; }

    public static Domino getEngine() { return engine; }

    public static int getDominoLevel() { return dominoLevel; }

    public static void decreaseDominoLevel () { dominoLevel--; }

    public static int getPlayersAmount() { return playersAmount; }

    public static MainGUI getForm() { return form; }

    public static int getIndividualPlayersAmount() { return individualPlayersAmount; }

    public static void main(String[] args) {
        dominoLevel = 12;
        playersAmount = 5;
        individualPlayersAmount = 1;
        Run.Game();
    }

    public static void setEngine(Domino engine) { Run.engine = engine; }

    //Game stage
    public static void Game(){

        //Creating dominos array
        dominos = new ArrayList<Domino>();
        for (int i = 0; i <= dominoLevel; i++) {
            for (int j = 0; j <= dominoLevel; j++) {
                Domino domino = new Domino(i, j);
                dominos.add(domino);
            }
        }

        personMap = new HashMap<Integer, Person>() ;

        //Creating players instances
        for (int i = 0; i<individualPlayersAmount; i++) {
            IndividualPerson individ = new IndividualPerson();
            individ.setTrain(i);
            personMap.put(i,individ);
        }
        for (int i = individualPlayersAmount; i<playersAmount; i++) {
            BotPerson individ = new BotPerson();
            individ.setTrain(i);
            personMap.put(i, individ);
        }

        if (individualPlayersAmount==1) { form = new MainGUI(); }       //TO CHANGE: Simple gui creation

        //Starting round
        engine = new Domino(dominoLevel, dominoLevel);
        Runnable round = new Round();
        Thread thread = new Thread(round);
        thread.start();
    }

    //Turn stage
    public static boolean Turn() {
        for (int i=0; i<personMap.size();i++){
            if (personMap.get(i).getClass()==IndividualPerson.class) {
                try{
                    form.currentTrain.addDomino(form.currentDomino);
                    form.getContentPane().remove(form.currentButton);
                }
                catch (IllegalArgumentException e){
                    System.out.println("Wrong domino attach");
                    return true;
                }
            }
            else {
                //BotAI
                System.out.println("bot moves");
            }
            if (personMap.get(i).isEmptyHand()) return false;
        }
        return true;
    }

}   //System.out.print("test");