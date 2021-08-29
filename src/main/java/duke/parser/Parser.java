package duke.parser;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.data.task.Task;
import duke.data.exception.DukeException;
import duke.ui.Ui;
import duke.command.*;

import java.util.ArrayList;

/**
 * Class that makes sense of the user commands to Duke.
 *
 * @author Wang Hong Yong
 */
public class Parser {
    private Storage storage;
    private ArrayList<Task> list;
    private TaskList taskList;

    /**
     * Constructor for the Parser class.
     */
    public Parser() {
        this.storage = new Storage();
        this.list = storage.loadFile();
        this.taskList = new TaskList(list, storage);
    }

    /**
     * Parses command and runs.
     *
     * @param input String representing the command.
     */
    public void parse(String input) throws DukeException {
        String commandInput = input.split("\\s+")[0];
        Command command;
        switch (commandInput) {
        case "list":
            command = new ListCommand(taskList);
            command.execute();
            break;
        case "done":
            command = new DoneCommand(taskList, input);
            command.execute();
            break;
        case "todo":
            command = new TodoCommand(taskList, input);
            command.execute();
            break;
        case "deadline":
            command = new DeadlineCommand(taskList, input);
            command.execute();
            break;
        case "event":
            command = new EventCommand(taskList, input);
            command.execute();
            break;
        case "delete":
            command = new DeleteCommand(taskList, input);
            command.execute();
            break;
        case "find":
            command = new FindCommand(taskList, input);
            command.execute();
            break;
        case "bye":
            command = new ByeCommand(taskList);
            command.execute();
            break;
        default:
            throw new DukeException(Ui.getUnknownInputMsg());
        }

    }
}
