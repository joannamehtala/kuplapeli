
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
		super(x, y, p.annaMaailma());
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

		for (int i = 0; i < 
				this.pelimaailma.annaMaailma().annaKuplat().size() - 1; 
				i++){
			this.kohde = this.pelimaailma.annaMaailma().annaKuplat().get(i);

			double deltaX = this.kohde.annaKeskiX() - aktiivinen.annaKeskiX();
			double deltaY = this.kohde.annaKeskiY() - aktiivinen.annaKeskiY();

			double etaisyysNelio = (deltaX * deltaX) + (deltaY * deltaY);

			double sade = 2 * this.kohde.annaSade();

			if (etaisyysNelio <= sade * sade){
				return true;
			}
		}
		return false;
	}

	public double annaEtaisyys(Kupla kupla, Piste piste){
		double deltaX = piste.annaX() - kupla.annaKeskiX();
		double deltaY = piste.annaY() - kupla.annaKeskiY();

		double etaisyysNelio = (deltaX * deltaX) + (deltaY * deltaY);

		return etaisyysNelio;
	}

	public void tasaaSijainti(Kupla kupla){
		Piste tutkittava;
		Piste lahin = null;
		Iterator<Piste> iteraattori = 
				this.pelimaailma.annaMaailma().pisteiteraattori();
		while (iteraattori.hasNext()){
			tutkittava = iteraattori.next();
			if (lahin == null || this.annaEtaisyys(kupla, tutkittava) 
					< this.annaEtaisyys(kupla, lahin)){
				lahin = tutkittava;
			}
		}
		kupla.asetaSijainti(lahin.annaX() - kupla.annaSade(), lahin.annaY() - 
				kupla.annaSade());
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
		if (y > 50 && y < 465 && 
				!this.pelimaailma.annaMaailma().annaNykyinen().koskeeToista()){
			x += Math.cos(Math.toRadians(aste))*muutos*0.1;
			y += Math.sin(Math.toRadians(aste))*muutos*0.1;

		} else {
			this.pysahtynyt = true;
			this.tasaaSijainti(this);
			this.annaNaapurit();
			/*for (int i = 0; i < this.annaNaapurit().size(); i++){
				System.out.println(this.annaNaapurit().get(i));
			}*/
			return;
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
