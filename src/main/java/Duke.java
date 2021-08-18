import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> itemList = new ArrayList<>();

    /**
     * Parses the user input string.
     * @param input
     * @return true if user enters the exit command, false otherwise.
     */
    private static boolean parseInput(String input) {

        String[] splitInput = input.split(" ");

        switch (splitInput[0]) {
            case "bye":
                System.out.println("Seeya!");
                return true;
            case "list":
                readList();
                break;
            case "done":
                setTaskDone(splitInput);
                break;
            default:
                add(input);
                break;
        }

        return false;
    }

    /**
     * Sets a task in itemList as 'done'.
     * @param input String array of split user input.
     */
    private static void setTaskDone(String[] input) {

        if (itemList.isEmpty()) {
            System.out.println("No tasks in list!");
        } else {
            try {
                int index = Integer.parseInt(input[1]) - 1;

                if (index < 0 || index >= itemList.size()) {
                    System.out.println("Invalid input, please enter a number from 1 to " + itemList.size());
                } else {
                    Task t = itemList.get(index);
                    t.markAsDone();
                    System.out.println("Alrighty, marking this task as done:\n" + t);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a number from 1 to " + itemList.size());
            }
        }

    }

    /**
     * Creates a new Task and adds it to itemList.
     * @param item name of Task to add to itemList.
     */
    private static void add(String item) {
        itemList.add(new Task(item));
        System.out.println("added:\n" + item);
    }

    /**
     * Prints out itemList.
     */
    private static void readList() {
        if (itemList.isEmpty()) {
            System.out.println("No tasks in list!");
        } else {
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + ". " + itemList.get(i));
            }
        }
    }

    /**
     * Echo function. Prints out given input String.
     * @param input The String to be printed.
     */
    private static void echo(String input) {
        System.out.println(input);
    }

    /**
     * Main function.
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Howdy! The name's\n" + logo + "\nWhat can I do for ya?");

        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!parseInput(sc.nextLine())) {
            continue;
        }

        sc.close();
    }
}
