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
        String[] messages = input.split(" ", 2);
        String command = messages[0];

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
            output = markTaskAsDone(messages);
            break;
        case("delete"): // Delete the task indicated
            output = deleteTask(messages);
            break;
        case("todo"): // Create a ToDo task and display the task
            output = addToDo(messages);
            break;
        case("deadline"): // Create a Deadline task and display the task
            output = addDeadline(messages);
            break;
        case("event"): // Create an Event task and display the task
            output = addEvent(messages);
            break;
        case("find"): // Find all tasks corresponding to the given keyword
            output = findTasks(messages);
            break;
        default: // If input does not have a recognised command
            output = ui.showInputError("invalid");
            break;
        }

        return output;
    }

    /**
     * Processes the String array given and marks the task with index
     * corresponding to index 1 of the String array as done.
     *
     * @param messages String array containing the input from user.
     * @return String with the updated task or an error message.
     */
    private String markTaskAsDone(String[] messages) {
        String output;

        try {
            int taskNumber = Integer.parseInt(messages[1]);
            String taskMessage = taskList.markAsDone(taskNumber);
            output = ui.writeOutput("Alright, I have marked the task as done:\n" + taskMessage);
            storage.rewriteFile(taskList.getList());
        } catch (IndexOutOfBoundsException e) {
            output = ui.showInputError("index");
        } catch (NumberFormatException n) {
            output = ui.showInputError("done");
        } catch (DukeException d) {
            output = ui.showDukeException(d);
        }

        return output;
    }

    /**
     * Processes the String array given and deletes the task with index
     * corresponding to index 1 of the String array.
     *
     * @param messages String array containing the input from user.
     * @return String with the deleted task or an error message.
     */
    private String deleteTask(String[] messages) {
        String output;

        try {
            int taskNumber = Integer.parseInt(messages[1]);
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

        return output;
    }

    /**
     * Processes the String array given and adds a ToDo task with the description
     * given in index 1 of the array.
     *
     * @param messages String array containing the input from user.
     * @return String with the added task or an error message.
     */
    private String addToDo(String[] messages) {
        String output;
        try {
            String description = messages[1];
            Task task = taskList.addToDo(description);
            storage.saveTask(task.saveString());
            output = ui.writeOutput("Task added successfully: \n" + task
                    + "\nNumber of tasks in list: " + taskList.getList().size());
        } catch (IndexOutOfBoundsException e) {
            output = ui.showInputError("todo");
        }

        return output;
    }

    /**
     * Processes the String array and adds a Deadline task with the description
     * and date given in index 1 of the array.
     *
     * @param messages String array containing the input from user.
     * @return String with the added task or an error message.
     */
    private String addDeadline(String[] messages) {
        String output;

        try {
            String description = messages[1].split("/by ")[0];
            String dateTime = messages[1].split("/by ")[1];
            LocalDate date = LocalDate.parse(dateTime);
            Task task = taskList.addDeadline(description, date);
            storage.saveTask(task.saveString());
            output = ui.writeOutput("Task added successfully: \n" + task
                    + "\nNumber of tasks in list: " + taskList.getList().size());
        } catch (IndexOutOfBoundsException e) {
            output = ui.showInputError("deadline");
        } catch (DateTimeParseException e) {
            output = ui.showInputError("dateformat");
        }

        return output;
    }

    /**
     * Processes the String array and adds an Event task with the description
     * and date given in index 1 of the array.
     *
     * @param messages String array containing the input from user.
     * @return String with the added task or an error message.
     */
    private String addEvent(String[] messages) {
        String output;

        try {
            String description = messages[1].split("/at ")[0];
            String dateTime = messages[1].split("/at ")[1];
            LocalDate date = LocalDate.parse(dateTime);
            Task task = taskList.addEvent(description, date);
            storage.saveTask(task.saveString());
            output = ui.writeOutput("Task added successfully: \n" + task
                    + "\nNumber of tasks in list: " + taskList.getList().size());
        } catch (IndexOutOfBoundsException e) {
            output = ui.showInputError("event");
        } catch (DateTimeParseException e) {
            output = ui.showInputError("dateformat");
        }

        return output;
    }

    /**
     * Processes the String array and finds all tasks that matches the keyword given.
     *
     * @param messages String array containing the input from user.
     * @return String with the added task or an error message.
     */
    private String findTasks(String[] messages) {
        String output;

        if (messages.length == 2) { // If there is a keyword given
            ArrayList<String> results = taskList.findTask(messages[1]);
            output = ui.showList(results);
        } else {
            output = ui.showInputError("find");
        }

        return output;
    }


}
