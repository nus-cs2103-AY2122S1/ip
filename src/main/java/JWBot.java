import java.util.Scanner;

public class JWBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Wassup bro! I'm JWBot\n"
                + "How can I help you?\n";
        String byeMessage = "You leaving already? See you soon bro!";

        System.out.println(greeting);

        String input = "";

        while(!input.equals("bye")) {
            input = sc.next();
            if (input.equals("bye")) {
                System.out.println(byeMessage);
            } else {
                System.out.println(input);
            }
        }
    }
}
