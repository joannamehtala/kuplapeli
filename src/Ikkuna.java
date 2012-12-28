import java.awt.BorderLayout;
import javax.swing.JFrame;
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
	private BorderLayout asettelu;
	private JPanel pelipaneeli;
	private boolean peliLoppunut;
	private Maailma maailma;
	
	/**
	 * Ikkunan konstruktorissa alustetaan attribuutit ja asetetaan ikkunalle
	 * otsikko. Pelipaneelille asetetaan myös suositeltava koko ja asetetaan
	 * ikkuna aukeamaan keskelle ruutua.
	 */
	public Ikkuna(){
		
		/*
		 * Alustetaan attribuutit.
		 */
		this.asettelu = new BorderLayout();
		this.maailma = new Maailma();
		this.pelipaneeli = new Pelimaailma(maailma, this);
		
		/*
		 * Asetetaan otsikko, asettelu, koko sekä aukeamiskohta.
		 */
		this.setTitle("Kuplapeli");
		this.setLayout(this.asettelu);
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
