package Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Ikkuna;
import View.Ohjenaytto;

/**
 * Hiirikuuntelijaluokka takaisin-nappia varten.
 * @author 345480
 *
 */
public class Hiirikuuntelija_Takaisin implements MouseListener {
	
	/** Takaisin-napin tavallinen kuva. */
	private static final ImageIcon takaisin_normal = 
			new ImageIcon("media/takaisin.png");
	
	/** Takaisin-napin kuva, kun hiiri liikutetaan sen p��lle. */
	private static final ImageIcon takaisin_hiiri = 
			new ImageIcon("media/takaisin2.png");
	
	/** Ohjen�ytt�. */
	private Ohjenaytto ohjenaytto;
	
	/** Ikkuna. */
	private Ikkuna ikkuna;
	
	/**
	 * Hiirikuuntelijan konstruktori, jossa alustetaan parametreina annetut
	 * ohjen�ytt� ja ikkuna.
	 * @param ohjenaytto
	 * @param ikkuna
	 */
	public Hiirikuuntelija_Takaisin(Ohjenaytto ohjenaytto, Ikkuna ikkuna){
		this.ohjenaytto = ohjenaytto;
		this.ikkuna = ikkuna;
	}

	/**
	 * Kun takaisin-nappia klikataan, siirryt��n takaisin aloitusn�ytt��n.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaAloitusnayttoon();
		
	}

	/**
	 * Kun liikutetaan hiiri napin p��lle, sen kuvaksi vaihdetaan pinkill�
	 * tekstill� varustettu kuva.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.ohjenaytto.takaisin_asetaKuva(takaisin_hiiri);
	}

	/**
	 * Kun liikutetaan hiiri pois napin p��lt�, sen kuvaksi vaihdetaan taas
	 * mustalla tekstill� varustettu kuva.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.ohjenaytto.takaisin_asetaKuva(takaisin_normal);
		
	}

	/**
	 * MouseListener-luokan implementointi vaatii t�m�n metodin, vaikka sit� ei
	 * k�ytett�isi mihink��n.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhj� metodi
		
	}

	/**
	 * MouseListener-luokan implementointi vaatii t�m�n metodin, vaikka sit� ei
	 * k�ytett�isi mihink��n.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhj� metodi
		
	}
}
