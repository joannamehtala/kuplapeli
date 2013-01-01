import java.util.Random;


public class AktiivinenKupla extends Kupla {
	private double aste;
	private double lahtokulma;
	public static Random rand = new Random();

	public AktiivinenKupla(double x, double y, Pelimaailma p){
		super(x, y);
		this.aste = rand.nextInt(181);
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
			this.aste = 180 - this.aste;
		}

		/*
		 * Törmättiin oikeaan seinään.
		 */
		if (x > 405){
			this.aste = 180 - this.aste;
		}

		/*
		 * Liikutetaan.
		 */
		x += Math.cos(Math.toRadians(this.aste))*muutos*0.1;

		/*
		 * Y:n muutokset (ei koskaan kimpoa y-suunnassa, vaan pysähtyy ylös
		 * tai kuplaan osuessaan).
		 */
		if (y > 50 && y < 465){
			y -= Math.sin(Math.toRadians(aste))*muutos*0.1;
		}

		this.asetaSijainti(x, y);
	}

	public double annaKulma(){
		return this.aste;
	}
}
