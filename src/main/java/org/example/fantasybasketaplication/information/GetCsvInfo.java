package org.example.fantasybasketaplication.information;

import java.io.FileWriter;
import java.io.IOException;

public class GetCsvInfo {
    public static void main(String[] args) {
        String[] header = {"DineroActual", "gasto", "NombreDelJugador"};
        String[][] data = {
                {"20000000.00","0","Ninguno"},
                {"19000000.00", "-43000.00", "Lebron"},
        };

        try (FileWriter writer = new FileWriter("registrosDeCompra.csv")) {
            // Escribir la cabecera
            for (String col : header) {
                writer.append(col).append(",");
            }
            writer.append("\n");

            // Escribir datos
            for (String[] row : data) {
                for (String field : row) {
                    writer.append(field).append(",");
                }
                writer.append("\n");
            }
            System.out.println("Archivo CSV escrito correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
