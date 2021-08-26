package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * A class representing the duke application.
 */
public class Duke {
    private static final String GREETING_MSG = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MSG = "Bye. Hope to see you again soon!";
    private static final String LIST_TASK_MSG = "Here are the tasks in your list: ";
    private static final String FIND_TASK_MSG = "Here are the matching tasks in your list: ";
    private static final String ADD_TASK_MSG_TEMPLATE = "Got it. I've added this task: \n"
                                                        + "  %s\n"
                                                        + "Now you have %d %s in the list.\n";
    private static final String DELETE_TASK_MSG_TEMPLATE = "Noted. I've removed this task: \n"
                                                        + "  %s\n"
                                                        + "Now you have %d %s in the list.\n";
    private static final String MARK_DONE_MSG_TEMPLATE = "Nice! I've marked this task as done: \n  %s\n";
    private static final String UNEXPECTED_ERROR_MSG = "Something went wrong";
    private static final DateTimeParser dateTimeParser = new DateTimeParser(
            new String[] {"yyyy-MM-dd", "d/M/yyyy"});
    
    private final Scanner scanner;
    private final Storage taskStorage;
    private final TaskList tasks;
    private final Ui ui;
    private final CommandParser commandParser;
    
    private Duke(String path) throws IOException {
        scanner = new Scanner(System.in);
        taskStorage = new Storage(path);
        tasks = new TaskList(taskStorage.load());
        ui = new Ui();
        commandParser = new CommandParser();
    }
    
    private void greet() {
        ui.show(GREETING_MSG);
    }

    private void exit() {
        ui.show(GOODBYE_MSG);
    }


    private void addTodo(Todo taskToAdd) throws IOException {
        ui.showf(
                ADD_TASK_MSG_TEMPLATE,
                tasks.addTask(taskToAdd), 
                tasks.getTaskCount(), 
                tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
    }

    private void addDeadline(Deadline taskToAdd) throws IOException {
        ui.showf(
                ADD_TASK_MSG_TEMPLATE,
                tasks.addTask(taskToAdd),
                tasks.getTaskCount(),
                tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
    }

    private void addEvent(Event taskToAdd) throws IOException {
        ui.showf(
                ADD_TASK_MSG_TEMPLATE,
                tasks.addTask(taskToAdd),
                tasks.getTaskCount(),
                tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
    }

    private void listTasks() {
        ui.show(LIST_TASK_MSG);
        ui.showf(tasks.toString());
    }

    private void doneTask(int index) throws IOException {
        tasks.getTask(index).markAsDone();
        ui.showf(MARK_DONE_MSG_TEMPLATE, tasks.getTask(index));
        taskStorage.backup(tasks);
    }

    private void deleteTask(int index) throws IOException {
        ui.showf(
                DELETE_TASK_MSG_TEMPLATE,
                tasks.deleteTask(index),
                tasks.getTaskCount(),
                tasks.getTaskCount() <= 1 ? "task" : "tasks"
        );
        taskStorage.backup(tasks);
    }
    
    private void findTask(String s) {
        ui.show(FIND_TASK_MSG);
        ui.showf(tasks.findTask(s).toString());
    }

    private void runCommand(String[] cmd) throws DukeException, IOException {
        LocalDate temp;
        switch (cmd[0]) {
        case "todo":
            addTodo(new Todo(cmd[1]));
            break;
        case "done":
            doneTask(Integer.parseInt(cmd[1]));
            break;
        case "delete":
            deleteTask(Integer.parseInt(cmd[1]));
            break;
        case "deadline":
            temp = dateTimeParser.parse(cmd[2]);
            if (temp == null) {
                addDeadline(new Deadline(cmd[1], cmd[2]));
            } else {
                addDeadline(new Deadline(cmd[1], temp));
            }
            break;
        case "event":
            temp = dateTimeParser.parse(cmd[2]);
            if (temp == null) {
                addEvent(new Event(cmd[1], cmd[2]));
            } else {
                addEvent(new Event(cmd[1], temp));
            }
            break;
        case "list":
            listTasks();
            break;
        case "find":
            findTask(cmd[1]);
            break;
        }
    }


    /**
     * Starts the duke application.
     * 
     * @param args Ignored arguments.
     */
    public static void main(String[] args) {
        try {
            Duke duke = new Duke("data/duke.txt");
            duke.greet();
            String[] cmd = new String[] { "" };
            do {
                try {
                    cmd = duke.commandParser.parse(duke.scanner.nextLine());
                    duke.runCommand(cmd);
                } catch (DukeException e) {
                    duke.ui.show(e.getMessage());
                }
            } while (!cmd[0].equals("bye"));
            duke.exit();
        } catch (IOException e) {
            // Because it's not a duke error, hence not using ui to show
            System.out.println(UNEXPECTED_ERROR_MSG);
        }
    }
}
