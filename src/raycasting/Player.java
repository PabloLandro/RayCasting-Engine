/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import Game.RenderFrame;
import Mapping.Map;
import java.awt.*;
import java.awt.event.*;
import static raycasting.RayOperations.*;

/**
 *
 * @author landr
 */
public class Player extends MyObject{
    
    private final static int SPEED = 10;
    private final static float ROT_SPEED = 0.11f;
    public final static int DOF = 15;//Depth Of Field
    private final static Color rayColor = Color.green;
    private final static int RAYS = 90;
    
    private int[] dirs;
    private KeyAdapter adapter;
    private double rotation = 0;
    
    private Render render;
    
    
    public Player(int x, int y) {
        super(x, y, 32);
        setUp();
    }
    
    private void setUp(){
        dirs = new int[4];
        adapter = new MyKeyAdapter();
        render = new Render();
        new RenderFrame(render);
    }
    
    public KeyAdapter getKeyAdapter(){
        return adapter;
    }
    public Map getMap(){
        return map;
    }
    
    public void draw(Graphics g){
        Color color = g.getColor();
        g.setColor(this.getColor());
        g.fillRect(x*map.getDisplaySize()/map.getUnitSize(), y*map.getDisplaySize()/map.getUnitSize(), width*map.getDisplaySize()/map.getUnitSize(), height*map.getDisplaySize()/map.getUnitSize());
        drawRay(g);
        g.setColor(color);
    }
    
    private void drawRay(Graphics g){
        
        double rot = normalisedRotation(rotation-degreesToRadians(RAYS/2));
        float n = Render.WIDTH / (RAYS);
        RenderedRect[] rects = new RenderedRect[RAYS];
        for(int i = 0; i < RAYS; i++){
            Ray ray = castRay(this, rot);
            g.setColor(ray.getColor());
            g.drawLine(ray.getPX()*map.getDisplaySize()/map.getUnitSize(), ray.getPY()*map.getDisplaySize()/map.getUnitSize(), ray.getRX()*map.getDisplaySize()/map.getUnitSize(), ray.getRY()*map.getDisplaySize()/map.getUnitSize());
            double angleOffset = normalisedRotation(rotation-rot);
            float dist  = ray.getDist()*(float)Math.cos(angleOffset);//Solve FishEye
            float lineHeight = Math.min(Render.HEIGHT, map.getUnitSize() * Render.HEIGHT / dist);
            float lineOffset = (Render.HEIGHT - lineHeight)/2;
            rects[i] = new RenderedRect((int)(i*n), (int)lineOffset, (int)n, (int)lineHeight, ray.getColor(), ray.getShade(), ray.getRX());
            rot = normalisedRotation(rot+degreesToRadians(1));
        }
        render.setRectangles(rects);
        render.repaint();
        
    }
    
    
    public void move(){
        
        rotation += (dirs[1] - dirs[3]) * ROT_SPEED;
        rotation = normalisedRotation(rotation);
        
        int moveX = (int)((dirs[0] - dirs[2]) * SPEED * Math.cos(rotation));
        int moveY = (int)((dirs[0] - dirs[2]) * SPEED * Math.sin(rotation));
        
        if(!getMap().collides(this, moveX, 0))
            x += moveX;
        if(!getMap().collides(this, 0, moveY))
            y += moveY;
    }

    
    private class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyChar()){
                case 'W': case 'w':
                    dirs[0] = 1;
                    break;
                case 'D': case 'd':
                    dirs[1] = 1;
                    break;
                case 'S': case's':
                    dirs[2] = 1;
                    break;
                case 'A': case'a':
                    dirs[3] = 1;
                    break;
            }
        }
        @Override
        public void keyReleased(KeyEvent e){
            switch(e.getKeyChar()){
                case 'W': case 'w':
                    dirs[0] = 0;
                    break;
                case 'D': case 'd':
                    dirs[1] = 0;
                    break;
                case 'S': case's':
                    dirs[2] = 0;
                    break;
                case 'A': case'a':
                    dirs[3] = 0;
                    break;
            }
        }
    }
    
}
