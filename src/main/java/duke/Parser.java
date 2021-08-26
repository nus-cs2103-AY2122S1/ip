package duke;

import tasks.TaskList;
import java.util.Scanner;

public class Parser {
    private static String DIVIDER = "____________________________________________________________";

    /**
     * Parses user input and calls the respective methods, or
     * throws error messages when input is invalid.
     *
     * @param tasks TaskList that is previously saved locally.
     */
    public static void startDuke(TaskList tasks) {
        boolean exit = false;

        while (!exit) {
            System.out.print("You: ");
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();

            System.out.println(DIVIDER);
            try {
                if (str.equals("bye")) {
                    exit = true;
                    System.out.println("Bye. Hope to see you again soon!");
                } else if (str.equals("list")) {
                    System.out.println(tasks.list());
                } else if (str.contains("done")) {
                    System.out.println(tasks.done(str));
                } else if (str.contains("delete")) {
                    System.out.println(tasks.delete(str));
                } else if (str.contains("todo") || str.contains("deadline") || str.contains("event")) {
                    System.out.println(tasks.addTask(str));
                } else {
                    throw new DukeException("Command is not valid!");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("tasks.Task does not exists!");
                if (tasks.getSize() == 0) {
                    System.out.println("You do not have any task in the list!");
                    System.out.println("Please add a task.");
                } else if (tasks.getSize() == 1) {
                    System.out.println("You only have one task in the list!");
                    System.out.println("Please enter 1 to delete or add more tasks.");
                } else {
                    System.out.println("Please enter an index number between 1 and " + tasks.getSize());
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(DIVIDER);
        }
    }
}
