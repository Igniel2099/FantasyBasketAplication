package org.example.fantasybasketaplication.information;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GetCsvInfo {

    public static void main(String[] args) {

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
}
