package Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import View.Ikkuna;
import View.Pelimaailma;

/**
 * Hiirikuuntelijaluokka Pelimaailman valikkoon-nappia varten.
 * @author Joanna
 *
 */
public class Hiirikuuntelija_Valikkoon extends MouseAdapter {

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
	 * @param pelimaailma jossa valikkoon-nappi sijaitsee
	 * @param ikkuna pelin ikkuna
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
}

