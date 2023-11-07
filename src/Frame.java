import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    Frame(){
        addKeyListener(new InputListener());
        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(640, 480);
        setResizable(false);
        setVisible(true);
    }
}
