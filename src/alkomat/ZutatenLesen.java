package alkomat;

import java.io.*;
import java.io.FileNotFoundException;

class ZutatenLesen {

	ZutatenLesen() throws FileNotFoundException {
		
	}
	
	String[] leseZutaten(){
		String zutaten[] = new String[6];
		BufferedReader reader;
		File zutatentxt = new File("./res/Zutaten.txt");
		try{
			String info;
			System.out.println("Reading...");
			reader=new BufferedReader(new FileReader(zutatentxt));
			info = reader.readLine();
			zutaten = info.split(",");
			for(int i=0;i<zutaten.length;i++)
				if(zutaten[i].equals("leer"))
					zutaten[i]="";
			} catch (Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		return zutaten;
	
		
	}

}
