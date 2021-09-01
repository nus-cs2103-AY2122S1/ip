package me.yukun99.ip;

import java.io.IOException;
import java.util.Scanner;

import me.yukun99.ip.commands.Command;
import me.yukun99.ip.core.Parser;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskFinder;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidCommandException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;

/**
 * Simple task tracking chat bot.
 */
public class HelpBot {
    // Scanner to scan for text inputs.
    private final Scanner scanner;
    // Ui instance to handle text Ui.
    private final Ui ui;
    // Parser instance to parse user commands.
    private final Parser parser;

    // Whether to exit bot.
    private boolean canExit = false;

    /**
     * Constructor for a HelpBot instance.
     *
     * @param name Name of the bot to be created.
     * @param filepath Filepath of the input file.
     */
    public HelpBot(String name, String filepath) throws IOException {
        TaskFinder taskFinder = new TaskFinder();
        TaskList taskList = new TaskList(taskFinder);
        Storage storage = new Storage(filepath, taskList);
        storage.loadTasks();
        this.ui = new Ui(name, taskList, storage, taskFinder);
        this.scanner = storage.getInputs();
        this.parser = new Parser(this, this.scanner, taskList, this.ui, storage);
        this.ui.start();
        this.listen();
    }

    /**
     * Method to listen for user inputs.
     */
    private void listen() {
        while (scanner.hasNext()) {
            try {
                Command command = parser.parseCommand();
                command.run();
            } catch (HelpBotIllegalArgumentException | HelpBotInvalidCommandException
                    | HelpBotInvalidTaskTypeException | HelpBotDateTimeFormatException e) {
                ui.error(e);
            }
            if (canExit) {
                return;
            }
        }
    }

    /**
     * Method to exit the bot.
     */
    public void exit() {
        canExit = true;
        scanner.close();
        ui.exit();
    }
}
