package programa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

    }

    private void iniciarEtiquetas() {

    }

    private void iniciarCuadrosTexto() {

    }

    private void iniciarTabla() {
        modeloT = new DefaultTableModel();
        tabla = new JTable(modeloT);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(5, 80, 250, 470);

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
