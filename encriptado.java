/*encriptando mensaje por medio del algoritmo de transposicion By: Vanessa Cruz*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class encriptado {
	public static void main (String args[]) throws IOException {
		BufferedReader entrada;
		String linea, texto="", clave="Ñ3hOXLsFQy", textdepurado="";
		int dim, columna, fila;
		char O[][];
		
		//clave en numeros ASCII: |165|51|104|79|88|76|115|70|81|121| 
		
		entrada= new BufferedReader(new FileReader("textollano.txt"));
		FileWriter archsalida = new FileWriter("textoCifrado.txt");//archivo de salida para el cifrado...
		PrintWriter apt = new PrintWriter(archsalida);
		
		//Leyendo archivo del texto llano.
		while((linea = entrada.readLine())!=null) texto = texto + linea;
		entrada.close();
		//Agregan saltos de lineas para depurar el texto tal cual como sale en el archivo...
		for(int k=0; k<texto.length(); k++){
			if(texto.charAt(k)=='.'||texto.charAt(k)==':'){
				if(texto.charAt(k)=='.') textdepurado= textdepurado + ".\n";
				else textdepurado= textdepurado + ":\n";
			}else{
				textdepurado= textdepurado + texto.charAt(k);
			}
		}
		
		/*System.out.println("TEXTO LLANO DEPURADO: ");
	    System.out.println(textdepurado);
	    */
		
	    texto=textdepurado;
		//Ingresadando texto en la matriz ...!!
		dim=texto.length();
		columna=(dim/(clave.length()*4)); //generando un tamano para las columnas relacionado con la clave 
		fila=(dim/columna);//tamano en filas
		O = new char[fila+1][columna+1];
		
		/*System.out.println("\n"+dim);
		System.out.println("\n"+columna);
		System.out.println("\n"+fila);*/
		
		int pos=0;
		
		for(int i=0; i<fila; i++){
			for(int j=0; j<columna;j++){
				  O[i][j]=texto.charAt(pos);
				  pos++;	
			}
		}
	
		//imprime matriz por pantalla
		/*for(int i=0;i<fila;i++){
			System.out.print("|");
			for(int j=0;j<columna;j++){
				System.out.print(O[i][j]+"|");	
			}
			System.out.print("");
		}
		System.out.println("");
		*/
	
	/*System.out.println("TEXTO LLANO: ");
    System.out.println(texto);
	System.out.println("\n");*/
	
		/*************ALGORITMO TRANSPOSICION**************/
		/****************GENERANDO PATRON SEGUN LA CLAVE****************/
		//Haciendo vector de indices para la clave.
		//SEGUN EL PATRON QUE GENERA LA CLAVE HASTA EL NUM DE COLUMNAS.
		int movcolunm[] = new int[22];
		int menor,k=0,p=0; 
		String claveA;
	   //se escoge a partir de la letra menor en la clave siguiendo un patron...
	   while(p<30){
		menor=1000;
		if(p==20) claveA="Ñ3";//rellenamos lo que falta en columnas con lo que viene del patron de la clave
		else claveA=clave; //utilizamos una clave auxiliar siempre inicializada en la clave establecida, para generar un patron repetitivo hasta llenar todas las columnas...
		for(int i=0; i<claveA.length(); i++){
		 for(int j=0; j<claveA.length(); j++){	
			if(menor>(int)claveA.charAt(j)&&claveA.charAt(j)!='#'){
				menor=(int)claveA.charAt(j);
				k=j+p;
			}	
		  }
		  movcolunm[i+p]=k;//vector de indices...
		  claveA=claveA.replace(claveA.charAt(k-p),'#');//marcamos la letra de menor valor para ir descartando...
		  menor=1000;//volvemos a inicializar la variable menor...
		}
		p=p+10;//actualizamos contador que nos dira en que patron repetitivo estaremos...
	  }	
		
		//imprime vector de indices por pantalla
		/*for(int i=0; i<movcolunm.length; i++){
			System.out.print(movcolunm[i]+" ");
		}
		System.out.println();*/
		
		/******GENERAMOS MATRIZ CIFRADA *******/
		/**A PARTIR DEL VECTOR DE INDICES CON EL PATRON DE LA CLAVE********/
		char M[][] = new char[fila+1][columna+1];
		//Introduciendo matriz cifrada.
		for(int i=0;i<columna;i++){
			for(int j=0; j<fila; j++){
				M[j][i]=O[j][movcolunm[i]];
			}
		}
		
		//imprime matriz cifrada por pantalla
			/*for(int i=0;i<fila;i++){
				System.out.print("|");
				for(int j=0;j<columna;j++){
					System.out.print(M[i][j]+"|");	
				}
				System.out.println("");
			}
			System.out.println("");
			*/
		/**********OBTENEMOS EL TEXTO CIFRADO RECORRIENDO LA MATRIZ CIFRADA ***********/
		/*******************DE ARRIBA HACIA ABAJO**************************/
		String textoCifrado="";
		int o, a=0;
		
		while(textoCifrado.length()!=dim){
			for(o=0; o<fila;o++){
			  textoCifrado=textoCifrado + M[o][a];	
			}
			a++;
		}
		//Escribe texto cifrado por pantalla
		/*System.out.println("TEXTO CIFRADO: ");
	    System.out.println(textoCifrado);
		System.out.println("\n");*/
		
		//Escribe texto cifrado en el archivo de salida
		apt.println(textoCifrado);
		apt.println("\n*");
		apt.close();
	}
}
