package poseidon;

import javafx.application.Application;

/**
 * Represents a launcher class to workaround classpath issues.
 *
 * @author Yeluri Ketan
 * @version CS2103T AY21/22 Sem 1 iP
 */
public class Launcher {

    /**
     * @param args Arguments for the launch of the Application.
     */
    public static void main(String[] args) {
        //assert false : "Assertions are active";
        Application.launch(Main.class, args);
    }
}
