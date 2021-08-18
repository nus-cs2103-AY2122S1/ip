import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JWBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "Wassup bro! I'm JWBot\n"
                + "How can I help you?\n";
        String byeMessage = "You leaving already? See you soon bro!";
        List<Task> items = new ArrayList<>();

        System.out.println(greeting);

        String input = "";

        while(!input.equals("bye")) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(byeMessage);
            } else if (input.equals("list")) {
                System.out.println("OK bro, the tasks in your list are: ");
                for (int i = 1; i < items.size() + 1; i++) {
                    System.out.println(i + ". " + items.get(i - 1).getStatus());
                }
            } else if (input.startsWith("done ")) {
                String[] separated = input.split(" ");
                int index = Integer.parseInt(separated[1]);
                items.get(index - 1).markAsDone();
                System.out.println("OK Bro, I noted you've done this task:\n" +
                        items.get(index - 1).getStatus());
            } else {
                items.add(new Task(input));
                System.out.println("OK bro, we just added: " + input);
            }
        }
    }
}
