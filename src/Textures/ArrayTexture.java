/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Textures;

import Mapping.Map;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author landr
 */
public abstract class ArrayTexture extends TextureType{
    
    int[] mapping;
    ArrayList<Color> colors;
    int width, height;
    
    public ArrayTexture(int width, int height){
        this.width = width;
        this.height = height;
        this.mapping = setMapping();
        this.colors = setColors();
    }
    
    abstract int[] setMapping();
    abstract ArrayList<Color> setColors();
    
    @Override
    public Color getColor(int i, int j, int width, int height){
        return colors.get(mapping[(int)((float)i*(float)this.width/(float)Map.getUnitSize())+(int)(this.width*((float)j*(float)this.height/(float)height))]);
    }
    
}
