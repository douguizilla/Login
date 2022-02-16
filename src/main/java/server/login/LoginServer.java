package server.login;

import br.proto.services.GrpcHashServiceGrpc;
import io.grpc.*;
import server.HashTable;

public class LoginServer {
    static HashTable hashTableB = new HashTable();
    static Server loginServer;
    static ResponsabilityRange responsabilityRange;
    static int currentServerPort;
    static GrpcHashServiceGrpc.GrpcHashServiceBlockingStub serviceStub;
    static String host;
    static int nextServerPort;

    public LoginServer(int currentServerPort, String host, int nextServerPort, ResponsabilityRange responsabilityRange){
        this.currentServerPort = currentServerPort;
        this.host = host;
        this.nextServerPort = nextServerPort;
        this.responsabilityRange = responsabilityRange;

    }

    public static void main(String[] args) {

        ManagedChannel servicesChannel;

        do{

            try{
                servicesChannel = ManagedChannelBuilder
                        .forAddress("host", 12345)
                        .usePlaintext()
                        .build();

                if (servicesChannel.getState(true) == ConnectivityState.READY)
                    break;

            }catch (Exception e){

            }

        }while (true);

        serviceStub =  br.proto.services.GrpcHashServiceGrpc.newBlockingStub(servicesChannel);

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

