package server.login;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LoginChord {
    static int min1 = 0;
    static int max1 = Integer.MAX_VALUE / 3;
    static int min2 = max1 + 1;
    static int max2 = max1 * 2;
    static int min3 = max2 + 1;
    static int max3 = Integer.MAX_VALUE;
    public static void main(String[] args) {
       LoginServer l1 = new LoginServer(12345,"localhost@12346",new ResponsabilityRange(1,min1, max1));
       LoginServer l2 = new LoginServer(12346,"localhost@12347",new ResponsabilityRange(2,min2, max2));
       LoginServer l3 = new LoginServer(12347,"localhost@12345",new ResponsabilityRange(3,min3, max3));

       List<LoginServer> serverList = new ArrayList<LoginServer>();
        serverList.add(l1);
        serverList.add(l2);
        serverList.add(l3);

        serverList.forEach(server -> {
            server.start();
            server.awaitTermination();
            server.id = server.responsabilityRange.getMax();
            fingerTable(server);
        });



    }

    public static void fingerTable(LoginServer server) {
        server.ft = new Object[5][2]; //M linhas e 2 colunas (ID, Endereço)
                                      //5 linhas de acordo com a conversão binaria: 16,8,4,2,1

        TreeMap<Integer, String[]> temp = new TreeMap<>();
        //NESSE TEMP PRECISAMOS COLOCAR O ID DO SERVIDOR CORRENTE E CONVERTER A LISTA DE SERVIDORES EM UMA STRING PARA QUE POSSA SER PREENCHIDO NA FINGER TABLE

                for (int i = 0; i < 5; i++) {
                    int ftpi = server.id + (int) Math.pow(2, i);// não se utiliza 2 ^ i-1 pq i já começa em 0 aqui

                    if (ftpi >= Math.pow(2, 5)) {
                        ftpi -= Math.pow(2, 5);
                    }

                    if (temp.ceilingKey(ftpi) != null) {
                        server.ft[i][0] = temp.ceilingKey(ftpi);
                    } else {
                        server.ft[i][0] = temp.firstKey();
                    }

                    server.ft[i][1] = temp.get((int) server.ft[i][0]);
                }

                // Impressão para conferência
                System.out.println("\nID: " + server.id + "\nFinger Table:");
                for (int i = 0; i < 5; i++) {
                    System.out.println("| " + (i + 1) + " | " + (int) server.ft[i][0] + " |");
                }

    }

}
