package com.freeagent.anagram.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class Configuracion {

    @Bean
    public List<String> diccionario() {
        return cargarDiccionario();
    }

    public static List<String> cargarDiccionario() {
        List<String> diccionario = new ArrayList<>();
        try {
            File myObj = new File("./src/main/resources/static/wordlist.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (!data.trim().isEmpty()) {
                    diccionario.add(data);
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return diccionario;
    }
}
