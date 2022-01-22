package server.login;

import br.proto.services.GrpcHashServiceGrpc;
import br.proto.services.Services.*;
import io.grpc.stub.StreamObserver;
import server.HashTable;

public class GrpcHashServiceImpl extends GrpcHashServiceGrpc.GrpcHashServiceImplBase {
    private HashTable hashTableB;

    GrpcHashServiceImpl(HashTable hashTable) {
        this.hashTableB = hashTable;
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {
        String key = request.getKey();
        String value = request.getValue();

        int result = hashTableB.add(key, value);

         CreateResponse response;
        if (result == 1) {
            System.out.println("CREATE GRPC REQ KEY: " + key + ", VALUE: " + value + ", STATUS: SUCCESS");
            response =  CreateResponse.newBuilder().setResponse(true).build();
        } else {
            System.out.println("CREATE GRPC REQ KEY: " + key + ", VALUE: " + value + ", STATUS: FAILURE");
            response =  CreateResponse.newBuilder().setResponse(false).build();
        }

        hashTableB.showAll();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void read( ReadRequest request, StreamObserver< ReadResponse> responseObserver) {
        String key = request.getKey();
        String value;

        value = hashTableB.read(key);
         ReadResponse response;
        if (value != null) {
            System.out.println("READ GRPC REQ KEY: " + key + ", VALUE: " + value + ", STATUS: SUCCESS");
            response =  ReadResponse.newBuilder().setKey(key).setValue(value).build();
        } else {
            System.out.println("READ GRPC REQ KEY: " + key + ", VALUE: null, STATUS: FAILURE");
            response =  ReadResponse.newBuilder().setKey(key).setValue("").build();
        }

        hashTableB.showAll();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void update( UpdateRequest request, StreamObserver< UpdateResponse> responseObserver) {
        String key = request.getKey();
        String value = request.getValue();

        String oldValue = hashTableB.read(key);
        int result = hashTableB.update(key, value);
         UpdateResponse response;
        if (result == 1) {
            System.out.println("UPDATE GRPC REQ KEY: " + key + ", CHANGE OLD_VALUE: " + oldValue + ", TO NEW_VALUE: " + value + ", STATUS: SUCCESS");
            response =  UpdateResponse.newBuilder().setResponse(true).build();
        } else {
            System.out.println("UPDATE GRPC REQ KEY: " + key + ", VALUE: null, STATUS: FAILURE");
            response =  UpdateResponse.newBuilder().setResponse(false).build();
        }

        hashTableB.showAll();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void delete( DeleteRequest request, StreamObserver< DeleteResponse> responseObserver) {
        String key = request.getKey();
        String value;

        value = hashTableB.remove(key);
         DeleteResponse response;
        if (value != null) {
            System.out.println("DELETE GRPC REQ KEY: " + key + ", RETURN_VALUE: " + value + ", STATUS: SUCCESS");
            response =  DeleteResponse.newBuilder().setResponse(true).setMessage(value).build();
        } else {
            System.out.println("DELETE GRPC REQ KEY: " + key + ", VALUE: null, STATUS: FAILURE");
            response =  DeleteResponse.newBuilder().setResponse(false).setMessage("").build();
        }

        hashTableB.showAll();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void exit( ExitRequest request, StreamObserver< ExitResponse> responseObserver) {
         ExitResponse response;
        response =  ExitResponse.newBuilder().setResponse(true).build();

        System.out.println("LOGOUT CLIENT GRPC");

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


}
