package alkomat;

import java.util.*;

public class MengenPruefen {

	private List<Double> mengenGeordnet = new ArrayList<Double>();
	
	public Double[] mengenOrdnen(String[] zutatenCocktail, String[] zutatenBeh�lter, Double[] mengen){
		for(int i = 0; i<zutatenBeh�lter.length; i++)
			for(int j = 0 ; j<zutatenCocktail.length; j++)
				if(zutatenBeh�lter[i].equals(zutatenCocktail[j]))
					mengenGeordnet.add(mengen[j]);
		
		if(mengenGeordnet.isEmpty())
			System.out.println("mengen liste leer");
		
			
		return mengenGeordnet.toArray(new Double[0]);
	}
}
