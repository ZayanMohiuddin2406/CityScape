import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cityscape");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CityScape cityscape = new CityScape();
        frame.add(cityscape);
        frame.getBackground();
        frame.setBackground(Color.BLACK);
        frame.setSize(1400, 1000);
        frame.setVisible(true);
    }
}

