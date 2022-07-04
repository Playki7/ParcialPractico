/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.modelo;

import Excepciones.ExcepcionArchivo;
import co.edu.unicesar.persistencia.*;
import java.util.List;


public class ListaPublicacion implements IPublicacionDao {
    
    private IPublicacionDao registroPublicaciones;

    public ListaPublicacion() {
        this.registroPublicaciones = new ArrayListPublicacion();
    }
    
    
    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionArchivo {
        
        this.registroPublicaciones.insertarPublicacion(p);
        
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionArchivo {
        
        return this.registroPublicaciones.leerPublicaciones();
        
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionArchivo {
        
        return this.registroPublicaciones.buscarPublicacion(p);
        
    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionArchivo {
        
        return this.registroPublicaciones.eliminarPublicacion(p);
        
    }
    

    
}
