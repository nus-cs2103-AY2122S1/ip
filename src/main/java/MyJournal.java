import java.sql.SQLOutput;
import java.util.Scanner;

public class MyJournal {
    public static void main(String[] args) {
        Task[] items = new Task[100];
        String ans = "";
        Scanner reader = new Scanner(System.in);
        int count = 0;
        System.out.println("Hello!\n"
                + "1. Type a task (todo/event/deadline) to be added into your task list.\n"
                + "2. Type 'list' if you want to generate your task list.\n"
                + "3. Type 'bye' to exit");
        while (true) {
            ans = reader.nextLine();
            String thisLine = ans;
            Scanner line = new Scanner(thisLine);
            String firstWord = line.next();
            if (ans.equals("bye")) {
                break;
            } else if (firstWord.equals("done")) {
                if (line.hasNextInt()) {
                    int index = line.nextInt() - 1;
                    if (index >= count || index < 0 || items[index] == null) {
                        System.out.println("That task does not exist");
                    } else {
                        items[index].setState(true);
                        System.out.println("Okay!! I have marked this task as done:\n"
                                    + items[index].getSymbol() + " [X] " + items[index].getTaskName());
                    }
                } else {
                    System.out.println("Input insufficient. Please specify the task that needs to be marked as done.");
                }
            } else if (firstWord.equals("list")) {
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + items[i].getSymbol() + " " + (items[i].getState()
                            ? "[X] " : "[ ] ") + items[i].getTaskName());
                }
            } else {
                if (firstWord.equals("todo")) {
                    items[count] = new Todo(ans);
                    count++;
                    System.out.println("Okay!! I've added the following task:\n"
                            + "[T] [ ] " + items[count - 1] + "\n"
                            + "Now you have " + count + " in the list");
                } else if (firstWord.equals("event")) {
                    items[count] = new Event(ans);
                    count++;
                    System.out.println("Okay!! I've added the following task:\n"
                            + "[E] [ ] " + items[count - 1] + "\n"
                            + "Now you have " + count + " in the list");
                } else if (firstWord.equals("deadline")){
                    items[count] = new Deadline(ans);
                    count++;
                    System.out.println("Okay!! I've added the following task:\n"
                            + "[D] [ ] " + items[count - 1] + "\n"
                            + "Now you have " + count + " in the list");
                } else {
                    System.out.println("Please put either Todo, Event or Deadline");
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!:)");
    }
}
