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

        if(matrix[x][y] == null)
            matrix[x][y] = new Cell(new ArrayList<>());

        matrix[x][y].getParticles().add(particle);
    }

    public List<Particle>[] getNeighbors() {
        return neighbors;
    }

    private void addNeighbor(
            Particle currentParticle,
            Particle possibleNeighbor) {
        if (getEucledianDistance(currentParticle, possibleNeighbor) <= Constants.radius) {
            neighbors[currentParticle.getIndex()].add(possibleNeighbor);
            neighbors[possibleNeighbor.getIndex()].add(currentParticle);
        }
    }

    private void cellNeighbor(Cell currentCell){
        if ((currentCell.getxPos() - 1) < 0 || (currentCell.getyPos() + 1) > Constants.L){
            if (!Constants.periodic){
//                 out of matrix -> tira error?
            }else {
//            duplicar fila/columna
            }
        }
        //TODO: llamar a add con las celdas que tengo en la L de vecinas -> Santi
//        addNeighbor(currentCell, matrix[currentCell.getxPos() - 1][currentCell.getyPos()]);
//        addNeighbor(currentCell, matrix[currentCell.getxPos() - 1][currentCell.getyPos() + 1]);
//        addNeighbor(currentCell, matrix[currentCell.getxPos()][currentCell.getyPos() + 1]);
//        addNeighbor(currentCell, matrix[currentCell.getxPos() + 1][currentCell.getyPos() + 1]);
    }

    // TODO: Santi fijate si es esto lo que querías escribir (esto solo aplica para non periodic)
    private void checkCellNeighborsNonPeriodic(Particle currentParticle, int x, int y) {
        if (y - 1 >= 0) {
            for (Particle p : matrix[x][y - 1].getParticles()) {
                addNeighbor(currentParticle, p);
            }
        }
        if (x + 1 < Constants.M) {
            if (y - 1 >= 0) {
                for (Particle p : matrix[x + 1][y - 1].getParticles()) {
                    addNeighbor(currentParticle, p);
                }
            }
            for (Particle p : matrix[x + 1][y].getParticles()) {
                addNeighbor(currentParticle, p);
            }
            if (y + 1 < Constants.M) {
                for (Particle p : matrix[x + 1][y + 1].getParticles()) {
                    addNeighbor(currentParticle, p);
                }
            }
        }

    }

    // TODO: Fijarse si tener en cuenta solo las celdas no vacías para más eficiencia
    public void createNeighbourList() {
        for(int i = 0; i < Constants.N; i++) {
            neighbors[i] = new ArrayList<>();
        }

        for(int i = 0; i < Constants.M; i++) {
            for(int j = 0; j < Constants.M; j++) {
                if(matrix[i][j] != null) {
                    for(Particle p: matrix[i][j].getParticles()) {
                        cellNeighbor(matrix[i][j]);
                    }
                }
            }
        }
    }

    private double getEucledianDistance(Particle p1, Particle p2) {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2)) - 2 * Constants.particleRadius;
    }

    @Override
    public String toString() {
        return "Matrix [matrix=" + matrix + "]";
    }

}
