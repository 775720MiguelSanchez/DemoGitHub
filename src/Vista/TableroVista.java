/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Control.OyenteVista;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.print.event.PrintJobAttributeEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Miguel
 */
public class TableroVista extends JPanel {

    private static final int ALTURA_FILA_HORIZONTAL = 40;
    private static final int ANCHURA_COLUMNA_HORIZONTAL = 40;
    private static final int ALTURA_FILA_CASA = 400;
    private static final int ANCHURA_COLUMNA_CASA = 400;
    private static final int XROJO = 3;
    private static final int YROJO = 3;
    private static final int XAMARILLO = 16;
    private static final int YAMARILLO = 16;
    private static final int XAZUL = 16;
    private static final int YAZUL = 3;
    private static final int XVERDE = 3;
    private static final int YVERDE = 16;
    private static final int ANCHURA_COLUMNA_VERTICAL = 40;
    private static final int MATRIZ_CASILLAS[][]
            = {{-1, -1, -1, -1, -1, -1, -1, 35, 35, 34, 34, 33, 33, -2, -2, -2, -2, -2, -2, -2},
            {-1, -1, -1, -1, -1, -1, -1, 36, 36, 84, 84, 32, 32, -2, -2, -2, -2, -2, -2, -2},
            {-1, -1, -1, -1, -1, -1, -1, 37, 37, 85, 85, 31, 31, -2, -2, -2, -2, -2, -2, -2},
            {-1, -1, -1, -1, -1, -1, -1, 38, 38, 86, 86, 30, 30, -2, -2, -2, -2, -2, -2, -2},
            {-1, -1, -1, -1, -1, -1, -1, 39, 39, 87, 87, 29, 29, -2, -2, -2, -2, -2, -2, -2},
            {-1, -1, -1, -1, -1, -1, -1, 40, 40, 88, 88, 28, 28, -2, -2, -2, -2, -2, -2, -2},
            {-1, -1, -1, -1, -1, -1, -1, 41, 41, 89, 89, 27, 27, -2, -2, -2, -2, -2, -2, -2},
            {50, 49, 48, 47, 46, 45, 44, 42, 42, 90, 90, 26, 26, 24, 23, 22, 21, 20, 19, 18},
            {50, 49, 48, 47, 46, 45, 44, 43, 0, 0, 0, 0, 25, 24, 23, 22, 21, 20, 19, 18},
            {51, 91, 92, 93, 94, 95, 96, 97, 0, 0, 0, 0, 83, 82, 81, 80, 79, 78, 77, 17},
            {51, 91, 92, 93, 94, 95, 96, 97, 0, 0, 0, 0, 83, 82, 81, 80, 79, 78, 77, 17},
            {52, 53, 54, 55, 56, 57, 58, 59, 0, 0, 0, 0, 9, 10, 11, 12, 13, 14, 15, 16},
            {52, 53, 54, 55, 56, 57, 58, 60, 60, 75, 75, 8, 8, 10, 11, 12, 13, 14, 15, 16},
            {-4, -4, -4, -4, -4, -4, -4, 61, 61, 74, 74, 7, 7, -3, -3, -3, -3, -3, -3, -3},
            {-4, -4, -4, -4, -4, -4, -4, 62, 62, 73, 73, 6, 6, -3, -3, -3, -3, -3, -3, -3},
            {-4, -4, -4, -4, -4, -4, -4, 63, 63, 72, 72, 5, 5, -3, -3, -3, -3, -3, -3, -3},
            {-4, -4, -4, -4, -4, -4, -4, 64, 64, 71, 71, 4, 4, -3, -3, -3, -3, -3, -3, -3},
            {-4, -4, -4, -4, -4, -4, -4, 65, 65, 70, 70, 3, 3, -3, -3, -3, -3, -3, -3, -3},
            {-4, -4, -4, -4, -4, -4, -4, 66, 66, 69, 69, 2, 2, -3, -3, -3, -3, -3, -3, -3},
            {-4, -4, -4, -4, -4, -4, -4, 67, 67, 68, 68, 1, 1, -3, -3, -3, -3, -3, -3, -3}};
    private CasillaVista casillas[][];
    private JuegoVista juegoVista;
    private DadoVista dados[];
    //private boolean habilitado = false; En un futuro para que en el cambio de turno se tenga que poner habilitado si te toca el turno y si no deshabilitado. Introducirlo cuando entren en juego cliente y servidor

