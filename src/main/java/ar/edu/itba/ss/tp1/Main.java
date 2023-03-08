package ar.edu.itba.ss.tp1;

import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.io.FileWriter;


public class Main {
    static int N;
    static List<Particle> particles = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        // Get parameter for periodic option
        Parser parser = new Parser();
        parser.parse();

        /**
         * Read particle information from input file
         * File format: x-pos y-pos radius
         */
        File inputFile = new File("input.txt");
        Scanner sc = new Scanner(inputFile);
        int lineCount = 0;
        int index = 0;
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            if(lineCount == 0) {
                N = Integer.parseInt(data);
            }
            else {
                String[] splitData = data.split(" ");
                Particle p = new Particle(Double.parseDouble(splitData[0]), Double.parseDouble(splitData[1]), index);
                particles.add(p);
                index++;
            }
            lineCount++;
            System.out.println(data);
        }
        sc.close();

        System.out.println();

        //Verify initial conditions
        Instant currentTimestamp = Instant.now();
        if ((double) Constants.L / Constants.M <= Constants.radius) {
            System.err.println("L/M does not satisfy the condition L/M > r_c");
            System.exit(1);
        }

        FileWriter file = new FileWriter("output.txt");

        Cell[][] cells = new Cell[Constants.M][Constants.M];
        Matrix matrix = new Matrix(cells);

        //Assign particles to cells based on their positions
        for(Particle p: particles) {
            System.out.println(p);
            matrix.addParticle(p);
        }

        System.out.println();

        //Print particles by cell -> verification purposes only
        for(int i = 0; i < Constants.M; i++) {
            for(int j = 0; j < Constants.M; j++) {
                if(matrix.getMatrix()[i][j] != null) {
                    Cell c = matrix.getMatrix()[i][j];
                    System.out.println("Cell: " + i + j);
                    System.out.println(c.getParticles());
                }
            }
        }

        System.out.println();

        matrix.createNeighborList();



//        int index = 0;
//        for(int i = 0; i < Constants.M; i++) {
//            for(int j = 0; j < Constants.M; j++) {
//                matrix[i][j] = new Cell(List.of(new Particle(Constants.M * rand.nextDouble(), Constants.M * rand.nextDouble(), index), new Particle(Constants.M * rand.nextDouble(), Constants.M * rand.nextDouble(), index+1)));
//                index += 2;
//            }
//        }


//        for (int i = 0; i < matrix1.getMatrix().length; i++) {
//            for (int j = 0; j < matrix1.getMatrix()[i].length; j++) {
//                Cell currentCell = matrix1.getMatrix()[i][j];
//                System.out.println(currentCell);
//                for (Particle particle : currentCell.getParticles()) {
//                    file.write(String.format("%f %f\n", particle.getX(), particle.getY()));
//                }
//            }
//        }

        file.close();
        System.out.println("Time elapsed: " + (Instant.now().toEpochMilli() - currentTimestamp.toEpochMilli()) + "ms");
    }
}
