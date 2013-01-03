import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;

public class Hiirikuuntelija_Valikkoon implements MouseListener {

	private static final ImageIcon valikkoon_normal = 
			new ImageIcon("media/valikkoon.png");
	private static final ImageIcon valikkoon_hiiri = 
			new ImageIcon("media/valikkoon2.png");
	private Pelimaailma pelimaailma;
	private Ikkuna ikkuna;

	public Hiirikuuntelija_Valikkoon(Pelimaailma pelimaailma, Ikkuna ikkuna){
		this.pelimaailma = pelimaailma;
		this.ikkuna = ikkuna;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaAloitusnayttoon();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		this.pelimaailma.valikkoon_asetaKuva(valikkoon_hiiri);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		this.pelimaailma.valikkoon_asetaKuva(valikkoon_normal);

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