    public static final boolean RECIBIR_EVENTOS_RATON = true;
    public static final boolean NO_RECIBIR_EVENTOS_RATON = false;

    public TableroVista(JuegoVista juegoVista, int casillas, boolean recibeEventosRaton, int fichas, int dados) {
        this.setEnabled(false);
        this.juegoVista = juegoVista;
        this.dados = new DadoVista[dados];
        this.setEnabled(false);
        this.juegoVista = juegoVista;

        setLayout(new GridLayout(20, 20));

        crearCasillas(20, 20, recibeEventosRaton);
        crearDado(recibeEventosRaton);

        pintarCasillas();
        pintarCentro();
        //pintarDados();
        inicializarFichas();
        inicializarDados();

//        pintarFichas();
        //setLayout(new GridBagLayout());
        //crearCasillas(casillas, recibeEventosRaton);
        this.setPreferredSize(new Dimension(21 * ALTURA_FILA_HORIZONTAL, 21 * ANCHURA_COLUMNA_VERTICAL));
        //crearCasillas(casillas, recibeEventosRaton);
//        Dimension size = getPreferredSize();
//        setPreferredSize(size);

    }

    private void crearDado(boolean recibeEventosRaton) {
        for (int i = 0; i < dados.length; i++) {
            if (i == 0) {
                dados[i] = new DadoVista(juegoVista, i, recibeEventosRaton, "ROJO", XROJO, YROJO);
            } else if (i == 1) {
                dados[i] = new DadoVista(juegoVista, i, recibeEventosRaton, "AMARILLO", XAMARILLO, YAMARILLO);
            } else if (i == 2) {
                dados[i] = new DadoVista(juegoVista, i, recibeEventosRaton, "AZUL", XAZUL, YAZUL);
            } else if (i == 3) {
                dados[i] = new DadoVista(juegoVista, i, recibeEventosRaton, "VERDE", XVERDE, YVERDE);
            }
            //add(dados[i]);

        }
    }

    public void pintarDado(String color) {
        DadoVista dado = obtenerDado(color);
        if (dado != null) {
            casillas[dado.getPosicionx()][dado.getPosiciony()].setIcon(new ImageIcon("src/imagenes/dados/" + dado.getColor() + "/" + dado.getTiradaActual() + ".png"));
            casillas[dado.getPosicionx()][dado.getPosiciony()].setEnabled(true);
            casillas[dado.getPosicionx()][dado.getPosiciony()].setEstado("DADO");
        }
    }

    private void inicializarDados() {
        for (int i = 0; i < dados.length; i++) {
            pintarDado(dados[i].getColor());
        }
    }

    public DadoVista obtenerDado(String color) {
        for (int i = 0; i < dados.length; i++) {
            if (color.equals(dados[i].getColor())) {
                return dados[i];
            }
        }
        return null;
    }

    private void pintarCentro() {
        for (int i = 8; i < 12; i++) {
            for (int j = 8; j < 12; j++) {
                casillas[i][j].setIcon(new ImageIcon("src/imagenes/central/" + (i - 8) + (j - 8) + ".png"));
                casillas[i][j].setEnabled(true);
            }
        }
    }

    private void inicializarFichas() {
        ImageIcon fichaRoja = new ImageIcon("src/imagenes/ficha_roja.png");
        ImageIcon fichaAmarilla = new ImageIcon("src/imagenes/ficha_amarilla.png");
        casillas[1][1].setIcon(fichaRoja);
        casillas[1][1].setEnabled(true);
        casillas[1][1].setEstado("FICHA");
        FichaVista ficha = new FichaVista(0, "ROJO");
        casillas[1][1].anyadirFicha(ficha);
        casillas[1][5].setIcon(fichaRoja);
        casillas[1][5].setEnabled(true);
        casillas[1][5].setEstado("FICHA");
        FichaVista ficha2 = new FichaVista(1, "ROJO");
        casillas[1][5].anyadirFicha(ficha2);
        casillas[5][1].setIcon(fichaRoja);
        casillas[5][1].setEnabled(true);
        casillas[5][1].setEstado("FICHA");
        FichaVista ficha3 = new FichaVista(2, "ROJO");
        casillas[5][1].anyadirFicha(ficha3);
        casillas[5][5].setIcon(fichaRoja);
        casillas[5][5].setEnabled(true);
        casillas[5][5].setEstado("FICHA");
        FichaVista ficha4 = new FichaVista(3, "ROJO");
        casillas[5][5].anyadirFicha(ficha4);
        casillas[14][14].setIcon(fichaAmarilla);
        casillas[14][14].setEnabled(true);
        casillas[14][14].setEstado("FICHA");
        FichaVista ficha5 = new FichaVista(0, "AMARILLO");
        casillas[14][14].anyadirFicha(ficha5);
        casillas[14][18].setIcon(fichaAmarilla);
        casillas[14][18].setEnabled(true);
        casillas[14][18].setEstado("FICHA");
        FichaVista ficha6 = new FichaVista(1, "AMARILLO");
        casillas[14][18].anyadirFicha(ficha6);
        casillas[18][14].setIcon(fichaAmarilla);
        casillas[18][14].setEnabled(true);
        casillas[18][14].setEstado("FICHA");
        FichaVista ficha7 = new FichaVista(2, "AMARILLO");
        casillas[18][14].anyadirFicha(ficha7);
        casillas[18][18].setIcon(fichaAmarilla);
        casillas[18][18].setEnabled(true);
        casillas[18][18].setEstado("FICHA");
        FichaVista ficha8 = new FichaVista(3, "AMARILLO");
        casillas[18][18].anyadirFicha(ficha8);
    }

