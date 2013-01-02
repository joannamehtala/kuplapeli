import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;


public class Hiirikuuntelija_Takaisin implements MouseListener {
	private static final ImageIcon takaisin_normal = 
			new ImageIcon("media/takaisin.png");
	private static final ImageIcon takaisin_hiiri = 
			new ImageIcon("media/takaisin2.png");
	private Ohjenaytto ohjenaytto;
	private Ikkuna ikkuna;
	
	public Hiirikuuntelija_Takaisin(Ohjenaytto ohjenaytto, Ikkuna ikkuna){
		this.ohjenaytto = ohjenaytto;
		this.ikkuna = ikkuna;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaAloitusnayttoon();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.ohjenaytto.takaisin_asetaKuva(takaisin_hiiri);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.ohjenaytto.takaisin_asetaKuva(takaisin_normal);
		
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
