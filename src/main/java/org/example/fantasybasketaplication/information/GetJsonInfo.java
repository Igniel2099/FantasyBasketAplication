package org.example.fantasybasketaplication.information;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.fantasybasketaplication.controllers.BuyController;

import java.io.*;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Esta clase se encarga de gestionar la obtención de la información que contienen lo json
 */
public class GetJsonInfo {

    /**
     * Este método se encarga de obtener un json de un archivo, sea que este fuera del jar como empaquetado, para poder
     * convertirlo en un string.
     * @param pathJson esta es la ruta del archivo
     * @return nos devuelve un json en string
     */
    public String getJsonString(String pathJson){

        InputStream inputStream = getClass().getResourceAsStream(pathJson);

        // Intentamos obtener el InputStreamReader para después poder leer lo que hacemos y determinar si al intentar
        // obtener el dato como un inputStream nos da null o no, si es null creamos un FileReader
        // ¿Porqué?
        // Básicamente porque necesitamos que este método cree tanto un archivo fuera del jar (Para poder actualizarlo),
        // como obtener uno que este empaquetado en este para solo leer datos.
        InputStreamReader isr = null;
        try{
            if(inputStream == null){
                isr = new FileReader(pathJson);
            }else {
                isr = new InputStreamReader(inputStream,"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder jsonString = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(isr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString.toString();
    }

    /**
     * Este método se encarga de obtener los datos de data.json un archivo que se empaqueta dentro del jar, en donde
     * guardamos datos importantes.
     * @return me retorna una lista de Players
     */
    public List<Player> getPlayersJson(){

        String jsonString =getJsonString("/org/example/fantasybasketaplication/Json/data.json");

        Gson gson = new Gson();


        // Definir el tipo de datos que queremos deserializar
        Type tipoDeListaPersonas = new TypeToken<ArrayList<Player>>(){}.getType();

        // Deserializar la cadena JSON en una lista de players
        return gson.fromJson(jsonString, tipoDeListaPersonas);
    }


    /**
     * Este método se encarga de obtener datos de los archivos .json
     * @param pathJson esta es la ruta del archivo
     * @return me devuelve una lista de HashMap's que contiene la información de los archivos .json
     */
    public List<HashMap<String, Object>> getPlayersHashMap(String pathJson){
        Gson gson = new Gson();
        List<HashMap<String, Object>>  players;
        String jsonString =getJsonString(pathJson);

        Type listType = new TypeToken<List<HashMap<String, Object>>>(){}.getType();

            // Convertir el JSON en una lista de HashMap
        players = gson.fromJson(jsonString, listType);

        return players;
    }

    /**
     * Este método sobrecargado se encarga de que cada que lo llame sin poner un paramétro el que use sera el archivo
     * data.json
     * @return me devuelve lo mismo que el método normal, una lista de hashmap's de los json
     */
    public List<HashMap<String, Object>> getPlayersHashMap(){
        return getPlayersHashMap("/org/example/fantasybasketaplication/Json/data.json");
    }


    /**
     * Este método se encarga de buscar a un jugador dentro de una lista de hashmap's
     * @param nameSearch el nombre del player que quiero encontrar
     * @return mne devuelve un hashmap que coincida con el nombre del jugador buscado.
     */
    public HashMap<String, Object> getSearchPlayer(String nameSearch){
        List<HashMap<String, Object>> players = getPlayersHashMap();

        HashMap<String, Object> hashMapPlayer = null;
        for (HashMap<String, Object> player : players) {

            if (player.get("name").equals(nameSearch)) {
                hashMapPlayer = player;
            }
        }

        return hashMapPlayer;
    }

}
