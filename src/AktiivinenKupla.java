import java.util.Iterator;
import java.util.Random;


public class AktiivinenKupla extends Kupla {
	public static Random rand = new Random();
	private Pelimaailma pelimaailma;
	private double aste;
	private boolean pysahtynyt;
	private boolean ammuttu;

	public AktiivinenKupla(double x, double y, Pelimaailma p){
		super(x, y);
		this.pelimaailma = p;
		/*this.asetaSijainti(Pelimaailma.LAHTO_X - this.annaSade(),
				Pelimaailma.LAHTO_Y - this.annaSade());*/
	}

	public void ammu(double aste){
		if (this.ammuttu){
			throw new RuntimeException("Oli jo ammuttu");
		}
		this.ammuttu = true;
		this.aste = aste;
	}

	public boolean koskeeToista(AktiivinenKupla aktiivinen, Kupla kohde){
		if (kohde.annaY() - aktiivinen.annaY() == 45){
			return true;
		} else {
			return false;
		}
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
		/*Iterator<Kupla> iteraattori = 
				this.pelimaailma.annaMaailma().kuplaiteraattori();
		while (iteraattori.hasNext()){
			Kupla kohde = iteraattori.next();*/
		if (y > 50){
			x += Math.cos(Math.toRadians(aste))*muutos*0.1;

		} else {
			this.pysahtynyt = true;

		}


		/*
		 * Y:n muutokset (ei koskaan kimpoa y-suunnassa, vaan pysähtyy ylös
		 * tai kuplaan osuessaan).
		 */
		if (y > 50 && y < 465){
			y += Math.sin(Math.toRadians(aste))*muutos*0.1;
		}

		this.asetaSijainti(x, y);
	}

	public boolean onPysahtynyt(){
		return this.pysahtynyt;
	}

	public boolean onAmmuttu(){
		return this.ammuttu;
	}
}
