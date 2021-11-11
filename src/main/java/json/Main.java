package json;

import common.Employee;
import common.WorkJSON;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("src/main/resources/data.json");
        List<Employee> list = WorkJSON.jsonToList(json);
        WorkJSON.listToJson(list);
        list.stream().forEach(System.out::println);
    }

    private static String readString(String filePath) {
        JSONParser parser = new JSONParser();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return parser.parse(reader).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
