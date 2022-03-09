package server.login;

import io.grpc.*;
import server.HashTable;

import java.util.*;
import java.util.stream.Collectors;

public class LoginServer {
    HashTable hashTable;
    Server loginServer;
    int currentServerPort;
    static List<FingerTableItem> fingerTable;
    int id;

    public LoginServer(int currentServerPort,int id, List<LoginServer> serverList){
        this.hashTable = new HashTable(id);
        this.currentServerPort = currentServerPort;
        this.id = id;
        this.fingerTable = fingerTable;



        this.loginServer = ServerBuilder
                .forPort(currentServerPort)
                .addService(new GrpcHashServiceImpl(hashTable, id, serverList))
                .build();
    }

    public LoginServer(int currentServerPort, int id){
        this.hashTable = new HashTable(id);
        this.currentServerPort = currentServerPort;
        this.id = id;
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


    public void fingerTable(LoginServer server, List<LoginServer> allServers) {

    server.fingerTable = new ArrayList<FingerTableItem>();

         id = server.id;

        // Teremos 7 linhas (bytes) pois o máximo de valor que aceitaremos é 128
        TreeMap<Integer, String> temp = new TreeMap<>();


        int index = 0;
        for (LoginServer value : allServers) {
            temp.put(value.id,"localhost@" + value.currentServerPort);
            index++;
        }

        for (int i = 0; i < 7; i++) {
            int ftpi = server.id + (int) Math.pow(2, i);// não se utiliza 2 ^ i-1 pq i já começa em 0 aqui

            if (ftpi >= Math.pow(2, 7)) {
                ftpi -= Math.pow(2, 7);
            }

            if (temp.ceilingKey(ftpi) != null) {
                server.fingerTable.add(new FingerTableItem(temp.ceilingKey(ftpi),temp.ceilingEntry(ftpi).getValue()));
            } else {
                server.fingerTable.add(new FingerTableItem(temp.firstKey(),temp.firstEntry().getValue()));
            }

        }

        System.out.println("\nServidor de ID: " + server.id + " - localhost@" + server.currentServerPort + "\nFinger Table:");
        // Impressão para conferência
        for (int i = 0; i < 7; i++) {
            System.out.println("| " + (i + 1) + " | " + server.fingerTable.get(i).idServer + " |");
        }


    }

}

