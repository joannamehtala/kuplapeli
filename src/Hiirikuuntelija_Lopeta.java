import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;


public class Hiirikuuntelija_Lopeta implements MouseListener{
	
	private static final ImageIcon lopeta_normal = 
			new ImageIcon("media/lopeta.png");
	private static final ImageIcon lopeta_hiiri = 
			new ImageIcon("media/lopeta2.png");
	private Aloitusnaytto aloitusnaytto;
	private Ikkuna ikkuna;
	
	public Hiirikuuntelija_Lopeta(Aloitusnaytto aloitusnaytto, Ikkuna ikkuna){
		this.aloitusnaytto = aloitusnaytto;
		this.ikkuna = ikkuna;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.exit(0);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.aloitusnaytto.lopeta_asetaKuva(lopeta_hiiri);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.aloitusnaytto.lopeta_asetaKuva(lopeta_normal);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
