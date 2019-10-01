import java.lang.*;

public class main {

	public static void main(String[] args) {

		String cubo = "444333333111244111222222555244000244553111553000553000";
		
		System.out.println("Cubo Inicial");
		System.out.println(cubo);
		
		System.out.println("Cubo tras B0");
		cubo = GirarBack(cubo, 0); // B0
		System.out.println(cubo); // B0

		System.out.println("Cubo tras B0 y b0");
		cubo = GirarBack2(cubo, 0); // b0
		System.out.println(cubo); // b0
		
		
		
	}
	
	public static String GirarBack(String c, int movimiento) { // Bmovimiento (0 <= movimiento < N)
		
		int N = (int)Math.sqrt(c.length()/6);
		
		System.out.println("N es: "+N);
		
		StringBuilder cubo = new StringBuilder(c);
		String Aux1 = ""; 
		String Aux2 = "";
			
		if(movimiento == 0) { // Se mueve Back
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = ""+cubo.charAt(N*i+N-1);
				cubo.replace(N*i+N-1, N*i+N, ""+cubo.charAt(i)); 
				
				Aux2 = ""+cubo.charAt(N*N-1-i); 
				cubo.replace(N*N-1-i, N*N-i, ""+Aux1); 
				
				Aux1 = ""+cubo.charAt(N*(N-1-i)); 
				cubo.replace(N*(N-1-i), N*(N-1-i)+1, ""+Aux2); 
			
				cubo.replace(i, i+1, ""+Aux1);
				
			}
			
		}else if(movimiento == N-1) { // Se mueve Front
			
			for(int i=0; i<N; i++) {
				
				Aux1 = ""+cubo.charAt(N*N*2+(N*(i+1))-1);
				cubo.replace(N*N*2+(N*(i+1))-1, N*N*2+(N*(i+1)), ""+cubo.charAt(i)); 
				
				Aux2 = ""+cubo.charAt(N*N*2+N*N-1-i); 
				cubo.replace(N*N*2+N*N-1-i, N*N*2+N*N-i, ""+Aux1); 
				
				Aux1 = ""+cubo.charAt(N*N*2+N*i); 
				cubo.replace(N*N*2+N*(N-1-i), N*N*2+N*(N-1-i)+1, ""+Aux2); 
			
				cubo.replace(N*N*2+i, N*N*2+i+1, ""+Aux1);
				
			}
		}
		
		for(int i=0; i<N; i++) {

			Aux1 = ""+cubo.charAt(N*N*4+i+N*movimiento);
			cubo.replace(N*N*4+i+N*movimiento, N*N*4+i+1+N*movimiento, ""+cubo.charAt(N*N+i+N*movimiento));
			
			Aux2 = ""+cubo.charAt(N*N*5+N*movimiento+i);
			cubo.replace(N*N*5+N*movimiento+i, N*N*5+N*movimiento+i+1, Aux1);
			
			Aux1 = ""+cubo.charAt(N*N*3+N*movimiento+i);
			cubo.replace(N*N*3+N*movimiento+i, N*N*3+N*movimiento+i+1, Aux2);
			
			cubo.replace(N*N+N*movimiento+i, N*N+N*movimiento+i+1, Aux1);
		
		}
		
		return ""+cubo;
		
	}
	
	public static String GirarBack2(String c, int movimiento) { // bmovimiento (0 <= movimiento < N)
		
		int N = (int)Math.sqrt(c.length()/6);
		
		StringBuilder cubo = new StringBuilder(c);
		String Aux1 = "";
		String Aux2 = "";
		
		if(movimiento == 0) { //Se mueve Back inversamente
			
			for(int i=0; i<N-1; i++) {
				
				Aux1 = ""+cubo.charAt(i);
				cubo.replace(i, i+1, ""+cubo.charAt(N*i+N-1));
				
				Aux2 = ""+cubo.charAt(N*(N-1-i));
				cubo.replace(N*(N-1-i), N*(N-1-i)+1, Aux1);
				
				Aux1 = ""+cubo.charAt(N*N-1-i);
				cubo.replace(N*N-1-i, N*N-i, Aux2);
				
				cubo.replace(N*i+N-1, N*i+N, Aux1);
				
			}
			
		}else if(movimiento == N-1) { //Se mueve Front inversamente
			
			for(int i=0; i<N; i++) {
				
				Aux1 = ""+cubo.charAt(i);
				cubo.replace(i, i+1, ""+cubo.charAt(N*N*2+N*i+N-1));
				
				Aux2 = ""+cubo.charAt(N*N*2+N*(N-1-i));
				cubo.replace(N*N*2+N*(N-1-i), N*N*2+N*(N-1-i)+1, Aux1);
				
				Aux1 = ""+cubo.charAt(N*N*2+N*N-1-i);
				cubo.replace(N*N*2+N*N-1-i, N*N*2+N*N-i, Aux2);
				
				cubo.replace(N*N*2+N*i+N-1, N*N*2+N*i+N, Aux1);
				
			}
		}
		
		for(int i=0; i<N; i++) {
			
			Aux1 = ""+cubo.charAt(N*N+i+N*movimiento);
			cubo.replace(N*N+i+N*movimiento, N*N+i+N*movimiento+1, ""+cubo.charAt(N*N*4+i+N*movimiento));
		
			Aux2 = ""+cubo.charAt(N*N*3+N*movimiento+i);
			cubo.replace(N*N*3+N*movimiento+i, N*N*3+N*movimiento+i+1, Aux1);
			
			Aux1 = ""+cubo.charAt(N*N*5+N*movimiento+i);
			cubo.replace(N*N*5+N*movimiento+i, N*N*5+N*movimiento+i+1, Aux2);
			
			cubo.replace(N*N*4+i+N*movimiento, N*N*4+i+1+N*movimiento, Aux1);
			
		}
		
		return ""+cubo;
		
	}
	
}