import java.sql.SQLOutput;
import java.util.Scanner;

public class MyJournal {
    public static void main(String[] args) {
        String[] items = new String[100];
        String ans = "";
        Scanner reader = new Scanner(System.in);
        int count = 0;
        System.out.println("Hello!\n"
                + "1. Type a task to be added into your todo list.\n"
                + "2. Type 'list' if you want to generate your todo list.\n"
                + "3. Type 'bye' to exit");
        while (true) {
            ans = reader.nextLine();
            if (ans.equals("bye")) {
                break;
            } else if (ans.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + items[i]);
                }
            } else {
                items[count] = ans;
                System.out.println("added: " + ans);
                count++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!:)");
    }
}
