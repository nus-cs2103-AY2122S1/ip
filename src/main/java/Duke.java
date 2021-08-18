import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot that helps a person keeps track of various things.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Duke {
    private String name;
    private TaskList list;

    /**
     * Class constructor specifying the name of the Chatbot to be created.
     *
     * @param name Name of the Chatbot.
     */
    public Duke(String name) {
        this.name = name;
        this.list = new TaskList();
    }

    /**
     * Print the greeting message of the Chatbot.
     */
    private void greet() {
        String message = 
                "    ____________________________________________________________\n" + 
                "    Hello! I'm " + this.name + "\n" +
                "    What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(message);
    }

    /**
     * Print the goodbye message of the Chatbot.
     */
    private void terminate() {
        String message = 
                "    ____________________________________________________________\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";
        System.out.println(message);
    }

    /**
     * Add the given task into the list and print a log accordingly.
     *
     * @param task The Task object to be added.
     */
    private void addTask(Task task) {
        this.list.add(task);
        String message =
                "    ____________________________________________________________\n" +
                "    Got it. I've added this task:\n" +
                "      " + task + "\n" +
                String.format("    Now you have %d tasks in the list.\n", this.list.size()) +        
                "    ____________________________________________________________";
        System.out.println(message);
    }

    /**
     * Prints the formatted list of content in <code>list</code>.
     */
    private void printList() {
        int listSize = this.list.size();
        
        System.out.println("    ____________________________________________________________");
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < listSize; i++) {
            int index = i + 1;
            Task content = this.list.get(i);
            System.out.println("    " + index + "." + content);
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Check if a given string is a command that denotes that a task is done.
     * If it is a valid Done command, the corresponding task will be marked done and returns true.
     * If it is a Done command with an incorrect index, an error prompt will be displayed and returns true.
     * For all other command received, returns false.
     * 
     * @param command The string corresponding to the input given by the user in the command line.
     * @return A boolean value, true if it is a Done command and false otherwise.
     */
    private boolean handleDoneCommand(String command) {
        String[] splitWord = command.split(" ", 2);
        
        if (splitWord.length != 2) {
            return false;
        }
        String firstWord = splitWord[0];
        String secondWord = splitWord[1];
        int secondWordLength = secondWord.length();
        
        if (firstWord.equals("done")) {
            for (int i = 0; i < secondWordLength; i++) {
                if (Character.isDigit(secondWord.charAt(i))) {
                    continue;
                } else {
                    return false;
                }
            }
            int taskToBeMarkDone = Integer.parseInt(secondWord) - 1;
            boolean markedDone = this.list.markDoneAtIndex(taskToBeMarkDone);
            if (markedDone) {
                String message =
                        "    ____________________________________________________________\n" +
                        "    Nice! I've marked this task as done:\n" +
                        "      " + this.list.get(taskToBeMarkDone) + "\n" +
                        "    ____________________________________________________________";
                System.out.println(message);
            } else {
                String message =
                        "    ____________________________________________________________\n" +
                        "    Kindly key in a valid index to be marked done.\n" +
                        "    ____________________________________________________________";
                System.out.println(message);
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to start the Chatbot and listen for user input.
     *
     * @param sc Scanner object to listen for user input.
     */
    public void start(Scanner sc) {
        this.greet();

        while (true) {
            String input = sc.nextLine();
            String[] splitWord = input.split(" ", 2);
            String firstWord = splitWord[0];
            
            switch (firstWord) {
                case "bye":
                    this.terminate();
                    return;
                case "list":
                    this.printList();
                    break;
                case "done":
                    boolean isDoneCommand = handleDoneCommand(input);
                    if (isDoneCommand) {
                        break;
                    }
                case "todo":
                    String toDoDescription = splitWord[1];
                    this.addTask(new ToDo(toDoDescription));
                    break;
                case "deadline":
                    String deadlineDescription = splitWord[1].split(" /by ", 2)[0];
                    String deadlineDateTime = splitWord[1].split(" /by ", 2)[1];
                    this.addTask(new Deadline(deadlineDescription, deadlineDateTime));
                    break;
                case "event":
                    String eventDescription = splitWord[1].split(" /at ", 2)[0];
                    String eventDateTime = splitWord[1].split(" /at ", 2)[1];
                    this.addTask(new Event(eventDescription, eventDateTime));
                    break;
            }
        }
    }

    /**
     * Main method to initialise a Duke object.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke("Duke");

        duke.start(sc);
    }
}
