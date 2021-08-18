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
        List<Task> tasks = new ArrayList<>();

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
                System.out.printf(format, "Here is your task list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("\t%d.%s\n", i + 1, tasks.get(i));
                }
                System.out.printf(format, horizontalLine);
            } else if (input.startsWith("done")) {
                // Set a task as done
                String taskNumberString = input.substring(5);
                System.out.printf(format, horizontalLine);
                if (taskNumberString.matches("\\d+")
                        && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                        && Integer.parseInt(taskNumberString) - 1 >= 0)) {
                    int taskIndex = Integer.parseInt(taskNumberString) - 1;
                    Task doneTask = tasks.get(taskIndex);
                    doneTask.markAsDone();
                    System.out.printf(format, "Good work! This task is now marked as done:");
                    System.out.printf("\t\t%s\n", doneTask.toString());
                } else {
                    // Invalid input (not a number or invalid number)
                    System.out.printf(format, "Please type in a valid task number.");
                }
                System.out.printf(format, horizontalLine);
            } else {
                // Create task object and add it to the list
                Task task = new Task(input);
                tasks.add(task);
                System.out.printf(format, horizontalLine);
                System.out.printf("\tadded: %s\n", input);
                System.out.printf(format, horizontalLine);
            }
        }
    }
}
