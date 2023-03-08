package ar.edu.itba.ss.tp1;

public class Constants {
    static int N = 8;
    static int L = 2;
    static int M = 2;
    static boolean periodic = false; // caso a) Sin condiciones periódicas de contorno
//    boolean periodic = true; // caso b) Com condiciones periódicas de contorno

    static double radius = 0.3;
    static double particleRadius = 0.1;

    public static void setPeriodic(boolean periodic) {
        Constants.periodic = periodic;
    }
}
