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
 * Ohjen‰ytˆn luokka. Ohjen‰ytˆll‰ n‰kyv‰t pelin ohjeet ja lis‰ksi se sis‰lt‰‰
 * nappulan, josta voi palata takaisin aloitusn‰yttˆˆn.
 * @author 345480
 *
 */
@SuppressWarnings("serial")
public class Ohjenaytto extends JPanel {
	
	/**
	 * Attribuuteiksi annetaan ohjen‰kym‰n taustakuva ja takaisin-nappulan kuva.
	 * Lis‰ksi annetaan itse nappula, ikkuna, tyhj‰ reunus ja insetsit.
	 */
	private static Image ohjenakyma = 
			Toolkit.getDefaultToolkit().createImage("media/ohjenakyma.png");
	private Ikkuna ikkuna;
	private JButton takaisin;
	private static ImageIcon takaisin_normal = new ImageIcon("media/takaisin.png");
	private Border reunus = BorderFactory.createEmptyBorder();
	private Insets insets;
	
	/**
	 * Konstruktorissa alustetaan attribuutit, asetetaan haluttu koko,
	 * asetetaan tyhj‰ LayoutManager jotta voidaan m‰‰ritell‰ pikseleiden
	 * avulla nappulan paikka ja lis‰t‰‰n nappula ominaisuuksineen.
	 * @param ikkuna
	 */
	public Ohjenaytto(Ikkuna ikkuna){
		
		/*
		 * Asetetaan paneelille koko ja tyhj‰ asettelija.
		 */
		this.ikkuna = ikkuna;
		this.setPreferredSize(new Dimension(500, 600));
		this.setLayout(null);
		/*
		 * M‰‰ritell‰‰n ominaisuuksia napille.
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
	 * Piirret‰‰n ruudulle taustakuva.
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(ohjenakyma, 0, 0, this);
	}
	
	/**
	 * Metodi, jolla voidaan asettaa takaisin-nappulalle kuva. T‰t‰ tarvitaan,
	 * jotta hiirikuuntelijaluokassa voidaan asettaa uusi kuva kun hiiri
	 * liikutetaan nappulan p‰‰lle.
	 * @param i
	 */
	public void takaisin_asetaKuva(ImageIcon i){
		this.takaisin.setIcon(i);
	}
}
