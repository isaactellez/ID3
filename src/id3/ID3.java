package id3;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ID3 {

	public static void entrada () throws IOException {
            
		int n;
                List<Map<String,Integer>> maps = new ArrayList<>();
                String[] atributos;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Dame el numero de atributos del data set (en orden):");
		n = Integer.parseInt(br.readLine());
                atributos = new String[n];

		for(int i=0; i<n ;i++){
                    
                    System.out.println("Dame el atributo " + i + ":");
                    String a = br.readLine();
                    
                    maps.add(new HashMap<>());
                    atributos[i] = a;
                        
                    //maps.get(i).put(a, 0);
                    
		}
                
                System.out.println("Dame el nombre de archivo donde se encuentra el data set:");
                //Acceder a archivo RAF?
                

	}

	public static void main(String[] args) throws IOException {
            
            entrada();
            
	}

}