package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.util.List;

import static duke.main.Parser.getCommandFromInput;
import static duke.main.Parser.getDescriptionFromInput;
import static duke.main.Parser.getTaskNum;

/**
 * Handles the execution of user commands.
 */
public class Executor {
    private Storage storage;
    private Ui ui;
    private TaskList taskList;


    /**
     * Constructor for Executor.
     *
     * @param storage  Storage from Duke.
     * @param ui       Ui from Duke.
     * @param taskList TaskList from Duke.
     */
    public Executor(Storage storage, Ui ui, TaskList taskList) {
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
     * Executes the command provided.
     *
     * @param command     String type of command.
     * @param description String description of the command.
     * @return String response upon executing the command.
     */
    private String executeCommand(String command, String description) {
        String response;

        //@formatter:off
        switch (command) {
        case "bye":
            response = ui.exitWithGoodbye();
            break;
        case "find":
            List<Task> matches = taskList.findTasks(description);
            response = ui.getMatchingTasksSummary(matches);
            break;
        case "tag":
            String[] taskNumAndTag = description.split(" ", 2);
            String taskNum = taskNumAndTag[0];

            if (taskNumAndTag.length < 2) {
                throw new DukeException("Please enter a tag");
            }

            String tag = taskNumAndTag[1];
            Task toTag = selectTask(taskNum);
            response = toTag.addTag(tag);
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
        //@formatter:on

        storage.writeToStorage(taskList);
        return response;
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
            throw new DukeException("Sorry, I can't seem to find that task\n");
        }
        return selectedTask;
    }

}
