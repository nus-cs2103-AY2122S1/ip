import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static void drawLine() {
        System.out.println("___________________________________________");
    }

    public static void main(String[] args) {
        String s;
        ArrayList<Task> list = new ArrayList<Task>();
        int taskNumber;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println(logo);
        drawLine();
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        drawLine();

        Scanner input = new Scanner(System.in);

        while(true) {
            s = input.nextLine();

            if (s.startsWith("bye")) {
                drawLine();
                System.out.println("\tBye. Hope to see you again soon!");
                drawLine();
                break;
            }

            else if (s.equals("list")) {
                drawLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i).toString());
                }
                drawLine();
                continue;
            }

            else if (s.startsWith("done")) {
                drawLine();
                System.out.println("\tNice! I've marked this task as done:");
                taskNumber = Integer.parseInt(s.substring(s.indexOf(" ") + 1)) - 1;
                list.get(taskNumber).markAsDone();
                System.out.println("\t\t[" + list.get(taskNumber).getStatusIcon() + "] "
                        + list.get(taskNumber).getdescription());
                drawLine();
                continue;
            }

            else if (s.startsWith("todo")) {
                Todo todo = new Todo(s.substring(s.indexOf(" ") + 1));
                list.add(todo);
                drawLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + todo);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                drawLine();
            }

            else if (s.startsWith("deadline")) {
                Deadline deadline = new Deadline(s.substring(s.indexOf(" ") + 1, s.indexOf(" /by")),
                        s.substring(s.indexOf("/by") + 4));
                list.add(deadline);
                drawLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + deadline);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                drawLine();
            }

            else if (s.startsWith("event")) {
                Event event = new Event(s.substring(s.indexOf(" ") + 1, s.indexOf(" /at")),
                        s.substring(s.indexOf("/at") + 4));
                list.add(event);
                drawLine();
                System.out.println("Got it. I've added this task:");
                System.out.println("\t" + event);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                drawLine();
            }
            else {
                drawLine();
                System.out.println("I don't understand. Please try again.");
                drawLine();
            }
        }
        input.close();
    }
}
