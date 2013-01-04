import java.util.Random;

/**
 * Maailma-luokka, joka sis‰lt‰‰ pelilogiikkaa maailman osalta; pidet‰‰n
 * silm‰ll‰ pelin kuplien lukum‰‰r‰‰, niiden v‰rej‰ ja asetetaan kuplat oikeille
 * paikoilleen.
 * @author 345480
 *
 */

public class Maailma {

	/**
	 * Attribuutteina leveys, korkeus, koordinaatit x ja y, pelimaailma,
	 * maailman nykyinen ja seuraava aktiivinen kupla, boolean pelin loppumisen
	 * tarkastelulle sek‰ kuplien lukum‰‰r‰‰ kuvaava int.
	 */
	private int leveys;
	private int korkeus;
	public static Random rand = new Random();
	private int kuplienLkm;
	private boolean peliLoppunut;
	private AktiivinenKupla nykyinen;

	/**
	 * Maailman konstruktori. Alustetaan attribuutit ja luodaan jonkin verran
	 * kuplia ruudun yl‰reunaan.
	 * @param 
	 */
	public Maailma(Pelimaailma pelimaailma){

		/*
		 * Alustetaan leveys ja korkeus pelikent‰n halutun koon mukaisesti.
		 */
		this.leveys = 450;
		this.korkeus = 500;

		/*
		 * Arvotaan max. 19 kuplaa aluksi yl‰reunaan.
		 */
		this.kuplienLkm = rand.nextInt(20);

		/*
		 * Luodaan aktiiviset kuplat.
		 */
		this.nykyinen = new AktiivinenKupla(pelimaailma, this, true);

		for (int i = 1; i < 10; i++){
			if (i % 2 == 1){
				//parittomille riveille luodaan 10 kuplaa alkaen x=50
			} else {
				//parillisille riveille luodaan 9 kuplaa alkaen x=72.5
			}
			
			
			/*if (i == 0){
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
				kupla3.asetaSijainti(x, y);*/
		}
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

	/**
	 * Palauttaa nykyisen aktiivisen kuplan.
	 * @return this.nykyinen
	 */
	public AktiivinenKupla annaNykyinen(){
		return this.nykyinen;
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
}
