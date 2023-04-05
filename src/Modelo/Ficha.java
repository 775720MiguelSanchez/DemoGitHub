/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author msanb
 */
public class Ficha {
    private int posicion;
    private Estado estado;
    private int id;
    private Color color;
    
    public Ficha(int posicion, Estado estado, int id, Color color){
        this.posicion = posicion;
        this.estado = estado;
        this.id = id;
        this.color = color;
    }

    public int getId(){
        return id;
    }
    
    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }  
}
