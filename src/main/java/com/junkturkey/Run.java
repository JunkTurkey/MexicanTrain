package com.junkturkey;

import com.junkturkey.domino.Domino;
import com.junkturkey.person.BotPerson;
import com.junkturkey.person.IndividualPerson;
import com.junkturkey.person.Person;

import java.util.*;

public class Run {

    public static ArrayList<Domino> dominos;
    public static Domino engine;
    public static MainGUI form;

    public static void main(String[] args) {
        Run.Game(12, 5, 1);
    }

    //Game stage
    public static void Game(int dominoLevel,  int playersAmount, int individualPlayersAmount){

        //Creating dominos array
        dominos = new ArrayList<Domino>();
        for (int i = 0; i <= dominoLevel; i++) {
            for (int j = 0; j <= dominoLevel; j++) {
                Domino domino = new Domino(i, j);
                dominos.add(domino);
            }
        }

        HashMap<Integer, Person> personMap = new HashMap<Integer, Person>() ;

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

        if (individualPlayersAmount==1) { form = new MainGUI(); }       //gui creation

        //Starting round
        for (int i=dominoLevel; i>0; i--){
            Round(personMap, dominoLevel);
        }

    }

    //Round stage
    public static void Round(HashMap<Integer, Person> individs, int dominoLevel){

        final Random random = new Random();

        ArrayList<Domino> roundDominos = dominos;

        engine = new Domino(dominoLevel,dominoLevel);
        roundDominos.remove(engine);

        //Gaining dominos to the start hand
        for (int i=0; i<individs.size(); i++){
            Person individ = individs.get(i);
            for (int j = 0; j < 12; j++) {        //TO CHANGE: Change (12) as start dominos amount
                int temp = random.nextInt(roundDominos.size());
                individ.addToHand(roundDominos.get(temp));
                roundDominos.remove(temp);
            }
            if (individ.getClass()==IndividualPerson.class){
                form.setSoloGame(individ.returnHand());
                form.setVisible(true);
            }
        }

        while (Turn(individs)){}

    }

    //Turn stage
    public static boolean Turn(HashMap<Integer, Person> individs) {
        Scanner in = new Scanner(System.in);
        for (int i=0; i<individs.size();i++){
            if (individs.get(i).getClass()==IndividualPerson.class){
                System.out.println("enter domino x y");
                Domino tempDomino = new Domino(in.nextInt(), in.nextInt());
                if (individs.get(i).search4Domino(tempDomino)){
                    System.out.println("enter train name");
                    if (in.nextLine().equals("OwnTrain") && individs.get(i).getTrain().isOpen() &&
                            individs.get(i).getTrain().getLast().getViableSide()==tempDomino.firstside() ||
                            individs.get(i).getTrain().getLast().getViableSide()==tempDomino.secondside()){     //TO CHANGE
                        individs.get(i).putDomino(tempDomino,individs.get(i).getTrain());
                    }
                }
            }
            else {
                //BotAI
                System.out.println("lol");
            }


            if (individs.get(i).isEmptyHand()) return false;
        }
        return true;
    }

}   //System.out.print("test");