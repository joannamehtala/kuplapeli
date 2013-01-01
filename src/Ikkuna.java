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
	private BorderLayout layout;
	private JPanel pelipaneeli;
	private boolean peliLoppunut;
	private Maailma maailma;
	private Pelimaailma pelimaailma;
	
	/**
	 * Ikkunan konstruktorissa alustetaan attribuutit ja asetetaan ikkunalle
	 * otsikko. Pelipaneelille asetetaan my�s suositeltava koko ja asetetaan
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
		
		/*
		 * Asetetaan otsikko, asettelu, koko sek� aukeamiskohta.
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
	 * Metodi palauttaa tiedon siit�, onko peli ohi vai ei.
	 * @return true, jos peli on loppunut, ja false, jos se on viel� k�ynniss�.
	 */
	public boolean peliOhi(){
		if (this.peliLoppunut){
			return true;
		} return false;
	}
	
	/**
	 * P��ohjelmametodi, jossa luodaan uusi ikkuna ja asetetaan se n�kyv�ksi.
	 * @param args
	 */
	public static void main(String[] args){
		Ikkuna ikkuna = new Ikkuna();
		ikkuna.setVisible(true);
	}
}
