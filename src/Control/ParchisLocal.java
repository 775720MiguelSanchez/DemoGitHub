/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Control;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Modelo.Juego;
import Modelo.Tupla;
import Vista.JuegoVista;

/**
 *
 * @author Miguel
 */
public class ParchisLocal implements OyenteVista {

    private JuegoVista vista;
    private static final int NUMERO_CASILLAS = 200;
    private static final String TURNO1 = "ROJO";
    private static final String TURNO2 = "AMARILLO";
    private static final String TURNO3 = "AZUL";
    private static final String TURNO4 = "VERDE";

    private Juego juego;
    private int numJugadores;

    public ParchisLocal() {

        
        vista = JuegoVista.devolverInstancia(this);//De aquí se construye la vista. Esto es lo que tendremos que poner mas adelante una vez seleccionada la partida y aqui tendremos que poner una imagen tipo bienvenidos al parchis blablabla, que haga como de pantalla de bienvenida.
        juego = new Juego();

        juego.nuevoObservador(vista);
    }

    private void nuevaPartida(int numJugadores) {
        juego.crearPartida(numJugadores);
        vista.nuevaPartida(numJugadores);        
    }

    private void salir() {
        System.exit(0);
    }

    private void mostrarReglas(){
        vista.mostrarReglas();
    }

    public void eventoProducido(Evento evento, Object obj) {
        switch (evento) {
            case NUEVA_PARTIDA_2:
                nuevaPartida(2);//(int)obj
                break;
            case NUEVA_PARTIDA_4:
                nuevaPartida(4);//(int)obj
                break;
            case SALIR:
                salir();
                break;
            case MOVER_FICHA:
                Tupla tupla = (Tupla) obj;
                juego.moverFicha((String) tupla.a, (int) tupla.b);
                break;
            case TIRAR_DADO:
                juego.tirarDado((String) obj);
                break;
            case MOSTRAR_REGLAS:
                mostrarReglas();
                break;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new ParchisLocal();
    }

}
