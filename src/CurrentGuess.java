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
    private String s = "";
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
            if(s.length() > i) g.drawString(String.valueOf(s.charAt(i)), 20+60*i, 20+35);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() != '\n'){
            if (e.getKeyChar() == '\b' && s.length() > 0) s = s.substring(0, s.length() - 1);
            else if (e.getKeyChar() != '\b' && s.length() < 5) s += e.getKeyChar();
        }
        super.removeAll();
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        /* If Enter-key pressed, submit the word as the answer.
        Reference: https://docs.oracle.com/javase/tutorial/uiswing/events/actionlistener.html
         */
        if (e.getKeyCode() == KeyEvent.VK_ENTER && s.length() == 5) {
            if(wordExists(s)){
                this.putClientProperty("guess", s);
                actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "guessSubmitted"));
                s = "";
                super.repaint();
            } else {
                //TODO: 画面実装
                System.out.println(s + " is not in word list...");
            }
        }
    }

    public boolean wordExists(String guess){
        /* checks if the word is included in the list
        Original List retrieved from: https://gist.github.com/scholtes/94f3c0303ba6a7768b47583aff36654d
        Reference:
            https://docs.oracle.com/javase/jp/8/docs/api/java/io/FileReader.html
            https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
         */
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
