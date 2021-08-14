import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        // Introduction message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        drawLine();

        //Initialise list to store todos and Scanner to get user input
        List<String> todos = new ArrayList<>(100);
        Scanner sc = new Scanner(System.in);

        // Main Code
        while(true) {
            String userInput = sc.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                dukeReply("Bye. Hope to see you again soon!");
                break;
            } else if(userInput.equalsIgnoreCase("list")) {
                showTodos(todos);
            }else {
                todos.add(userInput);
                dukeReply("added: " + userInput);

            }
        }
    }

    /**
     * Prints the list of todos sequentially
     * @param todos List of current todos
     */
    public static void showTodos(List<String> todos) {
        int x = 1;
        drawLine();
        for( int i = 0; i < todos.size(); i++) {
            System.out.printf("      %d. %s\n", i + 1, todos.get(i));
        }
        drawLine();
    }

    /**
     * Prints dukes reply in between two lines
     * @param message Duke's reply
     */
    public static void dukeReply(String message) {
        drawLine();
        System.out.println("      " + message);
        drawLine();
    }

    /**
     * Draws a line on the screen to separate speech.
     */
    public static void drawLine() {
        System.out.println("------------------------");
    }

}
