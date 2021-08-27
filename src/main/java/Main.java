import kayu.Kayu;

/**
 * Main class.
 * 
 * This class drives the whole program.
 */
public class Main {

    /**
     * Driver function for main logic using {@link kayu.Kayu}.
     *
     * @param args Command line arguments fed.
     */
    public static void main(String[] args) {
        Kayu kayu = new Kayu();
        kayu.runProgram();
        System.exit(0);
    }
}
