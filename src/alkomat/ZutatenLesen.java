package alkomat;

import java.io.*;
import java.io.FileNotFoundException;

class ZutatenLesen {

	ZutatenLesen() throws FileNotFoundException {
		
	}
	
	String[] leseZutaten(){
		String zutaten[] = new String[6];
		BufferedReader reader;
		File zutatentxt = new File("./Zutaten.txt");
		try{
			String info;
			System.out.println("Reading...");
			reader=new BufferedReader(new FileReader(zutatentxt));
			info = reader.readLine();
			zutaten = info.split(",");
						
			} catch (Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		return zutaten;
	
		
	}

}
