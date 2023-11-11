import javax.swing.*;
import java.io.IOException;

public class server extends Thread{
    public void run(){
        JFrame controllerPage = null;
        try {
            controllerPage = new ControllerPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
