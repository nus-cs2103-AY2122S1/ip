import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {

    public static String getDescription(String[] arr) {
        String str = "";
        for(int i = 1; i < arr.length; i++) {
            if (!(arr[i].charAt(0) == '/')) {
                str += arr[i] + " ";
            }
            else {
                break;
            }
        }
        return str;
    }

    public static String getDeadline(String[] arr) {
        String str = "";
        boolean bool = false;
        for(int i = 1; i < arr.length; i++) {
            if (arr[i].charAt(0) == '/') {
                bool = true;
            }
            else if (bool && i == arr.length - 1) {
                str += arr[i];
            }
            else if (bool) {
                str += arr[i] + " ";
            }

        }
        return str;
    }

    public static void main(String[] args) {
        String input = "";
        String[] arr;
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello! I'm Amped");
        System.out.println("What can I do for you?");
        List<Task> ls = new ArrayList<>();
        do {
            input = scan.nextLine();
            arr = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Good Bye. Have a nice day!");
            }
            else if (arr.length == 2) {
                if (arr[0].equals("done")
                        && Integer.parseInt(arr[1]) <= ls.size()
                        && Integer.parseInt(arr[1]) > 0) {
                    System.out.println("Nice! I've marked this task as done: ");
                    ls.get(parseInt(arr[1]) - 1).markAsDone();
                    System.out.println(ls.get(parseInt(arr[1]) - 1).toString());
                }
                else if (arr[0].equals("done") && ls.size() == 0) {
                    System.out.println("You have not added any task!");
                }
                else {
                    System.out.println("Enter a valid number!");
                }
            }
            else if (arr.length > 2) {
                if (arr[0].equals("todo")) {
                    System.out.println("Got it. I've added this task: ");
                    ls.add(new Todo(arr[0]));
                    System.out.println(ls.get(ls.size() - 1).toString());
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                }
                else if (arr[0].equals("deadline")) {
                    System.out.println("Got it. I've added this task: ");
                    ls.add(new Deadline(getDescription(arr), getDeadline(arr)));
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                }
                else if (arr[0].equals("event")) {
                    System.out.println("Got it. I've added this task: ");
                    ls.add(new Event(getDescription(arr), getDeadline(arr)));
                    System.out.println("Now you have " + ls.size() + " tasks in the list.");
                }
            }
            else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for(int i = 0; i < ls.size(); i++) {
                    System.out.println((i + 1) + "." + ls.get(i).toString());
                }
            }
            else {
                System.out.println("added: " + input);
                ls.add(new Task(input));
            }
        } while (!input.equals("bye"));
    }
}
