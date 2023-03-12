package ar.edu.itba.ss.tp1;

public class Utils {
    static int N = 8;
    static int L = 2;
    static int M = 2;
    static boolean periodic = false; // caso a) Sin condiciones periódicas de contorno
//    boolean periodic = true; // caso b) Com condiciones periódicas de contorno

    static double radius = 0.3;
    static double particleRadius = 0.1;

    public static void setN(int n) {
        N = n;
    }

    public static void setL(int l) {
        L = l;
    }

    public static void setM(int m) {
        M = m;
    }

    public static void setPeriodic(boolean periodic) {
        Utils.periodic = periodic;
    }


    public static void setParticleRadius(double particleRadius) {
        Utils.particleRadius = particleRadius;
    }

    public static double getEucledianDistance(Particle p1, Particle p2) {
        System.out.println("Checking particle: " + p2.getIndex());
        return Math.abs(Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2)) - 2 * Utils.particleRadius);
    }
}
