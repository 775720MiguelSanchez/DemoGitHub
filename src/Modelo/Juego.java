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
    private static int INICIO_ROJO = 39;
    private static int INICIO_AMARILLO = 5;
    private static int INICIO_AZUL = 22;
    private static int INICIO_VERDE = 56;
    private static int CASA_ROJO = -1;
    private static int CASA_AMARILLO = -3;
    private static int CASA_AZUL = -2;
    private static int CASA_VERDE = -4;
    private static int TUBERIA_ROJO = 84;
    private static int TUBERIA_AMARILLO = 69;
    private static int TUBERIA_AZUL = 77;
    private static int TUBERIA_VERDE = 91;
    private PropertyChangeSupport observadores;
    private Jugador[] jugadores;
    private Casilla[] tablero;
    private int estadoJuego;
    private String turno;
    private int numJugadores;

    private static final int MAXIMO_CASILLAS = 200;

    public Juego() {
        observadores = new PropertyChangeSupport(this);
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
            System.out.println("Se crea el jugador " + colores[i]);
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

    /**
     * Si el jugador tiene fichas y el seguro de la casa no esta bloqueado devuelve true.
     * @param color
     * @param idFicha
     * @return 
     */
    private boolean sacarFicha(String color, int idFicha) { //Podríamos plantearnos el metodo que devuelva int para saber si tenemos que sacarFicha, si se saca ficha y se mata o s i no ocurre nada. O a lo mejor el si se saca ficha se mata que sea otro metodo.
        Jugador jugador = buscarJugadorColor(color);
        if (jugador.getDado().getTirada() == 5) {
            Ficha[] fichasJugador = jugador.getFichas();
            for (int i = 0; i < fichasJugador.length; i++) {
                if (fichasJugador[i].getEstado().getEstado().equals("CASA")) {//Mirar el método alguna en casa que se ha creado recientemente.
                    switch (color) {
                        case "ROJO":
                            if (!hayDos(INICIO_ROJO)) {//Aqui tendremos que tener en cuenta el caso de que no sean las dos fichas iguales ya que tenemos que matar. Ya se ha creado el metodo dosMismoColor.
                                return true;
                            }
                            break;
                        case "AMARILLO":
                            if (!hayDos(INICIO_AMARILLO)) {
                                return true;
                            }
                            break;
                        case "VERDE":
                            if (!hayDos(INICIO_VERDE)) {
                                return true;
                            }
                            break;
                        case "AZUL":
                            if (!hayDos(INICIO_AZUL)) {
                                return true;
                            }
                            break;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Este metodo devuelve true si en la casilla existen dos fichas del mismo
     * color, si no false.
     *
     * @param idCasilla
     * @return
     */
    private boolean dosMismoColor(int idCasilla) {
        Ficha[] fichasEnCasilla = new Ficha[2];
        int contador = 0;
        if (buscarCasilla(idCasilla).getNumFichas() == 2) {
            Color[] colores = new Color[4];
            colores = Color.values();
            for (int i = 0; i < numJugadores; i++) {
                Ficha[] fichas = buscarJugadorColor(colores[i].getColor()).getFichas();
                if (fichas[i].getPosicion() == idCasilla) {
                    fichasEnCasilla[contador] = fichas[i];
                    contador++;
                }
            }
        }
        if (fichasEnCasilla[0] != null && fichasEnCasilla[1] != null) {
            if (fichasEnCasilla[0].getColor().equals(fichasEnCasilla[1].getColor())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Si el jugador a sacado un 6 devuelve true. En caso contrario, false.
     * @param color
     * @return 
     */
    private boolean vuelvoATirar(String color) {
        if (buscarJugadorColor(color).getDado().getTirada() == 6) {
            return true;
        }
        return false;
    }

    /**
     * Devuelve true si tiene todas las fichas fuera de casa, si no false.
     * @param color
     * @return 
     */
    private boolean seisOSiete(String color){
        if(algunaEnCasa(color)){
            return false;
        }
        return true;
    }
    
    /**
     * POSIBLE IDEA: Podríamos hacer el metodo de si muere o no por sacar 3 veces un 6 de manera Recursiva???? Para así tener el control de las tiradas de una manera sencilla o lo podríamos hacer si no con un atributo que al cambio de turno en la máquina de estados se pusiera a 0.
     */
    
    /**
     * El método devuelve true, si el jugador del que es el turno tiene alguna ficha en casa.
     * @param color
     * @return 
     */
    private boolean algunaEnCasa(String color) {
        switch (color) {
            case "ROJO":
                if (buscarCasilla(CASA_ROJO).getNumFichas() > 0) {//Aqui tendremos que tener en cuenta el caso de que no sean las dos fichas iguales ya que tenemos que matar.
                    return true;
                }
                break;
            case "AMARILLO":
                if (buscarCasilla(CASA_AMARILLO).getNumFichas() > 0) {
                    return true;
                }
                break;
            case "VERDE":
                if (buscarCasilla(CASA_VERDE).getNumFichas() > 0) {
                    return true;
                }
                break;
            case "AZUL":
                if (buscarCasilla(CASA_AZUL).getNumFichas() > 0) {
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Devuelve true si hay dos fichas en una casilla.Si no, false.
     * @param idCasilla
     * @return 
     */
    private boolean hayDos(int idCasilla) {
        return buscarCasilla(idCasilla).getNumFichas() == 2;
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
//        System.out.println("El estado de la maquina de estados es: " + estadoJuego + " y el turno es: " + turno);
    }

    public void tirarDado(String color) {
        if (comprobarTurno(color) && maquinaEstado(1)) { //Pasar a constantes simbolicas.
            int tirada = buscarJugadorColor(color).tirarDado();
            estadoJuego = 2;
            observadores.firePropertyChange(TIRAR_DADO, color, tirada);
//            System.out.println("El estado de la maquina de estados es: " + estadoJuego + " y el turno es: " + turno);
        }
    }

    public void moverFicha(String color, int idFicha) {
        if (comprobarTurno(color) && maquinaEstado(2)) { //Pasar a constantes simbolicas.
            if (comprobarNuevaPosicion(color, idFicha)) {
                Ficha fichaAnterior = buscarJugadorColor(color).getFicha(idFicha);
                int posicionAnterior = buscarJugadorColor(color).getFicha(idFicha).getPosicion();
                buscarJugadorColor(color).moverFicha(idFicha, devolverNuevaPosicion(color, idFicha));
                estadoJuego = 3;
                observadores.firePropertyChange(MOVER_FICHA, new Tupla(posicionAnterior, idFicha), new Tupla(color, buscarJugadorColor(color).getFicha(idFicha).getPosicion()));
//                System.out.println("El estado de la maquina de estados es: " + estadoJuego + " y el turno es: " + turno);
                pasarTurno();
            }
        }
    }

    private int devolverNuevaPosicion(String color, int idFicha) {
        int posicionActual = buscarJugadorColor(color).getFicha(idFicha).getPosicion();
//        System.out.println("La tirada del jugador es: " + buscarJugadorColor(color).getDado().getTirada());
        return buscarJugadorColor(color).getFicha(idFicha).getPosicion() + buscarJugadorColor(color).getDado().getTirada();
    }

    private boolean comprobarNuevaPosicion(String color, int idFicha) {
        int posicionActual = buscarJugadorColor(color).getFicha(idFicha).getPosicion();
        return true;
    }

    public void pasarTurno() {
        Color[] colores = new Color[4];
        colores = Color.values();
        int turnoNuevo = 0;
        for (int i = 0; i < numJugadores; i++) {
            if (colores[i].getColor().equals(turno)) {
                if (numJugadores - 1 == i) {
                    turnoNuevo = 0;
                } else {
                    turnoNuevo = i + 1;
                }
            }
        }
        turno = colores[turnoNuevo].getColor();
        estadoJuego = 1;
//                System.out.println("El estado de la maquina de estados es: " + estadoJuego + " y el turno es: " + turno);
    }
}
