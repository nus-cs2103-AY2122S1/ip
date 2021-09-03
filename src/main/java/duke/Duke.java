package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.data.Storage;
import duke.data.TaskList;
import duke.io.Command;
import duke.io.Parser;
import duke.io.OutputFormatter;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Main program managing all main functions.
 */
public class Duke {
    private final Storage storage;
    private TaskList taskList;
//    private final Ui ui;

    /**
     * Constructs the main Duke object to run the program.
     *
     * @param filePath The path of the save file to be used/created
     */
    public Duke(String filePath) {
//        ui = new Ui();
        storage = new Storage(filePath);

        try {
            taskList = storage.loadTaskData();
        } catch (IOException | DukeException e) {
//            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Constructs the main Duke object to run the program with a hardcoded default filepath.
     */
    public Duke() {
//        this.ui = new Ui();
        this.storage = new Storage("data/Duke.txt");

        try {
            taskList = storage.loadTaskData();
        } catch (IOException | DukeException e) {
//            ui.showLoadingError();
            taskList = new TaskList();
        }
    }

    /**
     * Runs the main program loop.
     * Manages the main loop, handling all user input and saving data/etc.
     */
    public void run() {
        // Deprecated
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
        while (true) {
            try {
                Command command = Parser.parse(userInput);
                String description;
                String keyword;
                LocalDate dateTime;
                int index;

                switch (command.getCommand()) {
                    case BYE:
                        return "Bye. Hope to see you again soon!";
                    case LIST:
                        return OutputFormatter.formatTaskList(taskList);
                    case DONE:
                        // -1 to account for zero-indexing
                        index = Integer.parseInt(command.getArgs()[0]) - 1;
                        taskList.get(index).markAsDone();

                        // update file
                        storage.saveTaskData(taskList);

                        return "Nice! I've marked this task as done:\n  " + taskList.get(index);
                    case DELETE:
                        // -1 to account for zero-indexing
                        Task removedTask = taskList.remove(Integer.parseInt(command.getArgs()[0]) - 1);

                        // update file
                        storage.saveTaskData(taskList);

                        return "Noted. I've removed this task:\n  " + removedTask + "\nNow you have "
                                + taskList.size() + " tasks in the list.";
                    case TODO:
                        Todo newTodo = new Todo(userInput.substring(5).trim());
                        this.taskList.add(newTodo);

                        // update file
                        storage.saveTaskData(taskList);

                        return "Got it. I've added this task:\n  "
                                + newTodo + "\nNow you have " + this.taskList.size()
                                + " tasks in the list.";
                    case DEADLINE:
                        description = command.getArgs()[0];
                        dateTime = LocalDate.parse(command.getArgs()[1]);
                        Deadline newDeadline = new Deadline(description, dateTime);
                        taskList.add(newDeadline);

                        // update file
                        storage.saveTaskData(taskList);

                        return "Got it. I've added this task:\n  " + newDeadline + "\nNow you have "
                                + taskList.size() + " tasks in the list.";
                    case EVENT:
                        description = command.getArgs()[0];
                        dateTime = LocalDate.parse(command.getArgs()[1]);
                        Event newEvent = new Event(description, dateTime);
                        taskList.add(newEvent);

                        // update file
                        storage.saveTaskData(taskList);

                        return "Got it. I've added this task:\n  "
                                + newEvent + "\nNow you have " + taskList.size()
                                + " tasks in the list.";
                    case DATE:
                        LocalDate queryDate = LocalDate.parse(userInput.substring(5));
                        TaskList dueTasks = taskList.filterByDate(queryDate);

                        return OutputFormatter.formatTaskList(dueTasks, queryDate);
                    case FIND:
                        // -1 to account for zero-indexing
                        keyword = command.getArgs()[0];

                        return OutputFormatter.formatTaskList(taskList.containsKeyword(keyword));
                    default:
                        break;
                }
            } catch (DukeException | IOException e) {
                return e.getMessage();
            } catch (DateTimeParseException e) {
                return "Unknown date format. Please input a valid date in the format: YYYY-MM-DD";
            }
        }
    }
}
