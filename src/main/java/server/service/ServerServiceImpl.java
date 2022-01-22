package server.service;

import br.proto.services.ServerServiceGrpc;
import br.proto.services.Services.*;
import io.grpc.stub.StreamObserver;
import server.HashTable;

public class ServerServiceImpl extends ServerServiceGrpc.ServerServiceImplBase {
    private HashTable hashTableA;
    public ServerServiceImpl(HashTable hashTable){
        this.hashTableA = hashTable;
    }

    @Override
    public void getService(
            ServiceRequest request,
            StreamObserver<ServiceResponse> responseObserver) {

        String key = request.getKey();
        String address = hashTableA.read(key);
        String[] parts = address.split("@");
        String ip = parts[0];
        int port = Integer.parseInt(parts[1]);

        ServiceResponse response = ServiceResponse
                .newBuilder()
                .setServiceIP(ip)
                .setServicePort(port)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
