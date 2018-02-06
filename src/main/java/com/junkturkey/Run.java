package com.junkturkey;

import com.junkturkey.domino.Domino;
import com.junkturkey.person.BotPerson;
import com.junkturkey.person.IndividualPerson;
import com.junkturkey.person.Person;

import java.util.*;

public class Run {

    private static ArrayList<Domino> dominos;
    private static Domino engine;
    private static MainGUI form;
    private static HashMap<Integer, Person> personMap;

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

        if (individualPlayersAmount==1) { form = new MainGUI(); }       //gui creation

        //Starting round
        for (int i=dominoLevel; i>0; i--){
            Round(dominoLevel);
        }

    }

    //Round stage
    public static void Round(int dominoLevel){

        final Random random = new Random();

        ArrayList<Domino> roundDominos = dominos;

        engine = new Domino(dominoLevel,dominoLevel);
        roundDominos.remove(engine);

        //Gaining dominos to the start hand
        for (int i=0; i<personMap.size(); i++){
            Person individ = personMap.get(i);
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

        //while (Turn(personMap)){}

    }

    //Turn stage
    public static boolean Turn() {
        for (int i=0; i<personMap.size();i++){
            if (personMap.get(i).getClass()==IndividualPerson.class) {
//                if (personMap.get(i).search4Domino(tempDomino)){
//                    System.out.println("enter train name");
//                    if (in.nextLine().equals("OwnTrain") && personMap.get(i).getTrain().isOpen() &&
//                            personMap.get(i).getTrain().getLast().getViableSide()==tempDomino.firstside() ||
//                            personMap.get(i).getTrain().getLast().getViableSide()==tempDomino.secondside()){     //TO CHANGE
//                        personMap.get(i).putDomino(tempDomino,personMap.get(i).getTrain());
//                    }
//                }
//                System.out.println(form.currentDomino.firstside());
//                Domino current = new Domino(form.currentDomino.firstside(),form.currentDomino.secondside());
//                System.out.println(current.firstside()+"|"+current.secondside());

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