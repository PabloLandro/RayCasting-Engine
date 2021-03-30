/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Mapping.Map;
import Mapping.MapLoader;
import javax.swing.JPanel;

/**
 *
 * @author landr
 */
public class MapFrame extends Frame{
    
    public MapFrame(String direccion) {
        super(new Map(new MapLoader(direccion)));
    }
    public MapFrame(Map map){
        super(map);
    }
    
    public Map getMap(){
        return (Map)this.getPanel();
    }
    
}
