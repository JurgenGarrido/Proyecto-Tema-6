package datos;

public class Hash {

    int dato;
    int estado; //0 = Vacío, 1 = Eliminado, 2 = Ocupado

    static int funcion(int n, int m) {
        return ((n + 1) % m);
    }

    static void insertaHash(Hash[] h, int n) {
        int m = h.length;
        boolean i, rep;
        rep = i = false;
        int r, j;
        r = j = funcion(n, m);
        do {
            if (h[j].estado == 0 || h[j].estado == 1) {
                h[j].dato = n;
                h[j].estado = 2;
                i = true;
            } else {
                j++;
                if (j == r && rep) {
                    break;
                }
                if (j == m && !i) {
                    j = 0;
                    rep = true;
                }
            }
        } while (j < m && !i);
        if (i) {
            javax.swing.JOptionPane.showMessageDialog(null, "¡Elemento insertado con éxito!");
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, "¡Tabla llena!");
        }
    }

    static int buscaHash(Hash[] h, int n) {
        int m = h.length;
        int j = funcion(n, m);
        while (j < m) {
            if (h[j].estado == 0) {
                return -1;
            } else if (h[j].dato == n) {
                if (h[j].estado == 1) {
                    return -1;
                } else {
                    return j;
                }
            } else {
                j++;
            }
        }
        return -1;
    }

    static int eliminaHash(Hash[] h, int n) {
        int m = h.length;
        int i = buscaHash(h, n);
        if (i == -1) {
            return -1;
        } else {
            h[i].estado = 1;
            return 1;
        }
    }

    /*
    public static void main(String[] args) {
        try {
            int i, n, resp;
            int m = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Ingrese el tamaño de la tabla"));
            Hash[] h = new Hash[m];
            for (i = 0; i < m; i++) {
                h[i] = new Hash();
                h[i].estado = 0;
            }
            do {
                resp = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Menú Principal\n"
                        + "Insertar (1)\nBuscar (2)\nEliminar (3)\nSalir (4)"));
                switch (resp) {
                    case 1:
                        n = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Ingrese el número a ser insertado en la tabla:"));
                        Hash.insertaHash(h, n);
                        break;
                    case 2:
                        n = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Ingrese el número a ser buscado en la tabla:"));
                        i = Hash.buscaHash(h, n);
                        if (i == -1) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Número no encontrado");
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "Número encontrado: " + i);
                        }
                        break;
                    case 3:
                        n = Integer.parseInt(javax.swing.JOptionPane.showInputDialog("Ingrese el número a ser eliminado de la tabla:"));
                        i = Hash.eliminaHash(h, n);
                        if (i == -1) {
                            javax.swing.JOptionPane.showMessageDialog(null, "Número no encontrado");
                        } else {
                            javax.swing.JOptionPane.showMessageDialog(null, "Número eliminado con éxito");
                        }
                        break;
                    case 4:
                        System.exit(0);
                    default:
                }
            } while (resp != 4);
        } catch (NumberFormatException nfe) {
            javax.swing.JOptionPane.showMessageDialog(null, "Está Saliendo del Programa");
        } catch (OutOfMemoryError ome) {
            javax.swing.JOptionPane.showMessageDialog(null, "No Hay Espacio");
        }
    }
*/
}
