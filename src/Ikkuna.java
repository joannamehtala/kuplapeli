import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Pelin ikkunan luokka.
 * @author 345480
 *
 */
@SuppressWarnings("serial")
public class Ikkuna extends JFrame {

	/**
	 * Asetetaan attribuuteiksi asettelu, joka toteutetaan BorderLayoutilla,
	 * ja JPanel-tyyppiset paneelit, joihin tulee taustakuva ja itse graafinen
	 * pelimaailma.
	 */
	private BorderLayout layout;
	private CardLayout naytot;
	private Aloitusnaytto aloitusnaytto;
	private Pelimaailma pelimaailma;
	private Ohjenaytto ohjenaytto;
	private Maailma maailma;
	private JPanel paneeli;
	private static final String ALOITUSNAYTTO = "aloitusnaytto";
	private static final String PELIMAAILMA = "pelimaailma";
	private static final String OHJENAYTTO = "ohjenaytto";

	/**
	 * Ikkunan konstruktorissa alustetaan attribuutit ja asetetaan ikkunalle
	 * otsikko. Pelipaneelille asetetaan myös suositeltava koko ja asetetaan
	 * ikkuna aukeamaan keskelle ruutua.
	 */
	public Ikkuna(){

		/*
		 * Alustetaan attribuutit; luodaan uusi layout ja eri näytöt. Lisäksi
		 * alustetaan maailmaksi uusi maailma, joka ottaa pelimaailman
		 * attribuuttinaan.
		 */
		
		this.layout = new BorderLayout();
		this.naytot = new CardLayout();
		this.aloitusnaytto = new Aloitusnaytto(this);
		this.pelimaailma = new Pelimaailma(this);
		this.ohjenaytto = new Ohjenaytto(this);
		this.paneeli = new JPanel();
		

		/*
		 * Asetetaan otsikko, layout, koko sekä aukeamiskohta. Lisätään
		 * ikkunaan eri näytöt.
		 */
		
		this.setTitle("Kuplapeli");
		//this.setLayout(layout);
		//this.add(paneeli, BorderLayout.CENTER);
		this.setLayout(naytot);
		this.add(this.aloitusnaytto, ALOITUSNAYTTO);
		this.add(this.pelimaailma, PELIMAAILMA);
		this.add(this.ohjenaytto, OHJENAYTTO);
		this.pack();
		this.setLocationRelativeTo(this);
		this.setResizable(false);
		
		/*
		 * Laitetaan pelin alussa näkymään aloitusnäyttö.
		 */
		this.vaihdaAloitusnayttoon();

		/*
		 * Asetetaan pelin ajo loppumaan, kun painetaan lopetusnappia.
		 */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void vaihdaAloitusnayttoon(){
		this.naytot.show(this.getContentPane(), ALOITUSNAYTTO);
	}

	public void vaihdaPelimaailmaan(){
		this.naytot.show(this.getContentPane(), PELIMAAILMA);
	}
	
	public void vaihdaOhjenayttoon(){
		this.naytot.show(this.getContentPane(), OHJENAYTTO);
	}
	/**
	 * Pääohjelmametodi, jossa luodaan uusi ikkuna ja asetetaan se näkyväksi.
	 * @param args
	 */
	public static void main(String[] args){
		Ikkuna ikkuna = new Ikkuna();
		ikkuna.setVisible(true);
	}
}
