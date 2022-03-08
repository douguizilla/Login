package server.login;

public class FingerTableItem implements Comparable<FingerTableItem>  {
    int idServer;
    String serverAdress;

    public FingerTableItem(int idServer, String adress) {
        this.idServer = idServer;
        this.serverAdress = adress;
    }

    public int getIdServer() {
        return idServer;
    }

    public String getAdress() {
        return serverAdress;
    }

    public void setIdServer(int idServer) {
        this.idServer = idServer;
    }

    public void setAdress(String adress) {
        this.serverAdress = adress;
    }

    @Override
    public int compareTo(FingerTableItem o) {
        return 0;
    }
}
