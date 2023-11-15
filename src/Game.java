import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame implements ActionListener {
    private final String word; //actual word
    private JPanel[] guesses; //list of words guessed by the user
    private JPanel panel; //buffer panel to layout multiple components
    private int attempts; //keep track of the # of attempts for end game flag
    private boolean correct; //keep track of input answer for end game flag

    public Game(String word){
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        setBackground(Color.WHITE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(325, 520);
        setResizable(true);
        setVisible(true);

        guesses = new JPanel[]{
                new CurrentGuess(this, false),
                new CurrentGuess(this, false),
                new CurrentGuess(this, false),
                new CurrentGuess(this, false),
                new CurrentGuess(this, false),
                new CurrentGuess(this, false)
        };
        attempts = 0;
        correct = false;
        this.word = word;

        add(panel);
        makeScreen();
    }

    public void makeScreen(){
        panel.removeAll();
        //re-add the components to buffer panel
        if(attempts < 6) guesses[attempts] = new CurrentGuess(this, true);
        for(JPanel p: guesses){
            if(p != null) panel.add(p);
        }
        panel.revalidate();
        panel.repaint();

        //check if game has ended; if yes, then close the game.
        if(correct) {
            JOptionPane.showMessageDialog(null, "Correct! Congratulations.");
            System.exit(0);
        } else if(attempts == 6){
            JOptionPane.showMessageDialog(null, "game over: correct word was " + word);
            System.exit(0);
        }
    }

    /* If Enter-key pressed, submit the word as the answer.
        Reference: https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("guessSubmitted")) {
            String guess = ((CurrentGuess)e.getSource()).getClientProperty("guess").toString();
            System.out.println("received word: " + guess);

            guesses[attempts] = new GuessedWord(new Word(guess), word);
            attempts++;
            if(guess.equals(word)) correct = true;

            makeScreen();
        }
    }
}
