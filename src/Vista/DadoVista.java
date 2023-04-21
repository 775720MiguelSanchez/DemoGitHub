/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

import Control.OyenteVista;

/**
 *
 * @author Miguel
 */
public class DadoVista extends JLabel {

    private int id;
    private int tiradaActual;
    private JuegoVista juegoVista;
    private String color;
    private int posicionx;
    private int posiciony;

    public DadoVista(JuegoVista juegoVista, int id, boolean recibeEventosRaton, String color, int posicionx, int posiciony) {
        this.id = id;
        tiradaActual = 6;
        this.juegoVista = juegoVista;
        this.color = color;
        this.posicionx = posicionx;
        this.posiciony = posiciony;
        setEnabled(false);
    }
    
        public int getId() {
        return id;
    }

    public int getTiradaActual() {
        return tiradaActual;
    }

    public JuegoVista getJuegoVista() {
        return juegoVista;
    }

    public String getColor() {
        return color;
    }

    public int getPosicionx() {
        return posicionx;
    }

    public int getPosiciony() {
        return posiciony;
    }

    public void setTiradaActual(int tirada){
        tiradaActual = tirada;
    }
    
}
