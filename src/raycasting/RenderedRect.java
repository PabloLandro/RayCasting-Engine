/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import Mapping.Map;
import Textures.TextureType;
import Textures.WallTexture;
import java.awt.Color;
import java.awt.Graphics;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author landr
 */
public class RenderedRect extends Rectangle{
    
    private Color color;
    private double angle;
    private final static TextureType texture = new WallTexture();
    private boolean shade;
    
    private int texturex;
    
    public RenderedRect(int x, int y, int width, int height, Color color, boolean shade, int texturex){
        super(x, y, width, height);
        this.color = color;
        this.angle = angle;
        this.shade = shade;
        this.texturex = texturex%Map.getUnitSize();
    }
    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect((int)this.getX(), (int)this.getY(), (int)this.getWidth(), (int)this.getHeight());
        /*
        for(int i = 0; i < this.getWidth(); i++){
            for(int j = 0; j < this.getHeight(); j++){
                g.setColor(texture.getColor((texturex+i)%Map.getUnitSize(), j, (int)this.getWidth(), (int)this.getHeight()));
                if(shade)
                g.setColor(g.getColor().darker());
                g.fillRect((int)this.getX()+i, (int)this.getY()+j, 1, 1);
            }
        }
*/
    }
}
