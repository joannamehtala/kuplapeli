import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Pelin ikkunan luokka.
 * @author 345480
 *
 */
@SuppressWarnings("serial")
public class Ikkuna extends JFrame implements MouseMotionListener, MouseListener{
	
	/**
	 * Asetetaan attribuuteiksi asettelu, joka toteutetaan BorderLayoutilla,
	 * ja JPanel-tyyppiset paneelit, joihin tulee taustakuva ja itse graafinen
	 * pelimaailma.
	 */
	private BorderLayout layout;
	private JPanel pelipaneeli;
	private boolean peliLoppunut;
	private Maailma maailma;
	private Pelimaailma pelimaailma;
	public static double seurattux;
	public static double seurattuy;
	private static boolean klikattu;
	
	/**
	 * Ikkunan konstruktorissa alustetaan attribuutit ja asetetaan ikkunalle
	 * otsikko. Pelipaneelille asetetaan myˆs suositeltava koko ja asetetaan
	 * ikkuna aukeamaan keskelle ruutua.
	 */
	public Ikkuna(){
		
		/*
		 * Alustetaan attribuutit; luodaan uusi layout ja asetetaan maailmaksi
		 * uusi maailma joka ottaa parametrinaan pelipaneelin paikalle luotavan
		 * pelimaailman.
		 */
		this.layout = new BorderLayout();
		this.maailma = new Maailma(this.pelimaailma);
		this.pelimaailma = new Pelimaailma(this.maailma, this);
		this.pelipaneeli = this.pelimaailma;
		klikattu = false;
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
		/*
		 * Asetetaan otsikko, asettelu, koko sek‰ aukeamiskohta.
		 */
		this.setTitle("Kuplapeli");
		this.setLayout(this.layout);
		this.pelipaneeli.setOpaque(false);
		this.add(this.pelipaneeli, BorderLayout.CENTER);
		this.pack();
		this.setLocationRelativeTo(this);
		
		/*
		 * Asetetaan pelin ajo loppumaan, kun painetaan lopetusnappia.
		 */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Metodi palauttaa tiedon siit‰, onko peli ohi vai ei.
	 * @return true, jos peli on loppunut, ja false, jos se on viel‰ k‰ynniss‰.
	 */
	public boolean peliOhi(){
		if (this.peliLoppunut){
			return true;
		} return false;
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		int hiirix = arg0.getX();
		int hiiriy = arg0.getY();
		double x = hiirix - 250;
		double y = hiiriy - 465;
		double kulma = Math.atan(y / x);
		
		if (hiirix >= 250){
			seurattux = Math.cos(kulma)*15;
			seurattuy = Math.sin(kulma)*15;
		} else {
			seurattux = Math.cos(kulma)*-15;
			seurattuy = Math.sin(kulma)*-15;
		}
		
		this.pelimaailma.repaint();

	}
	
	public static boolean onKlikattu(){
		return klikattu;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.klikattu = true;
		
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
	
	/**
	 * P‰‰ohjelmametodi, jossa luodaan uusi ikkuna ja asetetaan se n‰kyv‰ksi.
	 * @param args
	 */
	public static void main(String[] args){
		Ikkuna ikkuna = new Ikkuna();
		ikkuna.setVisible(true);
	}
}
