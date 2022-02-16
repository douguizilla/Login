package server.login;

import br.proto.services.ServerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.HashTable;

public class LoginServer {
    static HashTable hashTableB = new HashTable();
    static Server loginServer;
    static ResponsabilityRange responsabilityRange;
    static int currentServerPort;
    static ServerServiceGrpc.ServerServiceBlockingStub serviceStub;


    public LoginServer(int currentServerPort, String host, int nextServerPort, ResponsabilityRange responsabilityRange){
        this.currentServerPort = currentServerPort;

        ManagedChannel servicesChannel = ManagedChannelBuilder
                .forAddress(host, nextServerPort)
                .usePlaintext()
                .build();

        this.serviceStub =  ServerServiceGrpc.newBlockingStub(servicesChannel);

        this.responsabilityRange = responsabilityRange;

    }

    public static void main(String[] args) {
        loginServer = ServerBuilder
                .forPort(currentServerPort)
                .addService(new GrpcHashServiceImpl(hashTableB, serviceStub, responsabilityRange))
                .build();

        try{
            loginServer.start();
            loginServer.awaitTermination();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

