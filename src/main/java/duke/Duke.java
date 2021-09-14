package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;
import javafx.fxml.FXML;

public class Duke {
    private static final String FILEPATH = "duke.txt";
    private Storage storage;
    private TaskList taskList;
    @FXML
    private Ui ui;

    /**
     * Constructs duke.Duke objects
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (DukeException e) {
            ui.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Uses Parser to process the input.
     * @param input input string from user
     * @return output to user
     */
    public String process(String input) {
        Command command;
        try {
            command = Parser.parseInput(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
        String output = command.execute(taskList, ui, storage);
        if (output.equals("bye")) {
            System.exit(0);
        }
        return output;
    }

    /**
     * Runs program.
     */
    public void run() {
        ui.showGreeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String output = process(input);
            ui.showMessage(output);
        }
    }

    public static void main(String[] args) {
        new duke.Duke().run();
    }

}
