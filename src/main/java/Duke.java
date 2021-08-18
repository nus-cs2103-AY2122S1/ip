import java.util.Scanner;

/**
 * Project Duke is a educational software project.
 * It is designed to take you through the steps of building a small software incrementally.
 */

public class Duke {
    /**
     * This is the scanner object used to get user input.
     */
    private static Scanner sc= new Scanner(System.in);

    /**
     * This is the declared string that triggers the exit.
     */
    private static String cancelWord = "bye";

    /**
     * Stores the array of todos
     */
    private static Task[] todos = new Task[100];

    /**
     * Stores the current index that is awaiting to be filled.
     */
    private static int index = 0;

    /**
     * Prints out the initial greeting message.
     */
    private static void greet() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm Duke. \n\tWhat can I do for you?\n" +
                "\t____________________________________________________________");
    }

    /**
     * Starts the Duke chatbot.
     * Users can input String to interact with the chatbot.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // Initial greeting of user
        greet();

        // Starts to ask for string of instruction
        // boolean flag to indicate if loop should be exited
        boolean exit = false;

        // if boolean is false, loop will be ran
        while (!exit) {
            String response = sc.nextLine();

            switch (response) {
                case "list":
                    System.out.println("\t____________________________________________________________");
                    for (int i = 0; i < todos.length; i++) {
                        if (todos[i] == null) {
                            break;
                        } else {
                            System.out.println(String.format("\t%s",todos[i].toString()));
                        }
                    }
                    System.out.println("\t____________________________________________________________");
                    break;
                case "bye":
                    System.out.println("\t____________________________________________________________\n\t" +
                            "Bye. Hope to see you again soon!" +
                            "\n\t____________________________________________________________");
                    exit = true;
                    break;
                default:
                    Task newTask = new Task(response);
                    todos[index] = newTask;
                    index++;

                    System.out.println("\t____________________________________________________________\n\t" +
                            String.format("added: %s", newTask.getDescription()) +
                            "\n\t____________________________________________________________");
                    break;
            }
        }
    }
}
