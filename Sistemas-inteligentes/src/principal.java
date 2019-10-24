import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import java.util.ArrayList;
import java.util.Random;


public class principal {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException{
		long startTime = System.currentTimeMillis();
		Random rd = new Random();
		Frontera f = new Frontera();
		ArrayList<String> visitados = new ArrayList<String>();
		
		Object datosCubo[] = leerjson(3);
		
		String cubo = (String) datosCubo[0];
		
		System.out.println("Cubo Inicial");
		System.out.println(cubo);

		
		NodoArbol padre = new NodoArbol(cubo, rd.nextInt(100));
		f.insertar(padre); // La frontera inicial es el cubo del que partimos
		
		NodoArbol a = null;
		
		while (f.getNodos().size()>=1) {
			
			a = f.getNodos().get(0);
			
			if(EsObjetivo(a.getEstado())) {
				break;
			}else {
				
				if(calcularDepth(a)<10) {  //Control de profundidad
					
					visitados.add(a.getEstado());
					f.eliminar();
					GenerarFrontera(visitados, f, a);
				
				}else {
					
					f.eliminar();
				
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Nodos Visitados: "+visitados.size());
		
		if(EsObjetivo(a.getEstado())) {
			
			System.out.println("Objetivo encontrado");
			System.out.println(f.getNodos().get(0).getEstado());
			
		}else {
			
			System.out.println("Objetivo no encontrado");
			
		}
		
		System.out.println("\nTiempo transcurrido: "+ (endTime-startTime)/1000.0 +"s");

	}
	public static int calcularDepth(NodoArbol a) {
		int d = 0;
		while(a.getPadre() != null) {
			a = a.getPadre();
			d++;
		}
		return d;
	}
	
	public static Frontera GenerarFrontera(ArrayList<String> visitados, Frontera f, NodoArbol padre) {
		
		Random rd = new Random();
		String cubo = padre.getEstado();
		ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
		int N = (int) Math.sqrt(cubo.length()/6);
		String estado;
		String cubo2;
		NodoArbol a;
		
		for(int i = 0; i<N-1; i++) {
			inicializarcubomatriz(cubomatriz, cubo);
			GirarBack(cubomatriz, i);
			cubo2 = convertircubomatriz(cubomatriz);
			//falta md5 (ej: cubo2 = convertirMD5(cubo2))
			if(!visitados.contains(cubo2)) {
				a = new NodoArbol(padre, cubo2, "B"+i, rd.nextInt(100));
				f.insertar(a);
			}
				
			
			inicializarcubomatriz(cubomatriz, cubo);
			GirarBack2(cubomatriz, i);
			cubo2 = convertircubomatriz(cubomatriz);
			if(!visitados.contains(cubo2)) {
				a = new NodoArbol(padre, cubo2, "b"+i, rd.nextInt(100));
				f.insertar(a);
			}
			
			
			inicializarcubomatriz(cubomatriz, cubo);
			GirarDown(cubomatriz, i);
			cubo2 = convertircubomatriz(cubomatriz);
			if(!visitados.contains(cubo2)) {
				a = new NodoArbol(padre, cubo2, "D"+i, rd.nextInt(100));
				f.insertar(a);
			}
			
			
			inicializarcubomatriz(cubomatriz, cubo);
			GirarDown2(cubomatriz, i);
			cubo2 = convertircubomatriz(cubomatriz);
			if(!visitados.contains(cubo2)) {
				a = new NodoArbol(padre, cubo2, "d"+i, rd.nextInt(100));
				f.insertar(a);
			}
			
			
			inicializarcubomatriz(cubomatriz, cubo);
			GirarLeft(cubomatriz, i);
			cubo2 = convertircubomatriz(cubomatriz);
			if(!visitados.contains(cubo2)) {
				a = new NodoArbol(padre, cubo2, "L"+i, rd.nextInt(100));
				f.insertar(a);
			}
			
			
			inicializarcubomatriz(cubomatriz, cubo);
			GirarLeft2(cubomatriz, i);
			cubo2 = convertircubomatriz(cubomatriz);
			if(!visitados.contains(cubo2)) {
				a = new NodoArbol(padre, cubo2, "l"+i, rd.nextInt(100));
				f.insertar(a);
			}
			
				
		}
		
		return f;
		
	}
	
	public static boolean EsObjetivo(String estado) {
		
		ArrayList<String[][]> cubo = new ArrayList<String[][]>();
		inicializarcubomatriz(cubo, estado);
		
		boolean comprobar = true;
		int ca = 0;
		int i = 0;
		int j = 0;
		
		while(comprobar == true && ca<6) {
			
			i = 0;
			
			String[][] cara = cubo.get(ca);
			
			String color = cara[0][0];
			
			while(comprobar == true && i<cara.length) {
				
				j=0;
				
				while(comprobar == true && j<cara[i].length) {
			
					if(Integer.parseInt(cara[i][j]) != Integer.parseInt(color)) {
						comprobar = false;
						
					}
					
					j++;
				}
				i++;
			}
			ca++;
		}
		
		return comprobar;
		
	}
	
	public static String convertircubomatriz(ArrayList<String[][]> cubomatriz) {
		
		String cubo = "";
		
		for(int ca = 0; ca<6; ca++) {
			
			String[][] cara = cubomatriz.get(ca);
			
			for(int i=0; i<cara.length; i++) {
				
				for(int j=0; j<cara[i].length; j++) {
					
					cubo = cubo+cara[i][j];
					
				}
			}
			
		}
		
		return cubo;
		
	}
	
	public static void inicializarcubomatriz(ArrayList<String[][]> cubomatriz, String cubo) {
		
		int N = (int)Math.sqrt(cubo.length()/6);
		int posicion = 0;

		for(int ca = 0; ca<6; ca++) {
			
			String[][] cara = new String[N][N];
			
			for(int i=0; i<N; i++) {
				
				for(int j=0; j<N; j++) {
					
					cara[i][j] = ""+cubo.charAt(posicion);
					posicion++;
					
				}
			}
			
			cubomatriz.add(cara);
			
		}
	}
	
	public static void GirarBack(ArrayList<String[][]> cubo, int movimiento) {
		
		String[][] caraback = cubo.get(0);
		String[][] caradown = cubo.get(1);
		String[][] carafront = cubo.get(2);
		String[][] caraleft = cubo.get(3);
		String[][] cararight = cubo.get(4);
		String[][] caraup = cubo.get(5);
		
		int N = caraback.length;
		
		String Aux1 = ""; 
		String Aux2 = "";
		
		if(movimiento == 0) { // Se mueve Back
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = caraback[i][N-1];
				caraback[i][N-1] = caraback[0][i];
				
				Aux2 = caraback[N-1][N-1-i];
				caraback[N-1][N-1-i] = Aux1;
				
				Aux1 = caraback[N-1-i][0];
				caraback[N-1-i][0] = Aux2;
				
				caraback[0][i] = Aux1;				
			}
			
		}else if(movimiento == N-1) { // Se mueve Front
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = carafront[i][N-1];
				carafront[i][N-1] = carafront[0][i];
				
				Aux2 = carafront[N-1][N-1-i];
				carafront[N-1][N-1-i] = Aux1;
				
				Aux1 = carafront[N-i][0];
				carafront[N-1-i][0] = Aux2;
				
				carafront[0][i] = Aux1;				
			}
			
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = cararight[movimiento][i];
			cararight[movimiento][i] = caradown[movimiento][i];
			
			Aux2 = caraup[movimiento][i];
			caraup[movimiento][i] = Aux1;
			
			Aux1 = caraleft[movimiento][i];
			caraleft[movimiento][i] = Aux2;
			
			caradown[movimiento][i] = Aux1;
			
		}
		
	}
	
	
	public static void GirarBack2(ArrayList<String[][]> cubo, int movimiento) {
		
		String[][] caraback = cubo.get(0);
		String[][] caradown = cubo.get(1);
		String[][] carafront = cubo.get(2);
		String[][] caraleft = cubo.get(3);
		String[][] cararight = cubo.get(4);
		String[][] caraup = cubo.get(5);
	
		int N = caraback.length;
		
		String Aux1 = ""; 
		String Aux2 = "";
		
		if(movimiento == 0) { // Se mueve Back
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = caraback[0][i];
				caraback[0][i] = caraback[i][N-1];
				
				Aux2 = caraback[N-1-i][0];
				caraback[N-1-i][0] = Aux1;
				
				Aux1 = caraback[N-1][N-1-i];
				caraback[N-1][N-1-i] = Aux2;
				
				caraback[i][N-1] = Aux1;
							
			}
			
		}else if(movimiento == N-1) { // Se mueve Front
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = carafront[0][i];
				carafront[0][i] = carafront[i][N-1];
				
				Aux2 = carafront[N-1-i][0];
				carafront[N-1-i][0] = Aux1;
				
				Aux1 = carafront[N-1][N-1-i];
				carafront[N-1][N-1-i] = Aux2;
				
				carafront[i][N-1] = Aux1;
				
			}
			
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = caradown[movimiento][i];
			caradown[movimiento][i] = cararight[movimiento][i];
			
