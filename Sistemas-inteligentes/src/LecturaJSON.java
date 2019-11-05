import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LecturaJSON {

	public static String leer(String file) throws FileNotFoundException, IOException, ParseException{
		
		JSONParser parser = new JSONParser();
		String[] lados = {"BACK","DOWN","FRONT","LEFT","RIGHT","UP"};
		Object[] devuelve = new Object[2];
		String str = "";
		
		File f = new File(".");
		
		Object obj = parser.parse(new FileReader(f.getCanonicalPath()+file));
		JSONObject jsonObject = (JSONObject) obj;
		
		for(int i=0;i<6;i++) {
			
			JSONArray contenido = (JSONArray) jsonObject.get(lados[i]);
			Iterator iter = contenido.iterator();
			
			while(iter.hasNext()) {
				
				String s = ""+iter.next();
				
				for(int j=0; j<s.length(); j++) {
					
					if(isNumeric(s.substring(j, j+1))) {
						str = str+s.substring(j, j+1);
					}
				}
			}
		}
		return str;
	}
	
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

}
