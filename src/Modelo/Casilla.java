/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Modelo.TipoCasilla;

/**
 *
 * @author msanb
 */
public class Casilla {
    private int id;
    private TipoCasilla tipo;
    private int numFichas;
    
    public Casilla(int id, TipoCasilla tipo, int numFichas){
        this.id = id;
        this.tipo = tipo;
        this.numFichas = numFichas;
        
    }

    public int getNumFichas() {
        return numFichas;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoCasilla getTipo() {
        return tipo;
    }

    public void setTipo(TipoCasilla tipo) {
        this.tipo = tipo;
    }
    
    public void anyadirFichas(){
        numFichas++;         
    }
    
    public void quitarFichas(){
        numFichas--;         
    }
    
}
