package org.example.fantasybasketaplication.information;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetJsonInfo {
    public static void main(String[] args) {
        GetJsonInfo generateJsonInfo = new GetJsonInfo();
        List<Player> persons =  generateJsonInfo.getPersonsJson();

        for (Player person : persons) {
            System.out.println(person.getName());
        }

    }

    public List<Player> getPersonsJson(){
        Gson gson = new Gson();

        try (FileReader fr = new FileReader("src/main/java/org/example/fantasybasketaplication/information/data.json")){
            Type tipoDeListaPersonas = new TypeToken<ArrayList<Player>>(){}.getType();
            List<Player> personas = gson.fromJson(fr, tipoDeListaPersonas);

            for (Player persona : personas) {
                System.out.println(persona);
            }

            return personas;
        }catch (IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }


}
