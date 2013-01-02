import java.util.Random;

/**
 * Maailma-luokka, joka sis�lt�� pelilogiikkaa maailman osalta; pidet��n
 * silm�ll� pelin kuplien lukum��r��, niiden v�rej� ja asetetaan kuplat oikeille
 * paikoilleen.
 * @author 345480
 *
 */

public class Maailma {
	
	/**
	 * Attribuutteina leveys, korkeus, koordinaatit x ja y sek� ammus, joita
	 * voi olla peliss� vain yksi.
	 */
	private int leveys;
	private int korkeus;
	private double x;
	private double y;
	private Pelimaailma pelimaailma;
	public static Random rand = new Random();
	private int kuplienLkm;
	private boolean peliLoppunut;
	private AktiivinenKupla nykyinen;
	private AktiivinenKupla seuraava;

	/**
	 * Maailman konstruktori. Alustetaan attribuutit ja tehd��n leveyden ja
	 * korkeuden tarkistukset, jotta pysyt��n halutuissa rajoissa.
	 * @param 
	 */
	public Maailma(Pelimaailma pelimaailma){
		/*
		 * Alustetaan leveys ja korkeus 450:ksi pelikent�n halutun koon
		 * mukaisesti.
		 */
		this.leveys = 450;
		this.korkeus = 500;
		this.kuplienLkm = rand.nextInt(20);
		this.pelimaailma = pelimaailma;
		this.nykyinen = new AktiivinenKupla(228, 454, this.pelimaailma, this);
		this.seuraava = new AktiivinenKupla(228, 499, this.pelimaailma, this);
		
		this.x = 50;
		this.y = 50;
		
		for (int i = 0; i < kuplienLkm; i++){
			if (i == 0){
			Kupla kupla1 = new Kupla(x, y);
			kupla1.asetaSijainti(x, y);
			} else if (i < 10){
				this.x += 45;
				Kupla kupla2 = new Kupla(x, y);
				kupla2.asetaSijainti(x, y);
			} else if (i < 20){
				this.x += 50;
				this.y += 45;
				Kupla kupla3 = new Kupla(x, y);
				kupla3.asetaSijainti(x, y);
			}
		}
	}
	
	public Pelimaailma annaPelimaailma(){
		return this.pelimaailma;
	}
	
	/**
	 * Metodi palauttaa maailman leveyden.
	 * @return int leveys
	 */
	public int annaLeveys(){
		return this.leveys;
	}
	
	/**
	 * Metodi palauttaa maailman korkeuden.
	 * @return int korkeus
	 */
	public int annaKorkeus(){
		return this.korkeus;
	}
	
	public AktiivinenKupla annaNykyinen(){
		return this.nykyinen;
	}
	
	public AktiivinenKupla annaSeuraava(){
		return this.seuraava;
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
}
