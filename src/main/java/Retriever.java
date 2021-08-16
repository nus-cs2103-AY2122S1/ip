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
     * To print the user inputs that have been stored.
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
     * Adds a task with the given description to the list.
     *
     * @param description The description of the task to be added.
     */
    public void addTask(String description) {

        // Creating a new Task with the description.
        Task task = new Task(description);
        userTaskList.add(task);

        System.out.println("-> Added: " + description);
    }

    /**
     * To mark a particular task as done in the task list.
     *
     * @param userInput The command entered by the user with the "done" keyword.
     */
    public void taskDone(String userInput) {

        // Parsing the user input.
        String[] holder = userInput.split(" ");

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
            System.out.println("Sorry! The Task Number Entered Does Not Exist!");
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

            // Checking if the input is either "list" or "done" or not "bye".
            if(isItList(userInput)) {
                // Calling the method to print the list.
                printList();
            } else if(isItDone(userInput)) {
                // Calling the method to mark a particular task as done.
                taskDone(userInput);
            } else if(!isItBye(userInput)) {
                // Adding the user input to the task list.
                addTask(userInput);
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
