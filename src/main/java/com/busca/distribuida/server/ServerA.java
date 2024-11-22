package com.busca.distribuida.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerA {
    public static void main(String[] args) {
        int port = 12345; // Porta que o servidor vai escutar

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor A escutando na porta " + port);

            while (true) {
                // Aceitar conexão do cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Criar streams de entrada e saída
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Ler a substring enviada pelo cliente
                String substring = in.readLine();
                System.out.println("Substring recebida: " + substring);

                // Retornar a substring para o cliente (ecoar)
                out.println("Recebido pelo Servidor A: " + substring);

                // Fechar conexão com o cliente
                clientSocket.close();
            }

        } catch (IOException e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
