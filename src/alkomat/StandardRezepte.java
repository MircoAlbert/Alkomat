package alkomat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StandardRezepte {

	StandardRezepte() throws IOException{
		File standardRezepte = new File("Rezepte.txt");
		File rezepteNeu = new File("./Rezepte.txt");
		BufferedReader reader = new BufferedReader(new FileReader(standardRezepte));
		BufferedWriter writer = new BufferedWriter(new FileWriter(rezepteNeu,true));
		String info;
		while((info=reader.readLine()) != null){
			writer.write(info);
			writer.newLine();
			
		}
		reader.close();
		writer.close();
	}
}
