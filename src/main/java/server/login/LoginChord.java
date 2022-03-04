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
       LoginServer l1 = new LoginServer(12345,"localhost@12346", max1);
       LoginServer l2 = new LoginServer(12346,"localhost@12347",max2);
       LoginServer l3 = new LoginServer(12347,"localhost@12345",max3);

        List<LoginServer> serverList = new ArrayList<LoginServer>();
        serverList.add(l1);
        serverList.add(l2);
        serverList.add(l3);

        l1.start();
        l2.start();
        l3.start();

        l1.fingerTable(l1.id,serverList);
        l2.fingerTable(l2.id,serverList);
        l3.fingerTable(l3.id,serverList);

        l1.awaitTermination();
        l2.awaitTermination();
        l3.awaitTermination();




    }



}
