package ar.edu.itba.ss.tp1;

//import jdk.jshell.execution.Util;

import java.util.*;

public class Matrix {
    private final Cell[][] matrix;

    private final Map<Integer, Set<Particle>> neighbors = new HashMap<>();

    private final double cellSize = (double) Utils.L / Utils.M;

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
        System.out.println("Neighbor found!");
        neighbors.get(currentParticle.getIndex()).add(possibleNeighbor);
        neighbors.get(possibleNeighbor.getIndex()).add(currentParticle);
    }

    private void cellNeighbor(Cell currentCell){
        for (Particle p : currentCell.getParticles()) {
            if (Utils.periodic) {
                checkCellNeighborsPeriodic(p, currentCell.getxPos(), currentCell.getyPos());
            } else {
                checkCellNeighborsNonPeriodic(p, currentCell.getxPos(), currentCell.getyPos());
            }
        }
    }

    private void checkCellNeighborsPeriodic(Particle currentParticle, int x, int y){
        // 00 01
        // 10 11 X
        // 00 01
        if (x + 1 >= Utils.M && matrix[0][y] != null) { // necesito la celda de la misma col pero ultima fila
            for(Particle p : matrix[0][y].getParticles()) {
                Particle pCopy = new Particle(p.getX(), p.getY() + Utils.L, p.getIndex(), p.getRadius());
                if (checkNeighborCondition(currentParticle, pCopy)){
                    addNeighbor(currentParticle, p);
                }
            }
            if (y + 1 >= Utils.M && matrix[0][0] != null) { // caso del extremo superior derecho
                for (Particle p : matrix[0][0].getParticles()) {
                    Particle pCopy = new Particle(p.getX() + Utils.L, p.getY() + Utils.L, p.getIndex(), p.getRadius());
                    if (checkNeighborCondition(currentParticle, pCopy)){
                        addNeighbor(currentParticle, p);
                    }
                }
            }
        }
        // 10 11 10
        // 00 01 00
        if (y + 1 >= Utils.M && matrix[x][0] != null) { // necesito la celda de la misma fila pero 1ra col
            for (Particle p : matrix[x][0].getParticles()) {
                Particle pCopy = new Particle(p.getX() + Utils.L, p.getY(), p.getIndex(), p.getRadius());
                if (checkNeighborCondition(currentParticle, pCopy)){
                    addNeighbor(currentParticle, p);
                }
            }
            // 10 11 10
            // 00 01 00
            //       10
            if (x - 1 < 0 && matrix[Utils.M - 1][0] != null) { // caso de la esquina inferior derecha
                for (Particle p : matrix[Utils.M - 1][0].getParticles()) {
                    Particle pCopy = new Particle(p.getX() + Utils.L, p.getY() - Utils.L, p.getIndex(), p.getRadius());
                    if (checkNeighborCondition(currentParticle, pCopy)){
                        addNeighbor(currentParticle, p);
                    }
                }
            }
        }


 /*
        if (y - 1 < 0 && matrix[x][Utils.M - 1] != null){ // "copiar" la ultima fila
            for (Particle p : matrix[x][Utils.M - 1].getParticles()) {
                Particle pCopy = new Particle(p.getX(), p.getY() - Utils.L, p.getIndex());
//                addNeighbor(currentParticle, pCopy);
                if (checkNeighborCondition(currentParticle, pCopy)){
                    addNeighbor(currentParticle, pCopy);
                }
            }
            if (x + 1 < Utils.M && matrix[x + 1][Utils.M - 1] != null){ // no estoy en el borde derecho
                for (Particle p : matrix[x + 1][Utils.M - 1].getParticles()) {
                    Particle pCopy = new Particle(p.getX(), p.getY() - Utils.L, p.getIndex());
                    if (checkNeighborCondition(currentParticle, pCopy)){
                        addNeighbor(currentParticle, pCopy);
                    }
                }
            }
        }

        if (x + 1 >= Utils.M && matrix[0][y] != null){ // "copiar" la primera columna
            for (Particle p : matrix[0][x].getParticles()) {
                Particle pCopy = new Particle(p.getX() + Utils.L, p.getY(), p.getIndex());
                if (checkNeighborCondition(currentParticle, pCopy)){
                    addNeighbor(currentParticle, pCopy);
                }
            }
            if (y + 1 < Utils.M && matrix[0][y + 1] != null) { // no estoy en el borde inferior
                for (Particle p : matrix[0][y + 1].getParticles()) {
                    Particle pCopy = new Particle(p.getX() + Utils.L, p.getY(), p.getIndex());
                    if (checkNeighborCondition(currentParticle, pCopy)){
                        addNeighbor(currentParticle, pCopy);
                    }
                }
            }
        }
      */
        checkCellNeighborsNonPeriodic(currentParticle, x, y);
    }

    // TODO: Santi fijate si es esto lo que querías escribir (esto solo aplica para non periodic)
    private void checkCellNeighborsNonPeriodic(Particle currentParticle, int x, int y) {
        // Check particles in current cell
        System.out.println("Checking cell: " + x + y);
        matrix[x][y].getParticles().stream()
                .filter(p -> !currentParticle.equals(p) && checkNeighborCondition(currentParticle, p))
                .forEach(p -> addNeighbor(currentParticle, p));

        // Check cell at the top
        if (x - 1 >= 0)
            checkCellNeighborNonPeriodic(x - 1, y, currentParticle);
        if (y + 1 < Utils.M) {
            // Check cell on the right-top
            if (x - 1 >= 0)
                checkCellNeighborNonPeriodic(x - 1, y + 1, currentParticle);

            // Check cell on the right
            checkCellNeighborNonPeriodic(x, y + 1, currentParticle);

            // Check cell on the right-bottom
            if (x + 1 < Utils.M)
                checkCellNeighborNonPeriodic(x + 1, y + 1, currentParticle);
        }

    }

    private void checkCellNeighborNonPeriodic(int x, int y, Particle currentParticle) {
        if (matrix[x][y] != null) {
            System.out.println("Checking cell: " + (x) + (y));
            matrix[x][y].getParticles().stream()
                    .filter(p -> checkNeighborCondition(currentParticle, p))
                    .forEach(p -> addNeighbor(currentParticle, p));
        }
    }

    // TODO: Fijarse si tener en cuenta solo las celdas no vacías para más eficiencia
    public void createNeighborList() {
        // Initialize neighbors list
        for(int i = 0; i < Utils.N; i++) {
            neighbors.put(i, new HashSet<>());
        }
        // Fill with neighbors
        for(int i = 0; i < Utils.M; i++) {
            for(int j = 0; j < Utils.M; j++) {
                if(matrix[i][j] != null) {
                    System.out.println("Cell: " + i + j);
                    cellNeighbor(matrix[i][j]);
                    System.out.println();
                }
            }
        }
    }

    private boolean checkNeighborCondition(Particle p1, Particle p2) {
        return Utils.getEucledianDistance(p1, p2) <= Utils.radius;
    }

    @Override
    public String toString() {
        return "Matrix [matrix=" + matrix + "]";
    }

}
