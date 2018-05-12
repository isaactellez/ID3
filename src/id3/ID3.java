package id3;

import java.util.*;
import java.math.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ID3 {
    
    static Attribute[] atributos;
    static String target;
    static ArrayList<String[]> table;
    static int cols;
    
    static double log(double x, double base){
        
        if(x==0){
            return 0;
        }else{
            return (Math.log(x) / Math.log(base));
        }
        
    }

	public static void input () throws IOException {
            
		int n;
                List<Map<String,Integer>> maps = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Dame el numero de atributos del data set: ");
		n = Integer.parseInt(br.readLine());
                cols = n;
                atributos = new Attribute[n];

		for(int i=0; i<n ;i++){
                    
                    System.out.println("Dame el atributo " + i + " (en orden): ");
                    String a = br.readLine();
                    
                    atributos[i] = new Attribute(a);
                    
		}
                
                //Prueba llenando a mano el data set
//                for(int i=0;i<12;i++){
//                    int x = i%n;
//                    System.out.println("Dame el valor del atributo: " + x);
//                    String a = br.readLine();
//                    atributos[x].addValue(a);
//                }
                
                System.out.println("Dame el nombre de archivo (con extension) donde se encuentra el data set:");
                String file = br.readLine();
                file = "C:\\Users\\HTELLEZB\\Downloads\\ID3-master\\src\\id3\\" + file;
                FileReader fr = null;
                BufferedReader br2 = null;
                table = new ArrayList<>();
                
                try {
                        
                        fr = new FileReader(file);
			br2 = new BufferedReader(fr);

			String sCurrentLine;

			while ((sCurrentLine = br2.readLine()) != null) {
				//System.out.println(sCurrentLine);
                                String [] parts = sCurrentLine.split(",");
                                table.add(parts);
                                for(int i=0; i<n; i++){
                                    atributos[i].addValue(parts[i]);
                                }
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br2 != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
                
                //Leer archivo
                
                //Prueba
                //System.out.println("Atributo: " + atributos[0].name + " Cuenta: " + atributos[0].count + " Valor: " + atributos[0].m.get("limpio").toString());
                //System.out.println("tabla: " + table.get(2)[1]);
	}
        
        public static void entropy(){
            
            for (int i=0; i<atributos.length ; i++){
                
                double ent = 0;
                double log = 0;
                int ocuInd;
                
                System.out.println("Atributo " + atributos[i].name);
                //Iterando el mapa de cada atributo
                Iterator it = atributos[i].m.entrySet().iterator();
                while (it.hasNext()){
                    
                    Map.Entry pair = (Map.Entry)it.next();
                    //Guardando el valor de cada entrada en el mapa, asi como su ocurrencia
                    String val = pair.getKey().toString();
                    int ocu = Integer.parseInt(pair.getValue().toString());
                    
                    //Iterar la columna de evaluacion para saber que resultados se buscan
                    Iterator it2 = atributos[atributos.length-1].m.entrySet().iterator();
                    log = 0;
                    while(it2.hasNext()){
                        
                        ocuInd = 0;
                    
                        Map.Entry pair2 = (Map.Entry)it2.next();
                        String eval = pair2.getKey().toString();
                        
                        for(int j=0; j<table.size(); j++){
                            
                            //Contar ocurrencias individuales (de c/valor posible de evaluacion) para calcular logaritmos
                            if(table.get(j)[i].equals(val) && table.get(j)[table.get(0).length-1].equals(eval)){
                                ocuInd++;
                            }
                            
                            //it2.remove();
                            
                        }
                        
                        log += ((double)ocuInd/ocu) * log(((double)ocuInd/ocu),2);
                        
                    }
                    
//                    if(i==atributos.length-1){
//                        for(int j=0; j<atributos.length; j++){
//                            atributos[j].eval.add(val);
//                        }
//                    }
                    
                    it.remove();
                    //System.out.println("Val: " + val + " Ocu: " + ocu);
                    
                    //Calcular entropia segun el numero de posibles valores y el total de datos en el atributo
                    ent += ((double)ocu/(double)atributos[i].count) * (-log);
                    
                }
                
                atributos[i].setEntropy(ent);
                System.out.println(ent);
            
            }
        
        }

	public static void main(String[] args) throws IOException {
            
            input();
            entropy();
            
	}

}
