package yoyo;

import javafx.application.Application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Yoyo.class, args);
    }
}