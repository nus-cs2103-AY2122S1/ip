import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BobbyBot {
    private static final Task[] tasks = new Task[100];
    private static final String div = "____________________________________________________________\n";
    private static int totalTasks = 0;
    private static final String[] acceptedCommands = {"bye", "list", "done", "deadline", "todo", "event"};

    public BobbyBot() {
        System.out.println(div + "Hello! I'm Bobby\nWhat can I do for you?\n" + div);
    }
    public void doCommand(String userInput) throws InvalidCommandException {
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
        case "list":
            printList();
        case "done":
            markAsDone(Integer.parseInt(userInputList.get(1)));
        case "todo":
            description = String.join(" ", userInputList);
            createToDo(description);
            break;
        case "deadline":
            //split description and by
            userInputArgs = String.join(" ", userInputList).split("/by ");
            description = userInputArgs[0];
            String by = userInputArgs[1];
            createDeadline(description, by);
            break;
        case "event":
            //split description and at
            userInputArgs = String.join(" ", userInputList).split("/at ");
            description = userInputArgs[0];
            String at = userInputArgs[1];
            createEvent(description, at);
            break;
        }
    }

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
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println(div);
    }

    private void markAsDone(int taskNo) {
        Task taskCompleted = tasks[taskNo - 1];
        taskCompleted.markAsDone();
        System.out.println(div + "Nice! I've marked this task as done:");
        System.out.println("  " + taskCompleted + "\n" + div);
    }

    private void createToDo(String description) {
        tasks[totalTasks] = new ToDo(description);
        totalTasks++;
        System.out.println(div + "Got it. I've added this task:\n  " + tasks[totalTasks - 1] + "\n"
                + "Now you have " + totalTasks + " tasks in the list.\n" + div);
    }

    private void createEvent(String description, String at) {
        tasks[totalTasks] = new Event(description, at);
        totalTasks++;
        System.out.println(div + "Got it. I've added this task:\n  " + tasks[totalTasks - 1] + "\n"
                + "Now you have " + totalTasks + " tasks in the list.\n" + div);
    }

    private void createDeadline(String description, String by) {
        tasks[totalTasks] = new Deadline(description, by);
        totalTasks++;
        System.out.println(div + "Got it. I've added this task:\n  " + tasks[totalTasks - 1] + "\n"
                + "Now you have " + totalTasks + " tasks in the list.\n" + div);
    }
}
