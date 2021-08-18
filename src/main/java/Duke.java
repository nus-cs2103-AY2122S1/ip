import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String starter = "   (=^ ･ｪ･^=) < ";
        String buffer =  "                ";

        // Welcome text
        System.out.println(starter + "Hello! I'm TiTi~ ");
        System.out.println(buffer + "What would you like to do nya? ");

        String input = sc.nextLine();
        while (true) {
            // exit cue
            if (input.equals("bye")) {
                System.out.println(starter + "Already done? Come back again soon nya~");
                break;
            }

            // display list
            if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        System.out.println(starter + (i + 1) + ". " + list.get(i));
                    } else {
                        System.out.println(buffer + (i + 1) + ". " + list.get(i));
                    }
                }
                input = sc.nextLine();
                continue;
            }

            // add input to list
            System.out.println(starter + "added: " + input);
            list.add(input);
            input = sc.nextLine();
        }
    }
}
