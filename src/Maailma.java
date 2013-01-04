import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

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
	private boolean peliLoppunut;
	private Stack<Kupla> kuplat;
	private Pelimaailma pelimaailma;
	private Ohjaaja ohjaaja;
	

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
		this.pelimaailma = pelimaailma;
		
		
		this.kuplat = new Stack<Kupla>();
		AktiivinenKupla kupla_nykyinen = 
				new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
				Pelimaailma.LAHTO_Y - 22.5, this.pelimaailma);
		this.kuplat.push(kupla_nykyinen);
		this.ohjaaja = new Ohjaaja(this, this.pelimaailma, this.annaNykyinen());

		/*
		 * Luodaan aktiiviset kuplat.
		 */

		for (int i = 1; i < 10; i++){
			if (i % 2 == 1){
				//parittomille riveille luodaan 10 kuplaa alkaen x=50
			} else {
				//parillisille riveille luodaan 9 kuplaa alkaen x=72.5
			}
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
		return (AktiivinenKupla) this.kuplat.peek();
	}
	
	public Iterator<Kupla> kuplaiteraattori(){
		return this.kuplat.iterator();
	}
	
	public void ammuNykyinen(double kulma){
		this.annaNykyinen().ammu(kulma);
	}
	
	public void arvoUusi(){
		AktiivinenKupla arvottu = new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
				Pelimaailma.LAHTO_Y - 22.5, 
				this.pelimaailma);
		this.kuplat.push(arvottu);
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
