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
        ArrayList<String> storage = new ArrayList<String>();
        while(true) {
            Scanner newInput = new Scanner(System.in);
            String input = newInput.nextLine();
            if (input.equals("bye")) {
                print("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int len = storage.size();
                String sentence = "";
                for (int i = 1; i < len + 1; i++) {
                    sentence = sentence + i + "." + storage.get(i - 1) + "\n";
                }
                print(sentence);
            } else {
                storage.add(input);
                print("added: " + input);
            }
        }
    }

    public static void add() {

    }
}
