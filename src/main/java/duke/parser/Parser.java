package duke.parser;

import java.util.ArrayList;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.command.TodoCommand;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

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
     * @return String representation of command parsed.
     */
    public String parse(String input) throws DukeException {
        String commandInput = input.split("\\s+")[0];
        Command command;
        switch (commandInput) {
        case "list":
            command = new ListCommand(taskList);
            break;
        case "done":
            command = new DoneCommand(taskList, input);
            break;
        case "todo":
            command = new TodoCommand(taskList, input);
            break;
        case "deadline":
            command = new DeadlineCommand(taskList, input);
            break;
        case "event":
            command = new EventCommand(taskList, input);
            break;
        case "delete":
            command = new DeleteCommand(taskList, input);
            break;
        case "find":
            command = new FindCommand(taskList, input);
            break;
        case "bye":
            command = new ByeCommand(taskList);
            break;
        case "tag":
            command = new TagCommand(taskList, input);
            break;
        default:
            throw new DukeException(Ui.getUnknownInputMsg());
        }
        return command.execute();
    }
}
