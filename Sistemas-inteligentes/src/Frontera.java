import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Comparator;

public class Frontera {

	PriorityQueue<NodoArbol> nodos = new PriorityQueue<NodoArbol>();
	
	public void insertar(NodoArbol nodo) {
		nodos.add(nodo);
	}
	
	public PriorityQueue<NodoArbol> getNodos(){
		return nodos;
	}
	public void eliminar() {
		nodos.remove();
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
			texto = texto+"Nodo frontera en posicion: "+posicion+", estado: "+a.getEstado()+", md5:"+DigestUtils.md5Hex(a.getEstado())+", accion: "+a.getAccion()+", profundidad:"+a.getd()+" y con f: "+a.getf();
			texto = texto+"\n";
			posicion++;
		}
		return texto;
	}
	

}
