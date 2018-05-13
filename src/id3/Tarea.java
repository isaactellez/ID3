/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id3;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Tarea {
            
    public static void main(String[] args) throws IOException {
        int numeroAtributos = 4;
        //int j = 0;
        boolean exit = false;
        ArrayList<ID3> listado = new ArrayList<>();
        ArrayList<ID3> copiaListado;
            
        ID3 id3 = new ID3();       
        id3.input();
        id3.entropy();
        listado.add(id3);
        
        copiaListado = listado;
        
        while(!exit) {
            for (ID3 i : copiaListado) {
                if(!i.finished) {
                    for (String atributo : i.porAbrir) {
                        System.out.println(atributo);
                        ID3 tabla = new ID3();
                        tabla.input(atributo);
                        tabla.entropy();
                        tabla.father = atributo;
                        listado.add(tabla);
                    }
                }
                i.finished = true;
            }
            
            exit = true;
            
            for (ID3 j : listado) {
                exit = exit && j.finished;
            }

            copiaListado = listado;
        }
    }
}
