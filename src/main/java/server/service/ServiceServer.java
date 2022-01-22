package server.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.HashTable;

public class ServiceServer {
    static HashTable hashTableA = new HashTable();
    static Server serviceServer;
    public static void main(String[] args) {
        //publicando um serviço
        postService("login","localhost",12345);

        //criando a porta de conexão com o servidor que guarda os serviços
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

    public static int postService(
            String serviceName,
            String ipAddress,
            int port
    ){
        return hashTableA.add(serviceName,ipAddress+"@"+port);
    }
}
