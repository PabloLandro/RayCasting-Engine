package Game;


import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author landr
 */
public class Error extends JOptionPane{
    
    public Error(String descripcion){
        super();
        this.showMessageDialog(null, descripcion, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
