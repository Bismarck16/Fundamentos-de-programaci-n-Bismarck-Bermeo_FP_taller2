import java.util.Scanner;

public class Fundamentosdeprogramacion_2_bismarck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int asientos_disponibles = 100;
        int tiempo_multa = 12;
        int tiempo_bloqueo_total = 3;
        double precio_boleto = 100.0;

        System.out.println("Ingrese nombre:");
        String nombre = sc.nextLine();

        System.out.println("Ingrese número de documento:");
        String documento = sc.nextLine();

        System.out.println("Ingrese hora del vuelo (en formato 24h, solo la hora):");
        int hora_vuelo = sc.nextInt();

        System.out.println("Ingrese hora actual (en formato 24h, solo la hora):");
        int hora_actual = sc.nextInt();
        sc.nextLine();

        System.out.println("Tipo de boleto (económico/business/premium):");
        String tipo_boleto = sc.nextLine();

        int diferencia_horas = hora_vuelo - hora_actual;

        if (diferencia_horas < tiempo_bloqueo_total) {
            System.out.println("No se puede reservar, cambiar o cancelar el vuelo con menos de 3 horas de anticipación.");
            sc.close();
            return;
        }

        if (asientos_disponibles == 0) {
            System.out.println("No hay asientos disponibles.");
            sc.close();
            return;
        }

        asientos_disponibles--;

        System.out.println("Ingrese peso del equipaje de mano (kg):");
        double peso_mano = sc.nextDouble();

        if (peso_mano > 10) {
            System.out.println("El peso de equipaje de mano excede el límite permitido.");
            sc.close();
            return;
        }

        System.out.println("Ingrese peso del equipaje en bodega (kg):");
        double peso_bodega = sc.nextDouble();

        if (peso_bodega > 20) {
            System.out.println("El peso del equipaje en bodega excede el límite.");
            sc.close();
            return;
        }

        sc.nextLine();

        System.out.println("¿Desea cancelar el vuelo? (sí/no):");
        String cancelar = sc.nextLine();

        if (cancelar.equalsIgnoreCase("sí")) {
            if (diferencia_horas < tiempo_multa) {
                double multa = precio_boleto * 0.20;
                System.out.println("Cancelación dentro de las 12 horas. Multa del 20%: " + multa);
                System.out.println("Ruta asignada: ruta boleto 2");
            } else {
                System.out.println("Cancelación exitosa sin penalidad.");
                System.out.println("Ruta asignada: ruta de salida");
            }
        } else {
            System.out.println("Reserva completada para " + nombre);
            System.out.println("Documento: " + documento);
            System.out.println("Tipo de boleto: " + tipo_boleto);

            if (tipo_boleto.equalsIgnoreCase("económico")) {
                System.out.println("Ruta asignada: 1 escala.");
            } else if (tipo_boleto.equalsIgnoreCase("business")) {
                System.out.println("Ruta asignada: vuelo directo.");
            } else if (tipo_boleto.equalsIgnoreCase("premium")) {
                System.out.println("Ruta asignada: directo + servicios adicionales.");
            } else {
                System.out.println("Ruta asignada: ruta boleto 1");
            }

            if (diferencia_horas < tiempo_multa) {
                double multa = precio_boleto * 0.20;
                System.out.println("Reserva dentro de las 12 horas. Multa del 20%: " + multa);
            }

            System.out.println("Precio del boleto: " + precio_boleto);
            System.out.println("Asientos restantes: " + asientos_disponibles);
        }

        sc.close();
    }
}