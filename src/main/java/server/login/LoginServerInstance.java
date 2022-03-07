package server.login;

import java.util.ArrayList;
import java.util.List;

public class LoginServerInstance {
    //Responsabilidades de cada um dos três servidores que serao usados
    static int max1 = 128 / 3;
    static int max2 = max1 * 2;
    static int max3 = 128;

    static List<LoginServer> serverList = new ArrayList<>();

    //Configuração dos servidores para teste
    //LoginServer(12345,"localhost@12346",new ResponsabilityRange(1,min1, max1));
    //LoginServer(12346,"localhost@12347",new ResponsabilityRange(2,min2, max2));
    //LoginServer(12347,"localhost@12345",new ResponsabilityRange(3,min3, max3));

    public static void main(String[] args) {
        serverList.add(new LoginServer(12345, max1));
        serverList.add(new LoginServer(12346,max2));
        serverList.add(new LoginServer(12347,max3));

       //LoginServer l = new LoginServer(12345,"localhost@12346", max1, serverList);
        LoginServer l = new LoginServer(12346,max2,serverList);
       // LoginServer l = new LoginServer(12347,"localhost@12345",max3,serverList);

        l.fingerTable(l,serverList);
        l.start();
        l.awaitTermination();
    }
}
