package poseidon;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
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
