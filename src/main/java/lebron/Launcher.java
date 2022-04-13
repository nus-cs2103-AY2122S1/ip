package lebron;

import javafx.application.Application;
import lebron.controller.Bridge;

//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
//Credit to Jeffry Lum
/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Bridge.class, args);
    }
}
