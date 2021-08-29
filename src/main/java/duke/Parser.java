package duke;

import duke.exceptions.DukeException;
import duke.exceptions.DukeExitException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.nio.file.Path;

/**
 * Parser to parse user commands.
 */
public class Parser {
    UI ui;
    TaskArrayList taskList;
    Path storagePath;

    /**
     * Constructor for Parser.
     *
     * @param ui UI object to print to.
     * @param taskList TaskArrayList object to work with.
     * @param storagePath Path object representing path to savefile.
     */
    Parser(UI ui, TaskArrayList taskList, Path storagePath) {
        this.ui = ui;
        this.taskList = taskList;
        this.storagePath = storagePath;
    }

    /**
     * Runs a user input string.
     *
     * @param userInput String keyed in by user.
     * @return isExit, true if duke should exit after this command.
     * @throws DukeException if unable to run argument.
     */
    public String run(String userInput) throws DukeException {
        String[] cmd_args = userInput.split(" ", 2);
        Task toAdd;

        // switch case to split by command
        switch (cmd_args[0]) {
        case ("bye"):
            return runBye(cmd_args);
//            break;

        case ("list"):
            return runList(cmd_args);
//            break;

        case ("done"):
            return runDone(cmd_args);
//            break;

        case ("delete"):
            return runDelete(cmd_args);
//            break;

        case ("find"):
            return runFind(cmd_args);
//            break;

        case ("todo"):
            return runTodo(cmd_args);
//            break;

        case ("deadline"):
            return runDeadline(cmd_args);
//            break;

        case ("event"):
            return runEvent(cmd_args);
//            break;

        default:
            throw new DukeException("Unrecognised command");
        }
//        return "";
    }

    private String runBye(String[] cmd_args) throws DukeException {
        if (cmd_args.length > 1) {
            throw new DukeException("command bye takes no arguments.");
        }
        throw new DukeExitException("BYE");
//        return "BYEEEEEE!\nHope to see you again soon :)";
//        ui.display("BYEEEEEE!\nHope to see you again soon :)");
    }

    private String runList(String[] cmd_args) throws DukeException {
        if (cmd_args.length > 1) {
            throw new DukeException("command list takes no arguments.");
        }
        return taskList.enumerate();
//        ui.display(taskList.enumerate());
    }

    private String runDone(String[] cmd_args) throws DukeException {
        if (cmd_args.length != 2 || !cmd_args[1].matches("[0-9]+")) {
            throw new DukeException(TaskArrayList.DONE_USAGE_TEXT);
        }
        return taskList.markDone(Integer.parseInt(cmd_args[1]));
//        ui.display(taskList.markDone(Integer.parseInt(cmd_args[1])));
    }

    private String runDelete(String[] cmd_args) throws DukeException {
        if (cmd_args.length != 2) {
            throw new DukeException(TaskArrayList.DELETE_USAGE_TEXT);
        }
        if (!cmd_args[1].matches("[0-9]+")) {
            throw new DukeException(TaskArrayList.DELETE_USAGE_TEXT);
        }
        return taskList.deleteTask(Integer.parseInt(cmd_args[1]));
//        ui.display(taskList.deleteTask(Integer.parseInt(cmd_args[1])));
    }

    private String runFind(String[] cmd_args) throws DukeException {
        if (cmd_args.length != 2) {
            throw new DukeException(TaskArrayList.FIND_USAGE_TEXT);
        }
        return taskList.find(cmd_args[1]);
//        ui.display(taskList.find(cmd_args[1]));
    }

    private String runTodo(String[] cmd_args) throws DukeException {
        if (cmd_args.length != 2) {
            throw new DukeException(Todo.USAGE_TEXT);
        }
        Task toAdd = new Todo(cmd_args[1]);
        return taskList.addTask(toAdd);
//        ui.display(taskList.addTask(toAdd));
    }

    private String runDeadline(String[] cmd_args) throws DukeException {
        if (cmd_args.length != 2) {
            throw new DukeException(Deadline.USAGE_TEXT);
        }
        String[] name_by = cmd_args[1].split("/by", 2);
        if (name_by.length != 2) {
            throw new DukeException(Deadline.USAGE_TEXT);
        }
        Task toAdd = new Deadline(name_by[0], name_by[1]);
        return taskList.addTask(toAdd);
//        ui.display(taskList.addTask(toAdd));
    }

    private String runEvent(String[] cmd_args) throws DukeException {
        if (cmd_args.length != 2) {
            throw new DukeException(Event.USAGE_TEXT);
        }
        String[] name_at = cmd_args[1].split("/at", 2);
        if (name_at.length != 2) {
            throw new DukeException(Event.USAGE_TEXT);
        }
        Task toAdd = new Event(name_at[0], name_at[1]);
        return taskList.addTask(toAdd);
//        ui.display(taskList.addTask(toAdd));
    }


}
