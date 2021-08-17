/**
 * A personal assistant chatbot that helps a person to keep track of various things.
 *
 * @author Jovyn Tan
 * @version CS2103 AY21/22 Sem 1
 */
public class Duke extends Chatbot {
    private static String GREETING_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static String FAREWELL_MESSAGE = "See you soon! :)";

    private TaskList taskList;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        duke.greet();
        duke.taskMode();
    }

    /**
     * A constructor for Duke chatbot.
     */
    public Duke() {
        this.taskList = new TaskList();
    }

    /**
     * Prints a greeting to the user.
     */
    public void greet() {
        Chatbot.printMessage(GREETING_MESSAGE);
    }

    /**
     * Echoes the user's input, until the user says "bye".
     */
    public void echo() {
        String message = Chatbot.acceptUserInput();
        if (message.equals("bye")) {
            Chatbot.printMessage(FAREWELL_MESSAGE);
        } else {
            Chatbot.printMessage(message);
            echo();
        }
    }

    /**
     * Manages a user's tasks.
     */
    public void taskMode() {
        String message = Chatbot.acceptUserInput();
        if (message.equals("bye")) {
            Chatbot.printMessage(FAREWELL_MESSAGE);
        } else if (message.equals("list")) {
            String listedTasks = this.taskList.toString();
            Chatbot.printMessage(listedTasks);
            taskMode();
        } else {
            this.taskList.addTask(message);
            Chatbot.printMessage("added: " + message);
            taskMode();
        }
    }
}
