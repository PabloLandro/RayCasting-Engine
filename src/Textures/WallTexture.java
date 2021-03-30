/*
*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Textures;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author landr
 */
public class WallTexture extends ArrayTexture{
    
   public WallTexture(){
       super(2, 2);
   }

    @Override
    int[] setMapping() {
        int[] mapping = {0,1,
                         1,0};
        return mapping;
    }

    @Override
    ArrayList<Color> setColors() {
        ArrayList<Color> out = new ArrayList<>();
        out.add(Color.white);
        out.add(Color.black);
        return out;
    }
    
}
