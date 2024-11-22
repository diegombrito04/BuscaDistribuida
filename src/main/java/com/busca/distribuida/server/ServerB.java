package com.busca.distribuida.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerB {
    public static void main(String[] args) {
        int port = 12346; // Porta que o Servidor B vai escutar

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor B escutando na porta " + port);

            while (true) {
                // Aceitar conexão do Servidor A
                Socket clientSocket = serverSocket.accept();
                System.out.println("Conexão recebida de: " + clientSocket.getInetAddress());

                // Criar streams de entrada e saída
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                // Ler mensagem recebida
                String message = in.readLine();
                System.out.println("Mensagem recebida: " + message);

                // Responder ao Servidor A
                out.println("Servidor B recebeu: " + message);

                // Fechar conexão com o cliente
                clientSocket.close();
            }

        } catch (IOException e) {
            System.err.println("Erro no Servidor B: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
