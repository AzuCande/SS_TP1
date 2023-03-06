package ar.edu.itba.ss.tp1;

public class Particle {
    private double x;
    private double y;
    private int index;


    public Particle(double x, double y, int index) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Particle [x=" + x + ", y=" + y + ", index=" + index + "]";
    }
}
