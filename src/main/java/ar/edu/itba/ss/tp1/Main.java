package ar.edu.itba.ss.tp1;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.io.FileWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        Instant currentTimestamp = Instant.now();
        if ((double) Constants.L / Constants.M <= Constants.radius) {
            System.err.println("L/M does not satisfy the condition L/M > r_c");
            return;
        }

        FileWriter file = new FileWriter("data.txt");

        Cell[][] matrix = new Cell[Constants.M][Constants.M];

        matrix[0][0] = new Cell(List.of(new Particle(0.1, 0.3, 0), new Particle(0.7, 0.2, 1)));
        matrix[0][1] = new Cell(List.of(new Particle(0.23, 0.25, 2), new Particle(0.5, 0.3, 3)));
        matrix[1][0] = new Cell(List.of(new Particle(0.1, 0.5, 4), new Particle(0.2, 0.43, 5)));
        matrix[1][1] = new Cell(List.of(new Particle(0.8, 0.5, 6), new Particle(0.4, 0.6, 7)));

        Matrix matrix1 = new Matrix(matrix);

        for (int i = 0; i < matrix1.getMatrix().length; i++) {
            for (int j = 0; j < matrix1.getMatrix()[i].length; j++) {
                Cell currentCell = matrix1.getMatrix()[i][j];
                System.out.println(currentCell);
                for (Particle particle : currentCell.getParticles()) {
                    file.write(String.format("%f %f\n", particle.getX(), particle.getY()));
                }
            }
        }

        file.close();
        System.out.println("Time elapsed: " + (Instant.now().toEpochMilli() - currentTimestamp.toEpochMilli()) + "ms");
    }
}
