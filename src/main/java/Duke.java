import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";
        String format = "\t%s\n";
        String horizontalLine = "______________________________________________________";
        List<String> tasks = new ArrayList<>();

        System.out.print(logo);
        System.out.printf(format, horizontalLine);
        System.out.printf(format, "Hello there, I'm Duke!");
        System.out.printf(format, "What can I do for you today?");
        System.out.printf(format, horizontalLine);

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                // Exit chat bot
                System.out.printf(format, horizontalLine);
                System.out.printf(format, "Goodbye. Have a nice day!");
                System.out.printf(format, horizontalLine);
                break;
            } else if (input.equals("list")) {
                // List all added tasks
                System.out.printf(format, horizontalLine);
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("\t%d. %s\n", i + 1, tasks.get(i));
                }
                System.out.printf(format, horizontalLine);
            } else {
                // Add task
                tasks.add(input);
                System.out.printf(format, horizontalLine);
                System.out.printf("\tadded: %s\n", input);
                System.out.printf(format, horizontalLine);
            }
        }
    }
}
