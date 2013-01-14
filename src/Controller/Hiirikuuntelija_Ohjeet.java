package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Aloitusnaytto;
import View.Ikkuna;

/**
 * Hiirikuuntelijaluokka aloitusn‰ytˆn ohjeet-napin toimintoja varten.
 * @author 345480
 *
 */
public class Hiirikuuntelija_Ohjeet implements MouseListener{
	
	/** Ohjenapin kuvat. */
	private static final ImageIcon ohjeet_normal = 
			new ImageIcon("media/ohjeet.png");
	private static final ImageIcon ohjeet_hiiri = 
			new ImageIcon("media/ohjeet2.png");
	
	/** Aloitusn‰yttˆ ja ikkuna. */
	private Aloitusnaytto aloitusnaytto;
	private Ikkuna ikkuna;
	
	/**
	 * Hiirikuuntelijan konstruktori, jossa alustetaan attribuutit.
	 * @param aloitusnaytto
	 * @param ikkuna
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

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei sit‰
	 * ylikirjoiteta t‰ss‰.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhj‰ metodi
	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei sit‰
	 * ylikirjoiteta t‰ss‰.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhj‰ metodi
		
	}

}
