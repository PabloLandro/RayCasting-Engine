/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapping;

import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author landr
 */
public class ColorMap {
    
    private HashMap<Integer, Color> mapping;
    
    public ColorMap(){
        mapping = new HashMap<>();
    }
    
    public void addColor(int i, Color color){
        mapping.put(i, color);
    }
    
    public Color getColor(int i){
        for(Integer j: mapping.keySet())
            if(j.intValue() == i)
                return mapping.get(j);
        return Color.white;
    }
    
}
