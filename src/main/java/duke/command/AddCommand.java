package duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * This class encapsulates the command to add tasks. The supported
 * Task types are Deadline, Event and Todo.
 */
public class AddCommand extends Command {

    private String taskType;

    /**
     * Constructor for AddCommand.
     * @param userInput String containing the user input
     * @param taskType String identifying type of task to be added
     */
    public AddCommand(String userInput, String taskType) {
        super(userInput);
        this.taskType = taskType;
    }

    /**
     * Adds corresponding task based on taskType that is provided to AddCommand.
     *
     * @param taskList TaskList that contains the list of tasks
     * @param ui User interface for the chat bot
     * @param storage Storage object that handles saving and loading of data
     * @throws DukeException when an error occurs during saving data
     * @returns Outputs string describing command executed
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        if (taskType.equals("deadline")) {
            return addDeadline(this.getUserInput(), taskList, ui, storage);
        }

        if (taskType.equals("event")) {
            return addEvent(this.getUserInput(), taskList, ui, storage);
        }

        if (taskType.equals(("todo"))) {
            return addTodo(this.getUserInput(), taskList, ui, storage);
        }
            return "";
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private String displayAddedTask(Task currentTask, TaskList taskList, Ui ui) {
        String displayTask = String
                .format("Got it. I've added this duke.task: \n%s\nNow you have %d tasks in the list.",
                        currentTask, taskList.size());
        return ui.printMessage(displayTask);
    }

    private String addDeadline(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<String> inputArray = Arrays.asList(userInput.split(" /by "));
        String by = inputArray.get(1);
        ArrayList<String> descriptionArray = new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" ")));
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Deadline newDeadline = new Deadline(description, by);
        taskList.addTask(newDeadline);
        storage.saveData(taskList);
        return displayAddedTask(newDeadline, taskList, ui);

    }

    private String addEvent(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<String> inputArray = Arrays.asList(userInput.split(" /at "));
        String timeFrame = inputArray.get(1);
        ArrayList<String> descriptionArray = new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" ")));
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Event newEvent = new Event(description, timeFrame);
        taskList.addTask(newEvent);
        storage.saveData(taskList);
        return displayAddedTask(newEvent, taskList, ui);

    }

    private String addTodo(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<String> inputArray = Arrays.asList(userInput.split(" "));

        if (inputArray.size() <= 1) {
            throw new DukeException("todo");
        }

        ArrayList<String> descriptionArray = new ArrayList<String>(inputArray);
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Todo newTodo = new Todo(description);
        taskList.addTask(newTodo);
        storage.saveData(taskList);
        return displayAddedTask(newTodo, taskList, ui);
    }

}
