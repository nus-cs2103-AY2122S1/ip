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
     * Echoes whatever is passed into <code>input</code> as argument with formatting and
     * add the Task to the list.
     *
     * @param input The description of the Task to be added.
     */
    private void addTask(String input) {
        Task taskToBeAdded = new Task(input);
        this.list.add(taskToBeAdded);
        String message =
                "    ____________________________________________________________\n" +
                "    added: " + input + "\n" +
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
            boolean isDoneCommand = handleDoneCommand(input);
            if (isDoneCommand) {
                continue;
            }
            switch (input) {
                case "bye":
                    this.terminate();
                    return;
                case "list":
                    this.printList();
                    break;
                default:
                    this.addTask(input);
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
