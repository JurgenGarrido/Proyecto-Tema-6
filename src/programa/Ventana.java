package programa;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Ventana extends JFrame {

    private JPanel panel;
    private JTable tabla;
    private DefaultTableModel modeloT;

    public Ventana() {
        super("Proyecto final");
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        panel = new JPanel();
        panel.setLayout(null);

        this.setContentPane(panel);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        iniciarBotones();
        iniciarEtiquetas();
        iniciarCuadrosTexto();
        iniciarTabla();
//        leer();
    }

    private void iniciarBotones() {
        JButton bInsertar = new JButton();
        bInsertar.setText("Insertar");
        bInsertar.setBounds(275, 10, 190, 30);
        bInsertar.setBackground(Color.decode("#24A745"));
        bInsertar.setForeground(Color.white);
        bInsertar.setFont(new Font("Roboto", 0, 15));
        panel.add(bInsertar);
        
        JButton bBuscar = new JButton();
        bBuscar.setText("Buscar");
        bBuscar.setBounds(275, 50, 190, 30);
        bBuscar.setBackground(Color.decode("#FCC00D"));
        bBuscar.setFont(new Font("Roboto", 0, 15));
        panel.add(bBuscar);
        
        JButton bEliminar = new JButton();
        bEliminar.setText("Eliminar");
        bEliminar.setBounds(275, 90, 190, 30);
        bEliminar.setBackground(Color.decode("#DC3445"));
        bInsertar.setFont(new Font("Roboto", 0, 15));
        bEliminar.setForeground(Color.white);
        panel.add(bEliminar);
        
        
        
    }

    private void iniciarEtiquetas() {
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
    }

    private void iniciarCuadrosTexto() {
        //Campo de texto para placas
        JTextField campoPlacas = new JTextField();
        campoPlacas.setBounds(105, 35, 150, 30);
        panel.add(campoPlacas);
        
        //Campo de texto para nombre
        JTextField campoNombre = new JTextField();
        campoNombre.setBounds(105, 75, 150, 30);
        panel.add(campoNombre);
        
    }

    private void iniciarTabla() {
        modeloT = new DefaultTableModel();
        tabla = new JTable(modeloT);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(5, 140, 250, 410);
        
        //Columnas de la tabla
        String[] columnas = new String[]{"Clave", "Valores"};
        modeloT.setColumnIdentifiers(columnas);
        tabla.setModel(modeloT);

        panel.add(scroll);
    }

    private int generarClave(String placas) {//Tabla 101
        placas = placas.replaceAll("-", "");
        char[] datos = placas.toCharArray();
        int clave = 0;

        for (char dato : datos) {
            clave += dato;
        }

        return clave;
    }

    private void leer() {
        File archivo = new File("Auto.txt");
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                PrintWriter escribir = new PrintWriter(new FileWriter(archivo));
                for (int i = 0; i < 100; i++) {
                    escribir.println(i);
                }
                escribir.close();
            }
            
            BufferedReader leer = new BufferedReader(new FileReader(archivo));
        } catch (IOException ex) {
            System.err.println("Error");
        }

    }
}
