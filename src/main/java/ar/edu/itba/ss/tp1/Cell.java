package ar.edu.itba.ss.tp1;

import java.util.List;

public class Cell {
    List<Particle> particles;
    private int xPos;
    private int yPos;

    public Cell(List<Particle> particles, int xPos, int yPos) {
        this.particles = particles;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public List<Particle> getParticles() {
        return particles;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
    @Override
    public String toString() {
        return "Cell [particles=" + particles + ", xPos=" + xPos + ", yPos=" + yPos + "]";
    }
}
