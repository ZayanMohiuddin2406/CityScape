import java.awt.*;
import java.awt.event.*;

public class DrivableUFO extends UFO {

    private boolean right = false, left = false;
    private boolean up = false, down = false;
    private boolean beam;

    public DrivableUFO(int x, int y, int xa, int ya) {
        super(x, y, xa, ya);
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            beam = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            beam = false;
        }
    }

    public void move() {
        if (right) {
            posX += 2;
        }
        if (left) {
            posX -= 2;
        }
        if (down) {
            posY += 2;
        }
        if (up) {
            posY -= 2;
        }

        super.move();
    }

    private void paintBeam(Graphics2D g2d) {
        g2d.setColor(Color.GREEN);
        g2d.fillRect(super.posX,super.posY+120/6,120,1000);
    }

    public void paint(Graphics2D g2d) {
        if (beam) {
            paintBeam(g2d);
        }
        super.paint(g2d);
    }
}
