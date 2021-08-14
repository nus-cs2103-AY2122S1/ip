import java.util.Scanner;

public class Duke {
    private static final String[] list = new String[100];
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
     * @return continue or not.
     */
    public static boolean echo(String response) {
        switch (response) {
            case "bye":
                System.out.println("Bye, see you soon.");
                return false;
            case "list":
                System.out.println(getPattern(toListStrings()));
                return true;
            default:
                list[count] = response;
                count++;
                System.out.println(getPattern("added: " + response));
                return true;
        }
    }

    /**
     * Returns formatted string output.
     *
     * @param r The input string.
     * @return formatted string.
     */
    public static String getPattern(String r) {
        int lens = r.length();
        StringBuffer result = new StringBuffer();
        StringBuffer curr = new StringBuffer();
        String empty = "   ";
        curr.append("*".repeat(50));
        String out = empty + curr + "\n" + empty + r + "\n" + empty + curr + "\n";
        result.append(out);
        return result.toString();
    }

    /**
     * Returns all strings stored with indexing.
     *
     * @return all user's stored strings.
     */
    public static String toListStrings() {
        StringBuffer curr = new StringBuffer();
        String end = "\n   ";
        for (int i = 0; i < count; i++) {
            if (i == count - 1) {
                end = "";
            }
            String out = ((Integer)(i + 1)).toString() + ". " + list[i] + end;
            curr.append(out);
        }
        return curr.toString();
    }
}
