package duke;

import java.nio.file.Path;
import java.util.function.Supplier;

import duke.exceptions.DukeException;
import duke.exceptions.DukeExitException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;



/**
 * Parser to parse user commands.
 */
public class Parser {
    private TaskArrayList taskList;
    private Path storagePath;

    /**
     * Constructor for Parser.
     *
     * @param taskList    TaskArrayList object to work with.
     * @param storagePath Path object representing path to savefile.
     */
    Parser(TaskArrayList taskList, Path storagePath) {
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
        String[] cmdArgsArr = userInput.split(" ", 2);

        // switch case to split by command
        switch (cmdArgsArr[0]) {
        case ("bye"):
            return runBye(cmdArgsArr);

        case ("list"):
            return runList(cmdArgsArr);

        case ("done"):
            return runDone(cmdArgsArr);

        case ("delete"):
            return runDelete(cmdArgsArr);

        case ("find"):
            return runFind(cmdArgsArr);

        case ("todo"):
            return runTodo(cmdArgsArr);

        case ("deadline"):
            return runDeadline(cmdArgsArr);

        case ("event"):
            return runEvent(cmdArgsArr);

        default:
            throw new DukeException("Unrecognised command");
        }
    }

    // Use Boolean Suppliers for test conditions that can cause crash if previous gate fails
    // eg will only test arr[1] validity if previous gate of arr.length == 2 passes

    private String runBye(String[] cmdArgsArr) throws DukeException {
        boolean hasWrongArgumentCount = cmdArgsArr.length > 1;

        if (hasWrongArgumentCount) {
            throw new DukeException("command bye takes no arguments.");
        }

        throw new DukeExitException("BYE");
    }

    private String runList(String[] cmdArgsArr) throws DukeException {
        boolean hasWrongArgumentCount = cmdArgsArr.length > 1;

        if (hasWrongArgumentCount) {
            throw new DukeException("command list takes no arguments.");
        }

        return taskList.enumerate();
    }

    private String runDone(String[] cmdArgsArr) throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);
        Supplier<Boolean> isNotNumericArgument = () -> !cmdArgsArr[1].matches("[0-9]+");

        if (hasWrongArgumentCount || isNotNumericArgument.get()) {
            throw new DukeException(TaskArrayList.DONE_USAGE_TEXT);
        }

        return taskList.markDone(Integer.parseInt(cmdArgsArr[1]));
    }

    private String runDelete(String[] cmdArgsArr) throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);
        Supplier<Boolean> isNotNumericArgument = () -> !cmdArgsArr[1].matches("[0-9]+");

        if (hasWrongArgumentCount || isNotNumericArgument.get()) {
            throw new DukeException(TaskArrayList.DELETE_USAGE_TEXT);
        }

        return taskList.deleteTask(Integer.parseInt(cmdArgsArr[1]));
    }

    private String runFind(String[] cmdArgsArr) throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);

        if (hasWrongArgumentCount) {
            throw new DukeException(TaskArrayList.FIND_USAGE_TEXT);
        }

        return taskList.find(cmdArgsArr[1]);
    }

    private String runTodo(String[] cmdArgsArr) throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);

        if (hasWrongArgumentCount) {
            throw new DukeException(Todo.USAGE_TEXT);
        }

        Task toAdd = new Todo(cmdArgsArr[1]);
        return taskList.addTask(toAdd);
    }

    private String runDeadline(String[] cmdArgsArr) throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);
        Supplier<Boolean> isMissingByArgument = () -> (cmdArgsArr[1].split("/by", 2).length != 2);

        if (hasWrongArgumentCount || isMissingByArgument.get()) {
            throw new DukeException(Deadline.USAGE_TEXT);
        }

        String[] nameByPair = cmdArgsArr[1].split("/by", 2);
        Task toAdd = new Deadline(nameByPair[0], nameByPair[1]);
        return taskList.addTask(toAdd);
    }

    private String runEvent(String[] cmdArgsArr) throws DukeException {
        boolean hasWrongArgumentCount = (cmdArgsArr.length != 2);
        Supplier<Boolean> isMissingAtArgument = () -> (cmdArgsArr[1].split("/at", 2).length != 2);

        if (hasWrongArgumentCount || isMissingAtArgument.get()) {
            throw new DukeException(Event.USAGE_TEXT);
        }

        String[] nameAtPair = cmdArgsArr[1].split("/at", 2);
        Task toAdd = new Event(nameAtPair[0], nameAtPair[1]);
        return taskList.addTask(toAdd);
    }
}
