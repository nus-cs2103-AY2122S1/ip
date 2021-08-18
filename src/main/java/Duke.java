import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private boolean active;
    private final List<Task> list;

    public Duke() {
        this.active = true;
        this.list = new ArrayList<Task>();
    }

    public void executeCommand(String input) {
        String[] parsedInput = input.split(" ");
        switch (parsedInput[0]) {
            case "bye":
                this.terminate();
                break;
            case "list":
                this.listItems();
                break;
            case "done":
                this.done(Integer.parseInt(parsedInput[1]) - 1);
                break;
            default:
                this.addItem(input);
        }
    }

    public void terminate() {
        this.active = false;
        String message =
                "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void listItems() {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            Task task = this.list.get(i);
            System.out.println(String.format(" %d:[%s] %s", i + 1, task.getMarker(), task.getTask()));
        }
        System.out.println("____________________________________________________________\n");
    }

    public void addItem(String input) {
        this.list.add(new Task(input));
        String message =
                "____________________________________________________________\n" +
                " added: " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void done(int index) {
        Task task = this.list.get(index);
        task.setCompleted();
        String message =
                "____________________________________________________________\n" +
                " Nice! I've marked this task as done: \n" +
                String.format("   [%s] %s\n", task.getMarker(), task.getTask()) +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void echo(String input) {
        String message =
                "____________________________________________________________\n" +
                " " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public void run(Scanner sc) {
        String message =
                "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        System.out.println(message);
        while (this.active) {
            String input = sc.nextLine();
            this.executeCommand(input);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        duke.run(sc);
    }
}

