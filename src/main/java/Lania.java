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
        System.out.print("Lania has added: ");
        echo(s);
    }

    /**
     * Display all tasks stored.
     *
     */
    public static void list() {
        System.out.println("You have the following task(s):");
        for (int i = 0; i < count; i++) {
            System.out.println(i + 1 + "." + taskArray[i]);
        }
    }

    /**
     * Completes given task number.
     *
     * @param i The task number to be completed.
     */
    public static void complete(int i) {
        i--;
        taskArray[i].markAsDone();
        System.out.println("Good job! Lania has marked this task as done:");
        System.out.println(taskArray[i]);
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
        System.out.println("Hello I am Lania! How may Lania be of assistance?");
        System.out.println("Enter 'bye' to exit");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while(!input.equals("bye")) {
            String[] split = input.split(" ");
            if (split[0].equals("done")) {
                complete(Integer.parseInt(split[1]));
            } else {
                if (input.equals("list")) {
                    list();
                } else {
                    update(input);
                }
            }
            input = s.nextLine();
        }
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }
}
