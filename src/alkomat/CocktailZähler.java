package alkomat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CocktailZ�hler {
	File z�hler = new File("./res/.z�hler.alk");
	public void counter() throws IOException{
		String info;
		int counter;
		BufferedReader reader = new BufferedReader(new FileReader(z�hler));
		info=reader.readLine();
		counter=Integer.parseInt(info)+1;
		reader.close();	
		BufferedWriter writer = new BufferedWriter(new FileWriter(z�hler,false));
		writer.write(String.valueOf(counter));
		writer.close();
		z�hler.setWritable(true,false);
	}

}
