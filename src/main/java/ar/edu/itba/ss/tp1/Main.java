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
        //Get parameter for periodic option
        Parser parser = new Parser();
        parser.parse();

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
        }
        sc.close();

        System.out.println();

        //Verify initial conditions
        Instant currentTimestamp = Instant.now();
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

        matrix.createNeighborList();
        System.out.println("here");


        for(int i = 0; i < Utils.N; i++) {
            System.out.println(matrix.getNeighbors().get(i));
            outputFile.write(Integer.toString(particles.get(i).getIndex()) + " ");
            for (Particle p: matrix.getNeighbors().get(i)) {
                outputFile.write(p.getIndex() + " ");
            }
            outputFile.write("\n");
        }

        outputFile.close();
        System.out.println("Time elapsed: " + (Instant.now().toEpochMilli() - currentTimestamp.toEpochMilli()) + "ms");
    }
}
