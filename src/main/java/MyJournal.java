import java.sql.SQLOutput;
import java.util.Scanner;

public class MyJournal {
    public static void main(String[] args) {
        Task[] items = new Task[100];
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
                    System.out.println((i + 1) + ". " + (items[i].getState() ? "[X] ": "[ ] ") + items[i].getTaskName());
                }
            } else if (ans.substring(0,4).equals("done")) {
//                if (reader.hasNextInt()) {
                int index = Integer.parseInt(ans.substring(5, ans.length()), 10) - 1;
//                            reader.nextInt();
//
                if (items[index] == null || index >= count) {
                    System.out.println("That task does not exist");
                    continue;
                } else {
                    items[index].setState(true);
                    System.out.println("Okay!! I have marked this task as done:\n"
                            + "[X] " + items[index].getTaskName());
                }
//                } else {
//                    System.out.println("Which task do you want to mark as done?");
//                }
            } else {
                items[count] = new Task(ans);
                System.out.println("added: " + ans);
                count++;
            }
        }
        System.out.println("Bye. Hope to see you again soon!:)");
    }
}
