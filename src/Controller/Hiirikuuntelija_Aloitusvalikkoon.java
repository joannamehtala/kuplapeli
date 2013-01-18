package Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Ikkuna;
import View.Voittonakyma;

/**
 * Hiirikuuntelijaluokka voitto- ja häviönäkymän aloitusvalikoon-JButtonia
 * varten.
 * @author 345480
 *
 */

public class Hiirikuuntelija_Aloitusvalikkoon implements MouseListener {
	
	/** Aloitusvalikkoon-napin normaali kuva. */
	private static final ImageIcon aloitusvalikkoon_normal = 
			new ImageIcon("media/aloitusvalikkoon.png");
	
	/** Aloitusvalikkoon-napin kuva, kun hiiri liikutetaan sen päälle. */
	private static final ImageIcon aloitusvalikkoon_hiiri = 
			new ImageIcon("media/aloitusvalikkoon2.png");
	
	/** Voittonäkymä. */
	private Voittonakyma voittonakyma;
	
	/** Ikkuna. */
	private Ikkuna ikkuna;
	
	/**
	 * Hiirikuuntelijan konstruktori. Alustetaan attribuutit.
	 * @param voittonakyma
	 * @param ikkuna
	 */
	public Hiirikuuntelija_Aloitusvalikkoon(Voittonakyma voittonakyma,
			Ikkuna ikkuna){
		this.voittonakyma = voittonakyma;
		this.ikkuna = ikkuna;
	}
	
	/**
	 * Kun hiirtä klikataan, vaihdetaan aloitusnäyttöön.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaAloitusnayttoon();
	}
	
	/**
	 * Kun hiiri liikutetaan JButtonin päälle, asetetaan napille kuva, jossa
	 * on sama teksti pinkkinä.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.voittonakyma.aloitusvalikkoon_asetaKuva(aloitusvalikkoon_hiiri);
	}
	
	/**
	 * Kun hiiri liikutetaan pois napin päältä, asetetaan takaisin mustalla
	 * tekstillä varustettu kuva.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.voittonakyma.aloitusvalikkoon_asetaKuva(aloitusvalikkoon_normal);
	}
	
	/**
	 * Tyhjä metodi, jonka MouseListenerin implementointi vaatii.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhjä
	}
	
	/**
	 * Tyhjä metodi, jonka MouseListenerin implementointi vaatii.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhjä
	}
}
