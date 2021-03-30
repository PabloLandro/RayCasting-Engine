/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import Mapping.Map;
import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author landr
 */
public abstract class MyObject extends Rectangle{
    
    private Color color = Color.red;
    Map map;
    
    MyObject(int x, int y, int size){
        super(x, y, size, size);
    }
    
    public Color getColor(){
        return color;
    }
    
    public Map getMap(){
        return map;
    }
    public void setMap(Map map){
        this.map = map;
    }
    
    
}
