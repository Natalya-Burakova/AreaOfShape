package mypackage.processing;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.io.Console;
import java.io.File;
import java.io.IOException;

public class CheckOfCorrectness {

    private File xsd;
    private File xml;

    public CheckOfCorrectness(File xsd, File xml) {
        this.xsd = xsd;
        this.xml = xml;
    }

    public boolean scanFiles(){
        Console c = System.console();
        try {
            // Статичный объект newInstance() позволяет создавать объекты SchemaFactory используя заданную XML схему
            SchemaFactory shemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // Загрузка схемы
            Schema schema = shemaFactory.newSchema(new StreamSource(this.xsd));
            // Validator используется для проверки правильности документа.
            Validator validator = schema.newValidator();
            //  парсер, который создает деревья объектов DOM из документов XML.
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(this.xml);
            // Поверка правильности дерева DOM.
            validator.validate(new DOMSource(document));

        } catch (ParserConfigurationException e) {
            c.printf("Невозможно преобразовать XML в дерево DOM");
            return false;
        } catch (SAXParseException e) {
            c.printf("Неверный синтаксис файлов: %s и %s. \n", this.xsd.toString(), this.xml.toString());
            return false;
        } catch (SAXException e) {
            c.printf("Неверный синтаксис файлов: %s и %s. \n", this.xsd.toString(), this.xml.toString());
            return false;
        } catch (IOException e) {
            c.printf("Невозможно открыть файлы: %s и %s. \n", this.xsd.toString(), this.xml.toString());
            return false;
        }
        return true;
    }


}
