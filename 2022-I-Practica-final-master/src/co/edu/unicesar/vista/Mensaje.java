/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicesar.vista;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author JAIRO
 */
public class Mensaje {
    
    public static void mostrar(JDialog ventana, String titulo, String msg, int icono){
         JOptionPane.showMessageDialog(ventana, msg, titulo, icono);
    }
    
}
