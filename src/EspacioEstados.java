import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;


public class EspacioEstados {
	
	String file = new String();
	String estado_inicial = new String();
	
	public EspacioEstados(String file) {
		this.file = file;
		try {
			this.estado_inicial = LecturaJSON.leer(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String[]> Sucesores(String cubo) {
		
		ArrayList<String[]> ListaSucesores = new ArrayList<String[]>();		
				
		int N = (int) Math.sqrt(cubo.length()/6);
		
		for(int i = 0; i<N; i++) {
			
			ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarBack(cubomatriz, i);
			String[] sucesor = {OperacionesCubo.convertircubomatriz(cubomatriz), "B"+i};
			ListaSucesores.add(sucesor);
			
		}
		
		for (int i = 0; i<N; i++) {

			ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarBack2(cubomatriz, i);
			String[] sucesor = {OperacionesCubo.convertircubomatriz(cubomatriz), "b"+i};
			ListaSucesores.add(sucesor);
			
		}
		for(int i = 0; i<N; i++) {
			
			ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarDown(cubomatriz, i);
			String[] sucesor = {OperacionesCubo.convertircubomatriz(cubomatriz), "D"+i};
			ListaSucesores.add(sucesor);
			
		}
		for(int i = 0; i<N; i++) {
			
			ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarDown2(cubomatriz, i);
			String[] sucesor = {OperacionesCubo.convertircubomatriz(cubomatriz), "d"+i};
			ListaSucesores.add(sucesor);
			
		}
		for(int i = 0; i<N; i++) {
			
			ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarLeft(cubomatriz, i);
			String[] sucesor = {OperacionesCubo.convertircubomatriz(cubomatriz), "L"+i};
			ListaSucesores.add(sucesor);
			
		}
		for(int i = 0; i<N; i++) {
			
			ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarLeft2(cubomatriz, i);
			String[] sucesor = {OperacionesCubo.convertircubomatriz(cubomatriz), "l"+i};
			ListaSucesores.add(sucesor);
			
				
		}
		
		return ListaSucesores;
		
	}
	
	public String getFile() {
		
		return file;
		
	}
	
	public void setFile(String file) {
		
		this.file = file;
		
	}
	
	public String getEstado_inicial() {
		
		return estado_inicial;
		
	}
	
	public void setEstado_inicial(String estado) {
		this.estado_inicial = estado;
	}
	
	
}
