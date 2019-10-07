import java.util.ArrayList;
import java.util.List;

public class principal {

	public static void main(String[] args) {

		String cubo = "444333333111244111222222555244000244553111553000553000";
		
		System.out.println("Cubo Inicial");
		System.out.println(cubo);
		
		ArrayList<String[][]> cubomatriz = new ArrayList<String[][]>();  //El cubo con las 6caras en matrices
		
		System.out.println("Cubo tras B0:");
		
		inicializarcubomatriz(cubomatriz, cubo);
		GirarBack(cubomatriz, 0);
		cubo = convertircubomatriz(cubomatriz);
		
		System.out.println(cubo);
		
		System.out.println("Cubo tras B0 y b0:");
		
		inicializarcubomatriz(cubomatriz, cubo);
		GirarBack2(cubomatriz, 0);
		cubo = convertircubomatriz(cubomatriz);
		
		System.out.println(cubo);
		
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

}
