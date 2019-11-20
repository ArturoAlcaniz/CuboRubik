import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class principal {
	
	public static int idnodos = 0;
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		
		//Comprueba que no haya fallos en los movimientos (tarea1)
		comprobacionEjemplo();
		//Comprueba que no haya fallos en movimientos de tarea2
		//comprobacionEjemplo2();

		EspacioEstados prob = new EspacioEstados("/Cubos/cube.json"); 
		
		ArrayList<NodoArbol> resultado = new ArrayList<NodoArbol>();
		resultado = new ArrayList<NodoArbol>();
		
		boolean poda = true;
		
		idnodos = 0;
		resultado = crearSolucion(Busqueda(prob, "estrella", 6, poda));
		imprimirSolucion(resultado, "estrella");		
		
		resultado = new ArrayList<NodoArbol>();

		idnodos = 0;
		resultado = crearSolucion(Busqueda(prob, "anchura", 6, poda));
		imprimirSolucion(resultado, "anchura");
		
		resultado = new ArrayList<NodoArbol>();
		
		idnodos = 0;
	  	resultado = crearSolucion(Busqueda(prob, "profundidad", 6, poda));
		imprimirSolucion(resultado, "profundidad");
		
		resultado = new ArrayList<NodoArbol>();
		
		idnodos = 0;
		resultado = crearSolucion(Busqueda(prob, "voraz", 6, poda));
		imprimirSolucion(resultado, "voraz");
		
		resultado = new ArrayList<NodoArbol>();
		
		idnodos = 0;
	  	resultado = crearSolucion(Busqueda(prob, "coste_uniforme", 6, poda));
		imprimirSolucion(resultado, "coste_uniforme");
		
		
		EspacioEstados prob2 = new EspacioEstados("/Cubos/cube4.json"); 
		
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
	
	public static NodoArbol crearPadre(String estrategia, String estado) {
		double v = 0.0;
		double h = OperacionesCubo.calculoDesorden(estado);
		if(estrategia.equals("estrella") || estrategia.equals("voraz")) {
			v = OperacionesCubo.calculoDesorden(estado);
		}else if(estrategia.equals("profundidad")) {
			v = 1.0;
		}
		NodoArbol n = new NodoArbol(estado, h, v, 0);
		return n;
	}
	
	public static void expandirNodo(EspacioEstados prob, Frontera f, NodoArbol a, String estrategia, int limiteProfundidad) {
		ArrayList<String[]> ListaSucesores = prob.Sucesores(a.getEstado());
		ArrayList<NodoArbol> ListaNodos = ListaNodos(ListaSucesores, a, estrategia, limiteProfundidad);
		f.getNodos().addAll(ListaNodos);
	}
	
	public static boolean visitadoCosteInferior(ArrayList<String> visitados, ArrayList<Integer> visitadosCoste, int coste, String md5) {
		boolean res = false;
		
		if(visitados.contains(md5)) {
			
			int i = visitados.indexOf(md5);
			int costevisitado = visitadosCoste.get(i);
			
			if(costevisitado<=coste) {
				
				res = true;
				
			}else {
				
				visitados.remove(i);
				visitadosCoste.remove(i);
				
			}
		}
		return res;
	}
	
	public static NodoArbol Busqueda(EspacioEstados prob, String estrategia, int limiteProfundidad, boolean poda) {	
		
		NodoArbol solucion = null;
		ArrayList<String> visitados = new ArrayList<String>();
		ArrayList<Integer> visitadosCoste = new ArrayList<Integer>();
		Frontera f = new Frontera();
		boolean sol = false;
		
		try {
			
			String cubo = LecturaJSON.leer(prob.getFile());
	
			NodoArbol padre = crearPadre(estrategia, cubo);
	
			f.insertar(padre); // La frontera inicial es el cubo del que partimos
			idnodos++;
			
			while (sol == false) {
	
				if(!f.estavacia()) {
					
					NodoArbol a = f.getNodos().poll();
					
					if(EsObjetivo(a)) {
						
						solucion = a;
						sol = true;
						
					}	
					
					String md5 = DigestUtils.md5Hex(a.getEstado());
						
					if(poda) {
						if(!visitadoCosteInferior(visitados, visitadosCoste, a.getcoste(), md5)) {
						
							visitados.add(md5);	
							visitadosCoste.add(a.getcoste());
							expandirNodo(prob, f, a, estrategia, limiteProfundidad);
						
						}
					}else {
						expandirNodo(prob, f, a, estrategia, limiteProfundidad);
					}
				}
				else {
					sol = true;
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
				
				DecimalFormatSymbols formatosimbolos = new DecimalFormatSymbols();
				formatosimbolos.setDecimalSeparator('.');
				DecimalFormat formato = new DecimalFormat("#.##", formatosimbolos);
				
				if(a.getPadre() == null) {
					System.out.printf("["+a.getid()+"][None]"+md5+",c="+a.getcoste()+",p="+a.getd()+",h="+formato.format(a.geth())+",f="+formato.format(a.getf())+"\n");
				}else {
					System.out.printf("["+a.getid()+"]["+a.getAccion()+"]"+md5+",c="+a.getcoste()+",p="+a.getd()+",h="+formato.format(a.geth())+",f="+formato.format(a.getf())+"\n");
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
	
	public static ArrayList<NodoArbol> ListaNodos(ArrayList<String[]> ListaSucesores, NodoArbol nodo_actual, String estrategia, int limiteProfundidad){
		
		ArrayList<NodoArbol> ListaNodos = new ArrayList<NodoArbol>();
		if((nodo_actual.getd()+1)<=limiteProfundidad) {
			double f = 0.0;
			for(int i=0; i<ListaSucesores.size(); i++) {
				String[] estado_sucesor = ListaSucesores.get(i);
					
				if(estrategia.equals("anchura") || estrategia.equals("coste_uniforme")) {
						
					f = nodo_actual.getd()+1;
						
				}else if(estrategia.equals("profundidad")) {

					f = 1.0/(nodo_actual.getd()+2);
					
				}else if(estrategia.equals("voraz")) {
					
					f = OperacionesCubo.calculoDesorden(estado_sucesor[0]);
				
				}else if(estrategia.equals("estrella")) {
					
					f = (nodo_actual.getd()+1) + OperacionesCubo.calculoDesorden(estado_sucesor[0]);
				}
					
				double h = OperacionesCubo.calculoDesorden(estado_sucesor[0]);

				NodoArbol n = new NodoArbol(nodo_actual, estado_sucesor[0], estado_sucesor[1], h, f, nodo_actual.getd()+1, (nodo_actual.getcoste()+1));
				n.setid(idnodos);
				ListaNodos.add(n);
				idnodos++;
				
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
