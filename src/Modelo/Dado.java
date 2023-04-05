/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Random;

/**
 *
 * @author msanb
 */
public class Dado {
    private int tirada;
    
    public Dado(){
        tirada = 0;
    }

    public int getTirada() {
        return tirada;
    }
   
    public int tirar(){
        Random numeroAleatorio = new Random();
        tirada = numeroAleatorio.nextInt(6-1+1)+1;
        return tirada;
    }
}
