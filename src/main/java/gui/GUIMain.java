package gui;
import java.io.IOException;
import javax.swing.*;

public class GUIMain {
    private final JFrame mainWindow;

    /**
     * singleton pattern
     * ensures we only have access to one Jframe at a time
     */
    private GUIMain () throws IOException {
        mainWindow = new JFrame();
        mainWindow.setContentPane(new Registration().mainFrame);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }
    private static GUIMain instance;
    public static GUIMain getInstance() throws IOException {
        if(instance == null)
            instance = new GUIMain();
        return instance;
    }

    // allows to set a new view in the frame.
    public void setContentPane(JPanel panel) {
        mainWindow.setContentPane(panel);
        mainWindow.revalidate();
        mainWindow.repaint();
    }}

