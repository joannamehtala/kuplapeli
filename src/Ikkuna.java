
import java.awt.CardLayout;
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
	
	/** Eri n�yt�t. */
	private Aloitusnaytto aloitusnaytto;
	private Pelimaailma pelimaailma;
	private Ohjenaytto ohjenaytto;
	
	/** CardLayoutia varten vaaditut Stringit n�ytt�j� kuvaamaan.*/
	private static final String ALOITUSNAYTTO = "aloitusnaytto";
	private static final String PELIMAAILMA = "pelimaailma";
	private static final String OHJENAYTTO = "ohjenaytto";

	/**
	 * Ikkunan konstruktorissa alustetaan attribuutit ja lis�t��n ikkunaan
	 * eri n�kym�t. Lis�ksi asetetaan ikkunan koko ja otsikko ja laitetaan
	 * se aukeamaan keskelt�. Asetetaan my�s ohjelman suoritus loppumaan, kun
	 * ikkuna suljetaan.
	 */
	public Ikkuna(){

		/*
		 * Alustetaan attribuutit; luodaan uusi layout ja eri n�yt�t.
		 */
		
		this.naytot = new CardLayout();
		this.aloitusnaytto = new Aloitusnaytto(this);
		this.pelimaailma = new Pelimaailma(this);
		this.ohjenaytto = new Ohjenaytto(this);

		/*
		 * Asetetaan otsikko, layout, koko sek� aukeamiskohta. Lis�t��n
		 * ikkunaan eri n�yt�t. Asetetaan pelin ajo loppumaan, kun painetaan 
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
	 * Metodi n�ytt�� pelimaailman ottamalla sen esille CardLayoutissa.
	 */
	public void vaihdaPelimaailmaan(){
		this.naytot.show(this.getContentPane(), PELIMAAILMA);
	}
	
	/**
	 * Metodi n�ytt�� ohjen�kym�n ottamalla sen esille CardLayoutissa.
	 */
	public void vaihdaOhjenayttoon(){
		this.naytot.show(this.getContentPane(), OHJENAYTTO);
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
