/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import raycasting.Render;

/**
 *
 * @author landr
 */
public class RenderFrame extends Frame{
    
    public RenderFrame(Render render){
        super(render);
    }
    
    public Render getRender(){
        return (Render)this.getPanel();
    }
    
}
