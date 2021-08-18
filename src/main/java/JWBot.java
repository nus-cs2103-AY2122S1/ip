import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JWBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Wassup bro! I'm JWBot\n"
                + "How can I help you?\n";
        String byeMessage = "You leaving already? See you soon bro!";
        List<String> items = new ArrayList<>();

        System.out.println(greeting);

        String input = "";

        while(!input.equals("bye")) {
            input = sc.next();
            if (input.equals("bye")) {
                System.out.println(byeMessage);
            } else if (input.equals("list")) {
                for (int i = 1; i < items.size() + 1; i++) {
                    System.out.println(i + ". " + items.get(i - 1));
                }
            } else {
                items.add(input);
                System.out.println("OK bro, we just added: " + input);
            }
        }
    }
}
