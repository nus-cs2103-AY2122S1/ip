import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    private static String indent = "    ";

    private static void divider() {
        StringBuilder builder = new StringBuilder(100);
        Stream.generate(() -> '-').limit(60).forEach(e -> builder.append(e));
        String line = indent + '+' + builder.toString() + "+\n";
        System.out.println(line);
    }

    private static void greet() {
        String greeting = indent + "Hello! I'm Duke\n"
                + indent+ "What can I do for you?";

        divider();
        System.out.println(greeting);
        divider();
    }

    private static void exit() {
        String exitMessage = indent + "Bye. Hope to see you again soon!";

        divider();
        System.out.println(exitMessage);
        divider();
    }

    private static void echo(String command) {
        divider();
        System.out.println(indent + command);
        divider();
    }

    public static void main(String[] args) {
        // Greeting the user
        greet();

        // Taking in commands
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                exit();
                break;
            } else {
                // Echo the command
                echo(command);
            }
        }

    }
}
