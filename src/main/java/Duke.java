import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Start Prompt.
        System.out.println(pattern("Hello! I'm Duke\n   What can I do for you?"));

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
     * Returns a boolean to react to a response.
     *
     * @param response User inputs.
     * @return continue or not.
     */
    public static boolean echo(String response) {
        switch (response) {
            case "bye":
                System.out.println("Bye, see you soon.");
                return false;
            default:
                System.out.println(pattern(response));
                return true;
        }
    }

    /**
     * Returns formatted string output.
     *
     * @param r The input string.
     * @return formatted string.
     */
    public static String pattern(String r) {
        int lens = r.length();
        StringBuffer result = new StringBuffer();
        StringBuffer curr = new StringBuffer();
        String empty = "   ";
        curr.append("*".repeat(50));
        result.append(empty + curr + "\n" + empty + r + "\n" + empty + curr + "\n");
        return result.toString();
    }
}
