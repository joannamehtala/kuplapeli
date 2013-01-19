package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

import Controller.Ohjaaja;
import View.Pelimaailma;


/**
 * Maailma-luokka, joka sis‰lt‰‰ pelilogiikkaa: miten luodaan seuraavaksi
 * ammuttava kupla, miten lis‰t‰‰n kuplia maailmaan, miten saadaan yksin‰iset
 * kuplat tippumaan yms.
 * @author 345480
 *
 */

public class Maailma {

	/** Maailman leveys. */
	private int leveys;

	/** Maailman korkeus. */
	private int korkeus;

	/** Random-olio arpomista varten. */
	public static Random rand = new Random();

	/** Boolean-tyyppinen attribuutti, joka kertoo, onko peli loppunut vai ei.*/
	private boolean peliLoppunut;

	/** Kaikki maailman kuplat ker‰t‰‰n pinoon. */
	private Stack<Kupla> kuplat;

	/** Ehj‰t kuplat ker‰t‰‰n arraylistiin. */
	private ArrayList<Kupla> ehjat;

	/** Maailmaa vastaava graafinen pelimaailma. */
	private Pelimaailma pelimaailma;

	/** Pelin ohjaaja. */
	private Ohjaaja ohjaaja;

	/** Alkupiste x, josta piirt‰minen aloitetaan. */
	private double alkupiste_x;

