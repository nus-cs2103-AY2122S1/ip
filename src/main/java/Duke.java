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

        String[] splitInput = input.split(" ", 2);

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
            case "todo":
                addTodo(splitInput);
                break;
            case "event":
                addEvent(splitInput);
                break;
            case "deadline":
                addDeadline(splitInput);
                break;
            default:
                System.out.println("Command not recognised, sorry.");
                break;
        }

        return false;
    }

    private static void addTodo(String[] input) {
        try {
            String name = input[1];
            TodoTask task = new TodoTask(name);
            add(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Description for To-do cannot be empty.\nexample:\ntodo buy groceries");
        }
    }

    private static void addEvent(String[] input) {

        String TIME_MARKER = " /at ";

        try {
            String[] taskAndTime = input[1].split(TIME_MARKER, 2);
            EventTask event;
            if (taskAndTime.length > 1) {
                event = new EventTask(taskAndTime[0], taskAndTime[1]);
                add(event);
            } else {
                System.out.println("Error: Need to specify event name and time.\nexample:\nevent meeting /at Tuesday 12pm");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Description for Event cannot be empty.\nexample:\nevent meeting /at Tuesday 12pm");
        }
    }

    private static void addDeadline(String[] input) {

        String DEADLINE_MARKER = " /by ";

        try {
            String[] taskAndTime = input[1].split(DEADLINE_MARKER, 2);
            if (taskAndTime.length > 1) {
                DeadlineTask deadlineTask = new DeadlineTask(taskAndTime[0], taskAndTime[1]);
                add(deadlineTask);
            } else {
                System.out.println("Error: Need to specify task name and deadline.\nexample:\ndeadline return book /by Sunday");
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Description for deadline cannot be empty." +
                    "\nexample:\ndeadline return book /by Sunday");
        }

    }

    /**
     * Sets a task in itemList as 'done'.
     * @param input String of user input.
     */
    private static void setTaskDone(String[] input) {

        if (input.length < 2) {
            System.out.println("done: mark task in list as done.\nexample:\ndone 1");
        } else if (itemList.isEmpty()) {
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
     * Adds a given Task to itemList.
     * @param task Task to add to itemList.
     */
    private static void add(Task task) {
        itemList.add(task);
        System.out.println("Sure thing. Added to list:\n" + task + "");
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

        while (!parseInput(sc.nextLine())) {
            continue;
        }

        sc.close();
    }
}
