package Controller;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

import View.Ikkuna;
import View.Voittonakyma;


public class Hiirikuuntelija_Aloitusvalikkoon implements MouseListener {
	/** Takaisin-napin kuvat. */
	private static final ImageIcon aloitusvalikkoon_normal = 
			new ImageIcon("media/aloitusvalikkoon.png");
	private static final ImageIcon aloitusvalikkoon_hiiri = 
			new ImageIcon("media/aloitusvalikkoon2.png");
	
	/** Ohjenäyttö ja ikkuna. */
	private Voittonakyma voittonakyma;
	private Ikkuna ikkuna;
	
	public Hiirikuuntelija_Aloitusvalikkoon(Voittonakyma voittonakyma,
			Ikkuna ikkuna){
		this.voittonakyma = voittonakyma;
		this.ikkuna = ikkuna;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaAloitusnayttoon();
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.voittonakyma.aloitusvalikkoon_asetaKuva(aloitusvalikkoon_hiiri);
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		this.voittonakyma.aloitusvalikkoon_asetaKuva(aloitusvalikkoon_normal);
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
