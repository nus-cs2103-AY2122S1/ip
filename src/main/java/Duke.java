import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final List<String> list = new ArrayList<>();

    private static void addToList(String string) {
        list.add(string);
        System.out.println("added: " + string);
    }

    private static void listTasks() {
        if (list.size() < 1) {
            System.out.println("You haven't added anything to the list yet! Here, add something.");
        } else {
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                listTasks();
                continue;
            } else {
                addToList(input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
