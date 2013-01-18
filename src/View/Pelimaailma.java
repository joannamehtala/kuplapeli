package View;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Controller.Hiirikuuntelija_Pelimaailma;
import Controller.Hiirikuuntelija_Valikkoon;
import Model.Kupla;
import Model.Maailma;


/**
 * 
 * Pelimaailma-luokka: graafinen pelimaailma.
 * @author 345480
 *
 */
public class Pelimaailma extends JPanel /*implements MouseListener,
MouseMotionListener*/ {

	/** Swingin takia, muuten alkaa valittamaan. */
	private static final long serialVersionUID = -2718553638694558080L;

	/** Maailma ja ikkuna. */
	private Maailma maailma;
	private Ikkuna ikkuna;

	/** Kulma, jossa suuntaviiva liikkuu ja johon kupla ammutaan. */
	//private double kulma;

	/** Pelimaailman taustakuva. */
	private static final Image taustakuva =
			Toolkit.getDefaultToolkit().createImage("media/taustakuva.png");

	/** Valikkoon-napin kuva. */
	private ImageIcon valikkoon_normal = new ImageIcon("media/valikkoon.png");

	/** Valikkoon-nappi ja sen reunukset. */
	private JButton valikkoon;
	private Border reunus = BorderFactory.createEmptyBorder();

	/** Ikkunan reunusten koot. */
	private Insets insets;

	/** Kuplan lähtöpaikka. */
	public static final int LAHTO_X = 250;
	public static final int LAHTO_Y = 479;

	/** Pelimaailman hiirikuuntelija. */
	private Hiirikuuntelija_Pelimaailma hiirikuuntelija;


	/**
	 * Pelimaailman konstruktori, jossa alustetaan attribuutit. 
	 * Lisäksi annetaan paneelille haluttu koko ja lisätään pelimaailmalle
	 * mouselistenerit. Luodaan myös valikkoon-nappi ja asetetaan sille
	 * ominaisuuksia.
	 * 
	 * @param ikkuna
	 */
	public Pelimaailma(Ikkuna ikkuna){
		this.ikkuna = ikkuna;
		this.maailma = new Maailma(this);
		this.setPreferredSize(new Dimension(maailma.annaLeveys() + 50,
				maailma.annaKorkeus() + 100));
		this.hiirikuuntelija = new Hiirikuuntelija_Pelimaailma(this, 
				this.maailma);
		this.addMouseMotionListener(this.hiirikuuntelija);
		this.addMouseListener(this.hiirikuuntelija);
		this.setLayout(null);

		/*
		 * Valikkonapin ominaisuudet.
		 */
		this.valikkoon = new JButton();
		this.insets = this.getInsets();
		this.valikkoon = new JButton();
		this.valikkoon.setPreferredSize(new Dimension(130, 50));
		this.valikkoon.setIcon(valikkoon_normal);
		this.valikkoon.setBorder(this.reunus);
		this.valikkoon.addMouseListener(new Hiirikuuntelija_Valikkoon(this, 
				this.ikkuna));
		this.add(this.valikkoon);
		this.valikkoon.setBounds(280 + this.insets.left, 530 + this.insets.top, 
				130, 50);
	}

	/**
	 * Asettaa valikkoon-napille kuvan (tämän avulla hiirikuuntelijaluokassa
	 * voidaan muuttaa napin kuvaa).
	 * @param i
	 */
	public void valikkoon_asetaKuva(ImageIcon i){
		this.valikkoon.setIcon(i);
	}

	/**
	 * Piirretään maailmaan taustakuva, kuplat ja suuntaviiva.
	 */
	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		/*
		 * Piirretään taustakuva.
		 */
		g2d.drawImage(taustakuva, 0, 0, this);

		/*
		 * Piirretään maailman kuplat.
		 */

		Iterator<Kupla> iteraattori = this.maailma.kuplaiteraattori();
		while (iteraattori.hasNext()){
			Kupla kupla = iteraattori.next();
			if (kupla.onEhja()){
				g2d.drawImage(kupla.annaKuva(),
						(int) kupla.annaX(), (int) kupla.annaY(), this);
			}
		}

		/*
		 * Piirretään suuntaviiva.
		 */
		double radkulma = Math.toRadians(this.hiirikuuntelija.annaKulma());
		double loppuX = LAHTO_X + Math.cos(radkulma)*35;
		double loppuY = LAHTO_Y + Math.sin(radkulma)*35;

		g2d.setColor(Color.BLACK);
		g2d.drawLine(LAHTO_X, LAHTO_Y, (int) loppuX, (int) loppuY);
	}


//	/**
//	 * Kun klikataan pelimaailmaa, ammutaan kupla.
//	 */
//	@Override
//	public void mouseClicked(MouseEvent arg0) {
//		this.maailma.ammuNykyinen(this.kulma);
//	}
//
//	/**
//	 * Metodi määrittää, mitä tapahtuu, kun hiirtä liikutetaan ruudulla.
//	 * Seurataan hiiren koordinaatteja ja tallennetaan ne muuttujiin. Niiden
//	 * avulla lasketaan kulma, jonka mukaisesti hiirtä seuraava suuntaviiva 
//	 * liikkuu (ja jossa kupla lähtee liikkeelle).
//	 */
//	@Override
//	public void mouseMoved(MouseEvent arg0) {
//		int hiirix = arg0.getX();
//		int hiiriy = arg0.getY();
//
//		double radkulma = Math.atan2(hiiriy - LAHTO_Y, hiirix - LAHTO_X );
//		this.kulma = Math.toDegrees(radkulma);
//
//		if (this.kulma > 0 && this.kulma < 90){
//			this.kulma = -5;
//		}
//
//		if (this.kulma > 90 && this.kulma < 180){
//			this.kulma = -175;
//		}
//
//		this.repaint();
//	}

	/**
	 * Lopettaa pelin. Jos peli on voitettu, siirrytään voittonäkymään, ja jos
	 * se on hävitty, siirrytään häviönäkymään.
	 */
	public void lopetaPeli(boolean voitettu){
		if (voitettu){
			this.ikkuna.vaihdaVoittonakymaan();

		} else {
			this.ikkuna.vaihdaHavionakymaan();
		}
	}

//	/**
//	 * MouseListenerin implementointi vaatii tämän metodin, vaikkei se teekään
//	 * mitään.
//	 */
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// tyhjä metodi
//
//	}
//
//	/**
//	 * MouseListenerin implementointi vaatii tämän metodin, vaikkei se teekään
//	 * mitään.
//	 */
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// tyhjä metodi
//
//	}
//
//	/**
//	 * MouseListenerin implementointi vaatii tämän metodin, vaikkei se teekään
//	 * mitään.
//	 */
//	@Override
//	public void mousePressed(MouseEvent arg0) {
//		// tyhjä metodi
//
//	}
//
//	/**
//	 * MouseListenerin implementointi vaatii tämän metodin, vaikkei se teekään
//	 * mitään.
//	 */
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// tyhjä metodi
//
//	}
//
//	/**
//	 * MouseMotionListenerin implementointi vaatii tämän metodin, vaikkei se
//	 * teekään mitään.
//	 */
//	@Override
//	public void mouseDragged(MouseEvent arg0) {
//		// tyhjä metodi
//
//	}
}