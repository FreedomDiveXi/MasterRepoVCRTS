import javax.swing.*;
import java.io.IOException;

public class client extends Thread{
    public void run(){
        JFrame startPage = null;
        try {
            startPage = new StartPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        startPage.setTitle("Welcome to the Vehicular Cloud");
        startPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startPage.setVisible(true);
    }
}
