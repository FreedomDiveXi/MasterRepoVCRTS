package utils;

import javax.swing.*;

public class GuiHelper {

    public static void showGui(JFrame frame, JPanel frameContent) {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(frameContent);
        frame.pack();
        frame.setVisible(true);
    }


}
