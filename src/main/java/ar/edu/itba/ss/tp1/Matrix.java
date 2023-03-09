package ar.edu.itba.ss.tp1;

import java.util.*;

public class Matrix {
    private final Cell[][] matrix;

    private final Map<Integer, Set<Particle>> neighbors = new HashMap<>();

    private final double cellSize = (double) Constants.L / Constants.M;

    public Matrix(Cell[][] matrix) {
        this.matrix = matrix;
    }

    public Cell[][] getMatrix() {
        return matrix;
    }

    public void addParticle(Particle particle) {
        int x = (int) Math.floor(particle.getY() / cellSize);
        int y = (int) Math.floor(particle.getX() / cellSize);

        if(matrix[x][y] == null)
            matrix[x][y] = new Cell(new ArrayList<>(), x, y);

        matrix[x][y].getParticles().add(particle);
    }

    public Map<Integer, Set<Particle>> getNeighbors() {
        return neighbors;
    }

    private void addNeighbor(Particle currentParticle, Particle possibleNeighbor) {
        double distance = getEucledianDistance(currentParticle, possibleNeighbor);
        System.out.println("Checking particle: " + possibleNeighbor.getIndex());
        System.out.println("Distance to particle: " + distance);
        if (distance <= Constants.radius) {
            System.out.println("Neighbor found!");
            neighbors.get(currentParticle.getIndex()).add(possibleNeighbor);
            neighbors.get(possibleNeighbor.getIndex()).add(currentParticle);
        }
    }

    private void cellNeighbor(Cell currentCell){
        for (Particle p : currentCell.getParticles()) {
            if (Constants.periodic) {
                checkCellNeighborsPeriodic(p, currentCell.getxPos(), currentCell.getyPos());
            } else {
                checkCellNeighborsNonPeriodic(p, currentCell.getxPos(), currentCell.getyPos());
            }
        }
    }

    private void checkCellNeighborsPeriodic(Particle currentParticle, int x, int y){
        if (x - 1 < 0 && matrix[Constants.M - 1][y] != null){ // "copiar" la ultima fila
            for (Particle p : matrix[Constants.M - 1][y].getParticles()) {
                Particle pCopy = new Particle(p.getX() - Constants.L, p.getY(), p.getIndex());
                addNeighbor(currentParticle, pCopy);
            }
            if (y + 1 < Constants.M && matrix[Constants.M - 1][y+1] != null){ // no estoy en el borde derecho
                for (Particle p : matrix[Constants.M - 1][y + 1].getParticles()) {
                    Particle pCopy = new Particle(p.getX() - Constants.L, p.getY(), p.getIndex());
                    addNeighbor(currentParticle, pCopy);
                }
            } else {
                if(matrix[Constants.M - 1][0] != null) {
                    for (Particle p : matrix[Constants.M - 1][0].getParticles()) {
                        // traigo el de la esquina inferior izquierda
                        Particle pCopy = new Particle(p.getX() - Constants.L, p.getY() + Constants.L, p.getIndex());
                        addNeighbor(currentParticle, pCopy);
                    }
                }
            }
        }

        if (y + 1 >= Constants.M && matrix[x][0] != null){ // "copiar" la primera columna
            for (Particle p : matrix[x][0].getParticles()) {
                Particle pCopy = new Particle(p.getX(), p.getY() + Constants.L, p.getIndex());
                addNeighbor(currentParticle, pCopy);
            }
            if (x + 1 < Constants.M && matrix[x+1][0] != null) { // no estoy en el borde inferior
                for (Particle p : matrix[x + 1][0].getParticles()) {
                    Particle pCopy = new Particle(p.getX(), p.getY() + Constants.L, p.getIndex());
                    addNeighbor(currentParticle, pCopy);
                }
            } else {
                if(matrix[0][0] != null) {
                    for (Particle p : matrix[0][0].getParticles()) {
                        // traigo el de la esquina superior izquierda
                        Particle pCopy = new Particle(p.getX() + Constants.L, p.getY() + Constants.L, p.getIndex());
                        addNeighbor(currentParticle, pCopy);
                    }
                }
            }
        }
        checkCellNeighborsNonPeriodic(currentParticle, x, y);
    }

    // TODO: Santi fijate si es esto lo que querías escribir (esto solo aplica para non periodic)
    private void checkCellNeighborsNonPeriodic(Particle currentParticle, int x, int y) {
        System.out.println("Checking cell: " + x + y);
        for(Particle p: matrix[x][y].getParticles()) {
            if(!currentParticle.equals(p))
                addNeighbor(currentParticle, p);
        }
        if (x - 1 >= 0 && matrix[x-1][y] != null) {
            System.out.println("Checking cell: " + (x-1) + y);
            for (Particle p : matrix[x - 1][y].getParticles()) {
                addNeighbor(currentParticle, p);
            }
        }
        if (y + 1 < Constants.M) {
            if (x - 1 >= 0 && matrix[x-1][y+1] != null) {
                System.out.println("Checking cell: " + (x-1) + (y+1));
                for (Particle p : matrix[x - 1][y + 1].getParticles()) {
                    addNeighbor(currentParticle, p);
                }
            }
            if(matrix[x][y+1] != null) {
                System.out.println("Checking cell: " + x + (y+1));
                for (Particle p : matrix[x][y + 1].getParticles()) {
                    addNeighbor(currentParticle, p);
                }
            }
            if (x + 1 < Constants.M && matrix[x+1][y+1] != null) {
                System.out.println("Checking cell: " + (x+1) + (y+1));
                for (Particle p : matrix[x + 1][y + 1].getParticles()) {
                    addNeighbor(currentParticle, p);
                }
            }
        }

    }

    // TODO: Fijarse si tener en cuenta solo las celdas no vacías para más eficiencia
    public void createNeighborList() {
        // Initialize neighbors list
        for(int i = 0; i < Constants.N; i++) {
            neighbors.put(i, new HashSet<>());
        }
        // Fill with neighbors
        for(int i = 0; i < Constants.M; i++) {
            for(int j = 0; j < Constants.M; j++) {
                if(matrix[i][j] != null) {
                    System.out.println("Cell: " + i + j);
                    cellNeighbor(matrix[i][j]);
                    System.out.println();
                }
            }
        }
    }

    private double getEucledianDistance(Particle p1, Particle p2) {
        return Math.abs(Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2)) - 2 * Constants.particleRadius);
    }

    @Override
    public String toString() {
        return "Matrix [matrix=" + matrix + "]";
    }

}
