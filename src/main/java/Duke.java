import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> list = new ArrayList<>();

    private static void greet() {
        System.out.println("Hello! I'm Duke created by Tianyue\n" +
                "What can I do for you?");
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void addTask(Task task) {
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        if (list.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
        }
    }

    private static void list() {
        if (list.isEmpty()) {
            System.out.println("You have no task for now.");
            return;
        }

        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("%d. %s",
                    i + 1, list.get(i)));
        }
    }

    private static void setAsDone(int index) {
        list.get(index - 1).maskAsDone();

        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + list.get(index - 1));
    }

    public static void main(String[] args) {
        greet();

        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();


        while(!text.isEmpty()) {


            //mark as done
            if (text.startsWith("done")) {
                char last_digit = text.charAt(text.length() - 1);
                int index = Character.getNumericValue(last_digit);
                setAsDone(index);
                text = scanner.nextLine();
            }

            //list
            else if (text.equals("list")) {
                list();
                text = scanner.nextLine();
            }

            //exit program
            else if (text.equals("bye")) {
                bye();
                break;
            }

            //add new task
            else {
                Task newTask = null;
                if (text.contains("deadline")) {
                    int istart = text.indexOf(" ");
                    int iend = text.indexOf("/");
                    String description = text.substring(istart , iend);
                    String date = text.substring(iend + 4);
                    newTask = new Deadline(description, date);
                }

                else if (text.contains("event")) {
                    int istart = text.indexOf(" ");
                    int iend = text.indexOf("/");
                    String description = text.substring(istart , iend);
                    String date = text.substring(iend + 4);
                    newTask = new Event(description, date);
                }

                else if (text.contains("todo")) {
                    int istart = text.indexOf(" ");
                    String description = text.substring(istart);
                    newTask = new Todo(description);
                }

                list.add(newTask);
                addTask(newTask);

                text = scanner.nextLine();

            }
        }
    }


}
