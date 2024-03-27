import java.awt.*;
import java.util.Random;
public class Building {
    private Random random;
    private int x;
    private int y;
    private int width;
    private int height;
    private int[][] windowColors;
    public Building(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.random = new Random();
        generateWindowColors();
    }
    public int countWindows() {
        int countX = (width - 20) / 50;
        int countY = (height - 20) / 50;
        return countX * countY;
    }
    void generateWindowColors() {
        int countX = (width - 20) / 50;
        int countY = (height - 20) / 50;
        windowColors = new int[countX][countY];
        for (int i = 0; i < countX; i++) {
            for (int j = 0; j < countY; j++) {
                windowColors[i][j] = random.nextInt(2);
            }
        }
    }
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.fillRect(x, y - height, width, height);
        int countX = (width - 20) / 50;
        int countY = (height - 20) / 50;
        for (int i = 0; i < countX; i++) {
            int winStartX = x + 20 * i + 20 + 30 * i;
            for (int j = 0; j < countY; j++) {
                int winStartY = y - 20 - 20 * j - 30 * j;
                if (windowColors[i][j] == 1) {
                    g2d.setColor(Color.BLACK);
                } else {
                    g2d.setColor(Color.YELLOW);
                }
                g2d.fillRect(winStartX, winStartY - 20, 30, 30);
            }
        }
    }
}