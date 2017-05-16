package alkomat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ZutatenListeLesen {
	
	ZutatenListeLesen() throws FileNotFoundException{
		
	}
	public List<String> leseZutatenListe() throws IOException{
	List<String> zutatenList = new ArrayList<String>();
	BufferedReader reader;
	File zutatenListe = new File("./ZutatenListe.txt");
	zutatenList.add("");
	reader=new BufferedReader(new FileReader(zutatenListe));
	String info;
	while((info = reader.readLine()) != null){
	zutatenList.add(info);	
	}
	reader.close();
	return zutatenList;
	};	

}
