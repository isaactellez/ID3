package id3;

import java.util.*;

public class Attribute {
    String nombre;
    Map<String, Integer> m;
    double entropy;
        
    public void Atributo(String n){
        
        nombre = n;
        m = new HashMap<>();
        entropy = 1000;
        
    }
    
    public void addValue(String v){
        
        if(!m.containsKey(v)){
            m.put(v, 0);
        }else{
            m.put(v, m.get(v) + 1);
        }
        
    }
    
    public double getEntropy(){
        
        double ent=0;
        
        
        
        return ent;
        
    }
    
}
