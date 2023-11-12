import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        if (e.getKeyCode() == KeyEvent.VK_ENTER && s.length() == 5) {
            this.putClientProperty("guess", s);
            actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "guessSubmitted"));
            s = "";
            super.repaint();
        }
    }
}
