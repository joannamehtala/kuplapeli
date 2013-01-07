
import java.util.Iterator;
import java.util.Random;


public class AktiivinenKupla extends Kupla {
	public static Random rand = new Random();
	private Pelimaailma pelimaailma;
	private double aste;
	private boolean pysahtynyt;
	private boolean ammuttu;
	private Kupla kohde;

	public AktiivinenKupla(double x, double y, Pelimaailma p){
		super(x, y);
		this.pelimaailma = p;
	}

	public void ammu(double aste){
		if (this.ammuttu){
			throw new RuntimeException("Oli jo ammuttu");
		}
		this.ammuttu = true;
		this.aste = aste;
	}

	public boolean koskeeToista(){
		Kupla aktiivinen = this.pelimaailma.annaMaailma().annaNykyinen();
		Iterator<Kupla> iteraattori = 
				this.pelimaailma.annaMaailma().kuplaiteraattori();
		while (iteraattori.hasNext()){


			/*for (int i = 0; i < this.pelimaailma.annaMaailma().annaKuplat().size() - 1; i++){
			this.kohde = this.pelimaailma.annaMaailma().annaKuplat().get(i);*/

			this.kohde = iteraattori.next();
			double etaisyys = Math.sqrt(Math.pow(((this.kohde.annaX() + 
					this.kohde.annaSade()) - (aktiivinen.annaX() + 
							aktiivinen.annaSade())),2) + 
							Math.pow(((this.kohde.annaY() + 
									this.kohde.annaSade()) - (aktiivinen.annaY()
											+ aktiivinen.annaSade())), 2));
			if (etaisyys == this.kohde.annaSade() + aktiivinen.annaSade()){
				System.out.println("koskee");
				return true;
			}
		}
		System.out.println("ei koske");
		return false;
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
		if (y > 50 && y < 465){
			if (!this.pelimaailma.annaMaailma().annaNykyinen().koskeeToista()){
				x += Math.cos(Math.toRadians(aste))*muutos*0.1;
				y += Math.sin(Math.toRadians(aste))*muutos*0.1;
			}

		} else {
			this.pysahtynyt = true;
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
