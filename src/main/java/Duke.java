import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Starting message
        String start = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                + "Hello! My name is LHWBot!\n"
                + "What can I do for you today?\n"
                + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
        System.out.println(start);

        // Array of String to store user inputs
        Task[] TaskArray = new Task[100];
        int index = 0;

        // Define the scanner to read user inputs
        Scanner reader = new Scanner(System.in);

        // Continuously listen for user inputs
        while(true) {
            String input = reader.nextLine();
            if (input.equals("bye")) {
                // If the user types "bye", end bot
                String bye = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                        + "Bye... Hope to see you again soon!\n"
                        + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(bye);
                break;
            } else if (input.equals("list")) {
                // If the user types "list", show the list of tasks
                String list = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                for (int i = 0; i < index; i++) {
                    list = list
                            + String.valueOf(i + 1)
                            + ". "
                            + TaskArray[i].getTaskState()
                            + "\n";
                }
                list += "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(list);
            } else if (input.length() > 5 && input.substring(0, 5).equals("done ")) {
                // If the user types "done X" where X is a non-zero integer, mark the task as complete
                // TODO Error handling for inputs
                int doneIndex = Integer.parseInt(input.substring(5)) - 1;
                TaskArray[doneIndex].toggleState();
                String done = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                        + "Great! I've marked the following task as done:\n"
                        + TaskArray[doneIndex].getTaskState()
                        + "\n";
                done += "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(done);
            }else {
                // If the user types a command, store the command
                TaskArray[index] = new Task(input);
                index++;

                // After storing it, repeat it back to the user
                String cmd = "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n"
                        + "Task Added: "
                        + input
                        + "\n"
                        + "-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~\n";
                System.out.println(cmd);
            }
        }
    }
}

/**
 * Task class that encapsulates the individual task passed into the bot.
 */
class Task {

    /**
     * Variable that holds the task input as String
     */
    protected String task;
    protected boolean done;

    /**
     * Constructor for the Task class
     */
    Task(String task) {
        this.task = task;
        this.done = false;
    }

    /**
     * Retrieves ONLY the task that the class is holding.
     *
     * @return The task String held by the Task object.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * Retrieves ONLY the state of the task.
     * Displays X if done, else displays whitespace.
     *
     * @return The String representation of the task completion state.
     */
    public String getState() {
        return this.done ? "X" : " ";
    }

    /**
     * Retrieves the completion state of the task, followed by the task name.
     *
     * @return The String representation of the task completion state and the task name.
     */
    public String getTaskState() {
        return this.done ? "[X] " + this.task : "[ ] " + this.task;
    }

    /**
     * Toggles the current state by negating the boolean state value. (Done vs not done)
     */
    public void toggleState() {
        this.done = !this.done;
    }
}
