import java.util.Scanner;

/**
 * This is chatbot (Aisu) class.
 * You can:
 * 1) Type "todo (task)" - Add tasks without any date/time attached to it
 * 2) Type "list" - Show list
 * 3) Type "done (taskNumber)" - Mark task as done
 * 4) Type "deadline (task) /by (date)" - Add tasks that need to be done before a specific date/time
 * 5) Type "event (task) /at (date)" - Add tasks that start at a specific time and ends at a specific time
 * 6) Type "bye" - Exit program
 */
public class Aisu {
    // constants declaration
    private static final String DIV_HEAD = "╔═══════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════╗\n";
    private static final String DIV_TAIL = "\n╚═══════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════╝\n";
    private static final String LOGO = " █████╗  ██╗      ███████╗██╗   ██╗ \n" +
                                        " ██╔══██╗██║      ██╔════╝██║   ██║ \n" +
                                        " ███████║██║█████╗███████╗██║   ██║ \n" +
                                        " ██╔══██║██║╚════╝╚════██║██║   ██║ \n" +
                                        " ██║  ██║██║      ███████║╚██████╔╝ \n" +
                                        " ╚═╝  ╚═╝╚═╝      ╚══════╝ ╚═════╝ \n";

    public static void main(String[] args) {
        Tasklist tasklist = new Tasklist();

        System.out.println(Aisu.LOGO + Aisu.DIV_HEAD + "⚜  Hello, I'm Ai-su! How may I help you today?  ⚜" + Aisu.DIV_TAIL);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // get input

        while (!input.equals("bye")) {
            System.out.println(Aisu.DIV_HEAD);
            if (input.equals("list")) { // show list
                System.out.println(" These are your current tasks:");
                System.out.println(tasklist);

            } else if (input.startsWith("done ")) { // mark task as completed
                int n = Integer.parseInt(input.substring(5));
                tasklist.markDone(n);
                System.out.println(" Nice! I've marked this task as completed:");
                System.out.println(tasklist.GetTaskAt(n));
            } else if (input.startsWith("todo ")) {
                tasklist.addTask(input.substring(5), "T");
            } else if (input.startsWith("deadline ")) {
                tasklist.addTask(input.substring(9), "D");
            } else if (input.startsWith("event ")) {
                tasklist.addTask(input.substring(6), "E");
            }
            System.out.println(Aisu.DIV_TAIL);

            input = sc.nextLine(); // get next input
        }
        System.out.println(Aisu.DIV_HEAD + " See you next time! :D" + Aisu.DIV_TAIL); // end program when user types bye
    }
}
