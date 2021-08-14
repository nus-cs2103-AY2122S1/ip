import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Stores user inputted tasks
        ArrayList<Task> tasks = new ArrayList<>();

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
            String[] splitInput = input.split(" ");

            switch(splitInput[0]) {
                case "bye":
                    System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                    System.exit(0);
                    break;
                case "list":
                    System.out.println(line + "Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.printf("%d.%s%n", i + 1, tasks.get(i));
                    }
                    System.out.println(line);
                    break;
                case "done":
                    // Handle wrong number of arguments
                    if (splitInput.length != 2) {
                        System.out.println(line + "Invalid input!\n" + line);
                        break;
                    }

                    // Handle non-integer second argument
                    int taskIdx = -1;
                    try {
                        taskIdx = Integer.parseInt(splitInput[1]);
                    } catch (NumberFormatException e) {
                        System.out.println(line + "Invalid input!\n" + line);
                        break;
                    }

                    // Handle invalid index
                    if (taskIdx >= 1 && taskIdx <= tasks.size()) {
                        Task t = tasks.get(taskIdx - 1);
                        t.markAsDone();
                        System.out.println(line + String.format("Nice! I've marked this task as done:\n  %s\n", t) + line);
                    } else {
                        System.out.println(line + "Invalid index!\n" + line);
                        break;
                    }
                    break;
                default:
                    // Do nothing if user inputs an empty string, else add it to tasks ArrayList
                    if (!input.equals("")) {
                        tasks.add(new Task(input));
                        System.out.println(line + String.format("added: %s\n", input) + line);
                    }
            }
        }
    }
}
