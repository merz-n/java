package org.example;
import jakarta.xml.ws.Endpoint;
import org.example.webservice.LibraryWebServiceImpl;

public class Main {
    public static void main(String[] args) {
        String url = "http://localhost:8081/ws";
        Endpoint.publish(url, new LibraryWebServiceImpl());
        System.out.println("📡 SOAP-сервис запущен по адресу: " + url);
    }
}
