package id3;

import java.util.*;

public class Attribute {
    String name;
    Map<String, Integer> m;
    double entropy;
    int count = 0;

    Attribute(String n){
        
        name = n;
        m = new HashMap<>();
        entropy = 1000;
        
    }
    
    public void addValue(String v){
        
        if(!m.containsKey(v)){
            m.put(v, 1);
        }else{
            m.put(v, m.get(v) + 1);
        }
        
        count++;
        
    }
    
    public void setEntropy(double ent){
        
        entropy = ent;
        
    }
    
}
