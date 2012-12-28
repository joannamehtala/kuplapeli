
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Pelimaailma extends JPanel implements ActionListener{

	private int leveys;
	private int korkeus;
	private Maailma maailma;
	private Ikkuna ikkuna;
	private float x;
	private float y;
	private Timer timer;
	//private Kupla kuplat[][];
	private Kupla kupla;
	private long edellinenhetki;
	private double aste;
	private static final Image taustakuva =
			//new ImageIcon("media/tausta.jpg", "taustakuva");
			Toolkit.getDefaultToolkit().createImage("media/tausta.jpg");
	//private JLabel tausta;

	public Pelimaailma(Maailma maailma, Ikkuna ikkuna){
		this.maailma = maailma;
		this.leveys = maailma.annaLeveys();
		this.korkeus = maailma.annaKorkeus();
		this.ikkuna = ikkuna;
		//this.kuplat = new Kupla[10][10];
		this.x = 228;
		this.y = 404;
		this.kupla = new Kupla((int) this.x, (int) this.y);
		this.edellinenhetki = 0;
		this.aste = 170;
		//this.tausta = new JLabel();
		this.setOpaque(false);
		this.setPreferredSize(new Dimension(500, 500));
		/*this.tausta.setIcon(taustakuva);
		this.add(tausta);*/
		this.timer = new Timer(10, this);
		timer.start();

		/*for (x = 0; x < 10; x++){
			for (y = 0; y < 10; y++){
				Kupla kupla1 = new Kupla(x, y);
				this.kuplat[x][y] = kupla1;
			}
		}*/
	}

	public void paintComponent(Graphics g)  {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(taustakuva, 0, 0, this);
		g2d.drawImage(kupla.annaKuva(), (int) this.x, (int) this.y, this);
	}

	/**
	 * Metodi hoitaa kuplan liikuttamisen timerin avulla. Kupla liikkuu
	 * edellisen ja nykyisen hetken välisen muutoksen tarkistelun avulla
	 * tasaisesti riippumatta siitä, kuinka usein metodia kutsutaan.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.edellinenhetki == 0){
			this.edellinenhetki = System.currentTimeMillis();
			return;
		}
		long muutos = System.currentTimeMillis() - this.edellinenhetki;
		this.edellinenhetki = System.currentTimeMillis();
		
		/*
		 * Törmättiin vasempaan seinään.
		 */
		if (x < 50){
			double uusiX = this.x + Math.cos(Math.toRadians(aste))*muutos*0.1;
			double uusiY = this.y - Math.sin(Math.toRadians(aste))*muutos*0.1;
			//aste = Math.atan2(uusiY - this.y, uusiX - this.x);
			this.aste = 180 - this.aste;
		}
		
		if (x > 405){
			this.aste = 180 - this.aste;
		}
		
		this.x += Math.cos(Math.toRadians(aste))*muutos*0.1;

		if (y > 50 && y < 405){
			this.y -= Math.sin(Math.toRadians(aste))*muutos*0.1;
		}
		repaint();
	}
}