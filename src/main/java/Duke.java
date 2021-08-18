import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> myList = new ArrayList<>();
    public static boolean done = false;

    private static void endBot() {
        done = true;
        System.out.println("Bye for now!");
    }

    private static void listTasks() {
        System.out.println("Here is your to-do list!");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(i + 1 + ". " + myList.get(i).toString());
        }
    }

    private static void addTask(String input) {
        try {
            if (input.startsWith("todo")) {
                String remaining = input.substring(5);
                myList.add(new ToDo(remaining));
            } else if (input.startsWith("deadline")) {
                String remaining = input.substring(9);
                String[] segments = remaining.split(" /");
                myList.add(new Deadline(segments[0], segments[1]));
            } else if (input.startsWith("event")) {
                String remaining = input.substring(6);
                String[] segments = remaining.split(" /");
                myList.add(new Event(segments[0], segments[1]));
            } else {
                System.out.println("Invalid task. Please specify the type of task.");
                return;
            }
            System.out.println("I've added this task:");
            System.out.println(myList.get(myList.size() - 1));
            System.out.println("Now you have " + myList.size() + " tasks.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid format. Please follow this format. <type of task> <description> /<date if necessary>");
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Invalid input. Please type something after specifying the type of task.");
        }
    }

    private static void deleteTask(String input) {
        String[] segments = input.split(" ");
        try {
            int index = Integer.parseInt(segments[segments.length - 1]);
            Task myTask = myList.get(index - 1);
            myList.remove(index - 1);
            System.out.println("I've deleted this task:\n[X] " + myTask.description);
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after the keyword: delete");
        } catch (IndexOutOfBoundsException | NullPointerException e ) {
            System.out.println("Please input a valid task index");
        }
    }


    private static void markAsDone(String input) {
        String[] segments = input.split(" ");
        try {
            int index = Integer.parseInt(segments[segments.length - 1]);
            Task myTask = myList.get(index - 1);
            myTask.markAsDone();
            System.out.println("I've marked this task as done:\n[X] " + myTask.description);
        } catch (NumberFormatException e) {
            System.out.println("Please input a number after the keyword: done");
        } catch (IndexOutOfBoundsException | NullPointerException e ) {
            System.out.println("Please input a valid task index");
        }
    }

    public static void main(String[] args) {
        System.out.println("Sup! I'm Luka, your personal assistant.\n");
        Scanner myScanner = new Scanner(System.in);
        while (!done) {
            String input = myScanner.nextLine();
            if (input.equals("bye")) {
                endBot();
            } else if (input.equals("list")) {
                listTasks();
            } else if (input.startsWith("done")) {
                markAsDone(input);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else {
                addTask(input);
            }
        }
    }
}
