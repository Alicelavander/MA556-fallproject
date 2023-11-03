import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    Frame(){
        addKeyListener(new InputListener());
        setBackground(Color.GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 340);
        setResizable(false);
        setVisible(true);
    }
}
