/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author msanb
 */
public enum Color {
    ROJO ("ROJO"),
    AMARILLO ("AMARILLO"),
    AZUL ("AZUL"),
    VERDE ("VERDE");
    
    private final String color;
    
    Color(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }
    
    
}
