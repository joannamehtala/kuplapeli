package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Aloitusnaytto;
import View.Ikkuna;

/**
 * Hiirikuuntelijaluokka aloitusn‰kym‰n Aloita-JButtonin toimintaa varten.
 * @author 345480
 *
 */
public class Hiirikuuntelija_Aloita implements MouseListener{
	
	/** Aloita-napin normaali kuva. */
	private static final ImageIcon aloita_normal = 
			new ImageIcon("media/aloita.png");
	
	/** Aloita-napin kuva, kun hiiri liikutetaan sen p‰‰lle. */
	private static final ImageIcon aloita_hiiri = 
			new ImageIcon("media/aloita2.png");
	
	/** Aloitusn‰yttˆ. */
	private Aloitusnaytto aloitusnaytto;
	
	/** Ikkuna. */
	private Ikkuna ikkuna;
	
	/**
	 * Hiirikuuntelijan konstruktori, jossa alustetaan attribuutit.
	 * @param aloitusnaytto
	 * @param ikkuna
	 */
	public Hiirikuuntelija_Aloita(Aloitusnaytto aloitusnaytto, Ikkuna ikkuna){
		this.aloitusnaytto = aloitusnaytto;
		this.ikkuna = ikkuna;
	}

	/**
	 * Metodi n‰ytt‰‰ pelimaailman, kun aloita-nappia klikataan.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaPelimaailmaan();
		
	}

	/**
	 * Metodi vaihtaa aloita-napin kuvan (pinkki teksti), kun hiiri vied‰‰n sen
	 * p‰‰lle.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.aloitusnaytto.aloita_asetaKuva(aloita_hiiri);
	}
	
	/**
	 * Metodi vaihtaa aloita-napin kuvan takaisin alkuper‰iseksi, kun hiiri
	 * vied‰‰n pois sen p‰‰lt‰.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.aloitusnaytto.aloita_asetaKuva(aloita_normal);
		
	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei sill‰
	 * tehd‰k‰‰n mit‰‰n.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhj‰ metodi
		
	}

	/**
	 * MouseListenerin implementointi vaatii t‰m‰n metodin, vaikkei sill‰
	 * tehd‰k‰‰n mit‰‰n.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhj‰ metodi
	}
}
