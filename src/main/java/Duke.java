import java.io.IOException;

import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

public class Duke {
    private Scanner sc;
    private TaskList taskList;
    Storage storage = new Storage("data/duke.txt");

    public Duke() {
        sc = new Scanner(System.in);
        taskList = new TaskList();
    }

    public void informTaskAdded(Task task) {
        this.echo("I've added the following task:\n"
                + "    " + task + "\n"
                + "You have " + this.taskList.size() + " task(s) in the list.\n");
    }

    public void addTodo(String description) {
        Task task = new Todo(description);
        this.taskList.addTask(task);
        this.informTaskAdded(task);
        this.saveData();
    }

    public void addDeadline(String description, String by) throws DateTimeParseException {
        Task task = new Deadline(description, by);
        this.taskList.addTask(task);
        this.informTaskAdded(task);
        this.saveData();
    }

    public void addEvent(String description, String at) throws DateTimeParseException {
        Task task = new Event(description, at);
        this.taskList.addTask(task);
        this.informTaskAdded(task);
        this.saveData();
    }

    public void printList() {
        this.echo("Your current task(s):");
        System.out.println(taskList);
    }

    public void markDone(int index) {
        this.echo("Marked task " + index + " to done.");

        // Converting 1-based to 0-based
        this.taskList.markDone(index - 1);
        this.saveData();
    }

    public void deleteTask(int index) {
        // Converting 1-based to 0-based
        Task deletedTask = this.taskList.getTask(index - 1);
        this.taskList.deleteTask(index - 1);

        this.echo("Deleted the following task successfully:\n"
                + "    " + deletedTask + "\n"
                + "You have " + this.taskList.size() + " task(s) in the list.\n");

        this.saveData();
    }

    public void echo(String s) {
        System.out.println("Duke: " + s);
    }

    public String getResponse() {
        return sc.nextLine();
    }

    public void loadData() {
        this.echo("Retrieving data...");
        try {
            List<String> data = storage.load();
            taskList = TaskList.deserialize(data);

            this.echo("Data retrieved");
        } catch (IllegalArgumentException | DateTimeParseException e) {
            // Data stored in incorrect format
            this.echo("Unable to retrieve data. Data stored in invalid format");
        } catch (IOException e) {
            // Do nothing
            this.echo("No stored data found");
        }
    }

    public void saveData() {
        try {
            storage.store(taskList.serialize());
        } catch (IOException e) {
            // Do nothing
            this.echo("Unable to save data");
        }
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void start() {
        System.out.println("Duke: What can I help you with?");

        String response = "";

        while (true) {
            response = this.getResponse();

            try {
                if (response.equals("list")) {
                    this.printList();
                } else if (response.equals("bye")) {
                    break;
                } else if (response.startsWith("done ")) {
                    int index = Integer.parseInt(response.substring(5));
                    this.markDone(index);
                } else if (response.startsWith("delete ")) {
                    int index = Integer.parseInt(response.substring(7));
                    this.deleteTask(index);
                } else if (response.startsWith("todo ")) {
                    String description = response.substring(5);
                    this.addTodo(description);
                } else if (response.startsWith("deadline ")) {
                    if (!response.contains(" /by ")) {
                        throw new DukeException("Invalid format for `deadline` command. '/by' keyword is needed");
                    }
                    String[] params = response.substring(9).split(" /by ");
                    String description = params[0];
                    String by = params[1];
                    this.addDeadline(description, by);
                } else if (response.startsWith("event ")) {
                    if (!response.contains(" /at ")) {
                        throw new DukeException("Invalid format for `event` command. '/at' keyword is needed");
                    }
                    String[] params = response.substring(6).split(" /at ");
                    String description = params[0];
                    String at = params[1];
                    this.addEvent(description, at);
                } else {
                    throw new DukeException("Invalid command: " + response);
                }
            } catch (DukeException e) {
                this.echo(e.getMessage() + "\n");
            } catch (DateTimeParseException e) {
                this.echo("Invalid date format (must be in yyyy-mm-dd). Unable to add task.");
            }
        }
    }

    public void exit() {
        System.out.println("Duke: Good bye");
        System.out.println("Shutting down Duke...");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.greet();
        duke.loadData();
        duke.start();
        duke.exit();
    }
}
