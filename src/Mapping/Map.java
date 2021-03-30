/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapping;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import raycasting.*;

/**
 *
 * @author landr
 */
public class Map extends JPanel implements ActionListener{
    
    private final static int UNIT_SIZE = 64;
    private final static int PANEL_SIZE = 420;
    private final static int DELAY = 40;
    private int NUM_UNITS;
    private int DISPLAY_SIZE;//PANEL_SIZE/NUM_UNITS
    private Player player;
    private Timer timer;
    private ColorMap colorMap;
    private int[] map;
    private ArrayList<Integer> colliders;
    
    
    public Map(MapLoader loader){
        setUpMap(loader);
        setUpPanel();
    }
    
    private void setUpMap(MapLoader loader){
        map = loader.getMap();
        NUM_UNITS = loader.getWidth();
        colorMap = loader.getColorMap();
        colliders = loader.getColliders();
        DISPLAY_SIZE = PANEL_SIZE/NUM_UNITS;
    }
    
    private void setUpPanel(){
        //this.setPreferredSize(new Dimension(WIDTH_UNITS*UNIT_SIZE, HEIGHT_UNITS*UNIT_SIZE));
        this.setPreferredSize(new Dimension(PANEL_SIZE, PANEL_SIZE));
        this.setBackground(Color.black);
        this.setFocusable(true);
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public static int getUnitSize(){
        return UNIT_SIZE;
    }
    public int getDisplaySize(){
        return DISPLAY_SIZE;
    }
    
    public boolean isCollider(int index){
        if(index < 0 || index >= NUM_UNITS*NUM_UNITS)
            return false;
        else{
            for(Integer i: colliders)
                if(i.intValue() == map[index])
                    return true;
        }
        return false;
    }
    public int getTile(double x, double y){
        return (int)(x/UNIT_SIZE) + (int)(y/UNIT_SIZE) * NUM_UNITS;
    }
    public int getTileValue(double x, double y){
        if(getTile(x, y) >= NUM_UNITS*NUM_UNITS || getTile(x, y) < 0)
            return 0;
        else
            return map[getTile(x, y)];
    }
    public Color getTileColor(int x, int y){
        return colorMap.getColor(map[x+y*NUM_UNITS]);
    }
    public Color getTileColor(int index){
        if(index < 0 || index > NUM_UNITS*NUM_UNITS)
            return Color.white;
        return colorMap.getColor(map[index]);
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    private void draw(Graphics g){
        for(int i = 0; i < NUM_UNITS; i++){
            //g.drawLine(0, i*UNIT_SIZE, PANEL_WIDTH, i*UNIT_SIZE);
            for(int j = 0; j < NUM_UNITS; j++){
                //g.drawLine(j*UNIT_SIZE, 0, j*UNIT_SIZE, PANEL_HEIGHT);
                if(map[j + i*NUM_UNITS] != 2 && map[j+i*NUM_UNITS] != 0){
                    g.setColor(getTileColor(j, i));
                    g.fillRect(j*DISPLAY_SIZE, i*DISPLAY_SIZE, DISPLAY_SIZE, DISPLAY_SIZE);
                }
                else if(map[j + i*NUM_UNITS] == 2){
                    if(player != null)
                        player.draw(g);
                }
            }
        }
    }
    
    public void createPlayers(){
        for(int i = 0; i < NUM_UNITS; i++){
            for(int j = 0; j < NUM_UNITS; j++){
                if(map[j + i*NUM_UNITS] == 2){
                    player = new Player(j*UNIT_SIZE, i*UNIT_SIZE);
                    this.addKeyListener(player.getKeyAdapter());
                    player.setMap(this);
                }
            }
        }
    }
    
    
    
    public boolean collides(MyObject obj, int moveX, int moveY){
        int x = (int)obj.getX() + moveX;
        int y = (int)obj.getY() + moveY;
        int[] tiles = new int[4];
        tiles[0] = getTile(x, y);
        tiles[1] = getTile(x + obj.getWidth(), y);
        tiles[2] = getTile(x, y + obj.getHeight());
        tiles[3] = getTile(x + obj.getWidth(), y + obj.getHeight());
        for(int i = 0; i < tiles.length; i++)
            if(isCollider(tiles[i]))
                return true;
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        repaint();
        if(player != null)
            player.move();
    }
    
}
