
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class Hiirikuuntelija_Ohjeet implements MouseListener{
	private static final ImageIcon ohjeet_normal = new ImageIcon("media/ohjeet.png");
	private static final ImageIcon ohjeet_hiiri = 
			new ImageIcon("media/ohjeet2.png");
	private Aloitusnaytto aloitusnaytto;
	private Ikkuna ikkuna;
	
	public Hiirikuuntelija_Ohjeet(Aloitusnaytto aloitusnaytto, Ikkuna ikkuna){
		this.aloitusnaytto = aloitusnaytto;
		this.ikkuna = ikkuna;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaOhjenayttoon();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.aloitusnaytto.ohjeet_asetaKuva(ohjeet_hiiri);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.aloitusnaytto.ohjeet_asetaKuva(ohjeet_normal);
		
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