			Aux2 = caraleft[movimiento][i];
			caraleft[movimiento][i] = Aux1;
			
			Aux1 = caraup[movimiento][i];
			caraup[movimiento][i] = Aux2;
			
			cararight[movimiento][i] = Aux1;
			
		}
		
		
	}
	
	
	public static void GirarDown(ArrayList<String[][]> cubo, int movimiento) {
		
		String[][] caraback = cubo.get(0);
		String[][] caradown = cubo.get(1);
		String[][] carafront = cubo.get(2);
		String[][] caraleft = cubo.get(3);
		String[][] cararight = cubo.get(4);
		String[][] caraup = cubo.get(5);
		
		int N = caraback.length;
		
		String Aux1 = ""; 
		String Aux2 = "";
		
		if(movimiento == 0) { // Se mueve down
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = caradown[i][N-1];
				caradown[i][N-1] = caradown[0][i];
				
				Aux2 = caradown[N-1][N-1-i];
				caradown[N-1][N-1-i] = Aux1;
				
				Aux1 = caradown[N-1-i][0];
				caradown[N-1-i][0] = Aux2;
				
				caradown[0][i] = Aux1;				
			}
			
		}else if(movimiento == N-1) { // Se mueve up
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = caraup[i][N-1];
				caraup[i][N-1] = caraup[0][i];
				
				Aux2 = caraup[N-1][N-i];
				caraup[N-1][N-1-i] = Aux1;
				
				Aux1 = caraup[N-1-i][0];
				caraup[N-1-i][0] = Aux2;
				
				caraup[0][i] = Aux1;				
			}
			
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = caraback[N-1-movimiento][N-1-i];
			caraback[N-1-movimiento][N-1-i] = caraleft[i][N-1-movimiento];
			
			Aux2 = cararight[movimiento][N-1-i];
			cararight[movimiento][N-1-i] = Aux1;
			
			Aux1 = carafront[movimiento][i];
			carafront[movimiento][i] = Aux2;
			
			caraleft[i][N-1-movimiento] = Aux1;
			
		}
		
	}
	
	
	public static void GirarDown2(ArrayList<String[][]> cubo, int movimiento) {
		
		String[][] caraback = cubo.get(0);
		String[][] caradown = cubo.get(1);
		String[][] carafront = cubo.get(2);
		String[][] caraleft = cubo.get(3);
		String[][] cararight = cubo.get(4);
		String[][] caraup = cubo.get(5);
		
		int N = caraback.length;
		
		String Aux1 = ""; 
		String Aux2 = "";
		
		if(movimiento == 0) { // Se mueve down
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = caradown[0][i];
				caradown[0][i] = caradown[i][N-1];
				
				Aux2 = caradown[N-1-i][0];
				caradown[N-1-i][0] = Aux1;
				
				Aux1 = caradown[N-1][N-1-i];
				caradown[N-1][N-1-i] = Aux2;
				
				caradown[i][N-1] = Aux1;
						
			}
			
		}else if(movimiento == N-1) { // Se mueve up
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = caraup[0][i];
				caraup[0][i] = caraup[i][N-1];
				
				Aux2 = caraup[N-1-i][0];
				caraup[N-1-i][0] = Aux1;
				
				Aux1 = caraup[N-1][N-i];
				caraup[N-1][N-1-i] = Aux2;
				
				caraup[i][N-1] = Aux1;
								
			}
			
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = caraleft[i][N-1-movimiento];
			caraleft[i][N-1-movimiento] = caraback[N-1-movimiento][N-1-i];
			
			Aux2 = carafront[movimiento][i];
			carafront[movimiento][i] = Aux1;
			
			Aux1 = cararight[movimiento][N-1-i];
			cararight[movimiento][N-1-i] = Aux2;
			
			caraback[N-1-movimiento][N-1-i] = Aux1;
			
		}
		
		
	}
	
	
	public static void GirarLeft(ArrayList<String[][]> cubo, int movimiento) {
		
		String[][] caraback = cubo.get(0);
		String[][] caradown = cubo.get(1);
		String[][] carafront = cubo.get(2);
		String[][] caraleft = cubo.get(3);
		String[][] cararight = cubo.get(4);
		String[][] caraup = cubo.get(5);
		
		int N = caraback.length;
		
		String Aux1 = ""; 
		String Aux2 = "";
		
		if(movimiento == 0) { // Se mueve left
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = caraleft[i][N-1];
				caraleft[i][N-1] = caraleft[0][i];
				
				Aux2 = caraleft[N-1][N-1-i];
				caraleft[N-1][N-1-i] = Aux1;
				
				Aux1 = caraleft[N-1-i][0];
				caraleft[N-1-i][0] = Aux2;
				
				caraleft[0][i] = Aux1;				
			}
			
		}else if(movimiento == N-1) { // Se mueve right
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = cararight[i][N-1];
				cararight[i][N-1] = cararight[0][i];
				
				Aux2 = cararight[N-1][N-i];
				cararight[N-1][N-1-i] = Aux1;
				
				Aux1 = cararight[N-1-i][0];
				cararight[N-1-i][0] = Aux2;
				
				cararight[0][i] = Aux1;				
			}
			
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = caraback[i][movimiento];
			caraback[i][movimiento] = caradown[i][movimiento];
			
			Aux2 = caraup[N-1-movimiento][N-1-i];
			caraup[N-1-movimiento][N-1-i] = Aux1;
			
			Aux1 = carafront[i][movimiento];
			carafront[i][movimiento] = Aux2;
			
			caradown[i][movimiento] = Aux1;
			
		}
		
	}
	
	public static void GirarLeft2(ArrayList<String[][]> cubo, int movimiento) {
		
		String[][] caraback = cubo.get(0);
		String[][] caradown = cubo.get(1);
		String[][] carafront = cubo.get(2);
		String[][] caraleft = cubo.get(3);
		String[][] cararight = cubo.get(4);
		String[][] caraup = cubo.get(5);
		
		int N = caraback.length;
		
		String Aux1 = ""; 
		String Aux2 = "";
		
		if(movimiento == 0) { // Se mueve left
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = caraleft[0][i];
				caraleft[0][i] = caraleft[i][N-1];
				
				Aux2 = caraleft[N-1-i][0];
				caraleft[N-1-i][0] = Aux1;
				
				Aux1 = caraleft[N-1][N-1-i];
				caraleft[N-1][N-1-i] = Aux2;
				
				caraleft[i][N-1] = Aux1;
				
			}
			
		}else if(movimiento == N-1) { // Se mueve right
			
			for(int i=0; i<N-1; i++) {
				Aux1 = cararight[0][i];
				cararight[0][i] = cararight[i][N-1];
				
				Aux2 = cararight[N-1-i][0];
				cararight[N-1-i][0] = Aux1;
				
				Aux1 = cararight[N-1][N-i];
				cararight[N-1][N-1-i] = Aux2;
				
				cararight[i][N-1] = Aux1;		
			}
			
		}
		
		for(int i=0; i<N; i++) {
			Aux1 = caradown[i][movimiento];
			caradown[i][movimiento] = caraback[i][movimiento];
			
			Aux2 = carafront[i][movimiento];
			carafront[i][movimiento] = Aux1;
			
			Aux1 = caraup[N-1-movimiento][N-1-i];
			caraup[N-1-movimiento][N-1-i] = Aux2;
			
			caraback[i][movimiento] = Aux1;
			
		}
		
	}
	
	public static Object[] leerjson(int n) throws FileNotFoundException, IOException, ParseException{
		File f = new File(".");
		String str = "";
		String[] lados = {"BACK","DOWN","FRONT","LEFT","RIGHT","UP"};
		Object[] devuelve = new Object[2];
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(f.getCanonicalPath()+"/Cubos/cube.json"));
		JSONObject jsonObject = (JSONObject) obj;
		for(int i=0;i<6;i++) {
			JSONArray contenido = (JSONArray) jsonObject.get(lados[i]);
			for(Object a : contenido) {
				String[] ad=a.toString().split(",|\\[|\\]");
				for(int j=1;j<n+1;j++) 
				str = str + ad[n].toString();
			
			}
		}
		//Encripta la cedana en string MD5
		String md5 = DigestUtils.md5Hex(str);
		devuelve[0] = str;
		devuelve[1] = md5; 
		return devuelve;
		
	}
}
