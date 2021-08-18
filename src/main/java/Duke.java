import java.util.Scanner;

/**
 * The Duke class encapsulates the action of the chatbot Duke.
 */
public class Duke {
    /** Array to represent the to-do list. */
    private TaskList toDoList;
    /** Pointer to indicate the next position to store the task in the array. */
    private int nextSpaceToStore;

    /**
     * Constructor to initialise the Duke chatbot.
     */
    private Duke() {
        this.toDoList = new TaskList(100);
        this.nextSpaceToStore = 0;
    }

    /**
     * Method to add the task entered by the user to the toDoList, list out the current
     * tasks when the command is "list" and exits when the command is "bye".
     */
    public void commanding() {
        Scanner scan = new Scanner(System.in);
        String description = scan.nextLine();
        if (description.equals("bye")) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Okay then! I hope to see you again soon master!");
            System.out.println("-------------------------------------------------------------------");
        } else {
            Task currTask = new Task(description);
            if (description.equals("list")) {
                System.out.println("-------------------------------------------------------------------");
                toDoList.list();
                System.out.println("-------------------------------------------------------------------");
                commanding();
            } else {
                toDoList.store(currTask);
                System.out.println("-------------------------------------------------------------------");
                System.out.println("added: " + description);
                System.out.println("-------------------------------------------------------------------");
                commanding();
            }
        }
        scan.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Hello I'm\n" + logo + "How may I help you today master?\n");
        System.out.println("-------------------------------------------------------------------");

        Duke chatbot = new Duke();
        chatbot.commanding();
    }
}
