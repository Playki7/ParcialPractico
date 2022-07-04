/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicesar.persistencia;

import Excepciones.ExcepcionArchivo;
import Excepciones.ExcepcionEscritura;
import Excepciones.ExcepcionLectura;
import co.edu.unicesar.modelo.Publicacion;
import java.util.List;


public interface IPublicacionDao {
    
    void insertarPublicacion(Publicacion p) throws ExcepcionArchivo;
    List<Publicacion> leerPublicaciones() throws ExcepcionArchivo;
    Publicacion buscarPublicacion(Publicacion p) throws ExcepcionArchivo;
    Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionArchivo;

    
}
