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

/**
 * Aloitusn�yt�n luokka. Aloitusn�yt�ll� on taustakuva ja nappulat, joita
 * painamalla voi aloittaa pelin, saada ohjeet n�kyviin tai lopettaa ohjelman
 * suorituksen.
 * @author 345480
 *
 */
@SuppressWarnings("serial")
public class Aloitusnaytto extends JPanel {

	/** Attribuutteina aloitusn�yt�n taustakuva ja nappuloiden kuvat.*/
	private static final Image kuplapeli =
			Toolkit.getDefaultToolkit().createImage("media/kuplapeli.png");
	private static final ImageIcon aloita_normal = 
			new ImageIcon("media/aloita.png");
	private static final ImageIcon ohjeet_normal = 
			new ImageIcon("media/ohjeet.png");
	private static final ImageIcon lopeta_normal = 
			new ImageIcon("media/lopeta.png");
	
	/** Asetetaan attribuuteiksi aloitusn�yt�n nappulat ja niille reunukset.*/
	private JButton aloitus;
	private JButton ohjeet;
	private JButton lopeta;
	private Border reunus = BorderFactory.createEmptyBorder();
	
	/** Attribuutteina viel� ikkuna tiedonsaantia varten sek� ikkunan reunat.*/
	private Ikkuna ikkuna;
	private Insets insets;

	/**
	 * Konstruktorissa alustetaan attribuutteja ja asetetaan paneelille koko.
	 * LayoutManager on null koska n�in voidaan asettaa nappuloille paikat
	 * pikseleiden avulla. Lis�ksi m��ritell��n nappuloiden ominaisuudet ja
	 * lis�t��n ne paneeliin.
	 * @param ikkuna
	 */
	public Aloitusnaytto(Ikkuna ikkuna){
		this.ikkuna = ikkuna;
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(null);
		this.insets = this.getInsets();

		/*
		 * Aloitusnapin ominaisuudet.
		 */
		this.aloitus = new JButton();
		this.aloitus.setIcon(aloita_normal);
		this.aloitus.setPreferredSize(new Dimension(81, 39));
		this.aloitus.setBorder(reunus);
		this.aloitus.addMouseListener(new Hiirikuuntelija_Aloita(this, 
				this.ikkuna));
		this.add(this.aloitus);
		this.aloitus.setBounds(120 + this.insets.left,145 + this.insets.top, 
				81, 39);

		/*
		 * Ohjenapin ominaisuudet.
		 */
		this.ohjeet = new JButton();
		this.ohjeet.setIcon(ohjeet_normal);
		this.ohjeet.setPreferredSize(new Dimension(93, 37));
		this.ohjeet.setBorder(reunus);
		this.ohjeet.addMouseListener(new Hiirikuuntelija_Ohjeet(this, 
				this.ikkuna));
		this.add(this.ohjeet);
		this.ohjeet.setBounds(205 + this.insets.left, 180 + this.insets.top, 
				93, 37);

		/*
		 * Lopetusnapin ominaisuudet.
		 */
		this.lopeta = new JButton();
		this.lopeta.setIcon(lopeta_normal);
		this.lopeta.setPreferredSize(new Dimension(89,50));
		this.lopeta.setBorder(reunus);
		this.lopeta.addMouseListener(new Hiirikuuntelija_Lopeta(this));
		this.add(this.lopeta);
		this.lopeta.setBounds(310 + this.insets.left, 155 + this.insets.top,
				89, 50);

	}

	/**
	 * Metodilla piirret��n taustakuva.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(kuplapeli, 0, 0, this);
	}

	/**
	 * Metodilla voi asettaa aloitusnapille kuvan. T�t� tarvitaan, jotta
	 * hiirikuntelijaluokassa voidaan asettaa napille uusi kuva, kun hiirt�
	 * liikutetaan napin p��lle.
	 * @param i, aloitusnapin kuva
	 */
	public void aloita_asetaKuva(ImageIcon i){
		this.aloitus.setIcon(i);
	}

	/**
	 * Metodilla voi asettaa ohjenapille kuvan. T�t� tarvitaan, jotta
	 * hiirikuntelijaluokassa voidaan asettaa napille uusi kuva, kun hiirt�
	 * liikutetaan napin p��lle.
	 * @param i, ohjenapin kuva
	 */
	public void ohjeet_asetaKuva(ImageIcon i){
		this.ohjeet.setIcon(i);
	}

	/**
	 * Metodilla voi asettaa lopetusnapille kuvan. T�t� tarvitaan, jotta
	 * hiirikuuntelijaluokassa voidaan asettaa napille uusi kuva, kun hiiri
	 * siirret��n napin p��lle.
	 * @param i, lopetusnapin kuva
	 */
	public void lopeta_asetaKuva(ImageIcon i){
		this.lopeta.setIcon(i);
	}
}
