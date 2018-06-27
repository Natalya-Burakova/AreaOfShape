package mypackage.processing;

import mypackage.figures.Circle;
import mypackage.figures.Rectangle;
import mypackage.figures.Square;
import mypackage.figures.Triangle;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Analysis {

    private File xml;
    private File xsd;
    private HashMap<String, String>  memory = new HashMap<String, String>(); //хранит типы фигур

    public Analysis(File xml, File xsd){
        this.xml = xml;
        this.xsd  = xsd;
    }

    public void parseXsd() {
        Console c = System.console();
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(this.xsd);
            NodeList list = doc.getElementsByTagName("xs:complexType");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    NodeList listElement = element.getElementsByTagName("xs:element");
                    for (int j = 0; j < listElement.getLength(); j++) {
                        Node nodeElement = listElement.item(j);
                        if (nodeElement.getNodeType() == nodeElement.ELEMENT_NODE) {
                            Element car = (Element) nodeElement;
                            memory.put(car.getAttribute("name"), car.getAttribute("type"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            //System.out.print("Ошибка обработки файла.\n");
            c.printf("Ошибка обработки файла.\n");
        }
    }


    public void parserXml(){
        double squareSide, rectangleSide_1, rectangleSide_2, circleRadius, triangleSide_1, triangleSide_2, triangleSide_3;
        int countObject = 0;
        HashMap count = new HashMap();
        Console c = System.console();

        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(this.xml);
            NodeList list = doc.getElementsByTagName("square");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList listSide = eElement.getElementsByTagName("side");
                    squareSide = Double.parseDouble(listSide.item(0).getTextContent());
                    Square square = new Square(squareSide);
                    //System.out.printf("Площадь квадрата равна: %.3f. ", square.area());
                    //System.out.printf("Тип объекта: %s.\n", memory.get("square"));
                    c.printf("Площадь квадрата равна: %.3f.\n", square.area());
                    c.printf("Тип объекта: %s.\n", memory.get("square"));
                    countObject++;
                    count.put("square", i+1);
                }
            }

            NodeList list2 = doc.getElementsByTagName("rectangle");
            for (int i = 0; i < list2.getLength(); i++) {
                Node node = list2.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList listSide2 = eElement.getElementsByTagName("side");
                    rectangleSide_1 = Double.parseDouble(listSide2.item(0).getTextContent());
                    rectangleSide_2 = Double.parseDouble(listSide2.item(1).getTextContent());
                    Rectangle rectangle = new Rectangle(rectangleSide_1, rectangleSide_2);
                    //System.out.printf("Площадь прямоугольника равна: %.3f. ", rectangle.area());
                    //System.out.printf("Тип объекта: %s.\n", memory.get("rectangle"));
                    c.printf("Площадь прямоугольника равна: %.3f. ", rectangle.area());
                    c.printf("Тип объекта: %s.\n", memory.get("rectangle"));
                    countObject++;
                    count.put("rectangle", i+1);
                }
            }

            NodeList list3 = doc.getElementsByTagName("triangle");
            for (int i = 0; i < list3.getLength(); i++) {
                Node node = list3.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList listSide3 = eElement.getElementsByTagName("side");
                    triangleSide_1 = Double.parseDouble(listSide3.item(0).getTextContent());
                    triangleSide_2 = Double.parseDouble(listSide3.item(1).getTextContent());
                    triangleSide_3 = Double.parseDouble(listSide3.item(2).getTextContent());
                    Triangle triangle = new Triangle(triangleSide_1, triangleSide_2, triangleSide_3);
                    //System.out.printf("Площадь треугольника равна: %.3f. ", triangle.area());
                    //System.out.printf("Тип объекта: %s.\n", memory.get("triangle"));
                    c.printf("Площадь треугольника равна: %.3f. ", triangle.area());
                    c.printf("Тип объекта: %s.\n", memory.get("triangle"));
                    countObject++;
                    count.put("triangle", i+1);
                }
            }

            NodeList list4 = doc.getElementsByTagName("circle");
            for (int i = 0; i < list4.getLength(); i++) {
                Node node = list4.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    NodeList listSide4 = eElement.getElementsByTagName("radius");
                    circleRadius = Double.parseDouble(listSide4.item(0).getTextContent());
                    Circle circle = new Circle(circleRadius);
                    //System.out.printf("Площадь круга равна: %.3f. ", circle.area());
                    //System.out.printf("Тип объекта: %s.\n", memory.get("circle"));
                    c.printf("Площадь круга равна: %.3f. ", circle.area());
                    c.printf("Тип объекта: %s.\n", memory.get("circle"));
                    countObject++;
                    count.put("circle", i+1);
                }
            }

            //System.out.printf("Количество обрабатываемых объектов: %d.\n",countObject);
            c.printf("Количество обрабатываемых объектов: %d.\n",countObject);

            for(Object mem: memory.keySet())
                for (Object ct: count.keySet()) {
                    if (ct.equals(mem)) {
                        //System.out.printf("Количество объектов типа %s равно %d.\n", memory.get(mem), count.get(ct));
                        c.printf("Количество объектов типа %s равно %d.\n", memory.get(mem), count.get(ct));
                    }
                }
        } catch (ParserConfigurationException e) {
            //System.out.print("Ошибка обработки файла.\n");
            c.printf("Ошибка обработки файла.\n");
        } catch (SAXException e) {
            //System.out.print("Ошибка обработки файла.\n");
            c.printf("Ошибка обработки файла.\n");
        } catch (IOException e) {
            //System.out.print("Ошибка обработки файла.\n");
            c.printf("Ошибка обработки файла.\n");
        }
    }


}

