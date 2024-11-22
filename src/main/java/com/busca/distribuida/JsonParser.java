package com.busca.distribuida;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.Map;

public class JsonParser {
    public static void main(String[] args) {
        try {
            // Inicializa o Jackson ObjectMapper
            ObjectMapper mapper = new ObjectMapper();

            // Ler o JSON de forma segura
            @SuppressWarnings("unchecked")
            Map<String, Object> data = mapper.readValue(new File("data.json"), Map.class);

            // Exibe os dados no console
            System.out.println("Título: " + data.get("title"));
            System.out.println("Introdução: " + data.get("introduction"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
