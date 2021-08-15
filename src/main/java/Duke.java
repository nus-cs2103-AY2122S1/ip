import java.util.Scanner;

public class Duke {
    /** Number of tasks stored. */
    private static final Task[] list = new Task[100];
    private static int count;

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
            String response = sc.nextLine();
            flag = echo(response);
        }
    }

    /**
     * Returns a boolean to react to a response, while
     * printing the required information.
     *
     * @param response User inputs.
     * @return Continue to accept user input or not.
     */
    public static boolean echo(String response) {
        int len = response.length();
        boolean isDone = checkDone(response, len);
        if (isDone) {
            return true;
        }
        boolean isTodo = checkTodo(response, len);
        if (isTodo) {
            return true;
        }
        boolean isDeadline = checkDeadline(response, len);
        if (isDeadline) {
            return true;
        }
        boolean isEvent = checkEvent(response, len);
        if (isEvent) {
            return true;
        }
        switch (response) {
            case "bye":
                System.out.println(getPattern("Bye, see you soon. ^-^"));
                return false;
            case "list":
                System.out.println(getPattern(toListStrings()));
                return true;
            default:
                list[count] = new Task(response);
                count++;
                System.out.println(getPattern("added: " + response));
                return true;
        }
    }

    /**
     * Returns formatted string output.
     *
     * @param r The input string.
     * @return Formatted string.
     */
    public static String getPattern(String r) {
        int lens = r.length();
        StringBuilder result = new StringBuilder();
        StringBuilder curr = new StringBuilder();
        String empty = "   ";
        curr.append("*".repeat(50));
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
            String out = ((Integer)(i + 1)).toString() + "." + list[i].toString() + end;
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
        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (!(curr >= '0' && curr <= '9')) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Returns a boolean checking whether user input is
     * related to mark done of tasks.
     *
     * @param response The user input.
     * @return Is done operation or not.
     */
    public static boolean checkDone(String response, int len) {
        //check with the special response "done X", where X is a number.
        if (len >= 6 && response.substring(0, 5).equals("done ") && chekDigit(response.substring(5,len))) {
            int curr = Integer.parseInt(response.substring(5,len));
            Task shouldMark = list[curr - 1];
            shouldMark.markAsDone();
            String title = "Nice! I've marked this task as done: \n";
            String out = "     " + shouldMark.toString();
            System.out.println(getPattern(title + out));
            return true;
            //ERROR IF TASK NOT EXIST
        }
        return false;
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
     * Returns a boolean checking whether the user input is
     * related to todo operations.
     *
     * @param response The user input.
     * @param len The length of user input.
     * @return Whether the input is related to todo or not.
     */
    public static boolean checkTodo(String response, int len) {
        //check with the special response "todo X", where X is what to do.
        if (len >= 5 && response.substring(0, 5).equals("todo ")) {
            list[count] = new Todo(response.substring(5, len));
            Task curr = list[count];
            count ++;
            System.out.println(getPattern(getOutputFrame(curr.toString())));
            return true;
            //ERROR IF TASK NOT EXIST
        }
        return false;
    }

    /**
     * Returns a boolean checking whether the user input is
     * related to deadline operations.
     *
     * @param response The user input.
     * @param len The length of user input.
     * @return Whether the input is related to deadline or not.
     */
    public static  boolean checkDeadline(String response, int len) {
        //check with the special response "deadline X", where X is what to do and by what time.
        if (len >= 9 && response.substring(0, 9).equals("deadline ")
                && response.substring(9, len).contains(" /by ")) {
            String[] parts = response.substring(9, len).split(" /by ");
            String content = parts[0];
            String time = parts[1];
            list[count] = new Deadline(content, time);
            Task curr = list[count];
            count ++;
            System.out.println(getPattern(getOutputFrame(curr.toString())));
            return true;
            //ERROR IF TASK NOT EXIST
        }
        return false;
    }

    /**
     * Returns a boolean checking whether the user input is
     * related to event operations.
     *
     * @param response The user input.
     * @param len The length of user input.
     * @return Whether the input is related to event or not.
     */
    public static boolean checkEvent(String response, int len) {
        //check with the special response "todo X", where X is what to do.
        if (len >= 6 && response.substring(0, 6).equals("event ")
                && response.substring(6, len).contains(" /at ")) {
            String[] parts = response.substring(9, len).split(" /at ");
            String content = parts[0];
            String time = parts[1];
            list[count] = new Event(content, time);
            Task curr = list[count];
            count ++;
            System.out.println(getPattern(getOutputFrame(curr.toString())));
            return true;
            //ERROR IF TASK NOT EXIST
        }
        return false;
    }
}
