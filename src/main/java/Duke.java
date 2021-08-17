import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (respond(input)) break;
        }
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public static boolean respond(String command) {
        if (! command.equals("bye")) {
            System.out.println(command);
            return false;
        } else {
            System.out.println("Bye. Hope to see you again soon!");
            return true;
        }
    }
}
