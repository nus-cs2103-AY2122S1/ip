import java.util.NoSuchElementException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        String greet = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String output = "";

            if (input.equals("list")) {
                output = "list";
            }
            else if (input.equals("blah")) {
                output = "blah";
            }
            else if (input.equals("bye")) {
                break;
            }

            System.out.println(output);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
