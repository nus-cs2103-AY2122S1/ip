import java.util.Arrays;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * Current Progress: Level 4. ToDos, Events, Deadlines
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted commands:
 *   - 'todo x' -> adds a ToDo task of x with no date/time attached
 *   - 'deadline x /by y' -> adds a Deadline task of x that needs to be done by y
 *   - 'event x /at y' -> adds an Event task of x that starts and ends at a specific time y
 *   - 'list' -> displays current list of tasks
 *   - 'done' x -> marks Task x as done
 *   - 'bye' -> exits the program
 *   - any other String -> considered as invalid since tasks have valid commands
 *
 * @author Benedict Chua
 */
public class Duke {
    private static final String INDENTATION = "     ";
    private static final String LINE_SEPARATOR = "    ____________________________________________________________";
    private static final String[] GREETING = {"Hey! I'm Duke (Tsun)!", "What do you want?",
        "...It's not like I want to help you or anything!"};
    private static final String[] GOODBYE = {"Hmph! It's not like I want to see you again or anything!"};

    private static TaskList tasksList;

    private static void displayMessage(String[] messages) {
        System.out.println(LINE_SEPARATOR);
        for (String message : messages) {
            System.out.println(INDENTATION + message);
        }
        System.out.println(LINE_SEPARATOR + "\n");
    }

    private static void displayTasks() {
        System.out.println(LINE_SEPARATOR);
        tasksList.printList();
        System.out.println(LINE_SEPARATOR + "\n");
    }

    private static void addToList(String task, String typeOfTask) {
        displayMessage(tasksList.addToList(task, typeOfTask));
    }

    private static void completeTask(String index) {
        int listIndex = parseInt(index) - 1;
        displayMessage(tasksList.markTaskAsDone(listIndex));
    }

    public static void main(String[] args) {
        // Initialise program
        tasksList = new TaskList(100);
        Scanner sc = new Scanner(System.in);

        // Greets user
        displayMessage(GREETING);

        // Carries out commands inputted by user into the Scanner
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            switch (command.split(" ")[0]) {
            case "list":
                displayTasks();
                break;
            case "done":
                completeTask(command.split(" ")[1]);
                break;
            case "todo":
                addToList(command.split(" ", 2)[1], "ToDo");
                break;
            case "deadline":
                addToList(command.split(" ", 2)[1], "Deadline");
                break;
            case "event":
                addToList(command.split(" ", 2)[1], "Event");
                break;
            default:
                // error
                break;
            }
            command = sc.nextLine();
        }

        // Bids user farewell and program stops
        displayMessage(GOODBYE);
    }
}
