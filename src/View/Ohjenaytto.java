package View;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Controller.Hiirikuuntelija_Takaisin;


/**
 * Ohjenäytön luokka. Ohjenäytöllä näkyvät pelin ohjeet ja lisäksi se sisältää
 * nappulan, josta voi palata takaisin aloitusnäyttöön.
 * @author 345480
 *
 */
@SuppressWarnings("serial")
public class Ohjenaytto extends JPanel {

	/** Ohjenäkymän taustakuva ja takaisin-napin kuva. */
	private static Image ohjenakyma = 
			Toolkit.getDefaultToolkit().createImage("media/ohjenakyma.png");
	private static ImageIcon takaisin_normal = 
			new ImageIcon("media/takaisin.png");
	
	/** Ikkuna. */
	private Ikkuna ikkuna;
	
	/** Näkymän reunusten koot. */
	private Insets insets;
	
	/** Takaisin-nappula ja sen reunukset. */
	private JButton takaisin;
	private Border reunus = BorderFactory.createEmptyBorder();
	

	/**
	 * Ohjenäytön konstruktori. Luodaan takaisin-nappi ja asetetaan taustakuva.
	 * Lisäksi asetetaan haluttu koko ja käytetään tyhjää layoutmanageria jotta
	 * saadaan nappula asetettua haluttuun paikkaan koordinaattien mukaan.
	 * @param ikkuna
	 */
	public Ohjenaytto(Ikkuna ikkuna){

		/*
		 * Asetetaan paneelille koko ja tyhjä asettelija.
		 */
		this.ikkuna = ikkuna;
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(null);
		/*
		 * Määritellään ominaisuuksia napille.
		 */
		this.insets = this.getInsets();
		this.takaisin = new JButton();
		this.takaisin.setPreferredSize(new Dimension(100, 50));
		this.takaisin.setIcon(takaisin_normal);
		this.takaisin.setBorder(this.reunus);
		this.takaisin.addMouseListener(new Hiirikuuntelija_Takaisin(this, 
				this.ikkuna));
		this.add(this.takaisin);
		this.takaisin.setBounds(200 + this.insets.left, 420 + this.insets.top, 
				100, 50);

	}

	/**
	 * Piirretään ruudulle taustakuva.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(ohjenakyma, 0, 0, this);
	}

	/**
	 * Metodi, jolla voidaan asettaa takaisin-nappulalle kuva. Tätä tarvitaan,
	 * jotta hiirikuuntelijaluokassa voidaan asettaa uusi kuva kun hiiri
	 * liikutetaan nappulan päälle.
	 * @param i
	 */
	public void takaisin_asetaKuva(ImageIcon i){
		this.takaisin.setIcon(i);
	}
}
