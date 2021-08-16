import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input = "";
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Amped");
        System.out.println("What can I do for you?");
        List<String> ls = new ArrayList<>();
        do {
            input = scan.nextLine();
            if (input.equals("bye")) {
                System.out.println("Good Bye. Have a nice day!");
            }
            else if (input.equals("list")) {
                for(int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + ". " + ls.get(i));
                }
            }
            else {
                System.out.println("added: " + input);
                ls.add(input);
            }
        } while (!input.equals("bye"));
    }
}
