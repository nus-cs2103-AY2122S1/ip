import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List list = new List();
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String end = "    ---------------------------------------------------------------------------------";
        String indentation = "     ";
        System.out.println("Hello from\n" + logo);
        String str = sc.nextLine();

        while (true) {
            if (str.equals("Bye!")) {
                System.out.println(end + "\n" + indentation + "Bye. Hope to see you again soon! :D" + "\n" + end);
                break;
            } else if (str.equals("list")) {
                ArrayList<Task> todos = list.getList();
                todos.trimToSize();
                System.out.println(end);
                for (int i = 0; i < todos.size(); i++) {
                     System.out.println(indentation + (i + 1) + ": " + todos.get(i).taskName);
                }
                System.out.println(end);
            } else {
                Task task = new Task(str);
                list.add(task);
                System.out.println(end + "\n" + indentation + "added: " + task.taskName + "\n" + end);
            }

            str = sc.nextLine();
        }
    }
}
