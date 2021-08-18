import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<Task> ();

    public static void main(String[] args) {
        echo();
    }

    public static void echo() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();

        while (!s.equals("bye")) {
            if (s.equals("list")) {
                int index = 1;
                
                System.out.println("---------");
                for (Task item : list) {
                    System.out.println(index + ". " + item.toString());
                    index++;
                }
                System.out.println("---------");
            } else if (s.startsWith("done")) {
                int index = Integer.parseInt(s.split(" ")[1]) - 1;
                list.get(index).setStatus(true);

                System.out.println("---------");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list.get(index));
                System.out.println("---------");
            } else {
                // Adding tasks

                if (s.startsWith("todo")) {
                    list.add(new ToDo(s.replace("todo", "").trim()));
                } else if (s.startsWith("deadline")) {
                    String[] data = s.replace("deadline ", "").split("/by");
                    list.add(new Deadline(data[0].trim(), data[1].trim()));
                } else if (s.startsWith("event")) {
                    String[] data = s.replace("event ", "").split("/at");
                    list.add(new Event(data[0].trim(), data[1].trim()));
                } else {
                    list.add(new Task(s));
                }

                int len = list.size();
                System.out.println("---------");
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(len - 1).toString());
                System.out.println("Now you have " + len + " task in the list");
                System.out.println("---------");
            }

            s = in.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        in.close();
        return;
    }
}
