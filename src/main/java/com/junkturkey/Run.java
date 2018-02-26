package com.junkturkey;

import com.junkturkey.domino.Domino;
import com.junkturkey.person.BotPerson;
import com.junkturkey.person.IndividualPerson;
import com.junkturkey.person.Person;
import com.junkturkey.stage.Round;

import java.util.*;

public class Run {

    private static ArrayList<Domino> dominos;
    private static Domino engine;
    private static MainGUI form;
    private static HashMap<Integer, Person> personMap;
    private static int dominoLevel;
    private static int playersAmount;
    private static int individualPlayersAmount;

    public static ArrayList<Domino> getDominos() { return dominos; }

    public static Domino getEngine() { return engine; }

    public static HashMap<Integer, Person> getPersonMap() { return personMap; }

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

    //  Round stage
    //public static void Round(int dominoLevel){
//
//        final Random random = new Random();
//
//        ArrayList<Domino> roundDominos = new ArrayList<>();
//        roundDominos.addAll(dominos);
//
//        engine = new Domino(dominoLevel,dominoLevel);
//        roundDominos.remove(engine);
//
//        //Gaining dominos to the start hand
//        for (int i=0; i<personMap.size(); i++){
//            Person individ = personMap.get(i);
//            for (int j = 0; j < 12; j++) {        //TO CHANGE: Change (12) as start dominos amount
//                int temp = random.nextInt(roundDominos.size());
//                individ.addToHand(roundDominos.get(temp));
//                roundDominos.remove(temp);
//            }
//            if (individ.getClass()==IndividualPerson.class){        //TO CHANGE: Simple mode choosing method
//                form.setSoloGame(individ.returnHand());
//                form.setVisible(true);
//            }
//        }

        //while (Turn(personMap)){}
    //}

    //Turn stage
    public static boolean Turn() {
        for (int i=0; i<personMap.size();i++){
            if (personMap.get(i).getClass()==IndividualPerson.class) {
//                if (form.currentDomino.getViableSide()==form.currentTrain.getLast().getViableSide()) {
//                    form.currentTrain.addDomino(form.currentDomino);
//                    form.currentTrain=null;
//                    form.currentDomino=null;
//                    //System.out.println("added " + form.currentDomino.firstside()+ "|" + form.currentDomino.secondside());
//                }
//                else {
//                    System.out.println("Wrong domino attach");
//                    return true;
//                }
                try{
                    form.currentTrain.addDomino(form.currentDomino);
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