package server.login;

import br.proto.services.GrpcHashServiceGrpc;
import br.proto.services.Services.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import server.HashTable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GrpcHashServiceImpl extends GrpcHashServiceGrpc.GrpcHashServiceImplBase {
    private HashTable hashTableB;
    private String nextServerAddress;
    private GrpcHashServiceGrpc.GrpcHashServiceBlockingStub nextServerStub;
    private int id;
    private boolean isConnected = false;
    private List<LoginServer> serverList;

    GrpcHashServiceImpl(HashTable hashTable, int id, List<LoginServer> serverList) {
        this.hashTableB = hashTable;
        // this.nextServerAddress = nextServerAddress;
        this.id = id;
        // this.ft = ft;
        this.serverList = serverList;
    }

    public void connectToNextServer() {
        if (!isConnected) {
            String[] parts = this.nextServerAddress.split("@");
            String ip = parts[0];
            int port = Integer.parseInt(parts[1]);

            ManagedChannelBuilder channelBuilder = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().enableRetry();
            ManagedChannel channel = channelBuilder.build();
            this.nextServerStub = br.proto.services.GrpcHashServiceGrpc.newBlockingStub(channel);
            isConnected = true;
        }
    }

    @Override
    public void create(CreateRequest request, StreamObserver<CreateResponse> responseObserver) {

        String key = request.getKey();
        String value = request.getValue();

        CreateResponse response;

        if (isResponsable(key)) {
            int result = hashTableB.add(key, value);

            if (result == 1) {
                System.out.println("SERVER " + id + " IS RESPONSABLE TO CREATE THIS ACCOUNT. EMAIL: " + key + " PASSWORD: " + value + " STATUS: SUCCESS");
                response = CreateResponse.newBuilder().setResponse(true).build();
            } else {
                System.out.println("SERVER " + id + " IS RESPONSABLE TO CREATE THIS ACCOUNT. EMAIL: " + key + " PASSWORD: " + value + " STATUS: FAILED");
                response = CreateResponse.newBuilder().setResponse(false).build();
            }

        } else {
            connectToNextServer();
            System.out.println("SERVER WITH ID " + id + " IS NOT RESPONSABLE TO CREATE THIS ACCOUNT WITH EMAIL: " + key + " SENT TO NEXT NODE FROM FINGER TABLE");
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
                System.out.println("SERVER " + id + " IS RESPONSABLE TO READ THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: SUCCESS");
                response = ReadResponse.newBuilder().setKey(key).setValue(value).build();
            } else {
                System.out.println("SERVER " + id + " IS RESPONSABLE TO READ THIS ACCOUNT VALUE. EMAIL: " + key + " STATUS: FAILED");
                response = ReadResponse.newBuilder().setKey(key).setValue("").build();
            }

        } else {
            connectToNextServer();
            System.out.println("SERVER WITH ID" + id + " IS NOT RESPONSABLE TO READ THIS ACCOUNT WITH EMAIL: " + key + " SENT TO NEXT NODE");
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
                System.out.println("SERVER " + id + " IS RESPONSABLE TO UPDATE THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: SUCCESS");
                response = UpdateResponse.newBuilder().setResponse(true).build();
            } else {
                System.out.println("SERVER WITH ID " + id + " IS RESPONSABLE TO UPDATE THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: FAILED");
                response = UpdateResponse.newBuilder().setResponse(false).build();
            }

        } else {
            connectToNextServer();
            System.out.println("SERVER WITH ID " + id + " IS NOT RESPONSABLE TO UPDATE THIS ACCOUNT WITH EMAIL: " + key + " SENT TO NEXT NODE");

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
                System.out.println("SERVER WITH ID " + id + " IS RESPONSABLE TO DELETE THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: SUCCESS");
                response = DeleteResponse.newBuilder().setResponse(true).setMessage(value).build();
            } else {
                System.out.println("SERVER WITH ID " + id + " IS RESPONSABLE TO DELETE THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: FAILED");
                response = DeleteResponse.newBuilder().setResponse(false).setMessage("").build();
            }

        } else {
            connectToNextServer();
            System.out.println("SERVER WITH ID " + id + " IS NOT RESPONSABLE TO DELETE THIS ACCOUNT WITH EMAIL: " + key + " SENT TO NEXT NODE");
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
        connectToNextServer();

        ExitResponse response;
        response = ExitResponse.newBuilder().setResponse(true).build();

        System.out.println("LOGOUT CLIENT GRPC");

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }


    private boolean isResponsable(String key) {
        int hashCode = Math.abs(key.hashCode()) % 128;
        Integer max = null;
        Integer min = null;
        Integer lastIndexSmallerNode = null;
        Integer lastIndexGreaterOrEqualNode = null;
        FingerTableItem nextServerItem = null;

        min = Collections.min(LoginServer.fingerTable, Comparator.comparing(s -> s.getIdServer())).idServer;
        max = Collections.max(LoginServer.fingerTable, Comparator.comparing(s -> s.getIdServer())).idServer;

        for (int x = 0; x < LoginServer.fingerTable.stream().count(); x++) {
            nextServerItem = LoginServer.fingerTable.get(x);

            if (min > hashCode && nextServerItem.idServer == min) {
                lastIndexGreaterOrEqualNode = x;
            } else if (min < hashCode && nextServerItem.idServer > hashCode)
                lastIndexGreaterOrEqualNode = x;


            if (min < hashCode &&  nextServerItem.idServer == min)
                lastIndexSmallerNode = x;
        }

        if (lastIndexGreaterOrEqualNode != null
                && (LoginServer.fingerTable.get(0).idServer >= hashCode &&
                LoginServer.fingerTable.get(0).idServer == LoginServer.fingerTable.get(lastIndexGreaterOrEqualNode).idServer)) {
            nextServerItem = LoginServer.fingerTable.get(lastIndexGreaterOrEqualNode);
        } else if (lastIndexSmallerNode != null)
            nextServerItem = LoginServer.fingerTable.get(lastIndexSmallerNode);

        if (!((lastIndexSmallerNode != null && hashCode > LoginServer.fingerTable.get(lastIndexSmallerNode).idServer && hashCode < id)
                || lastIndexSmallerNode == null && hashCode < id)) {

            nextServerAddress = nextServerItem.serverAdress;

            System.out.println("| HASHCODE NUMBER: " + hashCode + " REDIRECTING TO SERVER ID: " + nextServerItem.idServer + " BECAUSE SERVER " + id + "IS NOT RESPONSABLE");

            return false;
        } else {
            System.out.println("| HASHCODE NUMBER: " + hashCode + " BELONGS TO SERVER ID: " + id);
            return true;
        }

    }
}




