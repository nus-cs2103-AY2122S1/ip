import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JWBot {
    public static void main(String[] args) throws JWBotException {
        Scanner sc = new Scanner(System.in);

        String greeting = "Wassup bro! I'm JWBot\n"
                + "How can I help you?\n";
        String byeMessage = "You leaving already? See you soon bro!";
        List<Task> items = new ArrayList<>();

        System.out.println(greeting);

        String input = "";

        while(!input.equals("bye")) {
            try {
                input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println(byeMessage);
                } else if (input.equals("list")) {
                    System.out.println("OK bro, the tasks in your list are: ");
                    for (int i = 1; i < items.size() + 1; i++) {
                        System.out.println(i + ". " + items.get(i - 1));
                    }
                    System.out.println("Bro, now you have " + items.size() + " task(s) stored in the list!");
                } else if (input.startsWith("done ")) {
                    try {
                        String[] separated = input.split(" ");
                        int index = Integer.parseInt(separated[1]);
                        items.get(index - 1).markAsDone();
                        System.out.println("OK Bro, I noted you've done this task:\n" +
                                items.get(index - 1));
                    } catch (Exception e) {
                        throw new JWBotException("Sorry bro, I think you chose an incorrect index number!");
                    }
                } else if (input.startsWith("deadline ")) {
                    try {
                        String content = input.split(" ", 2)[1];
                        String[] separated = content.split(" /by ");
                        Deadline deadline = new Deadline(separated[0], separated[1]);
                        items.add(deadline);
                        System.out.println("OK bro, I just added: " + deadline);
                    } catch (Exception e) {
                        throw new JWBotException("Sorry bro, I think you made an error with the deadline format!");
                    }
                } else if (input.startsWith("todo ")) {
                    String content = input.split(" ", 2)[1];
                    Todo todo = new Todo(content);
                    items.add(todo);
                    System.out.println("OK bro, I just added: " + todo);
                } else if (input.startsWith("event ")) {
                    try {
                        String content = input.split(" ", 2)[1];
                        String[] separated = content.split(" /at ");
                        Event event = new Event(separated[0], separated[1]);
                        items.add(event);
                        System.out.println("OK bro, I just added: " + event);
                    } catch (Exception e) {
                        throw new JWBotException("Sorry bro, I think you made an error with the event format!");
                    }
                } else {
                        throw new JWBotException("Sorry bro, I don't understand what you mean!");
                }
            } catch (JWBotException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
