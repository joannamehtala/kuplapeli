
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

/**
 * Pelimaailma-luokassa luodaan graafinen pelipaneeli, jossa pelaaminen
 * tapahtuu.
 * 
 * @author 345480
 *
 */
public class Pelimaailma extends JPanel implements MouseListener,
MouseMotionListener {

	/**
	 * Ensimm‰inen attribuutti on Swingin takia, muuten alkaa valittamaan.
	 * Muiksi attribuuteiksi annetaan maailma, ikkuna, maailman nykyinen ja
	 * seuraava aktiivinen kupla, sek‰ kulma, joka kertoo mihin suuntaan
	 * hiirt‰ seuraava viiva piirret‰‰n k‰ytt‰j‰lle. Hiirt‰ seurataan
	 * tallentamalla sen sijainti seurattux ja seurattuy -attribuutteihin.
	 * Asetetaan myˆs boolean-tyyppinen attribuutti klikattu, joka kertoo, 
	 * onko ruutua klikattu (jos on, ammutaan kupla). 
	 */
	private static final long serialVersionUID = -2718553638694558080L;
	private Maailma maailma;
	private Ikkuna ikkuna;
	private double kulma;
	private static final Image taustakuva =
			Toolkit.getDefaultToolkit().createImage("media/taustakuva1.png");
	private ImageIcon valikkoon_normal = new ImageIcon("media/valikkoon.png");
	private Border reunus = BorderFactory.createEmptyBorder();
	private Insets insets;
	private JButton valikkoon;
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
		 * Piirret‰‰n nykyinen kupla.
		 */

		Iterator<Kupla> iteraattori = this.maailma.kuplaiteraattori();
		while (iteraattori.hasNext()){
			Kupla kupla = iteraattori.next();
			g2d.drawImage(kupla.annaKuva(),
					(int) kupla.annaX(), (int) kupla.annaY(), this);
		}

		/*
		 * Piirret‰‰n viiva.
		 */
		double radkulma = Math.toRadians(this.kulma);
		double loppuX = LAHTO_X + Math.cos(radkulma)*35;
		double loppuY = LAHTO_Y + Math.sin(radkulma)*35;

		g2d.setColor(Color.BLACK);
		g2d.drawLine(LAHTO_X, LAHTO_Y, (int) loppuX, (int) loppuY);
	}


	/**
	 * Asetetaan klikkauksen yhteydess‰ attribuutti klikattu todeksi.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.maailma.ammuNykyinen(this.kulma);
	}

	/**
	 * Metodi m‰‰ritt‰‰, mit‰ tapahtuu, kun hiirt‰ liikutetaan ruudulla.
	 * Seurataan hiiren koordinaatteja ja tallennetaan ne muuttujiin. Niiden
	 * avulla lasketaan kulma, jonka mukaisesti hiirt‰ seuraava suuntaviiva 
	 * liikkuu.
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
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei sit‰
	 * ylikirjoiteta t‰ss‰.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei sit‰
	 * ylikirjoiteta t‰ss‰.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei sit‰
	 * ylikirjoiteta t‰ss‰.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei sit‰
	 * ylikirjoiteta t‰ss‰.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * MouseMotionListenerin implementointi vaatii t‰m‰n metodin, vaikkei sit‰
	 * ylikirjoiteta t‰ss‰.
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}