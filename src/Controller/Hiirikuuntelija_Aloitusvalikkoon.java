package Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Ikkuna;
import View.Voittonakyma;

/**
 * Hiirikuuntelijaluokka voitto- ja h�vi�n�kym�n aloitusvalikoon-JButtonia
 * varten.
 * @author 345480
 *
 */

public class Hiirikuuntelija_Aloitusvalikkoon implements MouseListener {
	
	/** Aloitusvalikkoon-napin normaali kuva. */
	private static final ImageIcon aloitusvalikkoon_normal = 
			new ImageIcon("media/aloitusvalikkoon.png");
	
	/** Aloitusvalikkoon-napin kuva, kun hiiri liikutetaan sen p��lle. */
	private static final ImageIcon aloitusvalikkoon_hiiri = 
			new ImageIcon("media/aloitusvalikkoon2.png");
	
	/** Voitton�kym�. */
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
	 * Kun hiirt� klikataan, vaihdetaan aloitusn�ytt��n.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaAloitusnayttoon();
	}
	
	/**
	 * Kun hiiri liikutetaan JButtonin p��lle, asetetaan napille kuva, jossa
	 * on sama teksti pinkkin�.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.voittonakyma.aloitusvalikkoon_asetaKuva(aloitusvalikkoon_hiiri);
	}
	
	/**
	 * Kun hiiri liikutetaan pois napin p��lt�, asetetaan takaisin mustalla
	 * tekstill� varustettu kuva.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.voittonakyma.aloitusvalikkoon_asetaKuva(aloitusvalikkoon_normal);
	}
	
	/**
	 * Tyhj� metodi, jonka MouseListenerin implementointi vaatii.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhj�
	}
	
	/**
	 * Tyhj� metodi, jonka MouseListenerin implementointi vaatii.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhj�
	}
}
