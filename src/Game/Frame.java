/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author landr
 */
public abstract class Frame extends JFrame{
    
    JPanel panel;
    
    public Frame(JPanel panel){
        this.add(panel);
        this.panel = panel;
        setUp();
    }
    
    private void setUp(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public JPanel getPanel(){
        return panel;
    }
    
}
