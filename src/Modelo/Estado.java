/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author msanb
 */
public enum Estado {
    CASA ("CASA"),
    TABLERO ("TABLERO"),
    TUBERIA ("TUBERIA");
    
    private final String estado;
    
    Estado(String estado){
        this.estado = estado;
    }
}
