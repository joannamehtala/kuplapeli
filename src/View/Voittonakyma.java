package View;

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

import Controller.Hiirikuuntelija_Aloitusvalikkoon;



@SuppressWarnings("serial")
public class Voittonakyma extends JPanel {
	private static Image voittonakyma = 
			Toolkit.getDefaultToolkit().createImage("media/voittonakyma.png");
	private Ikkuna ikkuna;
	private JButton aloitusvalikkoon;
	private static ImageIcon aloitusvalikkoon_normal = 
			new ImageIcon("media/aloitusvalikkoon.png");
	private Border reunus = BorderFactory.createEmptyBorder();
	private Insets insets;

	public Voittonakyma(Ikkuna ikkuna){
		/*
		 * Asetetaan paneelille koko ja tyhj� asettelija.
		 */
		this.ikkuna = ikkuna;
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(null);
		/*
		 * M��ritell��n ominaisuuksia napille.
		 */
		this.insets = this.getInsets();
		this.aloitusvalikkoon = new JButton();
		this.aloitusvalikkoon.setPreferredSize(new Dimension(200, 50));
		this.aloitusvalikkoon.setIcon(aloitusvalikkoon_normal);
		this.aloitusvalikkoon.setBorder(this.reunus);
		this.aloitusvalikkoon.addMouseListener(new 
				Hiirikuuntelija_Aloitusvalikkoon(this, this.ikkuna));
		this.add(this.aloitusvalikkoon);
		this.aloitusvalikkoon.setBounds(150 + this.insets.left, 330 + 
				this.insets.top, 200, 50);
	}
	
	/**
	 * Piirret��n ruudulle taustakuva.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(voittonakyma, 0, 0, this);
	}
	
	/**
	 * Metodi, jolla voidaan asettaa takaisin-nappulalle kuva. T�t� tarvitaan,
	 * jotta hiirikuuntelijaluokassa voidaan asettaa uusi kuva kun hiiri
	 * liikutetaan nappulan p��lle.
	 * @param i
	 */
	public void aloitusvalikkoon_asetaKuva(ImageIcon i){
		this.aloitusvalikkoon.setIcon(i);
	}
}