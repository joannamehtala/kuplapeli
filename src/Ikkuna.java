
import java.awt.CardLayout;
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
	
	/** Eri näytöt. */
	private Aloitusnaytto aloitusnaytto;
	private Pelimaailma pelimaailma;
	private Ohjenaytto ohjenaytto;
	
	/** CardLayoutia varten vaaditut Stringit näyttöjä kuvaamaan.*/
	private static final String ALOITUSNAYTTO = "aloitusnaytto";
	private static final String PELIMAAILMA = "pelimaailma";
	private static final String OHJENAYTTO = "ohjenaytto";

	/**
	 * Ikkunan konstruktorissa alustetaan attribuutit ja lisätään ikkunaan
	 * eri näkymät. Lisäksi asetetaan ikkunan koko ja otsikko ja laitetaan
	 * se aukeamaan keskeltä. Asetetaan myös ohjelman suoritus loppumaan, kun
	 * ikkuna suljetaan.
	 */
	public Ikkuna(){

		/*
		 * Alustetaan attribuutit; luodaan uusi layout ja eri näytöt.
		 */
		
		this.naytot = new CardLayout();
		this.aloitusnaytto = new Aloitusnaytto(this);
		this.pelimaailma = new Pelimaailma(this);
		this.ohjenaytto = new Ohjenaytto(this);

		/*
		 * Asetetaan otsikko, layout, koko sekä aukeamiskohta. Lisätään
		 * ikkunaan eri näytöt. Asetetaan pelin ajo loppumaan, kun painetaan 
		 * lopetusnappia.
		 */
		
		this.setTitle("Kuplapeli");
		this.setLayout(naytot);
		this.add(this.aloitusnaytto, ALOITUSNAYTTO);
		this.add(this.pelimaailma, PELIMAAILMA);
		this.add(this.ohjenaytto, OHJENAYTTO);
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
	 * Metodi näyttää pelimaailman ottamalla sen esille CardLayoutissa.
	 */
	public void vaihdaPelimaailmaan(){
		this.naytot.show(this.getContentPane(), PELIMAAILMA);
	}
	
	/**
	 * Metodi näyttää ohjenäkymän ottamalla sen esille CardLayoutissa.
	 */
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
