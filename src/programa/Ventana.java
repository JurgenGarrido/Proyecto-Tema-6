package programa;

import datos.Hash;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame {

    private JPanel panel;
    private JPanel panelDatos;
    private JTable tabla;
    private DefaultTableModel modeloT;
    private Hash[] tablaHash;
    private File archivo;
    private Auto autoSeleccionado;

    //Definición de botones
    JButton bInsertar = new JButton();
    JButton bBuscar = new JButton();
    JButton bEliminar = new JButton();
    JButton bCalcPrecio = new JButton();

    //Definición de cuadros de texto
    JTextField campoPlacas = new JTextField();
    JTextField campoNombre = new JTextField();
    JTextField campoPlacasDatos = new JTextField();
    JTextField campoNombreDatos = new JTextField();
    JTextField campoTiempoDatos = new JTextField();
    
    //Definición de etiquetas
    JLabel ePrecioDatos = new JLabel();

    public static final int TAM = 101;

    public Ventana() {
        super("Proyecto final");
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        archivo = new File("Auto.txt");
        tablaHash = new Hash[TAM];

        panel = new JPanel();
        panel.setLayout(null);

        //Declaración del panel de datos
        panelDatos = new JPanel();
        panelDatos.setLayout(null);

        panelDatos.setBounds(275, 140, 190, 410);
        panelDatos.setBackground(Color.GRAY);
        panel.add(panelDatos);

        this.setContentPane(panel);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        iniciarBotones();
        iniciarEtiquetas();
        iniciarCuadrosTexto();
        iniciarTabla();
        leer();   //Reservado para cuando se implementen las acciones de los botones
        eventos();
    }

    private void iniciarBotones() {
        bInsertar.setText("Insertar");
        bInsertar.setBounds(275, 10, 190, 30);
        bInsertar.setBackground(Color.decode("#24A745"));
        bInsertar.setForeground(Color.white);
        bInsertar.setFont(new Font("Roboto", 0, 15));
        panel.add(bInsertar);

        bBuscar.setText("Buscar");
        bBuscar.setBounds(275, 50, 190, 30);
        bBuscar.setBackground(Color.decode("#FCC00D"));
        bBuscar.setFont(new Font("Roboto", 0, 15));
        panel.add(bBuscar);

        bEliminar.setText("Eliminar");
        bEliminar.setBounds(275, 90, 190, 30);
        bEliminar.setBackground(Color.decode("#DC3445"));
        bEliminar.setFont(new Font("Roboto", 0, 15));
        bEliminar.setForeground(Color.white);
        panel.add(bEliminar);

        bCalcPrecio.setText("Calcular Precio");
        bCalcPrecio.setBounds(10, 285, 170, 50);
        bCalcPrecio.setBackground(Color.decode("#34495E"));
        bCalcPrecio.setFont(new Font("Roboto", 0, 20));
        bCalcPrecio.setForeground(Color.white);
        panelDatos.add(bCalcPrecio);
    }

    private void iniciarEtiquetas() {
        //ETIQUETAS DE FORMULARIO
        //Etiqueta de Placas
        JLabel ePlacas = new JLabel();
        ePlacas.setText("Placas: ");
        ePlacas.setBounds(5, 40, 100, 20);
        ePlacas.setHorizontalAlignment(SwingConstants.CENTER);
        ePlacas.setForeground(Color.black);
        ePlacas.setFont(new Font("Roboto", 0, 15));
        panel.add(ePlacas);

        //Etiqueta de Nombre
        JLabel eNombre = new JLabel();
        eNombre.setText("Nombre: ");
        eNombre.setBounds(5, 80, 100, 20);
        eNombre.setHorizontalAlignment(SwingConstants.CENTER);
        eNombre.setForeground(Color.black);
        eNombre.setFont(new Font("Roboto", 0, 15));
        panel.add(eNombre);

        //ETIQUETAS DE PANEL DE DATOS
        //Etiqueta de Descripción
        JLabel eDescDatos = new JLabel();
        eDescDatos.setText("Datos del vehiculo");
        eDescDatos.setBounds(20, 10, 150, 20);
        eDescDatos.setHorizontalAlignment(SwingConstants.CENTER);
        eDescDatos.setForeground(Color.white);
        eDescDatos.setFont(new Font("Roboto", 0, 15));
        panelDatos.add(eDescDatos);

        //Etiqueta de Nombre
        JLabel eNombreDatos = new JLabel();
        eNombreDatos.setText("Nombre");
        eNombreDatos.setBounds(20, 45, 150, 20);
        eNombreDatos.setHorizontalAlignment(SwingConstants.CENTER);
        eNombreDatos.setForeground(Color.black);
        eNombreDatos.setFont(new Font("Roboto", 0, 15));
        panelDatos.add(eNombreDatos);

        //Etiqueta de Placas
        JLabel ePlacasDatos = new JLabel();
        ePlacasDatos.setText("Placas");
        ePlacasDatos.setBounds(20, 125, 150, 20);
        ePlacasDatos.setHorizontalAlignment(SwingConstants.CENTER);
        ePlacasDatos.setForeground(Color.black);
        ePlacasDatos.setFont(new Font("Roboto", 0, 15));
        panelDatos.add(ePlacasDatos);

        //Etiqueta de Placas
        JLabel eTiempoDatos = new JLabel();
        eTiempoDatos.setText("Tiempo transcurrido");
        eTiempoDatos.setBounds(20, 205, 150, 20);
        eTiempoDatos.setHorizontalAlignment(SwingConstants.CENTER);
        eTiempoDatos.setForeground(Color.black);
        eTiempoDatos.setFont(new Font("Roboto", 0, 15));
        panelDatos.add(eTiempoDatos);

        //Etiqueta de Precio
        ePrecioDatos.setText(""); //Pnatilla al imprimir valor "Total: $ + precio"
        ePrecioDatos.setBounds(20, 360, 150, 20);
        ePrecioDatos.setHorizontalAlignment(SwingConstants.CENTER);
        ePrecioDatos.setForeground(Color.yellow);
        ePrecioDatos.setFont(new Font("Roboto", Font.BOLD, 20));
        panelDatos.add(ePrecioDatos);

    }

    private void iniciarCuadrosTexto() {
        //CAMPOS DE FORMULARIO
        //Campo de texto para placas
        campoPlacas.setBounds(105, 35, 150, 30);
        panel.add(campoPlacas);

        //Campo de texto para nombre
        campoNombre.setBounds(105, 75, 150, 30);
        panel.add(campoNombre);

        //CAMPOS DE PANEL DE DATOS
        //Campo de texto para placas
        campoPlacasDatos.setBounds(20, 75, 150, 30);
        campoPlacasDatos.setEditable(false);
        campoPlacasDatos.setHorizontalAlignment(SwingConstants.CENTER);
        campoPlacasDatos.setForeground(Color.red);
        panelDatos.add(campoPlacasDatos);

        //Campo de texto para nombre
        campoNombreDatos.setBounds(20, 155, 150, 30);
        campoNombreDatos.setEditable(false);
        campoNombreDatos.setHorizontalAlignment(SwingConstants.CENTER);
        campoNombreDatos.setForeground(Color.red);
        panelDatos.add(campoNombreDatos);

        //Campo de texto para tiempo
        campoTiempoDatos.setBounds(20, 235, 150, 30);
        campoTiempoDatos.setHorizontalAlignment(SwingConstants.CENTER);
        panelDatos.add(campoTiempoDatos);

    }

    private void iniciarTabla() {
        modeloT = new DefaultTableModel();
        tabla = new JTable();
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 140, 235, 410);

        //Columnas de la tabla
        String[] columnas = new String[]{"Clave", "Valores"};
        modeloT.setColumnIdentifiers(columnas);
        tabla.setModel(modeloT);

        panel.add(scroll);
    }

    private void eventos() {
        bInsertar.addActionListener((e) -> {
            String nombre = campoNombre.getText();
            String placas = campoPlacas.getText();
            if (!nombre.replaceAll(" ", "").equals("") && !placas.replaceAll(" ", "").equals("")) {
                Auto auto = new Auto(placas, nombre);
                Hash.insertaHash(tablaHash, auto);
                escribir();
            } else {
                JOptionPane.showMessageDialog(null, "Debes de rellenar todos los campos");
            }
        });

        bEliminar.addActionListener((e) -> {

        });

        bBuscar.addActionListener((e) -> {

        });
        
        tabla.getSelectionModel().addListSelectionListener((e) -> {
            
            int clave = (int)tabla.getValueAt(tabla.getSelectedRow(), 0);
            autoSeleccionado = tablaHash[clave].getDato();
            
            if(autoSeleccionado != null){
                campoNombreDatos.setText(autoSeleccionado.getNombre());
                campoPlacasDatos.setText(autoSeleccionado.getPlacas());
                campoTiempoDatos.setText(autoSeleccionado.getTiempo() + "");
                if(autoSeleccionado.getPrecio() != 0){
                    ePrecioDatos.setText("Total: $" + autoSeleccionado.getPrecio());
                }
            }else{
                campoNombreDatos.setText("");
                campoPlacasDatos.setText("");
                campoTiempoDatos.setText("");
                ePrecioDatos.setText("");
            }
        });
        
        bCalcPrecio.addActionListener((e) -> {
            int tiempo = Integer.parseInt(campoTiempoDatos.getText());
            autoSeleccionado.setTiempo(tiempo);
            autoSeleccionado.calcularPrecio();
            ePrecioDatos.setText("Total: $" + autoSeleccionado.getPrecio());
        });
    }

    private void leer() {
        if (archivo.exists()) {
            FileInputStream archivoLectura;
            ObjectInputStream leer;
            try {
                archivoLectura = new FileInputStream(archivo);
                leer = new ObjectInputStream(archivoLectura);
                Hash celda;
                int i = 0;
                modeloT.setRowCount(0);
                while (i < tablaHash.length) {
                    celda = (Hash) leer.readObject();
                    tablaHash[i] = celda;
                    if (i < tablaHash.length - 1) {
                        Object[] fila = {i, tablaHash[i].getDato()};
                        modeloT.addRow(fila);
                    }
                    i++;
                }
                leer.close();
                archivoLectura.close();
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println(ex);
            }
        } else {
            try {
                archivo.createNewFile();
                for (int i = 0; i < tablaHash.length; i++) {
                    tablaHash[i] = new Hash();
                }
                tablaHash[tablaHash.length - 1].setEstado(2);
                escribir();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    private int escribir() {
        int estado = 1;
        FileOutputStream archivoEscritura;
        ObjectOutputStream escribir;

        try {
            archivoEscritura = new FileOutputStream(archivo, false);
            escribir = new ObjectOutputStream(archivoEscritura);
            modeloT.setRowCount(0);
            for (int i = 0; i < tablaHash.length; i++) {
                escribir.writeObject(tablaHash[i]);
                if (i < tablaHash.length - 1) {
                    Object[] fila = {i, tablaHash[i].getDato()};
                    modeloT.addRow(fila);
                }
            }
            escribir.close();
            archivoEscritura.close();
        } catch (IOException ex) {
            estado = -1;
            System.err.println(ex);
        }

        return estado;
    }
}
