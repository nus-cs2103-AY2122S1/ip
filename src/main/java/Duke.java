import java.util.Scanner; // import the Scanner class
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<String> items = new ArrayList<String>(100);
        Scanner input = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        boolean end = false;
        String item;
        while (!end) {
            item = input.nextLine();
            if (item.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                end = true;
            } else if (item.equals("list")) {
                int count = 1;
                for (String i : items) {
                    System.out.println(count + ". " + i);
                    count += 1;
                }
            }
            else {
                items.add(item);
                System.out.println("added: " + item);
            }
        }
    }
}
