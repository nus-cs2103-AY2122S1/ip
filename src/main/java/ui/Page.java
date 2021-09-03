package ui;

import javafx.scene.Scene;

/**
 * Abstract class for different pages of the application
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.02
 * @since 0.01
 */
public abstract class Page {
    /**
     * Return the scene layout of the page for the app to switch around
     *
     * @return Scene layout of the page
     */
    public abstract Scene layout();
}
