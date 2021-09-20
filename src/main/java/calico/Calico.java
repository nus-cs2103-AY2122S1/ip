package calico;

// import java packages
import java.util.ArrayList;

// import duke packages
import calico.command.Todo;
import calico.task.Task;
import calico.task.TaskList;
import calico.util.Parser;
import calico.util.Storage;
import calico.util.Ui;

/**
 * A chatbot that stores tasks given by a user.
 */
public class Calico {
    private Storage storage;
    private TaskList commandList;
    private Ui ui;
    private int count;

    /**
     * Creates a Duke chatbot.
     *
     * @param filePath Filepath to save tasks and load tasks.
     */
    public Calico(String filePath) {
        ui = new Ui("", "");
        storage = new Storage(filePath);
        try {
            commandList = new TaskList(storage.loadTasks());
        } catch (CalicoException e) {
            ui.showLoadingError();
            commandList = new TaskList();
        }
        count = 0 + commandList.getLength();
    }

    /**
     * Converts Duke to string format.
     *
     * @return Duke as a string.
     */
    @Override
    public String toString() {
        return "im a chatbot ☺ - calico";
    }

    /**
     * Generates a response to user input.
     *
     * @param cmd Command from user
     * @return Chatbot response
     */
    public String getResponse(String cmd) {
        if (Parser.isBye(cmd)) {
            return ui.sayGoodbye();
        }
        try {
            // when command given is "list"
            if (Parser.isList(cmd)) {
                return ui.showList(commandList);
            } else if (Parser.isDone(cmd)) {
                // when command given is "done"
                return processDoneCmd(cmd);
            } else if (Parser.isValidTask(cmd)) {
                // when command is a valid todo/deadline/event command
                return processValidCmd(cmd);
            } else if (Parser.isDelete(cmd)) {
                return processDeleteCmd(cmd);
            } else if (Parser.isFind(cmd)) {
                return processFindCmd(cmd);
            } else {
                processInvalidCmd(cmd);
            }
        } catch (CalicoException e) {
            return ui.showDukeError(e);
        }
        // should not happen
        return "i cant seem to process the command";
    }

    /**
     * Say hello to user.
     *
     * @return Hello message.
     */
    public String intro() {
        return ui.sayHello();
    }

    /**
     * Processes a valid done command.
     *
     * @param cmd Command to be processed.
     * @return Output of command.
     * @throws CalicoException If task to be marked completed cannot be found.
     */
    private String processDoneCmd(String cmd) throws CalicoException {
        int itemNo = Parser.parseDoneCmd(cmd);

        // Make sure itemNo is within limit
        if ((itemNo <= count) && (itemNo > 0)) {
            commandList.markDone(itemNo - 1);
            storage.saveTasks(commandList.getTasks());
        } else {
            // throw exception when itemNo not within limit
            throw new CalicoException("i cant seem to find the task you're looking for");
        }
        return ui.showTaskDone(commandList, itemNo - 1);
    }

    /**
     * Processes a valid task command.
     *
     * @param cmd Task command to be processed.
     * @return Output of command.
     * @throws CalicoException If unable to process task properly.
     */
    private String processValidCmd(String cmd) throws CalicoException {
        String task = Parser.getTaskName(cmd);
        String desc = Parser.getDesc(cmd);
        if (Parser.isMissingArg(cmd)) {
            // when desc is not empty
            // case when only a whitespace follows a command
            throw new CalicoException("the description of a " + task + " cannot be empty");
        } else {
            // Separate into cases
            switch (task) {
            case "todo":
                commandList.add(new Todo(desc));
                break;
            case "deadline":
                commandList.add(Parser.getDeadlineTask(desc));
                break;
            case "event":
                commandList.add(Parser.getEventTask(desc));
                break;
            default:
                // should not happen
                throw new CalicoException("the name of task is not valid");
            }
            storage.saveTasks(commandList.getTasks());
            count++;
            return ui.showTaskAdded(commandList, count - 1);
        }
    }

    /**
     * Processes a valid delete command.
     *
     * @param cmd Command to be processed.
     * @return Output of command.
     * @throws CalicoException If task to be deleted cannot be found.
     */
    private String processDeleteCmd(String cmd) throws CalicoException {
        int itemNo = Parser.parseDeleteCmd(cmd);

        // Make sure itemNo is within limit
        if ((itemNo <= count) && (itemNo > 0)) {
            // Remove item from lists
            String deletionMessage = ui.showTaskDeleted(commandList, itemNo - 1);
            commandList.remove(itemNo - 1);
            storage.saveTasks(commandList.getTasks());
            count--;
            return deletionMessage;
        } else {
            // throw exception when itemNo not within limit
            throw new CalicoException("i cant seem to find the task you're looking for");
        }
    }

    /**
     * Processes a valid find command.
     *
     * @param cmd Command to be processed.
     * @return Output of command.
     * @throws CalicoException If description of command is empty.
     */
    private String processFindCmd(String cmd) throws CalicoException {
        String desc = Parser.getDesc(cmd);
        if (Parser.isMissingArg(cmd)) {
            // when desc is not empty
            // case when only a whitespace follows a command
            throw new CalicoException("the description of a find cannot be empty");
        }
        ArrayList<Task> matchingTasks = commandList.find(desc);
        if (matchingTasks.isEmpty()) {
            return ui.showNoMatch();
        } else {
            return ui.showMatchingTasks(matchingTasks);
        }
    }
    
    /**
     * Processes an invalid command.
     *
     * @param cmd Command to be processed.
     * @throws CalicoException If task description is missing or command is not recognised.
     */
    private void processInvalidCmd(String cmd) throws CalicoException {
        // throw exception when command not found
        // Error handling for todo, deadline & event
        if (Parser.isEmptyTask(cmd)) {
            throw new CalicoException("the description of a " + Parser.getTaskName(cmd) + " cannot be empty");
        } else {
            throw new CalicoException("im sorry, but i dont know what that means :(");
        }
    }
}
