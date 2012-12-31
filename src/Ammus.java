
public class Ammus {
	private Maailma maailma;
	private Ohjaaja ohjaaja;
	private Pelimaailma pelimaailma;
	private Kupla nykyinenKupla;
	private Kupla seuraavaKupla;
	
	public Ammus(Maailma maailma, Ohjaaja ohjaaja, Pelimaailma pelimaailma){
		this.maailma = maailma;
		this.ohjaaja = ohjaaja;
		this.pelimaailma = pelimaailma;
		this.nykyinenKupla = new Kupla(250, 404);
		this.seuraavaKupla = new Kupla(250, 449);
	}
}
