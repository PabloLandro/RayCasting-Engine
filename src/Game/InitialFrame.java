/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Mapping.Map;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author landr
 */
public class InitialFrame extends JFrame{
    
    private final static int WIDTH = 420;
    private final static int HEIGHT = 420;
    private final static int BUTTON_WIDTH = 200;
    private final static int BUTTON_HEIGHT = 40;
    
    JButton fileButton;
    JButton playButton;
    JButton viewButton;
    
    ArrayList<JButton> buttons;
    
    Scene scene;
    
    public InitialFrame(){
        setUpComponents();
        addComponents();
        setUp();
    }
    private void setUpComponents(){
        fileButton = new JButton("Open a World");
        playButton = new JButton("Play World");
        viewButton = new JButton("View World");
        fileButton.addActionListener((e)->exploreFiles());
        playButton.addActionListener((e)->openScene());
        viewButton.addActionListener((e)->viewScene());
        buttons = new ArrayList<>();
        buttons.add(fileButton);
        buttons.add(playButton);
        buttons.add(viewButton);
        setUpButtons();
    }
    
    private void setUpButtons(){
        for(JButton button: buttons){
            button.setFocusable(false);
            button.setPreferredSize(new Dimension(200, 40));
        }
    }
    private void addButtons(){
        for(JButton button: buttons){
            this.getContentPane().add(button);
        }
    }
    private void addComponents(){
        addButtons();
    }
    private void setUp(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("RayCasting Engine");
    }
    
    private void exploreFiles(){
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showOpenDialog(this);
        if(response == JFileChooser.APPROVE_OPTION){
            scene = new Scene(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }
    
    private void openScene(){
        if(checkSceneExists())
            scene.play();
    }
    
    private void viewScene(){
        if(checkSceneExists())
           new MapFrame((Map)scene.getDisplay());
    }
    
    private boolean checkSceneExists(){
        if(scene == null){
            new Error("Seleccione un mapa");
            return false;
        }
        return true;
    }
    
}
