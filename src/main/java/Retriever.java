import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Chatbot to help manage your daily schedule.
 */
public class Retriever {

    /** To store the user inputs */
    private List<Task> userTaskList = new ArrayList<>();

    /**
     * Returns a boolean suggesting whether the entered String
     * is the word "list" or not.
     *
     * @param userInput The input entered by the user.
     * @return a boolean according to the condition.
     */
    public boolean isItList(String userInput) {
        return userInput.toLowerCase().compareTo("list") == 0;
    }

    /**
     * Returns a boolean suggesting whether the entered String
     * is the word "bye" or not.
     *
     * @param userInput The input entered by the user.
     * @return a boolean according to the condition.
     */
    public boolean isItBye(String userInput) {
        return userInput.toLowerCase().compareTo("bye") == 0;
    }

    /**
     * Returns a boolean suggesting whether the entered String
     * contains the word "done" or not.
     *
     * @param userInput The input entered by the user.
     * @return a boolean according to the condition.
     */
    public boolean isItDone(String userInput) {
        return userInput.toLowerCase().contains("done");
    }

    /**
     * To print the tasks stored in the list.
     */
    public void printList() {
        // If the list is empty
        if(userTaskList.size() == 0) {
            System.out.println("My Memory Is Empty, Please Feed Items!");
        } else {
            System.out.println("-> Your Tasks, My Master:");
            for (int i = 0; i < userTaskList.size(); i++) {
                System.out.println("\t" + ((i + 1) + ". " + userTaskList.get(i)));
            }
        }
    }

    /**
     * To print the task added in a formatted style.
     *
     * @param task The task to be printed.
     */
    public void printTask(Task task) {
        System.out.println("-> Where's My Treat? I Added: \n\t" + task);
        System.out.println("\nYou Owe Me " + userTaskList.size() + " Treat(s), Master!");
    }

    /**
     * To mark a particular task as done in the task list.
     *
     * @param userInput The command entered by the user with the "done" keyword.
     * @throws TaskNotFoundException If the entered task number does not exist.
     * @throws IllegalTaskNumberException If the task number entered is not a number.
     */
    public void taskDone(String userInput) throws TaskNotFoundException, IllegalTaskNumberException {

        // Parsing the user input.
        String[] holder = userInput.split(" ");

        if(holder.length <= 1 || holder[1].compareTo("") == 0
                || !Character.isDigit(holder[1].charAt(0))) {
            throw new IllegalTaskNumberException("Please Enter An Integer Value For Task Number");
        }

        // Obtaining the task number to be marked as done.
        int taskNumber = (Integer.parseInt(holder[1])) - 1;

        // Making sure the task number exists in the list.
        if (taskNumber <= (userTaskList.size() - 1)) {
            Task task = userTaskList.get(taskNumber);
            task.markAsDone();

            System.out.println("Woof! Whose a Good Boy?\n"
                    + "Task Done!\n"
                    + "\t"
                    + task);
        } else {
            throw new TaskNotFoundException("Sorry! The Task Number Entered Does Not Exist!");
        }
    }

    /**
     * To parse and add a deadline type task to the task list.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalDeadlineFormatException If the format for adding a deadline task is not followed.
     */
    public void addDeadline(String userInput) throws IllegalDeadlineFormatException {
        if(!userInput.toLowerCase().contains("/by")
                || userInput.split(" ").length < 4
                || userInput.substring(9).split(" /by ").length < 2) {
            throw new IllegalDeadlineFormatException("Please Follow The Format For Adding A Deadline Task: \n"
                    + "\tdeadline task_description /by date");
        }

        // Parsing the user input to obtain the information about the task.
        String[] userInputArray = userInput.substring(9).split(" /by ");
        Task deadlineTask = new Deadline(userInputArray[0], userInputArray[1]);
        userTaskList.add(deadlineTask);

       printTask(deadlineTask);
    }

