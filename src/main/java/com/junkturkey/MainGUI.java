package com.junkturkey;

import com.junkturkey.domino.Domino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainGUI extends JFrame {

    private JPanel panel = new JPanel();

//    class MyListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            JButton b = (JButton)e.getSource();
//            b.setBackground(Color.black);
//        }
//    }
    public MainGUI(ArrayList<Domino> playerHand){
        super("Mexican Train");
        this.setBounds(400,200,700,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(panel);
        Container container = this.getContentPane();
        //container.setLayout(new GridBagLayout());

        for (Domino domino:playerHand){
            String dominoString = domino.firstside() + "|" + domino.secondside();
            JButton dominoButton = new JButton(dominoString);
            ActionListener al = (ActionEvent e) -> {
                JButton b = (JButton)e.getSource();
                b.setBackground(Color.black);
            };
            dominoButton.addActionListener(al);
//            dominoButton.setSize(30,30);
            container.add(dominoButton);
        }
    }


}
