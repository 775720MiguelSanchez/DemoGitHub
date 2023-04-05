/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author msanb
 */
public class Jugador {

    private static final int NUM_FICHAS = 4;

    private String nombre;
    private Ficha ultimaFicha;
    private Dado dado;
    private Ficha[] fichas;
    private Color color;
    private int posicionInicial;

    public Jugador(String nombre, Color color, int posicionInicial) {
        this.nombre = nombre;
        this.posicionInicial = posicionInicial;
        fichas = new Ficha[NUM_FICHAS];
        crearFichas();
        this.color = color;
        this.dado = new Dado();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ficha getUltimaFicha() {
        return ultimaFicha;
    }

    public void setUltimaFicha(Ficha ultimaFicha) {
        this.ultimaFicha = ultimaFicha;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public Ficha[] getFichas() {
        return fichas;
    }

    public Ficha getFicha(int idFicha) {
        for (int i = 0; i < fichas.length; i++) {
            if (fichas[i].getId() == idFicha) {
                return fichas[i];
            }
        }
        return null;

    }

    public void setFichas(Ficha[] fichas) {
        this.fichas = fichas;
    }

    public int tirarDado() {
        return dado.tirar();
    }

    public Color getColor() {
        return color;
    }

    private void crearFichas() {
        for (int i = 0; i < NUM_FICHAS; i++) {
            fichas[i] = new Ficha(posicionInicial, Estado.CASA, i, color);
        }
    }

    public void moverFicha(int id, int nuevaPosicion) {
        fichas[id].setPosicion(nuevaPosicion);
    }

}
