package ar.edu.itba.ss.tp1;

import java.util.List;

public class Cell {
    List<Particle> particles;
    private double xPos;
    private double yPos;

    public Cell(List<Particle> particles) {
        this.particles = particles;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    @Override
    public String toString() {
        return "Cell [particles=" + particles + ", xPos=" + xPos + ", yPos=" + yPos + "]";
    }
}
