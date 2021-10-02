package duke;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Duke {
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initializes a new Duke Object so that it can run the programme.
     *
     * @param path relative position of the txt file
     */
    public Duke(String path) {
        ui = new Ui();
        storage = new Storage(path);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke Object by calling introduceDuke() from the Ui class. It is capable of
     * Understanding user's commands and catching errors when thrown.
     */
    public void run() {
        ui.introduceDuke();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = "";
            try {
                fullCommand = ui.readCommand();
                assert !fullCommand.equals("") : "fullCommand should not be empty";
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }

    }

    public String getResponse(String input) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(stream);
        PrintStream old = System.out;
        System.setOut(ps);

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        System.out.flush();
        System.setOut(old);

        return stream.toString();
    }

    public String introduceDuke() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(stream);
        PrintStream old = System.out;
        System.setOut(ps);
        ui.introduceDuke();
        System.out.flush();
        System.setOut(old);

        return stream.toString();
    }

    /**
     * This is the main file for this java programme.
     *
     * @param args There is no arguments that is required to run this programme.
     */
    public static void main(String[] args) {
        new Duke("./tasks.txt").run();
    }
}
