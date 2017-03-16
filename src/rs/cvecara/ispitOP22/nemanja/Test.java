package rs.cvecara.ispitOP22.nemanja;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test {
	
	public static Scanner scanner = new Scanner(System.in);
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	public static boolean proveriDatum(String datum) {
		
		try {
			sdf.parse(datum);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static boolean daLiJeVeciDatum(String datumPocetni, String datumKrajnji) {

		Date datumP = null;
		Date datumK = null;
		try {
			datumP = sdf.parse(datumPocetni);
			datumK = sdf.parse(datumKrajnji);
			if(datumK.compareTo(datumP) > 0) {
				return true;
			} else {
				System.out.println("Krajnji datum mora biti veci od pocetnog. Pokusajte ponovo.");
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}
	
	public static boolean isDecimalNumber(String string) {
		try {
			double b = Double.parseDouble(string);
			if (b>0){
				return true;
			} else{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
	public static boolean isNumber(String string) {
		try{
			int b = Integer.parseInt(string);
			if (b>0){
				return true;
			} else{
				return false;
			}
		}catch (Exception e){
			return false;
		}
	}
	
	public static void unesiCvecaru (Cvecara cvecara){
		String naziv;
		String webAdresa;
		String telefon;
		System.out.println("Unesi naziv cvecare:");
		naziv = scanner.nextLine();
		System.out.println("Unesi web adresu cvecare:");
		webAdresa = scanner.nextLine();
		System.out.println("Unesi telefon cvecare:");
		telefon = scanner.nextLine();
		
		cvecara.unosCvecare(naziv, webAdresa, telefon);	
		
		System.out.println(cvecara);
	}
	
	public static boolean unosCveta (Cvecara cvecara){
		
		int id;
		String nazivVrste;
		Date datumUvrstavanja = null;
		Date datumPrestankaPonude = null;
		double cena;
		int kolicina;
		
		String idUnos = null;
		String pocDatUnos = null;
		String krDatUnos = null;
		String cenaUnos = null;
		String kolicinaUnos = null;
		
		do{
		System.out.println("Unesi ID cveta:");
		idUnos = scanner.nextLine();
		}while(!isNumber(idUnos));
		id = Integer.parseInt(idUnos);
		System.out.println("Unesi naziv cveta");
		nazivVrste = scanner.nextLine();
		
		do{
			System.out.println("Unesi datum uvrstavanja cveta:");
			pocDatUnos = scanner.nextLine();
		}while(!proveriDatum(pocDatUnos));
		do{
			System.out.println("Unesi datum prestanka ponude cveta:");
			krDatUnos = scanner.nextLine();
		}while(!daLiJeVeciDatum(pocDatUnos, krDatUnos));
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		try {
			datumUvrstavanja = sdf.parse(pocDatUnos);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			datumPrestankaPonude = sdf.parse(krDatUnos);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		do{
		System.out.println("Unesi cenu cveta:");
		cenaUnos = scanner.nextLine();
		}while(!isDecimalNumber(cenaUnos));
		cena = Double.parseDouble(cenaUnos);
		do{
			System.out.println("Unesi kolicinu:");
			kolicinaUnos = scanner.nextLine();
			}while(!isNumber(kolicinaUnos));
		kolicina = Integer.parseInt(kolicinaUnos);
		System.out.println();
		
		Cvet cvet = new Cvet(id, nazivVrste, datumUvrstavanja, datumPrestankaPonude, cena, kolicina);
		boolean provera = cvecara.unosCveta(cvet);
		
		if(provera) {
			System.out.println("Cvet je uspesno dodat.");
		} else {
			System.out.println("Cvet sa unetim ID brojem vec postoji. Pokusajte ponovo.");
		}
		return provera;
		
	}
	
	public static void ispisiCvetove(Cvecara cvecara) {

		cvecara.ispisCvetova();
	}
	
	public static Cvet izmenaCveta (Cvecara cvecara){
		
		int id;
		String nazivVrste;
		Date datumUvrstavanja = null;
		Date datumPrestankaPonude = null;
		double cena;
		int kolicina;
		
		String idUnos = null;
		String pocDatUnos = null;
		String krDatUnos = null;
		String cenaUnos = null;
		String kolicinaUnos = null;
		
		do{
		System.out.println("Unesi ID cveta za izmenu:");
		idUnos = scanner.nextLine();
		}while(!isNumber(idUnos));
		id = Integer.parseInt(idUnos);
		System.out.println("Unesi naziv cveta");
		nazivVrste = scanner.nextLine();
		
		do{
			System.out.println("Unesi datum uvrstavanja cveta:");
			pocDatUnos = scanner.nextLine();
		}while(!proveriDatum(pocDatUnos));
		do{
			System.out.println("Unesi datum prestanka ponude cveta:");
			krDatUnos = scanner.nextLine();
		}while(!daLiJeVeciDatum(pocDatUnos, krDatUnos));
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		try {
			datumUvrstavanja = sdf.parse(pocDatUnos);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			datumPrestankaPonude = sdf.parse(krDatUnos);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		do{
		System.out.println("Unesi cenu cveta:");
		cenaUnos = scanner.nextLine();
		}while(!isDecimalNumber(cenaUnos));
		cena = Double.parseDouble(cenaUnos);
		do{
			System.out.println("Unesi kolicinu:");
			kolicinaUnos = scanner.nextLine();
			}while(!isNumber(kolicinaUnos));
		kolicina = Integer.parseInt(kolicinaUnos);
		System.out.println();
		
		Cvet cvet = new Cvet(id, nazivVrste, datumUvrstavanja, datumPrestankaPonude, cena, kolicina);
		Cvet provera = cvecara.izmenaCveta(id, cvet);
		
		if(provera != null) {
			System.out.println("Cvet je uspesno izmenjen.");
		} else {
			System.out.println("Cvet nije uspesno izmenjen.");
		}
		return provera;
	}
	
	public static Cvet brisiCvet(Cvecara cvecara) {
		
		int id;
		String idUnos = null;
		do{
			System.out.println("Unesi ID cveta za brisanje: ");
			idUnos = scanner.nextLine();
		}while(!isNumber(idUnos));
		id = Integer.parseInt(idUnos);
		Cvet provera = cvecara.brisanjeCveta(id);
		if(provera != null) {
			System.out.println("Cvet uspesno obrisan!");
		} else {
			System.out.println("Cvet nije obrisan. Pokusajte ponovo.");
		}
		
		return provera;
	}	
	
	public static Cvet prodajaKolicine(Cvecara cvecara) {
		
		int id;
		String idUnos = null;
		int kolicina;
		String kolicinaUnos = null;
		do{
			System.out.println("Unesi ID cveta za prodaju: ");
			idUnos = scanner.nextLine();
		}while(!isNumber(idUnos));
		id = Integer.parseInt(idUnos);
		do{
			System.out.println("Unesi kolicinu za prodaju: ");
			kolicinaUnos = scanner.nextLine();
		}while(!isNumber(kolicinaUnos));
		kolicina = Integer.parseInt(kolicinaUnos);
		
		Cvet provera = cvecara.prodajaNavedeneKolicine(id, kolicina);
		
		if(provera != null) {
			System.out.println("Kolicina uspesno prodata!");
			System.out.println("Podaci o cvetu nakon prodaje:");
			System.out.println(provera);
		} else {
			System.out.println("Neuspesna prodaja. Pokusajte ponovo.");
		}
		
		return provera;
		
	}
	
	public static void pretraziPocetnoIme(Cvecara cvecara) {
		
		System.out.println("Unesi pocetak naziva cveta:");
		String pocetniNaziv = scanner.nextLine();
		
		cvecara.ispisPocetnoIme(pocetniNaziv);
		
	}	
	
	public static void prosekCenaSvihCvetova(Cvecara cvecara) {
		System.out.println("Prosecna cena svih cvetova je: " + cvecara.prosecnaVrednostCveca());
	}
	
	public static void pretraziMinCenaDatum(Cvecara cvecara) {
		
		String datumP = null;
		String datumK = null;
		Date datumPocetni = null;
		Date datumKrajnji = null;
		
		do{
			System.out.println("Unesi datum pocetka perioda za pretragu: ");
			datumP = scanner.nextLine();
		}while(!proveriDatum(datumP));
		do{
			System.out.println("Unesi datum kraja perioda za pretragu:");
			datumK = scanner.nextLine();
		}while(!daLiJeVeciDatum(datumP, datumK));	
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
		try {
			datumPocetni = sdf.parse(datumP);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			datumKrajnji = sdf.parse(datumK);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		cvecara.pretragaMinCenaDatum(datumPocetni, datumKrajnji);
	}	
	
	public static void ispisiCvecaru(Cvecara cvecara) {
		System.out.println(cvecara);
	}
	
	public static void main(String[] args) {
		
		Cvecara cvecara = new Cvecara();
		
		if(Files.exists(Paths.get("cvecara.txt"))) {
			cvecara.load("cvecara.txt");
		}
		
		String answer = null;
		
		do {

			System.out.println("Meni:");
			System.out.println("1. Unesi podatke o cvecari");
			System.out.println("2. Unesi novu vrstu cveta");
			System.out.println("3. Ispisi podatke o cvetovima u ponudi");
			System.out.println("4. Izmeni podatke o cvetu");
			System.out.println("5. Brisi cvet");
			System.out.println("6. Prodaj odredjenu kolicinu cvetova");
			System.out.println("7. Pretrazi cvetove ciji naziv pocinje prosledjenim nazivom");
			System.out.println("8. Prikazi prosecnu cenu cvetova");
			System.out.println("9. Prikazi najpovoljniji cvet za zadati period");
			System.out.println("10. Ispisi podatke o cvecari");
			System.out.println("x. Izlaz");

			answer = scanner.nextLine();

			switch (answer) {
			case "1":
				unesiCvecaru(cvecara);
				break;
			case "2":
				unosCveta(cvecara);
				cvecara.save("cvecara.txt");
				break;
			case "3":
				ispisiCvetove(cvecara);
				break;
			case "4":
				izmenaCveta(cvecara);
				cvecara.save("cvecara.txt");
				break;
			case "5":
				brisiCvet(cvecara);
				cvecara.save("cvecara.txt");
				break;
			case "6":
				prodajaKolicine(cvecara);
				break;
			case "7":
				pretraziPocetnoIme(cvecara);
				break;
			case "8":
				prosekCenaSvihCvetova(cvecara);
				break;	
			case "9":
				pretraziMinCenaDatum(cvecara);
				break;
			case "10":
				ispisiCvecaru(cvecara);
				break;	
			case "x":
				break;
			default:
				System.out.println("Pogresan izbor opcije. Pokusajte ponovo.");
			}

		} while (!answer.equals("x"));
		
		scanner.close();
		
	}
}
