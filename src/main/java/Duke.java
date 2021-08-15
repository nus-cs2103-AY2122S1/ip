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
        String item = space > 0 ? in.substring(space + 1) : in;

        while(!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                System.out.println(indent + line + "\n" +
                        indent + "Here are the tasks in your list: ");
                for (int i = 0; i < todos.size(); i++) {
                    System.out.println(indent + indent + (i + 1) + "." + todos.get(i).statusIcon() + " " + todos.get(i).getDesc());
                }
                System.out.println(indent + line);
            } else if (cmd.equals("done")) {
                int finishedTaskIndex = Integer.parseInt(item);
                Task finishedTask = todos.get(finishedTaskIndex - 1);
                finishedTask.markAsDone();
                System.out.println(indent + line + "\n" +
                                    indent + "Nice! I've marked this task as done: \n" +
                                    indent + indent + finishedTaskIndex + "." + finishedTask.statusIcon() + " " + finishedTask.getDesc() + "\n" +
                                    indent + line);
            }else {
                todos.add(new Task(in));
                System.out.println(indent + line + "\n" +
                                    indent + "added: " + in + "\n" +
                                    indent + line);
            }
            in = sc.nextLine();
            space = in.indexOf(' ');
            cmd = space > 0 ? in.substring(0, space) : in;
            item = space > 0 ? in.substring(space + 1) : in;
        }
        System.out.println(indent + line + "\n" +
                            indent + "Bye. Hope to see you again soon!\n" +
                            indent + line);
    }
}
