package duke.parser;

import java.util.ArrayList;

import duke.command.ArchiveCommand;
import duke.command.ArchivesCommand;
import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
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
    private Storage archives;

    /**
     * Initialise variables to run Duke.
     */
    public void initialiseDuke() {
        try {
            storage = new Storage("./data/taskList.txt");
            archives = new Storage("./data/archive.txt");
            ArrayList<Task> storageList = storage.loadTasks();
            ArrayList<Task> archiveList = archives.loadTasks();
            taskHandler = new TaskHandler(storageList, archiveList);
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
            ListCommand lc = new ListCommand(taskHandler, storage, archives);
            return lc.execute(cmd);
        case DoneCommand.COMMAND_WORD:
            DoneCommand dc = new DoneCommand(taskHandler, storage, archives);
            return dc.execute(cmd);
        case DeleteCommand.COMMAND_WORD:
            DeleteCommand dlc = new DeleteCommand(taskHandler, storage, archives);
            return dlc.execute(cmd);
        case ToDoCommand.COMMAND_WORD:
            ToDoCommand tc = new ToDoCommand(taskHandler, storage, archives);
            return tc.execute(cmd);
        case DeadlineCommand.COMMAND_WORD:
            DeadlineCommand deadlinec = new DeadlineCommand(taskHandler, storage, archives);
            return deadlinec.execute(cmd);
        case EventCommand.COMMAND_WORD:
            EventCommand ec = new EventCommand(taskHandler, storage, archives);
            return ec.execute(cmd);
        case FindCommand.COMMAND_WORD:
            FindCommand fc = new FindCommand(taskHandler, storage, archives);
            return fc.execute(cmd);
        case HelpCommand.COMMAND_WORD:
            HelpCommand hc = new HelpCommand(taskHandler, storage, archives);
            return hc.execute(cmd);
        case ArchiveCommand.COMMAND_WORD:
            ArchiveCommand ac = new ArchiveCommand(taskHandler, storage, archives);
            return ac.execute(cmd);
        case ArchivesCommand.COMMAND_WORD:
            ArchivesCommand asc = new ArchivesCommand(taskHandler, storage, archives);
            return asc.execute(cmd);
        case ByeCommand.COMMAND_WORD:
            isTerminated = true;
            ByeCommand bc = new ByeCommand(taskHandler, storage, archives);
            return bc.execute(cmd);
        default:
            throw new DukeException(Ui.inputUnknown());
        }
    }
}
