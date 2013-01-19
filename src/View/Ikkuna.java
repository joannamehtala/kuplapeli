package View;


import java.awt.CardLayout;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;


/**
 * Pelin ikkunan luokka. Kaikki peliss� tapahtuu ikkunan sis�ll�.
 * @author 345480
 *
 */
@SuppressWarnings("serial")
public class Ikkuna extends JFrame {

	/** CardLayout n�ytt�jen vaihtelua varten. */
	private CardLayout naytot;

	/** Aloitusn�ytt�. */
	private Aloitusnaytto aloitusnaytto;
	
	/** Pelimaailma - paneeli, jossa peli� pelataan. */
	private Pelimaailma pelimaailma;
	
	/** Ohjen�ytt�. */
	private Ohjenaytto ohjenaytto;
	
	/** Voitton�kym�. */
	private Voittonakyma voittonakyma;
	
	/** H�vi�n�kym�. */
	private Havionakyma havionakyma;

	/** CardLayoutia varten aloitusn�ytt�� kuvaava merkkijono.*/
	private static final String ALOITUSNAYTTO = "aloitusnaytto";
	
	/** CardLayoutia varten pelimaailmaa kuvaava merkkijono.*/
	private static final String PELIMAAILMA = "pelimaailma";
	
	/** CardLayoutia varten ohjen�ytt�� kuvaava merkkijono.*/
	private static final String OHJENAYTTO = "ohjenaytto";
	
	/** CardLayoutia varten voitton�kym�� kuvaava merkkijono.*/
	private static final String VOITTONAKYMA = "voittonakyma";
	
	/** CardLayoutia varten h�vi�n�kym�� kuvaava merkkijono.*/
	private static final String HAVIONAKYMA = "havionakyma";

	/**
	 * Ikkunan konstruktori. Luodaan n�yt�t ja cardlayout ja lis�t��n ne 
	 * ikkunaan. M��ritell��n my�s ikkunan koko ja asetetaan se aukeamaan
	 * ruudun keskelt�.
	 */
	public Ikkuna() {

		/*
		 * Alustetaan attribuutit; luodaan uusi layout ja eri n�yt�t.
		 */

		this.naytot = new CardLayout();
		this.aloitusnaytto = new Aloitusnaytto(this);
		this.pelimaailma = new Pelimaailma(this);
		this.ohjenaytto = new Ohjenaytto(this);
		this.voittonakyma = new Voittonakyma(this);
		this.havionakyma = new Havionakyma(this, this.voittonakyma);

		/*
		 * Asetetaan otsikko, layout, koko sek� aukeamiskohta. Lis�t��n
		 * ikkunaan eri n�yt�t. Asetetaan pelin ajo loppumaan, kun painetaan 
		 * lopetusnappia.
		 */

		this.setTitle("Kuplapeli");
		this.setLayout(this.naytot);
		this.add(this.aloitusnaytto, ALOITUSNAYTTO);
		this.add(this.pelimaailma, PELIMAAILMA);
		this.add(this.ohjenaytto, OHJENAYTTO);
		this.add(this.voittonakyma, VOITTONAKYMA);
		this.add(this.havionakyma, HAVIONAKYMA);
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*
		 * Laitetaan pelin alussa n�kym��n aloitusn�ytt�.
		 */
		this.vaihdaAloitusnayttoon();

	}

	/**
	 * Metodi n�ytt�� aloitusn�yt�n ottamalla sen esille CardLayoutissa.
	 */
	public void vaihdaAloitusnayttoon(){
		this.naytot.show(this.getContentPane(), ALOITUSNAYTTO);
	}

	/**
	 * Metodi luo uuden pelimaailman ja n�ytt�� sen CardLayoutissa.
	 */
	public void vaihdaPelimaailmaan(){
		this.add(new Pelimaailma(this), PELIMAAILMA);
		this.naytot.show(this.getContentPane(), PELIMAAILMA);
	}

	/**
	 * Metodi n�ytt�� ohjen�kym�n ottamalla sen esille CardLayoutissa.
	 */
	public void vaihdaOhjenayttoon(){
		this.naytot.show(this.getContentPane(), OHJENAYTTO);
	}

	/**
	 * Metodi n�ytt�� voitton�kym�n CardLayoutissa.
	 */
	public void vaihdaVoittonakymaan(){
		this.naytot.show(this.getContentPane(), VOITTONAKYMA);
	}

	/**
	 * Metodi n�ytt�� h�vi�n�kym�n CardLayoutissa.
	 */
	public void vaihdaHavionakymaan(){
		this.naytot.show(this.getContentPane(), HAVIONAKYMA);
	}
	
	/**
	 * Soittaa taustamusiikin.
	 * @throws Exception
	 */
	public void soitaMusiikki() throws Exception {
		File taustamusa = new File("media/musiikki.wav");
		Clip clip = AudioSystem.getClip();
		AudioInputStream input = AudioSystem.getAudioInputStream(taustamusa);
		clip.open(input);
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}

	/**
	 * P��ohjelmametodi, jossa luodaan uusi ikkuna ja asetetaan se n�kyv�ksi.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Ikkuna ikkuna = new Ikkuna();
		ikkuna.setVisible(true);
		ikkuna.soitaMusiikki();
	}
}
