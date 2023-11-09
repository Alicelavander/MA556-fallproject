import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



/*
Reference: https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
 */
public class CurrentGuess extends JPanel implements KeyListener {
    String s = "";

    public CurrentGuess(){
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        g.setFont(new Font("Monospaced", Font.PLAIN, 40));

        System.out.println(s);
        for(int i = 0; i < 5; i++){
            g.drawRect(10+60*i, height - 70, 50, 50);
            if(s.length() > i) g.drawString(String.valueOf(s.charAt(i)), 20+60*i, height - 35);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\b' && s.length() > 0) s = s.substring(0, s.length() - 1);
        else if (e.getKeyChar() != '\b' && s.length() < 5) s += e.getKeyChar();
        super.removeAll();
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) ;
    }
}
