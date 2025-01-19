package org.example.fantasybasketaplication.information;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class SetJsonInfo {


    public String convertFormatJson(String name, String position, Double price, String team, String pathPhotoName, String pathPhotoTeam){

        System.out.println(String.format(Locale.US,"{ \"name\" : \"%s\",\"position\" : \"%s\",\"price\": %.2f, \"team\" : \"%s\" , \"pathPhotoName\" : \"%s\", \"pathPhotoTeam\" : \"%s\" }",
                name, position, price, team, pathPhotoName, pathPhotoTeam));
        return String.format(Locale.US,"{ \"name\" : \"%s\",\"position\" : \"%s\",\"price\": %.2f, \"team\" : \"%s\" , \"pathPhotoName\" : \"%s\", \"pathPhotoTeam\" : \"%s\" }",
                name, position, price, team, pathPhotoName, pathPhotoTeam);
    }

    public void addJsonToFile(String json) {
        // Definir la ruta donde se guardará el archivo fuera del JAR
        String filePath = "savePlayers.json"; // Guardado en el directorio de ejecución actual.
        GetJsonInfo getJsonInfo = new GetJsonInfo();
        List<HashMap<String, Object>> jsonList = getJsonInfo.getPlayersHashMap(filePath);
        Gson gson = new Gson();
        // Convertir el nuevo JSON a un Map
        HashMap<String, Object> newJsonMap = gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());

        // Agregar el nuevo objeto a la lista
        jsonList.add(newJsonMap);

        // Guardar la lista actualizada en el archivo JSON
        try (Writer writer = new FileWriter("savePlayers.json")) {
            gson.toJson(jsonList, writer);
            System.out.println("JSON actualizado guardado en: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
