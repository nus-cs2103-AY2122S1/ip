import java.util.Scanner;

/**
 * A Chatbot to help manage your daily schedule.
 */
public class Duke {

    /**
     * Returns an interactive session with a Chatbot. Basically a mean to interact with it.
     *
     * @param args Here, usually nothing is passed.
     */
    public static void main(String[] args) {
        String userInput;

        Scanner sc = new Scanner(System.in);

        System.out.println("________________________________________");
        System.out.println("Hello, I am Retriever\nHow Can I Help You Today?");
        System.out.println("________________________________________");

        do {
            userInput = sc.nextLine();
            if(userInput.toLowerCase().compareTo("bye") != 0) {
                System.out.println("-> " + userInput);
            }
            System.out.println("________________________________________");
        } while (userInput.toLowerCase().compareTo("bye") != 0);

        System.out.println("-> Sad To See You Go!");

    }
}
