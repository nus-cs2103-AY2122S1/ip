package shybot;

import java.io.IOException;

import shybot.command.Command;
import shybot.command.WelcomeCommand;
import shybot.exception.ShyBotException;
import shybot.task.TaskList;

/**
 * ShyBot is a educational software project designed to take you through
 * the steps of building a small software incrementally, while applying
 * as many Java and SE techniques as possible along the way.
 */
public class ShyBot {
    private String response;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public ShyBot() {
    }

    /**
     * Constructs a ShyBot with the specified filePath.
     *
     * @param filePath Path of the file to store the task list.
     * @param isGui    Set to true if ShyBot is on GUI.
     */
    public ShyBot(String filePath, boolean isGui) {
        if (isGui) {
            this.setUpGui(filePath);
        } else {
            this.setUpUi(filePath);
        }
    }

    private void setUpGui(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            String successMessage = "Data loaded successfully!";
            this.setResponse(successMessage);
        } catch (ShyBotException | IOException e) {
            String errorMessage = "☹ OOPS!!! Seems like your data is corrupted. "
                + "Please make sure you data file has the correct format.";
            this.setResponse(errorMessage);
            tasks = new TaskList();
        }
    }

    private void setUpUi(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ShyBotException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Sets ShyBot's response.
     *
     * @param response ShyBot's response.
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Returns ShyBot's most recent response.
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Returns ShyBot's response based on the input.
     *
     * @param input User input.
     * @return ShyBot's response.
     */
    public String getResponse(String input) throws ShyBotException {
        Command c = Parser.parse(input);
        c.execute(this, tasks, storage);
        return this.response;
    }

    /**
     * Returns a warm welcome by ShyBot.
     *
     * @return ShyBot's welcome statement.
     */
    public String getWelcomeResponse() {
        Command c = new WelcomeCommand();
        try {
            c.execute(this, tasks, storage);
            return this.response;
        } catch (ShyBotException e) {
            return e.getMessage();
        }
    }

    /**
     * Runs text-based UI.
     */
    public void run() {
        this.sayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(this, tasks, storage);
                ui.showMessage(this.response);
                isExit = c.isExit();
            } catch (ShyBotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        Ui.getScanner().close();
    }

    private void sayWelcome() {
        ui.showLine();
        Command c = new WelcomeCommand();
        try {
            c.execute(this, tasks, storage);
        } catch (ShyBotException e) {
            ui.showError(e.getMessage());
        }
        ui.showMessage(this.response);
        ui.showLine();
    }

    public static void main(String[] args) {
        new ShyBot("data/tasks.txt", false).run();
    }

}
