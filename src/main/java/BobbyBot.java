import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BobbyBot {
    private static final List<Task> tasks = new ArrayList<Task>();
    private static final String div = "____________________________________________________________\n";
    private static int totalTasks = 0;
    private static final String[] acceptedCommands = {"bye", "list", "done", "deadline", "todo", "event", "delete"};

    public BobbyBot() {
        System.out.println(div + "Hello! I'm Bobby\nWhat can I do for you?\n" + div);
    }

    /**
     * Perform command based on String user input
     * @param userInput string command for chatbot
     * @throws InvalidCommandException Command not one of the acceptedCommands
     * @throws InvalidArgumentException Invalid or no arguments given
     * @throws TooManyArgumentsException Too many /by or /at connectors
     */
    public void doCommand(String userInput) throws InvalidCommandException,
            InvalidArgumentException, TooManyArgumentsException {
        List<String> userInputList = new LinkedList<>(Arrays.asList(userInput.split(" ")));
        String command = userInputList.get(0);
        String description;
        String[] userInputArgs;
        if (!commandIsValid(command)) {
            throw new InvalidCommandException("Invalid Command!");
        }

        switch (command) {
        case "bye":
            sayBye();
            break;
        case "list":
            printList();
            break;
        case "done":
            markAsDone(Integer.parseInt(userInputList.get(1)));
            break;
        case "delete":
            // check delete argument
            if (!isNumeric(userInputList.get(1))) {
                throw new InvalidArgumentException("Delete argument is not numeric");
            }
            deleteTask(Integer.parseInt(userInputList.get(1)));
            break;
        case "todo":
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for todo");
            }
            description = String.join(" ", userInputList);
            createToDo(description);
            break;
        case "deadline":
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for deadline");
            }
            //split description and by
            userInputArgs = String.join(" ", userInputList).split("/by ");
            if (userInputArgs.length > 2) {
                throw new TooManyArgumentsException("Too many arguments given for deadline");
            } else if (userInputArgs.length == 1) {
                throw new InvalidArgumentException("Could not find connector /by ");
            }
            description = userInputArgs[0];
            String by = userInputArgs[1];
            createDeadline(description, by);
            break;
        case "event":
            userInputList.remove(0);
            if (userInputList.size() == 0) {
                throw new InvalidArgumentException("No arguments submitted for event");
            }
            //split description and at
            userInputArgs = String.join(" ", userInputList).split("/at ");
            if (userInputArgs.length > 2) {
                throw new TooManyArgumentsException("Too many arguments given");
            } else if (userInputArgs.length == 1) {
                throw new InvalidArgumentException("Could not find connector /at");
            }
            description = userInputArgs[0];
            String at = userInputArgs[1];

            createEvent(description, at);
            break;
        }
    }

    /**
     * Checks if command is valid
     * @param command command for chatbot eg. (list,delete,todo..)
     * @return boolean if command is valid
     */
    private boolean commandIsValid(String command) {
        //check valid command
        boolean commandIsValid = false;
        for (String acceptedCommand : acceptedCommands) {
            if (acceptedCommand.equals(command)) {
                commandIsValid = true;
                break;
            }
        }
        return commandIsValid;
    }

    /**
     * Say Bye and close program
     */
    private void sayBye() {
        System.out.println(div + "Bye. Hope to see you again soon!\n" + div);
        System.exit(1);
    }

    /**
     * Print current to do list
     */
    private void printList() {
        System.out.println(div + "Here are the tasks in your list:");
        for (int i = 0; i < totalTasks; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(div);
    }

    /**
     * Mark a task as done
     * @param taskNo Task Number (starting from index 1)
     */
    private void markAsDone(int taskNo) {
        Task taskCompleted = tasks.get(taskNo - 1);
        taskCompleted.markAsDone();
        System.out.println(div + "Nice! I've marked this task as done:");
        System.out.println("  " + taskCompleted + "\n" + div);
    }

    /**
     * Delete a task
     * @param taskNo Task Number (starting from index 1)
     */
    private void deleteTask(int taskNo) {
        if (taskNo > tasks.size() || taskNo < 1) {
            System.out.println("Cannot find task! Use list command to see available tasks");
            return;
        }
        Task taskToDelete = tasks.get(taskNo - 1);
        System.out.println(div + "Noted. I've removed this task:");
        System.out.println("  " + taskToDelete);
        tasks.remove(taskToDelete);
        totalTasks--;
        System.out.println("Now you have " + totalTasks + " tasks in the list.\n" + div);
    }
    /**
     * Creates a todo task
     * @param description description of task
     */
    private void createToDo(String description) {
        tasks.add(new ToDo(description));
        totalTasks++;
        System.out.println(div + "Got it. I've added this task:\n  " + tasks.get(totalTasks - 1) + "\n"
                + "Now you have " + totalTasks + " tasks in the list.\n" + div);
    }

    /**
     * Creates an event task
     * @param description description of task
     * @param at time period of Event (start-end)
     */
    private void createEvent(String description, String at) {
        tasks.add(new Event(description, at));
        totalTasks++;
        System.out.println(div + "Got it. I've added this task:\n  " + tasks.get(totalTasks - 1) + "\n"
                + "Now you have " + totalTasks + " tasks in the list.\n" + div);
    }

    /**
     * Creates a deadline task
     * @param description description of task
     * @param by date and time that the task should be completed by
     */
    private void createDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
        totalTasks++;
        System.out.println(div + "Got it. I've added this task:\n  " + tasks.get(totalTasks - 1) + "\n"
                + "Now you have " + totalTasks + " tasks in the list.\n" + div);
    }

    /**
     * Helper function to check if string is numeric
     * @param str string to test if numeric
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}
