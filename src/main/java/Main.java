import javafx.application.Application;
import kayu.Launcher;

/**
 * Drives the whole program.
 */
public class Main {

    /**
     * Driver function for main logic using {@link kayu.Kayu} and JavaFX through {@link kayu.Launcher}.
     *
     * @param args Command line arguments fed.
     */
    public static void main(String[] args) {
        Application.launch(Launcher.class, args);
    }
}
