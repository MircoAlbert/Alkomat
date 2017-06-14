package alkomat;

public class MengenPruefen {

	private Double[] mengenGeordnet = new Double[6];
	
	public Double[] mengenOrdnen(String[] zutatenCocktail, String[] zutatenBeh�lter, Double[] mengen){
		for(int i = 0; i<zutatenBeh�lter.length; i++)
			for(int j = 0 ; j<zutatenCocktail.length; j++)
				if(zutatenBeh�lter[i].equals(zutatenCocktail[j])){
					mengenGeordnet[i]=mengen[j];
					for(int k=0;k<mengenGeordnet.length;k++)
						if(mengenGeordnet[k]==null)
							mengenGeordnet[k]=0.0;
				}
		
				
		return mengenGeordnet;
	}
}
