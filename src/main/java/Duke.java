import java.util.Scanner;

public class Duke {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String lineBreak = "\n____________________________________________________________\n";

    public Duke() {}

    /**
     * Returns a greeting message
     * @return greeting message
     */
    public String greet() {
        return wrapText("Hello from\n" + logo + "\nWhat can I do for you?");
    }

    /**
     * Returns a goodbye message
     * @return goodbye message
     */
    public String goodbye() {
        return wrapText("Bye. Hope to see you again soon!");
    }

    /**
     * Echos the given input
     * @param input the given text
     * @return same input
     */
    public String echo(String input) {
        return wrapText(input);
    }

    /**
     * Helper function to wrap given text with line break
     * @param input the text to wrap
     * @return input wrapped with line breaks
     */
    private String wrapText(String input) {
        return lineBreak + input + lineBreak;
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        System.out.println(bot.greet());

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println(bot.goodbye());
                break;
            }
            System.out.println(bot.echo(input));
        }

        sc.close();
    }
}
