package Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import View.Aloitusnaytto;
import View.Ikkuna;

/**
 * Hiirikuuntelijaluokka aloitusn‰ytˆn ohjeet-napin toimintoja varten.
 * @author 345480
 *
 */
public class Hiirikuuntelija_Ohjeet extends MouseAdapter{

	/** Ohjenapin tavallinen kuva. */
	private static final ImageIcon ohjeet_normal = 
			new ImageIcon("media/ohjeet.png");

	/** Ohjenapin kuva, kun hiiri liikutetaan sen p‰‰lle. */
	private static final ImageIcon ohjeet_hiiri = 
			new ImageIcon("media/ohjeet2.png");

	/** Aloitusn‰yttˆ. */
	private Aloitusnaytto aloitusnaytto;

	/** Ikkuna. */
	private Ikkuna ikkuna;

	/**
	 * Hiirikuuntelijan konstruktori, jossa alustetaan attribuutit.
	 * @param aloitusnaytto jossa ohjenappi sijaitsee
	 * @param ikkuna pelin ikkuna
	 */
	public Hiirikuuntelija_Ohjeet(Aloitusnaytto aloitusnaytto, Ikkuna ikkuna){
		this.aloitusnaytto = aloitusnaytto;
		this.ikkuna = ikkuna;
	}

	/**
	 * Metodi n‰ytt‰‰ ohjen‰kym‰n, kun hiirell‰ klikataan.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaOhjenayttoon();
	}

	/**
	 * Metodi vaihtaa napin kuvan pinkill‰ tekstill‰ varustetuksi, kun hiiri
	 * vied‰‰n napin p‰‰lle.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.aloitusnaytto.ohjeet_asetaKuva(ohjeet_hiiri);
	}

	/**
	 * Metodi vaihtaa napin kuvan alkuper‰iseksi, kun hiiri vied‰‰n pois napin
	 * p‰‰lt‰.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.aloitusnaytto.ohjeet_asetaKuva(ohjeet_normal);

	}
}
