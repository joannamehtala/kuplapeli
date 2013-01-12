import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;


@SuppressWarnings("serial")
public class Havionakyma extends JPanel{
	private static Image havionakyma = 
			Toolkit.getDefaultToolkit().createImage("media/havionakyma.png");
	private Ikkuna ikkuna;
	private Voittonakyma voittonakyma;
	private JButton aloitusvalikkoon;
	private static ImageIcon aloitusvalikkoon_normal = 
			new ImageIcon("media/aloitusvalikkoon.png");
	private Border reunus = BorderFactory.createEmptyBorder();
	private Insets insets;

	public Havionakyma(Ikkuna ikkuna, Voittonakyma voittonakyma){
		/*
		 * Asetetaan paneelille koko ja tyhj‰ asettelija.
		 */
		this.ikkuna = ikkuna;
		this.voittonakyma = voittonakyma;
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(null);
		/*
		 * M‰‰ritell‰‰n ominaisuuksia napille.
		 */
		this.insets = this.getInsets();
		this.aloitusvalikkoon = new JButton();
		this.aloitusvalikkoon.setPreferredSize(new Dimension(200, 50));
		this.aloitusvalikkoon.setIcon(aloitusvalikkoon_normal);
		this.aloitusvalikkoon.setBorder(this.reunus);
		this.aloitusvalikkoon.addMouseListener(new 
				Hiirikuuntelija_Aloitusvalikkoon(this.voittonakyma, this.ikkuna));
		this.add(this.aloitusvalikkoon);
		this.aloitusvalikkoon.setBounds(150 + this.insets.left, 330 + 
				this.insets.top, 200, 50);
	}
	
	/**
	 * Piirret‰‰n ruudulle taustakuva.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(havionakyma, 0, 0, this);
	}
}
