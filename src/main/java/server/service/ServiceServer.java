package server.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.HashTable;

public class ServiceServer {
    static HashTable hashTableA = new HashTable();
    static Server serviceServer;
    public static void main(String[] args) {
        serviceServer = ServerBuilder
                .forPort(54321)
                .addService(new ServerServiceImpl(hashTableA))
                .build();

        try{
            serviceServer.start();
            serviceServer.awaitTermination();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int postService(
            String serviceName,
            String ipAddress,
            int port
            ){
        return hashTableA.add(serviceName,ipAddress+"@"+port);
    }
}
