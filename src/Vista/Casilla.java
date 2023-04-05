/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import Control.OyenteVista;
import Modelo.Tupla;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Miguel
 */
public class Casilla extends JLabel {

    private int id;
    private JuegoVista juegoVista;
    private String estado;
    private Ficha ficha;

    Casilla(JuegoVista juegoVista, int id, boolean recibeEventosRaton) {
        this.id = id;
        this.juegoVista = juegoVista;
        estado = "VACIO";
        setEnabled(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        //setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        if (recibeEventosRaton) {
            recibirEventosRaton();
        }
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    private void recibirEventosRaton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isEnabled()) {
                    switch(estado){
                        case "DADO":
                            if (id == -1) {
                                juegoVista.notificacion(OyenteVista.Evento.TIRAR_DADO, "ROJO");
                            }
                            if (id == -3) {
                                juegoVista.notificacion(OyenteVista.Evento.TIRAR_DADO, "AMARILLO");
                            }
                            if (id == -2) {
                                juegoVista.notificacion(OyenteVista.Evento.TIRAR_DADO, "AZUL");
                            }
                            if (id == -4) {
                                juegoVista.notificacion(OyenteVista.Evento.TIRAR_DADO, "VERDE");
                            }
                            
                            break;
                        
                        case "FICHA":
                            Modelo.Tupla tupla = new Tupla( ficha.getColor(), ficha.getId());
                            juegoVista.notificacion(OyenteVista.Evento.MOVER_FICHA, tupla);

                    }
                    

                    //juegoVista.notificacion(OyenteVista.Evento.PONER_FICHA, posicion);         
                }
                System.out.println(id);
            }
        });
    }

    public int getId() {
        return id;
    }

    public void ponerIcono(Icon icono) {
        setIcon(icono);
    }
    
    public boolean anyadirFicha(Ficha fichaAnyadir){
        if (ficha == null){
            this.ficha = fichaAnyadir;
            return true;
        }
        return false;
    }
    
    public Ficha quitarFicha(){
        if (ficha != null){
            Ficha fichaQuitada = ficha;
            ficha = null;
            return fichaQuitada;
        }
        return null;
    }
}
