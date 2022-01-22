package server.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import server.HashTable;

public class ServiceServer {
    HashTable hashTableA = new HashTable();
    static Server serviceServer;
    public static void main(String[] args) {


    }

    public int postService(
            String serviceName,
            String ipAddress,
            int port
            ){
        return hashTableA.add(serviceName,ipAddress+"@"+port);
    }
}
