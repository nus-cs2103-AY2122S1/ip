import java.util.Scanner;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * Current Progress: Level 2. Add, List
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted commands:
 *   - any String that is not a command below -> assumed to be a task, adds it to list of tasks
 *   - list -> displays current list of tasks
 *   - bye -> exits the program
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

    private static void addToList(String task) {
        // Adds task to list (array) and increment list counter
        tasksList.addToList(task);
        displayMessage(new String[] {"added: " + task});
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
            // Using switch even though there's only 2 cases here to build up on it in higher Levels
            switch (command) {
            case "list":
                displayTasks();
                break;
            default:
                addToList(command);
                break;
            }
            command = sc.nextLine();
        }

        // Bids user farewell and program stops
        displayMessage(GOODBYE);
    }
}
