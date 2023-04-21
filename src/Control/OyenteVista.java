/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

/**
 *
 * @author Miguel
 */
public interface OyenteVista {
       public enum Evento { NUEVA_PARTIDA_2, NUEVA_PARTIDA_4, SALIR, MOVER_FICHA, TIRAR_DADO, MOSTRAR_REGLAS}
  
   /**
    *  Llamado para notificar un evento de la interfaz de usuario
    * 
    */ 
   public void eventoProducido(Evento evento, Object obj);
}
