package common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class WorkJSON {

    public static String listToJson(List<Employee> list) {
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.toJson(list, listType);
    }

    public static void writeString(String json, String fileName) {
        try (FileWriter file = new FileWriter(fileName)) {
            file.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Employee> jsonToList(String json) {
        JSONParser parser = new JSONParser();
        List<Employee> list = new ArrayList<>();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            for (Object str : jsonArray) {
                Employee employee = gson.fromJson(str.toString(), Employee.class);
                list.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
