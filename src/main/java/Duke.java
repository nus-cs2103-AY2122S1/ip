import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        ArrayList<String> l = new ArrayList<String>();

        System.out.println(
                "Yo! Duke here \n"
                + "What did you call me for? \n"
                + "It better be something useful or else... \n"
        );

        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int listLength = l.size();
                if (listLength == 0) {
                    System.out.println("Your list is empty.");
                } else {
                    System.out.println("Your list items:");
                    for (int i = 0; i < listLength; i ++) {
                        System.out.printf("%d. %s \n", i + 1, l.get(i));
                    }
                }

            } else {
                l.add(input);
                System.out.println("Added `" + input + "` to your list");
            }
            input = s.nextLine();
        }

        System.out.println("Good riddance! Time to continue my beauty sleep :)");
    }
}
