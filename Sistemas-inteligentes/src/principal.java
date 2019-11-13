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
		
		ArrayList<ArrayList> resultado = new ArrayList<ArrayList>();
		ArrayList<ArrayList> resultado2 = new ArrayList<ArrayList>();
		ArrayList<ArrayList> resultado3 = new ArrayList<ArrayList>();
		
		resultado = Busqueda(prob, "anchura", 6);
		resultado2 = Busqueda(prob, "profundidad", 6);
		resultado3 = Busqueda(prob, "coste_uniforme", 6);
		
		imprimirSolucion(resultado);
		imprimirSolucion(resultado2);
		imprimirSolucion(resultado3);
		
		long endTime = System.currentTimeMillis();
		System.out.println("\nTiempo transcurrido: "+ (endTime-startTime)/1000.0 +"s");

	}
	
	public static ArrayList<ArrayList> Busqueda(EspacioEstados prob, String estrategia, int limiteProfundidad) {	
		
		ArrayList<NodoArbol> solucion = new ArrayList<NodoArbol>();
		ArrayList<String> visitados = new ArrayList<String>();
		ArrayList<String> ids = new ArrayList<String>();
		
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
					
					visitados.add(DigestUtils.md5Hex(a.getEstado()));
					
					while(a.getPadre() != null) {import java.io.File;
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
		//Comprueba que no haya fallos en movimientos de tarea2
		comprobacionEjemplo2();
		

		EspacioEstados prob = new EspacioEstados("/Cubos/cube.json"); 
		
		ArrayList<NodoArbol> resultado = new ArrayList<NodoArbol>();
		resultado = new ArrayList<NodoArbol>();
		
		resultado = Busqueda(prob, "estrella", 6);
		imprimirSolucion(resultado, "estrella");		
		
		resultado = new ArrayList<NodoArbol>();
		
		resultado = Busqueda(prob, "anchura", 6);
		imprimirSolucion(resultado, "anchura");
		
		resultado = new ArrayList<NodoArbol>();
		
	  	resultado = Busqueda(prob, "profundidad", 6);
		imprimirSolucion(resultado, "profundidad");
		
		resultado = new ArrayList<NodoArbol>();
		
	  	resultado = Busqueda(prob, "voraz", 6);
		imprimirSolucion(resultado, "voraz");
		
		resultado = new ArrayList<NodoArbol>();
		
	  	resultado = Busqueda(prob, "coste_uniforme", 6);
		imprimirSolucion(resultado, "coste_uniforme");
		
		/*
		EspacioEstados prob2 = new EspacioEstados("/Cubos/cube4.json"); 
		
		resultado = new ArrayList<NodoArbol>();
		
		resultado = Busqueda(prob2, "estrella", 6);
		imprimirSolucion(resultado, "estrella");	
		
		resultado = new ArrayList<NodoArbol>();
		
	  	resultado = Busqueda(prob2, "voraz", 6);
		imprimirSolucion(resultado, "voraz");*/
		
		long endTime = System.currentTimeMillis();
		System.out.println("\nTiempo transcurrido: "+ (endTime-startTime)/1000.0 +"s");

	}
	
	public static ArrayList<NodoArbol> Busqueda(EspacioEstados prob, String estrategia, int limiteProfundidad) {	
		
		ArrayList<NodoArbol> solucion = new ArrayList<NodoArbol>();
		ArrayList<String> visitados = new ArrayList<String>();
		ArrayList<ArrayList<String>> v = new ArrayList<ArrayList<String>>();
		for(int i=0; i<limiteProfundidad; i++) {
				ArrayList<String> v1 = new ArrayList<String>();
				v.add(i, v1);
		}
		Frontera f = new Frontera();
		int n = 0;
		int d2 = 0;
		
		boolean sol = false;
		
		
		try {
			
			String cubo = LecturaJSON.leer(prob.getFile());
			int N = (int) Math.sqrt(cubo.length()/6);
			NodoArbol padre = null;
			double dou = 0.0;
			if(estrategia.equals("estrella")) {
				dou = OperacionesCubo.calculoDesorden(cubo);
			}else if(estrategia.equals("profundidad")) {
				dou = 1.0;
			}else if(estrategia.equals("voraz")) {
				dou = OperacionesCubo.calculoDesorden(cubo);
			}
			Double he = OperacionesCubo.calculoDesorden(cubo);
			int h = he.intValue();
			padre = new NodoArbol(cubo, h, dou, 0);
			
			
			f.insertar(padre); // La frontera inicial es el cubo del que partimos
			
			while (sol == false && !f.estavacia()) {
				
				NodoArbol a = f.getNodos().remove();
				
				if(EsObjetivo(a.getEstado())) {
					
					visitados.add(DigestUtils.md5Hex(a.getEstado()));
					
					while(a.getPadre() != null) {
						
						solucion.add(0, a);
						a = a.getPadre();
				
					}
					solucion.add(0, a);
					sol = true;
					break;
					
				}else{
					
					
					if(a.getd()<limiteProfundidad) {
						v.get(a.getd()).add(DigestUtils.md5Hex(a.getEstado()));
						
						ArrayList<String[]> sucesores = prob.Sucesores(a.getEstado());
						ArrayList<NodoArbol> ListaNodos = ListaNodos(limiteProfundidad, v, visitados, sucesores, a, estrategia);
						for(int i=0; i<ListaNodos.size(); i++) {
							NodoArbol s = ListaNodos.get(i);
							n++;
							s.setid(n);
							f.insertar(s);
						}
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
	
	public static void imprimirSolucion(ArrayList<NodoArbol> solucion, String estrategia) {	
		Iterator<NodoArbol> iter = solucion.iterator();
		System.out.println();
		
		if(estrategia.equals("anchura")) {
			System.out.println("Breadth (Anchura)");
		}else if(estrategia.equals("profundidad")){
			System.out.println("Depth (Profundidad)");
		}else if(estrategia.equals("coste_uniforme")) {
			System.out.println("Uniform (Costo Uniforme)");
		}else if(estrategia.equals("estrella")) {
			System.out.println("A");
		}else if(estrategia.equals("voraz")) {
			System.out.println("Greedy (Voraz)");
		}
		
		System.out.println("=================");
		
		if(!solucion.isEmpty()) {
			while(iter.hasNext()) {
				NodoArbol a = iter.next();
				String md5 = DigestUtils.md5Hex(a.getEstado());
				if(a.getPadre() == null) {
					System.out.printf("["+a.getid()+"][None]"+md5+",c="+a.getd()+",p="+a.getd()+",h="+a.geth()+",f=%.2f\n", a.getf());
				}else {
					System.out.printf("["+a.getid()+"]["+a.getAccion()+"]"+md5+",c="+a.getd()+",p="+a.getd()+",h="+a.geth()+",f=%.2f\n", a.getf());
				}
			}
			
		}else {
			System.out.println("Solucion no encontrada");
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
	public static void comprobacionEjemplo2() {
		
		try {
			String cubo = LecturaJSON.leer("/Cubos/cube.json");
			System.out.println("Cubo Inicial");
			System.out.println(cubo);
			String md5 = DigestUtils.md5Hex(cubo);
			System.out.println(md5);
			
			ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
			OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			
			System.out.println("Accion b0:");
			OperacionesCubo.GirarBack2(cubomatriz, 0);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion D0:");
			OperacionesCubo.GirarDown(cubomatriz, 0);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion d1:");
			OperacionesCubo.GirarDown2(cubomatriz, 1);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion B0:");
			OperacionesCubo.GirarBack(cubomatriz, 0);
			imprimirMD5(cubomatriz);
			
			System.out.println("Accion B0:");
			OperacionesCubo.GirarBack(cubomatriz, 0);
			imprimirMD5(cubomatriz);
			cubo = OperacionesCubo.convertircubomatriz(cubomatriz);
			System.out.println(cubo);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<NodoArbol> ListaNodos(int limite, ArrayList<ArrayList<String>> v, ArrayList<String> visitados, ArrayList<String[]> ListaSucesores, NodoArbol nodo_actual, String estrategia){
		
		ArrayList<NodoArbol> ListaNodos = new ArrayList<NodoArbol>();
		Random rd = new Random();
		String cubo = ListaSucesores.get(0)[0];
		int N = (int) Math.sqrt(cubo.length()/6);
		double f = 0;
		
		visitados.clear();
		if(nodo_actual.getd()+1 >= v.size()) {
			for(int i=0; i<=nodo_actual.getd(); i++) {
				visitados.addAll(v.get(i));
			}
		}else {
			for(int i=0; i<=nodo_actual.getd()+1; i++) {
				visitados.addAll(v.get(i));
			}
		}
		
		
		for(int i=0; i<ListaSucesores.size(); i++) {
			cubo = ListaSucesores.get(i)[0];
			String[] estado_sucesor = ListaSucesores.get(i);
			double aleatorio = rd.nextDouble();
				
			if(estrategia.equals("anchura") || estrategia.equals("coste_uniforme")) {
					
				f = nodo_actual.getd()+1;
					
			}else if(estrategia.equals("profundidad")) {

				f = 1.0/(nodo_actual.getd()+2);
				
			}else if(estrategia.equals("voraz")) {
				
				f = OperacionesCubo.calculoDesorden(estado_sucesor[0]);
			
			}else if(estrategia.equals("estrella")) {
				
				f = (nodo_actual.getd()+1) + OperacionesCubo.calculoDesorden(estado_sucesor[0]);
			}
				
			Double he = OperacionesCubo.calculoDesorden(estado_sucesor[0]);
			int h = he.intValue();
				
			NodoArbol n = new NodoArbol(nodo_actual, estado_sucesor[0], estado_sucesor[1], h, f, nodo_actual.getd()+1);
			String md5 = DigestUtils.md5Hex(n.getEstado());
			boolean visitado = visitados.contains(md5);
			if(!visitado) {
				ListaNodos.add(n);
			}			
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
						
						ids.add(0, ""+visitados.indexOf(DigestUtils.md5Hex(a.getEstado())));
						solucion.add(0, a);
						a = a.getPadre();
				
					}
					
					ids.add(0, ""+visitados.indexOf(DigestUtils.md5Hex(a.getEstado())));
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
		
		ArrayList<ArrayList> SOL = new ArrayList<ArrayList>();
		SOL.add(solucion);
		SOL.add(0, ids);
		return SOL;
		
		
	}
	
	public static void imprimirSolucion(ArrayList<ArrayList> solucion) {
		if(solucion.get(1).isEmpty()) {
			System.out.println("Solucion no encontrada");
		}else {
			System.out.println("Solucion encontrada");
	
			Iterator<NodoArbol> iter = solucion.get(1).iterator();
			ArrayList<String> ids = solucion.get(0);
			int n=-1;
			while(iter.hasNext()) {
				n++;
				NodoArbol a = iter.next();
				String md5 = DigestUtils.md5Hex(a.getEstado());
				if(a.getPadre() == null) {
					System.out.println("["+ids.get(n)+"][None]"+md5+",c="+a.getd()+",p="+a.getd()+",f="+a.getf());
				}else {
					System.out.println("["+ids.get(n)+"]["+a.getAccion()+"]"+md5+",c="+a.getd()+",p="+a.getd()+",f="+a.getf());
				}
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
