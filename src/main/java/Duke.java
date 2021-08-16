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
        input = sc.nextLine().toLowerCase();
        while (!input.equals("bye")) {
            try {
                parseInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println();
            } finally {
                input = sc.nextLine().toLowerCase();
                continue;
            }
        }
        exit();
    }

    public static void parseInput(String input) throws DukeException {
        if (input.equals("list")) {
            list();
        } else if (Pattern.matches("done.*", input)){
            markDone(input);
        } else if (Pattern.matches("event.*", input)) {
            String[] inputArr = input.replaceFirst("event ","").split("/at");
            if (inputArr.length == 1) {
                throw new DukeException("Describe the event and indicate its time!");
            }
            add(new Event(inputArr[0], inputArr[1]));
        } else if (Pattern.matches("deadline.*", input)) {
            String[] inputArr = input.replaceFirst("deadline ", "").split("/by");
            if (inputArr.length == 1) {
                throw new DukeException("Describe the activity and its deadline!");
            }
            add(new Deadline(inputArr[0], inputArr[1]));
        } else if (Pattern.matches("todo.*", input)) {
            String inputArr = input.replaceFirst("todo", "");
            if (inputArr.equals("")) {
                throw new DukeException("Describe the activity!");
            }
            add(new Task(inputArr.strip()));
        } else {
            throw new DukeException("I don't get what you mean? Try Again!");
        }
    }

    /**
     * This method prints the greetings to the user's terminal.
     */
    public static void greet() {
        System.out.println("Hello! I'm Duii, your personal assistant!");
        System.out.println("What do you need help with?");
        System.out.println();
    }

    /**
     * This method adds the input task into the list.
     *
     * @param task The task to be added to the list.
     */
    public static void add(Task task) throws DukeException {
        list[listLength] = task;
        System.out.println("New Task? I've added it to the list:");
        System.out.println(task.displayInfo());
        listLength++;
        System.out.println(String.format("Now you have %d task(s) in the list.", listLength));
        System.out.println();
    }

    public static void markDone(String input) throws DukeException {
        int id;
        try {
            id = Integer.parseInt(input.split(" ")[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Indicate the id of the task which you have completed!");
        }
        System.out.println("You've finished the task? Good job!");
        System.out.println("This task has been marked as done:");
        list[id - 1].complete();
        System.out.println(list[id - 1].displayInfo());
        System.out.println();
    }

    /**
     * This method enumerates all the tasks in the list.
     */
    public static void list() {
        System.out.println("Here's your current list:");
        for (int i = 0; i < listLength; i++) {
            System.out.println(String.format("%d. %s", i + 1, list[i].displayInfo()));
        }
        System.out.println();
    }

    /**
     * This method prints the exit message to the user's terminal.
     */
    public static void exit() {
        System.out.println("You're going already? Hope to see you again soon!");
    }
}
