import java.util.Scanner;

public class Duke {

    public Duke() {

    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetings =
                "-----------------------------------------\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "-----------------------------------------\n";
        System.out.println("Hello from\n" + logo + greetings);
    }

    public void echo(String action) {
        System.out.println("-----------------------------------------\n" +
                String.format("%s\n", action) +
                "-----------------------------------------\n");
    }

    public void exit() {
        String exitMessage =
                "-----------------------------------------\n" +
                "Bye. Hope to see you again soon!\n" +
                "-----------------------------------------\n";
        System.out.println(exitMessage);
        System.exit(0);
    }

    public void run() {
        this.greet();
        Scanner sc = new Scanner(System.in);
        String action;
        while (sc.hasNextLine()) {
            action = sc.nextLine();
            if ((action.equals("bye"))) {
                this.exit();
            } else {
                this.echo(action);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
