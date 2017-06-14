package alkomat;

public class Cocktail {

	   private String Name;
	   private String[] Zutaten;
	   private String[] Mengen;
	   
	   public Cocktail(String InputName, String[] InputZutaten, String[] InputMengen)
	   {
		   Name=InputName;
		   Zutaten= InputZutaten;
		   Mengen=InputMengen;
	   }


	   public String getName(){
		   return Name;
	   }
	   
	   public String[] getRezept(){
		   return Zutaten;
	   }
	   
	   public Double[] getMengen(){
		   Double[] MengenDouble=new Double[Mengen.length];
		   System.out.println(Mengen.length);
		   for(int i=0; i<Mengen.length; i++){
			   MengenDouble[i]=Double.parseDouble(Mengen[i]);
		   }
		   return MengenDouble;
	   }
}


