import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class principal {
	
	static int idnodos = 0;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		//Comprueba que no haya fallos en los movimientos (tarea1)
		comprobacionEjemplo();

		EspacioEstados prob = new EspacioEstados("/Cubos/cube.json"); 
		
		ArrayList<NodoArbol> resultado = new ArrayList<NodoArbol>();
		resultado = new ArrayList<NodoArbol>();
		
		boolean poda = true;
		
		resultado = crearSolucion(Buscar_Solucion(prob, "estrella", 6, poda, 1));
		imprimirSolucion(resultado, "estrella");		
		
		resultado = new ArrayList<NodoArbol>();

		resultado = crearSolucion(Buscar_Solucion(prob, "anchura", 6, poda, 1));
		imprimirSolucion(resultado, "anchura");
		
		resultado = new ArrayList<NodoArbol>();
		
	  	resultado = crearSolucion(Buscar_Solucion(prob, "profundidad_iterativa", 6, poda, 1));
		imprimirSolucion(resultado, "profundidad_iterativa");
		
		resultado = new ArrayList<NodoArbol>();
		
	  	resultado = crearSolucion(Buscar_Solucion(prob, "profundidad_acotada", 6, poda, 1));
		imprimirSolucion(resultado, "profundidad_acotada");
		
		resultado = new ArrayList<NodoArbol>();
		
		resultado = crearSolucion(Buscar_Solucion(prob, "voraz", 6, poda, 1));
		imprimirSolucion(resultado, "voraz");
		
		resultado = new ArrayList<NodoArbol>();
		
	  	resultado = crearSolucion(Buscar_Solucion(prob, "coste_uniforme", 6, poda, 1));
		imprimirSolucion(resultado, "coste_uniforme");
		
		
		long endTime = System.currentTimeMillis();
		System.out.println("\nTiempo transcurrido: "+ (endTime-startTime)/1000.0 +"s");

	}
	
	public static ArrayList<NodoArbol> crearSolucion(NodoArbol a){
		ArrayList<NodoArbol> solucion = new ArrayList<NodoArbol>();
		
		while(a.getPadre() != null) {
			
			solucion.add(0, a);
			a = a.getPadre();
	
		}
		solucion.add(0, a);
		
		return solucion;
	}
	
	public static boolean visitadoCosteInferior(ArrayList<String> visitados, ArrayList<Double> visitadosCoste, NodoArbol a) {
		boolean res = false;
		
		String md5 = DigestUtils.md5Hex(a.getEstado());
		
		double coste = a.getcoste();
		
		int i = visitados.indexOf(md5);
	
		if(i != -1) {
		
			double costevisitado = visitadosCoste.get(i);
		
			if(costevisitado<=coste) {
			
				res = true;
			
			}else {
			
				visitadosCoste.set(i, coste);
				
			}
			
		}else {
			
			i = visitados.size();
			visitados.add(i, md5);
			visitadosCoste.add(i, coste);
		
		}
		
		return res;
	}
	
	public static NodoArbol Buscar_Solucion(EspacioEstados prob, String estrategia, int limite, boolean poda, int inc_prof) {
		if(estrategia.equals("profundidad_iterativa")) {
			return Busqueda_iterativa(prob, estrategia, limite, poda, inc_prof);
				
		}else {
			return Busqueda(prob, estrategia, limite, poda);
		}
	}
	
	public static NodoArbol Busqueda_iterativa(EspacioEstados prob, String estrategia, int limite, boolean poda, int inc_prof) {
		NodoArbol solucion = null;
		int prof_actual = inc_prof;
		while(solucion == null && prof_actual <= limite) {
			solucion = Busqueda(prob, estrategia, prof_actual, poda);
			prof_actual = prof_actual + inc_prof;
		}
		return solucion;
	}
	
	public static NodoArbol Busqueda(EspacioEstados prob, String estrategia, int limiteProfundidad, boolean poda) {	
		
		NodoArbol solucion = null;
		
		idnodos = 0;
		
		ArrayList<String> visitados = new ArrayList<String>();
		ArrayList<Double> visitadosCoste = new ArrayList<Double>();
		
		Frontera f = new Frontera();
		
		NodoArbol padre = new NodoArbol(idnodos++, null, prob.getEstado_inicial(), null, OperacionesCubo.calculoDesorden(prob.getEstado_inicial()), 0, 0, 0);
		padre.setf(CrearValor(estrategia, padre));
		f.insertar(padre);
		
		while (solucion == null) {

			if(!f.estavacia()) {
				
				NodoArbol a = f.eliminar();
				
				if(EsObjetivo(a)) {
					
					solucion = a;
					
				}	
				
				ArrayList<String[]> ListaSucesores = prob.Sucesores(a.getEstado());
				ArrayList<NodoArbol> ListaNodos = ListaNodos(ListaSucesores, a, estrategia, limiteProfundidad);
				
				Iterator<NodoArbol> it = ListaNodos.iterator();
				
				while(it.hasNext()) {

					NodoArbol n = it.next();
					
					if(poda) {
					
						if(!visitadoCosteInferior(visitados, visitadosCoste, n)) {
					
							f.getNodos().add(n);
					
						}
				
					}else {
					
						f.getNodos().add(n);
				
					}
				}
			}
			else {
				break;
			}
		}
		return solucion;
		
	}
	
	public static void imprimirSolucion(ArrayList<NodoArbol> solucion, String estrategia) {	
		Iterator<NodoArbol> iter = solucion.iterator();
		System.out.println();
		
		if(estrategia.equals("anchura")) {
			System.out.println("Breadth (Anchura)");
		}else if(estrategia.equals("profundidad_acotada")){
			System.out.println("Depth (Profundidad acotada)");
		}else if(estrategia.equals("profundidad_iterativa")) {
			System.out.println("Depth (profundidad iterativa)");
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
				
				DecimalFormatSymbols formatosimbolos = new DecimalFormatSymbols();
				formatosimbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#.##", formatosimbolos);
				
				if(a.getPadre() == null) {
					System.out.printf("["+a.getid()+"][None]"+md5+",c="+a.getcoste()+",p="+a.getd()+",h="+formato.format(a.geth())+",v="+formato.format(a.getf())+"\n");
				}else {
					System.out.printf("["+a.getid()+"]["+a.getAccion()+"]"+md5+",c="+a.getcoste()+",p="+a.getd()+",h="+formato.format(a.geth())+",v="+formato.format(a.getf())+"\n");
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
	
	public static double CrearValor(String estrategia, NodoArbol a) {
		double valor = 0.0;
		
		if(estrategia.equals("anchura")){ 
			
			valor = a.getd();
				
		}else if(estrategia.equals("coste_uniforme")) {
			
			valor = a.getcoste();
			
		}else if(estrategia.equals("profundidad_acotada") || estrategia.equals("profundidad_iterativa")) {

			valor = 1.0/(a.getd()+1.0);
			
		}else if(estrategia.equals("voraz")) {
			
			valor = a.geth();
		
		}else if(estrategia.equals("estrella")) {
			
			valor = a.getd() + a.geth();
		}
		
		return valor;
	}
	
	public static ArrayList<NodoArbol> ListaNodos(ArrayList<String[]> ListaSucesores, NodoArbol nodo_actual, String estrategia, int limiteProfundidad){
		
		ArrayList<NodoArbol> ListaNodos = new ArrayList<NodoArbol>();
		
		if((nodo_actual.getd()+1)<=limiteProfundidad) {
			
			
			for(int i=0; i<ListaSucesores.size(); i++) {
				
				String[] estado_sucesor = ListaSucesores.get(i);
				
				double h = OperacionesCubo.calculoDesorden(estado_sucesor[0]);	
				double d = nodo_actual.getd()+1;
				double c = nodo_actual.getcoste()+1;
					
				NodoArbol n = new NodoArbol(idnodos++, nodo_actual, estado_sucesor[0], estado_sucesor[1], h, 0, d, c);
				n.setf(CrearValor(estrategia, n));
				ListaNodos.add(n);

				
			}
		}
		return ListaNodos;
	}
	
	
	
	public static boolean EsObjetivo(NodoArbol a) {
		
		if(a.geth() == 0) {
			return true;
		}else {
			return false;
		}		
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
