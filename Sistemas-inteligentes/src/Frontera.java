import java.util.ArrayList;
import java.util.Iterator;

public class Frontera {

	ArrayList<NodoArbol> nodos = new ArrayList<NodoArbol>(); 
	
	public void insertar(NodoArbol nodo) {
		int posicion = obtenerPosicionInsercion(nodo.getf());
		if(posicion>(nodos.size()-1)) {
			nodos.add(nodo);
		}
		else {
			nodos.add(posicion, nodo);
		}
	}
	
	public int obtenerPosicionInsercion(int f) {
		int posicion = 0;
		int aux = 0;
		if(estavacia()) {
			posicion = 0;
		}else {
			
			Iterator<NodoArbol> iter = nodos.iterator();
			
			while(iter.hasNext()) {
				aux = iter.next().getf();
				if(f<aux) {
					break;
				}
				posicion++;
			}
		}
		return posicion;
	}
	
	public void eliminar() {
		nodos.remove(0);
	}
	
	public boolean estavacia() {
		if(nodos == null || nodos.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		Iterator<NodoArbol> iter = nodos.iterator();
		int posicion = 0;
		String texto = "";
		while(iter.hasNext()) {
			NodoArbol a = iter.next();
			texto = texto+"Nodo frontera en posicion: "+posicion+", estado: "+a.getEstado()+", accion: "+a.getAccion()+" y con f: "+a.getf();
			texto = texto+"\n";
			posicion++;
		}
		return texto;
	}

}
