package rs.cvecara.ispitOP22.nemanja;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cvecara {
	
	private String naziv;
	private String webAdresa;
	private String telefon;
	private ArrayList<Cvet> lista = new ArrayList<Cvet>();
	
	
	public Cvecara() {
	}

	public Cvecara(String naziv, String webAdresa, String telefon) {
		this.naziv = naziv;
		this.webAdresa = webAdresa;
		this.telefon = telefon;
		this.lista = new ArrayList<Cvet>();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWebAdresa() {
		return webAdresa;
	}

	public void setWebAdresa(String webAdresa) {
		this.webAdresa = webAdresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public ArrayList<Cvet> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Cvet> lista) {
		this.lista = lista;
	}

	public double cenaSvogCveca() {
		double suma = 0.0;
		for(int i = 0; i < this.lista.size(); i++) {
			suma += this.lista.get(i).getCena();
		}
		return suma;
	}
	
	public double prosecnaVrednostCveca() {
		double prosek = 0;
		if(cenaSvogCveca() > 0.0) {
			prosek = cenaSvogCveca() / this.lista.size();
		}
		return prosek;
	}

	@Override
	public String toString() {
		String temp = "";
		temp += "Naziv cvecare je: " + this.naziv + "\n";
		temp += "Web adresa cvecare je: " + this.webAdresa + "\n";
		temp += "Telefon cvecare je: " + this.telefon + "\n";
		temp += "Ukupna vrednost cveca je: " + cenaSvogCveca() + "\n";
		temp += "\n";
		return temp;
	}
	
	public void unosCvecare(String naziv, String webAdresa, String telefon) {
		this.naziv = naziv;
		this.webAdresa = webAdresa;
		this.telefon = telefon;
	}
	
	public boolean unosCveta(Cvet cvet) {
		for(int i = 0; i < this.lista.size(); i++) {
			if(cvet.getId() == this.lista.get(i).getId()) {
				return false;
			}
		}
		this.lista.add(cvet);
		return true;
	}
	
	public void ispisCvetova() {
		
		for(int i = 0; i < this.lista.size(); i++) {
			System.out.println(this.lista.get(i));
		}
	
	}
	
	public Cvet izmenaCveta (int id, Cvet cvet) {
		
		int indeks = -1;
		for(int i = 0; i < this.lista.size(); i++) {
			if(this.lista.get(i).getId() == id) {
				indeks = i;
			}
		}
		if(indeks != -1){
			Cvet cvetZaIzmenu = this.lista.set(indeks, cvet);
			return cvetZaIzmenu;
		}
		return null;
	}
	
	public Cvet brisanjeCveta (int id)	{
		
		int indeks = -1;
		for(int i = 0; i < this.lista.size(); i++) {
			if(this.lista.get(i).getId() == id) {
				indeks = i;
			}
		}
		if(indeks != -1){
			Cvet cvetZaBrisanje = this.lista.remove(indeks);
			return cvetZaBrisanje;
		}
		return null;
		
	}
	
	public Cvet prodajaNavedeneKolicine (int id, int kolicina) {
		
		for(int i = 0; i < this.lista.size(); i++) {
			if(this.lista.get(i).getId() == id && this.lista.get(i).getKolicina() > kolicina && kolicina > 0) {
				this.lista.get(i).setKolicina(this.lista.get(i).getKolicina() - kolicina);
				return this.lista.get(i);
			}
		}
		
		return null;
	}
	
	public void ispisPocetnoIme(String naziv) {
		
		for(int i = 0; i < this.lista.size(); i++) {
			if(this.lista.get(i).getNazivVrste().startsWith(naziv)){
				System.out.println(this.lista.get(i));
			}
		}
	}
	
	public Cvet pretragaMinCenaDatum(Date datumPocetni, Date datumKrajnji)	{
		
		double min = this.lista.get(0).getCena();
		int indeks = -1;
		for(int i = 0; i < this.lista.size(); i++) {
			if(this.lista.get(i).getDatumUvrstavanja().compareTo(datumPocetni) <=0
				&& this.lista.get(i).getDatumPrestankaPonude().compareTo(datumKrajnji) >=0) {
				
				if(this.lista.get(i).getCena()<min){
					indeks = i;
				}
			}
			
			
		}
		if(indeks != -1){
			System.out.println(this.lista.get(indeks));
			return this.lista.get(indeks);
		}
		return null;
	}	
	
	
	
	public void save(String path) {
		
		ArrayList<String> lines = new ArrayList<String>();
		
		for (int i = 0; i < this.lista.size(); i++) {
			
			 SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
			 
			 Cvet cvet = this.lista.get(i); 
			 int identifikacioniBroj = cvet.getId();
			 String nazivVrste = cvet.getNazivVrste();
			 Date datumP = cvet.getDatumUvrstavanja();
			 String formatiraniDatumP = sdf.format(datumP); 
			 Date datumK = cvet.getDatumPrestankaPonude();
			 String formatiraniDatumK = sdf.format(datumK); 
			 double cena = cvet.getCena();
			 int kolicina = cvet.getKolicina();
			 String line = identifikacioniBroj  + ";" + nazivVrste + ";" + formatiraniDatumP + ";" + formatiraniDatumK + ";" + cena + ";" + kolicina;
			 lines.add(line);
			 
		}
		
		try {
			if(!Files.exists(Paths.get(path))){
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE_NEW);
			} else {
				Files.write(Paths.get(path), lines, Charset.defaultCharset(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronadjena.");
			e.printStackTrace();
		}
	}
	
	public void load(String path) {
		
		this.lista = new ArrayList<Cvet>();
		
		List<String> lines;
		try {
			
			lines = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
			
			for (String line: lines) {
				
				String[] attributes = line.split(";");
				
				int id = Integer.parseInt(attributes[0]); 
				String nazivVrste = attributes[1];
				String datumP = attributes[2];
				Date datumZaCuvanjeP = null;
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
				try {
					datumZaCuvanjeP = sdf.parse(datumP);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String datumK = attributes[3];
				Date datumZaCuvanjeK = null;
				try {
					datumZaCuvanjeK = sdf.parse(datumK);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				double cena = Double.parseDouble(attributes[4]); 
				int kolicina = Integer.parseInt(attributes[5]); 
				
				Cvet cvet = new Cvet(id, nazivVrste, datumZaCuvanjeP, datumZaCuvanjeK, cena, kolicina);
				this.lista.add(cvet);
				
			}
		} catch (java.io.IOException e) {
			System.out.println("Datoteka " + path + " nije pronadjena.");
			e.printStackTrace();
		}
	
	}
}
