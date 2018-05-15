/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 *
 * @author marco
 */
public class Tarea {
            
    public static void main(String[] args) throws IOException {
        //int j = 0;
        boolean exit = false;
        ArrayList<ID3> listado = new ArrayList<>();
        ArrayList<ID3> copiaListado;
            
        ID3 id3 = new ID3();       
        id3.input();
        id3.entropy();
        listado.add(id3);
        ID3 i;
        
        copiaListado = new ArrayList(listado);
        
        Stack pila = new Stack();
        
        while(!exit) {
            for (int ii = 0; ii < copiaListado.size(); ii++) {
                i = copiaListado.get(ii);
                if(!i.finished) {
                    for (String atributo : i.porAbrir) {
                        //System.out.println(atributo);
                        ID3 tabla = new ID3();
                        tabla.input(atributo,i.noEscogidos);
                        tabla.entropy();
                        tabla.father = atributo;
                        tabla.papa = i;
                        listado.add(tabla);
                    }
                }
                i.finished = true;
                
                //System.out.println(i.chosenOne.name);
            }
            
            exit = true;
            
            for (ID3 j : listado) {
                exit = exit && j.finished;
            }

            copiaListado = new ArrayList(listado);
        }
        
        System.out.println("\n" + "Reglas de producción:");
        
        for (ID3 id : listado) {
            Iterator it = id.arbolito.entrySet().iterator();
            ArrayList<Object> lista;
            
            while(it.hasNext()) {
                Map.Entry par = (Map.Entry)it.next();
                lista = (ArrayList<Object>) par.getValue();
                
                if((boolean)lista.get(0)) {
                    pila.push("\n");
                    //System.out.print(lista.get(1).toString());
                    pila.push(lista.get(1).toString());
                    //System.out.print(" <- ");
                    pila.push(" -> ");
                    //System.out.print("(");
                    //System.out.print(par.getKey().toString());
                    pila.push(par.getKey().toString());
                    //System.out.print(" = ");
                    pila.push(" = ");
                    //System.out.print(id.chosenOne.name);
                    pila.push(id.chosenOne.name);
                    
                    ID3 aux = new ID3();
                    aux = id;
                    
                    while(aux.papa != null) {
                        //System.out.print(" ^ ");
                        pila.push(" ^ ");
                        //System.out.print(aux.father);
                        pila.push(aux.father);
                        //System.out.print(" = ");
                        pila.push(" = ");
                        //System.out.print(aux.papa.chosenOne.name);
                        pila.push(aux.papa.chosenOne.name);
                        aux = aux.papa;
                        //System.out.print("");
                    }
                    
                    //System.out.print(" IF ");
                    pila.push("IF ");
                    //System.out.println("\n");
            
                    while(!pila.empty()) {
                        System.out.print(pila.pop());
                    }
                }
            }
        }
    }
}
