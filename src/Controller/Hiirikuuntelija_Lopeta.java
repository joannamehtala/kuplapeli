package Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Aloitusnaytto;

/**
 * Hiirikuuntelija aloitusn‰ytˆn lopeta-nappia varten.
 * @author 345480
 *
 */
public class Hiirikuuntelija_Lopeta implements MouseListener{
	
	/** Lopeta-napin normaali kuva. */
	private static final ImageIcon lopeta_normal = 
			new ImageIcon("media/lopeta.png");
	
	/** Lopeta-napin kuva, kun hiiri liikutetaan sen p‰‰lle. */
	private static final ImageIcon lopeta_hiiri = 
			new ImageIcon("media/lopeta2.png");
	
	/** Aloitusn‰yttˆ. */
	private Aloitusnaytto aloitusnaytto;
	
	/**
	 * Hiirikuuntelijan konstruktori, joka ottaa parametrinaan aloitusn‰ytˆn.
	 * Aloitusn‰yttˆ alustetaan konstruktorissa.
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
	 * Kun lopetusnapin p‰‰lle vied‰‰n hiiri, asetetaan kuvaksi pinkill‰
	 * tekstill‰ varustettu kuva.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		this.aloitusnaytto.lopeta_asetaKuva(lopeta_hiiri);
		
	}

	/**
	 * Kun lopetusnapin p‰‰lt‰ siirret‰‰n hiiri pois, asetetaan alkuper‰inen
	 * mustalla tekstill‰ varutettu kuva lopetusnapin kuvaksi.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		this.aloitusnaytto.lopeta_asetaKuva(lopeta_normal);
		
	}

	/**
	 * Kun implementoidaan MouseListeneri‰, t‰m‰ metodi tarvitaan, vaikkei sit‰
	 * k‰ytett‰isik‰‰n mihink‰‰n.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// tyhj‰ metodi
	}

	/**
	 * Kun implementoidaan MouseListeneri‰, t‰m‰ metodi tarvitaan, vaikkei sit‰
	 * k‰ytett‰isik‰‰n mihink‰‰n.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// tyhj‰ metodi
	}
}
