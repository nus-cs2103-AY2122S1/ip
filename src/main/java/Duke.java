import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private ArrayList<String> taskList;

    public Duke() {
        taskList = new ArrayList<>();
    }

    public void addTodo(String task) {
        this.taskList.add(task);
        this.echo("Added -- " + task + " -- to task list.");
    }

    public void printList() {
        this.echo("Your current task(s):");

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i));
        }
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
