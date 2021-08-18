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
     * Method to carry out the command entered by the user to the toDoList.
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
            System.out.println("-------------------------------------------------------------------");
            if (description.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                toDoList.list();
            } else if (description.startsWith("done")) {
                String taskNo = description.substring(5);
                System.out.println("Nice! I've marked this task as done:");
                toDoList.done(Integer.parseInt(taskNo));
            } else {
                toDoList.store(currTask);
                System.out.println("added: " + description);
            }
            System.out.println("-------------------------------------------------------------------");
            commanding();
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
