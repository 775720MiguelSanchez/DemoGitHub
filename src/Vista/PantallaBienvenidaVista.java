package Vista;


import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Miguel
 */
public class PantallaBienvenidaVista extends JPanel{
    private JuegoVista juegoVista;
    
    public PantallaBienvenidaVista(JuegoVista juegoVista){
        this.juegoVista = juegoVista;
        this.setLayout(new BorderLayout());
        JLabel img = new JLabel("");
        ImageIcon image = new ImageIcon("src/imagenes/Bienvenida.png");
        img.setIcon(image);
        img.setVisible(true);
        this.add(img);
    }
    
}
