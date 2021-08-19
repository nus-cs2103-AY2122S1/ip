import java.util.Scanner;

/**
 * Project Duke is a educational software project.
 * It is designed to take you through the steps of building a small software incrementally.
 */

public class Duke {
    /**
     * This is the scanner object used to get user input.
     */
    private static Scanner sc = new Scanner(System.in);

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

    private static void handleInput(String response) {
        String[] output = response.split(" ");
        String command = output[0];
        if (command.equals("done")) {
            Task editedTask = todos[Integer.parseInt(output[1])-1];
            editedTask.markIsDone();
            System.out.println(String.format("____________________________________________________________\n" +
                    "\tNice! I've marked this task as done: \n" +
                    "\t%s\n" +
                    "    ____________________________________________________________", editedTask.toString()));
        } else {
            Task newTask = null;
            switch (command) {
                case "todo":
                    String todoDescription = response.substring(5);
                    newTask = new Todo(todoDescription);
                    break;
                case "deadline":
                    String deadlineDescription = response.substring(9);
                    newTask = new Deadline(deadlineDescription);
                    break;
                case "event":
                    String eventDescription = response.substring(6);
                    newTask = new Event(eventDescription);
                    break;
                default:
                    break;
            }

            todos[index] = newTask;
            index++;

            System.out.println("\t____________________________________________________________\n\t" +
                    String.format("Got it. I've added this task:\n" +
                            "\t  %s\n" +
                            "\tNow you have %d tasks in the list.", newTask.toString(), index) +
                    "\n\t____________________________________________________________");
        }
    }

    /**
     * Starts the Duke chatbot.
     * Users can input String to interact with the chatbot.
     * Entering 'bye' exits the program.
     * Entering 'done' followed by an int will mark the task at that index as complete
     * Entering any other string will create a new todo.
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
                            System.out.println(String.format("\t%d.%s", (i+1), todos[i].toString()));
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
                    handleInput(response);
                    break;
            }
        }
    }
}
