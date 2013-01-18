package Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Ikkuna;
import View.Pelimaailma;

/**
 * Hiirikuuntelijaluokka Pelimaailman valikkoon-nappia varten.
 * @author Joanna
 *
 */
public class Hiirikuuntelija_Valikkoon implements MouseListener {

	/** Valikkoon-napin tavallinen kuva. */
	private static final ImageIcon valikkoon_normal = 
			new ImageIcon("media/valikkoon.png");
	
	/** Valikkoon-napin kuva, kun hiiri liikutetaan sen p‰‰lle. */
	private static final ImageIcon valikkoon_hiiri = 
			new ImageIcon("media/valikkoon2.png");
	
	/** Pelimaailma. */
	private Pelimaailma pelimaailma;
	
	/** Ikkuna. */
	private Ikkuna ikkuna;

	/**
	 * Hiirikuuntelijan konstruktori. Alustetaan attribuutit.
	 * @param pelimaailma
	 * @param ikkuna
	 */
	public Hiirikuuntelija_Valikkoon(Pelimaailma pelimaailma, Ikkuna ikkuna){
		this.pelimaailma = pelimaailma;
		this.ikkuna = ikkuna;
	}

	/**
	 * Kun nappia klikataan, vaihdetaan aloitusn‰yttˆˆn.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaAloitusnayttoon();
	}

	/**
	 * Kun hiiri vied‰‰n napin p‰‰lle, valikkonapille asetetaan pinkill‰
	 * tekstill‰ varustettu kuva.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.pelimaailma.valikkoon_asetaKuva(valikkoon_hiiri);
	}

	/**
	 * Kun hiiri vied‰‰n pois napin p‰‰lt‰, valikkonapille asetetaan mustalla
	 * tekstill‰ varustettu kuva.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.pelimaailma.valikkoon_asetaKuva(valikkoon_normal);
	}

	/**
	 * Tyhj‰ metodi, jonka MouseListenerin implementointi kuitenkin vaatii.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhj‰
	}

	/**
	 * Tyhj‰ metodi, jonka MouseListenerin implementointi kuitenkin vaatii.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhj‰
	}
}

