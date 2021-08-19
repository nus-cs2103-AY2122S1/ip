import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> list = new ArrayList<String>();

    public static void printList() {
        for (int i = 0; i < list.size(); i++)
        {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Jacky\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
                input = sc.nextLine();
                continue;
            }
            list.add(input);
            System.out.println("added: " + input);
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
