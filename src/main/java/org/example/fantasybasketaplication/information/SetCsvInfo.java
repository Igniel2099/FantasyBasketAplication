package org.example.fantasybasketaplication.information;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SetCsvInfo {
    public ArrayList<ArrayList<String>> setInformationInArrayList(ArrayList<ArrayList<String>> data, ArrayList<String> newData) throws Exception {
        if (newData.size() > 4){
            throw new Exception("No puedes añadir una lista de datos mayor que 4");
        }
        data.add(newData);

        return data;

    }

    public void writeToCsv(ArrayList<ArrayList<String>> data) throws Exception {

        for (ArrayList<String> dato : data) {
            if (dato.size() > 4){
                throw new Exception("La lista de datos contiene una lista de datos mayor que 4");
            }
        }

        try (FileWriter writer = new FileWriter("registrosDeCompra.csv")) {
            // Escribir la cabecera
            for (ArrayList<String> dato : data) {
                for (String item : dato) {
                    writer.append(item).append(',');
                }
                writer.append('\n');
            }
            System.out.println("Archivo CSV escrito correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
