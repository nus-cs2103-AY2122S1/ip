import java.util.Scanner;

public class Duke {
    private TaskList taskList;

    public Duke() {
        taskList = new TaskList();
    }

    public void addTodo(String description) {
        Task task = new Task(description);
        this.taskList.addTask(task);
        this.echo("Added -- " + description + " -- to task list.");
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
        Scanner sc = new Scanner(System.in);
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
