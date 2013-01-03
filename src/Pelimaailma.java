
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

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
	private AktiivinenKupla nykyinen;
	private AktiivinenKupla seuraava;
	private double kulma;
	private boolean klikattu;
	public static double seurattux;
	public static double seurattuy;
	private double deltax;
	private double deltay;
	private Ohjaaja ohjaaja;
	private static final Image taustakuva =
			Toolkit.getDefaultToolkit().createImage("media/taustakuva1.png");


	/**
	 * Pelimaailman konstruktori, jossa alustetaan attribuutit. 
	 * Lis‰ksi annetaan paneelille haluttu koko ja lis‰t‰‰n pelimaailmalle
	 * mouselistenerit.
	 * 
	 * @param ikkuna
	 */
	public Pelimaailma(Ikkuna ikkuna){
		this.ikkuna = ikkuna;
		this.maailma = new Maailma(this);
		this.nykyinen = maailma.annaNykyinen();
		this.seuraava = maailma.annaSeuraava();
		this.ohjaaja = new Ohjaaja(this, this.nykyinen);
		this.setPreferredSize(new Dimension(maailma.annaLeveys() + 50,
				maailma.annaKorkeus() + 100));
		this.klikattu = false;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
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
		g2d.drawImage(this.nykyinen.annaKuva(),
				(int) nykyinen.annaX(), (int) nykyinen.annaY(), this);
		/*
		 * Piirret‰‰n seuraava kupla.
		 */
		g2d.drawImage(this.seuraava.annaKuva(), (int) seuraava.annaX(),
				(int) seuraava.annaY(), this);
		/*
		 * Piirret‰‰n viiva.
		 */
		g2d.setColor(Color.BLACK);
		g2d.drawLine(250, 475, (int) seurattux + 250, 
				(int) seurattuy + 454);
	}

	/**
	 * Metodi kertoo, onko peliruutua klikattu. Jos on, palauttaa true, jos ei,
	 * palauttaa false.
	 * @return this.klikattu
	 */
	public boolean onKlikattu(){
		return this.klikattu;
	}

	/**
	 * Asetetaan klikkauksen yhteydess‰ attribuutti klikattu todeksi.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {

		int hiirix = arg0.getX();
		int hiiriy = arg0.getY();
		double cos = hiirix - 250;
		double sin = 454 - hiiriy;
		this.kulma = Math.toDegrees(Math.atan2(sin, cos));

		if (this.kulma > -90 && this.kulma < 0){
			kulma = 15;
		}

		if (this.kulma < -90){
			kulma = 165;
		}

		this.klikattu = true;
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
		this.deltax = hiirix - 250;
		this.deltay = hiiriy - 454;
		double viivakulma = Math.atan(deltay / deltax);

		if (hiirix >= 250){
			seurattux = Math.cos(viivakulma)*15;
			seurattuy = Math.sin(viivakulma)*15;
		} else {
			seurattux = Math.cos(viivakulma)*-15;
			seurattuy = Math.sin(viivakulma)*-15;
		}

		this.repaint();

	}

	/**
	 * Palauttaa kulman, jossa suuntaviivaa k‰‰nnell‰‰n.
	 * @return
	 */
	public double annaKulma(){
		return this.kulma;
	}
	
	public void asetaKulma(double kulma){
		this.kulma = kulma;
	}
	
	public AktiivinenKupla annaNykyinen(){
		return this.nykyinen;
	}
	
	public AktiivinenKupla annaSeuraava(){
		return this.seuraava;
	}
	
	public Maailma annaMaailma(){
		return this.maailma;
	}
	
	public void asetaKlikattu(boolean klikattu){
		this.klikattu = klikattu;
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