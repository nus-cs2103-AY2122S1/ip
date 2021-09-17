package duke;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Duke contains a main method which runs the program.
 *
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private ArrayList<Command> commandHistory;

    /**
     * Constructor of Duke. UI object, storage and tasks are instantiated.
     *
     */
    public Duke() {
        storage = new Storage(".\\level-7.txt");
        commandHistory = new ArrayList<>();
        try {
            tasks = new TaskList(storage.load());
            assert tasks != null: "tasks shouldn't be null here!";
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
     *
     * @return A String array with the input as the first element, and response as the second element.
     */

    @FXML
    String[] getResponse(String input) {
        String result = "";
        Command command;
        try {
            if (input.equals("bye")) {
                command = new ByeCommand(tasks);
            } else if (input.equals("list")) {
                command = new PrintListCommand(tasks);
            } else if (input.startsWith("done")) {
                command = new MarkAsDoneCommand(tasks, input);
            } else if (input.startsWith("delete")) {
                command = new DeleteTaskCommand(tasks, input);
            } else if (input.startsWith("find")) {
                command = new FindTaskCommand(tasks, input);
            } else if (input.startsWith("todo") || input.startsWith("event") || input.startsWith("deadline")) {
                command = new AddTaskCommand(tasks, input);
            } else if (input.equals("undo")) {
                command = new UndoCommand(tasks, commandHistory);
            } else {
                throw new InvalidCommandException();
            }
            commandHistory.add(command);
            result += command.run();
            storage.updateFile(tasks);
        } catch (DukeException e) {
            result = e.getMessage();
        } catch (NumberFormatException e) {
            result = "Task number is invalid!";
        } catch (IOException e) {
            result = "File failed to load!";
        }
        assert result != null: "result cannot be null here!";
        return new String[]{input, result};
    }
}
