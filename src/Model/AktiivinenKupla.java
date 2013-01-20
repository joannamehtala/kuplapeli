package Model;
import java.util.Iterator;
import java.util.Random;

/**
 * Kupla-luokan alaluokka, jossa m‰‰ritell‰‰n ominaisuuksia aktiiviselle
 * kuplalle, joka ammutaan peliruudun alareunasta maailmaan.
 * @author 345480
 *
 */
public class AktiivinenKupla extends Kupla {

	/** Random arpomista varten. */
	public static Random rand = new Random();

	/** Maailma */
	private Maailma maailma;
	
	/** Kupla, jota kohti aktiivinen kupla ammutaan */
	private Kupla kohde;

	/** Kulma, jossa kupla liikkuu */
	private double aste;

	/** Onko kupla pys‰htynyt */
	private boolean pysahtynyt;

	/** Onko kupla jo ammuttu */
	private boolean ammuttu;

	/**
	 * Aktiivisen kuplan konstruktori.
	 * @param x x-koordinaatti
	 * @param y y-koordinaatti
	 * @param maailma jossa kupla sijaitsee
	 */
	public AktiivinenKupla(double x, double y, Maailma maailma){
		super(x, y, maailma);
		this.maailma = maailma;;
	}

	/**
	 * Metodi, jolla kupla ammutaan liikkeelle maailmaan.
	 * @param aste kulma, jossa kupla liikkuu
	 */
	public void ammu(double aste){
		
		/*
		 * Jos kupla on jo ammuttu, heitet‰‰n RuntimeException, koska ei
		 * haluta maailmassa tapahtuvan mit‰‰n ennen kuin edellinen ammuttu
		 * kupla on pys‰htynyt.
		 */
		if (this.ammuttu){
			throw new RuntimeException("Oli jo ammuttu");
		}
		
		this.ammuttu = true;
		this.aste = aste;
	}

	/**
	 * Metodi selvitt‰‰, koskeeko kupla jotain toista kuplaa.
	 * @param aktiivinen kupla jota tarkastellaan
	 * @return true, jos koskee toista kuplaa, ja false, jos ei
	 */
	public boolean koskeeToista(Kupla aktiivinen){

		/*
		 * K‰yd‰‰n l‰pi kaikki maailman kuplat lukuunottamatta nykyist‰
		 * aktiivista kuplaa.
		 */
		for (int i = 0; i < this.maailma.annaKuplat().size() - 1; 
				i++){
			this.kohde = this.maailma.annaKuplat().get(i);

			/*
			 * Lasketaan et‰isyyden neliˆ (helpompi k‰ytt‰‰ kuin et‰isyyden
			 * nelilˆjuurikaavaa).
			 */
			double deltaX = this.kohde.annaKeskiX() - aktiivinen.annaKeskiX();
			double deltaY = this.kohde.annaKeskiY() - aktiivinen.annaKeskiY();

			double etaisyysNelio = (deltaX * deltaX) + (deltaY * deltaY);

			double sade = 2 * this.kohde.annaSade();

			/*
			 * Jos kuplien keskipisteiden et‰isyys toisistaan on yht‰ kuin
			 * kuplien halkaisija (kaikilla kuplilla on yht‰ suuri s‰de), niin
			 * kuplat koskevat toisiaan.
			 */
			if (etaisyysNelio <= sade * sade && this.kohde.onEhja()){
				return true;
			}
		}
		return false;
	}

	/**
	 * Metodi palauttaa parametreina annettujen kuplan ja pisteen v‰lisen
	 * et‰isyyden neliˆn.
	 * @param kupla kupla, jonka et‰isyytt‰ pisteest‰ lasketaan
	 * @param piste piste, jonka et‰isyytt‰ kuplasta lasketaan
	 * @return et‰isyyden neliˆ
	 */
	public double annaEtaisyys(Kupla kupla, Piste piste){
		double deltaX = piste.annaX() - kupla.annaKeskiX();
		double deltaY = piste.annaY() - kupla.annaKeskiY();

		double etaisyysNelio = (deltaX * deltaX) + (deltaY * deltaY);

		return etaisyysNelio;
	}

