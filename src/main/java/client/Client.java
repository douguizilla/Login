package client;

import br.proto.services.GrpcHashServiceGrpc.GrpcHashServiceBlockingStub;
import br.proto.services.ServerServiceGrpc;
import br.proto.services.ServerServiceGrpc.ServerServiceBlockingStub;
import br.proto.services.Services.*;
import br.proto.services.Services.ServiceRequest;
import br.proto.services.Services.ServiceResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Scanner;


public class Client {
    static GrpcHashServiceBlockingStub loginStub;
    static Scanner input;

    public static void main(String[] args) {
        try {
            ManagedChannel servicesChannel = ManagedChannelBuilder
                    .forAddress("localhost", 54321)
                    .usePlaintext()
                    .build();

            ServerServiceBlockingStub serviceStub = ServerServiceGrpc.newBlockingStub(servicesChannel);

            input = new Scanner(System.in);
            String serviceName;
            ServiceResponse response;
            do {
                serviceMenu();
                serviceName = input.next();
                ServiceRequest request = ServiceRequest
                        .newBuilder()
                        .setKey(serviceName)
                        .build();


                response = serviceStub.getService(request);
                if (response.getServiceIP().isEmpty()) {
                    System.out.println("O serviço solicitado não existe, tente novamente...");
                }
            } while (response.getServiceIP().isEmpty());

            String ip = response.getServiceIP();
            int port = response.getServicePort();

            ManagedChannel loginChannel = ManagedChannelBuilder
                    .forAddress(ip, port)
                    .usePlaintext()
                    .build();

            loginStub = br.proto.services.GrpcHashServiceGrpc.newBlockingStub(loginChannel);

            System.out.println("Serviço " + serviceName + " encontrado!");

            int option;
            while (true) {
                loginMenu();
                while (!input.hasNextInt()) {
                    System.out.println("Digite uma opção válida.\nOpção: ");
                    input.next();
                }

                option = input.nextInt();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void serviceMenu() {
        System.out.println("======================================");
        System.out.println("Digite o nome de um serviço: ");
        System.out.print("=> ");
    }

    public static void loginMenu() {
        System.out.println("============LOGIN=SERVICE============");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println("1 - Logar");
        System.out.println("2 - Criar conta");
        System.out.println("3 - Atualizar conta");
        System.out.println("4 - Deletar conta");
        System.out.println("5 - Sair do login");
        System.out.print("Opção: ");
    }

    private static void optionHandle(int option) {
        String email;
        String password;
        boolean result;
        switch (option) {
            case 1:
                System.out.println("Login:");
                System.out.print("Digite o email: ");
                email = input.next();

                System.out.print("Digite a senha: ");
                password = input.next();

                result = login(email, password);

                if (result) {
                    System.out.println("Login realizado com sucesso!");
                } else {
                    System.out.println("Email e/ou senha incorretos!");
                }
                break;
            case 2:
                System.out.println("Criando uma conta:");
                System.out.print("Digite o email: ");
                email = input.next();

                System.out.print("Digite a senha: ");
                password = input.next();

                CreateRequest createRequest = CreateRequest
                        .newBuilder()
                        .setKey(email)
                        .setValue(String.valueOf(password.hashCode()))
                        .build();

                CreateResponse createResponse = loginStub.create(createRequest);


                if (createResponse.getResponse()) {
                    System.out.println("Conta criada com sucesso!");
                } else {
                    System.out.println("Não foi possível criar uma conta, tente novamente mais tarde!");
                }
                break;
            case 3:
                System.out.print("Digite:\n1 - Atualizar o email\nAtualizar a senha\nOpção: ");
                while (!input.hasNextInt()) {
                    System.out.println("Digite uma opção válida.\nOpção: ");
                    input.next();
                }
                int attOption = input.nextInt();
                switch (attOption) {
                    case 1:
                        System.out.println("Atualizar o email:");
                        System.out.print("Digite o email ATUAL: ");
                        email = input.next();

                        System.out.print("Digite a senha: ");
                        password = input.next();

                        result = login(email, password);

                        if (result) {
                            System.out.print("Digite o NOVO email: ");
                            String newEmail = input.next();

                            ReadRequest readNewEmailRequest = ReadRequest
                                    .newBuilder()
                                    .setKey(newEmail)
                                    .build();

                            ReadResponse readNewEmailResponse = loginStub.read(readNewEmailRequest);

                            if (readNewEmailResponse.getValue().isEmpty()) {

                                DeleteRequest deleteOldEmailRequest = DeleteRequest
                                        .newBuilder()
                                        .setKey(email)
                                        .build();

                                DeleteResponse oldPasswordHashCodeResponse = loginStub.delete(deleteOldEmailRequest);
                                String oldPasswordHashCode = oldPasswordHashCodeResponse.getMessage();

                                CreateRequest attEmailRequest = CreateRequest
                                        .newBuilder()
                                        .setKey(newEmail)
                                        .setValue(oldPasswordHashCode)
                                        .build();

                                CreateResponse attEmailResponse = loginStub.create(attEmailRequest);

                                if (attEmailResponse.getResponse()) {
                                    System.out.println("Email atualizado com sucesso!");
                                }
                            } else {
                                System.out.println("Email já utilizado, tente com outro email...");
                            }
                        } else {
                            System.out.println("Não foi possível atualizar o email, pois o email atual e/ou senha estão incorretos");
                        }
                        break;
                    case 2:
                        System.out.println("Atualizar a senha:");
                        System.out.print("Digite o email: ");
                        email = input.next();

                        System.out.print("Digite a senha: ");
                        password = input.next();

                        result = login(email, password);

                        if(result){
                            System.out.print("Digite a nova senha: ");
                            String newPassword = input.next();

                            UpdateRequest attPassowrdRequest = UpdateRequest
                                    .newBuilder()
                                    .setKey(email)
                                    .setValue(String.valueOf(newPassword.hashCode()))
                                    .build();

                            UpdateResponse attPasswordResponse = loginStub.update(attPassowrdRequest);

                            if(attPasswordResponse.getResponse()){
                                System.out.println("Senha atualizada com sucesso!");
                            }else{
                                System.out.println("Não foi possível atualizar a senha...");
                            }
                        }else{
                            System.out.println("Não foi possível atualizar a senha, pois o email e/ou senha estão incorretos");
                        }
                        break;
                    default:
                        break;
                }


                break;
            case 4:
            case 5:
            default:
        }
    }

    private static boolean login(String email, String password) {
        ReadRequest loginRequest = ReadRequest
                .newBuilder()
                .setKey(email)
                .build();

        ReadResponse loginResponse = loginStub.read(loginRequest);
        String storedPassword = loginResponse.getValue();

        if (String.valueOf(password.hashCode()) == storedPassword) {
            return true;
        } else {
            return false;
        }
    }


}
