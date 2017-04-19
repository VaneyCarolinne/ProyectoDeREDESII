import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

public class desencriptado {

	/**
	 * PROYECTO DE DESCENCRIPTADO - por Vanessa Cruz.
	 */
	public static char repeticiones[] = new char[10000]; // array global
	public static boolean es_sustitucion = true; //variable bool para identificar que encriptado es...
	public static char R[][] = new char[1000][1000];
	public static String orden[] = new String[40];
	public static String tabla[] = new String[40];//para marcas las letras repetidas en sustitucion
	public static int cont[] = new int[10000];
	
	public static void main(String[] args) throws Exception {
		int dim, columna, fila;
		String clave="Ñ3hOXLsFQy";
		String texto = obtieneTexto();		
		texto=texto.toLowerCase();
		Desicion(texto); //Elige que algoritmo usar...
		
		if(es_sustitucion){/*******ALGORITMO DE SUSTITUCION SIMPLE A LA INVERSA...******/			
			iniciar();
			BuscarRepeticion(texto);
			Patron();
		}else{/*******ALGORITMO DE TRANSPOSICION SIMPLE A LA INVERSA...******/
			dim=texto.length();
			columna=(dim/(clave.length()*4)); //generando un tamano para las columnas relacionado con la clave 
			fila=(dim/columna);
			llenarMatriz(texto,fila,columna);
			transposicion(dim,fila,columna,clave);
		}
		
  }

/******************IMPRIME TEXTO*********************/
	public static void ImprimeCifradoArch(String texto) throws IOException{
		FileWriter archsalida = new FileWriter("Descifrado.txt");//archivo de salida para el cifrado...
		PrintWriter apt = new PrintWriter(archsalida);
		
		apt.println(texto);
		apt.println("\n");
		apt.close();
	}
/***OBTENIENDO TEXTO DEL ARCHIVO PARA ALGUN CASO****/
	public static String obtieneTexto() throws IOException{
		BufferedReader fuego;
		String linea, texto="";
	
		fuego= new BufferedReader(new FileReader("CasoCifrado.txt"));
	
		while((linea = fuego.readLine())!=null) texto = texto + linea;
			fuego.close();
	
	return texto;
	
	}
/**********************Verifica si esta marcada la letra en la tabla************/
	public static boolean estaMarcada(String lel){
		boolean bd=false;
		
		for(int i=0; i<orden.length; i++){
			if(lel.equals(orden[i])&&tabla[i].equals("#")&&!bd)
				bd=true;
		}
		
		return bd;
	}
/*************MARCANDO LETRA EN LA TABLA COMO USADA**********/	
	public static void Marcar(String letra){
		for(int i=0; i<orden.length; i++){
			if(letra.equals(orden[i]))
				tabla[i]="#";	
		}
		
	}
/*************INICIALIZA VECTOR ORDEN Y TABLA***************/
	public static void iniciar(){
		//Se inicializa en los caracteres segun el alfabeto aceptado por el proyecto...
		orden[0]="e";
		orden[1]="a";
		orden[2]="o";
		orden[3]="s";
		orden[4]="r";
		orden[5]="n";
		orden[6]="i";
		orden[7]="d";
		orden[8]="l";
		orden[9]="c";
		orden[10]="t";
		orden[11]="u";
		orden[12]="m";
		orden[13]="p";
		orden[14]="b";
		orden[15]="g";
		orden[16]="v";
		orden[17]="y";
		orden[18]="q";
		orden[19]="h";
		orden[20]="f";
		orden[21]="z";
		orden[22]="j";
		orden[23]="ñ";
		orden[24]="x";
		orden[25]="w";
		orden[26]="k";
		orden[27]=" ";
		orden[28]="\n";
		orden[29]=".";
		orden[30]=":";
		orden[31]=";";
		orden[32]=",";
		orden[33]=")";
		orden[34]="(";
		orden[35]="?";
		orden[36]="$";
		orden[37]="&";
		orden[39]="¿";
		
		tabla[0]="e";
		tabla[1]="a";
		tabla[2]="o";
		tabla[3]="s";
		tabla[4]="r";
		tabla[5]="n";
		tabla[6]="i";
		tabla[7]="d";
		tabla[8]="l";
		tabla[9]="c";
		tabla[10]="t";
		tabla[11]="u";
		tabla[12]="m";
		tabla[13]="p";
		tabla[14]="b";
		tabla[15]="g";
		tabla[16]="v";
		tabla[17]="y";
		tabla[18]="q";
		tabla[19]="h";
		tabla[20]="f";
		tabla[21]="z";
		tabla[22]="j";
		tabla[23]="ñ";
		tabla[24]="x";
		tabla[25]="w";
		tabla[26]="k";
		tabla[27]=" ";
		tabla[28]="\n";
		tabla[29]=".";
		tabla[30]=":";
		tabla[31]=";";
		tabla[32]=",";
		tabla[33]=")";
		tabla[34]="(";
		tabla[35]="?";
		tabla[36]="$";
		tabla[37]="&";
		tabla[39]="¿";
		
	}
/****BUSCANDO COMBINACION DE LETRAS REPETIDAS******/	
	public static void BuscarRepeticion(String text){
		int rep, aux;
		char qux;
		//String lel;

		//se inicializan los contadores y los vectores donde se guardaran las frecuencias...
		for(int j=0; j < text.length(); j++){
			cont[j]=0;
			repeticiones[j]=' ';
		}
		String hh;
	    for(int j=0; j < text.length(); j++){
	      rep=0;
	      hh=""+text.charAt(j);//Varable de cadena que trabajara en conjunto con las funciones para estudiar la frecuencia de las letras..
	      for(int i=0; i < text.length(); i++){
			if(text.charAt(j)==text.charAt(i)&&!estaMarcada(hh)){ // Cuenta los caracteres repetidos, caracter por caracter...
				rep++;		
			}	
		  }
	     
		  if(cont[j]<rep){ //los va guardando segun su repeticion y segun el mayor dentro de los vectores...
				cont[j]=rep;
				repeticiones[j]=text.charAt(j);
				Marcar(hh); //marca la letra como repetida en la frecuencia.
		  }
	    }//fin for grande...
	    
	    //Ordena los vectores de mayor a menor segun su frecuencia en el texto...
	    for (int i = 0; i < cont.length - 1; i++) {
	        for (int j = i + 1; j < cont.length; j++) {
	            if (cont[j] > cont[i]) {
	                aux = cont[i];
	                cont[i] = cont[j];
	                cont[j] = aux;
	                
	                qux = repeticiones[i];
	                repeticiones[i] = repeticiones[j];
	                repeticiones[j] = qux;
	            }
	        }
	    }
	    //MUESTRA POR PANTALLA LA FRECUENCIA...
	    int i;
	    i=0;
	    System.out.println("Caracteres:   Frecuencia en el texto:");
	    while(cont[i]!=0){
	    	System.out.println(repeticiones[i]+"                 "+cont[i]);
	    	i++;
	    }
	    
	    
	    //String tululu;
	    //Validando repeticiones...
	    /*for(int j=0; j<3; j++){
	    	tululu=""+repeticiones[j];
	    	//System.out.println(tululu);
	    	for(int i=0; i<orden.length; i++){
	    		if(tululu.equals(orden[i])&&tabla[i].equals("#")) // poniendo un limite constante de 90 repeticiones al menos por cada letra
	    			repeticiones[j]='#'; // se marca como que no es una sustitucion valida...
	    	}	
	    }*/
	    //NOTA: En caso de que el vector este lleno de puros # quiere decir que el
	    //texto se encripto con transposicion y no con sustitucion simple...
	    //if(repeticiones[1]=='#') es_sustitucion = false;
	}
/***********SE LLENA EL TEXTO CIFRADO EN LA MATRIZ************/
	public static void llenarMatriz(String texto, int fila, int columna){
		int pos=0;
		for(int i=0; i<fila; i++){
			for(int j=0; j<columna;j++){
				  R[i][j]=texto.charAt(pos);
				  pos++;	
			}
		}
	
	}
/********funcion que me retorna si es una vocal o una variable una char*********/
	public static boolean esVocal(char caracter){
		boolean band;
		if(caracter=='a'||caracter=='e'||caracter=='i'||caracter=='o'||caracter=='u')
			band=true;
		else
			band=false;
		return band;
	}
/********funcion que me retorna si es una constante terminal segun el alfabeto espa;ol*********/
	public static void esConTer(String casicifrado){
		String prueb="",decode="";
		
		iniciar(); // Usar nuevamente la tecnica de las tablas...
		for(int i=0; i<casicifrado.length()-1;i++){//probablemente es un final de palabra...
			prueb=""+casicifrado.charAt(i);
			if(casicifrado.charAt(i+1)==' '||casicifrado.charAt(i+1)==','||casicifrado.charAt(i+1)==':'||casicifrado.charAt(i+1)=='.'||casicifrado.charAt(i+1)=='\n'){
				
				if(!estaMarcada(prueb)&&((casicifrado.charAt(i-1)=='a'&&casicifrado.charAt(i-2)=='i')||casicifrado.charAt(i-1)=='o'||casicifrado.charAt(i-1)=='u'||casicifrado.charAt(i-1)=='e')){
					casicifrado.replace(casicifrado.charAt(i), 's');
				    decode="s";
				    System.out.println("ENTRE");
				}else{ 
					if(!estaMarcada(prueb)&&((casicifrado.charAt(i-1)=='a'&&(casicifrado.charAt(i-2)=='i'||casicifrado.charAt(i-2)=='u'||casicifrado.charAt(i-2)=='e'))||casicifrado.charAt(i-1)=='o')){
						casicifrado.replace(casicifrado.charAt(i), 'r');
						decode="r";
					}else{
						if(!estaMarcada(prueb)&&((casicifrado.charAt(i-1)=='a'&&(casicifrado.charAt(i-2)=='i'||casicifrado.charAt(i-2)=='e'))||casicifrado.charAt(i-1)=='o'||casicifrado.charAt(i-1)=='e'||casicifrado.charAt(i-1)=='a')){
							casicifrado.replace(casicifrado.charAt(i), 'n');
							decode="n";
						}else{
							if(!estaMarcada(prueb)&&casicifrado.charAt(i-1)=='a'){
								casicifrado.replace(casicifrado.charAt(i), 'd');
								decode="d";
							}else{ 
								if(!estaMarcada(prueb)&&(casicifrado.charAt(i-1)=='a'||casicifrado.charAt(i-1)=='e')){
									casicifrado.replace(casicifrado.charAt(i), 'l');
									decode="l";
								}else{
									if(!estaMarcada(prueb)&&casicifrado.charAt(i-1)=='a'){
										casicifrado.replace(casicifrado.charAt(i), 'z');
										decode="z";
									}
							    }
							 }
						 }
					 }
				}	
				
				limpiarCifrado(casicifrado,decode,prueb);
				Marcar(prueb);
				Marcar(decode);
			}//fin si grande...
		}//fin for grande...
	}//fin proc.
/********funcion que me retorna si es un caracter especial o una variable de una char*********/
	public static boolean esEspecial(char caracter){
		boolean band;
		
		//En caso de que sea un caracter especial...
		if(caracter==' '||caracter==10||caracter==')'||caracter=='('||caracter==','||caracter==';'||caracter==':'||caracter=='.'||caracter=='?'||caracter=='¿'){
			band=true;
		}else{
			band=false;
		}
		return band;	
	}
/*****************LIMPIANDO TEXTO CIFRADO*****************/
	public static void limpiarCifrado(String texto, String dec, String prueb){
		for(int i=0; i<texto.length();i++){
			if(texto.charAt(i)==prueb.charAt(0))
				texto.replace(prueb, dec);
		}
		System.out.println("\nTEXTO DESCIFRADO CON TERMINACIONES\n");	
		System.out.println(texto);
	}
/******************Buscar patron de recurrencias.. ****************/
	public static void Patron() throws IOException{
		String prue2, prue3, cifrado=obtieneTexto(),folo,tululu;
		int i=0,j=1;
		
		cifrado=cifrado.toLowerCase();
		folo="";
		iniciar();
		while(cont[i]!=0){	
			prue2="";
			prue3="";
			folo=""+repeticiones[i];
			if(!estaMarcada(folo)){ 
				tululu="";
				Marcar(folo);
				if(cont[i]>cont[j]){
					prue2=prue2+repeticiones[i]+repeticiones[j];
					tululu=tululu+repeticiones[j];
					cifrado=cifrado.replace(prue2,tululu);
				}else{
					prue3=prue3+repeticiones[j]+repeticiones[i];
					tululu=tululu+repeticiones[i];
					cifrado=cifrado.replace(prue3,tululu);
				}
			}//fin del si grande
			i++;
			j++;
		}//Fin del while...
		Depurar(cifrado);
	}//fin del procedimiento patron...
/**************DEPURA TEXTO CON SALTOS DE LINEA **************/
	public static void Depurar(String text) throws IOException{
		String textdepurado="";
		//Agregan saltos de lineas para depurar el texto tal cual como sale en el archivo...
			for(int k=0; k<text.length()-1; k++){
				if(text.charAt(k)=='.'||text.charAt(k)==':'){
					if(text.charAt(k)=='.') textdepurado= textdepurado + ".\n";
					else textdepurado= textdepurado + ":\n";
				}else{
					textdepurado= textdepurado + text.charAt(k);
				}
			}
		System.out.println("\nTEXTO DESCIFRADO SIN ORDENAR\n");	
		System.out.println(textdepurado);	
		ImprimeCifradoArch(textdepurado); //Imprime el texto descifrado en el archivo de Salida...
		//esConTer(textdepurado);
	}
/******************ALGORITMO DE TRANSPOSICION**************/
	public static void transposicion(int dim, int fila, int columna, String clave) throws IOException{
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
		for(int i=0; i<movcolunm.length; i++){
			System.out.print(movcolunm[i]+" ");
		}
		System.out.println();
		
		/******GENERAMOS MATRIZ DESCIFRADA *******/
		/**A PARTIR DEL VECTOR DE INDICES CON EL PATRON DE LA CLAVE********/
		char M[][] = new char[fila+1][columna+1];
		//Introduciendo matriz cifrada.
		for(int i=0;i<columna;i++){
			for(int j=0; j<fila; j++){
				M[j][i]=R[j][movcolunm[i]];
			}
		}
		
		//imprime matriz descifrada por pantalla
		System.out.println("\n		MATRIZ DESCIFRADA:\n");
			for(int i=0;i<fila;i++){
				System.out.print("|");
				for(int j=0;j<columna;j++){
					System.out.print(M[i][j]+"|");	
				}
				System.out.println("");
			}
			System.out.println("");
	
		/**********OBTENEMOS EL TEXTO DESCIFRADO RECORRIENDO LA MATRIZ ***********/
		/*******************DE ARRIBA HACIA ABAJO**************************/
		String textoCifrado="";
		int o, a=0;
		
		while(textoCifrado.length()<=dim){
			for(o=0; o<fila;o++){
			  textoCifrado=textoCifrado + M[o][a];	
			}
			a++;
		}
		//Escribe texto cifrado por pantalla
		System.out.println("\n		TEXTO DESCIFRADO:	 \n");
	    System.out.println(textoCifrado);
		System.out.println("\n");
		ImprimeCifradoArch(textoCifrado); //Imprime el texto descifrado en el archivo de Salida...
	}
/*************TOMA LA DESICION DE CUAL ALGORITMO USAR SI TRANSPOSICION SIMPLE
 * ***********O SUSTITUCION SIMPLE***********************/
	public static void Desicion(String texto){		
		for(int i=2; i<16; i++){
			if(texto.charAt(0)==texto.charAt(i))// Posibilidad de que el texto tenga una clave menor o igual a 16 bits...
				es_sustitucion=false;
		}
	}

}//fin de la clase desencriptado...