import java.util.Scanner;

public class Parser {
    private static String DIVIDER = "____________________________________________________________";

    public static void startDuke() {
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
                    System.out.println(TaskList.list());
                } else if (str.contains("done")) {
                    System.out.println(TaskList.done(str));
                } else if (str.contains("delete")) {
                    System.out.println(TaskList.delete(str));
                } else if (str.contains("todo") || str.contains("deadline") || str.contains("event")) {
                    System.out.println(TaskList.addTask(str));
                } else {
                    throw new DukeException("Command is not valid!");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Task does not exists!");
                if (TaskList.tasks.size() == 0) {
                    System.out.println("You do not have any task in the list!");
                    System.out.println("Please add a task.");
                } else if (TaskList.tasks.size() == 1) {
                    System.out.println("You only have one task in the list!");
                    System.out.println("Please enter 1 to delete or add more tasks.");
                } else {
                    System.out.println("Please enter an index number between 1 and " + TaskList.tasks.size());
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(DIVIDER);
        }
    }
}
