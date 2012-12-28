
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;


public class Pelimaailma extends JPanel {

	/**
	 * Swingin takia tuollainen attribuutti muuten valittaaaaaaa
	 */
	private static final long serialVersionUID = -2718553638694558080L;
	private Maailma maailma;
	private Ikkuna ikkuna;
	private Ohjaaja ohjaaja;
	private AktiivinenKupla kupla;
	private static final Image taustakuva =
			Toolkit.getDefaultToolkit().createImage("media/tausta.jpg");

	public Pelimaailma(Maailma maailma, Ikkuna ikkuna){
		this.maailma = maailma;
		this.ikkuna = ikkuna;
		this.kupla = new AktiivinenKupla(228, 404, this);
		this.ohjaaja = new Ohjaaja(this, kupla);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(maailma.annaLeveys() + 50,
				maailma.annaKorkeus() + 50));
	}

	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(taustakuva, 0, 0, this);
		g2d.drawImage(kupla.annaKuva(),
				(int) kupla.annaX(), (int) kupla.annaY(), this);
	}
}