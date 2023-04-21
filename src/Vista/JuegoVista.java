/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Modelo.Juego;
import Control.OyenteVista;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Miguel
 */
public class JuegoVista extends JFrame implements ActionListener, PropertyChangeListener {

    private static JuegoVista instancia = null;
    private OyenteVista oyenteVista;
    private TableroVista tableroVista;
    private PantallaBienvenidaVista pantallaVista;
    private static final int NUMERO_CASILLAS = 200;
    private JPanel contentPanel;
    private static final String REGLAS = "Movimiento de las fichas\n" +
"Inicialmente cada jugador tiene un par de fichas en la casilla inicial del recorrido, y el resto de fichas situadas en casa.\n" +
"\n" +
"Los participantes en su turno deben tirar el dado y avanzar con una de sus fichas el número de casillas indicado por el número obtenido, teniendo en cuenta las siguientes reglas:\n" +
"\n" +
" En caso de tirar un 5 y tener fichas en casa, una de ellas entra en juego obligatoriamente situándose en la casilla de salida.\n" +
" Las fichas avanzan siguiendo el recorrido en sentido inverso a las agujas del reloj.\n" +
" Cuando una ficha completa una vuelta al tablero y llega a la casilla que conecta con el pasillo de su color, se desvía hacia la casilla destino (hacia el centro del tablero).\n" +
" En caso de tirar un 6 y tener todas las fichas del mismo color fuera de casa, se avanzan 7 casillas.\n" +
" El jugador que ha obtenido un 6 juega de nuevo.\n" +
" En cada casilla del recorrido puede haber un máximo de dos fichas. Una ficha no puede jugarse si el movimiento le llevara a una casilla en la que ya hay dos fichas.\n" +
" Una ficha no puede moverse si para completar el avance tuviera que atravesar una barrera. Las barreras son pares de fichas de un mismo color situadas en la misma casilla.\n" +
" Para alcanzar el final del recorrido es necesario avanzar el número exacto de casillas que restan hasta completarlo. No es posible mover la ficha si el número del dado es superior.\n" +
" Cuando una ficha completa su recorrido, el jugador debe avanzar 10 casillas con otra de sus fichas.\n" +
" Puede darse el caso de que todas las fichas estén bloqueadas (por estar en casa, o tras una barrera, o en el final del recorrido). En este caso, simplemente no se realiza ningún movimiento.\n\n\n" +
" Capturas\n" +
"Una ficha come a otra de diferente color si finaliza su avance en la casilla ocupada por esta última.\n" +
"\n" +
" Las casillas de salida y las casillas marcadas con un círculo son seguros, donde las capturas no son posibles. Por tanto, en los seguros pueden coincidir dos fichas de diferente color.\n" +
" Si en la casilla de salida se encuentran dos fichas de diferente color y una nueva ficha sale de su casa, la ficha de diferente color (o si ambas lo son, la última ficha que hubiera llegado a la casilla) resulta capturada.\n" +
" Las fichas comidas vuelven a su casa, de manera que vuelven a entrar en juego cuando el jugador obtiene un 5 en su tirada.\n" +
" Quien come una ficha tiene el premio de avanzar 20 casillas con cualquiera de sus fichas.\n" +
" Si un jugador obtiene un 6 tres veces consecutivas, el tercer movimiento no se realiza, y la ficha movida con el segundo 6 vuelve a su casa, salvo que ya hubiese alcanzado el pasillo final del recorrido.";

