package xml_json;

import common.*;
import org.w3c.dom.*;

import javax.xml.parsers.*;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> list = parseXML("src/main/resources/data.xml");
        String json = WorkJSON.listToJson(list);
        WorkJSON.writeString(json, "src/main/resources/data2.json");
    }

    private static List<Employee> parseXML(String fileName) {
        List<Employee> employeeList = new ArrayList<Employee>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));

            Node node = document.getDocumentElement();
            NodeList nodeList = node.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node item = nodeList.item(i);
                if (item.getNodeName().equals("employee")) {
                    Employee employee = getEmployee(item);
                    if (employee == null){continue;}
                    employeeList.add(employee);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }

    private static Employee getEmployee(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;

            return new Employee(Long.parseLong(getTagValue("id", element)),
                    getTagValue("firstName", element),
                    getTagValue("lastName", element),
                    getTagValue("country", element),
                    Integer.parseInt(getTagValue("age", element)));
        }
        return null;
    }


}
