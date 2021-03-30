/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author landr
 */
public class Render extends JPanel{
    
    public final static int WIDTH = 1080;
    public final static int HEIGHT = 720;
    
    private RenderedRect[] rects;
    
    public Render(){
        setUp();
    }
    private void setUp(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
    }
    
    public void setRectangles(RenderedRect[] rects){
        this.rects = rects;
    }
    
    private void paintRectangles(Graphics g){
        if(rects!=null)
            for(RenderedRect rect: rects){
                rect.draw(g);
            }
    }
    
    private void paintBackground(Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(0, HEIGHT/2, WIDTH, HEIGHT/2);
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, WIDTH, HEIGHT/2);
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintBackground(g);
        paintRectangles(g);
    }
    
}
