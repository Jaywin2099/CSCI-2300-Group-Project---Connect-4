import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

public class CircularButton extends JButton {

    private Color color;

	public CircularButton() {
        super();
        setContentAreaFilled(false); // This ensures that the button is transparent
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.gray);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);
        
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }
    
    public void setColor(Color color) {
        this.color = color;
       
        repaint();
    }
	
}