import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String lineBreak = "\n____________________________________________________________\n";
    private ArrayList<String> list = new ArrayList<>();

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
     * Adds the given input into list
     * @param input the given text
     * @return message indicating successful addition to list
     */
    public String add(String input) {
        this.list.add(input);
        return wrapText("added: " + input);
    }

    /**
     * Outputs the list of items in numbered format
     * @return list of items
     */
    public String showList () {
        String[] lst = list.toArray(new String[0]);
        String output = "";
        int index = 1;
        for (String s : lst) {
            output += String.format("\n%d. %s", index++, s);
        }
        return wrapText(output);
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
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(bot.goodbye());
                break;
            } else if (input.equals("list")) {
                System.out.println(bot.showList());
            } else {
                System.out.println(bot.add(input));
            }
        }

        sc.close();
    }
}
