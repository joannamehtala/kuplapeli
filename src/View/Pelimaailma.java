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
import java.io.File;
import java.util.Iterator;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

import Controller.Hiirikuuntelija_Valikkoon;
import Model.Kupla;
import Model.Maailma;



/**
 * 
 * Pelimaailma-luokka: graafinen pelimaailma.
 * @author 345480
 *
 */
public class Pelimaailma extends JPanel implements MouseListener,
MouseMotionListener {

	/** Swingin takia, muuten alkaa valittamaan. */
	private static final long serialVersionUID = -2718553638694558080L;
	
	/** Maailma ja ikkuna. */
	private Maailma maailma;
	private Ikkuna ikkuna;
	
	/** Kulma, jossa suuntaviiva liikkuu ja johon kupla ammutaan. */
	private double kulma;
	
	/** Pelimaailman taustakuva. */
	private static final Image taustakuva =
			Toolkit.getDefaultToolkit().createImage("media/taustakuva1.png");
	
	/** Valikkoon-napin kuva. */
	private ImageIcon valikkoon_normal = new ImageIcon("media/valikkoon.png");
	
	/** Valikkoon-nappi ja sen reunukset. */
	private JButton valikkoon;
	private Border reunus = BorderFactory.createEmptyBorder();
	
	/** Ikkunan reunusten koot. */
	private Insets insets;
	
	/** Kuplan l‰htˆpaikka. */
	public static final int LAHTO_X = 250;
	public static final int LAHTO_Y = 475;


	/**
	 * Pelimaailman konstruktori, jossa alustetaan attribuutit. 
	 * Lis‰ksi annetaan paneelille haluttu koko ja lis‰t‰‰n pelimaailmalle
	 * mouselistenerit. Luodaan myˆs valikkoon-nappi ja asetetaan sille
	 * ominaisuuksia.
	 * 
	 * @param ikkuna
	 */
	public Pelimaailma(Ikkuna ikkuna){
		this.ikkuna = ikkuna;
		this.maailma = new Maailma(this);
		this.setPreferredSize(new Dimension(maailma.annaLeveys() + 50,
				maailma.annaKorkeus() + 100));
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
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
	 * Asettaa valikkoon-napille kuvan (t‰m‰n avulla hiirikuuntelijaluokassa
	 * voidaan muuttaa napin kuvaa).
	 * @param i
	 */
	public void valikkoon_asetaKuva(ImageIcon i){
		this.valikkoon.setIcon(i);
	}

	/**
	 * Piirret‰‰n maailmaan taustakuva, kuplat ja suuntaviiva.
	 */
	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;

		/*
		 * Piirret‰‰n taustakuva.
		 */
		g2d.drawImage(taustakuva, 0, 0, this);

		/*
		 * Piirret‰‰n maailman kuplat.
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
		 * Piirret‰‰n suuntaviiva.
		 */
		double radkulma = Math.toRadians(this.kulma);
		double loppuX = LAHTO_X + Math.cos(radkulma)*35;
		double loppuY = LAHTO_Y + Math.sin(radkulma)*35;

		g2d.setColor(Color.BLACK);
		g2d.drawLine(LAHTO_X, LAHTO_Y, (int) loppuX, (int) loppuY);
	}


	/**
	 * Kun klikataan pelimaailmaa, ammutaan kupla.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.maailma.ammuNykyinen(this.kulma);
	}

	/**
	 * Metodi m‰‰ritt‰‰, mit‰ tapahtuu, kun hiirt‰ liikutetaan ruudulla.
	 * Seurataan hiiren koordinaatteja ja tallennetaan ne muuttujiin. Niiden
	 * avulla lasketaan kulma, jonka mukaisesti hiirt‰ seuraava suuntaviiva 
	 * liikkuu (ja jossa kupla l‰htee liikkeelle).
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		int hiirix = arg0.getX();
		int hiiriy = arg0.getY();

		double radkulma = Math.atan2(hiiriy - LAHTO_Y, hiirix - LAHTO_X );
		this.kulma = Math.toDegrees(radkulma);
		this.repaint();
	}

	/**
	 * Palauttaa kulman, jossa kupla l‰htee liikkeelle.
	 * @return
	 */
	public double annaKulma(){
		return this.kulma;
	}

	/**
	 * Lopettaa pelin. Jos peli on voitettu, siirryt‰‰n voitton‰kym‰‰n, ja jos
	 * se on h‰vitty, siirryt‰‰n h‰viˆn‰kym‰‰n.
	 */
	public void lopetaPeli(boolean voitettu){
		if (voitettu){
			this.ikkuna.vaihdaVoittonakymaan();

		} else {
			this.ikkuna.vaihdaHavionakymaan();
		}
	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei se teek‰‰n
	 * mit‰‰n.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// tyhj‰ metodi

	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei se teek‰‰n
	 * mit‰‰n.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// tyhj‰ metodi

	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei se teek‰‰n
	 * mit‰‰n.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhj‰ metodi

	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei se teek‰‰n
	 * mit‰‰n.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhj‰ metodi

	}

	/**
	 * MouseMotionListenerin implementointi vaatii t‰m‰n metodin, vaikkei se
	 * teek‰‰n mit‰‰n.
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// tyhj‰ metodi

	}
}