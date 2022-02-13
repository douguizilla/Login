package server.login;

public class ResponsabilityRange {
    private int min;
    private int max;

    public ResponsabilityRange(int min, int max){
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
