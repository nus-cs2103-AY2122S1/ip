package duke;

import duke.command.Command;

import java.io.IOException;

public class Duke {
    private static final String PATHNAME = "data/duke.txt";

    private Ui ui;
    private Storage storage;
    private static TaskList taskList = new TaskList();

    /**
     * Constructor for Duke object
     *
     * @param filePath relative path to where the data was stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            TaskList temp = storage.readFile();
            taskList = temp == null ? new TaskList() : new TaskList(temp);
        } catch (IOException e) {
            ui.printFileError(e);
        }
    }


    /**
     * Runs duke program - a chatbot with personality
     */
    public void run() {
        ui.printGreetings();
        //isExit implementation is referenced from classmate Wu Xiaoyun
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                Command c = Parser.parseCommands(input);
                c.execute(this.taskList, this.ui, this.storage);
                isExit = c.isExit;
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
}
