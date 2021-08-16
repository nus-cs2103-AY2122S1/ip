import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents a personal assistant chatbot that responds to command line inputs.
 *
 * @author felix-ong
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, I am Duke!(≧◡≦)\n" + "How may I help you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<>();

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye! Feel free to ask me for help again anytime! (≧▽≦)/");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, inputs.get(i));
                }
            } else {
                inputs.add(input);
                System.out.println("Added: " + input);
            }
        }
    }
}
