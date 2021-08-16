import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Chatbot to help manage your daily schedule.
 */
public class Retriever {

    /** To store the user inputs */
    private List<String> userInputStorage = new ArrayList<>();

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
     * To print the user inputs that have been stored.
     */
    public void printList() {
        // If the list is empty
        if(userInputStorage.size() == 0) {
            System.out.println("My Memory Is Empty, Please Feed Items!");
        } else {
            System.out.println("->");
            for (int i = 0; i < userInputStorage.size(); i++) {
                System.out.println("\t" + ((i + 1) + ". " + userInputStorage.get(i)));
            }
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

            // Checking if the input is either "list" or not "bye".
            if(isItList(userInput)) {
                // Calling the method to print the list.
                printList();
            } else if(!isItBye(userInput)) {
                // Adding the user input to the list.
                userInputStorage.add(userInput);

                System.out.println("-> Added: " + userInput);
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
     * Returns an interactive session with a Chatbot. Basically a mean to interact with it.
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
