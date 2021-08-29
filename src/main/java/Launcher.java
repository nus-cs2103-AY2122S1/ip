import javafx.application.Application;

// This class is taken from JavaFX tutorial 4 by Jeffry Lum https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
