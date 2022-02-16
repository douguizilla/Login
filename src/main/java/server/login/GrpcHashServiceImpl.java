package server.login;

import br.proto.services.GrpcHashServiceGrpc;
import br.proto.services.Services.*;
import io.grpc.stub.StreamObserver;
import server.HashTable;

public class GrpcHashServiceImpl extends GrpcHashServiceGrpc.GrpcHashServiceImplBase {
    private HashTable hashTableB;
    private GrpcHashServiceGrpc.GrpcHashServiceBlockingStub nextServerStub;
    private ResponsabilityRange responsabilityRange;

    GrpcHashServiceImpl(HashTable hashTable, GrpcHashServiceGrpc.GrpcHashServiceBlockingStub serviceStub, ResponsabilityRange responsabilityRange) {
        this.hashTableB = hashTable;
        this.nextServerStub = serviceStub;
        this.responsabilityRange = responsabilityRange;
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {

        String key = request.getKey();
        String value = request.getValue();

        if (isResponsable(key)) {
            int result = hashTableB.add(key, value);

            CreateResponse response;
            if (result == 1) {
                System.out.println("CREATE GRPC REQ KEY: " + key + ", VALUE: " + value + ", STATUS: SUCCESS");
                response = CreateResponse.newBuilder().setResponse(true).build();
            } else {
                System.out.println("CREATE GRPC REQ KEY: " + key + ", VALUE: " + value + ", STATUS: FAILURE");
                response = CreateResponse.newBuilder().setResponse(false).build();
            }

            hashTableB.showAll();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {

            CreateRequest createRequest = CreateRequest
                    .newBuilder()
                    .setKey(key)
                    .setValue(value)
                    .build();

            CreateResponse createResponse = nextServerStub.create(createRequest);

        }

    }

    @Override
    public void read(ReadRequest request, StreamObserver<ReadResponse> responseObserver) {
        String key = request.getKey();
        String value;

        if (isResponsable(key)) {
            value = hashTableB.read(key);

            ReadResponse response;
            if (value != null) {
                System.out.println("READ GRPC REQ KEY: " + key + ", VALUE: " + value + ", STATUS: SUCCESS");
                response = ReadResponse.newBuilder().setKey(key).setValue(value).build();
            } else {
                System.out.println("READ GRPC REQ KEY: " + key + ", VALUE: null, STATUS: FAILURE");
                response = ReadResponse.newBuilder().setKey(key).setValue("").build();
            }

            hashTableB.showAll();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {

        }

    }

    @Override
    public void update(UpdateRequest request, StreamObserver<UpdateResponse> responseObserver) {
        String key = request.getKey();
        String value = request.getValue();

        if (isResponsable(key)) {
            String oldValue = hashTableB.read(key);
            int result = hashTableB.update(key, value);
            UpdateResponse response;
            if (result == 1) {
                System.out.println("UPDATE GRPC REQ KEY: " + key + ", CHANGE OLD_VALUE: " + oldValue + ", TO NEW_VALUE: " + value + ", STATUS: SUCCESS");
                response = UpdateResponse.newBuilder().setResponse(true).build();
            } else {
                System.out.println("UPDATE GRPC REQ KEY: " + key + ", VALUE: null, STATUS: FAILURE");
                response = UpdateResponse.newBuilder().setResponse(false).build();
            }

            hashTableB.showAll();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {

        }

    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) {
        String key = request.getKey();
        String value;

        if (isResponsable(key)) {
            value = hashTableB.remove(key);
            DeleteResponse response;
            if (value != null) {
                System.out.println("DELETE GRPC REQ KEY: " + key + ", RETURN_VALUE: " + value + ", STATUS: SUCCESS");
                response = DeleteResponse.newBuilder().setResponse(true).setMessage(value).build();
            } else {
                System.out.println("DELETE GRPC REQ KEY: " + key + ", VALUE: null, STATUS: FAILURE");
                response = DeleteResponse.newBuilder().setResponse(false).setMessage("").build();
            }

            hashTableB.showAll();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {


        }

    }

    @Override
    public void exit(ExitRequest request, StreamObserver<ExitResponse> responseObserver) {
        ExitResponse response;
        response = ExitResponse.newBuilder().setResponse(true).build();

        System.out.println("LOGOUT CLIENT GRPC");

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    private boolean isResponsable(String key) {
        int hashCode = key.hashCode();

        if (hashCode >= responsabilityRange.getMin() && hashCode <= responsabilityRange.getMax())
            return true;
        else
            return false;

    }


}
