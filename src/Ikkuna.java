import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
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
	private JPanel pelipaneeli;
	private boolean peliLoppunut;
	private Maailma maailma;
	private Pelimaailma pelimaailma;
	private CardLayout naytot;
	private static final ImageIcon kuplapeli = 
			new ImageIcon("media/kuplapeli.png");
	private JLabel label;

	/**
	 * Ikkunan konstruktorissa alustetaan attribuutit ja asetetaan ikkunalle
	 * otsikko. Pelipaneelille asetetaan myös suositeltava koko ja asetetaan
	 * ikkuna aukeamaan keskelle ruutua.
	 */
	public Ikkuna(){

		/*
		 * Alustetaan attribuutit; luodaan uusi layout ja asetetaan maailmaksi
		 * uusi maailma joka ottaa parametrinaan pelipaneelin paikalle luotavan
		 * pelimaailman.
		 */
		this.layout = new BorderLayout();
		this.naytot = new CardLayout();
		this.maailma = new Maailma(this.pelimaailma);
		JPanel aloitusnaytto = new JPanel();
		this.label = new JLabel();
		this.label.setIcon(kuplapeli);
		aloitusnaytto.add(label);
		this.pelimaailma = new Pelimaailma(this.maailma, this);
		this.pelipaneeli = this.pelimaailma;
		this.pelipaneeli.setLayout(naytot);
		this.naytot.show(pelipaneeli, "Naytto");

		/*
		 * Asetetaan otsikko, asettelu, koko sekä aukeamiskohta.
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
	 * Metodi palauttaa tiedon siitä, onko peli ohi vai ei.
	 * @return true, jos peli on loppunut, ja false, jos se on vielä käynnissä.
	 */
	public boolean peliOhi(){
		if (this.peliLoppunut){
			return true;
		} return false;
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
