import javax.swing.*;
import java.awt.*;

public class GuessedWord extends JPanel {
    private final Word guess;

    public GuessedWord(Word guess, String answer){
        this.guess = guess;
        this.guess.checkWithAnswer(answer); //TODO: I don't like this
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Monospaced", Font.PLAIN, 40));

        for(int i = 0; i < 5; i++){
            Letter letter = guess.getLetters()[i];
            g.setColor(letter.getColor());
            g.fillRect(10+60*i, 20, 50, 50);
            g.setColor(Color.black);
            g.drawString(String.valueOf(letter.getAlphabet()), 20+60*i, 20+35);
        }
    }
}
