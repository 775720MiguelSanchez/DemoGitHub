/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.google.gson.Gson;
import Modelo.TipoCasilla;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author msanb
 */
public class Juego {

    public static String TIRAR_DADO = "tirar dado";
    public static String MOVER_FICHA = "mover ficha";
    public static String INICIALIZAR_FICHAS = "iniciar fichas";
    public static String NUEVA_PARTIDA = "nueva partida";
    private PropertyChangeSupport observadores;
    private Jugador[] jugadores;
    private Casilla[] tablero;
    private int estadoJuego;
    private String turno;
    private int numJugadores;

    private static final int MAXIMO_CASILLAS = 200;

    public Juego() {
//        this.numJugadores = numJugadores;
//        jugadores = new Jugador[numJugadores];
//        tablero = new Casilla[MAXIMO_CASILLAS];
        observadores = new PropertyChangeSupport(this);
//        estadoJuego = 0;
//        turno = "ROJO";
        crearPartida(2);
    }

    public void nuevoObservador(PropertyChangeListener observador) {
        this.observadores.addPropertyChangeListener(observador);
    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public void setJugadores(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public Casilla[] getTablero() {
        return tablero;
    }

    public void setTablero(Casilla[] tablero) {
        this.tablero = tablero;
    }

    private void crearJugadores(int numJugadores) {
        Color[] colores = new Color[4];
        colores = Color.values();
        for (int i = 0; i < numJugadores; i++) {
            jugadores[i] = new Jugador(String.valueOf(i), colores[i], obtenerPosicionInicial(colores[i]));
        }
    }

    private int obtenerPosicionInicial(Color color) {
        String tipo = "CASA_" + color;
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i].getTipo().getTipo().equals(tipo)) {
                return tablero[i].getId();
            }
        }
        return -5;
    }

    private void crearCasillas() {
        tablero[0] = new Casilla(-1, TipoCasilla.CASA_ROJO, 0);
        tablero[1] = new Casilla(-2, TipoCasilla.CASA_AZUL, 0);
        tablero[2] = new Casilla(-3, TipoCasilla.CASA_AMARILLO, 0);
        tablero[3] = new Casilla(-4, TipoCasilla.CASA_VERDE, 0);
        tablero[4] = new Casilla(0, TipoCasilla.META, 0);
        for (int i = 5; i < tablero.length; i++) {
            if (i - 4 == 56) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.SEGURO_VERDE, 0);
            } else if (i - 4 == 5) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.SEGURO_AMARILLO, 0);
            } else if (i - 4 == 22) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.SEGURO_AZUL, 0);
            } else if (i - 4 == 39) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.SEGURO_ROJO, 0);
            } else if (i - 4 == 46 || i - 4 == 51 || i - 4 == 63 || i - 4 == 68 || i - 4 == 12 || i - 4 == 17 || i - 4 == 29 || i - 4 == 34) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.SEGURO, 0);
            } else if (i - 4 > 69 && i - 4 < 77) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.META_VERDE, 0);
            } else if (i - 4 >= 77 && i - 4 < 84) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.META_AZUL, 0);
            } else if (i - 4 >= 84 && i - 4 < 91) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.META_ROJO, 0);
            } else if (i - 4 >= 91 && i - 4 < 98) {
                tablero[i] = new Casilla(i - 4, TipoCasilla.META__AMARILLO, 0);
            } else {
                tablero[i] = new Casilla(i - 4, TipoCasilla.CASILLA, 0);
            }
        }
    }

    public Casilla buscarCasilla(int id) {
        for (int i = 0; i < tablero.length; i++) {
            if (id == tablero[i].getId()) {
                return tablero[i];
            }
        }
        return null;
    }

    public Jugador buscarJugador(String nombre) {
        for (int i = 0; i < jugadores.length; i++) {
            if (nombre.equals(jugadores[i].getNombre())) {
                return jugadores[i];
            }
        }
        return null;
    }

    public Jugador buscarJugadorColor(String color) {
        for (int i = 0; i < jugadores.length; i++) {
            if (color.equals(jugadores[i].getColor().getColor())) {
                return jugadores[i];
            }
        }
        return null;
    }

    public void pasarFichas() { // si usamos el property change es void :)
        Gson gson = new Gson();
        String datos = "";
        Ficha fichas[];
        int j;
        for (int i = 0; i < jugadores.length; i++) {
            j = 0;
            fichas = jugadores[i].getFichas();
            while (j < fichas.length) {
                datos = datos + gson.toJson(fichas[j]);
                j++;
            }
        }
        System.out.println(datos); // pasarselo al property change

    }

    private boolean comprobarTurno(String color) {
        if (color.equals(turno)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean maquinaEstado(int estado) {
        return estado == estadoJuego;
    }

    public void crearPartida(int numJugadores) {
        this.numJugadores = numJugadores;
                tablero = new Casilla[MAXIMO_CASILLAS];
        crearCasillas();
        jugadores = new Jugador[numJugadores];
        crearJugadores(numJugadores);
        estadoJuego = 1;
        turno = "ROJO";
        observadores.firePropertyChange(NUEVA_PARTIDA, null, numJugadores);
        System.out.println("El estado de la maquina de estados es: " + estadoJuego + " y el turno es: " + turno);
    }

    public void tirarDado(String color) {
        if (comprobarTurno(color) && maquinaEstado(1)) { //Pasar a constantes simbolicas.
            int tirada = buscarJugadorColor(color).tirarDado();
            estadoJuego = 2;
            observadores.firePropertyChange(TIRAR_DADO, color, tirada);
            System.out.println("El estado de la maquina de estados es: " + estadoJuego + " y el turno es: " + turno);
        }
    }

    public void moverFicha(String color, int idFicha) {
        if (comprobarTurno(color) && maquinaEstado(2)) { //Pasar a constantes simbolicas.
            if (comprobarNuevaPosicion(color, idFicha)) {
                buscarJugadorColor(color).moverFicha(idFicha, devolverNuevaPosicion(color, idFicha));
                estadoJuego = 3;
                observadores.firePropertyChange(MOVER_FICHA, new Tupla(color, idFicha), buscarJugadorColor(color).getFicha(idFicha).getPosicion());
                System.out.println("El estado de la maquina de estados es: " + estadoJuego + " y el turno es: " + turno);
                pasarTurno();
            }
        }
    }
    
    private int devolverNuevaPosicion(String color, int idFicha){
        int posicionActual = buscarJugadorColor(color).getFicha(idFicha).getPosicion();
        return posicionActual + 3;        
    }
    
    private boolean comprobarNuevaPosicion(String color, int idFicha){
        int posicionActual = buscarJugadorColor(color).getFicha(idFicha).getPosicion();
        return true;
    }
    
    public void pasarTurno(){
        Color[] colores = new Color[4];
        colores = Color.values();
        int turnoNuevo = 0;
        for(int i = 0; i < numJugadores; i++){
            if(colores[i].getColor().equals(turno)){
                if(numJugadores - 1 == i){
                    turnoNuevo = 0;
                }else {
                    turnoNuevo = i + 1;   
                }
            }
        }
        turno = colores[turnoNuevo].getColor();
        estadoJuego = 1;
                System.out.println("El estado de la maquina de estados es: " + estadoJuego + " y el turno es: " + turno);
    }
}
