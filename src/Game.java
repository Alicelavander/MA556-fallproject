import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {
    private final String word; //actual word
    private JPanel[] guesses; //list of words guessed by the user
    private int attempt;
    private boolean endGame;
    private JPanel panel;

    public Game(String word){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(325, 520);
        setResizable(true);
        setVisible(true);

        attempt = 0;

        this.word = word;
        guesses = new JPanel[]{
                new CurrentGuess(this, false),
                new CurrentGuess(this, false),
                new CurrentGuess(this, false),
                new CurrentGuess(this, false),
                new CurrentGuess(this, false),
                new CurrentGuess(this, false)
        };

        add(panel);
        makeScreen();
    }

    public void makeScreen(){
        panel.removeAll();
        if(attempt < 6) guesses[attempt] = new CurrentGuess(this, true);
        for(JPanel p: guesses){
            if(p != null) panel.add(p);
        }
        panel.revalidate();
        panel.repaint();

        if(attempt == 6) System.out.println("correct word: " + word);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("guessSubmitted")) {
            String guess = ((CurrentGuess)e.getSource()).getClientProperty("guess").toString();
            guesses[attempt] = new GuessedWord(new Word(guess), word);
            attempt++;

            makeScreen();

            System.out.println("received word: " + guess);
        }
    }
}
