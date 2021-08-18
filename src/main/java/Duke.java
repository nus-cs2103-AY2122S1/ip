import java.util.Scanner;
public class Duke {
    /** Array to represent the to-do list. */
    private String[] toDoList;
    /** Pointer to indicate the next position to store the task in the array. */
    private int nextSpaceToStore;

    /**
     * Constructor to initialise the Duke chatbot.
     */
    private Duke() {
        this.toDoList = new String[100];
        this.nextSpaceToStore = 0;
    }

    /**
     * Method to echo the commands entered by the user, and exits when the command is "bye".
     */
    public void commanding() {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        if (s.equals("bye")) {
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Okay then! I hope to see you again soon master!");
            System.out.println("-------------------------------------------------------------------");
        } else {
            System.out.println("-------------------------------------------------------------------");
            System.out.println(s);
            System.out.println("-------------------------------------------------------------------");
            commanding();
        }
        scan.close();
    }

    /**
     * Method to store the task to the toDoList and increment the nextSpaceToStore pointer by one.
     * @param toStore The task to store into the toDoList.
     */
    public void store(String toStore) {
        this.toDoList[nextSpaceToStore] = toStore;
        this.nextSpaceToStore = this.nextSpaceToStore + 1;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Hello from\n" + logo + "How may I help you today master?\n");
        System.out.println("-------------------------------------------------------------------");

        Duke chatbot = new Duke();
        chatbot.commanding();
    }
}
