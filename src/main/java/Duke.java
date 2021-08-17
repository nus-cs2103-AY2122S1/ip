import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String lineBreak = "\n____________________________________________________________\n";
    private ArrayList<Task> list = new ArrayList<>();

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
     * Adds the given task into list
     * @param input the given text
     * @return message indicating successful addition to list
     */
    public String add(String input) {
        this.list.add(new Task(input));
        return wrapText("added:" + input);
    }

    /**
     * Outputs the list of items in numbered format
     * @return list of items
     */
    public String showList () {
        Task[] lst = list.toArray(new Task[0]);
        String output = "Here are the tasks in your list:\n";
        int index = 1;
        for (Task t : lst) {
            output += String.format("\n%d.%s", index++, t.toString());
        }
        return wrapText(output);
    }

    /**
     * Marks task at given index as done
     * @param index index of task to be marked done
     * @return message indicating success
     */
    public String markDone(int index) {
        Task t = this.list.get(index - 1);
        t.setDone();
        return wrapText(String.format("Nice! I've marked this task as done: \n %s", t.toString()));
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
            String command = sc.next();
            if (command.equals("bye")) {
                System.out.println(bot.goodbye());
                break;
            } else if (command.equals("list")) {
                System.out.println(bot.showList());
            } else if (command.equals("add")){
                System.out.println(bot.add(sc.nextLine()));
            } else if (command.equals("done")) {
                System.out.println(bot.markDone(sc.nextInt()));
            }
        }

        sc.close();
    }
}
