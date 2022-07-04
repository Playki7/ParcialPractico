/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicesar.persistencia;

import Excepciones.ExcepcionArchivo;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import co.edu.unicesar.modelo.Libro;
import co.edu.unicesar.modelo.Publicacion;


public class ImpArchivoObjeto implements IPublicacionDao {

    private File archivo;
    private FileInputStream modoLectura;
    private FileOutputStream modoEscritura;

    public ImpArchivoObjeto() {
        this("Aires.obj");
    }

    public ImpArchivoObjeto(String path) {
        this.archivo = new File(path);
    }

    private void guardar(List<Publicacion> lista) throws ExcepcionArchivo {
        ObjectOutputStream oos = null;
        try {
            this.modoEscritura = new FileOutputStream(this.archivo);
            oos = new ObjectOutputStream(this.modoEscritura);
            oos.writeObject(lista);
            oos.close();

        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("El Archivo de escritura no existe, o no se puede abrir");
        } catch (SecurityException e) {
            throw new ExcepcionArchivo("No tiene permiso de escritura sobre el archivo");
        } catch (IOException e) {
            throw new ExcepcionArchivo("Error al escribir en el archivo");
        } catch (NullPointerException e) {
            throw new ExcepcionArchivo("Los datos de la lista son null");
        }

    }

    private List<Publicacion> cargarArchivo() throws ExcepcionArchivo {

        ObjectInputStream ois = null;

        if (!this.archivo.exists()) {
            return new ArrayList<Publicacion>();
        }
        try {
            this.modoLectura = new FileInputStream(this.archivo);
            ois = new ObjectInputStream(this.modoLectura);
            List<Publicacion> lista = (List<Publicacion>) ois.readObject();
            ois.close();
            return lista;

        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("El Archivo de lectura no existe, o no se puede abrir");
        } catch (SecurityException e) {
            throw new ExcepcionArchivo("No tiene permiso de lectura sobre el archivo");
        } catch (StreamCorruptedException e) {
            throw new ExcepcionArchivo("Error con los datos de flujo de cierre del objeto");
        } catch (IOException e) {
            throw new ExcepcionArchivo("Error al leer en el archivo");
        } catch (NullPointerException e) {
            throw new ExcepcionArchivo("El archivo a leer es null");
        }
        catch(ClassNotFoundException e){
            throw new ExcepcionArchivo("No existe la claase definida para el objeto leido");
        }

    }


    /**
     * @return the archivo
     */
    public File getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the modoLectura
     */
    public FileInputStream getModoLectura() {
        return modoLectura;
    }

    /**
     * @param modoLectura the modoLectura to set
     */
    public void setModoLectura(FileInputStream modoLectura) {
        this.modoLectura = modoLectura;
    }

    /**
     * @return the modoEscritura
     */
    public FileOutputStream getModoEscritura() {
        return modoEscritura;
    }

    /**
     * @param modoEscritura the modoEscritura to set
     */
    public void setModoEscritura(FileOutputStream modoEscritura) {
        this.modoEscritura = modoEscritura;
    }
    


    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionArchivo{
            List<Publicacion> lista = this.cargarArchivo();
            lista.add(p);
            this.guardar(lista);

    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionArchivo{
        return this.cargarArchivo();
    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionArchivo {
        List<Publicacion> lista = this.cargarArchivo();
        Publicacion buscado=null;
        for(Publicacion i: lista){
            if(i.getIdbn()==p.getIdbn()){
                buscado=i;
                break;
            }
        }
        return buscado;
    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionArchivo {
        List<Publicacion> lista = this.cargarArchivo();
        Publicacion eliminar=null;
        Iterator<Publicacion>i = lista.iterator();
        
        while(i.hasNext()){
            Publicacion aux= i.next();
            if(aux.getIdbn()==p.getIdbn()){
                eliminar=aux;
                i.remove();
            }
        }
        
        this.guardar(lista);
        
        return eliminar;
        
    }

    }




