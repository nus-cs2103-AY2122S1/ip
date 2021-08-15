import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String line = "____________________________________________________________";
    static String indent = "    ";

    public static void main(String[] args) {
        ArrayList<Task> todos = new ArrayList<>(100);

        Scanner sc = new Scanner(System.in);
        System.out.println(indent + line + "\n" +
                            indent + "Hello, I'm Duke!\n" +
                            indent + "How can I help you?\n" +
                            indent + line);
        String in = sc.nextLine();
        int space = in.indexOf(' ');
        String cmd = space > 0 ? in.substring(0, space) : in;
        String remainder = space > 0 ? in.substring(space + 1) : in;

        while(!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                System.out.println(indent + line + "\n" +
                        indent + "Here are the tasks in your list: ");
                for (int i = 0; i < todos.size(); i++) {
                    System.out.println(indent + indent + (i + 1) + "." + todos.get(i).toString());
                }
                System.out.println(indent + line);
            } else if (cmd.equals("done")) {
                int finishedTaskIndex = Integer.parseInt(remainder);
                Task finishedTask = todos.get(finishedTaskIndex - 1);
                finishedTask.markAsDone();
                System.out.println(indent + line + "\n" +
                                    indent + "Nice! I've marked this task as done: \n" +
                                    indent + indent + finishedTaskIndex + "." + finishedTask.toString() + "\n" +
                                    indent + line);
            } else if (cmd.equals("todo")) {
                Task temp = new Todo(remainder);
                todos.add(temp);
                System.out.println(indent + line + "\n" +
                                    indent + "Ok! A new task has been added:\n" +
                                    indent + indent + temp.toString() + "\n" +
                                    indent + "You now have " + todos.size() + " task(s) in total.\n" +
                                    indent + line);
            } else if (cmd.equals("deadline")) {
                int slash = remainder.indexOf("/");
                String actionName = remainder.substring(0, slash);
                String time = remainder.substring(slash + 4);
                Task temp = new Deadline(actionName, time);
                todos.add(temp);
                System.out.println(indent + line + "\n" +
                                    indent + "Ok! A new task has been added:\n" +
                                    indent + indent + temp.toString() + "\n" +
                                    indent + "You now have " + todos.size() + " task(s) in total.\n" +
                                    indent + line);
            } else if (cmd.equals("event")) {
                int slash = remainder.indexOf("/");
                String actionName = remainder.substring(0, slash);
                String time = remainder.substring(slash + 4);
                Task temp = new Event(actionName, time);
                todos.add(temp);
                System.out.println(indent + line + "\n" +
                                    indent + "Ok! A new task has been added:\n" +
                                    indent + indent + temp.toString() + "\n" +
                                    indent + "You now have " + todos.size() + " task(s) in total.\n" +
                                    indent + line);
            } else {
                System.out.println(indent + line + "\n" +
                                    indent + "I'm sorry, I don't understand. Please try again.\n" +
                                    indent + line);
            }
            in = sc.nextLine();
            space = in.indexOf(' ');
            cmd = space > 0 ? in.substring(0, space) : in;
            remainder = space > 0 ? in.substring(space + 1) : in;
        }
        System.out.println(indent + line + "\n" +
                            indent + "Bye. Hope to see you again soon!\n" +
                            indent + line);
    }
}
