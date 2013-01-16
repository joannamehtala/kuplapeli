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

/**
 * Häviönäkymä-luokka. Häviönäkymä näytetään, kun peli on hävitty.
 * @author 345480
 */
@SuppressWarnings("serial")
public class Havionakyma extends JPanel{
	
	/** Häviönäkymän taustakuva ja aloitusvalikkoon-napin kuva. */
	private static Image havionakyma = 
			Toolkit.getDefaultToolkit().createImage("media/havionakyma.png");
	private static ImageIcon aloitusvalikkoon_normal = 
			new ImageIcon("media/aloitusvalikkoon.png");
	
	/** Ikkuna ja voittonäkymä. */
	private Ikkuna ikkuna;
	private Voittonakyma voittonakyma;
	
	/** Aloitusvalikkoon-nappi ja sen reunus. */
	private JButton aloitusvalikkoon;
	private Border reunus = BorderFactory.createEmptyBorder();
	
	/** Ikkunan reunusten koot. */
	private Insets insets;

	/**
	 * Häviönäkymän konstruktori, jossa luodaan näkymä ja sijoitetaan siihen
	 * taustakuva ja aloitusvalikkoon-nappi.
	 * @param ikkuna
	 * @param voittonakyma
	 */
	public Havionakyma(Ikkuna ikkuna, Voittonakyma voittonakyma){
		/*
		 * Asetetaan paneelille koko ja tyhjä asettelija.
		 */
		this.ikkuna = ikkuna;
		this.voittonakyma = voittonakyma;
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(null);
		/*
		 * Määritellään ominaisuuksia napille.
		 */
		this.insets = this.getInsets();
		this.aloitusvalikkoon = new JButton();
		this.aloitusvalikkoon.setPreferredSize(new Dimension(200, 50));
		this.aloitusvalikkoon.setIcon(aloitusvalikkoon_normal);
		this.aloitusvalikkoon.setBorder(this.reunus);
		this.add(this.aloitusvalikkoon);
		this.aloitusvalikkoon.addMouseListener(new 
				Hiirikuuntelija_Aloitusvalikkoon(this.voittonakyma, 
						this.ikkuna));
		this.aloitusvalikkoon.setBounds(150 + this.insets.left, 330 + 
				this.insets.top, 200, 50);
	}
	
	/**
	 * Piirretään ruudulle taustakuva.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(havionakyma, 0, 0, this);
	}
}
