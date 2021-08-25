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
     * @return Boolean value indicating if program should continue.
     */
    public boolean process(String input) {
        boolean end = false;
        String[] splitMessage = input.split(" ", 2);
        String command = splitMessage[0];

        String description;
        String dateTime;
        switch(command) {
        case("bye"): // Print exit message, exit the program
            ui.showGoodBye();
            end = true;
            break;
        case("list"): // List the current tasks and their status
            ArrayList<String> tasks = taskList.getTaskList();
            ui.showList(tasks);
            break;
        case("done"): // Mark a task as done and display the task
            try {
                int taskNumber = Integer.parseInt(splitMessage[1]);
                String taskMessage = taskList.changeTaskStatus(taskNumber);
                ui.writeOutput("Alright, I have marked the task as done:\n" + taskMessage);
                storage.rewriteFile(taskList.getList());
            } catch (IndexOutOfBoundsException e) {
                ui.showInputError("index");
            } catch (NumberFormatException n) {
                ui.showInputError("done");
            } catch (DukeException d) {
                ui.showDukeException(d);
            }
            break;
        case("delete"): // Delete the task indicated
            try {
                int taskNumber = Integer.parseInt(splitMessage[1]);
                String taskMessage = taskList.deleteTask(taskNumber);
                ui.writeOutput("Alright, I have removed the following task:\n" + taskMessage);
                storage.rewriteFile(taskList.getList());
            } catch (IndexOutOfBoundsException e) {
                ui.showInputError("index");
            } catch (NumberFormatException n) {
                ui.showInputError("delete");
            } catch (DukeException d) {
                ui.showDukeException(d);
            }
            break;
        case("todo"): // Create a ToDo task and display the task
            try {
                description = splitMessage[1];
                String saveString = taskList.addTask(description);
                storage.saveTask(saveString);
            } catch (IndexOutOfBoundsException e) {
                ui.showInputError("todo");
            }
            break;
        case("deadline"): // Create a Deadline task and display the task
            try {
                description = splitMessage[1].split("/by ")[0];
                dateTime = splitMessage[1].split("/by ")[1];
                LocalDate date = LocalDate.parse(dateTime);
                String saveString = taskList.addTask(description, date, command);
                storage.saveTask(saveString);
            } catch (IndexOutOfBoundsException e) {
                ui.showInputError("deadline");
            } catch (DateTimeParseException e) {
                ui.showInputError("dateformat");
            }
            break;
        case("event"): // Create an Event task and display the task
            try {
                description = splitMessage[1].split("/at ")[0];
                dateTime = splitMessage[1].split("/at ")[1];
                LocalDate date = LocalDate.parse(dateTime);
                String saveString = taskList.addTask(description, date, command);
                storage.saveTask(saveString);
            } catch (IndexOutOfBoundsException e) {
                ui.showInputError("event");
            } catch (DateTimeParseException e) {
                ui.showInputError("dateformat");
            }
            break;
        case("find"): // Find all tasks corresponding to the given keyword
            if (splitMessage.length == 2) { // If there is a keyword given
                ArrayList<String> results = taskList.findTask(splitMessage[1]);
                ui.showList(results);
            } else { // If no keyword given
                ui.showInputError("find");
            }
            break;
        default: // If input does not have a recognised command
            ui.showInputError("invalid");
            break;
        }

        return end;
    }
}
