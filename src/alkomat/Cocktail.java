package alkomat;

public class Cocktail {

	   private String Name;
	   private String[] Zutaten;
	   private String[] Mengen;
	   private Double[] MengenDouble;

	   public Cocktail(String InputName, String[] InputZutaten, String[] InputMengen)
	   {
	Name=InputName;
	//System.out.println(" ");
	//System.out.print("GetrankeName: ");
	//System.out.println(InputName);
	Zutaten= InputZutaten;
	//System.out.println("ZutatenListe: ");
	for (int i = 0; i<InputZutaten.length;i++)
  {
		//System.out.println(InputZutaten[i]);
  }
	Mengen=InputMengen;
	   }


	   public String getName(){
		   return Name;
	   }
	   
	   public String[] getRezept(){
		   return Zutaten;
	   }
	   
	   public Double[] getMengen(){
		   MengenDouble=new Double[Mengen.length];
		   for(int i=0; i<Mengen.length; i++)
			   MengenDouble[i]=Double.parseDouble(Mengen[i]);
		   return MengenDouble;
	   }
}


