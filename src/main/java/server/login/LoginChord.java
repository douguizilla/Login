package server.login;

public class LoginChord {
    static int min1 = 0;
    static int max1 = Integer.MAX_VALUE / 3;
    static int min2 = max1 + 1;
    static int max2 = max1 * 2;
    static int min3 = max2 + 1;
    static int max3 = Integer.MAX_VALUE;
    public static void main(String[] args) {
        new LoginServer(12345,"localhost@12346",new ResponsabilityRange(1,min1, max1)).start();
        new LoginServer(12346,"localhost@12347",new ResponsabilityRange(1,min2, max2)).start();
        new LoginServer(12347,"localhost@12345",new ResponsabilityRange(1,min3, max3)).start();

    }

}
