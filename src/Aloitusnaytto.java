import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Aloitusnaytto extends JPanel implements MouseListener{
	
	private static final Image kuplapeli =
			Toolkit.getDefaultToolkit().createImage("media/kuplapeli.png");
	private static final ImageIcon aloita = new ImageIcon("media/aloita.png");
	private JButton aloitus;
	private Ikkuna ikkuna;
	
	public Aloitusnaytto(Ikkuna ikkuna){
		this.ikkuna = ikkuna;
		this.setPreferredSize(new Dimension(500, 600));
		this.addMouseListener(this);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(kuplapeli, 0, 0, this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.ikkuna.vaihdaPelimaailmaan();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
