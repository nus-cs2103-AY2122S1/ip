import java.util.Scanner;

public class Duke {

    /**
     * Prints the user input.
     *
     * @param s String provided by the user.
     */
    public static void echo(String s) {
        System.out.println(s);
    }

    /**
     * Chatbot that echoes the user input unless 'bye' is given as input
     *
     * @param args The command line arguments. Not required here.
     **/
    public static void main(String[] args) {
        System.out.println("Hello I am Lania! How may I be of assistance?");
        System.out.println("Enter 'bye' to exit");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();
        while(!input.equals("bye")) {
            echo(input);
            input = s.nextLine();
        }
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }
}
