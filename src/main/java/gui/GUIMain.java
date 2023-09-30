package gui;

import java.io.IOException;

import javax.swing.JFrame;

public class GUIMain {

    public static void main(String[] args) throws IOException{

        JFrame start = new StartPage();
        start.setTitle("Welcome");
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        start.setVisible(true);

    }
}
