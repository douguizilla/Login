package server.login;

import br.proto.services.GrpcHashServiceGrpc;
import io.grpc.*;
import server.HashTable;

public class LoginServer {
    static HashTable hashTableB = new HashTable();
    static Server loginServer;
    static ResponsabilityRange responsabilityRange;
    static int currentServerPort;
    static String nextServerAddress;

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
            loginServer.awaitTermination();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