    private void pintarCasillas() {
        for (int fil = 0; fil < casillas.length; fil++) {
            for (int col = 0; col < casillas[fil].length; col++) {
                switch (casillas[fil][col].getId()) {
                    case -1:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.RED);
                        casillas[fil][col].setBorder(null);
                        break;

                    case -2:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.BLUE);
                        casillas[fil][col].setBorder(null);
                        break;

                    case -3:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.YELLOW);
                        casillas[fil][col].setBorder(null);
                        break;

                    case -4:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.GREEN);
                        casillas[fil][col].setBorder(null);
                        break;

                    case 56:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.GREEN);
                        casillas[fil][col].setBorder(null);
                        break;

                    case 5:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.YELLOW);
                        casillas[fil][col].setBorder(null);
                        break;

                    case 22:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.BLUE);
                        casillas[fil][col].setBorder(null);
                        break;

                    case 39:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.RED);
                        casillas[fil][col].setBorder(null);
                        break;

                    case 0:
                        casillas[fil][col].setOpaque(true);
                        casillas[fil][col].setBackground(Color.BLACK);
                        casillas[fil][col].setBorder(null);
                        break;

                    default:
                        if (casillas[fil][col].getId() == 46 || casillas[fil][col].getId() == 51
                                || casillas[fil][col].getId() == 63 || casillas[fil][col].getId() == 68
                                || casillas[fil][col].getId() == 12 || casillas[fil][col].getId() == 17
                                || casillas[fil][col].getId() == 29 || casillas[fil][col].getId() == 34) {

                            casillas[fil][col].setOpaque(true);
                            casillas[fil][col].setBackground(Color.GRAY);
                            // casillas[fil][col].setBorder(null);      

                        } else if (casillas[fil][col].getId() >= 69 && casillas[fil][col].getId() < 77) {

                            casillas[fil][col].setOpaque(true);
                            casillas[fil][col].setBackground(Color.YELLOW);
                            //casillas[fil][col].setBorder(null); 

                        } else if (casillas[fil][col].getId() >= 77 && casillas[fil][col].getId() < 84) {

                            casillas[fil][col].setOpaque(true);
                            casillas[fil][col].setBackground(Color.BLUE);
                            //casillas[fil][col].setBorder(null); 

                        } else if (casillas[fil][col].getId() >= 84 && casillas[fil][col].getId() < 91) {
                            casillas[fil][col].setOpaque(true);
                            casillas[fil][col].setBackground(Color.RED);
                            // casillas[fil][col].setBorder(null); 

                        } else if (casillas[fil][col].getId() >= 91 && casillas[fil][col].getId() < 98) {
                            casillas[fil][col].setOpaque(true);
                            casillas[fil][col].setBackground(Color.GREEN);
                            //casillas[fil][col].setBorder(null); 

                        } else {
                            casillas[fil][col].setOpaque(true);
                            casillas[fil][col].setBackground(Color.WHITE);
                            //casillas[fil][col].setBorder(null);
                            //casillas[fil][col].setForeground(Color.BLACK);
                            casillas[fil][col].setText(MATRIZ_CASILLAS[fil][col] + "");
                        }
                }
                pintarDetalles();
                ponerBordes(fil, col);

            }

        }

    }

    private void pintarDetalles() {//Ya veremos si lo ponemos o no. Pero solo seria ponerlo del tamaño correcto y en el método de poner bordes que no le ponga a esas casillas el borde que le toca ya que viene dado por una foto
        casillas[12][7].setIcon(new ImageIcon("src/imagenes/esquinainferiorizquierda.png"));
        casillas[12][7].setEnabled(true);
    }

    private void ponerBordes(int fila, int columna) {
        int top = 0;
        int left = 0;
        int bottom = 0;
        int right = 0;

        if (fila == 0) {
            top = 2;
            if (casillas[fila][columna].getId() != casillas[fila + 1][columna].getId()) {
                bottom = 2;
            }
        }
        if (fila == 19) {
            bottom = 2;
            if (casillas[fila][columna].getId() != casillas[fila - 1][columna].getId()) {
                top = 2;
            }
        }
        if (columna == 0) {
            left = 2;
            if (casillas[fila][columna].getId() != casillas[fila][columna + 1].getId()) {
                right = 2;
            }
        }
        if (columna == 19) {
            right = 2;
            if (casillas[fila][columna].getId() != casillas[fila][columna - 1].getId()) {
                left = 2;
            }
        }
        if (fila > 0 && fila < 19) {
            if (casillas[fila][columna].getId() != casillas[fila + 1][columna].getId()) {
                bottom = 2;
            }
            if (casillas[fila][columna].getId() != casillas[fila - 1][columna].getId()) {
                top = 2;
            }
        }
        if (columna > 0 && columna < 19) {
            if (casillas[fila][columna].getId() != casillas[fila][columna + 1].getId()) {
                right = 2;
            }
            if (casillas[fila][columna].getId() != casillas[fila][columna - 1].getId()) {
                left = 2;
            }
        }
        if (columna == 7 && fila == 12) {
            top = 0;
            left = 2;
            right = 0;
            bottom = 2;
        }
        if (columna == 7 && fila == 11) {
            top = 2;
            left = 2;
            right = 2;
            bottom = 0;
        }
        casillas[fila][columna].setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.black));
    }

    private void crearCasillas(int filas, int columnas, boolean recibeEventosRaton) {
        casillas = new CasillaVista[filas][columnas];
        for (int fil = 0; fil < filas; fil++) {
            for (int col = 0; col < columnas; col++) {
                if (MATRIZ_CASILLAS[fil][col] > 0) {
                    if (existeCasilla(MATRIZ_CASILLAS[fil][col])) {
                        casillas[fil][col]
                                = new CasillaVista(juegoVista, MATRIZ_CASILLAS[fil][col], "B", recibeEventosRaton);
                        add(casillas[fil][col]);
                    } else {
                        casillas[fil][col]
                                = new CasillaVista(juegoVista, MATRIZ_CASILLAS[fil][col], "A", recibeEventosRaton);
                        add(casillas[fil][col]);
                    }

                } else {
                    casillas[fil][col]
                            = new CasillaVista(juegoVista, MATRIZ_CASILLAS[fil][col], "C", recibeEventosRaton);
                    add(casillas[fil][col]);
                }

            }
        }
    }

    private boolean existeCasilla(int idCasilla) {
        if ( devolverCasilla(idCasilla) != null) {
            return true;
        }
        return false;
    }

    public void anyadirFicha(int idCasilla, FichaVista ficha) {
        if (devolverCasilla(idCasilla).getFicha() == null) {
            devolverCasilla(idCasilla).anyadirFicha(ficha);
        }else{
            devolverCasillaB(idCasilla).anyadirFicha(ficha);
        }
        
    }

    public void quitarFicha(int idFicha, String color) {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if (casillas[i][j].getFicha() != null) {
                    if (casillas[i][j].getFicha().getId() == idFicha && casillas[i][j].getFicha().getColor().equals(color)) {
                        casillas[i][j].quitarFicha();
                    }
                }
            }
        }
    }

    private CasillaVista devolverCasilla(int idCasilla) {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if(casillas[i][j] == null){
                    return null;
                }
                if (casillas[i][j].getId() == idCasilla) {
                    return casillas[i][j];
                }
            }
        }
        return null;
    }
        private CasillaVista devolverCasillaB(int idCasilla) {
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas[i].length; j++) {
                if(casillas[i][j] == null){
                    return null;
                }
                if (casillas[i][j].getId() == idCasilla && casillas[i][j].getNumero().equals("B")) {
                    return casillas[i][j];
                }
            }
        }
        return null;
    }
}
