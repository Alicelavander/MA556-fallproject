import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
Reference: https://docs.oracle.com/javase/tutorial/uiswing/events/keylistener.html
 */
public class InputListener implements KeyListener {

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.print(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
