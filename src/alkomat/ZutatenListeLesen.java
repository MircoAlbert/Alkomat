package alkomat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ZutatenListeLesen {

	ZutatenListeLesen() {

	}


	public List<String> zutatenAusCocktailListe() throws IOException {
		Cocktail[] cocktails = (new CocktailPruefen()).getAllCocktails();
		List<String> zutaten = new ArrayList<String>();
		Set<String> zutatenTemp = new HashSet<String>();
		zutaten.add("");
		for (int i = 0; i < cocktails.length; i++){
			for (int j = 0; j < cocktails[i].getRezept().length; j++){
				zutatenTemp.add(cocktails[i].getRezept()[j]);
			}
		}	
		zutaten.addAll(zutatenTemp);	
		return zutaten;
	}

}
