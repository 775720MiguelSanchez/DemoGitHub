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
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author Miguel
 */
public class JuegoVista extends JFrame implements ActionListener, PropertyChangeListener{
    
    private static JuegoVista instancia = null;
    private OyenteVista oyenteVista;
    private TableroVista tableroVista;
    
    
    
    
    private JuegoVista(OyenteVista oyenteVista, int casillas){
        super();
        
        this.oyenteVista = oyenteVista;
        
        addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                oyenteVista.eventoProducido(OyenteVista.Evento.SALIR, null);
            }
        });
        
        crearElementosVentanaPrincipal(casillas, 2);//Aqui tendremos que comenzar a mostrar los elementos del menu, en vez de poner directamente el juego como haremos en estos prototipos iniciales.
        
        setVisible(true);
    }
    
    private void crearElementosVentanaPrincipal(int casillas, int numJugadores){
        setLayout(new BorderLayout());
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));//Realizarlo todas las constantes a través del config.properties.
        
        crearMenu(panel);
        crearBarraHerramientas(panel);
        add(panel, BorderLayout.NORTH);
        
        JPanel panelTablero = new JPanel();
        //setContentPane(panelTablero);
        crearTablero(panelTablero, casillas, numJugadores * 4, numJugadores);
        add(panelTablero, BorderLayout.CENTER); 
        
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        

    }
    
    private void crearTablero(JPanel panel, int casillas, int fichas, int dados){
        tableroVista = new TableroVista(this, casillas, TableroVista.RECIBIR_EVENTOS_RATON, fichas, dados);
        panel.add(tableroVista);
    }
    
    private void crearMenu(JPanel panel){
        JMenuBar menuBarraHerramientas = new JMenuBar();
        
        menuBarraHerramientas.add(crearMenuMenu());
        
        panel.add(menuBarraHerramientas);
    }
    
    private void crearBarraHerramientas(JPanel panel){
        JToolBar barra = new JToolBar();
        barra.setFloatable(false);
        barra.add(crearBotonBarraHerramientas("Prueba"));
    }
    
    private JButton crearBotonBarraHerramientas(String etiqueta){
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
    
    private JMenu crearMenuMenu(){
        JMenu menu = new JMenu("Menu");
        menu.add(crearMenuItem("Nueva partida"));
        menu.add(crearMenuItem("Salir"));
        
        return menu;
    }
    
    private JMenuItem crearMenuItem(String etiqueta) {
        JMenuItem menu = new JMenuItem(etiqueta);
        menu.addActionListener(this);
        menu.setActionCommand(etiqueta);
    
        return menu;
    }
    
    public static synchronized JuegoVista devolverInstancia(OyenteVista oyenteVista, int casillas){
        if (instancia == null){
            instancia = new JuegoVista(oyenteVista, casillas);
        }
        return instancia;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "SALIR":
                oyenteVista.eventoProducido(OyenteVista.Evento.SALIR, null);
                break;
        }
    }

    public void notificacion(OyenteVista.Evento evento, Object obj){
        oyenteVista.eventoProducido(evento, obj);
        System.out.println("JuegoVista se lo va a comunicar al controlador");

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(Juego.TIRAR_DADO)){
            System.out.println("Se tira el dado en la vista, el nuevo valor es: " + evt.getNewValue() + " el turno es " + evt.getOldValue());
            String color = (String)evt.getOldValue();
            tableroVista.obtenerDado(color).setTiradaActual((int)evt.getNewValue());
            tableroVista.pintarDado(color);
        }else if (evt.getPropertyName().equals(Juego.MOVER_FICHA)){
            Modelo.Tupla tupla = (Modelo.Tupla)evt.getOldValue();
            int posicion = (int)evt.getNewValue();
            System.out.println("El color de la ficha es: " + tupla.a + " el id de la ficha es: "+ tupla.b + " la posicion de la ficha es: " + posicion);
            tableroVista.anyadirFicha((int)tupla.b, new Ficha(posicion, (String)tupla.a));
            //tableroVista.quitarFicha(posicion);
        }else if(evt.getPropertyName().equals(Juego.NUEVA_PARTIDA)){
            int numJugadores = (int)evt.getNewValue(); 
        }
    }
}
