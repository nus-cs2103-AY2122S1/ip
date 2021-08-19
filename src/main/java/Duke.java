import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private  static Scanner sc;
    private static ArrayList<Task> userTasks = new ArrayList<>();
    private static String bye_input = "bye";
    private static String bye_output = "Bye. Hope to see you again soon!";
    private static String list_input = "list";
    private static String done_input = "done";
    private static String indent = "    ";
    private static String div_line = "    ____________________________________________________________";

    public static void main(String[] args) throws DukeException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet();
        sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next_input = sc.nextLine();
            String[] arr = next_input.split(" ", 2);

            if (next_input.equals(bye_input)) {
                exit();
                break;
            } else if (next_input.equals(list_input)){
                printAllTasks();
            } else if (arr[0].equals(done_input)) {
                if (arr.length == 1) {
                    throw new DukeException("Enter task no. to complete the task.");
                } else {
                    try {
                        int i = Integer.parseInt(arr[1]) - 1;
                        userTasks.get(i).markDone();
                        echo("Nice! I have marked this task as done:\n" + indent + userTasks.get(i));
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        echo("Oops! " + "Enter a valid task no. to complete the task.");
                    }
                }
            } else {
                Task t;
                if (arr[0].equals("todo")) {
                    if (arr.length == 1 || arr[1].trim().length() == 0) {
                        throw new DukeException("Invalid TODO entry. Description of a TODO cannot be empty.");
                    } else { t = new ToDo(arr[1]); }
                } else if (arr.length > 1 && arr[0].equals("event")){
                    String[] detail = arr[1].split("/at ", 2);
                    if (detail.length == 1 || detail[1].trim().length() == 0 || detail[0].trim().length() == 0) {
                        throw new DukeException("Invalid Event entry. Try something like: Meeting /at 2pm");
                    } else { t = new Event(detail[0], detail[1]); }
                } else if (arr.length > 1 && arr[0].equals("deadline")){
                    String[] detail = arr[1].split("/by ", 2);
                    if (detail.length == 1 || detail[1].trim().length() == 0 || detail[0].trim().length() == 0) {
                        throw new DukeException("Invalid Deadline entry.Try something like : HW due /by 2pm");
                    } else { t = new Deadline(detail[0], detail[1]); }
                } else {
                    throw new DukeException("Invalid entry. I'm sorry, I don't know what that means :(");
                }

                if (t != null) {
                    userTasks.add(t);
                    echo("Got it. I've added this task:\n" + indent + t + "\n" + indent +
                            "Now you have " + userTasks.size() + " tasks in the list.");
                }
            }
        }
    }

    private static void echo(String next_input) {
        System.out.println(div_line + "\n" + indent + next_input + "\n" + div_line);
    }

    private static void printAllTasks() {
        System.out.println(div_line);
        System.out.println(indent + "Here are the tasks in your list:");
        for (int i = 1; i < userTasks.size() + 1; i++) {
            System.out.println(indent + i + " " + userTasks.get(i-1));
        }
        System.out.println(div_line);
    }

    private static void exit() {
        echo(bye_output);
    }

    private static void greet() {
        String line1 = "Hello! I'm Duke\n";
        String line2 = indent + "What can I do for you?";
        echo(line1 + line2);
    }
}
