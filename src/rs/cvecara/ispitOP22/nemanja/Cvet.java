package rs.cvecara.ispitOP22.nemanja;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Cvet {
	
	private int id;
	private String nazivVrste;
	private Date datumUvrstavanja;
	private Date datumPrestankaPonude;
	private double cena;
	private int kolicina;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	public Cvet() {
	}

	public Cvet(int id, String nazivVrste, Date datumUvrstavanja, Date datumPrestankaPonude, double cena,
			int kolicina) {
		super();
		this.id = id;
		this.nazivVrste = nazivVrste;
		this.datumUvrstavanja = datumUvrstavanja;
		this.datumPrestankaPonude = datumPrestankaPonude;
		this.cena = cena;
		this.kolicina = kolicina;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNazivVrste() {
		return nazivVrste;
	}

	public void setNazivVrste(String nazivVrste) {
		this.nazivVrste = nazivVrste;
	}

	public Date getDatumUvrstavanja() {
		return datumUvrstavanja;
	}

	public void setDatumUvrstavanja(Date datumUvrstavanja) {
		this.datumUvrstavanja = datumUvrstavanja;
	}

	public Date getDatumPrestankaPonude() {
		return datumPrestankaPonude;
	}

	public void setDatumPrestankaPonude(Date datumPrestankaPonude) {
		this.datumPrestankaPonude = datumPrestankaPonude;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getKolicina() {
		return kolicina;
	}

	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}

	@Override
	public String toString() {
		
		String temp = "";
		temp += "Id cveta je: " + this.id + "\n";
		temp += "Naziv vrste cveta je: " + this.nazivVrste + "\n";
		temp += "Datum uvrstavanja u ponudu je: " + sdf.format(this.datumUvrstavanja) + "\n";
		temp += "Datum prestanka vazenja ponude je: " + sdf.format(this.datumPrestankaPonude) + "\n";
		temp += "Cena za komad je: " + this.cena + "\n";
		temp += "Kolicina iznosi: " + this.kolicina + " " + "kom." + "\n";
		temp += "\n";
		return temp;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
