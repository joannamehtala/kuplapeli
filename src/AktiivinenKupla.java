import java.util.Random;


public class AktiivinenKupla extends Kupla {
	public static Random rand = new Random();
	private Maailma maailma;
	private Pelimaailma pelimaailma;
	private double aste;
	private boolean pysahtynyt;

	public AktiivinenKupla(double x, double y, Pelimaailma p, Maailma m){
		super(x, y);
		this.maailma = m;
		this.pelimaailma = m.annaPelimaailma();
	}

	/**
	 * Metodi, jolla kuplaa liikutetaan. Kupla kimpoaa vasemmasta ja oikeasta
	 * seinästä kulmassa, jonka asteluku on 180 - tulokulma. Liikkuminen
	 * tapahtuu kasvattamalla x:n ja y:n arvoja haluttuun suuntaan.
	 * @param muutos
	 */
	public void liiku(long muutos){
		double x = this.annaX();
		double y = this.annaY();
		
		this.aste = this.pelimaailma.annaKulma();
		
		/*
		 * Törmättiin vasempaan seinään.
		 */
		if (x < 50){
			aste = 180 - aste;
		}
		
		/*
		 * Törmättiin oikeaan seinään.
		 */
		if (x > 405){
			aste = 180 - aste;
		}

		/*
		 * Liikutetaan.
		 */
		x += Math.cos(Math.toRadians(aste))*muutos*0.1;

		/*
		 * Y:n muutokset (ei koskaan kimpoa y-suunnassa, vaan pysähtyy ylös
		 * tai kuplaan osuessaan).
		 */
		if (y > 50 && y < 465){
			y -= Math.sin(Math.toRadians(aste))*muutos*0.1;
		}
		
		if (y == 50){
			this.pysahdy();
		}

		this.asetaSijainti(x, y);
	}
	
	/**
	 * Kupla pysähtyy, kun sen y-koord. on 50 eli se saapuu ruudun yläreunaan
	 * tai kun se törmää toiseen kuplaan. Kun kupla pysähtyy, 
	 */
	public void pysahdy(){
		this.pysahtynyt = true;
	}
	
	/**
	 * Antaa sen asteen, johon on liiku-metodissa päädytty mahdollisen törmäilyn
	 * seurauksena.
	 * @return
	 */
	public double annaAste(){
		return this.aste;
	}
	
	public boolean onPysahtynyt(){
		return this.pysahtynyt;
	}
}
