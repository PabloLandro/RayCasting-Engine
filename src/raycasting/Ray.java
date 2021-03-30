/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import java.awt.Color;
import static raycasting.RayOperations.dist;

/**
 *
 * @author landr
 */
public class Ray {
    
    private int px, py, rx, ry;
    private Color color;
    private boolean shade;
    
    public Ray(int px, int py, boolean shade){
        this.px = px;
        this.py = py;
        this.shade = shade;
    }
    
    public void update(int rx, int ry){
        this.rx = rx;
        this.ry = ry;
    }
    
    public void transform(int rx, int ry){
        this.rx += rx;
        this.ry += ry;
    }
    
    public void setColor(Color color){
        this.color = color;
    }
    public Color getColor(){
        return color;
    }
    
    public int getRX(){
        return rx;
    }
    public int getRY(){
        return ry;
    }
    public int getPX(){
        return px;
    }
    public int getPY(){
        return py;
    }
    
    public float getDist(){
        return dist(px, py, rx, ry);
    }
    public boolean getShade(){
        return shade;
    }
    
}
