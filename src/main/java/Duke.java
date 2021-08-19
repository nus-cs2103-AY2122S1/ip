import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the robot which has corresponding reaction to the user inputs.
 *
 * @author QIN GUORUI
 */
public class Duke {
    /** The data structure used to store the tasks. */
    private static final ArrayList<Task> list = new ArrayList<>(100);

    /** Number of tasks stored. */
    private static int count;

    /** User inputs. */
    private static String response;

    /** The length of user input. */
    private static int len;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Start Prompt.
        System.out.println(getPattern("Hello! I'm Duke\n   What can I do for you?"));

        //User input.
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        //Continue the loop until user says bye.
        while (flag) {
            response = sc.nextLine();
            len = response.length();
            flag = echo2();
        }
    }

    /**
     * Returns formatted string output.
     *
     * @param r The input string.
     * @return Formatted string.
     */
    public static String getPattern(String r) {
        StringBuilder result = new StringBuilder();
        StringBuilder curr = new StringBuilder();
        String empty = "   ";
        curr.append("*".repeat(80));
        String out = empty + curr + "\n" + empty + r + "\n" + empty + curr + "\n";
        result.append(out);
        return result.toString();
    }

    /**
     * Returns all strings stored with indexing.
     *
     * @return All user's stored strings.
     */
    public static String toListStrings() {
        StringBuilder curr = new StringBuilder();
        String end = "\n   ";
        String begin = "Here are the tasks in your list:\n   ";
        curr.append(begin);
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                end = "";
            }
            String out = ((Integer) (i + 1)).toString() + "." + list.get(i).toString() + end;
            curr.append(out);
        }
        return curr.toString();
    }

    /**
     * Returns boolean value which checks whether
     * input string is digit or not.
     *
     * @param input String input.
     * @return Content of input is digit or not.
     */
    public static boolean chekDigit(String input) {
        boolean flag = true;
        int i = 0;
        if (input.charAt(0) == '-') {
            i = 1;
        }
        for (; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (!(curr >= '0' && curr <= '9')) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Returns the formatted string output.
     *
     * @param task The possible task string representations.
     * @return The desirable output string related to task.
     */
    public static String getOutputFrame(String task) {
        String title = "Got it. I've added this task:\n   ";
        String middle = "  " + task + "\n   ";
        String end = "Now you have " + count + " tasks in the list.";
        return title + middle + end;
    }

    /**
     * Returns the correct enum operation according to response,
     * or it returns null to show exception occurred.
     *
     * @return Type of operation for the next judgement.
     */
    public static Operation checkResponse() {
        Operation op;
        try {
            if (response.startsWith("done ")
                    && chekDigit(response.substring(5, len))) {
                op = Operation.DONE;
            } else if (response.startsWith("todo ")) {
                op = Operation.TODO;
            } else if (response.startsWith("deadline ")
                    && response.substring(9, len).contains(" /by ")) {
                op = Operation.DEADLINE;
            } else if (response.startsWith("event ")
                    && response.substring(6, len).contains(" /at ")) {
                op = Operation.EVENT;
            } else if (response.startsWith("delete ")
                    && chekDigit(response.substring(7, len))) {
                op = Operation.DELETE;
            } else if (response.equals("delete") || response.equals("todo") || response.equals("deadline")
                    || response.equals("event") || response.equals("done")) {
                String curr = response;
                throw new EmptyInputException(curr);
            } else {
                //This means there's no match of operations.
                throw new NotRecognizeException();
            }
        }catch (DukeException e) {
            System.out.println(getPattern(e.getMessage()));
            return null;
        }
        return op;
    }

    /**
     * Returns a boolean to react to a response, while
     * printing the required information.
     *
     * @return Whether the user continues to input or not.
     */
    public static boolean echo2() {
        switch (response) {
        case "bye":
            System.out.println(getPattern("Bye, see you soon. ^-^"));
            return false;
        case "list":
            System.out.println(getPattern(toListStrings()));
            return true;
        default:
            Operation op = checkResponse();
            if (op == null) {
                return true;
            }
            switch (op) {
            case DEADLINE:
                //Fallthrough
            case TODO:
                //Fallthrough
            case EVENT:
                count++;
                System.out.println(getPattern(getOutputFrame(
                        op.eventOrDeadlineOrTodo(response, len, list))));
                break;
            case DONE:
                    System.out.println(getPattern(
                            op.doneOrDelete(response, len, list, count)));
                    break;
            case DELETE:
                //Draft output with ending 'S' or 'F'.
                String outDraft = op.doneOrDelete(response, len, list, count);
                int lens = outDraft.length();
                String outExact = outDraft.substring(0, lens - 1);
                System.out.println(getPattern(outExact));

                //Judge whether there's a need to decrease count.
                if (outDraft.charAt(lens - 1) == 'S') {
                    count--;
                }
                break;
            default:
                break;
            }
            return true;
        }
    }
}
