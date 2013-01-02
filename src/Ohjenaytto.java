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
	private JButton takaisin;
	private static ImageIcon takaisin_normal = new ImageIcon("media/takaisin.png");
	private Border reunus = BorderFactory.createEmptyBorder();
	private Insets insets;
	
	public Ohjenaytto(Ikkuna ikkuna){
		this.ikkuna = ikkuna;
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(null);
		this.insets = this.getInsets();
		this.takaisin = new JButton();
		this.takaisin.setPreferredSize(new Dimension(100, 50));
		this.takaisin.setIcon(takaisin_normal);
		this.takaisin.setBorder(this.reunus);
		this.takaisin.addMouseListener(new Hiirikuuntelija_Takaisin(this, this.ikkuna));
		this.add(this.takaisin);
		this.takaisin.setBounds(200 + this.insets.left, 420 + this.insets.top, 
				100, 50);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(ohjenakyma, 0, 0, this);
	}
	
	public void takaisin_asetaKuva(ImageIcon i){
		this.takaisin.setIcon(i);
	}
}
