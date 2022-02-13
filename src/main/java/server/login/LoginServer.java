package server.login;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.HashTable;

public class LoginServer {
    static HashTable hashTableB = new HashTable();
    static Server loginServer;
    static Server nextServer;
    static ResponsabilityRange responsabilityRange;

    public LoginServer(ResponsabilityRange responsabilityRange, Server nextServer){
        this.responsabilityRange = responsabilityRange;
        this.nextServer = nextServer;
    }

    public static void main(String[] args) {
        loginServer = ServerBuilder
                .forPort(12345)
                .addService(new GrpcHashServiceImpl(hashTableB))
                .build();

        try{
            loginServer.start();
            loginServer.awaitTermination();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