	/**
	 * Metodi tasaa pys‰htyneen kuplan sijainnin niin, ett‰ se asettuu
	 * l‰himp‰‰n sopivaan pisteeseen.
	 * @param kupla jonka sijaintia tasataan
	 */
	public void tasaaSijainti(Kupla kupla){
		
		Piste tutkittava;
		Piste lahin = null;
		Iterator<Piste> iteraattori = 
				this.maailma.pisteiteraattori();
		
		/*
		 * K‰yd‰‰n l‰pi maailman pisteit‰ ja tutkitaan, mink‰ et‰isyys
		 * kuplasta on pienin. Tallennetaan aina toistaiseksi l‰hin v‰liaikais-
		 * muuttujaan.
		 */
		while (iteraattori.hasNext()){
			tutkittava = iteraattori.next();
			if (lahin == null || this.annaEtaisyys(kupla, tutkittava) 
					< this.annaEtaisyys(kupla, lahin)){
				lahin = tutkittava;
			}
		}
		/*
		 * Kun ollaan lˆydetty l‰hin piste, asetetaan kupla siihen pisteeseen.
		 */
		kupla.asetaSijainti(lahin.annaX() - kupla.annaSade(), lahin.annaY() - 
				kupla.annaSade());
		
		/*
		 * Jos kuplalle asetettu sijainti on alarajan toisella puolen, peli
		 * on h‰vitty.
		 */
		if (kupla.annaY() >= 450){
			this.maailma.annaPelimaailma().lopetaPeli(false);
		}
	}


	/**
	 * Metodi, jolla kuplaa liikutetaan. Kupla kimpoaa vasemmasta ja oikeasta
	 * sein‰st‰ kulmassa, jonka asteluku on 180 - tulokulma. Liikkuminen
	 * tapahtuu kasvattamalla x:n ja y:n arvoja haluttuun suuntaan.
	 * @param muutos jonka arvolla kuplaa liikutetaan
	 */
	public void liiku(long muutos){
		double x = this.annaX();
		double y = this.annaY();

		/*
		 * Vasempaan sein‰‰n tˆrm‰ys k‰‰nt‰‰ kuplan suuntaa.
		 */
		if (x < 50){
			aste = 180 - aste;
		}

		/*
		 * Oikeaan sein‰‰n tˆrm‰ys k‰‰nt‰‰ kuplan suuntaa.
		 */
		if (x > 410){
			aste = 180 - aste;
		}

		/*
		 * Kupla liikkuu, jos se on halutulla v‰lill‰ eik‰ se koske mit‰‰n
		 * toista kuplaa.
		 */
		if (y > 50 && y < 465 && 
				!this.maailma.annaNykyinen().
				koskeeToista(this.maailma.annaNykyinen())){
			x += Math.cos(Math.toRadians(aste))*muutos*0.5;
			y += Math.sin(Math.toRadians(aste))*muutos*0.5;

		/*
		 * Jos kupla on osunut yl‰reunaan tai tˆrm‰nnyt toiseen, asetetaan
		 * se pys‰htyneeksi ja tasataan sen sijainti. Sen j‰lkeen tarkistetaan
		 * poksautettavat kuplat ja mahdolliset yksin hengailemaan j‰‰neet
		 * kuplat.
		 */
		} else {
			this.pysahtynyt = true;
			this.tasaaSijainti(this);
			this.tarkistaPoksautettavat();
			this.maailma.tarkistaYksinaiset();
			return;
		}

		/*
		 * Lopuksi asetetaan uusi sijainti kuplalle.
		 */
		this.asetaSijainti(x, y);
	}

	/**
	 * Palauttaa tiedon siit‰, onko kupla pys‰htynyt.
	 * @return true, jos on pys‰htynyt, false, jos ei
	 */
	public boolean onPysahtynyt(){
		return this.pysahtynyt;
	}

	/**
	 * Palauttaa tiedon siit‰, onko kupla ammuttu.
	 * @return true, jos on ammuttu, false, jos ei
	 */
	public boolean onAmmuttu(){
		return this.ammuttu;
	}
}
