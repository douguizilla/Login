package server.login;

import io.grpc.*;
import server.HashTable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LoginServer {
    HashTable hashTable;
    Server loginServer;
    //ResponsabilityRange responsabilityRange;
    int currentServerPort;
    String nextServerAddress; //"localhost@12345"
    Map<Integer,String> ft;
    int id;

    public LoginServer(int currentServerPort, String nextServerAddress, int id){
        this.hashTable = new HashTable(id);
        this.currentServerPort = currentServerPort;
        this.nextServerAddress = nextServerAddress;
//        this.responsabilityRange = responsabilityRange;
        this.id = id;
        this.ft = ft;



        this.loginServer = ServerBuilder
                .forPort(currentServerPort)
                .addService(new GrpcHashServiceImpl(hashTable, nextServerAddress, id))
                .build();
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

    public void fingerTable(int currentServerId, List<LoginServer> serverList){
        serverList.stream().
                filter(eachServer -> eachServer.id != currentServerId)
                .forEach(eachServer -> {
                    ft.put(eachServer.id,"localhost@" + eachServer.currentServerPort);
                });
    }

//    public void fingerTable(LoginServer server) {
//        server.ft = new Object[5][2]; //M linhas e 2 colunas (ID, Endereço)
//        //5 linhas de acordo com a conversão binaria: 16,8,4,2,1
//
//        TreeMap<Integer, String[]> temp = new TreeMap<>();
//        //NESSE TEMP PRECISAMOS COLOCAR O ID DO SERVIDOR CORRENTE E CONVERTER A LISTA DE SERVIDORES EM UMA STRING PARA QUE POSSA SER PREENCHIDO NA FINGER TABLE
//
//        for (int i = 0; i < 5; i++) {
//            int ftpi = server.id + (int) Math.pow(2, i);// não se utiliza 2 ^ i-1 pq i já começa em 0 aqui
//
//            if (ftpi >= Math.pow(2, 5)) {
//                ftpi -= Math.pow(2, 5);
//            }
//
//            if (temp.ceilingKey(ftpi) != null) {
//                server.ft[i][0] = temp.ceilingKey(ftpi);
//            } else {
//                server.ft[i][0] = temp.firstKey();
//            }
//
//            server.ft[i][1] = temp.get((int) server.ft[i][0]);
//        }
//
//        // Impressão para conferência
//        System.out.println("\nID: " + server.id + "\nFinger Table:");
//        for (int i = 0; i < 5; i++) {
//            System.out.println("| " + (i + 1) + " | " + (int) server.ft[i][0] + " |");
//        }
//
//    }

}

