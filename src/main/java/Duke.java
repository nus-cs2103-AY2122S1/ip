import java.sql.SQLOutput;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\r\nWhat can I do for you?");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.next(); // Can also convert result to lower-case to handle cases.
            switch(command) {
                case "list":
                case "blah":
                    System.out.println(command);
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                default:
                    System.out.println("Command don't exist. To exit, type the command 'bye'");
            }
            if(command.equals("bye")) {
                break;
            }
        }



    }
}
