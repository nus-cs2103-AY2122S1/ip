import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Greetings
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        // List to store all user inputs
        List<Task> list = new ArrayList<>();

        // Take in user input
        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (!str.equals("list") && !str.contains("done")) {
                if (str.contains("todo")) list.add(new ToDo(str.substring(5)));
                else if (str.contains("deadline")) {
                    String description = "";
                    String by = "";
                    boolean trigger = false;
                    String[] arr = str.split(" ");
                    for (String s: arr){
                        if (s.equals("deadline")) continue;
                        if (s.equals("/by")) {
                            trigger = true;
                            continue;
                        }
                        if (!trigger) description += s + " ";
                        else by += s + " ";
                    }
                    description = description.substring(0, description.length()-1);
                    by = by.substring(0, by.length()-1);
                    list.add(new Deadline(description, by));
                }
                else if (str.contains("event")) {
                    String description = "";
                    String at = "";
                    boolean trigger = false;
                    String[] arr = str.split(" ");
                    for (String s: arr){
                        if (s.equals("deadline")) continue;
                        if (s.equals("/at")) {
                            trigger = true;
                            continue;
                        }
                        if (!trigger) description += s + " ";
                        else at += s + " ";
                    }
                    description = description.substring(0, description.length()-1);
                    at = at.substring(0, at.length()-1);
                    list.add(new Event(description, at));
                }
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.println("       " + list.get(list.size()-1));
                System.out.println("     Now you have " + list.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            }
            if (str.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (str.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("     " + (i+1) + "." + list.get(i));
                }
                System.out.println("    ____________________________________________________________");
            }
            else if (str.contains("done")){
                int taskNumber = Integer.valueOf(str.substring(5));
                list.get(taskNumber-1).setIsDone(true);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("       " + list.get(taskNumber-1));
                System.out.println("    ____________________________________________________________");
            }
        }
    }
}
