package ar.edu.itba.ss.tp1;

import java.util.Objects;

public class Particle {
    private double x;
    private double y;
    private int index;
    private double radius;
    private double property;

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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getProperty() {
        return property;
    }

    public void setProperty(double property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "Particle [x=" + x + ", y=" + y + ", index=" + index + "]";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return Double.compare(particle.x, x) == 0 && Double.compare(particle.y, y) == 0 && index == particle.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, index);
    }
}
