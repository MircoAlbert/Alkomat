package alkomat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class CocktailPruefen {

	String[] zutaten;
	BufferedReader zeilen;
	BufferedReader reader;
	File rezepte = new File("./res/Rezepte.txt");
	Cocktail[] Cocktails;
	InputStream standardRezepte = this.getClass().getClassLoader().getResourceAsStream("res/Rezepte.txt");
	
	// public static void main(String[] args) {
	// new CocktailPruefen();
	// }

	public CocktailPruefen() throws IOException {
		System.out.println(URLDecoder.decode(this.getClass().getClassLoader().getResource("res/Rezepte.txt").getFile(), "UTF-8"));
		if (!rezepte.exists()) {
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(standardRezepte));
			BufferedWriter writer = new BufferedWriter(new FileWriter(rezepte, true));
			String info;
			while ((info = reader.readLine()) != null) {
				writer.write(info);
				writer.newLine();

			}
			rezepte.setWritable(true, false);
			reader.close();
			writer.close();
		}

	}

	public boolean vergleichZutaten(String[] Vorhanden, String[] ImRezept) {

		int ZutatenCount = 0;
		int VorhandenCount = 0;

		for (int i = 0; i < ImRezept.length; i++) {
			if (!ImRezept[i].equals(""))
				ZutatenCount++;
		}
		for (int f = 0; f < Vorhanden.length; f++) {
			for (int u = 0; u < ImRezept.length; u++) {
				if (!(Vorhanden[f].equals("")))
					if (Vorhanden[f].equals(ImRezept[u]))
						VorhandenCount++;
			}

		}

		if (VorhandenCount == ZutatenCount) {
			// System.out.println("Cocktail moeglich");
			return true;
		} else {
			// System.out.println("Nicht genug Zutaten");
			return false;
		}
	}

	public List<Cocktail> loadCocktail(String[] zutaten) throws IOException {
		int zeilenzähler = -1;
		zeilen = new BufferedReader(new FileReader(rezepte));
		while (zeilen.readLine() != null) {
			zeilenzähler++;
		}

		Cocktails = new Cocktail[zeilenzähler];// Anzahl der Zeilen
		List<Cocktail> CocktailsList = new ArrayList<Cocktail>();
		// angeben!!!

		// Scanner input = new
		// Scanner(getClass().getResourceAsStream("rezepte.txt"));
		reader = new BufferedReader(new FileReader(rezepte));

		int countline = 0;
		String info;
		System.out.println(reader.readLine());
		while ((info = reader.readLine()) != null) {
			// Zeile Laden
			String elements[] = info.split(", "); // Aufteilung Name +
													// Zutaten
			String Zutaten[] = elements[1].split(";"); // Zutaten aufteilung
			String[] Mengen = elements[2].split(";"); // Mengen Aufteilung
			Cocktails[countline] = new Cocktail(elements[0], Zutaten, Mengen); // Objekte
			// herstellen
			countline++; // Zeilenzähler
			
		}
		String[] ImBehalter = zutaten;

		for (int i = 0; i < Cocktails.length; i++) {
			if (vergleichZutaten(ImBehalter, Cocktails[i].getRezept()) == true) {
				// System.out.println(Cocktails[i].getName());
				// System.out.println(" ist möglich");
				CocktailsList.add(Cocktails[i]);
			} else {
				// System.out.print(Cocktails[i].getName());
				// System.out.println(" ist nicht möglich");

			}

		}
		if (CocktailsList.isEmpty())
			return null;

		return CocktailsList;

	}

	public Cocktail[] getAllCocktails() throws IOException {
		String[] a = { "", "", "", "", "", "" };
		loadCocktail(a);
		return Cocktails;
	}

}
