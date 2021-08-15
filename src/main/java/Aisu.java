import java.util.Scanner;

/**
 * This is chatbot (Aisu) class.
 * You can:
 * 1) Type a task - Add to list
 * 2) Type "list" - Show list
 * 3) Type "done (taskNumber)" - Mark task as done
 * 4) Type "bye" - Exit program
 */
public class Aisu {
    // constants declaration
    private static final String DIVHEAD = "╔═══════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════╗\n";
    private static final String DIVTAIL = "\n╚═══════════*.·:·.☽✧    ✦    ✧☾.·:·.*═══════════╝\n";
    private static final String LOGO = " █████╗  ██╗      ███████╗██╗   ██╗ \n" +
                                        " ██╔══██╗██║      ██╔════╝██║   ██║ \n" +
                                        " ███████║██║█████╗███████╗██║   ██║ \n" +
                                        " ██╔══██║██║╚════╝╚════██║██║   ██║ \n" +
                                        " ██║  ██║██║      ███████║╚██████╔╝ \n" +
                                        " ╚═╝  ╚═╝╚═╝      ╚══════╝ ╚═════╝ \n";

    public static void main(String[] args) {
        Tasklist tasklist = new Tasklist();

        System.out.println(Aisu.LOGO + Aisu.DIVHEAD + "⚜  Hello, I'm Ai-su! How may I help you today?  ⚜" + Aisu.DIVTAIL);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // get input

        while (!input.equals("bye")) {
            System.out.println(Aisu.DIVHEAD);
            if (input.equals("list")) { // show list
                System.out.println(" These are your current tasks:");
                System.out.println(tasklist);

            } else if (input.startsWith("done ")) { // mark task as completed
                int n = Integer.parseInt(input.substring(5));
                tasklist.markDone(n);
                System.out.println(" Nice! I've marked this task as completed: ");
                System.out.println(tasklist.GetTaskAt(n));

            } else { // add task to list
                tasklist.addTask(input);
                System.out.println(" added: " + input);
            }
            System.out.println(Aisu.DIVTAIL);

            input = sc.nextLine(); // get next input
        }
        System.out.println(Aisu.DIVHEAD + " See you next time! :D" + Aisu.DIVTAIL); // end program when user types bye
    }
}
