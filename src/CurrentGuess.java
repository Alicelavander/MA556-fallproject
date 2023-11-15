import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

/*
Reference: https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
 */
public class CurrentGuess extends JPanel implements KeyListener {
    private String inputString = "";
    private final ActionListener actionListener;
    private final boolean active;

    public CurrentGuess(ActionListener actionListener, boolean active){
        addKeyListener(this);
        if(active) setFocusable(true);
        this.actionListener = actionListener;
        this.active = active;
    }

    public void paintComponent(Graphics g) {
        //request focus to listen to the key inputs
        if(active) requestFocusInWindow();

        super.paintComponent(g);

        g.setFont(new Font("Monospaced", Font.PLAIN, 40));

        for(int i = 0; i < 5; i++){
            g.drawRect(10+60*i, 20, 50, 50);
            if(inputString.length() > i) g.drawString(String.valueOf(inputString.charAt(i)), 20+60*i, 20+35);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Don't add to string if they are escape characters
        switch(e.getKeyChar()){
            case '\b':
                if(!inputString.isEmpty()) inputString = inputString.substring(0, inputString.length() - 1);
                break;
            case '\n':
                if(inputString.length() == 5){
                    checkAndSubmitWord(inputString);
                    inputString = "";
                }
                break;
            case '\t':
            case '\r':
            case '\f':
                break;
            default:
                inputString += e.getKeyChar();
        }
        super.removeAll();
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /* If Enter-key pressed, submit the word as the answer.
    Reference: https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
     */
    public void checkAndSubmitWord(String word){
        if(wordExists(word)){
            this.putClientProperty("guess", word);
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "guessSubmitted"));
        } else {
            //input string not in word list, show dialog
            JOptionPane.showMessageDialog(null, word + " is not in word list...");
        }
    }

    /* checks if the word is included in the list
    Original List retrieved from: https://gist.github.com/scholtes/94f3c0303ba6a7768b47583aff36654d
    Reference:
        https://docs.oracle.com/javase/jp/8/docs/api/java/io/FileReader.html
        https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
     */
    public boolean wordExists(String guess){
        final File[] files = new File[]{
                new File("src/wordle-La.txt"),
                new File("src/wordle-Ta.txt")
        };

        for (File file: files){
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                if(reader.lines().toList().contains(guess)) return true;
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + file.toString());
            }
        }

        return false;
    }
}
