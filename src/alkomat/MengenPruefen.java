package alkomat;

import java.util.*;

public class MengenPruefen {

	private List<Integer> mengenGeordnet = new ArrayList<Integer>();
	
	public Integer[] mengenOrdnen(String[] zutatenCocktail, String[] zutatenBehälter, Integer[] mengen){
		for(int i = 0; i<zutatenBehälter.length; i++)
			for(int j = 0 ; j<zutatenCocktail.length; j++)
				if(zutatenCocktail[i].equals(zutatenBehälter[j]))
					mengenGeordnet.add(mengen[i]);
				
			
		return mengenGeordnet.toArray(new Integer[0]);
	}
}
