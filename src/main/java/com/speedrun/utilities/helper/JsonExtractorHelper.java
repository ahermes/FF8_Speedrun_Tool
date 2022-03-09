package com.speedrun.utilities.helper;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.speedrun.cardrng.object.json.CountRngJsonPage;

import java.io.FileReader;
import java.lang.reflect.Type;

import static com.speedrun.utilities.GlobalValues.ACCESSIBLE_RESSOURCES;

public class JsonExtractorHelper {

    public static Object getJson(String path, Class objectClass){
        Object res = null;
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            res = gson.fromJson(reader, objectClass);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        return res;
    }

    public static Object getJson(String path, Type objectClass){
        Object res = null;
        Gson gson = new Gson();
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            res = gson.fromJson(reader, objectClass);
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
        }
        return res;
    }
}
