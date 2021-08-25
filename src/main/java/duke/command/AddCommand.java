package duke.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

public class AddCommand extends Command {

    private String taskType;

    public AddCommand(String userInput, String taskType) {
        super(userInput);
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {

        if (taskType.equals("deadline")) {
            addDeadline(this.getUserInput(), taskList, ui, storage);
        }

        if (taskType.equals("event")) {
           addEvent(this.getUserInput(), taskList, ui, storage);
        }

        if (taskType.equals(("todo"))) {
           addTodo(this.getUserInput(), taskList, ui, storage);
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }

    private void displayAddedTask(Task currentTask, TaskList taskList, Ui ui) {
        String displayTask = String.format("Got it. I've added this duke.task: \n%s\nNow you have %d tasks in the list.",
                                currentTask, taskList.size());
        ui.printMessage(displayTask);
    }

    private void addDeadline(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<String> inputArray = Arrays.asList(userInput.split(" /by "));
        String by = inputArray.get(1);
        ArrayList<String> descriptionArray = new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" ")));
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Deadline newDeadline = new Deadline(description, by);
        taskList.addTask(newDeadline);
        storage.saveData(taskList);
        displayAddedTask(newDeadline, taskList, ui);

    }

    private void addEvent(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<String> inputArray = Arrays.asList(userInput.split(" /at "));
        String timeFrame = inputArray.get(1);
        ArrayList<String> descriptionArray = new ArrayList<String>(Arrays.asList(inputArray.get(0).split(" ")));
        descriptionArray.remove(0);
        String description = String.join(" ", descriptionArray);
        Event newEvent = new Event(description, timeFrame);
        taskList.addTask(newEvent);
        storage.saveData(taskList);
        displayAddedTask(newEvent, taskList, ui);

    }

    private void addTodo(String userInput, TaskList taskList, Ui ui, Storage storage) throws DukeException {
        List<String>  inputArray = Arrays.asList(userInput.split(" "));

        if (inputArray.size() <= 1) {
            throw new DukeException("todo");
        }

        ArrayList<String> descriptionArray =  new ArrayList<String>(inputArray);
        descriptionArray.remove(0);
        String description = String.join(" ",descriptionArray);
        Todo newTodo = new Todo(description);
        taskList.addTask(newTodo);
        storage.saveData(taskList);
        displayAddedTask(newTodo, taskList, ui);
    }

}
