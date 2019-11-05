import java.util.ArrayList;

public class OperacionesCubo {

	public static ArrayList<String[][]> inicializarcubomatriz(ArrayList<String[][]> cubomatriz, String cubo) {
		
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
		return cubomatriz;
	}
	
	public static void imprimircubo(String[][] cubo) {
		for(int i=0; i<cubo.length;i++) {
			for(int j=0; j<cubo[i].length; j++) {
				System.out.print(cubo[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static int calcularcuadrados(int N) {
		int cuadrados = 0;
		int aux = 4;
		while(aux>3) {
			int M = N*N;
			int c = 3;
			int t = N*c;
			aux = M-t;
			N=N-2;
			cuadrados++;
		}
		return cuadrados;
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
		int cuadrados = calcularcuadrados(N);
		
		if(movimiento == 0) { // Se mueve Back
			
			for(int j=0; j<cuadrados; j++) {
			
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = caraback[i][N-1-j];
					caraback[i][N-1-j] = caraback[j][i];
				
					Aux2 = caraback[N-1-j][N-1-i];
					caraback[N-1-j][N-1-i] = Aux1;
				
					Aux1 = caraback[N-1-i][j];
					caraback[N-1-i][j] = Aux2;
				
					caraback[j][i] = Aux1;				
				}
			}
			
		}else if(movimiento == N-1) { // Se mueve Front
			
			for(int j=0; j<cuadrados; j++) {
			
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = carafront[i][N-1-j];
					carafront[i][N-1-j] = carafront[j][i];
				
					Aux2 = carafront[N-1-j][N-1-i];
					carafront[N-1-j][N-1-i] = Aux1;
				
					Aux1 = carafront[N-1-i][j];
					carafront[N-1-i][j] = Aux2;
				
					carafront[j][i] = Aux1;				
				}
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
		int cuadrados = calcularcuadrados(N);
		
		if(movimiento == 0) { // Se mueve Back
			
			for(int j=0; j<cuadrados; j++) {
				
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = caraback[j][i];
					caraback[j][i] = caraback[i][N-1-j];
				
					Aux2 = caraback[N-1-i][j];
					caraback[N-1-i][j] = Aux1;
				
					Aux1 = caraback[N-1-j][N-1-i];
					caraback[N-1-j][N-1-i] = Aux2;
				
					caraback[i][N-1-j] = Aux1;
							
				}
			}
			
		}else if(movimiento == N-1) { // Se mueve Front
			
			for(int j=0; j<cuadrados; j++) {
			
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = carafront[j][i];
					carafront[j][i] = carafront[i][N-1-j];
				
					Aux2 = carafront[N-1-i][j];
					carafront[N-1-i][j] = Aux1;
				
					Aux1 = carafront[N-1-j][N-1-i];
					carafront[N-1-j][N-1-i] = Aux2;
				
					carafront[i][N-1-j] = Aux1;
				
				}
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
		int cuadrados = calcularcuadrados(N);
		
		if(movimiento == 0) { // Se mueve down
			
			for(int j=0; j<cuadrados; j++) {
				
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = caradown[i][N-1-j];
					caradown[i][N-1-j] = caradown[j][i];
				
					Aux2 = caradown[N-1-j][N-1-i];
					caradown[N-1-j][N-1-i] = Aux1;
				
					Aux1 = caradown[N-1-i][j];
					caradown[N-1-i][j] = Aux2;
				
					caradown[j][i] = Aux1;				
				}
			}
			
		}else if(movimiento == N-1) { // Se mueve up
			
			for(int j=0; j<cuadrados; j++) {
				
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = caraup[i][N-1-j];
					caraup[i][N-1-j] = caraup[j][i];
				
					Aux2 = caraup[N-1-j][N-1-i];
					caraup[N-1-j][N-1-i] = Aux1;
				
					Aux1 = caraup[N-1-i][j];
					caraup[N-1-i][j] = Aux2;
				
					caraup[j][i] = Aux1;				
				}
			}
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = caraback[N-1-movimiento][N-1-i];
			caraback[N-1-movimiento][N-1-i] = caraleft[i][N-1-movimiento];
			
			Aux2 = cararight[N-1-i][movimiento];
			cararight[N-1-i][movimiento] = Aux1;
			
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
		
		int cuadrados = calcularcuadrados(N);
		
		if(movimiento == 0) { // Se mueve down	
			
			for(int j=0; j<cuadrados; j++) {
			
				for(int i=0+j; i<N-1-j; i++) {
				
				
					Aux1 = caradown[j][i];
					caradown[j][i] = caradown[i][N-1-j];
				
					Aux2 = caradown[N-1-i][j];
					caradown[N-1-i][j] = Aux1;
				
					Aux1 = caradown[N-1-j][N-1-i];
					caradown[N-1-j][N-1-i] = Aux2;
				
					caradown[i][N-1-j] = Aux1;
				}
			}
			
		}else if(movimiento == N-1) { // Se mueve up
			
			for(int j=0; j<cuadrados; j++) {
				
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = caraup[j][i];
					caraup[j][i] = caraup[i][N-1-j];
				
					Aux2 = caraup[N-1-i][j];
					caraup[N-1-i][j] = Aux1;
				
					Aux1 = caraup[N-1-j][N-i-1];
					caraup[N-1-j][N-1-i] = Aux2;
				
					caraup[i][N-1-j] = Aux1;
								
				}
			}
				
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = caraleft[i][N-1-movimiento];
			caraleft[i][N-1-movimiento] = caraback[N-1-movimiento][N-1-i];
			
			Aux2 = carafront[movimiento][i];
			carafront[movimiento][i] = Aux1;
			
			Aux1 = cararight[N-1-i][movimiento];
			cararight[N-1-i][movimiento] = Aux2;
			
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
		int cuadrados = calcularcuadrados(N);
		
		String Aux1 = ""; 
		String Aux2 = "";
		
		if(movimiento == 0) { // Se mueve left
			
			for(int j=0; j<cuadrados; j++) {
			
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = caraleft[i][N-1-j];
					caraleft[i][N-1-j] = caraleft[j][i];
					
					Aux2 = caraleft[N-1-j][N-1-i];
					caraleft[N-1-j][N-1-i] = Aux1;
					
					Aux1 = caraleft[N-1-i][j];
					caraleft[N-1-i][j] = Aux2;
				
					caraleft[j][i] = Aux1;				
				}
			}
			
		}else if(movimiento == N-1) { // Se mueve right
			
			for(int j=0; j<cuadrados; j++) {
			
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = cararight[i][N-1-j];
					cararight[i][N-1-j] = cararight[j][i];
				
					Aux2 = cararight[N-1-j][N-1-i];
					cararight[N-1-j][N-1-i] = Aux1;
				
					Aux1 = cararight[N-1-i][j];
					cararight[N-1-i][j] = Aux2;
				
					cararight[j][i] = Aux1;				
				}
			}
			
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = caraback[i][movimiento];
			caraback[i][movimiento] = caradown[i][movimiento];
			
			Aux2 = caraup[N-1-i][N-1-movimiento];
			caraup[N-1-i][N-1-movimiento] = Aux1;
			
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
		int cuadrados = calcularcuadrados(N);
		
		String Aux1 = ""; 
		String Aux2 = "";
		
		if(movimiento == 0) { // Se mueve left

			for(int j=0; j<cuadrados; j++) {
			
				for(int i=0+j; i<N-1-j; i++) {
				
					Aux1 = caraleft[j][i];
					caraleft[j][i] = caraleft[i][N-1-j];
				
					Aux2 = caraleft[N-1-i][j];
					caraleft[N-1-i][j] = Aux1;
				
					Aux1 = caraleft[N-1-j][N-1-i];
					caraleft[N-1-j][N-1-i] = Aux2;
				
					caraleft[i][N-1-j] = Aux1;
				
				}
			}
			
		}else if(movimiento == N-1) { // Se mueve right
			
			for(int j=0; j<cuadrados; j++) {
			
				for(int i=0+j; i<N-1-j; i++) {
					Aux1 = cararight[j][i];
					cararight[j][i] = cararight[i][N-1-j];
				
					Aux2 = cararight[N-1-i][j];
					cararight[N-1-i][j] = Aux1;
				
					Aux1 = cararight[N-1-j][N-1-i];
					cararight[N-1-j][N-1-i] = Aux2;
				
					cararight[i][N-1-j] = Aux1;		
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			Aux1 = caradown[i][movimiento];
			caradown[i][movimiento] = caraback[i][movimiento];
			
			Aux2 = carafront[i][movimiento];
			carafront[i][movimiento] = Aux1;
			
			Aux1 = caraup[N-1-i][N-1-movimiento];
			caraup[N-1-i][N-1-movimiento] = Aux2;
			
			caraback[i][movimiento] = Aux1;
			
		}
	}
	

}
