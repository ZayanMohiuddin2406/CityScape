import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CityScape extends JPanel implements KeyListener {
    private static BufferedImage img = null;
    private Building[] buildings;
    private UFO[] ufos;
    private Car car;
    private Random random;
    private Timer timer;
    private static DrivableUFO drivableUFO;

    public static int getDrivableUFOX() {
        return drivableUFO.getX();
    }

    public static int getDrivableUFOY() {
        return drivableUFO.getY();
    }

    public CityScape() {
        random = new Random();
        buildings = new Building[5];
        ufos = new UFO[5];

        int storeX = 0;
        for (int i = 0; i < buildings.length; i++) {
            int winNumWidth = random.nextInt(2, 6);
            int winNumHeight = random.nextInt(2, 8);
            int buildingWidth = winNumWidth * 30 + (winNumWidth + 1) * 20;
            int buildingHeight = 30 * winNumHeight + 20 * (winNumHeight + 1);
            int startX = storeX + random.nextInt(20, 30);
            storeX = startX + buildingWidth;
            buildings[i] = new Building(startX, 800, buildingWidth, buildingHeight);
        }

        for (Building building : buildings) {
            building.generateWindowColors();
        }

        int original = 0;
        for (int i = 0; i < ufos.length; i++) {
            ufos[i] = new UFO(original, random.nextInt(500), (int) (10 * Math.random() - 5), (int) (10 * Math.random() - 5));
            original = original + 120;
        }

        initializeCar();
        initializeDrivableUFO();
        startTimer();
        setFocusable(true);
        addKeyListener(this);
    }

    private void startTimer() {
        timer = new Timer(10, e -> {
            move();
            repaint();
        });
        timer.start();
    }

    private void initializeCar() {
        try {
            img = ImageIO.read(new File("res/car.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        car = new Car(50, 800, img, 3);
    }

    private void initializeDrivableUFO() {
        drivableUFO = new DrivableUFO(500, 500, 0, 0);
    }

    private void move() {
        for (int i = 0; i < ufos.length; i++) {
            for (int j = i + 1; j < ufos.length; j++) {
                ufos[i].collision(ufos[j]);
            }
            ufos[i].move();
        }
        car.move();
        drivableUFO.move();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(0, 0, 75));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.yellow);
        g.fillOval(100, 100, 100, 100);
        g.setColor(new Color(0, 0, 75));
        g.fillOval(120, 90, 100, 100);
        g.setColor(new Color(128, 128, 128));
        g.fillRect(0, 800, 1500, 200);
        g.setColor(Color.yellow);
        int startX = 5;
        for (int i = 0; i < 15; i++) {
            g.fillRect(startX, 850, 60, 5);
            startX = startX + 60 + 30;
        }

        for (Building building : buildings) {
            building.paint(g2d);
        }

        for (UFO ufo : ufos) {
            ufo.paint(g2d);
        }
        drivableUFO.paint(g2d);
        car.paintComponent(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        drivableUFO.keyPressed(e);
        car.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        drivableUFO.keyReleased(e);
        car.keyReleased(e);
    }
}
