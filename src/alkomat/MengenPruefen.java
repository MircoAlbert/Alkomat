package alkomat;

import java.util.*;

public class MengenPruefen {

	private List<Integer> mengenGeordnet = new ArrayList<Integer>();
	
	public Integer[] mengenOrdnen(String[] zutatenCocktail, String[] zutatenBehälter, Integer[] mengen){
		for(int i = 0; i<zutatenBehälter.length; i++)
			for(int j = 0 ; j<zutatenCocktail.length; j++)
				if(zutatenBehälter[i].equals(zutatenCocktail[j]))
					mengenGeordnet.add(mengen[j]);
		
		if(mengenGeordnet.isEmpty())
			System.out.println("mengen liste leer");
		
			
		return mengenGeordnet.toArray(new Integer[0]);
	}
}
