package duke.parser;

import java.util.ArrayList;

import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.data.TaskHandler;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Class that makes sense of the user commands to Duke.
 *
 * @author Won Ye Ji
 */
public class Parser {
    private static boolean isTerminated = false;
    private TaskHandler taskHandler;
    private Storage storage;

    /**
     * Initialise variables to run Duke.
     */
    public void initialiseDuke() {
        try {
            storage = new Storage("./data/taskList.txt");
            ArrayList<Task> list = storage.loadTasks();
            taskHandler = new TaskHandler(list);
        } catch (DukeException e) {
            System.err.println("Error: Unable to initialise duke");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs Duke.
     *
     * @param cmd User input.
     * @return Duke's response to the user.
     */
    public String runDuke(String cmd) throws DukeException {
        String commandWord = cmd.split(" ")[0].toUpperCase();
        switch (commandWord) {
        case ListCommand.COMMAND_WORD:
            ListCommand lc = new ListCommand(taskHandler, storage);
            return lc.execute(cmd);
        case DoneCommand.COMMAND_WORD:
            DoneCommand dc = new DoneCommand(taskHandler, storage);
            return dc.execute(cmd);
        case DeleteCommand.COMMAND_WORD:
            DeleteCommand dlc = new DeleteCommand(taskHandler, storage);
            return dlc.execute(cmd);
        case ToDoCommand.COMMAND_WORD:
            ToDoCommand tc = new ToDoCommand(taskHandler, storage);
            return tc.execute(cmd);
        case DeadlineCommand.COMMAND_WORD:
            DeadlineCommand deadlinec = new DeadlineCommand(taskHandler, storage);
            return deadlinec.execute(cmd);
        case EventCommand.COMMAND_WORD:
            EventCommand ec = new EventCommand(taskHandler, storage);
            return ec.execute(cmd);
        case FindCommand.COMMAND_WORD:
            FindCommand fc = new FindCommand(taskHandler, storage);
            return fc.execute(cmd);
        case ByeCommand.COMMAND_WORD:
            isTerminated = true;
            ByeCommand bc = new ByeCommand(taskHandler, storage);
            return bc.execute(cmd);
        default:
            throw new DukeException(Ui.inputUnknown());
        }
    }
}