	/** Alkupiste y, josta piirt‰minen aloitetaan. */
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
	 * kuplia ruudun yl‰reunaan sek‰ pisteet, joihin kuplat ammuttaessa
	 * asettuvat.
	 * @param pelimaailma
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
			 * Luodaan parittomien rivien kuplat (9).
			 */
			if (i % 2 == 0){
				for (int a = 0; a < 9; a++){
					Piste piste = new Piste(this.alkupiste_x+22.5+(a*45),
							this.alkupiste_y+22.5+(i*45));
					this.pisteet.add(piste);
				}
				/*
				 * Luodaan parillisten rivien kuplat (8).
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
		 * Luodaan ensimm‰inen ammuttava kupla peliruudun alareunaan.
		 */
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
	 * Ampuu nykyisen kuplan halutussa kulmassa ja kasvatetaan ampumiskertoja.
	 * @param kulma, jossa kupla ammutaan liikkeelle
	 */
	public void ammuNykyinen(double kulma){
		this.annaNykyinen().ammu(kulma);
		this.ampumiskerrat++;
	}

	/**
	 * Tutkitaan maailman kuplat ja selvitet‰‰n, ovatko ne ehji‰.
	 */
	public void tutkiEhjat(){
		this.ehjat = new ArrayList<Kupla>();
		Iterator<Kupla> iteraattori = this.kuplaiteraattori();
		while (iteraattori.hasNext()){
			Kupla tutkittava = iteraattori.next();
			if (tutkittava.onEhja()){
				ehjat.add(tutkittava);
			}
		}
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

		this.tutkiEhjat();

		/* Kuplia lis‰t‰‰n vain, jos maailmassa on ehji‰ kuplia.*/
		if (!this.ehjat.isEmpty() && !this.onVainPutoavia()){

			/* Kuplia lis‰t‰‰n tiettyjen ampumiskertojen v‰lein. */
			if (this.ampumiskerrat % 10 == 0){

				/*
				 * K‰yd‰‰n l‰pi maailman pisteet ja pudotetaan niit‰ yhden 
				 * rivin verran alasp‰in.
				 */

				Iterator<Piste> iteraattori = this.pisteiteraattori();
				while(iteraattori.hasNext()){
					Piste pudotettava = iteraattori.next();
					pudotettava.asetaSijainti(pudotettava.annaX(), 
							pudotettava.annaY() + 45);
				}

				/* Pudotetaan myˆs kuplia yhden rivin verran alasp‰in. */

				for (int i = 0; i < this.kuplat.size(); i++){
					Kupla pudotettava = this.kuplat.get(i);
					pudotettava.asetaSijainti(pudotettava.annaX(), 
							pudotettava.annaY() + 45);

					/* 
					 * Jos pudotettavat kuplat putoavat maailman alareunan
					 * yli (tai siis ali hehheh), niin peli h‰vit‰‰n.
					 */

					if (pudotettava.onEhja() && pudotettava.annaY() >= 450){

						this.pelimaailma.lopetaPeli(false);
					}
				}

				/* 
				 * Joka toisella kerralla kuplat luodaan limitt‰in ja joka 
				 * toisella "suoraan". Aloitetaan limitt‰in luomisesta, koska 
				 * alempi rivi on tehty "suoraan".
				 * 
				 */

				if (this.lisaamiskerrat % 2 == 0){

					/* Luodaan uudet pisteet (8) yl‰reunaan. */
					for (int a = 0; a < 8; a++){
						Piste uusi = new Piste(this.alkupiste_x+45+(a*45),
								this.alkupiste_y+22.5);
						this.pisteet.add(uusi);
					}

					/* Luodaan rivin verran (8) kuplia yl‰reunaan. */
					for (int b = 0; b < 8; b++){
						Kupla uusi = new Kupla(this.alkupiste_x+22.5+(b*45),
								this.alkupiste_y, this);
						this.kuplat.push(uusi);
					}
					this.lisaamiskerrat++;

					/* Nyt luodaan uudet pisteet ja kuplat "suoraan". */
				} else {

					/* Luodaan uudet pisteet (9) yl‰reunaan. */
					for (int a = 0; a < 9; a++){
						Piste uusi = new Piste(this.alkupiste_x+22.5+(a*45),
								this.alkupiste_y+22.5);
						this.pisteet.add(uusi);
					}

					/* Luodaan rivin verran (9) kuplia yl‰reunaan. */
					for (int b = 0; b < 9; b++){
						Kupla uusi = new Kupla(this.alkupiste_x+(b*45),
								this.alkupiste_y, this);
						this.kuplat.push(uusi);
					}
					this.lisaamiskerrat++;
				}
			}

			/* Jos maailmassa ei ole en‰‰ ehji‰ kuplia, peli on voitettu. */
		} else {
			this.peliLoppunut = true;
			this.pelimaailma.lopetaPeli(true);
		}
	}

	/**
	 * Metodi palauttaa listan maailman kuplien v‰reist‰.
	 * @return lista kuplien v‰reist‰
	 */
	public ArrayList<Vari> annaKuplienVarit(){

		ArrayList<Vari> kuplienVarit = new ArrayList<Vari>();
		Iterator<Kupla> iteraattori = this.kuplaiteraattori();

		while (iteraattori.hasNext()){
			Kupla tutkittava = iteraattori.next();

			/* Kukin v‰ri lis‰t‰‰n vain kerran listaan. */
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

		this.tutkiEhjat();

		/*
		 * Uusi kupla arvotaan, jos maailmassa on ehji‰ kuplia, jotka eiv‰t
		 * ole (kaikki) putoavia kuplia.
		 */
		double arpa = rand.nextDouble();

		if (!ehjat.isEmpty() && !this.onVainPutoavia()){

			/* 5 % todenn‰kˆisyydell‰ maailmaan arvotaan superkupla. */
			if (arpa < 0.05){

				Superkupla superkupla = 
						new Superkupla(Pelimaailma.LAHTO_X - 22.5,
								Pelimaailma.LAHTO_Y - 22.5, this);
				this.kuplat.push(superkupla);

				/* 95 % todenn. maailmaan luodaan normaali kupla. */	
			} else {
				AktiivinenKupla arvottu =
						new AktiivinenKupla(Pelimaailma.LAHTO_X - 22.5,
								Pelimaailma.LAHTO_Y - 22.5, this);

				/* 
				 * Jos maailmasta on poksautettu kaikki tietynv‰riset 
				 * kuplat, maailmaan ei en‰‰ arvota sen v‰rist‰ kuplaa.
				 */
				if (this.annaKuplienVarit().contains(arvottu.annaVari())){
					this.kuplat.push(arvottu);
				} else {
					this.arvoUusi();
				}
			}

			//Jos ehji‰ kuplia ei ole en‰‰ maailmassa, niin peli on voitettu.
		} else {
			this.peliLoppunut = true;
			this.pelimaailma.lopetaPeli(true);
		}
	}

	/**
	 * Liikutetaan nykyist‰ kuplaa parametrina nykyisen hetken ja edellisen
	 * hetken v‰linen erotus. Jos kupla on pys‰htynyt, arvotaan uusi
	 * kupla ja mahdollisesti myˆs lis‰t‰‰n rivi kuplia.
	 * @param muutos
	 */
	public void liikutaNykyista(long muutos){
		this.annaNykyinen().liiku(muutos);
		if (this.annaNykyinen().onPysahtynyt()){
			this.lisaaKuplia();
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

	/**
	 * Palauttaa maailman pelimaailman.
	 * @return pelimaailma
	 */
	public Pelimaailma annaPelimaailma(){
		return this.pelimaailma;
	}

	/**
	 * Tarkistetaan maailmassa hengailevat mahdolliset yksin‰iset kuplat, eli
	 * sellaiset, jotka eiv‰t ole mit‰‰n kautta kiinni katossa. Jos sellaisia
	 * lˆytyy, asetetaan ne putoaviksi.
	 */
	public void tarkistaYksinaiset(){

		ArrayList<Kupla> pysyvat = new ArrayList<Kupla>();

		/* 
		 * K‰yd‰‰n l‰pi kaikki maailman kuplat. Jos kupla on yl‰riviss‰,
		 * lis‰t‰‰n se pysyvien listaan kutsumalla metodia lisaaPysyva, joka
		 * lis‰‰ listaan myˆs kaikki kuplan naapurin naapurit.
		 */
		for (int i = 0; i < this.kuplat.size(); i++){
			Kupla tutkittava = this.kuplat.get(i);
			if (tutkittava.annaY() == 50 && tutkittava.onEhja()){
				this.lisaaPysyva(tutkittava, pysyvat);
			}
		}

		/*
		 * Nyt kun on tallennettu pysyvien listaan kaikki ne maailman kuplat,
		 * jotka ovat naapureidensa kautta kiinni katossa, asetetaan putoaviksi
		 * kaikki listan ulkopuolelle j‰‰neet.
		 */
		for (int i = 0; i < this.kuplat.size(); i++){
			if (!pysyvat.contains(this.kuplat.get(i)) && 
					this.kuplat.get(i).onEhja()){
				this.kuplat.get(i).asetaPutoavaksi();
			}
		}
	}

	/**
	 * Metodi lis‰‰ parametrina annettuun listaan kaikki parametrina annetun
	 * kuplan naapurit rekursion avulla.
	 * @param kupla, lis‰tt‰v‰ kupla
	 * @param pysyvat, lista niist‰ kuplista, joita ei ole tarkoitus pudottaa
	 */
	public void lisaaPysyva(Kupla kupla, ArrayList<Kupla> pysyvat){

		/*
		 * Jos lista ei jo sis‰ll‰ kyseist‰ kuplaa, lis‰t‰‰n kupla listaan.
		 */
		if (!pysyvat.contains(kupla)){
			pysyvat.add(kupla);
			ArrayList<Kupla> naapurit = kupla.annaNaapurit();
			/*
			 * Lis‰t‰‰n kaikki kuplan naapurin naapurit listaan rekursiolla.
			 */
			for (int i = 0; i < naapurit.size(); i++){
				this.lisaaPysyva(naapurit.get(i), pysyvat);
			}
		}
	}

	/**
	 * Metodi pudottaa kaikki maailman putoaviksi asetetut kuplat eli k‰ynnist‰‰ 
	 * niiden putoamisen k‰ytt‰en apunaan nykyisen hetken ja edellisen hetken 
	 * v‰list‰ erotusta (joka annetaan ohjaaja-luokassa).
	 * @param muutos
	 */
	public void pudota(long muutos){
		for (int i = 0; i < this.kuplat.size(); i++){
			Kupla tutkittava = this.kuplat.get(i);
			if (tutkittava.onPutoava() && tutkittava.onEhja()){
				tutkittava.putoa(muutos);
			}
		}
	}

	/**
	 * Kertoo, onko maailmassa j‰ljell‰ en‰‰ vain putoavia kuplia. T‰t‰
	 * tarvitaan, kun lis‰t‰‰n kuplia maailmaan tai arvotaan uusi - ei haluta
	 * enemp‰‰ kuplia, jos maailman viimeiset kuplat ovat juuri putoamassa.
	 * @return true, jos maailmassa on j‰ljell‰ vain putoavia kuplia, ja false,
	 * jos on j‰ljell‰ muitakin.
	 */
	public boolean onVainPutoavia(){
		ArrayList<Kupla> putoavat = new ArrayList<Kupla>();

		for (int i = 0; i < this.ehjat.size(); i++){
			Kupla putoava = this.ehjat.get(i);
			if (putoava.onPutoava()){
				putoavat.add(putoava);
			}
		}

		if (putoavat.size() == this.ehjat.size()){
			return true;
		}
		return false;
	}
}
