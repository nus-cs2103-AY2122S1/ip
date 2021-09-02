package me.yukun99.ip.ui;

import java.io.IOException;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.yukun99.ip.core.Parser;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.ui.elements.Window;

/**
 * Class used to handle message the Ui and responses from the HelpBot.
 */
public class Ui {
    // Name of the HelpBot.
    private final String name;
    // List of tasks to be completed.
    private final TaskList taskList;
    // Storage instance from the current HelpBot.
    private final Storage storage;
    // TaskFinder instance from the current HelpBot.
    private final TaskFinder taskFinder;
    // Parser instance from the current HelpBot.
    private final Parser parser;

    // Stage where we display our messages.
    private Stage stage;

    /**
     * Constructor for a Ui instance.
     *
     * @param name Name of the HelpBot.
     */
    public Ui(String name, TaskList taskList, Storage storage, TaskFinder taskFinder, Parser parser) {
        this.name = name;
        this.taskList = taskList;
        this.storage = storage;
        this.taskFinder = taskFinder;
        this.parser = parser;
    }

    /**
     * Sends user instructions and bot information on startup.
     */
    public void start(Stage stage, FXMLLoader fxmlLoader) {
        this.stage = stage;
        stage.setResizable(false);
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            fxmlLoader.<Window>getController().setup(parser, this, storage, name);
            Scene scene = new Scene(anchorPane);

            stage.setScene(scene);
            stage.setTitle("Bob v2.0 - Why are you even using me?");
            stage.setResizable(false);
            stage.show();
        } catch (IOException ignored) {
            // ignored
        }
    }

    /**
     * Sends message when user exits the bot.
     */
    public void exit() {
        stage.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ui ui = (Ui) o;
        return name.equals(ui.name) && taskList.equals(ui.taskList) && storage.equals(ui.storage)
                && taskFinder.equals(ui.taskFinder) && parser.equals(ui.parser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, taskList, storage, taskFinder);
    }
}
