/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raycasting;

import Mapping.Map;
import java.awt.Color;
import raycasting.*;
import static raycasting.Player.DOF;

/**
 *
 * @author landr
 */
public abstract class RayOperations {
    public static float dist(float ax, float ay, float bx, float by){
        return (float)Math.sqrt((ax-bx)*(ax-bx) + (ay-by)*(ay-by));
    }
    public static double normalisedRotation(double rot){
        if(rot < 0)
            rot += 2*Math.PI;
        else if(rot > 2*Math.PI)
            rot -= 2*Math.PI;
        return rot;
    }
    public static double degreesToRadians(int degrees){
        return normalisedRotation(0.0174533 * degrees);
    }
    
    public static Ray castRay(Player player, double rot){
        
        Ray horizontal = castHorizontalRay(player, rot);
        Ray vertical = castVerticalRay(player, rot);
        
        if(vertical.getDist() < horizontal.getDist())
            return vertical;
        else
            return horizontal;
    }
    
    private static Ray castHorizontalRay(Player player, double rot){
        
        int px = (int)player.getCenterX();
        int py = (int)player.getCenterY();
        
        Ray ray = new Ray((int)player.getCenterX(), (int)player.getCenterY(), false);
        
        float x0 = 0, y0 = 0;
        
        //---CHECK-HORIZONTAL-LINES---
        float atan = (float) (-1 / Math.tan(rot));
        int dof = 0;
        float hx = px, hy = py;
        if (rot > Math.PI) {//Looking up
            hy = py - (py % player.getMap().getUnitSize()) - 0.0001f;//Top line y
            hx = (float) ((py - hy) * atan) + px;
            y0 = -player.getMap().getUnitSize();
            x0 = (float) (-y0 * atan);
        }
        else if (rot < Math.PI) {//Looking down
            hy = py - (py % player.getMap().getUnitSize()) + player.getMap().getUnitSize();//Bottom line y
            hx = (float) ((py - hy) * atan) + px;
            y0 = player.getMap().getUnitSize();
            x0 = (float) (-y0 * atan);
        }
        else if (rot == 0 || rot == Math.PI)
            dof = DOF;
        while (dof < DOF) {
            if (player.getMap().isCollider(player.getMap().getTile(hx, hy)))//Hit a wall
                dof = DOF;
            else{//Next line
                hx += x0; hy += y0; dof++;
            }
        }
        ray.update((int)hx, (int)hy);
        ray.setColor(player.getMap().getTileColor(player.getMap().getTile(hx, hy)));
        return ray;
    }
    
    private static Ray castVerticalRay(Player player, double rot){
        
        int px = (int)player.getCenterX();
        int py = (int)player.getCenterY();
        
        Ray ray = new Ray(px, py, true);
        
        float x0 = 0, y0 = 0, vx = px, vy = py;
        float ntan = (float) -Math.tan(rot);
        int dof = 0;
        
        //---CHECK-VERTICAL-LINES---
        
        if (rot > (Math.PI / 2) && rot < 3 * Math.PI / 2) {//Looking left
            vx = px - (px % player.getMap().getUnitSize()) - 0.0001f;//Top line y
            vy = (float) ((px - vx) * ntan) + py;
            x0 = -player.getMap().getUnitSize(); y0 = (float) (-x0 * ntan);
        }
        else if (rot < (Math.PI / 2) || rot > (3 * Math.PI / 2)) {//Looking right
            vx = px - (px % player.getMap().getUnitSize()) + player.getMap().getUnitSize();//Bottom line y
            vy = (float) ((px - vx) * ntan) + py;
            x0 = player.getMap().getUnitSize(); y0 = (float) (-x0 * ntan);
        }
        else if (rot == 0 || rot == Math.PI)
            dof = DOF;

        while (dof < DOF) {
            if (player.getMap().isCollider(player.getMap().getTile(vx, vy)))//Hit a wall
                dof = DOF;
            else {//Next line
                vx += x0; vy += y0; dof++;
            }
        }
        
        ray.update((int)vx, (int)vy);
        ray.setColor(player.getMap().getTileColor(player.getMap().getTile(vx, vy)).darker());
        return ray;
    }
}
