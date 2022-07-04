/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicesar.modelo;




public class Libro extends Publicacion {
    
    private String Idbn;
    private String Titulo;
    private String Autor;
    private int añoPublicacion;
    private int Costo;
    private int nPaginas;
    private int edicion;

    public Libro(String idbn) {
        super(idbn);
    }

    public Libro() {
    }

    public Libro(int nPaginas, int edicion, String idbn, String titulo, String autor, int anio, double costo) {
        super(idbn, titulo, autor, anio, costo);
        this.nPaginas = nPaginas;
        this.edicion = edicion;
    }

    public Libro(int bn, String titulo, String autor, int añopublicacion, double costo, int noPagina, int noEdicion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Libro(String idbn, String titulo, String autor, int añoPublicacion, int Costo, int NoPagina, int NoEdicion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the nPaginas
     */
    public int getnPaginas() {
        return nPaginas;
    }

    /**
     * @param nPaginas the nPaginas to set
     */
    public void setnPaginas(int nPaginas) {
        this.nPaginas = nPaginas;
    }

    /**
     * @return the edicion
     */
    public int getEdicion() {
        return edicion;
    }

    /**
     * @param edicion the edicion to set
     */
    public void setEdicion(int edicion) {
        this.edicion = edicion;
    }

    @Override
    public String toString() {
        return "Libro{" +super.toString()+ ", nPaginas=" + this.nPaginas + ", edicion=" + edicion + '}';
    }

    @Override
    public String getDataStringFormat() {
        return "L;" + this.idbn + ";" + this.titulo + ";" + this.autor + ";" + this.anio + ";" + this.costo + ";" + this.nPaginas + ";" + this.edicion + "\n";
    }

    @Override
    public String getStringFormat() {
        
        String cadena = String.format("%-10s %-10s %-30s %-30s %-10d %-10d %-10s %-10s %-10s", 
                "Libro", this.idbn, this.titulo, this.autor, this.nPaginas, this.edicion, "","","");
        return cadena;
    }

   
    
    
}