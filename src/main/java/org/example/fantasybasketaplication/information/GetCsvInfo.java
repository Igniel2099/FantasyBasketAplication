package org.example.fantasybasketaplication.information;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetCsvInfo {

    public ArrayList<Double> getColumnDineroActual(){

        String lineas = readFromCsv();
        ArrayList<ArrayList<String>> listString = getInformationInArrayList(lineas);
        ArrayList<Double> listDouble = new ArrayList<>();
        for (int i = 1; i < listString.size(); i++) {
            listDouble.add(Double.parseDouble(listString.get(i).getFirst().trim().replaceAll("[.$]", "")));
        }
        System.out.println(listDouble);
        return listDouble;
    }


    public String readFromCsv() {
        String linea = "";
        StringBuilder csv = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader("registrosDeCompra.csv"))){

            while ((linea = br.readLine())!=null){
                csv.append(linea).append("\n");
            }

        }catch (IOException e){
            System.out.println("Error:" + e.getMessage());
        }
        return csv.toString();
    }

    public ArrayList<ArrayList<String>> getInformationInArrayList(String lineas) {
        String[] filas = lineas.split("\n");
        ArrayList<ArrayList<String>> datos = new ArrayList<>();

        for (String s : filas) {
            String[] fila = s.split(",");
            ArrayList<String> dato = new ArrayList<>(Arrays.asList(fila));
            datos.add(dato);
        }

        return datos;
    }


    public static ObservableList<TableRowData> readCSV(String filePath) throws IOException {
        ObservableList<TableRowData> dataList = FXCollections.observableArrayList();
        List<String> lines = Files.readAllLines(Paths.get(filePath));

        for (String line : lines) {
            String[] values = line.split(",");
            // Asumimos que los valores están en el orden: DineroActual, Gasto, NombreDelJugador
            String dineroActual = values[0].trim();
            String gasto = values[1].trim();
            String nombreJugador = values[2].trim();
            // Creamos un objeto TableRowData con los valores leídos
            dataList.add(new TableRowData(dineroActual, gasto, nombreJugador));
        }

        return dataList;
    }
}
