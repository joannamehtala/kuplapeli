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
 * Aloitusnäytön luokka. Aloitusnäytöllä on taustakuva ja nappulat, joita
 * painamalla voi aloittaa pelin, saada ohjeet näkyviin tai lopettaa ohjelman
 * suorituksen.
 * @author 345480
 *
 */
@SuppressWarnings("serial")
public class Aloitusnaytto extends JPanel {

	/**
	 * Attribuuteiksi asetetaan aloitusnäytön taustakuva sekä aloita- ja 
	 * ohjenappulan kuvat. Lisäksi asetetaan attribuuteikse itse nappulat,
	 * ikkuna, nappuloiden reunukset sekä insetsit.
	 */
	private static final Image kuplapeli =
			Toolkit.getDefaultToolkit().createImage("media/kuplapeli.png");
	private static final ImageIcon aloita_normal = 
			new ImageIcon("media/aloita.png");
	private static ImageIcon ohjeet_normal = new ImageIcon("media/ohjeet.png");
	private JButton aloitus;
	private JButton ohjeet;
	private Ikkuna ikkuna;
	private Border reunus = BorderFactory.createEmptyBorder();
	private Insets insets;

	/**
	 * Konstruktorissa alustetaan attribuutteja ja asetetaan paneelille koko.
	 * LayoutManager on null koska näin voidaan asettaa nappuloille paikat
	 * pikseleiden avulla. Lisäksi määritellään nappuloiden ominaisuudet ja
	 * lisätään ne paneeliin.
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
		this.ohjeet.setBounds(205 + this.insets.left, 170 + this.insets.top, 
				93, 37);

	}

	/**
	 * Metodilla piirretään taustakuva.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(kuplapeli, 0, 0, this);
	}
	
	/**
	 * Metodilla voi asettaa aloitusnapille kuvan. Tätä tarvitaan, jotta
	 * hiirikuntelijaluokassa voidaan asettaa napille uusi kuva, kun hiirtä
	 * liikutetaan kuvan päälle.
	 * @param i
	 */
	public void aloita_asetaKuva(ImageIcon i){
		this.aloitus.setIcon(i);
	}
	
	/**
	 * Metodilla voi asettaa ohjenapille kuvan. Tätä tarvitaan, jotta
	 * hiirikuntelijaluokassa voidaan asettaa napille uusi kuva, kun hiirtä
	 * liikutetaan kuvan päälle.
	 * @param i
	 */
	public void ohjeet_asetaKuva(ImageIcon i){
		this.ohjeet.setIcon(i);
	}
}
