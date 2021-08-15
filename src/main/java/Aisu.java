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
    private static final String DIV_HEAD = "=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=\n";
    private static final String DIV_TAIL = "\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=\n";
    private static final String LOGO = " (`-')  _   _               (`-').->           \n" +
                                        "(OO ).-/  (_)              ( OO)_       .->\n" +
                                        "/ ,---.   ,-(`-') (`-')   (_)--\\_) ,--.(,--.\n" +
                                        "| \\ /`.\\  | ( OO) ( OO).->/    _ / |  | |(`-')\n" +
                                        "'-'|_.' | |  |  )(,------.\\_..`--. |  | |(OO )\n" +
                                        "(|  .-. |(|  |_/  `------'.-._)   \\|  | | |  \\ \n" +
                                        "|  | |  | |  |'->         \\      / \\  '-'(_ .'\n" +
                                        "`--' `--' `--'             `-----'  `-----'   \n";

    public static void main(String[] args) {
        Tasklist tasklist = new Tasklist();

        System.out.println(Aisu.LOGO + Aisu.DIV_HEAD + " Hello, I'm Ai-su! How may I help you today?" + Aisu.DIV_TAIL);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // get input

        while (!input.equals("bye")) {
            System.out.println(Aisu.DIV_HEAD);
            try {
                if (input.equals("list")) { // show list
                    System.out.println(tasklist);
                } else if (input.startsWith("done ")) { // mark task as completed
                    int n = Integer.parseInt(input.substring(5));
                    tasklist.markDone(n);
                } else if (input.startsWith("todo ")) {
                    tasklist.addTask(input.substring(5), "T");
                } else if (input.startsWith("deadline ")) {
                    tasklist.addTask(input.substring(9), "D");
                } else if (input.startsWith("event ")) {
                    tasklist.addTask(input.substring(6), "E");
                } else if (input.startsWith("delete ")) {
                    int n = Integer.parseInt(input.substring(7));
                    tasklist.deleteTask(n);
                } else {
                    tasklist.addTask(input, "Error");
                }
            } catch (AisuException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(e);
            } finally {
                System.out.println(Aisu.DIV_TAIL);

                input = sc.nextLine(); // get next input
            }
        }
        System.out.println(Aisu.DIV_HEAD + " See you next time! :D" + Aisu.DIV_TAIL); // end program when user types bye
    }
}
