package sun.parser;

import java.util.ArrayList;

import sun.command.ArchiveCommand;
import sun.command.ArchivesCommand;
import sun.command.ByeCommand;
import sun.command.DeadlineCommand;
import sun.command.DeleteCommand;
import sun.command.DoneCommand;
import sun.command.EventCommand;
import sun.command.FindCommand;
import sun.command.HelpCommand;
import sun.command.ListCommand;
import sun.command.ToDoCommand;
import sun.data.TaskHandler;
import sun.data.exception.SunException;
import sun.data.task.Task;
import sun.storage.Storage;
import sun.ui.Ui;

/**
 * Class that makes sense of the user commands to Sun.
 *
 * @author Won Ye Ji
 */
public class Parser {
    private static boolean isTerminated = false;
    private TaskHandler taskHandler;
    private Storage storage;
    private Storage archives;

    /**
     * Initialise variables to run Sun.
     */
    public void initialiseSun() {
        try {
            storage = new Storage("./data/taskList.txt");
            archives = new Storage("./data/archive.txt");
            ArrayList<Task> storageList = storage.loadTasks();
            ArrayList<Task> archiveList = archives.loadTasks();
            taskHandler = new TaskHandler(storageList, archiveList);
        } catch (SunException e) {
            System.err.println("Error: Unable to initialise Sun");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs Sun.
     *
     * @param cmd User input.
     * @return Sun's response to the user.
     */
    public String runSun(String cmd) throws SunException {
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
            throw new SunException(Ui.getInputUnknownError());
        }
    }
}
