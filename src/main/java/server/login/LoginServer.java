package server.login;

import io.grpc.*;
import server.HashTable;

public class LoginServer {
    HashTable hashTable;
    Server loginServer;
    ResponsabilityRange responsabilityRange;
    int currentServerPort;
    String nextServerAddress; //"localhost@12345"
    Object[][] ft;
    int id;

    public LoginServer(int currentServerPort, String nextServerAddress, ResponsabilityRange responsabilityRange){
        this.hashTable = new HashTable(responsabilityRange.getIdServer());
        this.currentServerPort = currentServerPort;
        this.nextServerAddress = nextServerAddress;
        this.responsabilityRange = responsabilityRange;
        this.ft = ft;
        this.id = id;


        this.loginServer = ServerBuilder
                .forPort(currentServerPort)
                .addService(new GrpcHashServiceImpl(hashTable, nextServerAddress, responsabilityRange))
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

}