    private JuegoVista(OyenteVista oyenteVista) {
        super();
        this.oyenteVista = oyenteVista;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                oyenteVista.eventoProducido(OyenteVista.Evento.SALIR, null);
            }
        });

        crearElementosVentanaPrincipal();//Aqui tendremos que comenzar a mostrar los elementos del menu, en vez de poner directamente el juego como haremos en estos prototipos iniciales.
        setVisible(true);
    }

    private void crearElementosVentanaPrincipal() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));//Realizarlo todas las constantes a través del config.properties.

        crearMenu(panel);
        crearBarraHerramientas(panel);
        add(panel, BorderLayout.NORTH);
        contentPanel = new JPanel();
        crearPantallaBienvenida(contentPanel);
        add(contentPanel, BorderLayout.CENTER);

        setResizable(false);
        pack();
        setLocationRelativeTo(null);

    }

    private void crearTablero(JPanel panel, int numJugadores) {
        tableroVista = new TableroVista(this, TableroVista.RECIBIR_EVENTOS_RATON, numJugadores);
        panel.add(tableroVista);
    }

    public void nuevaPartida(int numJugadores) {
        System.out.println("Se va a crear una nueva partida con el numero de jugadores de " + numJugadores);
        contentPanel.removeAll();
        JPanel panelTablero = new JPanel();
        crearTablero(panelTablero, numJugadores);
        contentPanel.add(panelTablero, BorderLayout.CENTER);
        pack();
    }

    private void crearPantallaBienvenida(JPanel panel) {
        pantallaVista = new PantallaBienvenidaVista(this);
        contentPanel.add(pantallaVista);
    }

    private void crearMenu(JPanel panel) {
        JMenuBar menuBarraHerramientas = new JMenuBar();

        menuBarraHerramientas.add(crearMenuMenu());

        panel.add(menuBarraHerramientas);
    }

    private void crearBarraHerramientas(JPanel panel) {
        JToolBar barra = new JToolBar();
        barra.setFloatable(false);
        barra.add(crearBotonBarraHerramientas("Prueba"));
    }

    private JButton crearBotonBarraHerramientas(String etiqueta) {
        return crearBotonBarraHerramientas(etiqueta, true);
    }

    private JButton crearBotonBarraHerramientas(String etiqueta,
            boolean enabled) {
        JButton botonBarra = new JButton();
        botonBarra.setEnabled(enabled);
        botonBarra.setToolTipText(etiqueta);
        botonBarra.addActionListener(this);
        botonBarra.setActionCommand(etiqueta);

        return botonBarra;
    }

    private JMenu crearMenuMenu() {
        JMenu menu = new JMenu("Menu");
        menu.add(crearMenuItem("NUEVA PARTIDA 2 JUGADORES"));
        menu.add(crearMenuItem("NUEVA PARTIDA 4 JUGADORES"));
        menu.add(crearMenuItem("MOSTRAR REGLAS"));
        menu.add(crearMenuItem("SALIR"));

        return menu;
    }

    private JMenuItem crearMenuItem(String etiqueta) {
        JMenuItem menu = new JMenuItem(etiqueta);
        menu.addActionListener(this);
        menu.setActionCommand(etiqueta);

        return menu;
    }

    public static synchronized JuegoVista devolverInstancia(OyenteVista oyenteVista) {
        if (instancia == null) {
            instancia = new JuegoVista(oyenteVista);
        }
        return instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SALIR":
                oyenteVista.eventoProducido(OyenteVista.Evento.SALIR, null);
                System.out.println("Saliendo desde el menu");
                break;
            case "NUEVA PARTIDA 2 JUGADORES":
                oyenteVista.eventoProducido(OyenteVista.Evento.NUEVA_PARTIDA_2, null);
                System.out.println("Se va a preparar la partida para 2 jugadores.");
                break;
            case "NUEVA PARTIDA 4 JUGADORES":
                oyenteVista.eventoProducido(OyenteVista.Evento.NUEVA_PARTIDA_4, null);
                System.out.println("Se va a preparar la partida para 4 jugadores.");
                break;
            case "MOSTRAR REGLAS":
                oyenteVista.eventoProducido(OyenteVista.Evento.MOSTRAR_REGLAS, null);
                System.out.println("Se van a enseñar las reglas.");
                break;

        }

//            System.out.println(e.getSource());
    }

    public void notificacion(OyenteVista.Evento evento, Object obj) {
        oyenteVista.eventoProducido(evento, obj);
    }
    
    public void mostrarReglas(){
        JOptionPane.showMessageDialog(null, REGLAS);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Juego.TIRAR_DADO)) {
            String color = (String) evt.getOldValue();
            tableroVista.obtenerDado(color).setTiradaActual((int) evt.getNewValue());
            tableroVista.pintarDado(color);
        } else if (evt.getPropertyName().equals(Juego.MOVER_FICHA)) {
            Modelo.Tupla tuplaAntigua = (Modelo.Tupla) evt.getOldValue();
            Modelo.Tupla tuplaNueva = (Modelo.Tupla) evt.getNewValue();
            int nuevaPosicion = (int) tuplaNueva.b;
            System.out.println("Hola: " + (int) tuplaAntigua.b);
            tableroVista.quitarFicha((int) tuplaAntigua.b, (String) tuplaNueva.a);
            tableroVista.anyadirFicha(nuevaPosicion, new FichaVista((int) tuplaAntigua.b, (String) tuplaNueva.a));

        } else if (evt.getPropertyName().equals(Juego.NUEVA_PARTIDA)) {
            int numJugadores = (int) evt.getNewValue();
        }
    }
}
