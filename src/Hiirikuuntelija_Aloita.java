
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class Hiirikuuntelija_Aloita implements MouseListener{
	private static final ImageIcon aloita_normal = new ImageIcon("media/aloita.png");
	private static final ImageIcon aloita_hiiri = 
			new ImageIcon("media/aloita2.png");
	private Aloitusnaytto aloitusnaytto;
	private Ikkuna ikkuna;
	
	public Hiirikuuntelija_Aloita(Aloitusnaytto aloitusnaytto, Ikkuna ikkuna){
		this.aloitusnaytto = aloitusnaytto;
		this.ikkuna = ikkuna;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaPelimaailmaan();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.aloitusnaytto.aloita_asetaKuva(aloita_hiiri);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.aloitusnaytto.aloita_asetaKuva(aloita_normal);
		
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
