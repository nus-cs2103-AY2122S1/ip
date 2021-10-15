package me.yukun99.ip;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import me.yukun99.ip.core.Parser;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.ui.Ui;

/**
 * Simple task tracking chat bot.
 */
public class HelpBot extends Application {
    // Name for my instance of help bot, not so fancy anymore. :(
    private static final String LOGO = "Bob";

    // Ui instance to handle Gui.
    private final Ui ui;

    /**
     * Constructor for a HelpBot instance.
     */
    public HelpBot() throws HelpBotIoException {
        TaskFinder taskFinder = new TaskFinder();
        TaskList taskList = new TaskList(taskFinder);
        Storage storage = new Storage(taskList);
        storage.loadSavedTasks();
        Parser parser = new Parser(this, taskList, storage, taskFinder);
        this.ui = new Ui(LOGO, taskList, storage, taskFinder, parser);
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelpBot.class.getResource("/views/Window.fxml"));
        this.ui.start(stage, fxmlLoader);
    }

    @Override
    public void stop() {
        ui.exit();
    }
}
