package server.login;

import br.proto.services.GrpcHashServiceGrpc;
import br.proto.services.Services.*;
import io.grpc.stub.StreamObserver;
import server.HashTable;

public class GrpcHashServiceImpl extends GrpcHashServiceGrpc.GrpcHashServiceImplBase {
    private HashTable hashTableB;
    private GrpcHashServiceGrpc.GrpcHashServiceBlockingStub nextServerStub;
    private ResponsabilityRange responsability;

    GrpcHashServiceImpl(HashTable hashTable, GrpcHashServiceGrpc.GrpcHashServiceBlockingStub serviceStub, ResponsabilityRange responsabilityRange) {
        this.hashTableB = hashTable;
        this.nextServerStub = serviceStub;
        this.responsability = responsabilityRange;
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {

        String key = request.getKey();
        String value = request.getValue();

        CreateResponse response;

        if (isResponsable(key)) {
            int result = hashTableB.add(key, value);

            if (result == 1) {
                System.out.println("SERVER " + responsability.getIdServer() + "IS RESPONSABLE TO CREATE THIS ACCOUNT. EMAIL: " + key + "PASSWORD: " + value + " STATUS: SUCCESS");
                response = CreateResponse.newBuilder().setResponse(true).build();
            } else {
                System.out.println("SERVER " + responsability.getIdServer() + "IS RESPONSABLE TO CREATE THIS ACCOUNT. EMAIL: " + key + "PASSWORD: " + value + " STATUS: FAILED");
                response = CreateResponse.newBuilder().setResponse(false).build();
            }

        } else {
            System.out.println("SERVER " + responsability.getIdServer() + "IS NOT RESPONSABLE TO CREATE THIS ACCOUNT. EMAIL: " + key + "PASSWORD: " + value + " SENT TO NEXT SERVER");
            CreateRequest createRequest = CreateRequest
                    .newBuilder()
                    .setKey(key)
                    .setValue(value)
                    .build();

            CreateResponse nextResponse = nextServerStub.create(createRequest);

            response = CreateResponse.newBuilder().setResponse(nextResponse.getResponse()).build();
        }

        hashTableB.showAll();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void read(ReadRequest request, StreamObserver<ReadResponse> responseObserver) {
        String key = request.getKey();
        ReadResponse response;

        if (isResponsable(key)) {
            String value = hashTableB.read(key);

            if (value != null) {
                System.out.println("SERVER " + responsability.getIdServer() + "IS RESPONSABLE TO READ THIS ACCOUNT VALUE. EMAIL: " + key + "PASSWORD: " + value + " STATUS: SUCCESS");
                response = ReadResponse.newBuilder().setKey(key).setValue(value).build();
            } else {
                System.out.println("SERVER " + responsability.getIdServer() + "IS RESPONSABLE TO READ THIS ACCOUNT VALUE. EMAIL: " + key + " STATUS: FAILED");
                response = ReadResponse.newBuilder().setKey(key).setValue("").build();
            }

        } else {

            System.out.println("SERVER " + responsability.getIdServer() + "IS NOT RESPONSABLE TO READ THIS ACCOUNT. EMAIL: " + key + "PASSWORD: " + " SENT TO NEXT SERVER");
            ReadRequest readRequest = ReadRequest
                    .newBuilder()
                    .setKey(key)
                    .build();

            response = nextServerStub.read(readRequest);

        }

        hashTableB.showAll();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void update(UpdateRequest request, StreamObserver<UpdateResponse> responseObserver) {
        String key = request.getKey();
        String value = request.getValue();
        UpdateResponse response;

        if (isResponsable(key)) {
            String oldValue = hashTableB.read(key);
            int result = hashTableB.update(key, value);

            if (result == 1) {
                System.out.println("SERVER " + responsability.getIdServer() + "IS RESPONSABLE TO UPDATE THIS ACCOUNT VALUE. EMAIL: " + key + "PASSWORD: " + value + " STATUS: SUCCESS");
                response = UpdateResponse.newBuilder().setResponse(true).build();
            } else {
                System.out.println("SERVER " + responsability.getIdServer() + "IS RESPONSABLE TO UPDATE THIS ACCOUNT VALUE. EMAIL: " + key + "PASSWORD: " + value + " STATUS: FAILED");
                response = UpdateResponse.newBuilder().setResponse(false).build();
            }

        } else {
            System.out.println("SERVER " + responsability.getIdServer() + "IS NOT RESPONSABLE TO UPDATE THIS ACCOUNT. EMAIL: " + key + "PASSWORD: " + value + " SENT TO NEXT SERVER");

            UpdateRequest updateRequest = UpdateRequest
                    .newBuilder()
                    .setKey(key)
                    .setValue(value)
                    .build();

            response = nextServerStub.update(updateRequest);
        }

        hashTableB.showAll();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

    @Override
    public void delete(DeleteRequest request, StreamObserver<DeleteResponse> responseObserver) {
        String key = request.getKey();
        String value;
        DeleteResponse response;

        if (isResponsable(key)) {
            value = hashTableB.remove(key);

            if (value != null) {
                System.out.println("SERVER " + responsability.getIdServer() + "IS RESPONSABLE TO DELETE THIS ACCOUNT VALUE. EMAIL: " + key + "PASSWORD: " + value + " STATUS: SUCCESS");
                response = DeleteResponse.newBuilder().setResponse(true).setMessage(value).build();
            } else {
                System.out.println("SERVER " + responsability.getIdServer() + "IS RESPONSABLE TO DELETE THIS ACCOUNT VALUE. EMAIL: " + key + "PASSWORD: " + value + " STATUS: FAILED");
                response = DeleteResponse.newBuilder().setResponse(false).setMessage("").build();
            }

        } else {
            System.out.println("SERVER " + responsability.getIdServer() + "IS NOT RESPONSABLE TO DELETE THIS ACCOUNT. EMAIL: " + key + "PASSWORD: " + " SENT TO NEXT SERVER");
            DeleteRequest deleteRequest = DeleteRequest
                    .newBuilder()
                    .setKey(key)
                    .build();

            response = nextServerStub.delete(deleteRequest);

        }

        hashTableB.showAll();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

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

        if (hashCode >= responsability.getMin() && hashCode <= responsability.getMax())
            return true;
        else
            return false;

    }


}
