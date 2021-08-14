import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Stores user inputted tasks
        ArrayList<String> tasks = new ArrayList<>();

        // Print welcome text
        String line = "--------------------------------\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + logo + "\nHello! I'm Duke :)\nWhat can I do for you?\n" + line);

        // Handle user input
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            switch(input) {
                case "bye":
                    System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                    System.exit(0);
                    break;
                case "list":
                    System.out.print(line);
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d: %s%n", i + 1, tasks.get(i));
                    }
                    System.out.println(line);
                    break;
                default:
                    tasks.add(input);
                    System.out.println(line + String.format("added: %s\n", input) + line);
            }
        }
    }
}
