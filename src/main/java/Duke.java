import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {

        Duke duke = new Duke();
        duke.greeting();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.next();
            if (command.equals("bye")) {
                duke.bye();
                break;
            } else {
                duke.echo(command);
            }
        }

    }

    public void greeting() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void echo(String command) {
        System.out.println(command);
    }
}
