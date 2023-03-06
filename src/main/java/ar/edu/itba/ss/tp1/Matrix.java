package ar.edu.itba.ss.tp1;

import java.util.List;

public class Matrix {
    private Cell[][] matrix;

    private List<Particle>[] neighbors;

    private final double cellSize = (double) Constants.L / Constants.M;

    public Matrix(Cell[][] matrix) {
        this.matrix = matrix;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public void addParticle(Particle particle) {
        int x = (int) Math.floor(particle.getX() / cellSize);
        int y = (int) Math.floor(particle.getY() / cellSize);
        matrix[x][y].getParticles().add(particle);
    }

    public List<Particle>[] getNeighbors() {
        return neighbors;
    }

    private void addNeighbor(Particle currentParticle, Particle possibleNeighbor, int currentParticlePosition, int possibleNeighborPosition) {
        if (getEucledianDistance(currentParticle, possibleNeighbor) <= Constants.radius) {
            neighbors[currentParticlePosition].add(possibleNeighbor);
            neighbors[possibleNeighborPosition].add(currentParticle);
        }
    }

    private void cellNeighbor(Cell currentCell){
        if ((currentCell.getxPos() - 1) < 0 || (currentCell.getyPos() + 1) > Constants.L){
            // out of matrix
        }
        // llamar a add con las celdas que tengo en la L de vecinas -> Santi
    }

    private double getEucledianDistance(Particle p1, Particle p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2)) - 2 * Constants.particleRadius;
    }

    @Override
    public String toString() {
        return "Matrix [matrix=" + matrix + "]";
    }

}
