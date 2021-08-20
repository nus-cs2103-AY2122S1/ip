import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        while(true) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                int i = 1;
                for (String item : list) {
                    System.out.println(i + ". " + item);
                    i++;
                }

            }
            else {
                list.add(input);
                System.out.println("Added: " + input);
            }
        }
    }
}
