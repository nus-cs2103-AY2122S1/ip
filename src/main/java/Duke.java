import java.util.Scanner;

/**
 * Represents a personal assistant chatbot that responds to command line inputs.
 *
 * @author felix-ong
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke!(≧◡≦)\n" + "How may I help you?");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye! Feel free to ask me for help again anytime! (≧▽≦)/");
                break;
            } else {
                System.out.println(command);
            }
        }
    }
}
