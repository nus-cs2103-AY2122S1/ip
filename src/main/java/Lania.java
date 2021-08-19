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
    public static void update(String s) throws LaniaException {
        Task t;
        String[] split = s.split(" ", 2);
        if (split[0].equals("todo")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                t = new Todo(split[1]);
            }
        } else if (split[0].equals("deadline")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                String[] splitDeadline = split[1].split(" /by ");
                t = new Deadline(splitDeadline[0], splitDeadline[1]);
            }
        } else if (split[0].equals("event")) {
            if (split.length == 1) {
                throw new LaniaEmptyDescriptionException(split[0]);
            } else {
                String[] splitEvent = split[1].split(" /at ");
                t = new Event(splitEvent[0], splitEvent[1]);
            }
        } else {
            throw new LaniaException("Sorry, but Lania doesn't know what that means");
        }
        taskArray[count] = t;
        count++;
        System.out.println("Lania has added: ");
        System.out.println(t);
        System.out.println("Great! Now you have " + count + (count == 1 ? " task" : " tasks") + " in your list.");
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
            try {
                String[] split = input.split(" ");
                if (input.equals("list")) {
                    list();
                } else if (split[0].equals("done")) {
                    complete(Integer.parseInt(split[1]));
                } else {
                    update(input);
                }
            } catch (LaniaException e) {
                System.out.println(e);
            } finally {
                input = s.nextLine();
            }
        }
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }
}
