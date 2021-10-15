package me.yukun99.ip.ui;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import me.yukun99.ip.core.Parser;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.ui.elements.Window;

/**
 * Class used to handle message the Ui and responses from the HelpBot.
 */
public class Ui {
    // Title of the HelpBot window.
    private static final String TITLE = "Bob v2.0 - Why are you even using me?";
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

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
     * @param taskList TaskList instance from the current HelpBot.
     * @param storage Storage instance from the current HelpBot.
     * @param taskFinder TaskFinder instance from the current HelpBot.
     * @param parser Parser instance from the current HelpBot.
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
     *
     * @param stage Stage to do startup for.
     * @param fxmlLoader FXMLLoader instance used to load fxml files to the stage.
     */
    public void start(Stage stage, FXMLLoader fxmlLoader) {
        this.stage = stage;
        stage.setResizable(false);
        try {
            AnchorPane anchorPane = fxmlLoader.load();
            fxmlLoader.<Window>getController().setup(parser, storage, name, taskList);
            assert anchorPane != null;
            Scene scene = new Scene(anchorPane);

            stage.setScene(scene);
            stage.setTitle(TITLE);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("Unable to load necessary files for GUI, closing in 3 seconds.");
            executorService.schedule(stage::close, 3, TimeUnit.SECONDS);
        } catch (HelpBotIoException e) {
            System.out.println(
                    "Unable to load tasks. Please delete your tasks file before continuing. Closing in 3 seconds.");
            executorService.schedule(stage::close, 3, TimeUnit.SECONDS);
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
