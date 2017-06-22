package alkomat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CocktailZähler {
	File zähler = new File("./res/.zähler.alk");
	public void counter() throws IOException{
		String info;
		int counter;
		BufferedReader reader = new BufferedReader(new FileReader(zähler));
		info=reader.readLine();
		counter=Integer.parseInt(info)+1;
		reader.close();	
		BufferedWriter writer = new BufferedWriter(new FileWriter(zähler,false));
		writer.write(String.valueOf(counter));
		writer.close();
		zähler.setWritable(true,false);
	}

}
