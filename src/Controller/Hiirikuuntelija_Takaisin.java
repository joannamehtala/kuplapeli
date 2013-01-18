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
	
	/** Takaisin-napin kuva, kun hiiri liikutetaan sen päälle. */
	private static final ImageIcon takaisin_hiiri = 
			new ImageIcon("media/takaisin2.png");
	
	/** Ohjenäyttö. */
	private Ohjenaytto ohjenaytto;
	
	/** Ikkuna. */
	private Ikkuna ikkuna;
	
	/**
	 * Hiirikuuntelijan konstruktori, jossa alustetaan parametreina annetut
	 * ohjenäyttö ja ikkuna.
	 * @param ohjenaytto
	 * @param ikkuna
	 */
	public Hiirikuuntelija_Takaisin(Ohjenaytto ohjenaytto, Ikkuna ikkuna){
		this.ohjenaytto = ohjenaytto;
		this.ikkuna = ikkuna;
	}

	/**
	 * Kun takaisin-nappia klikataan, siirrytään takaisin aloitusnäyttöön.
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaAloitusnayttoon();
		
	}

	/**
	 * Kun liikutetaan hiiri napin päälle, sen kuvaksi vaihdetaan pinkillä
	 * tekstillä varustettu kuva.
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.ohjenaytto.takaisin_asetaKuva(takaisin_hiiri);
	}

	/**
	 * Kun liikutetaan hiiri pois napin päältä, sen kuvaksi vaihdetaan taas
	 * mustalla tekstillä varustettu kuva.
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.ohjenaytto.takaisin_asetaKuva(takaisin_normal);
		
	}

	/**
	 * MouseListener-luokan implementointi vaatii tämän metodin, vaikka sitä ei
	 * käytettäisi mihinkään.
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// tyhjä metodi
		
	}

	/**
	 * MouseListener-luokan implementointi vaatii tämän metodin, vaikka sitä ei
	 * käytettäisi mihinkään.
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// tyhjä metodi
		
	}
}
