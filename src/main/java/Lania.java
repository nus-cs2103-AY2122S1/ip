import java.util.Scanner;

public class Lania {

    /** Task array containing user tasks */
    private static Task[] taskArray = new Task[100];
    /** Keep track of number of user inputs */
    private static int count = 0;

    /**
     * Store user input in task array and show that it is added.
     *
     * @param s String provided by the user.
     */
    public static void update(String s) {
        Task t = new Task(s);
        taskArray[count] = t;
        count++;
        System.out.print("added: ");
        echo(s);
    }

    /**
     * Display all tasks stored.
     *
     */
    public static void list() {
        System.out.println("You have the following task(s):");
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + ".[" + taskArray[i].getStatusIcon() + "] " + taskArray[i].description);
        }
    }

    /**
     * Prints the user input.
     *
     * @param s String provided by the user.
     */
    public static void echo(String s) {
        System.out.println(s);
    }

    /**
     * Chatbot that stores and displays the user input.
     *
     * @param args The command line arguments. Not required here.
     **/
    public static void main(String[] args) {
        System.out.println("Hello I am Lania! How may I be of assistance?");
        System.out.println("Enter 'bye' to exit");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while(!input.equals("bye")) {
            if (input.equals("list")) {
                list();
                input = s.nextLine();
            } else {
                update(input);
                input = s.nextLine();
            }
        }
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }
}
