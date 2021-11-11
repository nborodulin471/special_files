package csv_json;

import com.opencsv.*;
import com.opencsv.bean.*;
import common.*;
import java.io.FileReader;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> list = parseCSV(columnMapping, "src/main/resources/data.csv");
        String json = WorkJSON.listToJson(list);
        WorkJSON.writeString(json, "src/main/resources/data.json");
    }

    private static List<Employee> parseCSV(String[] columnMapping, String fileName) {
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);

            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();

            return csv.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
