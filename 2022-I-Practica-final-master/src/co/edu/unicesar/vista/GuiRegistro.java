
package co.edu.unicesar.vista;

import Excepciones.ExcepcionArchivo;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.image.ImageObserver.ABORT;
import javax.swing.*;
import co.edu.unicesar.modelo.Libro;
import co.edu.unicesar.modelo.ListaPublicacion;


public class GuiRegistro extends JDialog implements ActionListener {
    private JPanel panelDatos, panelOpciones, panelPrincipal, panelTmp;
    private JLabel lbIsbn, lbTitulo, lbAutor, lbAñoPublicacion,lbCosto,lbNoPagina,lbNoEdicion;
    private JButton btnNuevo, btnGuardar, btnCancelar;
    private JTextField txtIsbn;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtAñoPublicacion;
    private JTextField txtCosto;
    private JTextField txtNoPagina;
    private JTextField txtNoEdicion;
    private ButtonGroup grupoOpciones;
    private Container contenedor;
    private ListaPublicacion modelo;

    public GuiRegistro(Frame frame, boolean bln) {
        super(frame, bln);
        this.modelo = new ListaPublicacion();
        this.setTitle("Programa Libros - Registro - Version 1.0");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.iniciarComponentes();
        this.setSize(900, 300);
        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
       
    }
    
    
    
    private void iniciarComponentes(){
        
        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new FlowLayout());
        
        this.panelPrincipal = new JPanel();
        this.panelPrincipal.setLayout(new BorderLayout());
        
        this.iniciarPanelDatos();
        this.iniciarPanelOpciones();
        
        this.panelPrincipal.setBorder(BorderFactory.createTitledBorder("Informacion del aire"));
        
        this.contenedor.add(this.panelPrincipal);
        
    
    }
    
    private void iniciarPanelDatos(){
        
        this.panelDatos = new JPanel();
        this.panelDatos.setLayout(new GridLayout(4,2,5,5));
        
        this.lbIsbn= new JLabel("No Isbn: ");
        this.lbTitulo= new JLabel("Titulo: ");
        this.lbAutor= new JLabel("Autor: ");
        this.lbAñoPublicacion = new JLabel("Año publicacion: ");
        this.lbCosto= new JLabel("Costo$: ");
        this.lbNoPagina = new JLabel("No Pagina: ");
        this.lbNoEdicion = new JLabel("No Edicion: ");
        
        this.txtIsbn = new JTextField(15);
        this.txtTitulo = new JTextField(15);
        this.txtAutor = new JTextField(15);
        this.txtAñoPublicacion = new JTextField(15);
        this.txtCosto = new JTextField(15);
        this.txtNoPagina = new JTextField(15);
        this.txtNoEdicion = new JTextField(15);
        
        
        
        this.panelDatos.add(this.lbIsbn);
        this.panelDatos.add(this.txtIsbn);
        
        this.panelDatos.add(this.lbTitulo);
        this.panelDatos.add(this.txtTitulo);
 
        this.panelDatos.add(this.lbAutor);
        this.panelDatos.add(this.txtAutor);
        
        this.panelDatos.add(this.lbAñoPublicacion);
        this.panelDatos.add(this.txtAñoPublicacion);
        
        this.panelDatos.add(this.lbCosto);
        this.panelDatos.add(this.txtCosto);
        
        this.panelDatos.add(this.lbNoPagina);
        this.panelDatos.add(this.txtNoPagina);
        
        this.panelDatos.add(this.lbNoEdicion);
        this.panelDatos.add(this.txtNoEdicion);
        
        this.panelDatos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.panelPrincipal.add(this.panelDatos, BorderLayout.CENTER);
    
    }
    
    private void iniciarPanelOpciones(){
        
        this.panelOpciones = new JPanel();
        this.panelOpciones.setLayout(new GridLayout(3,1,5,5));
        
        this.btnNuevo = new JButton("Nuevo");
        this.btnNuevo.addActionListener(this);
        
        this.btnGuardar = new JButton("Guardar");
        this.btnGuardar.addActionListener(this);
        
        this.btnCancelar= new JButton("Cancelar");
        this.btnCancelar.addActionListener(this);
        
        this.panelOpciones.add(this.btnNuevo);
        this.panelOpciones.add(this.btnGuardar);
        this.panelOpciones.add(this.btnCancelar);
        
        this.panelOpciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        this.panelPrincipal.add(this.panelOpciones, BorderLayout.EAST);
    
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==this.btnNuevo){
            //JOptionPane.showMessageDialog(this, "Click en boton nuevo", "Informe ActionListener", JOptionPane.INFORMATION_MESSAGE);
            Mensaje.mostrar(this, "Informe ActionListener", "Click en boton nuevo", ABORT);
        }
        
        if(e.getSource()==this.btnGuardar){
            //JOptionPane.showMessageDialog(this, "Click en boton Guardar", "Informe ActionListener", JOptionPane.ERROR_MESSAGE);
            this.guardarDatos();
        }
        
        if(e.getSource()==this.btnCancelar){
            this.limpiarCampos();
            JOptionPane.showMessageDialog(this, "Click en boton Cancelar", "Informe ActionListener", JOptionPane.QUESTION_MESSAGE);
        }
        
        
    }
    
    private void guardarDatos(){
        Libro libro = this.leerDatos();
        this.modelo.insertarPublicacion(libro);
        Mensaje.mostrar(this, "Exito", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);  
        
    }
    
    
    private Libro leerDatos(){
        
        int isbn = Integer.valueOf(this.txtIsbn.getText());
        String titulo = String.valueOf(this.txtTitulo.getText());
        String autor = String.valueOf(this.txtAutor.getText());
        int añopublicacion = Integer.valueOf(this.txtAñoPublicacion.getText());
        double costo = Integer.valueOf(this.txtCosto.getText());
        int noPagina = Integer.valueOf(this.txtNoPagina.getText());
        int noEdicion = Integer.valueOf(this.txtNoEdicion.getText());

                
        return new Libro(isbn, titulo, autor, añopublicacion,costo,noPagina,noEdicion);
    
    }
    
    private void limpiarCampos(){
        this.txtIsbn.setText(null);
        this.txtTitulo.setText(null);
        this.txtAutor.setText(null);
        this.txtAñoPublicacion.setText(null);
        this.txtCosto.setText(null);
        this.txtNoPagina.setText(null);
        this.txtNoEdicion.setText(null);
    }
    
}
