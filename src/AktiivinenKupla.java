import java.util.Random;


public class AktiivinenKupla extends Kupla {
	public static Random rand = new Random();
	private Pelimaailma pelimaailma;
	private double aste;
	private boolean pysahtynyt;
	private boolean ammuttu;
	private double x;
	private double y;

	public AktiivinenKupla(Pelimaailma p){
		super();
		this.pelimaailma = p;
	}

	public void ammu(double aste){
		if (this.ammuttu){
			throw new RuntimeException("Oli jo ammuttu");
		}
		this.ammuttu = true;
		this.aste = aste;
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
}
