import java.awt.*;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UFO {
    private Random rnd;
    protected int posX;
    protected int posY;
    int speedX;
    int speedY;
    private int diameter = 120;
    private UFO otherUFO;
    private Color[] lightColors = {Color.YELLOW, Color.BLACK};
    private int colorIndex = 0;
    private Timer lightsTimer;

    public UFO(int posX, int posY, int speedX, int speedY) {
        this.posX = posX;
        this.posY = posY;
        this.speedX = speedX;
        this.speedY = speedY;
        startLightsTimer();
    }

    public void collision(UFO other) {
        int dx = (posX - other.posX) + (speedX - other.speedX);
        int dy = (posY - other.posY) + (speedY - other.speedY);
        if (Math.sqrt(dx * dx + dy * dy) <= diameter) {
            int tempSpeedX = speedX;
            int tempSpeedY = speedY;
            speedX = other.speedX;
            speedY = other.speedY;
            other.speedX = tempSpeedX;
            other.speedY = tempSpeedY;
        }
    }

    public void move() {
        if (posX + speedX < 0) {
            speedX = Math.abs(speedX);
        }
        if (posX + speedX > 1400 - diameter) {
            speedX = -Math.abs(speedX);
        }
        if (posY + speedY < 0) {
            speedY = Math.abs(speedY);
        }
        if (posY + speedY > 500 - diameter / 3) {
            speedY = -Math.abs(speedY);
        }
        posX += speedX;
        posY += speedY;
    }

    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        g2d.fillOval(posX + diameter / 4, posY - diameter / 6, diameter / 2, diameter / 2);
        g2d.setColor(Color.GRAY);
        g2d.fillOval(posX, posY, diameter, diameter / 3);
        paintLights(g2d);
    }

    private void startLightsTimer() {
        lightsTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                colorIndex = (colorIndex + 1) % lightColors.length;
            }
        });
        lightsTimer.setRepeats(true);
        lightsTimer.start();
    }

    private void paintLights(Graphics2D g2d) {
        for (int i = 0; i < 3; i++) {
            int startX = posX + diameter / 6 + (diameter / 4) * i;
            g2d.setColor(lightColors[colorIndex]);
            g2d.fillOval(startX, posY + diameter / 12, diameter / 6, diameter / 6);
        }
    }
}
