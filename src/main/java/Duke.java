import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List list = new List();
        String command;
        String line = "    ____________________________________________________________\n";
        System.out.println(line + "     Hello! I'm Duke\n" + "     What can I do for you?\n" + line);
        command = sc.next();
        while (!command.equals("bye")) {
            Task task;
            switch(command) {
            case "list":
                System.out.println(line + list.returnItems() + line);
                break;
            case "done":
                int index = sc.nextInt();
                System.out.println(line + "     Nice! I've marked this task as done:\n"
                        + "       " + list.markDone(index) + "\n" + line);
                break;
            case "todo":
                command = sc.nextLine();
                task = new Todo(command);
                list.addItem(task);
                System.out.println(line + "     Got it. I've added this task:\n"
                        + "       " + task + "\n" + list.returnItemCount() + "\n" + line);
                break;
            case "deadline":
                command = sc.nextLine();
                String[] deadlineEntry = command.split(" /by ");
                task = new Deadline(deadlineEntry[0], deadlineEntry[1]);
                list.addItem(task);
                System.out.println(line + "     Got it. I've added this task:\n"
                        + "       " + task + "\n" + list.returnItemCount() + "\n" + line);
                break;
            case "event":
                command = sc.nextLine();
                String[] eventEntry = command.split(" /at ");
                task = new Event(eventEntry[0], eventEntry[1]);
                list.addItem(task);
                System.out.println(line + "     Got it. I've added this task:\n"
                        + "       " + task + "\n" + list.returnItemCount() + "\n" + line);
                break;
            }
            command = sc.next();
        }
        System.out.println(line + "     Bye. Hope to see you again soon!\n" + line);
    }
}