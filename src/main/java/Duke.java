import java.util.Locale;
import java.util.Scanner;
import java.util.regex.*;

public class Duke {
    private static String input = "";
    private static Task[] list = new Task[100];
    private static int listLength = 0;

    public static void main(String[] args) {
        String logo = " ____        _ _\n"
                    + "|  _ \\ _   _(_|_)\n"
                    + "| | | | | | | | |\n"
                    + "| |_| | |_| | | |\n"
                    + "|____/ \\__,_|_|_|\n";
        System.out.println(logo);
        greet();

        Scanner sc = new Scanner(System.in);
        while (!(input = sc.nextLine().toLowerCase()).equals("bye")) {
            if (input.equals("list")) {
                list();
            } else if (Pattern.matches("done.*", input)){
                markDone(Integer.parseInt(input.split(" ")[1]));
            } else if (Pattern.matches("event.*", input)) {
                String[] inputArr = input.replaceFirst("event ","").split("/at");
                add(new Event(inputArr[0], inputArr[1]));
            } else if (Pattern.matches("deadline.*", input)) {
                String[] inputArr = input.replaceFirst("deadline ", "").split("/by");
                add(new Deadline(inputArr[0], inputArr[1]));
            } else if (Pattern.matches("todo.*", input)) {
                String inputArr = input.replaceFirst("todo ", "");
                add(new Task(inputArr));
            }
        }
        exit();
    }

    /**
     * This method prints the greetings to the user's terminal.
     */
    public static void greet() {
        System.out.println("Hello! I'm Duii, your personal assistant!");
        System.out.println("What do you need help with?");
    }

    /**
     * This method adds the input task into the list.
     *
     * @param task The task to be added to the list.
     */
    public static void add(Task task) {
        list[listLength] = task;
        System.out.println("New Task? I've added it to the list: ");
        System.out.println(task.displayInfo());
        listLength++;
        System.out.println(String.format("Now you have %d task(s) in the list.", listLength));
    }

    public static void markDone(int id) {
        System.out.println("You've finished the task? Good job!");
        System.out.println("This task has been marked as done: ");
        list[id - 1].complete();
        System.out.println(String.format("[%s] %s", list[id - 1].getStatus(), list[id - 1].getTaskName()));
    }

    /**
     * This method enumerates all the tasks in the list.
     */
    public static void list() {
        System.out.println("Here's your current list: ");
        for (int i = 0; i < listLength; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i].displayInfo()));
        }
    }

    /**
     * This method prints the exit message to the user's terminal.
     */
    public static void exit() {
        System.out.println("You're going already? Hope to see you again soon!");
    }
}
