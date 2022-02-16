package server.login;

public class ResponsabilityRange {
    private int idServer;
    private int min;
    private int max;

    public ResponsabilityRange(int idServer, int min, int max){
        this.idServer = idServer;
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getIdServer() {return idServer;}
}
