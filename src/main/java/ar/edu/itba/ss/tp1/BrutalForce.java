package ar.edu.itba.ss.tp1;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

public class BrutalForce {
    private static int N;
    private static List<Particle> particles = new ArrayList<>();
    public static void main(String[] args) throws IOException {


//        for (int i=0; i<100; i++){
//            // print random values from 0 to 20 with 4 decimal places
//            System.out.println(Math.random()*20);
//        }


        // Set current time
        Instant currentTimestamp = Instant.now();

        //Get parameter for periodic option
        Parser parser = new Parser();
        parser.parseParameters();

        // Parse dynamic input
        parser.parseDynamicFile(particles);
        // Parse static input
        parser.parseStaticFile(particles);

        System.out.println();

        //Verify initial conditions
        Utils.L = 20;
        if ((double) Utils.L / Utils.M <= Utils.radius) {
            System.err.println("L/M does not satisfy the condition L/M > r_c");
            System.exit(1);
        }

        FileWriter outputFile = new FileWriter("output.txt");

        Cell[][] cells = new Cell[Utils.M][Utils.M];
        Matrix matrix = new Matrix(cells);

        //Assign particles to cells based on their positions
        for(Particle p: particles) {
            System.out.println(p);
            matrix.addParticle(p);
        }

        System.out.println();

        //Print particles by cell -> verification purposes only
        for(int i = 0; i < Utils.M; i++) {
            for(int j = 0; j < Utils.M; j++) {
                if(matrix.getMatrix()[i][j] != null) {
                    Cell c = matrix.getMatrix()[i][j];
                    System.out.println("Cell: " + i + j);
                    System.out.println(c.getParticles());
                }
            }
        }

        System.out.println();

        // brutal force:
        // /*
        Map<Integer, Set<Particle>> neighbors = new HashMap<>();
        for(int i = 0; i < Utils.N; i++) {
            neighbors.put(i, new HashSet<>());
        }

        for (Particle p: particles) {
            for (Particle p2: particles) {
                if (!(p.equals(p2))) {
                    if (Utils.getEucledianDistance(p, p2) <= Utils.radius) {
                        neighbors.get(p.getIndex()).add(p2);
                        neighbors.get(p2.getIndex()).add(p);
                    }
                }
            }
        }
// */
//        matrix.createNeighborList();
        System.out.println("here");

        for(int i = 0; i < Utils.N; i++) {
            System.out.println(neighbors.get(i));
            outputFile.write(Integer.toString(particles.get(i).getIndex()) + " ");
            for (Particle p: neighbors.get(i)) {
                outputFile.write(p.getIndex() + " ");
            }
            outputFile.write("\n");
        }
//        for(int i = 0; i < Utils.N; i++) {
//            System.out.println(matrix.getNeighbors().get(i));
//            outputFile.write(Integer.toString(particles.get(i).getIndex()) + " ");
//            for (Particle p: matrix.getNeighbors().get(i)) {
//                outputFile.write(p.getIndex() + " ");
//            }
//            outputFile.write("\n");
//        }

        outputFile.close();
        System.out.println("Time elapsed: " + (Instant.now().toEpochMilli() - currentTimestamp.toEpochMilli()) + "ms");
    }
}
