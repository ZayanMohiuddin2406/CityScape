import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.*;
public class Car extends JPanel {
    protected static int x;
    private int y;
    private int speed;
    private BufferedImage img;
    private boolean flipped = false;
    private boolean Beem;
    public Car(int x, int y, BufferedImage img, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.img = img;
    }
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            Beem = true;
        }
    }
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Beem = false;
        }
    }
    private BufferedImage flipImageHorizontally(BufferedImage img) {
        BufferedImage flippedImage = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

        Graphics2D g = flippedImage.createGraphics();
        g.drawImage(img, img.getWidth(), 0, 0, img.getHeight(), 0, 0, img.getWidth(), img.getHeight(), null);
        g.dispose();

        return flippedImage;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (flipped) {
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-img.getWidth(null), 0);
            AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            img = op.filter(img, null);
        }
        g2d.drawImage(img, x, y,img.getWidth()/4,img.getHeight()/4, null);
    }
    public void move() {
        x += speed;

        if (x + img.getWidth()/4 > 1400||x<0) {
            speed =-speed;
            img =flipImageHorizontally(img);
        }
        if (Beem && x == CityScape.getDrivableUFOX()){
            speed=0;
            for (int j=0; y>CityScape.getDrivableUFOY();j++){
                y=-1;
            }
        }
        if (!Beem&&y!=800){
            y=800;
            speed=0;
            x+=speed;
        }
    }
}

