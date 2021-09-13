import javafx.application.Application;
import kayu.KayuInterface;

/**
 * Drives the whole program.
 */
public class Main {

    /**
     * Runs main logic using {@link kayu.Kayu} and JavaFX through {@link KayuInterface}.
     *
     * @param args Command line arguments fed.
     */
    public static void main(String[] args) {
        Application.launch(KayuInterface.class, args);
    }
}
