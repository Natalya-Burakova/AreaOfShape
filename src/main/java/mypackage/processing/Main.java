package mypackage.processing;

import mypackage.processing.Analysis;
import mypackage.processing.CheckOfCorrectness;

import java.io.Console;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        Console c = System.console();
        if(args.length != 2)
            System.exit(-1);
        String xsd = args[0]; //xsd
        String xml = args[1]; //xml

        //String xsd = "src/example.xsd";
        //String xml = "src/example-file.xml";

        //проверяем файл
        CheckOfCorrectness check = new CheckOfCorrectness(new File(xsd), new File(xml));
        Boolean flagCheck =  check.scanFiles();

        // если файлы корректные
        if(flagCheck){
            c.printf("Файлы корректные. Начинается обработка.\n");
            Analysis analysis = new Analysis(new File(xml), new File(xsd));
            analysis.parseXsd();
            analysis.parserXml();
            c.printf("Обработка завершена.\n");
        }
        else
            System.exit(0);
    }
}
