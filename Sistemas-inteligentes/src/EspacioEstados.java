
import java.util.ArrayList;


public class EspacioEstados {
	
	String file = new String();
	
	public EspacioEstados(String file) {
		this.file = file;
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
			
			cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarBack2(cubomatriz, i);
			String[] sucesor2 = {OperacionesCubo.convertircubomatriz(cubomatriz), "b"+i};
			ListaSucesores.add(sucesor2);

			cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarDown(cubomatriz, i);
			String[] sucesor3 = {OperacionesCubo.convertircubomatriz(cubomatriz), "D"+i};
			ListaSucesores.add(sucesor3);

			cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarDown2(cubomatriz, i);
			String[] sucesor4 = {OperacionesCubo.convertircubomatriz(cubomatriz), "d"+i};
			ListaSucesores.add(sucesor4);
			
			cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarLeft(cubomatriz, i);
			String[] sucesor5 = {OperacionesCubo.convertircubomatriz(cubomatriz), "L"+i};
			ListaSucesores.add(sucesor5);
			
			cubomatriz = new ArrayList<String[][]>();
			cubomatriz = OperacionesCubo.inicializarcubomatriz(cubomatriz, cubo);
			OperacionesCubo.GirarLeft2(cubomatriz, i);
			String[] sucesor6 = {OperacionesCubo.convertircubomatriz(cubomatriz), "l"+i};
			ListaSucesores.add(sucesor6);
				
		}
		return ListaSucesores;
		
	}
	
	public String getFile() {
		
		return file;
		
	}
	
	public void setFile(String file) {
		
		this.file = file;
		
	}
	
	
}
