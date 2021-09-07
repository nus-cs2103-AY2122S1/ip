package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.util.List;

/**
 * Handles the parsing of user commands.
 */
public class Parser {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructor for Parser.
     *
     * @param storage  Storage from Duke.
     * @param ui       Ui from Duke.
     * @param taskList TaskList from Duke.
     */
    public Parser(Storage storage, Ui ui, TaskList taskList) {
        this.storage = storage;
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses user inputs.
     *
     * @param input String from user.
     * @return String response from the parser.
     */
    public String parseAndExecute(String input) {
        String command = getCommandFromInput(input);
        String description = getDescriptionFromInput(input);
        String response = executeCommand(command, description);
        return response;
    }

    /**
     * Returns the command in user input.
     *
     * @param input String from user.
     * @return String command.
     */
    private String getCommandFromInput(String input) {
        String[] commandAndDesc = splitInput(input);
        return commandAndDesc[0];
    }

    /**
     * Returns the description in user input.
     *
     * @param input String from user.
     * @return String description.
     */
    private String getDescriptionFromInput(String input) {
        String[] commandAndDesc = splitInput(input);
        boolean containsDescription = commandAndDesc.length == 2;
        return containsDescription ? commandAndDesc[1] : "";
    }

    /**
     * Splits the input into its command and description.
     *
     * @param input String from user.
     * @return String[] containing parts of the input.
     */
    private String[] splitInput(String input) {
        return input.split(" ", 2);
    }

    /**
     * Executes the command provided.
     *
     * @param command     String type of command.
     * @param description String description of the command.
     * @return String response upon executing the command.
     */
    private String executeCommand(String command, String description) {
        String response;
        switch (command) {
            case "bye":
                response = ui.exitWithGoodbye();
                break;
            case "find":
                List<Task> matches = taskList.findTasks(description);
                response = ui.getMatchingTasksSummary(matches);
                break;
            case "clear":
                String resetTaskMessage = storage.resetTasks();
                String clearTaskMessage = taskList.clearTasks();
                response = resetTaskMessage + clearTaskMessage;
                break;
            case "list":
                response = ui.getTaskListSummary(taskList);
                break;
            case "deadline":
                response = taskList.addTask(new Deadline(description));
                break;
            case "event":
                response = taskList.addTask(new Event(description));
                break;
            case "todo":
                response = taskList.addTask(new ToDo(description));
                break;
            case "done":
                Task selectedTask = selectTask(description);
                response = selectedTask.markAsDone();
                break;
            case "delete":
                Task toDelete = selectTask(description);
                response = taskList.deleteTask(toDelete);
                break;
            default:
                if (command.equals("")) {
                    response = ui.getEmptyInputMessage();
                } else {
                    response = ui.getUnknownCommandMessage(command);
                }
        }
        updateStorage();
        return response;
    }

    /**
     * Commits the changes in taskList to storage.
     */
    private void updateStorage() {
        storage.writeToStorage(taskList);
    }

    /**
     * Selects the task with the given description.
     *
     * @param description String number corresponding to the task.
     * @return Task selected.
     */
    private Task selectTask(String description) {
        int taskNum = getTaskNum(description);
        Task selectedTask = taskList.getTask(taskNum);
        if (selectedTask == null) {
            throw new DukeException("\tSorry, I can't seem to find that task\n");
        }
        return selectedTask;
    }

    /**
     * Parses the description for the task number.
     *
     * @param description String containing the task number.
     * @return int task number.
     */
    private int getTaskNum(String description) {
        try {
            return Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException("\tI'm Sorry, WHAT?!?!\n");
        }
    }
}
