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
     * Handles the logic for managing a user's tasks.
     */
    public void taskMode() throws DukeException {
        String message = Chatbot.acceptUserInput();
        if (message.equals("bye")) {
            Chatbot.printMessage(FAREWELL_MESSAGE);
            return;
        }
        try {
            if (message.equals("list")) {
                // List the tasks
                String listedTasks = this.taskList.toString();
                Chatbot.printMessage(listedTasks);
            } else if (message.startsWith("done ")) {
                // Mark a task as done
                int indexNumber = Integer.parseInt(message.substring(5)) - 1;
                String completedTask = this.taskList.completeTask(indexNumber);
                Chatbot.printMessage("Nice! I've marked this task as done:\n\t" + completedTask);
            } else {
                // Add a task to the list
                Task task;
                if (message.startsWith("todo")) {
                    task = this.taskList.addTodo(message.substring(4).trim());
                } else if (message.startsWith("event")) {
                    task = this.taskList.addEvent(message.substring(5).trim());
                } else if (message.startsWith("deadline")) {
                    task = this.taskList.addDeadline(message.substring(8).trim());
                } else {
                    throw new DukeException("I don't know what that command means.\nPlease input a valid command.");
                }
                Chatbot.printMessage("Got it. I've added this task:\n\t" + task.toString() + this.taskList.countTasks());
            }
        } catch (DukeException e) {
            Chatbot.printMessage(e.getMessage());
        } finally {
            taskMode();
        }
    }
}
