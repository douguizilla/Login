package server.login;

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

       l1.start();
       l2.start();
       l3.start();

        l1.awaitTermination();
        l2.awaitTermination();
        l3.awaitTermination();

    }

}
