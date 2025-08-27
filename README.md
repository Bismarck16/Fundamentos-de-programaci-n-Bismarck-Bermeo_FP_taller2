import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

class Factura {
    private String numero;
    private String cliente;
    private double monto;

    public Factura(String numero, String cliente, double monto) {
        this.numero = numero;
        this.cliente = cliente;
        this.monto = monto;
    }

    public String getNumero() {
        return numero;
    }

    public String getCliente() {
        return cliente;
    }

    public double getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "Factura N°: " + numero + ", Cliente: " + cliente + ", Monto: $" + monto;
    }
}

public class SistemaFacturas {
    private static ArrayList<Factura> facturas = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        do {
            String menu = """
                    MENÚ DE FACTURAS
                    1. Registrar factura
                    2. Consulta específica de una factura
                    3. Mostrar facturas en archivo de texto
                    4. Salir
                    Elija una opción:
                    """;

            opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1 -> registrarFactura();
                case 2 -> consultarFactura();
                case 3 -> guardarFacturasEnArchivo();
                case 4 -> JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                default -> JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        } while (opcion != 4);
    }

    private static void registrarFactura() {
        try {
            String numero = JOptionPane.showInputDialog("Ingrese número de factura:");
            String cliente = JOptionPane.showInputDialog("Ingrese nombre del cliente:");
            double monto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese monto de la factura:"));

            facturas.add(new Factura(numero, cliente, monto));
            JOptionPane.showMessageDialog(null, "Factura registrada exitosamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El monto debe ser un número válido.");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error: Debe ingresar todos los datos.");
        }
    }

    private static void consultarFactura() {
        String numero = JOptionPane.showInputDialog("Ingrese número de factura a consultar:");
        boolean encontrada = false;

        for (Factura f : facturas) {
            if (f.getNumero().equals(numero)) {
                JOptionPane.showMessageDialog(null, f.toString());
                encontrada = true;
                break;
            }
        }

        if (!encontrada) {
            JOptionPane.showMessageDialog(null, "Factura no se encuentra registrada.");
        }
    }

    private static void guardarFacturasEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("facturas.txt"))) {
            for (Factura f : facturas) {
                writer.write(f.toString());
                writer.newLine();
            }
            JOptionPane.showMessageDialog(null, "Facturas guardadas en facturas.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar las facturas en el archivo.");
        }
    }
}
