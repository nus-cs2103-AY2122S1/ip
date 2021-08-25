import java.util.Scanner;

/**
 * Represents a Personal Assistant Chatbot that helps a person keeps track of various things.
 *
 * @author Sherman Ng Wei Sheng
 */
public class Duke {
    private final String name;
    private final TaskList list;

    /**
     * Constructor specifying the name of the Chatbot to be created.
     *
     * @param name Name of the Chatbot.
     */
    public Duke(String name) {
        this.name = name;
        
        TaskList taskList = new TaskList();
        taskList.loadFromStorage();
        this.list = taskList;
    }

    /**
     * Prints the greeting message of the Chatbot.
     */
    private void greet() {
        String message =
                "Hello! I'm " + this.name + "\n" +
                "What can I do for you?";
        printMessage(message);
    }

    /**
     * Prints the goodbye message of the Chatbot.
     */
    private void terminate() {
        String message = "Bye. Hope to see you again soon!";
        printMessage(message);
    }

    /**
     * Adds the given todo task into the list and print a log accordingly.
     * 
     * @param command The input command given by user.
     * @throws IllegalFormatException if the description of a todo is empty.
     */
    private void addTodo(String command) throws IllegalFormatException {
        String[] splitWord = command.split(" ",2);
        if (splitWord.length == 1) {
            throw new IllegalFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        
        String toDoDescription = command.split(" ",2)[1];
        if (toDoDescription.length() == 0) {
            throw new IllegalFormatException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task taskToBeAdded = new ToDo(toDoDescription);
        this.list.add(taskToBeAdded);

        printAddedTaskMessage(taskToBeAdded);
    }

    /**
     * Adds the given deadline task into the list and print a log accordingly.
     *
     * @param command The input command given by user.
     * @throws IllegalFormatException if the description of a deadline is empty or invalid deadline given.
     */
    private void addDeadline(String command) throws IllegalFormatException {
        String[] splitWord = command.split(" ",2);
        if (splitWord.length == 1) {
            throw new IllegalFormatException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        
        String[] splitTask = splitWord[1].split(" /by ", 2);
        String deadlineDescription = splitTask[0];
        if (deadlineDescription.length() == 0) {
            throw new IllegalFormatException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        if (splitTask.length == 1) {
            throw new IllegalFormatException("☹ OOPS!!! Please specify the deadline date/time in the correct format.");
        }
        
        String deadlineDateTime = splitTask[1];
        if (deadlineDateTime.length() == 0) {
            throw new IllegalFormatException("☹ OOPS!!! Please specify the deadline date/time in the correct format.");
        }
  
        Task taskToBeAdded = new Deadline(deadlineDescription, deadlineDateTime);
        this.list.add(taskToBeAdded);

        printAddedTaskMessage(taskToBeAdded);
    }

    /**
     * Adds the given event task into the list and print a log accordingly.
     *
     * @param command The input command given by user.
     * @throws IllegalFormatException if the description of a deadline is empty or invalid deadline given.
     */
    private void addEvent(String command) throws IllegalFormatException {
        String[] splitWord = command.split(" ",2);
        if (splitWord.length == 1) {
            throw new IllegalFormatException("☹ OOPS!!! The description of a event cannot be empty.");
        }

        String[] splitTask = splitWord[1].split(" /at ", 2);
        String eventDescription = splitTask[0];
        if (eventDescription.length() == 0) {
            throw new IllegalFormatException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        if (splitTask.length == 1) {
            throw new IllegalFormatException("☹ OOPS!!! Please specify the event date/time in the correct format.");
        }

        String eventDateTime = splitTask[1];
        if (eventDateTime.length() == 0) {
            throw new IllegalFormatException("☹ OOPS!!! Please specify the event date/time in the correct format.");
        }

        Task taskToBeAdded = new Event(eventDescription, eventDateTime);
        this.list.add(taskToBeAdded);

        printAddedTaskMessage(taskToBeAdded);
    }

    /**
     * Checks if a given string is a command that denotes that a task is done.
     * If it is a valid Done command, the corresponding task will be marked done.
     * If it is a Done command with an incorrect index, an error prompt will be displayed.
     * For all other command received, returns false.
     * 
     * @param command The string corresponding to the input given by the user in the command line.
     * @throws InvalidIndexException thrown if an invalid index is provided by the user.
     * @throws IllegalFormatException thrown if the format for marking task to be done is wrong.
     */
    private void handleDoneCommand(String command) throws InvalidIndexException, IllegalFormatException {
        String[] splitWord = command.split(" ");
        
        if (splitWord.length != 2) {
            throw new IllegalFormatException("☹ OOPS!!! Please specify task to be mark done in the correct format.");
        }

        String secondWord = splitWord[1];
        int secondWordLength = secondWord.length();
        for (int i = 0; i < secondWordLength; i++) {
            if (!Character.isDigit(secondWord.charAt(i))) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify the task to be mark done as a number.");
            }
        }
        int taskToBeMarkDone = Integer.parseInt(secondWord) - 1;
        boolean markedDone = this.list.markDoneAtIndex(taskToBeMarkDone);
        if (markedDone) {
            String message =
                    "Nice! I've marked this task as done:\n" +
                    this.list.get(taskToBeMarkDone);
            printMessage(message);
        } else {
            throw new InvalidIndexException();
        }
    }

    /**
     * Removes the task based on the command given.
     * 
     * @param command The input string by the user.
     * @throws InvalidIndexException thrown when user provides an index that is out of the range of the list.
     * @throws IllegalFormatException thrown when the user do not follow the format for deleting
     */
    private void removeTask(String command) throws InvalidIndexException, IllegalFormatException {
        String[] splitWord = command.split(" ");

        if (splitWord.length != 2) {
            throw new IllegalFormatException("☹ OOPS!!! Please specify task to be removed in the correct format.");
        }

        String secondWord = splitWord[1];
        int secondWordLength = secondWord.length();
        for (int i = 0; i < secondWordLength; i++) {
            if (!Character.isDigit(secondWord.charAt(i))) {
                throw new IllegalFormatException("☹ OOPS!!! Please specify the task to be mark done as a number.");
            }
        }
        int taskIndexToBeRemoved = Integer.parseInt(secondWord) - 1;
        Task removed = this.list.deleteAtIndex(taskIndexToBeRemoved);
        if (removed != null) {
            String message =
                    "Noted.I've removed this task:\n" +
                    "  " + removed + "\n" +
                    String.format("Now you have %d tasks in the list.", this.list.size());
            printMessage(message);
        } else {
            throw new InvalidIndexException();
        }
    }

    /**
     * Prints the formatted list of content in <code>list</code>.
     */
    private void printList() {
        String message = this.list.generateMessage();
        printMessage(message);
    }
    
    /**
     * Prints the log for task object being added
     *
     * @param task The task object to be printed.
     */
    private void printAddedTaskMessage(Task task) {
        String message =
                "Got it. I've added this task:\n" +
                        "  " + task + "\n" +
                        String.format("Now you have %d tasks in the list.", this.list.size());
        printMessage(message);
    }
    
    /**
     * Prints the input content to console with formatting.
     * 
     * @param content The content to be printed, wrapped between horizontal lines.
     */
    private void printMessage(String content) {
        String format = 
                "        ____________________________________________________________\n" + 
                "        %s\n" + 
                "        ____________________________________________________________\n";
        System.out.printf(format, content.replaceAll("\n", "\n        "));
    }

    /**
     * Starts the Chatbot and listens for user input.
     *
     * @param sc Scanner object to listen for user input.
     */
    public void start(Scanner sc) {
        this.greet();

        while (true) {
            try {
                String input = sc.nextLine();
                String[] splitWord = input.split(" ", 2);
                String firstWord = splitWord[0];

                if (input.equals("bye")) {
                    this.terminate();
                    return;
                } else if (input.equals("list")) {
                    this.printList();
                } else if (firstWord.equals("done")) {
                    this.handleDoneCommand(input);
                } else if (firstWord.equals("todo")) {
                    this.addTodo(input);
                } else if (firstWord.equals("deadline")) {
                    this.addDeadline(input);
                } else if (firstWord.equals("event")) {
                    this.addEvent(input);
                } else if (firstWord.equals("delete")) {
                    this.removeTask(input);
                } else {
                    throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                printMessage(e.getMessage());
            }
        }
    }

    /**
     * Initializes a Duke object and runs the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke("Duke");

        duke.start(sc);
        sc.close();
    }
}
