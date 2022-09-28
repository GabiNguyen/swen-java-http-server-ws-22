package org.example;

import org.example.server.Server;
import org.example.application.demo.DemoApp;

public class Main {
    // java -jar main.jar --version --help
    //string array mit arguments
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        Server server = new Server(new DemoApp());
        //server.start();

        String response = server.handle("HTTP REQUEST STRING");
        System.out.println(response);
    }


}

//static: muss keine Instanz von einer Klasse erstellen (Methode aufrufen)
//interface: Liste, abstrakt arbeiten, mit Elementen und Ideen arbeiten
//final: Wert ohne setter c++: const