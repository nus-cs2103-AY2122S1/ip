import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final ArrayList<Task> list = new ArrayList<>();

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
     * @param t the task to add
     * @return message indicating successful addition to list
     */
    public String add(Task t) {
        this.list.add(t);
        return wrapText(
                String.format(
                        "Got it. I've added this task:\n %s\nNow you have %d task(s) in the list",
                        t.toString(),
                        Task.count));
    }

    /**
     * Outputs the list of items in numbered format
     * @return list of items
     */
    public String showList () {
        Task[] lst = list.toArray(new Task[0]);
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        int index = 1;
        for (Task t : lst) {
            output.append(String.format("\n%d.%s", index++, t.toString()));
        }
        return wrapText(output.toString());
    }

    /**
     * Marks task at given index as done
     * @param index index of task to be marked done
     * @return message indicating success
     */
    public String markDone(int index) throws InvalidIndexException {
        if ((index) > Task.count) throw new InvalidIndexException();
        Task t = this.list.get(index - 1);
        t.setDone();
        return wrapText(String.format("Nice! I've marked this task as done:\n %s", t.toString()));
    }

    /**
     * Responds to given user command and input
     * @param command the user command
     * @param input the array storing user input split with /
     * @return Duke's response
     */
    public String run(String command, String[] input) {
        try {
            switch (command) {
                case "list":
                    return this.showList();
                case "todo":
                    return this.add(new Todo(input[0]));
                case "deadline":
                    return this.add(new Deadline(input[0], input[1]));
                case "event":
                    return this.add(new Event(input[0], input[1]));
                case "done":
                    return this.markDone(Integer.parseInt(input[0].strip()));
                default:
                    throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            return wrapText(e.toString());
        }
    }

    /**
     * Helper function to wrap given text with line break
     * @param input the text to wrap
     * @return input wrapped with line breaks
     */
    private String wrapText(String input) {
        String lineBreak = "\n____________________________________________________________\n";
        return lineBreak + input + lineBreak;
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        System.out.println(bot.greet());

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String command = sc.next();
            if (command.equals("bye")) break;
            String[] input = sc.nextLine().split(" /[a-z][a-z] ");
            System.out.println(bot.run(command, input));
        }
        
        System.out.println(bot.goodbye());
        sc.close();
    }
}
