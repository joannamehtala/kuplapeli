package Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Aloitusnaytto;

/**
 * Hiirikuuntelija aloitusnäytön lopeta-nappia varten.
 * @author 345480
 *
 */
public class Hiirikuuntelija_Lopeta implements MouseListener{
	
	/** Lopeta-napin kuvat. */
	private static final ImageIcon lopeta_normal = 
			new ImageIcon("media/lopeta.png");
	private static final ImageIcon lopeta_hiiri = 
			new ImageIcon("media/lopeta2.png");
	
	/** Aloitusnäyttö. */
	private Aloitusnaytto aloitusnaytto;
	
	/**
	 * Hiirikuuntelijan konstruktori, joka ottaa parametrikseen aloitusnäytön.
	 * Aloitusnäyttö alustetaan konstruktorissa.
	 * @param aloitusnaytto
	 */
	public Hiirikuuntelija_Lopeta(Aloitusnaytto aloitusnaytto){
		this.aloitusnaytto = aloitusnaytto;
	}

	/**
	 * Kun lopetusnappia klikataan, peli loppuu.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
		
	}

	/**
	 * Kun lopetusnapin päälle viedään hiiri, asetetaan kuvaksi pinkillä
	 * tekstillä varustettu kuva.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		this.aloitusnaytto.lopeta_asetaKuva(lopeta_hiiri);
		
	}

	/**
	 * Kun lopetusnapin päältä siirretään hiiri pois, asetetaan alkuperäinen
	 * mustalla tekstillä varutettu kuva lopetusnapin kuvaksi.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		this.aloitusnaytto.lopeta_asetaKuva(lopeta_normal);
		
	}

	/**
	 * Kun implementoidaan MouseListeneriä, tämä metodi tarvitaan, vaikkei sitä
	 * käytettäisikään mihinkään.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// tyhjä metodi
	}

	/**
	 * Kun implementoidaan MouseListeneriä, tämä metodi tarvitaan, vaikkei sitä
	 * käytettäisikään mihinkään.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// tyhjä metodi
	}
}
