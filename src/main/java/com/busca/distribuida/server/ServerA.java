package com.busca.distribuida.server;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

public class ServerA {
    public static void main(String[] args) {
        int port = 12345; // Porta que o Servidor A vai escutar
        String serverBHost = "localhost"; // Endereço do Servidor B
        int serverBPort = 12346; // Porta do Servidor B

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor A escutando na porta " + port);

            while (true) {
                // Aceitar conexão do Cliente
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Criar streams de entrada e saída para o Cliente
                BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream(), true);

                // Ler mensagem do Cliente
                String messageFromClient = clientIn.readLine();
                System.out.println("Mensagem recebida do Cliente: " + messageFromClient);

                // Conectar ao Servidor B
                try (Socket serverBSocket = new Socket(serverBHost, serverBPort);
                     BufferedReader serverBIn = new BufferedReader(new InputStreamReader(serverBSocket.getInputStream()));
                     PrintWriter serverBOut = new PrintWriter(serverBSocket.getOutputStream(), true)) {

                    // Enviar mensagem para o Servidor B
                    serverBOut.println(messageFromClient);

                    // Ler resposta do Servidor B
                    String responseFromServerB = serverBIn.readLine();
                    System.out.println("Resposta recebida do Servidor B: " + responseFromServerB);

                    // Enviar resposta de volta ao Cliente
                    clientOut.println(responseFromServerB);
                }

                // Fechar conexão com o Cliente
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Erro no Servidor A: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
