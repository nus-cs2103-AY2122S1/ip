import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private boolean active;
    private List<String> list;

    public Duke() {
        this.active = true;
        this.list = new ArrayList<String>();
    }

    public void executeCommand(String command) {
        switch (command) {
            case "bye":
                this.terminate();
                break;
            case "list":
                this.listItems();
                break;
            default:
                this.addItem(command);
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
        System.out.println("____________________________________________________________\n");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(String.format("%d: %s", i + 1, this.list.get(i)));
        }
        System.out.println("____________________________________________________________\n");
    }

    public void addItem(String input) {
        this.list.add(input);
        String message =
                "____________________________________________________________\n" +
                " added: " + input + "\n" +
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

