/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicesar.persistencia;

import Excepciones.ExcepcionArchivo;
import Excepciones.ExcepcionEscritura;
import Excepciones.ExcepcionLectura;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import co.edu.unicesar.modelo.Publicacion;
import co.edu.unicesar.modelo.Libro;


public class ImpArchivoTexto implements IPublicacionDao {

    private File archivo;
    private FileWriter modoEscritura;
    private Scanner modoLectura;

    public ImpArchivoTexto() {
        this("Aires.inv");
    }

    public ImpArchivoTexto(String path) {
        this.archivo = new File(path);
    }

 
    
    private Publicacion cargar(String data[]){
        
        String idbn = data[0];
        String titulo = data[1];
        String autor = data[2];
        int añoPublicacion = Integer.valueOf(data[3]);
        int Costo = Integer.valueOf(data[4]);
        int NoPagina = Integer.valueOf(data[5]);
        int NoEdicion = Integer.valueOf(data[6]);
        
        return new Libro(idbn, titulo, autor, añoPublicacion,Costo,NoPagina,NoEdicion);
    }


    
    private void renombrarArchivo(File tmp) throws IOException{
        if(!tmp.exists())
            tmp.createNewFile();
        
        if(!this.archivo.delete())
            throw new IOException("Error al eliminar el archivo principal");
        
        if(!tmp.renameTo(this.archivo))
            throw new IOException("Error al renombrar el archivo tmp");
        
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
     * @return the modoEscritura
     */
    public FileWriter getModoEscritura() {
        return modoEscritura;
    }

    /**
     * @param modoEscritura the modoEscritura to set
     */
    public void setModoEscritura(FileWriter modoEscritura) {
        this.modoEscritura = modoEscritura;
    }

    /**
     * @return the modoLectura
     */
    public Scanner getModoLectura() {
        return modoLectura;
    }

    /**
     * @param modoLectura the modoLectura to set
     */
    public void setModoLectura(Scanner modoLectura) {
        this.modoLectura = modoLectura;
    }

    @Override
    public void insertarPublicacion(Publicacion p) throws ExcepcionArchivo {
        PrintWriter pw = null;
        try {

            this.modoEscritura = new FileWriter(this.archivo, true);
            pw = new PrintWriter(this.modoEscritura);
            pw.println(p.getDataStringFormat());

        } catch (IOException e) {
            throw new ExcepcionArchivo("Error al abrir o crear archivo en modo escritura");
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }

    @Override
    public List<Publicacion> leerPublicaciones() throws ExcepcionArchivo {
        List<Publicacion> lista;
        try {
            this.modoLectura = new Scanner(this.archivo);
            lista = new ArrayList();
            while(this.modoLectura.hasNext()){
                String[] data = this.modoLectura.nextLine().split(";");
                Publicacion a = this.cargar(data);
                lista.add(a);
            }
            return lista;
        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("Error al leer archivo en modo lectura");
           
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }

    }

    @Override
    public Publicacion buscarPublicacion(Publicacion p) throws ExcepcionArchivo {
        Publicacion buscado = null;
        try {
            this.modoLectura = new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String[] data = this.modoLectura.nextLine().split(";");
                buscado = this.cargar(data);
                if(buscado.getIdbn()==p.getIdbn()){
                    return buscado;
                }    
            }
            return null;
        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("Error al buscar archivo en modo lectura");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }

    @Override
    public Publicacion eliminarPublicacion(Publicacion p) throws ExcepcionArchivo {
        Publicacion eliminado = null;
        ImpArchivoTexto archivoTemporal = new ImpArchivoTexto("Aires.tmp");
        try {
            this.modoLectura = new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String[] data = this.modoLectura.nextLine().split(";");
                Publicacion aux = this.cargar(data);
                if(aux.getIdbn()==p.getIdbn()){
                    eliminado=aux;
                }    
                else{
                   archivoTemporal.insertarPublicacion(aux);
                }
            }
            this.modoLectura.close();
            this.renombrarArchivo(archivoTemporal.archivo);
            return eliminado;
        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("Error al buscar archivo en modo lectura");
        }
        catch(IOException e){
            throw new ExcepcionArchivo(e.getMessage());
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }
    }

 

