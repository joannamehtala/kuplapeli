package Model;


import java.util.Iterator;
import java.util.Random;






public class AktiivinenKupla extends Kupla {
	public static Random rand = new Random();
	private Maailma maailma;
	private double aste;
	private boolean pysahtynyt;
	private boolean ammuttu;
	private Kupla kohde;

	public AktiivinenKupla(double x, double y, Maailma maailma){
		super(x, y, maailma);
		this.maailma = maailma;;
	}

	public void ammu(double aste){
		if (this.ammuttu){
			throw new RuntimeException("Oli jo ammuttu");
		}
		this.ammuttu = true;
		this.aste = aste;
	}

	public boolean koskeeToista(Kupla aktiivinen){

		for (int i = 0; i < this.maailma.annaKuplat().size() - 1; 
				i++){
			this.kohde = this.maailma.annaKuplat().get(i);

			double deltaX = this.kohde.annaKeskiX() - aktiivinen.annaKeskiX();
			double deltaY = this.kohde.annaKeskiY() - aktiivinen.annaKeskiY();

			double etaisyysNelio = (deltaX * deltaX) + (deltaY * deltaY);

			double sade = 2 * this.kohde.annaSade();

			if (etaisyysNelio <= sade * sade && this.kohde.onEhja()){
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
				this.maailma.pisteiteraattori();
		while (iteraattori.hasNext()){
			tutkittava = iteraattori.next();
			if (lahin == null || this.annaEtaisyys(kupla, tutkittava) 
					< this.annaEtaisyys(kupla, lahin)){
				lahin = tutkittava;
			}
		}
		kupla.asetaSijainti(lahin.annaX() - kupla.annaSade(), lahin.annaY() - 
				kupla.annaSade());
		if (kupla.annaY() >= 450){
			System.out.println("h�visit");
			this.maailma.annaPelimaailma().lopetaPeli(false);
		}
	}


	/**
	 * Metodi, jolla kuplaa liikutetaan. Kupla kimpoaa vasemmasta ja oikeasta
	 * sein�st� kulmassa, jonka asteluku on 180 - tulokulma. Liikkuminen
	 * tapahtuu kasvattamalla x:n ja y:n arvoja haluttuun suuntaan.
	 * @param muutos
	 */
	public void liiku(long muutos){
		double x = this.annaX();
		double y = this.annaY();

		/*
		 * T�rm�ttiin vasempaan sein��n.
		 */
		if (x < 50){
			aste = 180 - aste;
		}

		/*
		 * T�rm�ttiin oikeaan sein��n.
		 */
		if (x > 405){
			aste = 180 - aste;
		}

		/*
		 * Liikutetaan.
		 */
		if (y > 50 && y < 465 && 
				!this.maailma.annaNykyinen().
				koskeeToista(this.maailma.annaNykyinen())){
			x += Math.cos(Math.toRadians(aste))*muutos*0.3;
			y += Math.sin(Math.toRadians(aste))*muutos*0.3;
			

		} else {
			this.pysahtynyt = true;
			this.tasaaSijainti(this);
			this.tarkistaPoksautettavat();
			this.maailma.tarkistaYksinaiset();
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