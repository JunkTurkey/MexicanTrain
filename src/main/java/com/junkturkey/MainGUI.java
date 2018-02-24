package com.junkturkey;

import com.junkturkey.domino.Domino;
import com.junkturkey.person.Person;
import com.junkturkey.train.Train;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MainGUI extends JFrame {

    private JPanel panel = new JPanel();
    public Domino currentDomino;
    public Train currentTrain;
    private JButton endTurn = new JButton("End Turn");
    private ArrayList<Domino> playerHand = new ArrayList<>();
    public JLabel urtrain; //TO CHANGE: Current train label
    private Set<JButton> tempSet;

    ActionListener endTurnAL = (ActionEvent e) -> {
        if (Run.Turn()){
            currentDomino = null;
            for(JButton button:tempSet)
                button.setBackground(Color.WHITE);
        }


    };

    public MainGUI() {
        super("Mexican Train");
        this.setBounds(400,200,700,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        endTurn.addActionListener(endTurnAL);
        endTurn.setEnabled(false);
        this.getContentPane().add(endTurn);
    }

    public void setSoloGame(Person individ){
        this.playerHand = individ.returnHand();
        Container container = this.getContentPane();
        HashMap<JButton,Domino> handMap = new HashMap<JButton,Domino>();
        for (Domino domino:playerHand){
            currentTrain = individ.getTrain();
            String dominoString = domino.firstside() + "|" + domino.secondside();
            JButton dominoButton = new JButton(dominoString);
            dominoButton.setBackground(Color.WHITE);
            ActionListener al = (ActionEvent e) -> {
                JButton b = (JButton)e.getSource();
                if (b.getBackground()==Color.WHITE)  {
                    tempSet = handMap.keySet();
                    for(JButton button:tempSet)
                        button.setBackground(Color.WHITE);
                    b.setBackground(Color.BLACK);
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
            container.add(dominoButton);
        }
        String tempString = new StringBuilder().append(currentTrain.getLast().firstside()).append("|").append(currentTrain.getLast().secondside()).toString();
        urtrain = new JLabel(tempString);
        container.add(urtrain);
    }
}
