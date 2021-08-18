import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");
        String input = in.nextLine();
        while(!input.replaceAll("\\s","").toLowerCase().equals("bye")) {
            try {
                String inputLower = input.toLowerCase();
                if (inputLower.replaceAll("\\s", "").equals("list")) {
                    if (list.size() == 0) {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     You have no tasks in your list!");
                    } else {
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            Task item = list.get(i);
                            System.out.println(String.format("     %d. %s", (i + 1), item.toString()));
                        }
                    }
                } else if (input.length() > 3 && inputLower.substring(0, 4).equals("done")) {
                    if (input.replaceAll("\\s", "").length() == 4) {
                        throw new DukeException("     ☹ OOPS!!! You haven't specified the task you have completed.");
                    } else {
                        String modInput = input.replaceAll("\\s", "");
                        Task item = list.get(Integer.parseInt(modInput.substring(modInput.length() - 1)) - 1);
                        item.markAsDone();
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println(String.format("       %s", item.toString()));
                    }
                } else if (input.length() >= 4 && inputLower.substring(0, 4).equals("todo")) {
                    if (input.replaceAll("\\s", "").length() == 4) {
                        throw new DukeException("     ☹ OOPS!!! The description of a todo cannot be empty.");
                    } else {
                        Todo t = new Todo(input.substring(5));
                        list.add(t);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + t.toString());
                        System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                    }
                } else if (input.length() >= 5 && inputLower.substring(0, 5).equals("event")) {
                    if (input.replaceAll("\\s", "").length() == 5) {
                        throw new DukeException("     ☹ OOPS!!! The description of an event cannot be empty.");
                    } else {
                        String[] splitString = input.substring(6).split(" /at ", 2);
                        Event e = new Event(splitString[0], splitString[1]);
                        list.add(e);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + e.toString());
                        System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                    }
                } else if (input.length() >= 8 && inputLower.substring(0, 8).equals("deadline")) {
                    if (input.replaceAll("\\s", "").length() == 8) {
                        throw new DukeException("     ☹ OOPS!!! The description of a deadline cannot be empty.");
                    } else {
                        String[] splitString = input.substring(9).split(" /by ", 2);
                        Deadline d = new Deadline(splitString[0], splitString[1]);
                        list.add(d);
                        System.out.println("    ____________________________________________________________");
                        System.out.println("     Got it. I've added this task:");
                        System.out.println("       " + d.toString());
                        System.out.println(String.format("     Now you have %d tasks in the list.", list.size()));
                    }
                } else {
                    throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                System.out.println("    ____________________________________________________________");
                input = in.nextLine();
            } catch (DukeException e) {
                System.out.println("    ____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("    ____________________________________________________________");
                input = in.nextLine();
            }
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
