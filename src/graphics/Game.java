package graphics;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public Game(){
        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(325, 520);
        setResizable(false);
        setVisible(true);
        CurrentGuess a = new CurrentGuess();
        add(a);
        a.requestFocusInWindow();
    }
}
