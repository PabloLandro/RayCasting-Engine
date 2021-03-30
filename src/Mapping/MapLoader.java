/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapping;

import java.awt.Color;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author landr
 */
public class MapLoader {
    
    String direccion;
    private int width;
    private int height;
    private int unit_size;
    int[] map;
    ColorMap colorMap;
    ArrayList<Integer> colliders;
    
    public MapLoader(String direccion){
        colorMap = new ColorMap();
        this.direccion = direccion;
        colliders = new ArrayList<>();
        load();
    }
    
    private void load(){
        try{
            File file = new File(direccion);
            FileReader lector = new FileReader(file);
            BufferedReader bf = new BufferedReader(lector);
            String linea;
            int aux = 0;
            int line = 0;
            while((linea = bf.readLine()) != null){
                String[] partes = linea.split(",");
                if(line == 0){
                    width = Integer.parseInt(partes[0]);
                    height = Integer.parseInt(partes[1]);
                    unit_size = Integer.parseInt(partes[2]);
                    map = new int[width*height];
                    line++;
                }
                else if(aux < height){
                    for(int i = 0; i < width; i++){
                        map[i+aux*width] = Integer.parseInt(partes[i]);
                    }
                    aux++;
                    line++;
                }
                else{
                    colorMap.addColor(Integer.parseInt(partes[0]), new Color(Float.parseFloat(partes[1]), Float.parseFloat(partes[2]), Float.parseFloat(partes[3])));
                    colliders.add(Integer.parseInt(partes[0]));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public int[] getMap(){
        return map;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public ColorMap getColorMap(){
        return colorMap;
    }
    public int getUnitSize(){
        return unit_size;
    }
    public ArrayList<Integer> getColliders(){
        return colliders;
    }
    
}
