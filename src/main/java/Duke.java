import exceptions.InvalidInputException;

import commands.Command;

import tasks.TaskList;

import utils.Parser;
import utils.Storage;
import utils.Ui;

public class Duke {
    private final String FILE_PATH = "./data/duke.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        this.taskList = new TaskList(this.storage.load());

    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            String message = command.execute(this.taskList, this.ui, this.storage);
            this.storage.save(this.taskList.getFormattedData());
            return message;
        } catch (InvalidInputException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Run Duke ChatBot
     */
    public void run() {
        ui.printWelcomeMessage();

        boolean isExitCommand = false;

        while (!isExitCommand) {
            try {
                String userCommand = ui.readCommand();
                Command command = Parser.parse(userCommand);
                isExitCommand = command.execute(this.taskList, this.ui, this.storage).equals("Goodbye Courier!");
                this.storage.save(this.taskList.getFormattedData());
            } catch (InvalidInputException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.printExitMessage();
    }
}
