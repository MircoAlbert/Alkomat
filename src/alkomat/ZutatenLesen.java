package alkomat;

import java.awt.Toolkit;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
			
			
			
			/*Scanner input = new Scanner(getClass().getResourceAsStream("./Zutaten.txt"));
			String info = input.nextLine();
			zutaten = info.split(",");
			//for (String s : zutaten)
			//	System.out.println(s);
			input.close();
			*/
			
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			
		}
		return zutaten;
	
		
	}

}
