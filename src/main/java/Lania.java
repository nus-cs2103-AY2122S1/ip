import java.util.Scanner;

public class Lania {

    /** String array containing user inputs */
    private static String[] strArray = new String[100];
    /** Keep track of number of user inputs */
    private static int count = 0;

    /**
     * Store user input in array and show that it is added.
     *
     * @param s String provided by the user.
     */
    public static void update(String s) {
        strArray[count] = s;
        count++;
        System.out.print("added: ");
        echo(s);
    }

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
            update(input);
            input = s.nextLine();
        }
        System.out.println("Bye. Lania looks forward to seeing you again!");
    }
}
