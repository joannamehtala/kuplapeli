
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

	/** Tallennetaan attribuuttiin ampumiskerrat. */
	private int ampumiskerrat;

	/** Tallennetaan attribuuttiin kuplien lis‰‰miskerrat. T‰h‰n ei lasketa
	 * alkuper‰isten kuplien luomista.
	 */
	private int lisaamiskerrat;


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
		 * Peli ei ole viel‰ loppunut maailmaa luotaessa.
		 */
		this.peliLoppunut = false;

		/*
		 * Alustetaan ampumiskerrat nollaksi.
		 */
		this.ampumiskerrat = 0;

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

		AktiivinenKupla kupla_nykyinen = 
				new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
						Pelimaailma.LAHTO_Y - 22.5, this);
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
		this.ampumiskerrat++;
	}

	/**
	 * Metodi lis‰‰ kuplia maailmaan joka kymmenennen ampumiskerran j‰lkeen.
	 * Aluksi pudotetaan kaikkia maailman kuplia ja pisteit‰ yhden rivin
	 * verran alasp‰in. Sitten luodaan yl‰riviin uudet kuplat ja pisteet joka
	 * toisella kerralla limitt‰in ja joka toisella suoraan. Uudet pisteet
	 * t‰ytyy luoda, jotta kuplat asettuisivat oikein, kun niit‰ ammutaan
	 * uuteen yl‰riviin.
	 */
	public void lisaaKuplia(){
		System.out.println(this.lisaamiskerrat);
		if (this.ampumiskerrat % 10 == 0){

			Iterator<Piste> iteraattori = this.pisteiteraattori();
			while(iteraattori.hasNext()){
				Piste pudotettava = iteraattori.next();
				pudotettava.asetaSijainti(pudotettava.annaX(), 
						pudotettava.annaY() + 45);
			}

			for (int i = 0; i < this.kuplat.size(); i++){
				Kupla pudotettava = this.kuplat.get(i);
				pudotettava.asetaSijainti(pudotettava.annaX(), 
						pudotettava.annaY() + 45);
			}

			//Joka toisella kerralla kuplat luodaan limitt‰in ja joka toisella
			//"suoraan". Aloitetaan limitt‰in luomisesta, koska alempi rivi
			//on tehty "suoraan".
			System.out.println(this.lisaamiskerrat);
			if (this.lisaamiskerrat % 2 == 0){

				//Luodaan uudet pisteet yl‰reunaan.
				for (int a = 0; a < 8; a++){
					Piste uusi = new Piste(this.alkupiste_x+45+(a*45),
							this.alkupiste_y+22.5);
					this.pisteet.add(uusi);
				}
				
				//Luodaan rivin verran kuplia yl‰reunaan.
				for (int b = 0; b < 8; b++){
					Kupla uusi = new Kupla(this.alkupiste_x+22.5+(b*45),
							this.alkupiste_y, this);
					this.kuplat.push(uusi);
				}
				this.lisaamiskerrat++;

			//Nyt luodaan uudet pisteet ja kuplat "suoraan".
			} else {
				
				//Luodaan uudet pisteet yl‰reunaan.
				for (int a = 0; a < 9; a++){
					Piste uusi = new Piste(this.alkupiste_x+22.5+(a*45),
							this.alkupiste_y+22.5);
					this.pisteet.add(uusi);
				}
				
				//Luodaan rivin verran kuplia yl‰reunaan.
				for (int b = 0; b < 9; b++){
					Kupla uusi = new Kupla(this.alkupiste_x+(b*45),
							this.alkupiste_y, this);
					this.kuplat.push(uusi);
				}
				this.lisaamiskerrat++;
			}
		}
	}
	
	public void tarkistaSijainnit(){
		for (int i = 0; i < this.kuplat.size() - 1; i++){
			if (this.kuplat.get(i).annaY() >= 450){
				this.pelimaailma.lopetaPeli(false);
			}
		}
	}

	public ArrayList<Vari> annaKuplienVarit(){
		ArrayList<Vari> kuplienVarit = new ArrayList<Vari>();
		Iterator<Kupla> iteraattori = this.kuplaiteraattori();
		while (iteraattori.hasNext()){
			Kupla tutkittava = iteraattori.next();
			if (tutkittava.onEhja() && 
					!kuplienVarit.contains(tutkittava.annaVari())){
				kuplienVarit.add(tutkittava.annaVari());
			}
		}
		return kuplienVarit;
	}


	/**
	 * Arvotaan uusi kupla seuraavaksi ammuttavaksi ja lis‰t‰‰n se maailman
	 * kuplien pinoon p‰‰llimm‰iseksi.
	 */
	public void arvoUusi(){

		ArrayList<Kupla> ehjat = new ArrayList<Kupla>();
		Iterator<Kupla> iteraattori = this.kuplaiteraattori();
		while (iteraattori.hasNext()){
			Kupla tutkittava = iteraattori.next();
			if (tutkittava.onEhja()){
				ehjat.add(tutkittava);
			}
		}

		double arpa = rand.nextDouble();

		if (!ehjat.isEmpty()){
			if (arpa < 0.04){
				Superkupla superkupla = new Superkupla(Pelimaailma.LAHTO_X - 22.5,
						Pelimaailma.LAHTO_Y - 22.5, this);
				this.kuplat.push(superkupla);
			} else {
				AktiivinenKupla arvottu = new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
						Pelimaailma.LAHTO_Y - 22.5, this);
				if (this.annaKuplienVarit().contains(arvottu.annaVari())){
					this.kuplat.push(arvottu);
				} else {
					this.arvoUusi();
				}
			}

		} else {
			this.peliLoppunut = true;
			this.pelimaailma.lopetaPeli(true);
		}
	}

	/**
	 * Liikutetaan nykyist‰ kuplaa. Jos kupla on pys‰htynyt, arvotaan uusi
	 * kupla.
	 * @param muutos
	 */
	public void liikutaNykyista(long muutos){
		this.annaNykyinen().liiku(muutos);
		if (this.annaNykyinen().onPysahtynyt()){
			this.lisaaKuplia();
			//TODO t‰h‰n joku timer.wait koska muuten pelaaja ei ehdi n‰hd‰,
			//miksi se h‰vi‰‰ kun sen kupla putoaa pelimaailman ulkopuolelle
			//kun tulee lis‰‰ kuplia.
			this.tarkistaSijainnit();
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

	public Pelimaailma annaPelimaailma(){
		return this.pelimaailma;
	}
}
