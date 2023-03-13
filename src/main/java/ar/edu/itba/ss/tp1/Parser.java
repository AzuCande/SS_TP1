package ar.edu.itba.ss.tp1;

import java.io.File;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Parser {

    private String PERIODIC = "periodic";
    private String M = "matrixSize";
    private String STATIC_FILE = "staticFile";
    private String DYNAMIC_FILE = "dynamicFile";

    private String staticFile = "staticInput.txt";
    private String dynamicFile = "dynamicInput.txt";
    private  String resourcesPath = "src/main/resources/";

    /**
     * To add a parameter to your runner, add a VM option in the configuration with the following format:
     * -Dparameter=value
    */
    public void parseParameters() {
        Properties properties = System.getProperties();
        String periodic = properties.getProperty(PERIODIC);

        if (periodic == null) {
            System.err.println("No periodic parameter provided");
            System.exit(1);
        }

        switch (periodic) {
            case "true":
                Utils.setPeriodic(true);
                break;
            case "false":
                break;
            default:
                System.err.println("Invalid value for periodic parameter");
                System.out.println("Using default value: false");
        }

        String m = properties.getProperty(M);

        if (m == null) {
            System.err.println("No matrix size parameter provided");
            System.out.println("Using default matrix size: 2");
        }

        Utils.setM(Integer.parseInt(m));
        System.out.println("Matrix size: " + Utils.M + "x" + Utils.M);

        String staticFile = properties.getProperty(STATIC_FILE);

        if (staticFile == null) {
            System.err.println("No static file parameter provided");
            System.out.println("Using default static file: staticInput.txt");
        } else {
            this.staticFile = staticFile;
        }

        String dynamicFile = properties.getProperty(DYNAMIC_FILE);

        if (dynamicFile == null) {
            System.err.println("No dynamic file parameter provided");
            System.out.println("Using default dynamic file: dynamicInput.txt");
        } else {
            this.dynamicFile = dynamicFile;
        }
    }

    public void parseDynamicFile(List<Particle> particles) {
        File inputFile = new File(resourcesPath + dynamicFile);
        try (Scanner sc = new Scanner(inputFile)) {
            // Skip t_0
            if (sc.hasNextLine()) sc.nextLine();

            int index = 0;
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] splitData = data.split(" ");
                Particle p = new Particle(Double.parseDouble(splitData[0]), Double.parseDouble(splitData[1]), index);
                particles.add(p);
                index++;
            }
        } catch (Exception e) {
            System.err.println("Error while parsing dynamic file");
            System.exit(1);
        }
    }

    public void parseStaticFile(List<Particle> particles) {
        File inputFile = new File(resourcesPath + staticFile);
        try (Scanner sc = new Scanner(inputFile)) {
            int i = 0;

            if (sc.hasNextLine()) Utils.setN(Integer.parseInt(sc.nextLine()));
            // TODO: Chequear si L es int
            if (sc.hasNextLine()) Utils.setL(Integer.parseInt(sc.nextLine()));

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] splitData = data.split(" ");
                particles.get(i).setRadius(Double.parseDouble(splitData[0]));
                particles.get(i).setProperty(Double.parseDouble(splitData[1]));
                i++;
            }
        } catch (Exception e) {
            System.err.println("Error while parsing static file");
            System.exit(1);
        }
    }
}