    /**
     * To parse and add an event type task to the task list.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalEventFormatException If the format for adding an event task is not followed.
     */
    public void addEvent(String userInput) throws IllegalEventFormatException {
        if(!userInput.toLowerCase().contains("/at")
                || userInput.split(" ").length < 4
                || userInput.substring(5).split(" /at ").length < 2) {
            throw new IllegalEventFormatException("Please Follow The Format For Adding An Event Task: \n"
                    + "\tevent task_description /at time");
        }

        // Parsing the user input to obtain the information about the task.
        String[] userInputArray = userInput.substring(6).split(" /at ");
        Task eventTask = new Event(userInputArray[0], userInputArray[1]);
        userTaskList.add(eventTask);

        printTask(eventTask);
    }

    /**
     * To parse and add a tdod type task to the task list.
     *
     * @param userInput The task details input by the user.
     * @throws IllegalTodoFormatException If the format for adding a todo task is not followed.
     */
    public void addTodo(String userInput) throws IllegalTodoFormatException {
        if(userInput.split(" ").length < 2
                || userInput.substring(5).compareTo("") == 0) {
            throw new IllegalTodoFormatException("Please Follow The Format For Adding A Todo Task: \n"
                    + "\ttodo task_description");
        }

        // Parsing the user input to obtain the information about the task.
        String userInputTodo = userInput.substring(5);
        Task todoTask = new Todo(userInputTodo);
        userTaskList.add(todoTask);

        printTask(todoTask);
    }

    /**
     * Parsing the user input to find out which type of
     * task does the user want to make the entry for.
     *
     * @param userInput The details input by the user.
     * @throws IllegalCommandException If the command entered by the user does not make sense.
     * @throws IllegalDeadlineFormatException If the format for adding a deadline task is not followed.
     * @throws IllegalEventFormatException If the format for adding an event task is not followed.
     * @throws IllegalTodoFormatException If the format for adding a todo task is not followed.
     */
    public void parseUserInput(String userInput) throws IllegalCommandException,
            IllegalDeadlineFormatException, IllegalEventFormatException, IllegalTodoFormatException {

        String[] userInputArray = userInput.split(" ");
        String command = userInputArray[0].toLowerCase();

        switch (command) {
            case "deadline":
                addDeadline(userInput);
                break;
            case "event":
                addEvent(userInput);
                break;
            case "todo":
                addTodo(userInput);
                break;
            default:
                throw new IllegalCommandException("Woof! Command Not Found! Can I Sleep?");
        }
    }

    /**
     * Main body of the Retriever Chatbot.
     */
    public void run() {
        // Set up scanner for user input.
        Scanner sc = new Scanner(System.in);

        // To store the user input string.
        String userInput;

        // Main body which is repeated till the "bye" keyword is encountered.
        do {
            // Taking input
            userInput = sc.nextLine();

            try {
                // Checking if the input is either "list" or "done" or not "bye".
                if (isItList(userInput)) {
                    // Calling the method to print the list.
                    printList();
                } else if (isItDone(userInput)) {
                    // Calling the method to mark a particular task as done.
                    taskDone(userInput);
                } else if (!isItBye(userInput)) {
                    // Here, we need to check if it is an event or a deadline or a todo type of task.
                    parseUserInput(userInput);
                }
            } catch (TaskNotFoundException | IllegalDeadlineFormatException
                    | IllegalEventFormatException | IllegalTodoFormatException
                    | IllegalCommandException | IllegalTaskNumberException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("________________________________________");
        } while(!isItBye(userInput));

        // Closing the scanner.
        sc.close();

        // Printing the good-bye message.
        System.out.println("-> Sad To See You Go!");
        System.out.println("________________________________________");
    }

    /**
     * Returns an interactive session with the Chatbot. Basically a mean to interact with it.
     *
     * @param args Here, usually nothing is passed.
     */
    public static void main(String[] args) {
        // Dog logo made with characters.
        String logo = "  __      ^\n"
                + "o'')}____//\n"
                + " `_'      )\n"
                + "(_(_/-(_/\n";

        // Printing welcome messages.
        System.out.println("________________________________________");
        System.out.println("Hello, I am Retriever\nHow Can I Help You Today?");
        System.out.println(logo);
        System.out.println("________________________________________");

        // Calling the run() method to start the Chatbot.
        new Retriever().run();
    }
}
