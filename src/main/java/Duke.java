import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        run();
    }

    public static void print(String text) {
        System.out.println("=======================================");
        text.lines().map(x -> "    " + x).forEach(x -> System.out.println(x));
        System.out.println("=======================================");
    }

    public static void run() {
        print("Hello! My name is Alexa \nHow can I help you today?");

        while(true) {
            Scanner newInput = new Scanner(System.in);
            String input = newInput.nextLine();
            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            } else {
                print(input);
            }
        }
    }

    public static void add() {

    }
}
