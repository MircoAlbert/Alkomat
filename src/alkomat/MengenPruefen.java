package alkomat;

public class MengenPruefen {

	private Double[] mengenGeordnet = new Double[6];
	
	public Double[] mengenOrdnen(String[] zutatenCocktail, String[] zutatenBehälter, Double[] mengen){
		for(int i = 0; i<zutatenBehälter.length; i++)
			for(int j = 0 ; j<zutatenCocktail.length; j++)
				if(zutatenBehälter[i].equals(zutatenCocktail[j])){
					mengenGeordnet[i]=mengen[j];
					for(int k=0;k<mengenGeordnet.length;k++)
						if(mengenGeordnet[k]==null)
							mengenGeordnet[k]=0.0;
				}
		
				
		return mengenGeordnet;
	}
}
