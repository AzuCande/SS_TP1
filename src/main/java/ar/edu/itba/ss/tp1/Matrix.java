package ar.edu.itba.ss.tp1;

import java.util.*;

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
        if(matrix[x][y] == null) {
            matrix[x][y] = new Cell(new ArrayList<>());
        }
        matrix[x][y].getParticles().add(particle);
    }

    public List<Particle>[] getNeighbors() {
        return neighbors;
    }

    private void addNeighbor(
            Particle currentParticle,
            Particle possibleNeighbor,
            int currentParticlePosition,
            int possibleNeighborPosition) {
        if (getEucledianDistance(currentParticle, possibleNeighbor) <= Constants.radius) {
            neighbors[currentParticlePosition].add(possibleNeighbor);
            neighbors[possibleNeighborPosition].add(currentParticle);
        }
    }

    private void cellNeighbor(Cell currentCell){
        if ((currentCell.getxPos() - 1) < 0 || (currentCell.getyPos() + 1) > Constants.L){
            if (!periodic){
//                 out of matrix -> tira error?
            }else {
//            duplicar fila/columna
            }
        }
        //TODO: llamar a add con las celdas que tengo en la L de vecinas -> Santi
        addNeighbor(currentCell, matrix[currentCell.getxPos() - 1][currentCell.getyPos()]);
        addNeighbor(currentCell, matrix[currentCell.getxPos() - 1][currentCell.getyPos() + 1]);
        addNeighbor(currentCell, matrix[currentCell.getxPos()][currentCell.getyPos() + 1]);
        addNeighbor(currentCell, matrix[currentCell.getxPos() + 1][currentCell.getyPos() + 1]);
    }

    private double getEucledianDistance(Particle p1, Particle p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2)) - 2 * Constants.particleRadius;
    }

    @Override
    public String toString() {
        return "Matrix [matrix=" + matrix + "]";
    }

}
