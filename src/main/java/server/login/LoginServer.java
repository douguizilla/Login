package server.login;

import io.grpc.*;
import server.HashTable;

import java.util.*;
import java.util.stream.Collectors;

public class LoginServer {
    HashTable hashTable;
    Server loginServer;
    //ResponsabilityRange responsabilityRange;
    int currentServerPort;
    String nextServerAddress; //"localhost@12345"
    //Map<Integer,String> ft;
    static Object[][] ft;
    int id;

    public LoginServer(int currentServerPort, String nextServerAddress, int id, List<LoginServer> serverList){
        this.hashTable = new HashTable(id);
        this.currentServerPort = currentServerPort;
        this.nextServerAddress = nextServerAddress;
//        this.responsabilityRange = responsabilityRange;
        this.id = id;
        this.ft = ft;



        this.loginServer = ServerBuilder
                .forPort(currentServerPort)
                .addService(new GrpcHashServiceImpl(hashTable, id, serverList))
                .build();
    }

    public LoginServer(int currentServerPort, String nextServerAddress, int id){
        this.hashTable = new HashTable(id);
        this.currentServerPort = currentServerPort;
        this.nextServerAddress = nextServerAddress;
//        this.responsabilityRange = responsabilityRange;
        this.id = id;
        this.ft = ft;

    }

    public void start(){
        try{
            loginServer.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void awaitTermination(){
        try{
            loginServer.awaitTermination();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

//    public void fingerTable1(int currentServerId, List<LoginServer> serverList){
//        serverList.stream().
//                filter(eachServer -> eachServer.id != currentServerId)
//                .forEach(eachServer -> {
//                    ft.put(eachServer.id,"localhost@" + eachServer.currentServerPort);
//                });
//    }

    public void fingerTable(LoginServer server, List<LoginServer> allServers) {
            server.ft = new Object[7][2];
         int id, predecessor, sucessor;
         id = server.id;

        // server.ft = new Object[7][2]; //M linhas e 2 colunas (ID, Endereço)
        //5 linhas de acordo com a conversão binaria: 16,8,4,2,1

        // Teremos 7 linhas pois o máximo de valor que aceitaremos é 128
        TreeMap<Integer, String> temp = new TreeMap<>();


        //String[] array = new String[allServers.size()];
        int index = 0;
        for (LoginServer value : allServers) {
            temp.put(value.id,"localhost@" + value.currentServerPort);
            index++;
        }
//
//
//        // Salvando o ID do servidor anterior
//        if (temp.floorKey(server.id) != null) {
//            predecessor = temp.floorKey(id);
//        } else {
//            predecessor = temp.lastKey();
//        }

        for (int i = 0; i < 7; i++) {
            int ftpi = server.id + (int) Math.pow(2, i);// não se utiliza 2 ^ i-1 pq i já começa em 0 aqui

            if (ftpi >= Math.pow(2, 7)) {
                ftpi -= Math.pow(2, 7);
            }

            if (temp.ceilingKey(ftpi) != null) {
                server.ft[i][0] = temp.ceilingKey(ftpi);
            } else {
                server.ft[i][0] = temp.firstKey();
            }

            server.ft[i][1] = temp.get((int) server.ft[i][0]);
        }

        // Impressão para conferência
        System.out.println("\nServidor de ID: " + server.id + " - localhost@" + server.currentServerPort + "\nFinger Table:");
        for (int i = 0; i < 7; i++) {
            System.out.println("| " + (i + 1) + " | " + (int) server.ft[i][0] + " |");
        }

    }

}

