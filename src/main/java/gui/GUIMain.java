package gui;

import javax.swing.JFrame;

public class GUIMain {

    public static void main(String[] args){

        JFrame start = new StartPage();
        start.setTitle("Welcome");
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setVisible(true);

    }
}
