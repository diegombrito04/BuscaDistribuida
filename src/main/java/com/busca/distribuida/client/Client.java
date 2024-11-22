package com.busca.distribuida.client;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        // Configurações do servidor
        String host = "localhost";
        int port = 12345; // Porta onde o Servidor A estará escutando

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Substring de exemplo para busca
            String substring = "machine learning";
            System.out.println("Enviando substring: " + substring);

            // Envia a substring para o Servidor A
            out.println(substring);

            // Recebe os resultados do Servidor A
            String response;
            System.out.println("Resultados recebidos:");
            while ((response = in.readLine()) != null) {
                System.out.println(response);
            }

        } catch (IOException e) {
            System.err.println("Erro na conexão com o servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
