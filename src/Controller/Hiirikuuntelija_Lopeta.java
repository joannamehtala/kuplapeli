package Controller;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

import View.Aloitusnaytto;

/**
 * Hiirikuuntelija aloitusnäytön lopeta-nappia varten.
 * @author 345480
 *
 */
public class Hiirikuuntelija_Lopeta extends MouseAdapter{
	
	/** Lopeta-napin normaali kuva. */
	private static final ImageIcon lopeta_normal = 
			new ImageIcon("media/lopeta.png");
	
	/** Lopeta-napin kuva, kun hiiri liikutetaan sen päälle. */
	private static final ImageIcon lopeta_hiiri = 
			new ImageIcon("media/lopeta2.png");
	
	/** Aloitusnäyttö. */
	private Aloitusnaytto aloitusnaytto;
	
	/**
	 * Hiirikuuntelijan konstruktori, joka ottaa parametrinaan aloitusnäytön.
	 * Aloitusnäyttö alustetaan konstruktorissa.
	 * @param aloitusnaytto aloitusnäyttö, jossa lopeta-nappi sijaitsee
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
}
