package duke;

import java.io.IOException;

import duke.command.Command;
import javafx.application.Platform;

public class Duke {
    private static final String PATHNAME = "data/duke.txt";
    private static TaskList taskList = new TaskList();
    private Ui ui;
    private Storage storage;
    private ResponseFormatter responseFormatter;
    private History history;

    /**
     * Constructor for Duke object
     *
     * @param filePath relative path to where the data was stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        responseFormatter = new ResponseFormatter();
        storage = new Storage(filePath);
        history = new History();
        try {
            TaskList temp = storage.readFile();
            taskList = temp == null ? new TaskList() : new TaskList(temp);
        } catch (IOException e) {
            ui.printFileError(e);
        }
    }


    /**
     * Runs duke program - a chatbot
     */
    public void run() {
        ui.printGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parseCommands(input);
                c.execute(this.taskList, this.ui, this.storage, history);
                isExit = c.getExit();
            } catch (IOException e) {
                ui.printFileError(e);
            }

        }
    }

    /**
     * Driver for duke
     *
     * @param args arguments that user inputs
     */
    public static void main(String[] args) {
        new Duke(PATHNAME).run();
    }

    public String getResponse(String input) {
        assert !input.isEmpty() : "input should not be empty!";
        try {
            Command command = Parser.parseCommands(input);
            boolean isExit = command.getExit();

            if (isExit) {
                Platform.exit();
            }

            return command.execute(taskList, responseFormatter, storage, history);
        } catch (IOException e) {
            return responseFormatter.formatFileError(e);
        }
    }
}
