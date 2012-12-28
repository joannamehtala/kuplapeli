

public class Maailma {
	private int leveys;
	private int korkeus;
	private int x;
	private int y;

	public Maailma(){
		this.leveys = 450;
		this.korkeus = 450;
		
		if (this.x > this.leveys){
			this.x = this.leveys;
		}
		if (this.y > this.korkeus){
			this.y = this.korkeus;
		}
	}
	
	public int annaLeveys(){
		return this.leveys;
	}
	
	public int annaKorkeus(){
		return this.korkeus;
	}
}
