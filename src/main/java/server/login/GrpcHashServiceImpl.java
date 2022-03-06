package server.login;

import br.proto.services.GrpcHashServiceGrpc;
import br.proto.services.Services.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import server.HashTable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GrpcHashServiceImpl extends GrpcHashServiceGrpc.GrpcHashServiceImplBase {
    private HashTable hashTableB;
    private String nextServerAddress;
    private GrpcHashServiceGrpc.GrpcHashServiceBlockingStub nextServerStub;
    private ResponsabilityRange responsability;
    private int id;
    private boolean isConnected = false;
    //private Object[][] ft;
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
        // connectToNextServer();

        String key = request.getKey();
        String value = request.getValue();

        CreateResponse response;

        if (isResponsable(key)) {
            int result = hashTableB.add(key, value);

            if (result == 1) {
                System.out.println("SERVER " + responsability.getIdServer() + " IS RESPONSABLE TO CREATE THIS ACCOUNT. EMAIL: " + key + " PASSWORD: " + value + " STATUS: SUCCESS");
                response = CreateResponse.newBuilder().setResponse(true).build();
            } else {
                System.out.println("SERVER " + responsability.getIdServer() + " IS RESPONSABLE TO CREATE THIS ACCOUNT. EMAIL: " + key + " PASSWORD: " + value + " STATUS: FAILED");
                response = CreateResponse.newBuilder().setResponse(false).build();
            }

        } else {
            System.out.println("SERVER " + responsability.getIdServer() + " IS NOT RESPONSABLE TO CREATE THIS ACCOUNT WITH EMAIL: " + key + " SENT TO NEXT SERVER");
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
        connectToNextServer();

        String key = request.getKey();
        ReadResponse response;

        if (isResponsable(key)) {
            String value = hashTableB.read(key);

            if (value != null) {
                System.out.println("SERVER " + responsability.getIdServer() + " IS RESPONSABLE TO READ THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: SUCCESS");
                response = ReadResponse.newBuilder().setKey(key).setValue(value).build();
            } else {
                System.out.println("SERVER " + responsability.getIdServer() + " IS RESPONSABLE TO READ THIS ACCOUNT VALUE. EMAIL: " + key + " STATUS: FAILED");
                response = ReadResponse.newBuilder().setKey(key).setValue("").build();
            }

        } else {

            System.out.println("SERVER " + responsability.getIdServer() + " IS NOT RESPONSABLE TO READ THIS ACCOUNT WITH EMAIL: " + key + " SENT TO NEXT SERVER");
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
        connectToNextServer();

        String key = request.getKey();
        String value = request.getValue();
        UpdateResponse response;

        if (isResponsable(key)) {
            String oldValue = hashTableB.read(key);
            int result = hashTableB.update(key, value);

            if (result == 1) {
                System.out.println("SERVER " + responsability.getIdServer() + " IS RESPONSABLE TO UPDATE THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: SUCCESS");
                response = UpdateResponse.newBuilder().setResponse(true).build();
            } else {
                System.out.println("SERVER " + responsability.getIdServer() + " IS RESPONSABLE TO UPDATE THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: FAILED");
                response = UpdateResponse.newBuilder().setResponse(false).build();
            }

        } else {
            System.out.println("SERVER " + responsability.getIdServer() + " IS NOT RESPONSABLE TO UPDATE THIS ACCOUNT WITH EMAIL: " + key + " SENT TO NEXT SERVER");

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
        connectToNextServer();

        String key = request.getKey();
        String value;
        DeleteResponse response;

        if (isResponsable(key)) {
            value = hashTableB.remove(key);

            if (value != null) {
                System.out.println("SERVER " + responsability.getIdServer() + " IS RESPONSABLE TO DELETE THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: SUCCESS");
                response = DeleteResponse.newBuilder().setResponse(true).setMessage(value).build();
            } else {
                System.out.println("SERVER " + responsability.getIdServer() + " IS RESPONSABLE TO DELETE THIS ACCOUNT VALUE. EMAIL: " + key + " PASSWORD: " + value + " STATUS: FAILED");
                response = DeleteResponse.newBuilder().setResponse(false).setMessage("").build();
            }

        } else {
            System.out.println("SERVER " + responsability.getIdServer() + " IS NOT RESPONSABLE TO DELETE THIS ACCOUNT WITH EMAIL: " + key + " SENT TO NEXT SERVER");
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

        if (hashCode > id) {
            TreeMap<Integer, String> temp = new TreeMap<>();

            for (int x = 0; x < LoginServer.ft.length; x++) {
                temp.put((Integer) LoginServer.ft[x][0], String.valueOf(LoginServer.ft[x][1]));
            }

            int teste = temp.ceilingKey(hashCode);

            if (temp.ceilingKey(hashCode) != null) {
                teste = temp.ceilingKey(hashCode);
            } else {
                teste = temp.firstKey();
            }

            String teste1 = temp.ceilingEntry(teste).getValue();

//        System.out.println(Arrays.stream(LoginServer.ft).count());
//        int teste = (int) Arrays.stream(LoginServer.ft).flatMap(Arrays::stream)
//                .collect(Collectors.toList()).stream()
//                .filter(e -> (int)e > hashCode).findFirst().orElse(0);
//
//        int index = Arrays.stream(LoginServer.ft).flatMap(Arrays::stream)
//                .collect(Collectors.toList()).indexOf(teste);
//
//        int i;
            // String servidor = (String) LoginServer.ft[index][1];
            nextServerAddress = teste1;

            return false;
        } else {
            return true;
        }

        //   AQUI VAI ACONTECER A MAGICA
//        System.out.println("MIN: " + responsability.getMin() + " MAX: " + responsability.getMax());
//        System.out.println("HASHCODE: " + hashCode);
//
//        if (hashCode >= responsability.getMin() && hashCode <= responsability.getMax()) {
//            return true;
//        }else {
//            return false;
//        }

    }


}
