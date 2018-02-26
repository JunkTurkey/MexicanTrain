package com.junkturkey;

import com.junkturkey.domino.Domino;
import com.junkturkey.person.Person;
import com.junkturkey.stage.Round;
import com.junkturkey.train.Train;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainGUI extends JFrame {

    private JPanel panel = new JPanel();
    private Person currentIndivid;
    public JButton currentButton;
    public Domino currentDomino;
    public Train currentTrain;
    private JButton endTurn = new JButton("End Turn");
    public JLabel urtrain; //TO CHANGE: Current train label
    private HashMap<JButton,Domino> handMap;
    private Set<JButton> buttonsSet;
    private JButton takeDomino = new JButton("Take domino");
    final Random random = new Random();

    ActionListener endTurnAL = (ActionEvent e) -> {
        if (Run.Turn()){
            currentDomino = null;
            for(JButton button:buttonsSet)
                button.setBackground(Color.WHITE);
            endTurn.setEnabled(false);
        }
        else {
            if (Run.getDominoLevel() > 0) {
                Runnable round = new Round();
                Thread thread = new Thread(round);
                try {
                    Run.decreaseDominoLevel();
                    Run.setEngine(new Domino(Run.getDominoLevel(),Run.getDominoLevel()));
                    thread.join();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };
    ActionListener takeDominoAL = (ActionEvent e) -> {
//        int temp = random.nextInt(Run.roundDominos.size());
//        currentIndivid.addToHand(Run.roundDominos.get(temp));
//        handMap.put()
//        Run.roundDominos.remove(temp);
    };

    public MainGUI() {
        super("Mexican Train");
        this.setBounds(400,200,700,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        endTurn.addActionListener(endTurnAL);
        endTurn.setEnabled(false);
        this.getContentPane().add(endTurn);

        takeDomino.addActionListener(takeDominoAL);
        this.getContentPane().add(takeDomino);
    }

    public void setSoloGame(Person individ){
        this.currentIndivid = individ;
        ArrayList<Domino> playerHand = currentIndivid.returnHand();
        handMap = new HashMap<JButton,Domino>();
        for (Domino domino:playerHand){
            currentTrain = individ.getTrain();
            String dominoString = domino.firstside() + "|" + domino.secondside();
            JButton dominoButton = new JButton(dominoString);
            dominoButton.setBackground(Color.WHITE);
            ActionListener al = (ActionEvent e) -> {
                JButton b = (JButton)e.getSource();
                if (b.getBackground()==Color.WHITE)  {
                    buttonsSet = handMap.keySet();
                    for(JButton button:buttonsSet)
                        button.setBackground(Color.WHITE);
                    b.setBackground(Color.BLACK);
                    currentButton = b;
                    currentDomino = domino;
                    currentTrain = individ.getTrain(); //TO CHANGE: Chosing the train
                    endTurn.setEnabled(true);   //TO CHANGE
                }
                else  {
                    b.setBackground(Color.WHITE);
                    currentDomino=null;
                    endTurn.setEnabled(false);
                }
            };
            dominoButton.addActionListener(al);
            handMap.put(dominoButton,domino);
            this.getContentPane().add(dominoButton);
        }
        String tempString = new StringBuilder().append(currentTrain.getLast().firstside()).append("|").append(currentTrain.getLast().secondside()).toString();
        urtrain = new JLabel(tempString);
        this.getContentPane().add(urtrain);

    }
}
