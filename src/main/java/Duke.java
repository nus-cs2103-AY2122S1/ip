import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */
        String indent = "    ";
        String greeting = indent + "Hello! I'm Duke\n"
                + indent+ "What can I do for you?";
        String exitMessage = indent + "Bye. Hope to see you again soon!";

        StringBuilder builder = new StringBuilder(100);
        Stream.generate(() -> '-').limit(60).forEach(e -> builder.append(e));
        String line = indent + '+' + builder.toString() + "+\n";

        // Greeting the user
        System.out.println(line);
        System.out.println(greeting);
        System.out.println(line);

        // Taking in commands
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            System.out.println(line);
            if (command.equals("bye")) {
                System.out.println(exitMessage);
                System.out.println(line);
                break;
            } else {
                // Echo the command
                System.out.println(indent + command);
            }
            System.out.println(line);
        }

    }
}
