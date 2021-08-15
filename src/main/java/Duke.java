import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Greet
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?\n");

        ArrayList<String> task = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 1; i < task.size() + 1; i++) {
                    System.out.println(i + ". " + task.get(i - 1));
                }
            } else {
                task.add(input);
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");

    }
}
