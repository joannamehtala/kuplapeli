
import java.util.ArrayList;
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

	/** Maailman leveys ja korkeus. */
	private int leveys;
	private int korkeus;

	/** Random-olio arpomista varten. */
	public static Random rand = new Random();

	/** Boolean-tyyppinen attribuutti, joka kertoo, onko peli loppunut vai ei.*/
	private boolean peliLoppunut;

	/** Kaikki maailman kuplat ker‰t‰‰n pinoon. */
	private Stack<Kupla> kuplat;

	/** Maailman pelimaailma ja ohjaaja. */
	private Pelimaailma pelimaailma;
	private Ohjaaja ohjaaja;

	/** Alkupisteet x ja y, joista kuplien piirt‰minen aloitetaan. */
	private double alkupiste_x;
	private double alkupiste_y;

	/** Lista pisteille, joihin kuplat asetetaan. */
	private ArrayList<Piste> pisteet;


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

		/*
		 * Alustetaan kuplien pino ja pisteiden lista.
		 */
		this.kuplat = new Stack<Kupla>();
		this.pisteet = new ArrayList<Piste>();

		/*
		 * M‰‰ritet‰‰n kuplien piirt‰misen alkupisteiksi kohta (50,50).
		 */
		this.alkupiste_x = 50;
		this.alkupiste_y = 50;

		/*
		 * Luodaan pisteit‰ 10 riville, joihin maailmaan ammuttavat kuplat 
		 * asetetaan.
		 */
		for (int i = 0; i < 10; i++){
			/*
			 * Parittomille riveille tulee 10 paikkaa kuplille.
			 */
			if (i % 2 == 0){
				for (int a = 0; a < 9; a++){
					Piste piste = new Piste(this.alkupiste_x+22.5+(a*45),
							this.alkupiste_y+22.5+(i*45));
					this.pisteet.add(piste);
				}
				/*
				 * Parillisille riveille tulee 9 paikkaa kuplille, jotta ne
				 * asetetaan kauniisti limitt‰in.
				 */
			} else {
				for (int a = 0; a < 8; a++){
					Piste piste2 = new Piste(this.alkupiste_x+45+(a*45),
							this.alkupiste_y+22.5+(i*45));
					this.pisteet.add(piste2);

				}
			}
		}
		/*
		 * Luodaan kuplia muutaman rivin verran pelin aluksi.
		 */
		for(int i = 0; i < this.pisteet.size() - 51; i++){
			Kupla kupla = new Kupla(this.pisteet.get(i).annaX() - 22.5, 
					this.pisteet.get(i).annaY() - 22.5, this);
			this.kuplat.push(kupla);
		}

		/*
		for (int i = 0; i < 4; i++){
			if (i % 2 == 0){
				/*
				 * Parittomille riveille luodaan 10 kuplaa alkaen x = 50.

				for (int a = 0; a < 9; a++){
					Kupla kupla = new Kupla(this.alkupiste_x+(a*45), 
							this.alkupiste_y+(i*45), this);
					this.kuplat.push(kupla);
				}
			} else {
				/*
				 * Parillisille riveille luodaan 9 kuplaa alkaen x = 72.5
				 
				for (int a = 0; a < 8; a++){
					Kupla kupla2 = new Kupla(this.alkupiste_x+22.5+(a*45),
							this.alkupiste_y+(i*45), this);
					this.kuplat.push(kupla2);
				}
			}
		}*/

		/*
		 * Luodaan pelin aluksi yksi aktiivinen kupla peliruudun alareunaan
		 * seuraavaksi ammuttavaksi.
		 */
		AktiivinenKupla kupla_nykyinen = 
				new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
						Pelimaailma.LAHTO_Y - 22.5, this.pelimaailma);
		this.kuplat.push(kupla_nykyinen);
		
		/*
		 * Luodaan ohjaaja.
		 */
		this.ohjaaja = new Ohjaaja(this, this.pelimaailma);
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
	 * @return kuplat-pinon p‰‰llimm‰inen kupla
	 */
	public AktiivinenKupla annaNykyinen(){
		return (AktiivinenKupla) this.kuplat.peek();
	}

	/**
	 * Iteroidaan maailman kuplia.
	 * @return kuplaiteraattori
	 */
	public Iterator<Kupla> kuplaiteraattori(){
		return this.kuplat.iterator();
	}

	/**
	 * Iteroidaan maailman pisteit‰.
	 * @return pisteiteraattori
	 */
	public Iterator<Piste> pisteiteraattori(){
		return this.pisteet.iterator();
	}

	/**
	 * Ampuu nykyisen kuplan halutussa kulmassa.
	 * @param kulma, jossa kupla ammutaan liikkeelle
	 */
	public void ammuNykyinen(double kulma){
		this.annaNykyinen().ammu(kulma);
	}

	/**
	 * Arvotaan uusi kupla seuraavaksi ammuttavaksi ja lis‰t‰‰n se maailman
	 * kuplien pinoon p‰‰llimm‰iseksi.
	 */
	public void arvoUusi(){
		AktiivinenKupla arvottu = new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
				Pelimaailma.LAHTO_Y - 22.5, 
				this.pelimaailma);
		this.kuplat.push(arvottu);
	}

	/**
	 * Liikutetaan nykyist‰ kuplaa. Jos kupla on pys‰htynyt, arvotaan uusi
	 * kupla.
	 * @param muutos
	 */
	public void liikutaNykyista(long muutos){
		this.annaNykyinen().liiku(muutos);
		if (this.annaNykyinen().onPysahtynyt()){
			this.arvoUusi();
		}
	}

	/**
	 * Palauttaa kuplien pinon.
	 * @return kuplat, maailman kuplien pino
	 */
	public Stack<Kupla> annaKuplat(){
		return this.kuplat;
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
