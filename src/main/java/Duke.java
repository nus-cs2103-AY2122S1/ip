import java.util.ArrayList;
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
        ArrayList<String> library = new ArrayList<>(100);

        while (true) {
            String input = scanner.nextLine();
            String output = "";

            if (input.equals("list")) {
                int count = 1;
                for (String book: library) {
                    output += String.format("%d. %s\n", count++, book);
                }
            }
            else if (input.equals("blah")) {
                output = "blah";
            }
            else if (input.equals("bye")) {
                break;
            }
            else {
                library.add(input);
                output = "added: " + input;
            }

            System.out.println(output);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
