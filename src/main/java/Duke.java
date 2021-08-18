import java.util.Scanner;

public class Duke {
    private boolean active;

    public Duke() {
        this.active = true;
    }

    public void executeCommand(String command) {
        switch (command) {
            case "bye":
                this.terminate();
                break;
            default:
                this.echo(command);
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

    public void echo(String input) {
        String message =
                "____________________________________________________________\n" +
                " " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(message);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String message =
                "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        System.out.println(message);
        while (duke.active) {
            String input = sc.nextLine();
            duke.executeCommand(input);
        }
    }
}

