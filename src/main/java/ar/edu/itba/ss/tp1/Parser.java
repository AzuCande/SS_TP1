//package ar.edu.itba.ss.tp1;
//
//import java.util.Properties;
//
//public class Parser {
//
//    private String PERIODIC = "periodic";
//
//    /**
//     * To add a parameter to your runner, add a VM option in the configuration with the folowing format:
//     * -Dparameter=value
//    */
//    public void parse() {
//        Properties properties = System.getProperties();
//        String periodic = properties.getProperty(PERIODIC);
//
////        System.out.println("properties: " + properties);
//        System.out.println("periodic: " + periodic);
//
//        if (periodic == null) {
//            System.err.println("No periodic parameter provided");
//            System.exit(1);
//        }
//
//        switch (periodic) {
//            case "true":
//                Constants.setPeriodic(true);
//                break;
//            case "false":
//                break;
//            default:
//                System.err.println("Invalid value for periodic parameter");
//                System.exit(1);
//        }
//
//    }
//}
