package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * The Parser class takes in the input string and performs the operations
 * based on the command found in the string.
 */
public class Parser {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for Parser.
     *
     * @param ui Ui instance for output.
     * @param storage Storage instance for saving task.
     * @param taskList TaskList instance for operations on tasks.
     */
    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Processes the input string and decide what operations to perform.
     *
     * @param input The input String.
     * @return Output string to be displayed to user.
     */
    public String process(String input) {
        String[] splitMessage = input.split(" ", 2);
        String command = splitMessage[0];

        String description;
        String dateTime;
        String output = "";

        switch(command) {
        case("bye"): // Print exit message, exit the program
            output = ui.showGoodBye();
            break;
        case("list"): // List the current tasks and their status
            ArrayList<String> tasks = taskList.getTaskList();
            output = ui.showList(tasks);
            break;
        case("done"): // Mark a task as done and display the task
            try {
                int taskNumber = Integer.parseInt(splitMessage[1]);
                String taskMessage = taskList.changeTaskStatus(taskNumber);
                output = ui.writeOutput("Alright, I have marked the task as done:\n" + taskMessage);
                storage.rewriteFile(taskList.getList());
            } catch (IndexOutOfBoundsException e) {
                output = ui.showInputError("index");
            } catch (NumberFormatException n) {
                output = ui.showInputError("done");
            } catch (DukeException d) {
                output = ui.showDukeException(d);
            }
            break;
        case("delete"): // Delete the task indicated
            try {
                int taskNumber = Integer.parseInt(splitMessage[1]);
                String taskMessage = taskList.deleteTask(taskNumber);
                output = ui.writeOutput("Alright, I have removed the following task:\n" + taskMessage);
                storage.rewriteFile(taskList.getList());
            } catch (IndexOutOfBoundsException e) {
                output = ui.showInputError("index");
            } catch (NumberFormatException n) {
                output = ui.showInputError("delete");
            } catch (DukeException d) {
                output = ui.showDukeException(d);
            }
            break;
        case("todo"): // Create a ToDo task and display the task
            try {
                description = splitMessage[1];
                Task task = taskList.addTask(description);
                storage.saveTask(task.saveString());
                output = ui.writeOutput("Task added successfully: \n" + task
                        + "\nNumber of tasks in list: " + taskList.getList().size());
            } catch (IndexOutOfBoundsException e) {
                output = ui.showInputError("todo");
            }
            break;
        case("deadline"): // Create a Deadline task and display the task
            try {
                description = splitMessage[1].split("/by ")[0];
                dateTime = splitMessage[1].split("/by ")[1];
                LocalDate date = LocalDate.parse(dateTime);
                Task task = taskList.addTask(description, date, command);
                storage.saveTask(task.saveString());
                output = ui.writeOutput("Task added successfully: \n" + task
                        + "\nNumber of tasks in list: " + taskList.getList().size());
            } catch (IndexOutOfBoundsException e) {
                output = ui.showInputError("deadline");
            } catch (DateTimeParseException e) {
                output = ui.showInputError("dateformat");
            }
            break;
        case("event"): // Create an Event task and display the task
            try {
                description = splitMessage[1].split("/at ")[0];
                dateTime = splitMessage[1].split("/at ")[1];
                LocalDate date = LocalDate.parse(dateTime);
                Task task = taskList.addTask(description, date, command);
                storage.saveTask(task.saveString());
                output = ui.writeOutput("Task added successfully: \n" + task
                        + "\nNumber of tasks in list: " + taskList.getList().size());
            } catch (IndexOutOfBoundsException e) {
                output = ui.showInputError("event");
            } catch (DateTimeParseException e) {
                output = ui.showInputError("dateformat");
            }
            break;
        case("find"): // Find all tasks corresponding to the given keyword
            if (splitMessage.length == 2) { // If there is a keyword given
                ArrayList<String> results = taskList.findTask(splitMessage[1]);
                output = ui.showList(results);
            } else { // If no keyword given
                output = ui.showInputError("find");
            }
            break;
        default: // If input does not have a recognised command
            output = ui.showInputError("invalid");
            break;
        }

        return output;
    }

}
