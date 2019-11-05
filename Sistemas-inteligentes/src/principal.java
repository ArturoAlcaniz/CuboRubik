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
import java.util.Iterator;
import java.util.Random;



public class principal {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		//Comprueba que no haya fallos en los movimientos (tarea1)
		comprobacionEjemplo();
		
		EspacioEstados prob = new EspacioEstados("/Cubos/cube.json"); 
		
		ArrayList<NodoArbol> resultado = new ArrayList<NodoArbol>();
		
		resultado = Busqueda(prob, "anchura", 6);
		
		if(!resultado.isEmpty()) {
			System.out.println("Solucion encontrada");
			imprimirSolucion(resultado);
			
		}else {
			System.out.println("Solucion no encontrada");
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("\nTiempo transcurrido: "+ (endTime-startTime)/1000.0 +"s");

	}
	
	public static ArrayList<NodoArbol> Busqueda(EspacioEstados prob, String estrategia, int limiteProfundidad) {	
		
		ArrayList<NodoArbol> solucion = new ArrayList<NodoArbol>();
		ArrayList<String> visitados = new ArrayList<String>();
		
		Frontera f = new Frontera();
		
		Random rd = new Random();
		
		boolean sol = false;
		
		NodoArbol a = null;
		
		int n=0;
		
		try {
			
			String cubo = LecturaJSON.leer(prob.getFile());
			
			NodoArbol padre = new NodoArbol(cubo, 0, 0);
			
			f.insertar(padre); // La frontera inicial es el cubo del que partimos
			
			while (sol == false && !f.estavacia()) {
				
				a = f.getNodos().get(0);
				
				if(EsObjetivo(a.getEstado())) {
					
					while(a.getPadre() != null) {
						
						solucion.add(0, a);
						a = a.getPadre();
				
					}
					solucion.add(0, a);
					
					sol = true;
					break;
					
				}else {
					
					if(a.getd()<limiteProfundidad) {  //Control de profundidad
						
						ArrayList<String[]> sucesores = prob.Sucesores(a.getEstado());
						
						ArrayList<NodoArbol> ListaNodos = ListaNodos(sucesores, a, estrategia);
						
						visitados.add(DigestUtils.md5Hex(a.getEstado()));
						f.eliminar();
						
						for(int i=0; i<ListaNodos.size(); i++) {
							
							String md5sucesor = DigestUtils.md5Hex(ListaNodos.get(i).getEstado());
							
							
							if(!visitados.contains(md5sucesor)) {
								System.out.println(ListaNodos.get(i).getAccion());
								NodoArbol s = ListaNodos.get(i);
								NodoArbol nodofrontera = new NodoArbol(s.getPadre(), s.getEstado(), s.getAccion(), s.getf(), s.getd());
								
								f.insertar(nodofrontera);
							}
						}
						
						
						
					}else {
					
						f.eliminar();
					}

				}
			}
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return solucion;
		
		
	}
	
	public static void imprimirSolucion(ArrayList<NodoArbol> solucion) {
		Iterator<NodoArbol> iter = solucion.iterator();
		while(iter.hasNext()) {
			NodoArbol a = iter.next();
			String md5 = DigestUtils.md5Hex(a.getEstado());
			if(a.getPadre() == null) {
				System.out.println("[None]"+md5+",c="+a.getd()+",p="+a.getd()+",f="+a.getf());
			}else {
				System.out.println("["+a.getAccion()+"]"+md5+",c="+a.getd()+",p="+a.getd()+",f="+a.getf());
			}
		}
		
	}
	
	public static void imprimirMD5(ArrayList<String[][]> cubomatriz) {
		String cubo = OperacionesCubo.convertircubomatriz(cubomatriz);
		String md5 = DigestUtils.md5Hex(cubo);
		System.out.println(md5);
	}
	
	public static void comprobacionEjemplo() {
		
		try {
			String cubo = LecturaJSON.leer("/Cubos/cube3.json");
			System.out.println("Cubo Inicial");
			System.out.println(cubo);
			String md5 = DigestUtils.md5Hex(cubo);
			System.out.println(md5);
			
			ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
			OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			
			System.out.println("Accion l3:");
			OperacionesCubo.GirarLeft2(cubomatriz, 3);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion D1:");
			OperacionesCubo.GirarDown(cubomatriz, 1);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion l1:");
			OperacionesCubo.GirarLeft2(cubomatriz, 1);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion d0:");
			OperacionesCubo.GirarDown2(cubomatriz, 0);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion B0:");
			OperacionesCubo.GirarBack(cubomatriz, 0);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion b5:");
			OperacionesCubo.GirarBack2(cubomatriz, 5);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion l2:");
			OperacionesCubo.GirarLeft2(cubomatriz, 2);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion d1:");
			OperacionesCubo.GirarDown2(cubomatriz, 1);
			imprimirMD5(cubomatriz);
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public static int calcularDepth(NodoArbol a) {
		int d = 0;
		while(a.getPadre() != null) {
			a = a.getPadre();
			d++;
		}
		return d;
	}
	
	public static ArrayList<NodoArbol> ListaNodos(ArrayList<String[]> ListaSucesores, NodoArbol nodo_actual, String estrategia){
		
		ArrayList<NodoArbol> ListaNodos = new ArrayList<NodoArbol>();
		Random rd = new Random();
		int f = 0;
		
		for(int i=0; i<ListaSucesores.size(); i++) {
			
			String[] estado_sucesor = ListaSucesores.get(i);

			int Aleatorio = rd.nextInt(100);
				
			if(estrategia.equals("anchura") || estrategia.equals("coste_uniforme")) {
					
				f = nodo_actual.getd()+1;
					
			}else if(estrategia.equals("profundidad")) {
					
				f = 1/(nodo_actual.getd()+2);
					
			}
				
			NodoArbol a = new NodoArbol(nodo_actual, estado_sucesor[0], estado_sucesor[1], f, nodo_actual.getd()+1);
			ListaNodos.add(a);
				
		}
		
		return ListaNodos;
		
	}
	
	
	
	public static boolean EsObjetivo(String estado) {
		
		ArrayList<String[][]> cubo = new ArrayList<String[][]>();
		OperacionesCubo.inicializarcubomatriz(cubo, estado);
		
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
	
	
	public static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	

}
