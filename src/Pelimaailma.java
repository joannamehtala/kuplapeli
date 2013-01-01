
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 * Pelimaailma on Maailma-luokan graafinen vastine, k‰ytt‰j‰lle n‰kyv‰ versio.
 * 
 * @author Joanna
 *
 */
public class Pelimaailma extends JPanel {

	/**
	 * Swingin takia tuollainen attribuutti, muuten valittaa.
	 * Muiksi attribuuteiksi annetaan maailma, ikkuna, ohjaaja ja aktiivinen
	 * kupla, koska pelimaailma tarvitsee tietoja kaikilta n‰ilt‰. Lis‰ksi
	 * annetaan taustakuva.
	 */
	private static final long serialVersionUID = -2718553638694558080L;
	private Maailma maailma;
	private Ikkuna ikkuna;
	private Ohjaaja ohjaaja;
	private AktiivinenKupla kupla;
	private static final Image taustakuva =
			Toolkit.getDefaultToolkit().createImage("media/taustakuva.png");

	/**
	 * Pelimaailman konstruktori, jossa alustetaan attribuutit ja asetetaan
	 * paneeli taustakuvan n‰kyvyyden takia l‰pin‰kyv‰ksi. Lis‰ksi annetaan
	 * paneelille haluttu koko.
	 * 
	 * @param maailma
	 * @param ikkuna
	 */
	public Pelimaailma(Maailma maailma, Ikkuna ikkuna){
		this.maailma = maailma;
		this.ikkuna = ikkuna;
		this.kupla = new AktiivinenKupla(228, 464, this);
		this.ohjaaja = new Ohjaaja(this, kupla);
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(maailma.annaLeveys() + 50,
				maailma.annaKorkeus() + 90));
	}

	/**
	 * Piirret‰‰n maailmaan taustakuva ja kupla.
	 */
	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(taustakuva, 0, 0, this);
		g2d.drawImage(kupla.annaKuva(),
				(int) kupla.annaX(), (int) kupla.annaY(), this);
		g2d.setColor(Color.WHITE);
		g2d.drawLine(250, 465, (int) Ikkuna.seurattux + 250, 
				(int) Ikkuna.seurattuy + 450);
		
		/*Graphics2D g2d2 = (Graphics2D)g;
		g2d2.translate(25, 50);
		g2d2.rotate(0, 200, 410);
		g2d2.drawImage(this.maailma.annaTykki().annaKuva(), 200, 410, this);*/
	}
}