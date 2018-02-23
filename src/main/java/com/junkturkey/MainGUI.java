package com.junkturkey;

import com.junkturkey.domino.Domino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class MainGUI extends JFrame implements Runnable {

    private JPanel panel = new JPanel();
    //public JButton currentButton;
    public Domino currentDomino;
    private JButton endTurn = new JButton();
    private ArrayList<Domino> playerHand;

    ActionListener endTurnAL = (ActionEvent e) -> {
        while (Run.Turn()){}
    };

    public MainGUI() throws HeadlessException {
        super("Mexican Train");
        this.setBounds(400,200,700,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        endTurn.addActionListener(endTurnAL);
        endTurn.setEnabled(false);
        this.getContentPane().add(endTurn);
    }

    public void setSoloGame(ArrayList<Domino> playerHand){
        this.playerHand = new ArrayList<>();
        this.playerHand = playerHand;
        Container container = this.getContentPane();
        HashMap<JButton,Domino> handMap = new HashMap<JButton,Domino>();
        //ArrayList<Domino> playerHand = new ArrayList<>();
        for (Domino domino:playerHand){
            String dominoString = domino.firstside() + "|" + domino.secondside();
            JButton dominoButton = new JButton(dominoString);
            dominoButton.setBackground(Color.BLUE);
            ActionListener al = (ActionEvent e) -> {
                JButton b = (JButton)e.getSource();
                if (b.getBackground()==Color.BLUE)  {
                    b.setBackground(Color.BLACK);
                    currentDomino = domino;
                    endTurn.setEnabled(true);   //TO CHANGE
                }
                else  {
                    b.setBackground(Color.BLUE);
                    currentDomino=null;
                }
            };
            dominoButton.addActionListener(al);
            handMap.put(dominoButton,domino);
            container.add(dominoButton);

        }
    }

    @Override
    public void run() {

    }
}
