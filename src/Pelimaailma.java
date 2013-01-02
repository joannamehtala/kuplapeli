
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

/**
 * Pelimaailma on Maailma-luokan graafinen vastine, käyttäjälle näkyvä versio.
 * 
 * @author Joanna
 *
 */
public class Pelimaailma extends JPanel implements MouseListener,
			MouseMotionListener {

	/**
	 * Swingin takia tuollainen attribuutti, muuten valittaa.
	 * Muiksi attribuuteiksi annetaan maailma, ikkuna, ohjaaja ja aktiivinen
	 * kupla, koska pelimaailma tarvitsee tietoja kaikilta näiltä. Lisäksi
	 * annetaan taustakuva.
	 */
	private static final long serialVersionUID = -2718553638694558080L;
	private Maailma maailma;
	private Ikkuna ikkuna;
	private Ohjaaja ohjaaja;
	private AktiivinenKupla nykyinen;
	private AktiivinenKupla seuraava;
	private static final Image taustakuva =
			Toolkit.getDefaultToolkit().createImage("media/taustakuva1.png");
	public static double seurattux;
	public static double seurattuy;
	public double kulma;
	private boolean klikattu;

	/**
	 * Pelimaailman konstruktori, jossa alustetaan attribuutit ja asetetaan
	 * paneeli taustakuvan näkyvyyden takia läpinäkyväksi. Lisäksi annetaan
	 * paneelille haluttu koko.
	 * 
	 * @param maailma
	 * @param ikkuna
	 */
	public Pelimaailma(Maailma maailma, Ikkuna ikkuna){
		this.maailma = maailma;
		this.ikkuna = ikkuna;
		this.nykyinen = maailma.annaNykyinen();
		this.seuraava = maailma.annaSeuraava();
		this.ohjaaja = new Ohjaaja(this, nykyinen);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(maailma.annaLeveys() + 50,
				maailma.annaKorkeus() + 100));
		this.klikattu = false;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}

	/**
	 * Piirretään maailmaan taustakuva ja kupla.
	 */
	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(taustakuva, 0, 0, this);
		g2d.drawImage(nykyinen.annaKuva(),
				(int) nykyinen.annaX(), (int) nykyinen.annaY(), this);
		g2d.drawImage(seuraava.annaKuva(), (int) seuraava.annaX(),
				(int) seuraava.annaY(), this);
		g2d.setColor(Color.WHITE);
		g2d.drawLine(250, 475, (int) seurattux + 250, 
				(int) seurattuy + 454);

		/*Graphics2D g2d2 = (Graphics2D)g;
		g2d2.translate(25, 50);
		g2d2.rotate(0, 200, 410);
		g2d2.drawImage(this.maailma.annaTykki().annaKuva(), 200, 410, this);*/
	}

	public boolean onKlikattu(){
		return this.klikattu;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.klikattu = true;

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		int hiirix = arg0.getX();
		int hiiriy = arg0.getY();
		double deltax = hiirix - 250;
		double deltay = hiiriy - 454;
		this.kulma = Math.atan(deltay / deltax);
		
		if (hiirix >= 250){
			seurattux = Math.cos(this.kulma)*15;
			seurattuy = Math.sin(this.kulma)*15;
		} else {
			seurattux = Math.cos(this.kulma)*-15;
			seurattuy = Math.sin(this.kulma)*-15;
		}
		
		this.repaint();

	}
	
	public double annaKulma(){
		return this.kulma;
	}
}