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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Miguel
 */
public class CasillaVista extends JLabel {

    private int id;
    private String numero;
    private JuegoVista juegoVista;
    private String estado;
    private FichaVista ficha;

    CasillaVista(JuegoVista juegoVista, int id, String numero, boolean recibeEventosRaton) {
        this.id = id;
        this.juegoVista = juegoVista;
        this.numero = numero;
        estado = "VACIO";
        setEnabled(false);
        setHorizontalAlignment(SwingConstants.CENTER);
        //setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        if (recibeEventosRaton) {
            recibirEventosRaton();
        }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    private void recibirEventosRaton() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isEnabled()) {
                    System.out.println("El estado es: " + estado);
                    switch (estado) {
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
                            System.out.println("El color de la ficha es: " + ficha.getId() + " - " + ficha.getColor());
                            Modelo.Tupla tupla = new Tupla(ficha.getColor(), ficha.getId());
                            juegoVista.notificacion(OyenteVista.Evento.MOVER_FICHA, tupla);

                    }
                    //juegoVista.notificacion(OyenteVista.Evento.PONER_FICHA, posicion);         
                }
            }
        });
    }

    public int getId() {
        return id;
    }

    public void anyadirFicha(FichaVista fichaAnyadir) {
        if (ficha == null) {
            ficha = fichaAnyadir;
            estado = "FICHA";
            pintar(ficha);
        }
    }

    public FichaVista getFicha() {
        return ficha;
    }

    public void quitarFicha() {
        if (ficha != null) {
            estado = "VACIO";
            ficha = null;
            setIcon(null);
        }
    }

    public void pintar(FichaVista ficha) {
        ImageIcon fichaRoja = new ImageIcon("src/imagenes/ficha_roja.png");
        ImageIcon fichaAmarilla = new ImageIcon("src/imagenes/ficha_amarilla.png");
        ImageIcon fichaAzul = new ImageIcon("src/imagenes/ficha_azul.png");
        ImageIcon fichaVerde = new ImageIcon("src/imagenes/ficha_verde.png");
        if (ficha.getColor().equals("ROJO")) {
            setIcon(fichaRoja);
        } else if (ficha.getColor().equals("AMARILLO")) {
            setIcon(fichaAmarilla);
        } else if (ficha.getColor().equals("AZUL")) {
            setIcon(fichaAzul);
        } else if (ficha.getColor().equals("VERDE")) {
            setIcon(fichaVerde);
        }
        setEnabled(true);
    }
}
