import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String end = "bye";
        String display = "list";
        String lineBreak = "------------------------------";
        ArrayList<String> inputs = new ArrayList<>();

        // Welcome message
        System.out.println(lineBreak
                + "\n"
                + "Hello! I'm Azure.\n"
                + "How can I help you today?\n"
                + lineBreak);

        Scanner myObj = new Scanner(System.in);

        // String input
        while(true) {
            String input = myObj.nextLine();

            // normal input
            if (!input.equals(end) && !input.equals(display)) {
                System.out.println("     added: " + input + "\n" + lineBreak + "\n");
                inputs.add(input);
                continue;
            }

            // display saved inputs
            if (input.equals(display)) {
                for (int i = 0; i < inputs.size(); i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + inputs.get(i));
                }
                System.out.println(lineBreak + "\n");
                continue;
            }

            // exit
            System.out.println("     Bye! See you next time! :)" + "\n" + lineBreak + "\n");
            break;
        }
    }
}
