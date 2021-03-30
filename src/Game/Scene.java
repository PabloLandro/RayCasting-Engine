/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Mapping.*;
import javax.swing.JPanel;

/**
 *
 * @author landr
 */
public class Scene {
    
    String direccion;
    
    public Scene(String direccion){
        this.direccion = direccion;
    }
    
    public void play(){
        MapFrame mapFrame = new MapFrame(direccion);
        mapFrame.getMap().createPlayers();
    }
    public JPanel getDisplay(){
        Map map = new Map(new MapLoader(direccion));
        return map;
    }
    
}
