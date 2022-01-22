package client;

import br.proto.services.GrpcHashServiceGrpc.*;
import br.proto.services.ServerServiceGrpc.*;
import br.proto.services.Services.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;

import static br.proto.services.ServerServiceGrpc.newBlockingStub;
import static br.proto.services.GrpcHashServiceGrpc.newBlockingStub;

public class Client {
    public static void main(String[] args) {
        try{
            ManagedChannel servicesChannel = ManagedChannelBuilder
                    .forAddress("localhost",54321)
                    .usePlaintext()
                    .build();

            ServerServiceBlockingStub serviceStub = newBlockingStub(servicesChannel);

            Scanner input = new Scanner(System.in);
            String serviceName;
            ServiceResponse response;
            do{
                serviceMenu();
                serviceName = input.next();
                ServiceRequest request = ServiceRequest
                        .newBuilder()
                        .setKey(serviceName)
                        .build();


                response = serviceStub.getService(request);
                if(response.getServiceIP().isEmpty()){
                    System.out.println("O serviço solicitado não existe, tente novamente...");
                }
            }while(response.getServiceIP().isEmpty());

            String ip = response.getServiceIP();
            int port = response.getServicePort();

            ManagedChannel loginChannel = ManagedChannelBuilder
                    .forAddress(ip,port)
                    .usePlaintext()
                    .build();

            GrpcHashServiceBlockingStub loginStub = GrpcHashServiceBlockingStub.newBlockingStub(loginChannel);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void serviceMenu(){
        System.out.println("======================================");
        System.out.println("Digite o nome de um serviço: ");
        System.out.print("=> ");
    }
}
