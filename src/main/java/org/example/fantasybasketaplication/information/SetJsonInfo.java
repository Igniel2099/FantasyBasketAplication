package org.example.fantasybasketaplication.information;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;

import java.util.*;

public class SetJsonInfo {


    public String convertFormatJson(String name, String position, Double price, String team, String estado, String pathPhotoName, String pathPhotoTeam){

        return String.format(Locale.US,"{ \"name\" : \"%s\",\"position\" : \"%s\",\"price\": %.2f, \"team\" : \"%s\" , \"estado\" : \"%s\", \"pathPhotoName\" : \"%s\", \"pathPhotoTeam\" : \"%s\" }",
                name, position, price, team, estado, pathPhotoName, pathPhotoTeam);
    }

    public void addJsonToFile(String json, String filePath) {

        GetJsonInfo getJsonInfo = new GetJsonInfo();
        List<HashMap<String, Object>> jsonList = getJsonInfo.getPlayersHashMap(filePath);
        Gson gson = new Gson();
        // Convertir el nuevo JSON a un Map
        HashMap<String, Object> newJsonMap = gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {}.getType());

        // Agregar el nuevo objeto a la lista
        jsonList.add(newJsonMap);

        // Guardar la lista actualizada en el archivo JSON
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(jsonList, writer);
            System.out.println("JSON actualizado guardado en: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void writeJsonToFile(List<HashMap<String, Object>> listJson, String filePath) {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(listJson, writer);
            System.out.println("JSON actualizado guardado en: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteJsonFromFile(String filePath,String atributoSearch, String nameSearch) {
        GetJsonInfo getJsonInfo = new GetJsonInfo();
        List<HashMap<String, Object>> jsonList = getJsonInfo.getPlayersHashMap(filePath);
        int indexRemove = 0;
        for (int i = 0; i < jsonList.size(); i++) {
            HashMap<String, Object> jsonMap = jsonList.get(i);
            if (jsonMap.get(atributoSearch).equals(nameSearch)) {
                indexRemove = i;
            }
        }
        jsonList.remove(indexRemove);
        System.out.println(jsonList);
        writeJsonToFile(jsonList, filePath);
        System.out.println("Jugador " + nameSearch + "borrado con éxito del JSON guardado en: " + filePath);
    }

}
