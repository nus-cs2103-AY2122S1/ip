package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.application.Application;

/**
 * A class representing the duke application.
 */
public class Duke {
    private static final String GREETING_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private static final String ADD_TASK_MSG_TEMPLATE = "Got it. I've added this task: \n"
                                                        + "  %s\n"
                                                        + "Now you have %d %s in the list.\n";
    private static final String DELETE_TASK_MSG_TEMPLATE = "Noted. I've removed this task: \n"
                                                        + "  %s\n"
                                                        + "Now you have %d %s in the list.\n";
    private static final String MARK_DONE_MSG_TEMPLATE = "Nice! I've marked this task as done: \n  %s\n";
    private static final String UNEXPECTED_ERROR_MSG = "Something went wrong";
    private static final String LIST_TASK_TEMPLATE = "Here are the tasks in your list: \n%s";
    private static final String FIND_TASK_MSG = "Here are the matching tasks in your list: \n%s";

    private static final DateTimeParser dateTimeParser = new DateTimeParser(
        new String[] {"yyyy-MM-dd", "d/M/yyyy"});
    private final Scanner scanner;
    private final Storage taskStorage;
    private final TaskList tasks;
    private final CommandParser commandParser;

    /**
     * A constructor for Duke.
     *
     * @throws IOException If unexpected IO errors occur when initializing storage.
     */
    public Duke() throws IOException {
        this("data/duke.txt");
    }

    private Duke(String path) throws IOException {
        scanner = new Scanner(System.in);
        taskStorage = new Storage(path);
        tasks = new TaskList(taskStorage.load());
        commandParser = new CommandParser();
    }

    public String greet() {
        return GREETING_MSG;
    }

    public String exit() {
        return GOODBYE_MSG;
    }


    private String addTodo(Todo taskToAdd) throws IOException, DukeException {
        String res = String.format(
            ADD_TASK_MSG_TEMPLATE,
            tasks.addTask(taskToAdd),
            tasks.getTaskCount(),
            tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
        return res;
    }

    private String addDeadline(Deadline taskToAdd) throws IOException, DukeException {
        String res = String.format(
            ADD_TASK_MSG_TEMPLATE,
            tasks.addTask(taskToAdd),
            tasks.getTaskCount(),
            tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
        return res;
    }

    private String addEvent(Event taskToAdd) throws IOException, DukeException {
        String res = String.format(
            ADD_TASK_MSG_TEMPLATE,
            tasks.addTask(taskToAdd),
            tasks.getTaskCount(),
            tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
        return res;
    }

    private String listTasks() {
        return String.format(LIST_TASK_TEMPLATE, tasks.toString());
    }

    private String doneTask(int index) throws IOException, DukeException {
        tasks.getTask(index).markAsDone();
        String res = String.format(MARK_DONE_MSG_TEMPLATE, tasks.getTask(index));
        taskStorage.backup(tasks);
        return res;
    }

    private String deleteTask(int index) throws IOException, DukeException {
        String res = String.format(
            DELETE_TASK_MSG_TEMPLATE,
            tasks.deleteTask(index),
            tasks.getTaskCount(),
            tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
        return res;
    }

    private String findTask(String s) {
        return String.format(FIND_TASK_MSG, tasks.findTask(s).toString());
    }

    private String runCommand(String[] cmd) throws DukeException, IOException {
        LocalDate temp;
        switch (cmd[0]) {
        case "todo":
            return addTodo(new Todo(cmd[1]));
        case "done":
            return doneTask(Integer.parseInt(cmd[1]));
        case "delete":
            return deleteTask(Integer.parseInt(cmd[1]));
        case "deadline":
            temp = dateTimeParser.parse(cmd[2]);
            if (temp == null) {
                return addDeadline(new Deadline(cmd[1], cmd[2]));
            } else {
                return addDeadline(new Deadline(cmd[1], temp));
            }
        case "event":
            temp = dateTimeParser.parse(cmd[2]);
            if (temp == null) {
                return addEvent(new Event(cmd[1], cmd[2]));
            } else {
                return addEvent(new Event(cmd[1], temp));
            }
        case "list":
            return listTasks();
        case "find":
            return findTask(cmd[1]);
        case "bye":
            return null;
        default:
            return "";
        }
    }

    /**
     * Return the response of duke after receiving the input.
     *
     * @param input The input to be parsed into a command and executed.
     * @return The response after executing the command.
     */
    public String getResponse(String input) {
        try {
            String[] cmd = commandParser.parse(input);
            return runCommand(cmd);
        } catch (DukeException e) {
            return e.getMessage();
        } catch (IOException e) {
            return UNEXPECTED_ERROR_MSG;
        }
    }

    /**
     * Launch the Duke application.
     *
     * @param args Ignored parameters.
     */
    public static void main(String[] args) {
        Application.launch(Ui.class, args);
    }
}
