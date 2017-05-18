import javax.swing.JPanel;
import java.awt.Graphics;

public class Drawing extends JPanel {
    public void paint(Graphics g) {
        setSize(1000, 200);
        g.drawOval(100,100,100,100);
    }
}
