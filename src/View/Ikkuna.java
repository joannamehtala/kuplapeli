package View;


import java.awt.CardLayout;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;


/**
 * Pelin ikkunan luokka. Kaikki pelissä tapahtuu ikkunan sisällä.
 * @author 345480
 *
 */
@SuppressWarnings("serial")
public class Ikkuna extends JFrame {

	/** CardLayout näyttöjen vaihtelua varten. */
	private CardLayout naytot;

	/** Aloitusnäyttö. */
	private Aloitusnaytto aloitusnaytto;
	
	/** Pelimaailma - paneeli, jossa peliä pelataan. */
	private Pelimaailma pelimaailma;
	
	/** Ohjenäyttö. */
	private Ohjenaytto ohjenaytto;
	
	/** Voittonäkymä. */
	private Voittonakyma voittonakyma;
	
	/** Häviönäkymä. */
	private Havionakyma havionakyma;

	/** CardLayoutia varten aloitusnäyttöä kuvaava merkkijono.*/
	private static final String ALOITUSNAYTTO = "aloitusnaytto";
	
	/** CardLayoutia varten pelimaailmaa kuvaava merkkijono.*/
	private static final String PELIMAAILMA = "pelimaailma";
	
	/** CardLayoutia varten ohjenäyttöä kuvaava merkkijono.*/
	private static final String OHJENAYTTO = "ohjenaytto";
	
	/** CardLayoutia varten voittonäkymää kuvaava merkkijono.*/
	private static final String VOITTONAKYMA = "voittonakyma";
	
	/** CardLayoutia varten häviönäkymää kuvaava merkkijono.*/
	private static final String HAVIONAKYMA = "havionakyma";

	/**
	 * Ikkunan konstruktori. Luodaan näytöt ja cardlayout ja lisätään ne 
	 * ikkunaan. Määritellään myös ikkunan koko ja asetetaan se aukeamaan
	 * ruudun keskeltä.
	 */
	public Ikkuna() {

		/*
		 * Alustetaan attribuutit; luodaan uusi layout ja eri näytöt.
		 */

		this.naytot = new CardLayout();
		this.aloitusnaytto = new Aloitusnaytto(this);
		this.pelimaailma = new Pelimaailma(this);
		this.ohjenaytto = new Ohjenaytto(this);
		this.voittonakyma = new Voittonakyma(this);
		this.havionakyma = new Havionakyma(this, this.voittonakyma);

		/*
		 * Asetetaan otsikko, layout, koko sekä aukeamiskohta. Lisätään
		 * ikkunaan eri näytöt. Asetetaan pelin ajo loppumaan, kun painetaan 
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
		 * Laitetaan pelin alussa näkymään aloitusnäyttö.
		 */
		this.vaihdaAloitusnayttoon();

	}

	/**
	 * Metodi näyttää aloitusnäytön ottamalla sen esille CardLayoutissa.
	 */
	public void vaihdaAloitusnayttoon(){
		this.naytot.show(this.getContentPane(), ALOITUSNAYTTO);
	}

	/**
	 * Metodi luo uuden pelimaailman ja näyttää sen CardLayoutissa.
	 */
	public void vaihdaPelimaailmaan(){
		this.add(new Pelimaailma(this), PELIMAAILMA);
		this.naytot.show(this.getContentPane(), PELIMAAILMA);
	}

	/**
	 * Metodi näyttää ohjenäkymän ottamalla sen esille CardLayoutissa.
	 */
	public void vaihdaOhjenayttoon(){
		this.naytot.show(this.getContentPane(), OHJENAYTTO);
	}

	/**
	 * Metodi näyttää voittonäkymän CardLayoutissa.
	 */
	public void vaihdaVoittonakymaan(){
		this.naytot.show(this.getContentPane(), VOITTONAKYMA);
	}

	/**
	 * Metodi näyttää häviönäkymän CardLayoutissa.
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
	 * Pääohjelmametodi, jossa luodaan uusi ikkuna ja asetetaan se näkyväksi.
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		Ikkuna ikkuna = new Ikkuna();
		ikkuna.setVisible(true);
		ikkuna.soitaMusiikki();
	}
}
