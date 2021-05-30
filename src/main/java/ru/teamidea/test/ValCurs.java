package ru.teamidea.test;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class ValCurs {
    public static final String XML_URL = "http://www.cbr.ru/scripts/XML_daily.asp";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        scanUserInput();
    }

    public static void scanUserInput() throws IOException, ParserConfigurationException, SAXException {
        System.out.println("Введите цифровой или буквенный код валюты:");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            getCurse(scanner.nextInt());
        } else if (scanner.hasNextLine()) {
            getCurse(scanner.nextLine());
        }
        scanner.close();
    }

    public static NodeList connectAndParseXML(String url) throws IOException, ParserConfigurationException, SAXException {
        InputStream cursXmL = new URL(url).openConnection().getInputStream();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(cursXmL);
        NodeList nodeList = doc.getDocumentElement().getElementsByTagName("Valute");
        cursXmL.close();
        return nodeList;
    }

    public static void getCurse(String charCode) throws IOException, ParserConfigurationException, SAXException {
        NodeList nodeList = connectAndParseXML(XML_URL);
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getChildNodes().item(1).getTextContent().equals(charCode)) {
                System.out.println("Текущий курс " + nodeList.item(i).getChildNodes().item(3).getTextContent() +
                        " составляет: " + nodeList.item(i).getChildNodes().item(4).getTextContent() +
                        " рублей за " + nodeList.item(i).getChildNodes().item(2).getTextContent() +
                        " " + nodeList.item(i).getChildNodes().item(1).getTextContent() + ".");
                return;
            }
        }
        System.out.println("Такой валюты нет в базе.");
    }

    public static void getCurse(int numCode) throws IOException, ParserConfigurationException, SAXException {
        NodeList nodeList1 = connectAndParseXML(XML_URL);
        for (int i = 0; i < nodeList1.getLength(); i++) {
            if (nodeList1.item(i).getChildNodes().item(0).getTextContent().equals(String.valueOf(numCode))) {
                System.out.println("Текущий курс " + nodeList1.item(i).getChildNodes().item(3).getTextContent() +
                        " составляет: " + nodeList1.item(i).getChildNodes().item(4).getTextContent() +
                        " рублей за " + nodeList1.item(i).getChildNodes().item(2).getTextContent() +
                        " " + nodeList1.item(i).getChildNodes().item(1).getTextContent() + ".");
                return;
            }
        }
        System.out.println("Такой валюты нет в базе.");
    }
}

