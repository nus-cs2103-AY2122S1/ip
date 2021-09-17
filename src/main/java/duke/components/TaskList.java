package duke.components;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents a Task List that contains all tasks.
 */
public class TaskList {
    private ArrayList<Task> inputs;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Creates a TaskList object.
     *
     * @param storage The Storage of Duke.
     * @param ui The UI of Duke.
     * @param parser The Parser of Duke.
     */
    public TaskList(Storage storage, Ui ui, Parser parser) {
        inputs = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
        this.parser = parser;
    }

    /**
     * Gets the length of the task list.
     *
     * @return The length of the task list.
     */
    public int getSize() {
        return this.inputs.size();
    }

    /**
     * Adds a task read from data file to task list.
     *
     * @param task A task read from data file.
     */
    public void addTaskFromDataFile(Task task) {
        inputs.add(task);
    }

    /**
     * Takes in user input, parse it and create a task.
     * Adds it into task list and writes the updated list to data file.
     *
     * @param input User input used to create a task.
     */
    public void addTaskFromInput(String input) {

        String[] group = input.split(" ");

        // Case Todo
        if (parser.isTodo(group[0]) && group.length > 1) {
            String todoToAdd = input.substring(5); // name of the task is after "todo" and space
            Task newTodo = new Todo(todoToAdd);
            inputs.add(newTodo);

            storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());

            for (int i = 1; i < inputs.size(); i++) {
                storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
            }

            ui.displayAddTaskMessage(newTodo);
            ui.displayTaskNumber(this);
            ui.displayLinebreak();
        }
        if (parser.isTodo(group[0]) && group.length == 1) {
            ui.displayTodoEmptyMessage();
            ui.displayLinebreak();
        }

        // Case Deadline
        if (parser.isDeadline(group[0]) && input.contains(" /by ")) {
            String[] ddlGroup = input.split(" /by ");
            String ddlToAdd = ddlGroup[0].substring(9); // name of the task is after "deadline" and space
            String ddlByTime = ddlGroup[1];
            LocalDateTime dateTime = LocalDateTime.parse(ddlByTime, parser.getFormatter());
            Task newDeadline = new Deadline(ddlToAdd, dateTime);
            inputs.add(newDeadline);

            storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());

            for (int i = 1; i < inputs.size(); i++) {
                storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
            }

            ui.displayAddTaskMessage(newDeadline);
            ui.displayTaskNumber(this);
            ui.displayLinebreak();
        }

        // Case Event
        if (parser.isEvent(group[0]) && input.contains(" /at ")) {
            String[] eveGroup = input.split(" /at ");
            String eveToAdd = eveGroup[0].substring(6); // name of the task is after "event" and space
            String eveAtTime = eveGroup[1];
            LocalDateTime dateTime = LocalDateTime.parse(eveAtTime, parser.getFormatter());
            Task newEvent = new Event(eveToAdd, dateTime);
            inputs.add(newEvent);

            storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());

            for (int i = 1; i < inputs.size(); i++) {
                storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
            }

            ui.displayAddTaskMessage(newEvent);
            ui.displayTaskNumber(this);
            ui.displayLinebreak();
        }
    }

    /**
     * Deletes a task based on user input.
     *
     * @param input User input used to delete a task.
     */
    public void deleteTask(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if (parser.isDelete(parts[0]) && index < inputs.size()) {
            System.out.println("Noted. I've removed this task: ");
            System.out.println(inputs.get(index).toString());
            inputs.remove(index);

            if (inputs.size() == 0) {
                storage.eraseFileContent();
            } else {
                storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());
                for (int i = 1; i < inputs.size(); i++) {
                    storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
                }
            }

            ui.displayLinebreak();
        }
    }

    /**
     * Mark a task as done.
     *
     * @param input User input used to mark a task as done.
     */
    public void markDone(String input) {
        String[] parts = input.split(" ");
        int index = Integer.parseInt(parts[1]) - 1;
        if (parser.isDone(parts[0]) && index < inputs.size()) {
            inputs.get(index).setDone();

            storage.writeTaskToFile(inputs.get(0).toString() + System.lineSeparator());
            for (int i = 1; i < inputs.size(); i++) {
                storage.appendTaskToFile(inputs.get(i).toString() + System.lineSeparator());
            }

            ui.displayMarkDoneMessage(inputs.get(index).toString());
            ui.displayLinebreak();
        }
    }

    /**
     * Display all tasks in task list.
     */
    public void displayAllTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < inputs.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + inputs.get(i).toString());
        }
        ui.displayLinebreak();
    }

    /**
     * Finds and displays all tasks matched with user input.
     *
     * @param input User input used to find matching tasks.
     */
    public void displayFindTasks(String input) {
        String toBeFound = input.substring(5);
        ArrayList<Task> tasksFound = new ArrayList<>();

        // find and add matching tasks to another list
        for (int i = 0; i < inputs.size(); i++) {
            Task element = inputs.get(i);
            if (element.toString().contains(toBeFound)) {
                tasksFound.add(element);
            }
        }

        System.out.println("Here are the matching tasks in your list: ");
        for (int i = 0; i < tasksFound.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + tasksFound.get(i).toString());
        }
        ui.displayLinebreak();
    }

}
