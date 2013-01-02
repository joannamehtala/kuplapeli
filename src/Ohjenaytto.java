import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;


public class Ohjenaytto extends JPanel {
	
	private static Image ohjenakyma = 
			Toolkit.getDefaultToolkit().createImage("media/ohjenakyma.png");
	private Ikkuna ikkuna;
	private Border reunus = BorderFactory.createEmptyBorder();
	
	public Ohjenaytto(Ikkuna ikkuna){
		this.ikkuna = ikkuna;
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(null);
		Insets insets = this.getInsets();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(ohjenakyma, 0, 0, this);
	}
	
}
