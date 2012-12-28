
public class AktiivinenKupla extends Kupla {
	private double aste;
	
	public AktiivinenKupla(double x, double y, Pelimaailma p){
		super(x, y);
		this.aste = 45;
	}
	
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
		 * LIikutetaan.
		 */
		x += Math.cos(Math.toRadians(aste))*muutos*0.1;

		/*
		 * Y:n muutokset (ei koskaan kimpoa y-suunnassa, vaan pysähtyy ylös
		 * tai kuplaan osuessaan).
		 */
		if (y > 50 && y < 405){
			y -= Math.sin(Math.toRadians(aste))*muutos*0.1;
		}
		
		this.asetaSijainti(x, y);
	}
}
