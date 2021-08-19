import task.*;

import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private TaskList taskList;

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
    }

    public void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        this.taskList.addTask(task);
        this.informTaskAdded(task);
    }

    public void addEvent(String description, String at) {
        Task task = new Event(description, at);
        this.taskList.addTask(task);
        this.informTaskAdded(task);
    }

    public void printList() {
        this.echo("Your current task(s):");
        System.out.println(taskList);
    }

    public void markDone(int index) {
        this.echo("Marked task " + index + " to done.");

        // Converting 1-based to 0-based
        this.taskList.markDone(index - 1);
    }

    public void echo(String s) {
        System.out.println("Duke: " + s);
    }

    public String getResponse() {
        return sc.nextLine();
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

            if (response.equals("list")) {
                this.printList();
            } else if (response.equals("bye")) {
                break;
            } else if (response.startsWith("done ")) {
                int index = Integer.parseInt(response.substring(5));
                this.markDone(index);
            } else if (response.startsWith("todo ")) {
                String description = response.substring(5);
                this.addTodo(description);
            } else if (response.startsWith("deadline ")) {
                String[] params = response.substring(9).split(" /by ");
                String description = params[0];
                String by = params[1];
                this.addDeadline(description, by);
            } else if (response.startsWith("event ")) {
                String[] params = response.substring(6).split(" /at ");
                String description = params[0];
                String at = params[1];
                this.addEvent(description, at);
            } else {
                this.addTodo(response);
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
        duke.start();
        duke.exit();
    }
}
