import java.io.*;
import java.util.*;

public class Duke {
    private final static String LINE = "-----------------------------------------";
    private final static String DEFAULT_MESSAGE = LINE + "\nHello! I'm Duke \nWhat can I do for you?\n" + LINE;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println(DEFAULT_MESSAGE);
        String task = sc.nextLine();
        Store.readFromFile();

        while(!task.equals("bye")) {
            try {
                if (task.equals("list")) {
                    TaskList.printList();
                } else if (task.startsWith("done")) {
                    TaskList.complete(task);
                    Store.overwrite();
                } else if (task.startsWith("deadline") || task.startsWith("event") || task.startsWith("todo")) {
                    Store.writeToFile(task);
                } else if (task.startsWith("delete")) {
                    String numString = task.replaceAll("[^0-9]", "");
                    int num = Integer.parseInt(numString);
                    TaskList.deleteTask(num);
                    Store.overwrite();
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            task = sc.nextLine();
        }
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
        sc.close();
    }
}