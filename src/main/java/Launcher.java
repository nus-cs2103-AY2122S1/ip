import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches Duke.
     *
     * @param args Left blank.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
