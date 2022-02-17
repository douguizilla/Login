package server.login;

import br.proto.services.GrpcHashServiceGrpc;
import io.grpc.*;
import server.HashTable;

public class LoginServer {
    HashTable hashTableB = new HashTable();
    Server loginServer;
    ResponsabilityRange responsabilityRange;
    int currentServerPort;
    String nextServerAddress; //"localhost@12345"

    public LoginServer(int currentServerPort, String nextServerAddress, ResponsabilityRange responsabilityRange){
        this.currentServerPort = currentServerPort;
        this.nextServerAddress = nextServerAddress;
        this.responsabilityRange = responsabilityRange;

        this.loginServer = ServerBuilder
                .forPort(currentServerPort)
                .addService(new GrpcHashServiceImpl(hashTableB, nextServerAddress, responsabilityRange))
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

