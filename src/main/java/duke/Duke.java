package duke;

import duke.exception.DukeException;
import javafx.fxml.FXML;

import java.io.IOException;


/**
 * Duke contains a main method which runs the program.
 *
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor of Duke. UI object, storage and tasks are instantiated.
     *
     */
    public Duke() {
        storage = new Storage(".\\src\\main\\level-7.txt");
        try {
            tasks = new TaskList(storage.load());
            assert tasks != null: "tasks shouldnt be null here!";
        } catch (DukeException e) {
            System.out.println("RESET!");
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("File failed to load.");
        }
    }

    /**
     * A method that allows user give input and runs the program.
     * Contains a parser which reads command from the input.
     */


    @FXML
    String getResponse(String input) {
        String result = "";
        if (input.equals("bye")) {
            result += "Babai! See you again soon! XD";
        } else if (input.equals("list")) {
            result += tasks.printList();
        } else if (input.startsWith("done")) {
            try {
                result += tasks.markAsDone(input);
            } catch (DukeException e) {
                result = e.getMessage();
            } catch (NumberFormatException e) {
                result = "Task number is invalid!";
            }
        } else if (input.startsWith("delete")) {
            try {
                result += tasks.deleteTask(input);
            } catch (DukeException e) {
                result += e.getMessage();
            }
        } else if (input.startsWith("find")) {
            try {
                result += tasks.findTask(input);
            } catch (DukeException e) {
                result += e.getMessage();
            }
        } else {
            try {
                result += tasks.addTask(input);
            } catch (DukeException e) {
                result += e.getMessage();
            }
        }
        try {
            storage.updateFile(tasks);
        } catch (IOException e) {
            return "File failed to load!";
        }
        assert result != null: "result cannot be null here!";
        return result;
    }
}
